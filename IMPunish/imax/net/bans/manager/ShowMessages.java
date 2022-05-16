package imax.net.bans.manager;

import imax.net.bans.api.BotJDA;
import imax.net.bans.utils.ConfigYML;
import imax.net.bans.utils.ConvertTime;
import org.bukkit.Bukkit;

public class ShowMessages {

    public static void showBanPerm(String name, String motivo, String author, String prova){
        if (ConfigYML.ANUNCIAR_BAN)
            for (String msg : ConfigYML.MSG_BAN_PERMA)
                send(replace(msg, name, motivo, author, prova));

        BotJDA.sendMessage(name, motivo, prova, "Permanente", author);
    }

    public static void showBan(String name, String motivo, String author, double tempo, String prova){
        if (ConfigYML.ANUNCIAR_BAN)
            for (String msg : ConfigYML.MSG_BAN)
                send(replace(msg, name, motivo, author, tempo, prova));

        BotJDA.sendMessage(name, motivo, prova, ConvertTime.getTimeString((long) tempo), author);
    }

    public static void showMutePerm(String name, String motivo, String author, String prova){
        if (ConfigYML.ANUNCIAR_MUTE)
            for (String msg : ConfigYML.MSG_MUTE_PERMA)
                send(replace(msg, name, motivo, author, prova));
    }

    public static void showMute(String name, String motivo, String author, double tempo, String prova){
        if (ConfigYML.ANUNCIAR_MUTE)
            for (String msg : ConfigYML.MSG_MUTE)
                send(replace(msg, name, motivo, author, tempo, prova));
    }

    public static void showKick(String name, String motivo, String author){
        if (ConfigYML.ANUNCIAR_KICK)
            for (String msg : ConfigYML.MSG_KICK)
                send(msg
                        .replace("&", "§")
                        .replace("%user%", name)
                        .replace("%motivo%", motivo)
                        .replace("%author%", author));
    }

    public static void unMute(String name){
        if (ConfigYML.ANUNCIAR_UNMUTE)
            for (String msg : ConfigYML.UNMUTE)
                send(msg
                        .replace("&", "§")
                        .replace("%user%", name));
    }

    public static void unBan(String name){
        if (ConfigYML.ANUNCIAR_UNBAN)
            for (String msg : ConfigYML.UNBAN)
                send(msg
                        .replace("&", "§")
                        .replace("%user%", name));
    }

    private static String replace(String msg, String name, String motivo, String author, double tempo, String prova){
        return msg.replace("&", "§")
                .replace("%user%", name)
                .replace("%motivo%", motivo)
                .replace("%author%", author)
                .replace("%prova%", prova)
                .replace("%tempo%", ConvertTime.getTimeString((long) tempo));
    }

    private static String replace(String msg, String name, String motivo, String author, String prova){
        return msg.replace("&", "§")
                .replace("%user%", name)
                .replace("%motivo%", motivo)
                .replace("%prova%", prova)
                .replace("%author%", author);
    }
    private static void send(String msg){
        Bukkit.broadcastMessage(msg);
    }
}
