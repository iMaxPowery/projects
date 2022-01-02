package imax.net.upgrade.events;

import com.massivecraft.factions.entity.MPlayer;
import imax.net.upgrade.apis.ActionBar;
import imax.net.upgrade.blocks.BlocksManager;
import imax.net.upgrade.economy.EconomyManager;
import imax.net.upgrade.faction.Methods;
import imax.net.upgrade.utils.Config;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class BlockBreak implements Listener {

    @EventHandler
    public static void onBreak(BlockBreakEvent e) {
        if (e.getBlock().getLocation().getWorld().getName().equalsIgnoreCase("Spawn")) {
            boolean b = new Config().isBlock(e.getBlock());
            if (b) {
                Methods m = new Methods();
                Player p = e.getPlayer();
                String tag = MPlayer.get(p).getFactionTag();
                e.setCancelled(true);
                if (m.hasFac() && m.getFacTag().equalsIgnoreCase(tag)) {
                    BlocksManager.addBlock(e.getBlock().getLocation().getBlock());
                    e.getBlock().setType(Material.AIR);
                    luckForBlocks(tag, p, m);
                } else {
                    new ActionBar(p, "§cSua facção não está dominando essa área.");
                }
            }
        }
    }

    private static void luckForBlocks(String factag, Player p, Methods m){
        Random r = new Random();
        int v = r.nextInt(100);
        if (v <= 30){
            new ActionBar(p, "§fVocê achou um bloco de §dElixir §fe ganhou §d+1 §fde §dElixir§f.");
            EconomyManager.getAPI().depositValue(factag, 20);
            m.getFacManager().addBreakedBlocks();
        }
    }
}
