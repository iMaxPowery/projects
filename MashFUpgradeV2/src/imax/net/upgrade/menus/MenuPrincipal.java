package imax.net.upgrade.menus;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import imax.net.upgrade.apis.ItemBuilder;
import imax.net.upgrade.apis.MenuBuilder;
import imax.net.upgrade.database.manager.DataBase;
import imax.net.upgrade.database.manager.DataBaseManager;
import imax.net.upgrade.menus.submenus.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MenuPrincipal extends MenuBuilder {

    public MenuPrincipal(Player p) {
        super("§8Upgrades", MenuSize.THREE_LINES);

        MPlayer mp = MPlayer.get(p);
        Faction fac = mp.getFaction();
        DataBase facdb = DataBaseManager.getAPI().getFac(fac.getTag());

        ItemBuilder voltar = new ItemBuilder(Material.ARROW, "      §c§lVOLTAR");
        ItemBuilder evoluir = new ItemBuilder(Material.INK_SACK, (short) 10, "     §a§lEVOLUIR");
        ItemBuilder claim = new ItemBuilder(Material.GRASS, "      §6§lCLAIM", "", " §fUpgrades relacionado ao claim.", "", "§7Clique para acessar.");
        ItemBuilder protection = new ItemBuilder(Material.BEDROCK, "      §6§lPROTEÇÃO", "", " §fUpgrades relacionado a blocos de proteção.", "", "§7Clique para acessar.");
        ItemBuilder banco = new ItemBuilder(Material.GOLD_INGOT, "      §6§lBANCO", "", " §fUpgrades relacionado ao banco.", "", "§7Clique para acessar.");
        ItemBuilder membros = new ItemBuilder(Material.SKULL_ITEM, (short) 3, "      §6§lMEMBROS", "", " §fUpgrades relacionado aos membros.", "", "§7Clique para acessar.");
        ItemBuilder spawners = new ItemBuilder(Material.MONSTER_EGG, "      §6§lSPAWNERS", "", " §fUpgrades relacionado aos spawners.", "", "§7Clique para acessar.");
        ItemBuilder cristais = new ItemBuilder(Material.PRISMARINE_CRYSTALS, "      §6§lCRISTAIS",
                "",
                " §fQuantidade§7: §b " + facdb.getCoins(),
                "",
                "§7Botão Esquerdo para informações.", "§7Botão Direito para coletar.");

        setItem(10, claim, e -> {
            if (e.isLeftClick()){
                new ClaimMenu(p, voltar, evoluir).open(p);
            }
        });
        setItem(11, protection, e -> {
            if (e.isLeftClick()){
                new ProtecaoMenu(p, voltar, evoluir).open(p);
            }
        });
        setItem(13, banco, e -> {
            if (e.isLeftClick()) {
                new BancoMenu(p, voltar, evoluir).open(p);
            }
        });
        setItem(15, membros, e -> {
            if (e.isLeftClick()) {
                new MembrosMenu(p, voltar, evoluir).open(p);
            }
        });
        setItem(16, spawners, e -> {
            if (e.isLeftClick()) {
                new SpawnersMenu(p, voltar, evoluir).open(p);
            }
        });
        setItem(26, cristais, e -> {
            if (e.isLeftClick()) {
                new CristaisMenu(p).open(p);
            }
        });
    }
}
