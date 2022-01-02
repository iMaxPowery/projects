package imax.net.upgrade.utils;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Config {
    static List<ItemStack> blocks = new ArrayList<>();

    public Config() {
        blocks.add(new ItemStack(Material.WOOL, 1, (short) 10));
        blocks.add(new ItemStack(Material.STAINED_GLASS, 1, (short) 10));
    }

    public boolean isBlock(Block b) {
        ProtectedRegion r = null;
        for (ProtectedRegion re : WGBukkit.getRegionManager(b.getWorld()).getApplicableRegions(b.getLocation())) {
            r = re;
        }
        try {
            if (r.getId().equalsIgnoreCase("arenaelixir")) {
                ItemStack bl = new ItemStack(b.getType(), 1, b.getData());
                return (blocks.contains(bl));
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
