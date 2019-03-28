package edu.eci.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.eci.models.Car;
import edu.eci.persistences.RepositoryException;
import edu.eci.persistences.repositories.ICarRepository;
import edu.eci.persistences.repositories.IUserRepository;
import edu.eci.services.contracts.ICarServices;

@Component
public class CarServices implements ICarServices {

	
    @Autowired
    @Qualifier("CarPostgresRepository")
    private ICarRepository carRepository;
	
	@Override
	public List<Car> list() throws RepositoryException {
		return carRepository.findAll();
	}

	@Override
	public Car create(Car car) throws RepositoryException {
		return carRepository.save(car);
	}

	@Override
	public Car getByLicence(String licencePlate) throws RepositoryException {
		return carRepository.getCarByLicensePlate(licencePlate);
	}

	@Override
	public List<Car> getByBrand(String brand) throws RepositoryException {
		return carRepository.getByBrand(brand);
	}

	@Override
	public void delete(String licencePlate) throws RepositoryException {
		carRepository.delete(licencePlate);;
	}

	@Override
	public void updateCar(Car car) throws RepositoryException {
		carRepository.update(car);
	}

}
