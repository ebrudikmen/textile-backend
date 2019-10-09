package app.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

    int save(T t) throws SQLException;

    boolean update(T t) throws SQLException;

    boolean delete(int id) throws SQLException;

    T get(int id) throws SQLException;

    List<T> getAll() throws SQLException;
}