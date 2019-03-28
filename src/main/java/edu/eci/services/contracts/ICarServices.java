package edu.eci.services.contracts;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.eci.models.Car;
import edu.eci.models.User;
import edu.eci.persistences.RepositoryException;

@Service
public interface ICarServices {
	
	List<Car> list() throws RepositoryException;
	Car create(Car car) throws RepositoryException;
	Car getByLicence(String licencePlate) throws RepositoryException;
	List<Car> getByBrand(String brand) throws RepositoryException;
	void delete(String licencePlate) throws RepositoryException;
	void updateCar(Car car) throws RepositoryException;

}
