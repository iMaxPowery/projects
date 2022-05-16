package imax.net.bans.manager;

import imax.net.bans.utils.PunishType;
import imax.net.bans.database.manager.DBManagement;
import imax.net.bans.database.manager.DataBase;
import org.bukkit.command.CommandSender;

public class Time {

    double seconds;

    public static long tickToSeconds(long tick){
        return tick/1000;
    }

    public static void check(DataBase db){
        long t = tickToSeconds(System.currentTimeMillis());
        if (db.getTempo() <= t && !db.getTipo().equals(PunishType.BAN_PERMA.toString()) && !db.getTipo().equals(PunishType.MUTE_PERMA.toString())){
            DBManagement.getAPI().delete(db.getUuid(), db.getTipo());
        }
    }

    public static long convertToTime(String msg, CommandSender s){
        try {
            if (msg.contains("m")){
                return Integer.valueOf(msg.replace("m", "")) * 60;
            }
            if (msg.contains("h")){
                return Integer.valueOf(msg.replace("h", "")) * 60 * 60;
            }
            if (msg.contains("d")){
                return Integer.valueOf(msg.replace("d", "")) * 60 * 60 * 24;
            }
            if (msg.contains("s")){
                return Integer.valueOf(msg.replace("s", "")) * 60 * 60 * 24 * 7;
            }
            return Integer.valueOf(msg);
        }catch (Exception e){
            s.sendMessage("§cOcorreu um erro ao definir o tempo do jogador.");
            return -1;
        }
    }
}
