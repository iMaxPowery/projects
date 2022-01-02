package imax.net.upgrade.menus.submenus;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import imax.net.upgrade.apis.ActionBar;
import imax.net.upgrade.apis.ItemBuilder;
import imax.net.upgrade.apis.MenuBuilder;
import imax.net.upgrade.database.manager.DataBase;
import imax.net.upgrade.database.manager.DataBaseManager;
import imax.net.upgrade.economy.EconomyManager;
import imax.net.upgrade.menus.MenuPrincipal;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ProtecaoMenu extends MenuBuilder {

    public ProtecaoMenu(Player p, ItemBuilder voltar, ItemBuilder evoluir) {
        super("§8Upgrade - ProteÃ§Ã£o", MenuSize.FIVE_LINES);

        MPlayer mp = MPlayer.get(p);
        Faction fac = mp.getFaction();

        DataBase facdb = DataBaseManager.getAPI().getFac(fac.getTag());

        int blocoprotecao, temporegen;
        final int[] blocoAtual = new int[1];
        final int[] tempoAtual = new int[1];

        blocoAtual[0] = facdb.getbProtecao();
        tempoAtual[0] = facdb.gettRegn();

        blocoprotecao = 1;
        temporegen = 20;

        ItemBuilder blocktyps = new ItemBuilder(Material.ENDER_STONE, "      §6§lBLOCOS DE PROTEÇÃO" , "",
                " §fBlocos de proteção próximos ao spawners",
                " §fficarão com sua proteção total.",
                "",
                " §6➥ " + getColor("Nível 0", blocoAtual[0]) + " §8➝ §70/10",
                " §6➥ " + getColor("Nível 1", blocoAtual[0]) + " §8➝ §75/10",
                " §6➥ " + getColor("Nível 2", blocoAtual[0]) + " §8➝ §710/10",
                "",
                " §aNível atual: " + blocoAtual[0] + "/2", " §aValor: " + blocoprotecao, "", " §7Clique no item abaixo para evoluir.");
        ItemBuilder regentemp = new ItemBuilder(Material.APPLE, "      §6§lTEMPO DE REGENERAÇÃO" , "",
                " §fDOBRE o tempo de regeneração de suas proteções.",
                "",
                " §aNível atual: " + tempoAtual[0] + "/1", " §aValor: " + temporegen, "", " §7Clique no item abaixo para evoluir.");

        // Tipos de Bloco
        setItem(10, blocktyps);
        setItem(19, evoluir, e -> {
            if (blocoAtual[0] >= 2) {
                new ActionBar(p, "§cEsse produto já chegou em seu limite.");
                return;
            }
            if (EconomyManager.getAPI().debilitValue(p, fac.getTag(), blocoprotecao)) {
                facdb.setbProtecao(blocoAtual[0] + 1);
                updateItem(p, voltar, evoluir);
            }
        });
        // Regen
        setItem(11, regentemp);
        setItem(20, evoluir, e -> {
            if (tempoAtual[0] >= 1) {
                new ActionBar(p, "§cEsse produto já chegou em seu limite.");
                return;
            }
            if (EconomyManager.getAPI().debilitValue(p, fac.getTag(), temporegen)) {
                facdb.settRegn(tempoAtual[0] + 1);
                updateItem(p, voltar, evoluir);
            }
        });

        // Outros
        setItem(40, voltar, e -> {
            new MenuPrincipal(p).open(p);
        });
    }

    private String getColor(String value, int level){
        int s = Integer.valueOf(value.replace("Nível ", ""));
        if (value.contains(String.valueOf(level)))
            return "§a" + value;
        if (s < level)
            return "§c§m" + value + "§f";
        return "§f" + value;
    }

    private void updateItem(Player p, ItemBuilder voltar, ItemBuilder evoluir){
        new ProtecaoMenu(p, voltar, evoluir).open(p);
    }
}
