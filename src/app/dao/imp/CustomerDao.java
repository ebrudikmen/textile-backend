package app.dao.imp;

import app.dao.Dao;
import app.database.DatabaseConnection;
import app.model.Customer;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao extends DatabaseConnection implements Dao<Customer> {

    // sql
    private static final String SQL_SELECT = "SELECT * FROM customer WHERE customer_id=?";
    private static final String SQL_DELETE = "DELETE FROM customer WHERE customer_id=?";
    private static final String SQL_UPDATE = "UPDATE customer SET name=?, surname=?, date=? WHERE customer_id=?";
    private static final String SQL_INSERT = "INSERT INTO customer(name, surname, date) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM customer";

    public CustomerDao(String url, String driver, String username, String password) throws ClassNotFoundException, SQLException {
        super(url, driver, username, password);
        this.connect();
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(SQL_SELECT_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int customerId = resultSet.getInt("customer_id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            Date date = resultSet.getDate("date");
            Customer temp = new Customer();
            temp.setId(customerId);
            temp.setDate(date);
            temp.setName(name);
            temp.setSurname(surname);
            customers.add(temp);
        }
        return customers;
    }

    @Override
    public int save(Customer customer) throws SQLException {
        int customerId = -1;
        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getSurname());
        preparedStatement.setDate(3, new Date(customer.getDate().getTime()));
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected == 1) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next())
                customerId = resultSet.getInt(1);
        } else
            return -1;
        return customerId;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(SQL_UPDATE);
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getSurname());
        preparedStatement.setDate(3, new Date(customer.getDate().getTime()));
        preparedStatement.setInt(4, customer.getId());
        int rowAffected = preparedStatement.executeUpdate();
        return rowAffected > 0;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(SQL_DELETE);
        preparedStatement.setInt(1, id);
        int rowAffected = preparedStatement.executeUpdate();
        return rowAffected > 0;
    }

    @Override
    public Customer get(int id) throws SQLException {
        Customer temp = null;
        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(SQL_SELECT);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int customerId = resultSet.getInt("customer_id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            Date date = resultSet.getDate("date");
            temp = new Customer();
            temp.setId(customerId);
            temp.setDate(date);
            temp.setName(name);
            temp.setSurname(surname);
        }
        return temp;
    }
}
