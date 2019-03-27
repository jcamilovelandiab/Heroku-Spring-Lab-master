package edu.eci.services;

import edu.eci.models.User;
import edu.eci.persistences.RepositoryException;
import edu.eci.persistences.repositories.IUserRepository;
import edu.eci.services.contracts.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserServices implements IUserServices{

    @Autowired
    @Qualifier("UserPostgresRepository")
    private IUserRepository userRepository;

    @Override
    public List<User> list() throws RepositoryException {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) throws RepositoryException {
        if(null == user.getId())
            throw new RuntimeException("Id invalid");
        else if(userRepository.find(user.getId()) != null)
            throw new RuntimeException("The user exists");
        else {
        	userRepository.save(user);
            return user;	
        }
            
    }

    @Override
    public User get(UUID id) throws RepositoryException {
        return userRepository.find(id);
    }

    @Override
    public User get(String name) throws RepositoryException {
        return userRepository.getUserByUserName(name);
    }

	@Override
	public void updateUser(User user) throws RepositoryException {
		if(null == user.getId())
            throw new RuntimeException("Id invalid");
        else {
        	userRepository.update(user);
        }
	}

	@Override
	public void delete(UUID id) throws RepositoryException {
		if(userRepository.find(id) == null)
	          throw new RuntimeException("The don't user exists");
        else {
        	userRepository.delete(id);
        }
	}
	
}
