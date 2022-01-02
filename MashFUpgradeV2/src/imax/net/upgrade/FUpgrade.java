package imax.net.upgrade;

import imax.net.upgrade.apis.FUpgradeAPI;
import imax.net.upgrade.commands.FUpgradeCommands;
import imax.net.upgrade.database.DataBaseIF;
import imax.net.upgrade.database.manager.DataBaseManager;
import imax.net.upgrade.database.provider.MySQL;
import imax.net.upgrade.events.*;
import imax.net.upgrade.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FUpgrade extends JavaPlugin {

    static FUpgrade instance;
    public DataBaseIF database;

    public static FUpgrade getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        database = new MySQL();

        DataBaseManager.getAPI().carregar();
        FUpgradeAPI.fUpgradeAPI = new FUpgradeAPI();

        registerEvents();
        new Config();
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new FUpgradeCommands(), this);
        pm.registerEvents(new UseItem(), this);
        pm.registerEvents(new BlockBreak(), this);
        pm.registerEvents(new FacCreate(), this);
        pm.registerEvents(new FacDelete(), this);
        pm.registerEvents(new BlockPlace(), this);
    }

    public void onDisable() {
        DataBaseManager.getAPI().saveAll();
    }
}
