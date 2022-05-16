package imax.net.bans.utils;

import imax.net.bans.Bans;
import imax.net.bans.bungeecord.ServerList;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ConfigYML {

    public static List<String> MSG_BAN, MSG_BAN_PERMA, MSG_MUTE, MSG_MUTE_PERMA, MSG_KICK,
            SCREEN_BAN, SCREEN_BAN_PERMA, SCREEN_KICK, UNBAN, UNMUTE = new ArrayList<>();
    public static String MUTE_ON_SAY, MUTE_ON_SAY_PERMA;
    public static String CONFIRMAR_ICON, CANCELAR_ICON, CONFIRMSILLENCE_ICON, IPBAN_ICON,
    CONFIRMAR, CANCELAR, CONFIRMSILLENCE, IPBAN;

    public static boolean ANUNCIAR_ALL, ANUNCIAR_BAN, ANUNCIAR_UNBAN, ANUNCIAR_MUTE, ANUNCIAR_UNMUTE, ANUNCIAR_KICK;

    public static boolean BungeeCord;

    public static String DATABASE;
    public static String USER;
    public static String PASSWORD;
    public static String IP;
    public static int PORT;
    public static boolean DB;

    public static boolean BOT;
    public static String TOKEN, GUILD, CHANNELBAN, CHANNELSOLICT;
    public static String BOT_TITLE, BOT_TITLE_SOLICT;

    public ConfigYML(){
        FileConfiguration config = Bans.getInstance().getConfig();

        //Mensagens
        MSG_BAN = config.getStringList("Server-Mensagens.Ban");
        MSG_BAN_PERMA = config.getStringList("Server-Mensagens.Ban-Perma");
        MSG_MUTE = config.getStringList("Server-Mensagens.Mute");
        MSG_MUTE_PERMA = config.getStringList("Server-Mensagens.Mute-Perma");
        MSG_KICK = config.getStringList("Server-Mensagens.Kick");
        SCREEN_BAN = config.getStringList("Server-Mensagens.Tela-Ban");
        SCREEN_BAN_PERMA = config.getStringList("Server-Mensagens.Tela-Ban-Perma");
        SCREEN_KICK = config.getStringList("Server-Mensagens.Tela-Kick");
        MUTE_ON_SAY = config.getString("Server-Mensagens.MutadoMSG");
        MUTE_ON_SAY_PERMA = config.getString("Server-Mensagens.MutadoPermaMSG");
        UNBAN = config.getStringList("Server-Mensagens.unBan");
        UNMUTE = config.getStringList("Server-Mensagens.unMute");

        //Click
        CONFIRMAR = config.getString("Icons.Confirmar.TEXT").replace("&", "§");
        CANCELAR = config.getString("Icons.Cancelar.TEXT").replace("&", "§");
        CONFIRMSILLENCE = config.getString("Icons.SilenceBan.TEXT").replace("&", "§");
        IPBAN = config.getString("Icons.IPBan.TEXT").replace("&", "§");
        CONFIRMAR_ICON = config.getString("Icons.Confirmar.ICON").replace("&", "§");
        CANCELAR_ICON = config.getString("Icons.Cancelar.ICON").replace("&", "§");
        CONFIRMSILLENCE_ICON = config.getString("Icons.SilenceBan.ICON").replace("&", "§");
        IPBAN_ICON = config.getString("Icons.IPBan.ICON").replace("&", "§");

        //Anuncio
        ANUNCIAR_ALL = config.getBoolean("Mensagens.Anunciar-All");
        ANUNCIAR_BAN = config.getBoolean("Mensagens.Ban");
        ANUNCIAR_UNBAN = config.getBoolean("Mensagens.unBan");
        ANUNCIAR_MUTE = config.getBoolean("Mensagens.Mute");
        ANUNCIAR_UNMUTE = config.getBoolean("Mensagens.unMute");
        ANUNCIAR_KICK = config.getBoolean("Mensagens.Kick");

        //BungeeCord
        BungeeCord = config.getBoolean("BungeeCord.Ativo");

        //DATABASE
        DATABASE = config.getString("MySQL.DataBase");
        USER = config.getString("MySQL.User");
        PASSWORD = config.getString("MySQL.Pass");
        IP = config.getString("MySQL.IP");
        PORT = config.getInt("MySQL.Port");
        DB = config.getBoolean("MySQL.DataBase");

        //BOT CONNECTION
        TOKEN = config.getString("Bot.Token");
        GUILD = config.getString("Bot.Guild");
        CHANNELBAN = config.getString("Bot.BanChannel");
        CHANNELSOLICT = config.getString("Bot.SolicitacaoChannel");
        BOT = config.getBoolean("Bot.Ativo");

        //BOT MENSAGES
        BOT_TITLE = config.getString("Bot-Mensagens.Ban.Titulo");
        BOT_TITLE_SOLICT = config.getString("Bot-Mensagens.Solicitacao.Titulo");

        new ServerList();
    }

    public static String getScreenBan(String name, String motivo, String author, String prova, double tempo){
        String msg = "";
        System.out.println(tempo);
        for (String b : SCREEN_BAN)
            msg += b.replace("&", "§")
                    .replace("%user%", name)
                    .replace("%motivo%", motivo)
                    .replace("%author%", author)
                    .replace("%prova%", prova)
                    .replace("%tempo%", ConvertTime.getTimeString((long) tempo)
                    .replace("", "")) + "\n";
        return msg;
    }

    public static String getScreenBanPerma(String name, String motivo, String author, String prova){
        String msg = "";
        for (String b : SCREEN_BAN_PERMA)
            msg += b.replace("&", "§")
                    .replace("%user%", name)
                    .replace("%motivo%", motivo)
                    .replace("%author%", author)
                    .replace("%prova%", prova) + "\n";
        return msg;
    }

    public static String getScreenKick(String name, String motivo, String author){
        String msg = "";
        for (String b : SCREEN_BAN)
            msg += b.replace("&", "§")
                    .replace("%user%", name)
                    .replace("%motivo%", motivo)
                    .replace("%author%", author) + "\n";
        return msg;
    }
}
