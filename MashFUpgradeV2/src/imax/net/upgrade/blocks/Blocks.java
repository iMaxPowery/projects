package imax.net.upgrade.blocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Blocks {

    int x, y, z;
    Material material;
    String worldname;
    byte data;

    public Blocks(Block b) {
        x = b.getX();
        y = b.getY();
        z = b.getZ();
        material = b.getType();
        worldname = b.getWorld().getName();
        data = b.getData();
    }

    public Material getMaterial() {
        return material;
    }

    public byte getData() {
        return data;
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(worldname), x, y, z);
    }
}
