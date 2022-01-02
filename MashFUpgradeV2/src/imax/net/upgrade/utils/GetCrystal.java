package imax.net.upgrade.utils;

import imax.net.upgrade.apis.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GetCrystal {

    public static ItemStack get() {
        ItemBuilder crystal = new ItemBuilder(Material.PAPER, "      §6Mina Ativador",
                "",
                " §fClique com o botão direito para ativar.",
                " §fItem usado para liberar a mina para sua facção.",
                "");
        return crystal;
    }
}
