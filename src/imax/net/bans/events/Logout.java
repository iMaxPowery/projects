package imax.net.bans.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Logout implements Listener {

    @EventHandler
    public static void onLeft(PlayerQuitEvent e){
        if (SayPunish.list.containsKey(e.getPlayer().getUniqueId()))
            SayPunish.list.remove(e.getPlayer().getUniqueId());
    }
}
