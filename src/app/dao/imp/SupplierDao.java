package app.dao.imp;

import app.dao.Dao;
import app.database.DatabaseConnection;
import app.model.Supplier;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDao extends DatabaseConnection implements Dao<Supplier> {

    // sql
    private static final String SQL_DELETE = "DELETE FROM supplier WHERE supplier_id=?";
    private static final String SQL_INSERT = "INSERT INTO supplier(name, surname, date) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE supplier SET name=?, surname=?, date=? WHERE supplier_id=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM supplier";
    private static final String SQL_SELECT = "SELECT * FROM supplier WHERE supplier_id=?";

    public SupplierDao(String url, String driver, String username, String password) throws ClassNotFoundException, SQLException {
        super(url, driver, username, password);
        this.connect();
    }

    @Override
    public int save(Supplier supplier) throws SQLException {
        int supplierId = -1;
        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, supplier.getName());
        preparedStatement.setString(2, supplier.getSurname());
        preparedStatement.setDate(3, new Date(supplier.getDate().getTime()));
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected == 1) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next())
                supplierId = resultSet.getInt(1);
        } else
            return -1;
        return supplierId;
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(SQL_UPDATE);
        preparedStatement.setString(1, supplier.getName());
        preparedStatement.setString(2, supplier.getSurname());
        preparedStatement.setDate(3, new Date(supplier.getDate().getTime()));
        preparedStatement.setInt(4, supplier.getSupplierId());
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
    public Supplier get(int id) throws SQLException {
        Supplier tempSupplier = null;
        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(SQL_SELECT);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int supplierId = resultSet.getInt("Supplier_id");
            String name = resultSet.getString("Name");
            String surname = resultSet.getString("Surname");
            Date date = resultSet.getDate("Date");
            tempSupplier = new Supplier();
            tempSupplier.setSupplierId(supplierId);
            tempSupplier.setName(name);
            tempSupplier.setSurname(surname);
            tempSupplier.setDate(date);
        }
        return tempSupplier;
    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(SQL_SELECT_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int supplierId = resultSet.getInt("supplier_id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            Date date = resultSet.getDate("date");
            Supplier tempSupplier = new Supplier();
            tempSupplier.setSupplierId(supplierId);
            tempSupplier.setName(name);
            tempSupplier.setSurname(surname);
            tempSupplier.setDate(date);
            suppliers.add(tempSupplier);
        }
        return suppliers;

    }
}
