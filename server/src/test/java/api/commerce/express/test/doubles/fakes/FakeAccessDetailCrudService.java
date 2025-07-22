package api.commerce.express.test.doubles.fakes;

import api.commerce.express.domain.AccessDetail;
import api.commerce.express.repository.IAccessDetailsCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class FakeAccessDetailCrudService implements IAccessDetailsCrudService {

    List<AccessDetail> accessDetails = new ArrayList<>();

    public void addAccessDetail(AccessDetail accessDetail) {
        accessDetails.add(accessDetail);
    }

    @Override
    public Page<AccessDetail> findAll(@NotNull Pageable pageable) {
        int start = Integer.parseInt(String.valueOf(pageable.getOffset()));
        int end   = Math.min(start + pageable.getPageSize(), accessDetails.size());
        return new PageImpl<>(accessDetails.subList(start, end), pageable, accessDetails.size());
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends AccessDetail> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends AccessDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<AccessDetail> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public AccessDetail getOne(Long aLong) {
        return null;
    }

    @Override
    public AccessDetail getById(Long aLong) {
        return null;
    }

    @Override
    public AccessDetail getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends AccessDetail> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends AccessDetail> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends AccessDetail> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends AccessDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends AccessDetail> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends AccessDetail> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends AccessDetail, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends AccessDetail> S save(S entity) {
        return null;
    }

    @Override
    public <S extends AccessDetail> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<AccessDetail> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<AccessDetail> findAll() {
        return List.of();
    }

    @Override
    public List<AccessDetail> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(AccessDetail entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends AccessDetail> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<AccessDetail> findAll(Sort sort) {
        return List.of();
    }
}
