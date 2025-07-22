package api.commerce.express.repository;

import api.commerce.express.domain.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb_${random.uuid}",
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.jpa.show-sql=true"
})
public class AddressCrudServiceIntegrationTests {

    @Autowired
    private IAddressCrudService addressCrudService;

    private Address createTestAddress() {
        Address address = new Address();
        address.setPostalAddress("Test Street, Test City");
        address.setPhysicalAddress("123 Test Street, Test City");
        address.setPostalCode("12345");
        return address;
    }

    @Test
    @DisplayName("Should create a new address successfully")
    public void shouldCreateAddress_WhenValidAddressIsProvided() {
        // Given
        Address testAddress = createTestAddress();

        // When
        Address savedAddress = addressCrudService.save(testAddress);
        addressCrudService.flush();

        // Then
        assertThat(savedAddress).isNotNull();
        assertThat(savedAddress.getId()).isNotNull();
        assertThat(savedAddress.getPostalAddress()).isEqualTo(testAddress.getPostalAddress());
        assertThat(savedAddress.getPhysicalAddress()).isEqualTo(testAddress.getPhysicalAddress());
        assertThat(savedAddress.getPostalCode()).isEqualTo(testAddress.getPostalCode());
    }

    @Test
    @DisplayName("Should find address by ID when address exists")
    public void shouldFindAddressById_WhenAddressExists() {
        // Given
        Address testAddress = createTestAddress();
        Address savedAddress = addressCrudService.save(testAddress);
        addressCrudService.flush();

        // When
        Optional<Address> foundAddress = addressCrudService.findById(savedAddress.getId());

        // Then
        assertThat(foundAddress).isPresent();
        assertThat(foundAddress.get().getId()).isEqualTo(savedAddress.getId());
        assertThat(foundAddress.get().getPostalAddress()).isEqualTo(testAddress.getPostalAddress());
        
        System.out.println("Found address: " + foundAddress.get());
    }

    @Test
    @DisplayName("Should return empty when finding address by non-existent ID")
    public void shouldReturnEmpty_WhenFindingAddressByNonExistentId() {
        // When
        Optional<Address> foundAddress = addressCrudService.findById(999L);

        // Then
        assertThat(foundAddress).isEmpty();
    }

    @Test
    @DisplayName("Should find all addresses when addresses exist")
    public void shouldFindAllAddresses_WhenAddressesExist() {
        // Given
        Address address1 = createTestAddress();
        Address address2 = createTestAddress();

        addressCrudService.save(address1);
        addressCrudService.save(address2);
        addressCrudService.flush();

        // When
        List<Address> allAddresses = addressCrudService.findAll();

        // Then
        assertThat(allAddresses).hasSize(2);
        assertThat(allAddresses).extracting("postalAddress")
                .anyMatch(postalAddress -> postalAddress.toString().contains("Test Street"));
    }

    @Test
    @DisplayName("Should return empty list when no addresses exist")
    public void shouldReturnEmptyList_WhenNoAddressesExist() {
        // When
        List<Address> allAddresses = addressCrudService.findAll();

        // Then
        assertThat(allAddresses).isEmpty();
    }

    @Test
    @DisplayName("Should update address successfully")
    public void shouldUpdateAddress_WhenValidAddressIsProvided() {
        // Given
        Address testAddress = createTestAddress();
        Address savedAddress = addressCrudService.save(testAddress);
        addressCrudService.flush();

        // When
        savedAddress.setPostalAddress("Updated Address");
        savedAddress.setPostalCode("54321");
        Address updatedAddress = addressCrudService.save(savedAddress);
        addressCrudService.flush();

        // Then
        assertThat(updatedAddress.getId()).isEqualTo(savedAddress.getId());
        assertThat(updatedAddress.getPostalAddress()).isEqualTo("Updated Address");
        assertThat(updatedAddress.getPostalCode()).isEqualTo("54321");
        assertThat(updatedAddress.getPhysicalAddress()).isEqualTo(testAddress.getPhysicalAddress());
        
        System.out.println("Updated address with ID: " + updatedAddress.getId());
    }

    @Test
    @DisplayName("Should delete address successfully")
    public void shouldDeleteAddress_WhenAddressExists() {
        // Given
        Address testAddress = createTestAddress();
        Address savedAddress = addressCrudService.save(testAddress);
        addressCrudService.flush();

        System.out.println("Deleting address with ID: " + savedAddress.getId());

        // When
        addressCrudService.deleteById(savedAddress.getId());
        addressCrudService.flush();

        // Then
        Optional<Address> deletedAddress = addressCrudService.findById(savedAddress.getId());
        assertThat(deletedAddress).isEmpty();
        
        System.out.println("Address deleted successfully");
    }

    @Test
    @DisplayName("Should count addresses correctly")
    public void shouldCountAddresses_Correctly() {
        // Given
        Address testAddress1 = createTestAddress();
        Address testAddress2 = createTestAddress();

        addressCrudService.save(testAddress1);
        addressCrudService.save(testAddress2);
        addressCrudService.flush();

        // When
        long count = addressCrudService.count();

        // Then
        assertThat(count).isEqualTo(2);
        
        System.out.println("Counted " + count + " addresses");
    }

    @Test
    @DisplayName("Should check if address exists by ID")
    public void shouldCheckIfAddressExists_ByID() {
        // Given
        Address testAddress = createTestAddress();
        Address savedAddress = addressCrudService.save(testAddress);

        System.out.println("Checking existence of address with ID: " + savedAddress.getId());

        // When & Then
        boolean exists = addressCrudService.existsById(savedAddress.getId());
        System.out.println("Address exists: " + exists);
        
        assertThat(exists).isTrue();
        assertThat(addressCrudService.existsById(999L)).isFalse();
    }

    @Test
    @DisplayName("Should delete all addresses")
    public void shouldDeleteAllAddresses() {
        // Given
        Address testAddress1 = createTestAddress();
        Address testAddress2 = createTestAddress();

        addressCrudService.save(testAddress1);
        addressCrudService.save(testAddress2);

        System.out.println("Deleting all addresses...");

        // When
        addressCrudService.deleteAll();
        addressCrudService.flush();

        // Then
        assertThat(addressCrudService.count()).isEqualTo(0);
        assertThat(addressCrudService.findAll()).isEmpty();
        
        System.out.println("All addresses deleted successfully");
    }

    @Test
    @DisplayName("Should save multiple addresses in batch")
    public void shouldSaveMultipleAddressesInBatch() {
        // Given
        Address address1 = createTestAddress();
        Address address2 = createTestAddress();
        Address address3 = createTestAddress();

        List<Address> addresses = List.of(address1, address2, address3);

        System.out.println("Saving " + addresses.size() + " addresses in batch...");

        // When
        List<Address> savedAddresses = addressCrudService.saveAll(addresses);

        // Then
        assertThat(savedAddresses).hasSize(3);
        assertThat(savedAddresses).allMatch(address -> address.getId() != null);
        
        long count = addressCrudService.count();
        System.out.println("Saved " + savedAddresses.size() + " addresses, count shows: " + count);
        
        assertThat(count).isEqualTo(3);
    }

    @Test
    @DisplayName("Should delete multiple addresses by IDs")
    public void shouldDeleteMultipleAddressesByIds() {
        // Given
        Address address1 = createTestAddress();
        Address address2 = createTestAddress();

        Address saved1 = addressCrudService.save(address1);
        Address saved2 = addressCrudService.save(address2);
        addressCrudService.flush();

        List<Long> idsToDelete = List.of(saved1.getId(), saved2.getId());

        System.out.println("Deleting addresses with IDs: " + idsToDelete);

        // When
        addressCrudService.deleteAllById(idsToDelete);
        addressCrudService.flush();

        // Then
        assertThat(addressCrudService.existsById(saved1.getId())).isFalse();
        assertThat(addressCrudService.existsById(saved2.getId())).isFalse();
        
        System.out.println("Multiple addresses deleted successfully");
    }
} 