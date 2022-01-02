package imax.net.upgrade.menus.submenus;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import imax.net.upgrade.apis.ItemBuilder;
import imax.net.upgrade.apis.MenuBuilder;
import imax.net.upgrade.database.manager.DataBaseManager;
import imax.net.upgrade.economy.EconomyManager;
import imax.net.upgrade.menus.MenuPrincipal;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BancoMenu extends MenuBuilder {

    public BancoMenu(Player p, ItemBuilder voltar, ItemBuilder evoluir) {
        super("§8Upgrade - Banco", MenuSize.FIVE_LINES);

        MPlayer mp = MPlayer.get(p);
        Faction fac = mp.getFaction();
        int durabilidadeV, capacidadeV, rendaV, contraataqueV, salvagranaV, roubagranaV;

        durabilidadeV = 3;
        capacidadeV = 2;
        rendaV = 5;
        contraataqueV = 2;
        salvagranaV = 4;
        roubagranaV = 4;

        ItemBuilder durabilidade = new ItemBuilder(Material.BEDROCK, "      §6§lDURABILIDADE",
                "",
                " §aNível atual: /2", " §aValor: " + durabilidadeV, "", " §7Clique no item abaixo para evoluir.");
        ItemBuilder capacidade = new ItemBuilder(Material.CHEST, "      §6§lCAPACIDADE",
                "",
                " §aNível atual: /5", " §aValor: " + capacidadeV, "", " §7Clique no item abaixo para evoluir.");
        ItemBuilder renda = new ItemBuilder(Material.GOLD_INGOT, "      §6§lRENDA",
                "",
                " §aNível atual: /3", " §aValor: " + rendaV, "", " §7Clique no item abaixo para evoluir.");
        ItemBuilder cta = new ItemBuilder(Material.GOLD_SWORD, "      §6§lCONTRA ATAQUE",
                "",
                " §aNível atual: /3", " §aValor: " + contraataqueV, "", " §7Clique no item abaixo para evoluir.");
        ItemBuilder sg = new ItemBuilder(Material.GOLDEN_APPLE, "      §6§lSALVA GRANA",
                "",
                " §aNível atual: /1", " §aValor: " + salvagranaV, "", " §7Clique no item abaixo para evoluir.");
        ItemBuilder rg = new ItemBuilder(Material.SPIDER_EYE, "      §6§lROUBA GRANA",
                "",
                " §aNível atual: /1", " §aValor: " + roubagranaV, "", " §7Clique no item abaixo para evoluir.");

        // Durabilidade
        setItem(10, durabilidade);
        setItem(19, evoluir, e -> {
            if (EconomyManager.getAPI().debilitValue(p, fac.getTag(), 3)){
            }
        });

        // Capacidade
        setItem(11, capacidade);
        setItem(20, evoluir, e -> {
        });

        // Renda
        setItem(12, renda);
        setItem(21, evoluir, e -> {
        });

        // Contra Ataque
        setItem(13, cta);
        setItem(22, evoluir, e -> {
        });

        // Salva Grana
        setItem(14, sg);
        setItem(23, evoluir, e -> {
        });

        setItem(15, rg);
        setItem(24, evoluir, e -> {
        });

        setItem(40, voltar, e -> {
            new MenuPrincipal(p).open(p);
        });
    }
}