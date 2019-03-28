package edu.eci.persistences;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.eci.config.DataBases;
import edu.eci.models.User;
import edu.eci.persistences.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Qualifier("UserPostgresRepository")
public class UserPostgresRepository implements IUserRepository {


	@Autowired
	private DataBases database;

	@Override
	public User getUserByUserName(String userName) throws RepositoryException {
		String query = String.format("SELECT * FROM users where name = '%s'", userName);
		User user = null;
		try {
			Connection connection = database.basicDataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				user = new User();
				user.setId(UUID.fromString(resultSet.getString("id")));
				user.setName(resultSet.getString("name"));
			}
			if (user == null) {
				throw new RepositoryException("NOT FOUND");
			}
			connection.close();
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<User> findAll() {
		String query = "SELECT * FROM users";
		List<User> users = new ArrayList<>();
		try (Connection connection = database.basicDataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				User user = new User();
				user.setId(UUID.fromString(rs.getString("id")));
				user.setName(rs.getString("nombre"));
				users.add(user);
			}
			return users;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public User find(UUID id) throws RepositoryException {
		String query = String.format("SELECT * FROM users where id = '%s'", id.toString());
		User user = null;
		try {
			Connection connection = database.basicDataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				user = new User();
				user.setId(UUID.fromString(resultSet.getString("id")));
				user.setName(resultSet.getString("nombre"));
			}
			connection.close();
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User save(User entity) {
		String query = String.format("INSERT INTO users(id,nombre) VALUES('%s','%s')", entity.getId().toString(),
				entity.getName());
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
	public void update(User entity) throws RepositoryException {
		try {
			String query = String.format("UPDATE users SET nombre = '%s' WHERE id = '%s'", entity.getName(),
					entity.getId().toString());
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
	public void delete(User entity) throws RepositoryException {
		try {
			User user = this.find(entity.getId());
			String query = String.format("UPDATE users SET nombre = '%s' WHERE id = '%s'", entity.getName(),entity.getId().toString());
			Connection connnection = database.basicDataSource.getConnection();
			Statement statement = connnection.createStatement();
			statement.execute(query);
			connnection.close();
		} catch (RepositoryException re) {
			throw new RepositoryException("NOT FOUND");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}



	

	@Override
	public void delete(UUID o) throws RepositoryException {
		try {
			String query = String.format("DELETE FROM users WHERE id = '%s'",o.toString());
			Connection connnection = database.basicDataSource.getConnection();
			Statement statement = connnection.createStatement();
			statement.execute(query);
			connnection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
