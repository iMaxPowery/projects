package imax.net.upgrade.menus;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import imax.net.upgrade.utils.ConvertTime;
import imax.net.upgrade.apis.ItemBuilder;
import imax.net.upgrade.apis.MenuBuilder;
import imax.net.upgrade.blocks.BlocksManager;
import imax.net.upgrade.faction.Methods;
import org.bukkit.Material;

public class MinaInfo extends MenuBuilder {

    public MinaInfo() {
        super("§dMina de Elixir", MenuSize.THREE_LINES);

        ItemBuilder mina;
        Methods m = new Methods();
        if (m.hasFac()) {
            Faction fac = FactionColl.get().getByTag(m.getFacTag());
            float value = ((m.getFacManager().getBreakedBlocks() * 100) / BlocksManager.getSize());

            mina = new ItemBuilder(Material.STAINED_GLASS_PANE, (short)2, "         §d§n§lMina de Elixir",
                    "",
                    "  §fStatus: §c§nOcupada",
                    "  §fTempo restante: §7" + ConvertTime.getTimeString((long) m.getFacManager().getTime()),
                    "  §fFacção: §7§n" + m.getFacTag(),
                    "  §fBlocos minerados: §7" + BlocksManager.getSize(),
                    "  §fLoots de §dElixir§f dropados: §7" + m.getFacManager().getBreakedBlocks() + " (" + value + "%)",
                    "  §fMembros: §7" + fac.getOnlinePlayers().size(),
                    "",
                    "§7* Mesmo uma facção dominando esse território",
                    "§7ainda é possível que uma facção inimiga",
                    "§7vá até lá para atrapalhar a dominante.",
                    "");
        } else {
            mina = new ItemBuilder(Material.STAINED_GLASS_PANE, (short)2, "         §d§n§lMina de Elixir",
                    "",
                    "  §fStatus: §aLivre",
                    "  §fA Mina está livre, use um ativador para domina-la.",
                    "");
        }
        setItem(13, mina);
    }
}
