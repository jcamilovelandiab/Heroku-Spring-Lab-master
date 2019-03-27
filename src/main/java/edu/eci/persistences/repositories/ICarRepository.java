package edu.eci.persistences.repositories;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import edu.eci.models.Car;
import edu.eci.models.User;
import edu.eci.persistences.RepositoryException;

@Repository
public interface ICarRepository extends DAO<Car, String>{
	Car getCarByLicensePlate(String licensePlate) throws RepositoryException;
}
