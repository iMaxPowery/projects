package imax.net.bans.database;

import java.sql.ResultSet;

public interface DBIF {
    void open();

    void close();

    void createTable();

    boolean hasConnection();

    ResultSet pesquisa(String var1);

    boolean execute(String var1);

    String getName();
}
