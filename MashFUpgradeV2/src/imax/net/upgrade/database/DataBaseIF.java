package imax.net.upgrade.database;

import java.sql.ResultSet;

public interface DataBaseIF {
    void open();

    void close();

    void createTable();

    boolean hasConnection();

    ResultSet pesquisa(String var1);

    boolean execute(String var1);

    String getName();
}