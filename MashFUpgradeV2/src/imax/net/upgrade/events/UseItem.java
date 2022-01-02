package imax.net.upgrade.events;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import imax.net.upgrade.apis.ActionBar;
import imax.net.upgrade.utils.GetCrystal;
import imax.net.upgrade.faction.Manager;
import imax.net.upgrade.faction.Methods;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class UseItem implements Listener {

    @EventHandler
    public static void useItem(PlayerInteractEvent e) {
        if (!e.getAction().equals(Action.RIGHT_CLICK_AIR) && !e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if (e.getPlayer().getItemInHand() == null || e.getPlayer().getItemInHand().getType() == Material.AIR) return;
        Player p = e.getPlayer();

        ItemStack inHand, Crystal;
        inHand = p.getItemInHand();
        Crystal = GetCrystal.get();

        MPlayer mp = MPlayer.get(p);
        Faction fac = mp.getFaction();

        if (Crystal.isSimilar(inHand)) {
            Methods m = new Methods();
            if (!mp.hasFaction()) {
                new ActionBar(p, "§fVocê precisa de uma facção para dominar a §d§nMina de Elixir§f.");
                return;
            }
            if (m.hasFac()) {
                new ActionBar(p, "§fA §d§nMina de Elixir§f já está sendo dominada pela facção " + m.getFacTag());
                return;
            } else {
                if (e.getItem().getAmount() == 1)
                    e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
                else
                    e.getItem().setAmount(e.getItem().getAmount() - 1);
                new Manager(fac);
            }
        }
    }
}
