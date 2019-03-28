package edu.eci.persistences;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.eci.config.DataBases;
import edu.eci.models.Car;
import edu.eci.models.User;
import edu.eci.persistences.repositories.ICarRepository;
import edu.eci.persistences.repositories.IUserRepository;


@Component
@Qualifier("CarPostgresRepository")
public class CarPostgresRepository implements ICarRepository {

	@Autowired
	private DataBases database;

	@Override
	public List<Car> findAll() throws RepositoryException {
		String query = String.format("SELECT * FROM cars");
		List<Car> cars = new ArrayList<>();
		try {
			Connection connection = database.basicDataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Car car = new Car();
				car.setBrand(resultSet.getString("brand"));
				car.setLicencePlate(resultSet.getString("licenceplate"));
				cars.add(car);
			}
			connection.close();
			return cars;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Car find(String id) throws RepositoryException {
		return this.getCarByLicensePlate(id);
	}

	@Override
	public Car save(Car entity) throws RepositoryException {
		String query = String.format("INSERT INTO cars(brand,licenceplate) VALUES('%s','%s')",
				entity.getBrand().toString(), entity.getLicencePlate());
		try {
			Connection connnection = database.basicDataSource.getConnection();
			Statement statement = connnection.createStatement();
			statement.execute(query);
			connnection.close();
			return entity;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Car entity) throws RepositoryException {
		try {
			String query = String.format("UPDATE cars SET brand = '%s' WHERE licenceplate = '%s'", entity.getBrand(),
					entity.getLicencePlate());
			System.out.println(query);
			Connection connnection = database.basicDataSource.getConnection();
			Statement statement = connnection.createStatement();
			statement.execute(query);
			connnection.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void delete(Car o) throws RepositoryException {
		String query = String.format("DELETE FROM cars WHERE licenceplate  = '%s'", o.getLicencePlate());
		try {
			Connection connection = database.basicDataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.execute(query);
			connection.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void delete(String licensePlate) throws RepositoryException {
		String query = String.format("DELETE FROM cars WHERE licenceplate  = '%s'", licensePlate);
		try {
			Connection connection = database.basicDataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.execute(query);
			connection.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Car getCarByLicensePlate(String licensePlate) throws RepositoryException {
		String query = String.format("SELECT * FROM cars where licenceplate = '%s'", licensePlate);
		try {
			Connection connection = database.basicDataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			Car car = null;
			while (resultSet.next()) {
				car = new Car();
				car.setBrand(resultSet.getString("brand"));
				car.setLicencePlate(resultSet.getString("licenceplate"));
			}
			connection.close();
			return car;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Car> getByBrand(String brand) throws RepositoryException {
		String query = String.format("SELECT * FROM cars where brand = '%s'", brand);
		try {
			Connection connection = database.basicDataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			List<Car> cars = new ArrayList<>();
			while (resultSet.next()) {
				Car car = new Car();
				car.setBrand(resultSet.getString("brand"));
				car.setLicencePlate(resultSet.getString("licenceplate"));
				cars.add(car);
			}
			connection.close();
			return cars;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
