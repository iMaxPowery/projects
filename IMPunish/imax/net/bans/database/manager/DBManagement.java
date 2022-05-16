package imax.net.bans.database.manager;

import imax.net.bans.Bans;
import imax.net.bans.database.DBIF;
import imax.net.bans.utils.PunishType;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DBManagement {
    private static DBManagement API = new DBManagement();
    public HashMap<String, DataBase> usersBan = new HashMap();
    public HashMap<String, DataBase> usersMute = new HashMap();

    public static DBManagement getAPI() {
        return API;
    }

    public DataBase createPunish(String uuid, String nome, String author, String motivo, String prova, String tipo, double tempo, String server) {
        DataBase punish = new DataBase(uuid, nome, author, motivo, prova, tipo, tempo, server);
        if (tipo.equals(PunishType.BAN_PERMA.toString()) || tipo.equals(PunishType.BAN.toString()))
            usersBan.put(punish.getUuid(), punish);
        else
            usersMute.put(punish.getUuid(), punish);
        return punish;
    }
    public DataBase getPunishBans(String ID) {
        return this.usersBan.getOrDefault(ID, null);
    }

    public DataBase getPunishMute(String ID) {
        return this.usersMute.getOrDefault(ID, null);
    }

    public void loadPunishments() {
        DBIF db = Bans.getInstance().database;

        try {
            if (db != null && db.hasConnection()) {
                ResultSet rs = db.pesquisa("SELECT * FROM `punishments`");

                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    String nome = rs.getString("nome");
                    String author = rs.getString("author");
                    String motivo = rs.getString("motivo");
                    String prova = rs.getString("prova");
                    String tipo = rs.getString("tipo");
                    double tempo = rs.getDouble("tempo");
                    String server = rs.getString("server");
                    this.createPunish(uuid, nome, author, motivo, prova, tipo, tempo, server);
                }
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }
    }

    public boolean isPunished(UUID uuid, PunishType type){
        try {
            if (type == PunishType.BAN_PERMA || type == PunishType.BAN)
                for (Map.Entry<String, DataBase> user : usersBan.entrySet()) {
                    if (user.getValue().getUuid().equals(uuid.toString()))
                        return true;
                }
            else
                for (Map.Entry<String, DataBase> user : usersMute.entrySet()) {
                    if (user.getValue().getUuid().equals(uuid.toString()))
                        return true;
                }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void delete(UUID uuid, PunishType type) {
        DBIF db = Bans.getInstance().database;

        try {
            if (db != null && db.hasConnection()) {
                db.execute("DELETE FROM `punishments` WHERE `uuid` = '"
                        + uuid.toString() + "' AND `tipo` = '" + type.toString() + "'");
                if (type == PunishType.BAN_PERMA || type == PunishType.BAN)
                    usersBan.remove(uuid.toString());
                else
                    usersMute.remove(uuid.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(String uuid, String type) {
        DBIF db = Bans.getInstance().database;

        try {
            if (db != null && db.hasConnection()) {
                db.execute("DELETE FROM `punishments` WHERE `uuid` = '"
                        + uuid + "' AND `tipo` = '" + type + "'");
                if (type.equals(PunishType.BAN_PERMA.toString()) || type.equals(PunishType.BAN.toString()))
                    usersBan.remove(uuid);
                else
                    usersMute.remove(uuid);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveAll(){
        long i = System.currentTimeMillis();
        System.out.println("[IMPunish] Salvando...");
        usersMute.forEach((p, v) -> v.savePunishments());
        usersBan.forEach((p, v) -> v.savePunishments());
        System.out.println("[IMPunish] Salvo com sucesso! Tempo gasto: " + (System.currentTimeMillis() - i) + " ms.");
    }
}
