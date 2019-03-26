package br.com.seniorsistemas.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;

public abstract class GenericService<T, ID> {

    private final JpaRepository<T, ID> repository;

    public GenericService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    public T create(T entity) {
        return this.repository.save(entity);
    }

    public T update(T entity) {
        return this.repository.save(entity);
    }

    public void remove(ID id) {
        this.repository.deleteById(id);
    }

    public T getById(ID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    public Page<T> pagination(int page, int count, Sort.Direction direction, String property) {
        return this.repository.
                findAll(PageRequest.of(page, count, new Sort(direction, property)));
    }

}
