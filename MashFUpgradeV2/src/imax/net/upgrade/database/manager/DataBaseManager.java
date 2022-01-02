package imax.net.upgrade.database.manager;

import imax.net.upgrade.FUpgrade;
import imax.net.upgrade.database.DataBaseIF;

import java.sql.ResultSet;
import java.util.HashMap;

public class DataBaseManager {
    private static DataBaseManager API = new DataBaseManager();
    public HashMap<String, DataBase> facs = new HashMap();

    public static DataBaseManager getAPI() {
        return API;
    }

    public DataBase createFac(String nome, int coins, int claimL, int dclaimT, int dAtaque, int bProtecao, int tRegn, int lMembros,
                                 int pPoder, int tRegen, int dSpawner, int lValor, int aLucro) {
        DataBase pp = new DataBase(nome, coins, claimL, dclaimT, dAtaque, bProtecao, tRegn, lMembros, pPoder, tRegen, dSpawner, lValor, aLucro);
        facs.put(pp.getNome(), pp);
        return pp;
    }
    public DataBase getFac(String factag) {
        return this.facs.getOrDefault(factag, null);
    }

    public void carregar() {
        DataBaseIF db = FUpgrade.getInstance().database;

        try {
            if (db != null && db.hasConnection()) {
                ResultSet rs = db.pesquisa("SELECT * FROM `fupgrades`");

                while (rs.next()) {
                    String nome = rs.getString("fac");
                    int coins = rs.getInt("coins");
                    int claimL = rs.getInt("claimL");
                    int dclaimT = rs.getInt("dclaimT");
                    int dAtaque = rs.getInt("dAtaque");
                    int bProtecao = rs.getInt("bProtecao");
                    int tRegn = rs.getInt("tRegn");
                    int lMembros = rs.getInt("lMembros");
                    int pPoder = rs.getInt("pPoder");
                    int tRegen = rs.getInt("tRegen");
                    int dSpawner = rs.getInt("dSpawner");
                    int lValor = rs.getInt("lValor");
                    int aLucro = rs.getInt("aLucro");
                    this.createFac(nome, coins, claimL, dclaimT, dAtaque, bProtecao, tRegn, lMembros, pPoder, tRegen, dSpawner, lValor, aLucro);
                }
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    public void delete(String factag) {
        DataBaseIF db = FUpgrade.getInstance().database;

        try {
            if (db != null && db.hasConnection()) {
                db.execute("DELETE FROM `fupgrades` WHERE `fac` = '" + factag + "'");
                facs.remove(factag);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void debug(){
        facs.forEach((v, v2) -> System.out.println(v + " " + v2));
    }

    public void saveAll(){
        long i = System.currentTimeMillis();
        System.out.println("[FUpgrade] Salvando...");
        facs.forEach((p, v) -> v.salvar());
        System.out.println("[FUpgrade] Salvo com sucesso! Tempo gasto: " + (System.currentTimeMillis() - i) + " ms.");
    }

    public HashMap<String, DataBase> getFacs() {
        return facs;
    }
}
