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

public class MembrosMenu extends MenuBuilder {

    public MembrosMenu(Player p, ItemBuilder voltar, ItemBuilder evoluir) {
        super("Â§8Upgrade - Membros", MenuSize.FIVE_LINES);

        MPlayer mp = MPlayer.get(p);
        Faction fac = mp.getFaction();

        DataBase facdb = DataBaseManager.getAPI().getFac(fac.getTag());

        int limiteV, perdapoderV, regeneracaoV;
        final int[] limiteAtual = new int[1];
        final int[] perdaAtual = new int[1];
        final int[] regenAtual = new int[1];

        limiteAtual[0] = facdb.getlMembros();
        perdaAtual[0]= facdb.getpPoder();
        regenAtual[0]= facdb.gettRegen();

        limiteV = 5;
        perdapoderV = 5;
        regeneracaoV = 7;
        ItemBuilder limite = new ItemBuilder(Material.EYE_OF_ENDER, "      §6§lLIMITE DE MEMBROS", "",
                " §fQue facção aperta heim? Que tal liberar",
                " §fainda mais espaço nela?",
                "",
                " §fUm membro extra será adicionado por cada level.",
                "",
                " §aNível atual: " + limiteAtual[0] + "/5"," §aValor: " + limiteV, "", " §7Clique no item abaixo para evoluir.");
        ItemBuilder pdp = new ItemBuilder(Material.MAGMA_CREAM, "      §6§lPERDA DE PODER", "",
                " §fEsse produto reduzirá sua perda de poder",
                " §fde 2.5 para 2.0.",
                "",
                " §aNível atual: " + perdaAtual[0] + "/1"," §aValor: " + perdapoderV, "", " §7Clique no item abaixo para evoluir.");
        ItemBuilder rdp = new ItemBuilder(Material.APPLE, "      §6§lTEMPO DE REGENERAÇÃO", "",
                " §fRecupere seu poder mais rapidamente!",
                " §fDe 1 poder p/h para 1.5 p/h.",
                "",
                " §aNível atual: " + regenAtual[0] + "/1"," §aValor: " + regeneracaoV, "", " §7Clique no item abaixo para evoluir.");

        // Limite de Membros
        setItem(10, limite);
        setItem(19, evoluir, e -> {
            if (limiteAtual[0] >= 5) {
                new ActionBar(p, "§cEsse produto já chegou em seu limite.");
                return;
            }
            if (EconomyManager.getAPI().debilitValue(p, fac.getTag(), limiteV)) {
                facdb.setlMembros(limiteAtual[0] + 1);
                fac.setMemberBoost(limiteAtual[0] + 1);
                updateItem(p, voltar, evoluir);
            }
        });
        // Perda de Poder
        setItem(11, pdp);
        setItem(20, evoluir, e -> {
            if (perdaAtual[0] >= 1) {
                new ActionBar(p, "§cEsse produto já chegou em seu limite.");
                return;
            }
            if (EconomyManager.getAPI().debilitValue(p, fac.getTag(), perdapoderV)) {
                facdb.setpPoder(perdaAtual[0] + 1);
                updateItem(p, voltar, evoluir);
            }
        });

        // Tempo de RegeneraÃ§Ã£o
        setItem(12, rdp);
        setItem(21, evoluir, e -> {
            if (regenAtual[0] >= 1) {
                new ActionBar(p, "§cEsse produto já chegou em seu limite.");
                return;
            }
            if (EconomyManager.getAPI().debilitValue(p, fac.getTag(), regeneracaoV)) {
                facdb.settRegen(regenAtual[0] + 1);
                updateItem(p, voltar, evoluir);
            }
        });

        // Outros
        setItem(40, voltar, e -> {
            new MenuPrincipal(p).open(p);
        });
    }

    private void updateItem(Player p, ItemBuilder voltar, ItemBuilder evoluir){
        new MembrosMenu(p, voltar, evoluir).open(p);
    }
}
