package imax.net.bans.database.provider;

import imax.net.bans.Bans;
import imax.net.bans.database.DBIF;
import org.bukkit.Bukkit;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLite implements DBIF {
    private Connection connection;

    private Statement statement;

    public SQLite() {
        open();
    }

    public void open() {
        File file = new File(Bans.getInstance().getDataFolder(), "save.db");
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + file;
            if (!hasConnection()) {
                this.connection = DriverManager.getConnection(url);
                this.statement = this.connection.createStatement();
                createTable();
                Bukkit.getConsoleSender().sendMessage("§aSQLite conectado");
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    public void close() {
        try {
            if (hasConnection()) {
                this.connection.close();
                this.connection = null;
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }
    }

    public void createTable() {
        execute("CREATE TABLE IF NOT EXISTS `punishments` (`key` INTEGER PRIMARY KEY AUTOINCREMENT, `uuid` TEXT, `nome` TEXT, `author` TEXT, `motivo` TEXT, " +
                "`prova` TEXT, `tipo` TEXT, `tempo` DOUBLE, `server` TEXT)");
    }

    public boolean hasConnection() {
        return (this.connection != null);
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
        return "SQLite";
    }
}