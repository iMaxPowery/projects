package imax.net.upgrade.events;

import com.massivecraft.factions.event.EventFactionsDisband;
import imax.net.upgrade.database.manager.DataBaseManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FacDelete implements Listener {

    @EventHandler
    public static void facDelete(EventFactionsDisband e){
        DataBaseManager.getAPI().delete(e.getFaction().getTag());
    }
}
