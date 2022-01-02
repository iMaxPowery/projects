package imax.net.upgrade.commands;

import com.massivecraft.factions.entity.MPlayer;
import imax.net.upgrade.apis.ActionBar;
import imax.net.upgrade.utils.GetCrystal;
import imax.net.upgrade.menus.MenuPrincipal;
import imax.net.upgrade.menus.MinaInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class FUpgradeCommands implements Listener {

    @EventHandler
    public static void processCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (e.getMessage().equalsIgnoreCase("/f upgrade")) {
            e.setCancelled(true);
            if (!MPlayer.get(p).hasFaction()){
                new ActionBar(p, "§cVocê precisa de uma facção para usar esse comando.");
                return;
            }
            new MenuPrincipal(e.getPlayer()).open(e.getPlayer());
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/f minagive")) {
            e.setCancelled(true);
            if (p.hasPermission("FUpgrade.Admin"))
                p.getInventory().addItem(GetCrystal.get());
            else
                new ActionBar(p, "§cVocê não tem permissão para isso.");
            return;
        }
        if (e.getMessage().equalsIgnoreCase("/minaelixir")) {
            e.setCancelled(true);
            new MinaInfo().open(p);
        }
    }
}
