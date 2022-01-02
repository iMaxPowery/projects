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

public class SpawnersMenu extends MenuBuilder {


    public SpawnersMenu(Player p, ItemBuilder voltar, ItemBuilder evoluir) {
        super("§8Upgrade - Spawner", MenuSize.FIVE_LINES);

        MPlayer mp = MPlayer.get(p);
        Faction fac = mp.getFaction();

        DataBase facdb = DataBaseManager.getAPI().getFac(fac.getTag());

        int desbloquearspV, antilucroV;
        final int[] spawnersAtual = new int[1];
        final int[] alucroAtual = new int[1];

        desbloquearspV = 10;
        antilucroV = 10;

        if (facdb == null)
            return;
        spawnersAtual[0] = facdb.getdSpawner();
        alucroAtual[0] = facdb.getaLucro();

        ItemBuilder desbloqueio = new ItemBuilder(Material.MOB_SPAWNER, "      §6§lDESBLOQUEAR SPAWNER", "",
                " §6➥ " + getColor("Nível 0", spawnersAtual[0]) + " §8➝ §7Ovelha",
                " §6➥ " + getColor("Nível 1", spawnersAtual[0]) + " §8➝ §7Vaca",
                " §6➥ " + getColor("Nível 2", spawnersAtual[0]) + " §8➝ §7Vaca Cogumelo",
                " §6➥ " + getColor("Nível 3", spawnersAtual[0]) + " §8➝ §7Porco Zumbi",
                " §6➥ " + getColor("Nível 4", spawnersAtual[0]) + " §8➝ §7Golem de Ferro",
                " §6➥ " + getColor("Nível 5", spawnersAtual[0]) + " §8➝ §7Aldeão",
                " §6➥ " + getColor("Nível 6", spawnersAtual[0]) + " §8➝ §7Cubo de Magma",
                " §6➥ " + getColor("Nível 7", spawnersAtual[0]) + " §8➝ §7Enderman",
                " §6➥ " + getColor("Nível 8", spawnersAtual[0]) + " §8➝ §7Wither",
                "",
                " §aNível atual: " + spawnersAtual[0] + "/8"," §aValor: " + desbloquearspV, "", " §7Clique no item abaixo para evoluir.");
        ItemBuilder al = new ItemBuilder(Material.EMERALD, "      §6§lANTI LUCRO", "",
                " §fEsse produto reduzirá a quantidade de",
                " §fspawners dropados em uma explosão.",
                "",
                " §fObs: Cuidado, caso você exploda seus próprios",
                " §fspawners, ele também poderá não dropar.",
                "",
                " §aNível atual: " + alucroAtual[0] + "/1"," §aValor: " + antilucroV, "", " §7Clique no item abaixo para evoluir.");

        // Desbloquear Spawners
        setItem(10, desbloqueio);
        setItem(19, evoluir, e -> {
            if (spawnersAtual[0] >= 8){
                new ActionBar(p, "§cEsse produto já chegou em seu limite.");
                return;
            }
            if (EconomyManager.getAPI().debilitValue(p, fac.getTag(), desbloquearspV)){
                facdb.setdSpawner(spawnersAtual[0] + 1);
                updateItem(p, voltar, evoluir);
            }
        });

        // Anti Lucro
        setItem(11, al);
        setItem(20, evoluir, e -> {
            if (alucroAtual[0] >= 2){
                new ActionBar(p, "§cEsse produto já chegou em seu limite.");
                return;
            }
            if (EconomyManager.getAPI().debilitValue(p, fac.getTag(), antilucroV)){
                facdb.setaLucro(alucroAtual[0] + 1);
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
        new SpawnersMenu(p, voltar, evoluir).open(p);
    }
}