package imax.net.upgrade.apis;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder extends ItemStack {

    public ItemBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public ItemBuilder(Material type, String display) {
        super(type);
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(display);
        setItemMeta(meta);
    }

    public ItemBuilder(Material type, String display, String... lore) {
        super(type);
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(display);
        meta.setLore(Arrays.asList(lore));
        setItemMeta(meta);
    }

    public ItemBuilder(Material type, short data, String display) {
        super(type, 1, data);
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(display);
        setItemMeta(meta);
    }

    public ItemBuilder(Material type, short data, String display, String... lore) {
        super(type, 1, data);
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(display);
        meta.setLore(Arrays.asList(lore));
        setItemMeta(meta);
    }

    public ItemBuilder(Material type, int quantia, short data, String display, String... lore) {
        super(type, quantia, data);
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(display);
        meta.setLore(Arrays.asList(lore));
        setItemMeta(meta);
    }
}

