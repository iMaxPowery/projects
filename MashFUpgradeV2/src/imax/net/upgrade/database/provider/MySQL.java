package imax.net.upgrade.database.provider;

import imax.net.upgrade.FUpgrade;
import imax.net.upgrade.database.DataBaseIF;
import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL implements DataBaseIF {
    private Connection connection;
    private Statement statement;

    public MySQL() {
        this.open();
    }

    public void open() {
        String host = FUpgrade.getInstance().getConfig().getString("MySQL.IP");
        String user = FUpgrade.getInstance().getConfig().getString("MySQL.Usuario");
        String password = FUpgrade.getInstance().getConfig().getString("MySQL.Senha");
        String database2 = FUpgrade.getInstance().getConfig().getString("MySQL.DataBase");
        int port = FUpgrade.getInstance().getConfig().getInt("MySQL.Porta");

        try {
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database2 + "?autoReconnect = true";
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
        this.execute("CREATE TABLE IF NOT EXISTS `fupgrades` (`fac` TEXT, `coins` INT, `claimL` INT, `dclaimT` INT, "
                + "`dAtaque` INT, `bProtecao` INT, `tRegn` INT, `lMembros` INT, `pPoder` INT, `tRegen` INT, `dSpawner` INT, `lValor` INT, `aLucro` INT)");
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
