package edu.eci.persistences.repositories;

import edu.eci.persistences.RepositoryException;

import java.io.Serializable;
import java.util.List;

public interface DAO<T extends Serializable, PK> {
    public List<T> findAll() throws RepositoryException;
    public T find(PK id) throws RepositoryException;
    public T save(T entity) throws RepositoryException;
    public void update(T entity) throws RepositoryException;
    public void delete(T o) throws RepositoryException;
    public void delete(PK o) throws RepositoryException;
    public void remove(Long id) throws RepositoryException;
    public void remove(String licensePlate) throws RepositoryException;
}
