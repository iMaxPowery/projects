package imax.net.upgrade.events;

import com.massivecraft.factions.event.EventFactionsCreate;
import imax.net.upgrade.database.manager.DataBaseManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FacCreate implements Listener {

    @EventHandler
    public static void facCreate(EventFactionsCreate e){
        String factag = e.getFactionTag();
        DataBaseManager.getAPI().createFac(factag, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0);
        System.out.println("Facção " + factag + " criada com sucesso!");
    }
}
