package imax.net.upgrade.apis;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import imax.net.upgrade.FUpgrade;
import imax.net.upgrade.database.DataBaseIF;
import imax.net.upgrade.database.manager.DataBaseManager;

import java.sql.ResultSet;
import java.util.HashMap;

public class FUpgradeAPI {
    public static FUpgradeAPI fUpgradeAPI;
    private static DataBaseManager manager = DataBaseManager.getAPI();

    public HashMap<Faction, Integer> getAll(){
        HashMap<Faction, Integer> map = new HashMap<>();
        DataBaseIF db = FUpgrade.getInstance().database;
        DataBaseManager.getAPI().saveAll();

        try {
            if (db != null && db.hasConnection()) {
                ResultSet rs = db.pesquisa("SELECT * FROM `fupgrades`");

                while (rs.next()) {
                    String nome = rs.getString("fac");
                    int coins = rs.getInt("coins");
                    Faction fac = FactionColl.get().getByTag(nome);
                    if (fac != null)
                    map.put(fac, coins);
                }
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }
        return map;
    }
    public int fGetLimiteClaimT(String fac) {
        if (manager.getFac(fac) == null)
            return 0;
        return manager.getFac(fac).getClaimL();
    }
    public int fGetDurationClaim(String fac){
        if (manager.getFac(fac) == null)
            return 0;
        return manager.getFac(fac).getDclaimT();
    }
    public int fGetDurationAtaque(String fac){
        if (manager.getFac(fac) == null)
            return 0;
        return manager.getFac(fac).getdAtaque();
    }
    public int fGettRegeneracaoBloco(String fac){
        if (manager.getFac(fac) == null)
            return 0;
        return manager.getFac(fac).gettRegn();
    }
    public int fGetperdaPoder(String fac){
        if (manager.getFac(fac) == null)
            return 0;
        return manager.getFac(fac).getpPoder();
    }
    public int fGetTempoPoderRegen(String fac){
        if (manager.getFac(fac) == null)
            return 0;
        return manager.getFac(fac).gettRegen();
    }
    public int fGetSpawnerLevel(String fac){
        if (manager.getFac(fac) == null)
            return 0;
        return manager.getFac(fac).getdSpawner();
    }
    public int fGetLimiteValor(String fac){
        if (manager.getFac(fac) == null)
            return 0;
        return manager.getFac(fac).getlValor();
    }
    public int fGetAntiLucro(String fac){
        if (manager.getFac(fac) == null)
            return 0;
        return manager.getFac(fac).getaLucro();
    }
}
