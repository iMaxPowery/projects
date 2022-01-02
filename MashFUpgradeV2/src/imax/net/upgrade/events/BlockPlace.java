package imax.net.upgrade.events;

import com.massivecraft.factions.entity.MPlayer;
import com.sk89q.worldedit.blocks.MobSpawnerBlock;
import imax.net.upgrade.apis.ActionBar;
import imax.net.upgrade.database.manager.DataBase;
import imax.net.upgrade.database.manager.DataBaseManager;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BlockPlace implements Listener {

    @EventHandler
    public static void blockPlace(BlockPlaceEvent e){
        Block b = e.getBlock();

        if (b.getType() == Material.MOB_SPAWNER && MPlayer.get(e.getPlayer()).hasFaction()) {
            try {
                Player p = e.getPlayer();

                DataBase facdb = DataBaseManager.getAPI().getFac(MPlayer.get(p).getFactionTag());

                ItemStack i = e.getItemInHand();
                List<String> lore = i.getItemMeta().getLore();
                String type = lore.get(1).replace(" §fEsse spawner é do tipo §e", "").replace("§f.", "");
                int v = getSpLevel(type);
                if (v > facdb.getdSpawner()){
                    e.setCancelled(true);
                    new ActionBar(p, "§cSua facção não possui permissão de colocar esse spawner.");
                    return;
                }
            } catch (Throwable ex) {
            }
        }
    }

    private static Integer getSpLevel(String sp){
        switch (sp.toUpperCase()){
            case "OVELHA":
                return 0;
            case "VACA":
                return 1;
            case "VACA DE COGUMELO":
                return 2;
            case "PORCO ZUMBI":
                return 3;
            case "GOLEM DE FERRO":
                return 4;
            case "ALDEÃO":
                return 5;
            case "CUBO DE MAGMA":
                return 6;
            case "ENDERMAN":
                return 7;
            case "WITHER":
                return 8;
        }
        return 99;
    }
}
