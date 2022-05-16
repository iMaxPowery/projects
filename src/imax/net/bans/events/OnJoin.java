package imax.net.bans.events;

import imax.net.bans.utils.PunishType;
import imax.net.bans.database.manager.DBManagement;
import imax.net.bans.database.manager.DataBase;
import imax.net.bans.manager.Time;
import imax.net.bans.utils.ConfigYML;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

public class OnJoin implements Listener {

    @EventHandler(priority= EventPriority.HIGHEST)
    public static void joinEvent(AsyncPlayerPreLoginEvent  e){
        UUID uuid = e.getUniqueId();

        if (DBManagement.getAPI().getPunishBans(uuid.toString()) != null ||
                DBManagement.getAPI().getPunishBans(e.getAddress().getHostAddress()) != null){
            DataBase db = DBManagement.getAPI().getPunishBans(uuid.toString());

            Time.check(db);

            if (db.getTipo().equals(PunishType.BAN_PERMA.toString()) || db.getTipo().equals(PunishType.BAN.toString())) {
                String msg = (!db.getTipo().equals(PunishType.BAN_PERMA.toString()) ? ConfigYML.getScreenBan(db.getNome(),
                        db.getMotivo(), db.getAuthor(), db.getProva(), db.getTempo() - System.currentTimeMillis() / 1000) :
                        ConfigYML.getScreenBanPerma(db.getNome(), db.getMotivo(), db.getAuthor(), db.getMotivo()));
                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, msg);
            }
        }
    }
}
