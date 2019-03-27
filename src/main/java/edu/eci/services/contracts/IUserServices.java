package edu.eci.services.contracts;

import edu.eci.models.User;
import edu.eci.persistences.RepositoryException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IUserServices {
    List<User> list() throws RepositoryException;
    User create(User user) throws RepositoryException;
    User get(UUID id) throws RepositoryException;
    User get(String name) throws RepositoryException;
	void delete(UUID id) throws RepositoryException;
	void updateUser(User user) throws RepositoryException;
}
