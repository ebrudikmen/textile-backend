package app.dao.imp;

import app.dao.Dao;
import app.database.DatabaseConnection;
import app.model.Product;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends DatabaseConnection implements Dao<Product> {

    // sql
    private static final String SQL_INSERT = "INSERT INTO product(name, quantity, price) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM product";
    private static final String SQL_SELECT = "SELECT * FROM product WHERE product_id=?";
    private static final String SQL_DELETE = "DELETE FROM product WHERE product_id=?";
    private static final String SQL_UPDATE = "UPDATE product SET name=?, quantity=?, price=? WHERE product_id=?";

    public ProductDao(String url, String driver, String username, String password) throws ClassNotFoundException, SQLException {
        super(url, driver, username, password);
        this.connect();
    }

    @Override
    public int save(Product product) throws SQLException {
        int productId = -1;
        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, product.getProductName());
        preparedStatement.setInt(2, product.getQuantity());
        preparedStatement.setFloat(3, product.getPrice());
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected == 1) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next())
                productId = resultSet.getInt(1);
        } else
            return -1;
        return productId;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(SQL_UPDATE);
        preparedStatement.setString(1, product.getProductName());
        preparedStatement.setInt(2, product.getQuantity());
        preparedStatement.setFloat(3, product.getPrice());
        preparedStatement.setInt(4, product.getProductId());
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
    public Product get(int id) throws SQLException {
        Product tempProduct = null;
        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(SQL_SELECT);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int productId = resultSet.getInt("product_id");
            String name = resultSet.getString("name");
            int quantity = resultSet.getInt("quantity");
            int price = resultSet.getInt("price");
            tempProduct = new Product();
            tempProduct.setProductId(productId);
            tempProduct.setProductName(name);
            tempProduct.setQuantity(quantity);
            tempProduct.setPrice(price);
        }
        return tempProduct;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(SQL_SELECT_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int productId = resultSet.getInt("product_id");
            String name = resultSet.getString("name");
            int quantity = resultSet.getInt("quantity");
            float price = resultSet.getFloat("price");
            Product tempProduct = new Product();
            tempProduct.setProductId(productId);
            tempProduct.setProductName(name);
            tempProduct.setQuantity(quantity);
            tempProduct.setPrice(price);
            products.add(tempProduct);
        }
        return products;
    }
}