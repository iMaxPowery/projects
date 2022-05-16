package imax.net.bans.database.provider;

import imax.net.bans.database.DBIF;
import imax.net.bans.utils.ConfigYML;
import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL implements DBIF {
    private Connection connection;
    private Statement statement;

    public MySQL() {
        this.open();
    }

    public void open() {
        String host = ConfigYML.IP;
        String user = ConfigYML.USER;
        String password = ConfigYML.PASSWORD;
        String database = ConfigYML.DATABASE;
        int port = ConfigYML.PORT;

        try {
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect = true";
            if (!this.hasConnection()) {
                this.connection = DriverManager.getConnection(url, user, password);
                this.statement = connection.createStatement();
                this.createTable();
                Bukkit.getConsoleSender().sendMessage("§aMySQL conectado");
            }
        } catch (SQLException ex) {
            System.out.println("Erro na conexão com o banco de dados!");
            ex.printStackTrace();
        }

    }

    public void close() {
        try {
            if (this.hasConnection()) {
                this.connection.close();
                this.connection = null;
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void createTable() {
        this.execute("CREATE TABLE IF NOT EXISTS `punishments` (`key` INTEGER PRIMARY KEY AUTOINCREMENT, `uuid` TEXT, `nome` TEXT, `author` TEXT, `motivo` TEXT, " +
                "`prova` TEXT, `tipo` TEXT, `tempo` DOUBLE, `server` TEXT)");
    }

    public boolean hasConnection() {
        return this.connection != null;
    }

    public ResultSet pesquisa(String s) {
        try {
            return this.statement.executeQuery(s);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public boolean execute(String s) {
        try {
            return this.statement.execute(s);
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public String getName() {
        return "MySQL";
    }
}
