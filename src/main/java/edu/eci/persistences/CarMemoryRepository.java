package edu.eci.persistences;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;

@Component
@Qualifier("CarMemoryRepository")
public class CarMemoryRepository implements  ICarRepository {

    public static List<Car> carsContainer;
    
    public static List<Car> getContainer(){
        if(CarMemoryRepository.carsContainer == null)
            CarMemoryRepository.carsContainer = new ArrayList<>();
        return CarMemoryRepository.carsContainer;
    }

    @Override
	public Car getCarByLicensePlate(String licensePlate) {
		return CarMemoryRepository.getContainer().stream().filter(
				c -> licensePlate.equals(c.getLicencePlate())).findFirst().get();
	}
    
    @Override
	public List<Car> findAll() {
		return CarMemoryRepository.getContainer();
	}

    
	@Override
	public Car find(String licencePlate) {
		Optional<Car> answer = CarMemoryRepository.getContainer()
                .stream()
                .filter(u -> licencePlate.equals(u.getLicencePlate()))
                .findFirst();
        return answer.isPresent() ? answer.get() : null;
	}

	@Override
	public Car save(Car entity) {
		CarMemoryRepository.getContainer().add(entity);
		return entity;
	}

	@Override
	public void update(Car entity) {
		CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
	                .stream()
	                .map(u -> u.getLicencePlate().equals(entity.getLicencePlate()) ? entity : u)
	                .collect(toList());
		
	}

	@Override
	public void delete(Car c) {
		CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
	                .stream()
	                .filter(u -> !u.getLicencePlate().equals(c.getLicencePlate()))
	                .collect(toList());
	}


	@Override
	public void delete(String licensePlate) throws RepositoryException {
		CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
				.stream().filter(c-> !c.getLicencePlate().equals(licensePlate)).collect(toList());		
	}


	@Override
	public List<Car> getByBrand(String brand) throws RepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
