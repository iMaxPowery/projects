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

public class ClaimMenu extends MenuBuilder {

    public ClaimMenu(Player p, ItemBuilder voltar, ItemBuilder evoluir) {
        super("§8Upgrade - Claim", MenuSize.FIVE_LINES);
        MPlayer mp = MPlayer.get(p);
        Faction fac = mp.getFaction();

        int limitetempV, claimtempV, ataquetempV;
        final int[] limiteAtual = new int[1];
        final int[] claimAtual = new int[1];
        final int[] ataqueAtual = new int[1];


        limitetempV = 5;
        claimtempV = 5;
        ataquetempV = 20;

        DataBase facdb = DataBaseManager.getAPI().getFac(fac.getTag());

        limiteAtual[0] = facdb.getDclaimT();
        claimAtual[0] = facdb.getClaimL();
        ataqueAtual[0] = facdb.getdAtaque();

        ItemBuilder limitetemp = new ItemBuilder(Material.ENDER_PEARL, "      §6§lLIMITE DE CLAIM TEMPORÁRIO", "",
                " §fCansado de pedir o território ",
                " §fde seu aliado emprestado?",
                " §fCom esse upgrade você ganhará ",
                " §f+1 território por cada level.",
                "",
                " §aNível atual: " + limiteAtual[0] + "/2", " §aValor: " + limitetempV, "", " §7Clique no item abaixo para evoluir.");
        ItemBuilder claimtemp = new ItemBuilder(Material.MAGMA_CREAM, "      §6§lDURAÇÃO CLAIM TEMPORÁRIO", "",
                " §fEsse produto é consumido toda vez ",
                " §fque um terreno temporário chega ao seu fim.",
                "",
                " §6➥ " + getColor("Nível 0", claimAtual[0]) + " §8➝ §730m",
                " §6➥ " + getColor("Nível 1", claimAtual[0]) + " §8➝ §745m",
                " §6➥ " + getColor("Nível 2", claimAtual[0]) + " §8➝ §750m",
                " §6➥ " + getColor("Nível 3", claimAtual[0]) + " §8➝ §755m",
                "",
                " §aNível atual: " + claimAtual[0] + "/3", " §aValor: " + claimtempV, "", " §7Clique no item abaixo para evoluir.");
        ItemBuilder ataquetemp = new ItemBuilder(Material.NETHERRACK, "      §6§lDURAÇÃO DE ATAQUE", "",
                " §fReduza o tempo que sua facção fica em ataque.",
                "",
                " §6➥ " + getColor("Nível 0", ataqueAtual[0]) + " §8➝ §75m",
                " §6➥ " + getColor("Nível 1", ataqueAtual[0]) + " §8➝ §74m 30s",
                " §6➥ " + getColor("Nível 2", ataqueAtual[0]) + " §8➝ §74m",
                "",
                " §aNível atual: " + ataqueAtual[0] + "/2", " §aValor: " + ataquetempV, "", " §7Clique no item abaixo para evoluir.");

        // Limite temporÃ¡rio
        setItem(10, limitetemp);
        setItem(19, evoluir, e -> {
            if (limiteAtual[0] >= 2) {
                new ActionBar(p, "§cEsse produto já chegou em seu limite.");
                return;
            }
            if (EconomyManager.getAPI().debilitValue(p, fac.getTag(), limitetempV)) {
                facdb.setDclaimT(limiteAtual[0] + 1);
                updateItem(p, voltar, evoluir);
            }
        });

        // Tempo de Claim
        setItem(11, claimtemp);
        setItem(20, evoluir, e -> {
            if (claimAtual[0] >= 3) {
                new ActionBar(p, "§cEsse produto já chegou em seu limite.");
                return;
            }
            if (EconomyManager.getAPI().debilitValue(p, fac.getTag(), claimtempV)) {
                facdb.setClaimL(claimAtual[0] + 1);
                updateItem(p, voltar, evoluir);
            }
        });

        // Ataque tempo
        setItem(12, ataquetemp);
        setItem(21, evoluir, e -> {
            if (ataqueAtual[0] >= 2) {
                new ActionBar(p, "§cEsse produto já chegou em seu limite.");
                return;
            }
            if (EconomyManager.getAPI().debilitValue(p, fac.getTag(), ataquetempV)) {
                facdb.setdAtaque(ataqueAtual[0] + 1);
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
        new ClaimMenu(p, voltar, evoluir).open(p);
    }
}
