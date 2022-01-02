package imax.net.upgrade.apis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MenuBuilder {
    private final String name;
    private final MenuBuilder.MenuItem[] items;

    static {
        register(JavaPlugin.getProvidingPlugin(MenuBuilder.class));
    }

    public MenuBuilder(String name, int size) {
        this.name = name;
        this.items = new MenuBuilder.MenuItem[size];
    }

    public MenuBuilder(String name, MenuBuilder.MenuSize size) {
        this.name = name;
        this.items = new MenuBuilder.MenuItem[size.getSlots()];
    }

    public static void register(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            private void onInvClick(InventoryClickEvent e) {
                if (!e.isCancelled() && e.getInventory().getHolder() instanceof MenuBuilder.MenuHolder) {
                    ((MenuBuilder.MenuHolder) e.getInventory().getHolder()).getMenu().inventoryClick(e);
                }

            }
        }, plugin);
    }

    public MenuBuilder setItem(int slot, ItemStack itemStack) {
        return this.setItem(slot, itemStack, (MenuBuilder.MenuItemClick) null);
    }

    public MenuBuilder setItem(int slot, ItemStack itemStack, MenuBuilder.MenuItemClick itemClick) {
        return this.setItem(slot, new MenuBuilder.MenuItem(itemStack, itemClick));
    }

    public MenuBuilder setItem(int slot, MenuBuilder.MenuItem menuItem) {
        this.items[slot] = menuItem;
        return this;
    }

    public MenuBuilder open(HumanEntity humanEntity) {
        MenuBuilder.MenuHolder holder = new MenuBuilder.MenuHolder(this);
        Inventory inventory = Bukkit.createInventory(holder, this.items.length, this.name);
        holder.setInventory(inventory);

        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] != null) {
                inventory.setItem(i, this.items[i].getIcon());
            } else {
                inventory.setItem(i, new ItemStack(Material.AIR));
            }
        }

        humanEntity.openInventory(inventory);
        return this;
    }

    private void inventoryClick(InventoryClickEvent e) {
        e.setCancelled(true);
        int slot = e.getRawSlot();
        if (slot >= 0 && slot < this.items.length && this.items[slot] != null) {
            this.items[slot].onClick(e);
        }

    }

    public static class MenuHolder implements InventoryHolder {
        private final MenuBuilder menu;
        private Inventory inventory;

        public MenuHolder(MenuBuilder menu) {
            this.menu = menu;
        }

        public MenuBuilder getMenu() {
            return this.menu;
        }

        public Inventory getInventory() {
            return this.inventory;
        }

        public void setInventory(Inventory inventory) {
            this.inventory = inventory;
        }
    }

    public static class MenuItem {
        private final ItemStack icon;
        private final MenuBuilder.MenuItemClick itemClick;

        public MenuItem(ItemStack icon, MenuBuilder.MenuItemClick itemClick) {
            this.icon = icon;
            this.itemClick = itemClick;
        }

        void onClick(InventoryClickEvent e) {
            if (this.itemClick != null) {
                this.itemClick.onClick(e);
            }

        }

        public ItemStack getIcon() {
            return this.icon;
        }
    }

    public interface MenuItemClick {
        void onClick(InventoryClickEvent var1);
    }

    public static enum MenuSize {
        ONE_LINE(9),
        TWO_LINES(18),
        THREE_LINES(27),
        FOUR_LINES(36),
        FIVE_LINES(45),
        SIX_LINES(54);

        private final int slots;

        private MenuSize(int slots) {
            this.slots = slots;
        }

        public int getSlots() {
            return this.slots;
        }
    }
}
