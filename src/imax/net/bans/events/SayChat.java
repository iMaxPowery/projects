package imax.net.bans.events;

import imax.net.bans.utils.ConvertTime;
import imax.net.bans.utils.PunishType;
import imax.net.bans.api.ActionBar;
import imax.net.bans.database.manager.DBManagement;
import imax.net.bans.database.manager.DataBase;
import imax.net.bans.manager.Time;
import imax.net.bans.utils.ConfigYML;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class SayChat implements Listener {

    @EventHandler
    public static void playerSay(AsyncPlayerChatEvent e){
        UUID uuid = e.getPlayer().getUniqueId();

        if (DBManagement.getAPI().getPunishMute(uuid.toString()) != null) {
            DataBase db = DBManagement.getAPI().getPunishMute(uuid.toString());
            Time.check(db);

            if (db.getTipo().equals(PunishType.MUTE_PERMA.toString()) || db.getTipo().equals(PunishType.MUTE.toString())) {
                String msg = e.getMessage();
                if (!msg.contains("/") || msg.startsWith("/tell") || msg.startsWith("/g")) {
                    e.setCancelled(true);
                    Player p = e.getPlayer();
                    if (db.getTipo().equals(PunishType.MUTE_PERMA.toString()))
                        new ActionBar(p, ConfigYML.MUTE_ON_SAY_PERMA
                                .replace("&", "§")
                                .replace("%author%", db.getAuthor())
                                .replace("%motivo%", db.getMotivo()));
                    else
                        new ActionBar(p, ConfigYML.MUTE_ON_SAY
                                .replace("&", "§")
                                .replace("%author%", db.getAuthor())
                                .replace("%motivo%", db.getMotivo())
                                .replace("%tempo%", ConvertTime.getTimeString((long) (db.getTempo() - System.currentTimeMillis()/1000))));
                }
            }
        }
    }
}
