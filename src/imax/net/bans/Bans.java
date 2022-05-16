package imax.net.bans;

import imax.net.bans.api.BotJDA;
import imax.net.bans.bungeecord.PluginListener;
import imax.net.bans.commands.*;
import imax.net.bans.database.provider.MySQL;
import imax.net.bans.database.provider.SQLite;
import imax.net.bans.events.*;
import imax.net.bans.utils.Dependency;
import imax.net.bans.database.DBIF;
import imax.net.bans.database.manager.DBManagement;
import imax.net.bans.utils.ConfigYML;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Bans extends JavaPlugin {

    static Bans instance;
    public DBIF database;

    public static Bans getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        checkDependency();

        new ConfigYML();
        if (ConfigYML.BOT)
            new BotJDA(this);

        database = (ConfigYML.DB ? new MySQL() : new SQLite());

        DBManagement.getAPI().loadPunishments();

        registryCommands();
        registryEvents();

        if (ConfigYML.BungeeCord)
            bungeeCoord();
    }

    private void registryCommands(){
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("mute").setExecutor(new MuteCommand());
        getCommand("unban").setExecutor(new Unban());
        getCommand("unmute").setExecutor(new Unmute());
        getCommand("kick").setExecutor(new KickCommand());
        getCommand("pver").setExecutor(new VerCommand());
    }

    private void registryEvents(){
        Bukkit.getPluginManager().registerEvents(new OnJoin(), this);
        Bukkit.getPluginManager().registerEvents(new SayChat(), this);
        Bukkit.getPluginManager().registerEvents(new SayPunish(), this);
        Bukkit.getPluginManager().registerEvents(new Ban(), this);
        Bukkit.getPluginManager().registerEvents(new Logout(), this);
    }

    private void checkDependency(){
        try {
            Class.forName("net.dv8tion.jda.core.JDABuilder");
        } catch (Exception var7) {
            Bukkit.getConsoleSender().sendMessage("§9[IMPunish] §aBaixando dependencias...");
            try {
                new Dependency("https://cdn.discordapp.com/attachments/975572664234151977/975579972519485440/Discord_Bot_API_V_4.0.jar", new File("./plugins"));
                Bukkit.getConsoleSender().sendMessage("§9[IMPunish] §aDependencias baixadas com sucesso!");
                Bukkit.getConsoleSender().sendMessage("");
                Bukkit.getConsoleSender().sendMessage("");
                Bukkit.getConsoleSender().sendMessage("");
                Bukkit.getConsoleSender().sendMessage("§9[IMPunish] §aReinicie o servidor para que tudo funcione normalmente!");
                Bukkit.getConsoleSender().sendMessage("");
                Bukkit.getConsoleSender().sendMessage("");
                Bukkit.getConsoleSender().sendMessage("");
            } catch (Exception var5) {
                Bukkit.getConsoleSender().sendMessage("§9[IMPunish] §4[⚠]  Não foi possivel de baixar as dependencias!");
                return;
            }
        }
    }
    private void bungeeCoord(){
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCoord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCoord", new PluginListener());
    }

    public void onDisable() {
        DBManagement.getAPI().saveAll();

        if (database.hasConnection())
            database.close();
        if (ConfigYML.BOT)
            if (BotJDA.jda != null)
                BotJDA.jda.shutdown();
    }
}
