#!/usr/bin/env bash

# =============================================================================
# Mutation Testing Watcher Script
# Supports: Java (PIT), TypeScript (Stryker), PHP (Infection)
# Usage: ./mutation-watcher.sh [watch|run|setup]
# =============================================================================

set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(dirname "$SCRIPT_DIR")"

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
CYAN='\033[0;36m'
NC='\033[0m'

log_info() { echo -e "${BLUE}[INFO]${NC} $1"; }
log_success() { echo -e "${GREEN}[SUCCESS]${NC} $1"; }
log_warn() { echo -e "${YELLOW}[WARN]${NC} $1"; }
log_error() { echo -e "${RED}[ERROR]${NC} $1"; }
log_header() { echo -e "\n${CYAN}═══════════════════════════════════════════════════════════${NC}"; echo -e "${CYAN}  $1${NC}"; echo -e "${CYAN}═══════════════════════════════════════════════════════════${NC}\n"; }

# Detect project type
detect_project_type() {
    if [[ -f "$PROJECT_ROOT/pom.xml" ]] || [[ -f "$PROJECT_ROOT/server/pom.xml" ]]; then
        echo "java"
    elif [[ -f "$PROJECT_ROOT/package.json" ]]; then
        if grep -q "typescript" "$PROJECT_ROOT/package.json" 2>/dev/null; then
            echo "typescript"
        else
            echo "javascript"
        fi
    elif [[ -f "$PROJECT_ROOT/composer.json" ]]; then
        echo "php"
    else
        echo "unknown"
    fi
}

# Get Maven directory
get_maven_dir() {
    if [[ -d "$PROJECT_ROOT/server" ]]; then
        echo "$PROJECT_ROOT/server"
    else
        echo "$PROJECT_ROOT"
    fi
}

# =============================================================================
# JAVA (PIT) Functions
# =============================================================================

setup_java_pit() {
    log_header "Setting up Java PIT Incremental Analysis"

    local maven_dir=$(get_maven_dir)
    local pit_history_dir="$maven_dir/.pit-history"

    mkdir -p "$pit_history_dir"
    log_info "Created history directory: $pit_history_dir"

    # Check if PIT plugin is in pom.xml
    if ! grep -q "pitest-maven" "$maven_dir/pom.xml" 2>/dev/null; then
        log_warn "PIT plugin not found in pom.xml"
        log_info "Add this to your pom.xml build/plugins section:"
        cat << 'EOF'

<plugin>
    <groupId>org.pitest</groupId>
    <artifactId>pitest-maven</artifactId>
    <version>1.15.3</version>
    <dependencies>
        <dependency>
            <groupId>org.pitest</groupId>
            <artifactId>pitest-junit5-plugin</artifactId>
            <version>1.2.1</version>
        </dependency>
    </dependencies>
    <configuration>
        <historyInputFile>${project.basedir}/.pit-history/history.bin</historyInputFile>
        <historyOutputFile>${project.basedir}/.pit-history/history.bin</historyOutputFile>
        <withHistory>true</withHistory>
        <threads>4</threads>
        <outputFormats>
            <format>XML</format>
            <format>HTML</format>
        </outputFormats>
        <timestampedReports>false</timestampedReports>
    </configuration>
</plugin>
EOF
        echo ""
    else
        log_success "PIT plugin found in pom.xml"
    fi

    # Add .pit-history to gitignore if not present
    local gitignore="$PROJECT_ROOT/.gitignore"
    if [[ -f "$gitignore" ]]; then
        if ! grep -q ".pit-history" "$gitignore"; then
            echo ".pit-history/" >> "$gitignore"
            log_info "Added .pit-history/ to .gitignore"
        fi
    fi

    log_success "Java PIT setup complete"
}

run_java_mutation() {
    log_header "Running PIT Mutation Tests (Incremental)"

    local maven_dir=$(get_maven_dir)
    cd "$maven_dir"

    local start_time=$(date +%s)

    # Run PIT with incremental history
    mvn -B org.pitest:pitest-maven:mutationCoverage \
        -DhistoryInputLocation=.pit-history/history.bin \
        -DhistoryOutputLocation=.pit-history/history.bin \
        -DwithHistory=true \
        -Dthreads=4

    local end_time=$(date +%s)
    local duration=$((end_time - start_time))

    log_success "Mutation testing complete in ${duration}s"
    log_info "Report: $maven_dir/target/pit-reports/index.html"

    # Open report if on macOS
    if [[ "$OSTYPE" == "darwin"* ]] && [[ -f "$maven_dir/target/pit-reports/index.html" ]]; then
        read -p "Open report in browser? (y/n) " -n 1 -r
        echo
        if [[ $REPLY =~ ^[Yy]$ ]]; then
            open "$maven_dir/target/pit-reports/index.html"
        fi
    fi
}

# =============================================================================
# TypeScript (Stryker) Functions
# =============================================================================

setup_typescript_stryker() {
    log_header "Setting up TypeScript Stryker"

    cd "$PROJECT_ROOT"

    if ! grep -q "@stryker-mutator" "package.json" 2>/dev/null; then
        log_info "Installing Stryker..."
        npm install --save-dev @stryker-mutator/core @stryker-mutator/typescript-checker @stryker-mutator/jest-runner
    fi

    if [[ ! -f "stryker.config.mjs" ]] && [[ ! -f "stryker.conf.js" ]]; then
        log_info "Creating stryker.config.mjs..."
        cat > stryker.config.mjs << 'EOF'
/** @type {import('@stryker-mutator/api/core').PartialStrykerOptions} */
const config = {
  packageManager: "npm",
  reporters: ["html", "clear-text", "progress"],
  testRunner: "jest",
  coverageAnalysis: "perTest",
  checkers: ["typescript"],
  tsconfigFile: "tsconfig.json",
  incremental: true,
  incrementalFile: ".stryker-incremental.json"
};

export default config;
EOF
        log_info "Added .stryker-incremental.json to .gitignore"
        echo ".stryker-incremental.json" >> .gitignore
    fi

    log_success "Stryker setup complete"
}

run_typescript_mutation() {
    log_header "Running Stryker Mutation Tests (Incremental)"

    cd "$PROJECT_ROOT"

    local start_time=$(date +%s)
    npx stryker run --incremental
    local end_time=$(date +%s)
    local duration=$((end_time - start_time))

    log_success "Mutation testing complete in ${duration}s"
    log_info "Report: reports/mutation/html/index.html"
}

# =============================================================================
# PHP (Infection) Functions
# =============================================================================

setup_php_infection() {
    log_header "Setting up PHP Infection"

    cd "$PROJECT_ROOT"

    if ! grep -q "infection/infection" "composer.json" 2>/dev/null; then
        log_info "Installing Infection..."
        composer require --dev infection/infection
    fi

    if [[ ! -f "infection.json.dist" ]] && [[ ! -f "infection.json" ]]; then
        log_info "Creating infection.json.dist..."
        cat > infection.json.dist << 'EOF'
{
    "$schema": "https://raw.githubusercontent.com/infection/infection/master/resources/schema.json",
    "source": {
        "directories": ["src", "apps", "libs"]
    },
    "logs": {
        "html": "infection.html",
        "text": "infection.log"
    },
    "mutators": {
        "@default": true
    }
}
EOF
    fi

    log_success "Infection setup complete"
}

run_php_mutation() {
    log_header "Running Infection Mutation Tests"

    cd "$PROJECT_ROOT"

    local start_time=$(date +%s)
    vendor/bin/infection --threads=4 --show-mutations
    local end_time=$(date +%s)
    local duration=$((end_time - start_time))

    log_success "Mutation testing complete in ${duration}s"
    log_info "Report: infection.html"
}

# =============================================================================
# File Watcher
# =============================================================================

watch_files() {
    local project_type=$(detect_project_type)
    log_header "Mutation Testing Watcher"
    log_info "Project type: $project_type"

    # Setup based on project type
    local watch_dir=""
    local watch_pattern=""

    case $project_type in
        java)
            setup_java_pit
            watch_dir="$(get_maven_dir)/src"
            watch_pattern="*.java"
            ;;
        typescript|javascript)
            setup_typescript_stryker
            watch_dir="$PROJECT_ROOT/src"
            watch_pattern="*.ts"
            ;;
        php)
            setup_php_infection
            watch_dir="$PROJECT_ROOT"
            watch_pattern="*.php"
            ;;
        *)
            log_error "Unknown project type. Cannot determine mutation testing tool."
            exit 1
            ;;
    esac

    log_info "Watching: $watch_dir"
    log_info "Pattern: $watch_pattern"
    log_info "Press Ctrl+C to stop"
    echo ""

    # Find available watcher
    if command -v fswatch &> /dev/null; then
        log_info "Using fswatch (macOS)"
        fswatch -0 -r -e ".*" -i "\\.java$" -i "\\.ts$" -i "\\.php$" "$watch_dir" | while read -d "" file; do
            log_info "Changed: $file"
            run_mutation_for_type "$project_type"
        done
    elif command -v entr &> /dev/null; then
        log_info "Using entr"
        while true; do
            find "$watch_dir" -type f \( -name "*.java" -o -name "*.ts" -o -name "*.php" \) 2>/dev/null | \
                entr -d -p "$SCRIPT_DIR/mutation-watcher.sh" run
        done
    elif command -v inotifywait &> /dev/null; then
        log_info "Using inotifywait (Linux)"
        inotifywait -m -r -e modify --format '%w%f' "$watch_dir" | while read file; do
            if [[ "$file" =~ \.(java|ts|php)$ ]]; then
                log_info "Changed: $file"
                run_mutation_for_type "$project_type"
            fi
        done
    else
        log_error "No file watcher found!"
        log_info "Install one of:"
        log_info "  macOS:  brew install fswatch"
        log_info "  Linux:  apt install inotify-tools"
        log_info "  Any:    brew/apt install entr"
        exit 1
    fi
}

run_mutation_for_type() {
    local project_type="$1"
    case $project_type in
        java) run_java_mutation ;;
        typescript|javascript) run_typescript_mutation ;;
        php) run_php_mutation ;;
    esac
}

# =============================================================================
# Main CLI
# =============================================================================

show_help() {
    echo "Mutation Testing Watcher"
    echo ""
    echo "Usage: $0 [command]"
    echo ""
    echo "Commands:"
    echo "  watch    Watch for file changes and run mutation tests (default)"
    echo "  run      Run mutation tests once"
    echo "  setup    Setup mutation testing for the project"
    echo "  help     Show this help message"
    echo ""
    echo "Supported:"
    echo "  Java       → PIT (Pitest) with incremental history"
    echo "  TypeScript → Stryker with incremental mode"
    echo "  PHP        → Infection"
}

main() {
    local command="${1:-watch}"
    local project_type=$(detect_project_type)

    case "$command" in
        watch)
            watch_files
            ;;
        run)
            case $project_type in
                java) run_java_mutation ;;
                typescript|javascript) run_typescript_mutation ;;
                php) run_php_mutation ;;
                *) log_error "Unknown project type"; exit 1 ;;
            esac
            ;;
        setup)
            case $project_type in
                java) setup_java_pit ;;
                typescript|javascript) setup_typescript_stryker ;;
                php) setup_php_infection ;;
                *) log_error "Unknown project type"; exit 1 ;;
            esac
            ;;
        help|--help|-h)
            show_help
            ;;
        *)
            log_error "Unknown command: $command"
            show_help
            exit 1
            ;;
    esac
}

main "$@"
