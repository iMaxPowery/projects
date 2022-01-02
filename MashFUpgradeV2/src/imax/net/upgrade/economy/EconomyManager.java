package imax.net.upgrade.economy;

import imax.net.upgrade.apis.ActionBar;
import imax.net.upgrade.database.manager.DataBase;
import imax.net.upgrade.database.manager.DataBaseManager;
import org.bukkit.entity.Player;

public class EconomyManager {

    private static EconomyManager api = new EconomyManager();

    public static EconomyManager getAPI() {
        return api;
    }

    public boolean contaisValue(String factag, int value) {
        DataBase facdb = DataBaseManager.getAPI().getFac(factag);

        return facdb.getCoins() >= value;
    }

    public void depositValue(String factag, double value) {
        DataBase facdb = DataBaseManager.getAPI().getFac(factag);

        if (facdb != null) {
            double v = facdb.getCoins();
            facdb.setCoins((int) (v + value));
        }else{
            System.out.println("Null fac");
        }
    }

    public boolean debilitValue(Player p, String factag, int value) {
        if (contaisValue(factag, value)) {
            try {
                DataBase facdb = DataBaseManager.getAPI().getFac(factag);
                int v = facdb.getCoins();
                facdb.setCoins(v - value);
                new ActionBar(p, "§aVocê comprou esse produto com sucesso!");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                new ActionBar(p, "§cOcorreu um erro ao efetuar sua transação, comunique um staffer.");
                return false;
            }
        } else {
            new ActionBar(p, "§cVocê não possui Cristais o suficiente.");
        }
        return false;
    }
}
