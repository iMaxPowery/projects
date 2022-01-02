package imax.net.upgrade.menus.submenus;

import imax.net.upgrade.apis.ItemBuilder;
import imax.net.upgrade.apis.MenuBuilder;
import imax.net.upgrade.menus.MenuPrincipal;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CristaisMenu extends MenuBuilder {
    public CristaisMenu(Player p) {
        super("§8Upgrade - Cristais", MenuSize.FIVE_LINES);

        ItemBuilder info = new ItemBuilder(Material.NETHER_STAR, "      §6§lINFO", "", " §fLogo abaixo vocÃª vai encontrar", " §fas formas de adquirir cristais.", "");
        ItemBuilder minerando = new ItemBuilder(Material.DIAMOND_PICKAXE, "      §6§lMINERANDO", "", " §fMundo§7: §eMina", " §fCristais§7: §b1 a 3", " §fChance§7: §e15%", "");
        ItemBuilder plantancao = new ItemBuilder(Material.GOLD_HOE, "      §6§lPLANTAÇÃO", "", " §fMundo§7: §eFactions", " §fCristais§7: §b2 e 3", " §fChance§7: §e13%", "");
        ItemBuilder pesca = new ItemBuilder(Material.FISHING_ROD, "      §6§lPESCA", "", " §fMundo§7: §eFactions", " §fCristais§7: §b5 a 7", " §fChance§7: §e4%", "", "§7A pesca só irá funcionar em", "§7um lugar com 15 por 15 de Ã¡gua.");
        ItemBuilder spawner = new ItemBuilder(Material.MONSTER_EGG, "      §6§lDROP BÃ”NUS", "", " §fMundo§7: §eFactions", " §fCristais§7: §b1 e 2", " §fChance§7: §e2%", "");
        ItemBuilder pvp = new ItemBuilder(Material.DIAMOND_SWORD, "      §6§lPvP", "", " §fMundo§7: §eFactions", " §fCristais§7: §b5", " §fChance§7: §e45%", "", "§7SÃ³ irÃ¡ funcionar se vocÃª matar", "§7um inimigo em seu claim.");
        ItemBuilder voltar = new ItemBuilder(Material.ARROW, "      §a§lVOLTAR");

        setItem(4, info);
        setItem(19, minerando);
        setItem(20, plantancao);
        setItem(22, pesca);
        setItem(24, spawner);
        setItem(25, pvp);
        setItem(40, voltar, e -> {
            new MenuPrincipal(p).open(p);
        });
    }
}