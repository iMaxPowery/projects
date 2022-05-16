package imax.net.bans.bungeecord;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String ch, Player p, byte[] bytes) {
        if (!ch.equalsIgnoreCase("BungeeCoord"))
            return;


    }
}
