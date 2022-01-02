package imax.net.upgrade.blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlocksManager {

    static List<Blocks> blocks = new ArrayList<>();

    public static void replaceAll() {
        if (blocks.size() >= 1)
            blocks.forEach(b -> {
                Location loc = b.getLocation();
                loc.getBlock().setType(b.getMaterial());
                loc.getBlock().setData(b.getData());
            });
        blocks.clear();
    }

    public static void addBlock(Block bl) {
        if (bl.getType() == Material.AIR)
            return;
        Blocks b = new Blocks(bl);
        blocks.add(b);
    }

    public static int getSize(){
        return blocks.size();
    }
}
