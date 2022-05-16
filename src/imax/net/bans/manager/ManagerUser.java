package imax.net.bans.manager;

public class ManagerUser {

    /*public static HashMap<UUID, User> users = new HashMap<>();

    public static boolean loadAll(){
        try {
            FileConfiguration c = Bans.getInstance().database.getConfig();
                c.getConfigurationSection("").getKeys(false).forEach(value -> {
                    User user = new User(value, c.getString(value + ".Nome"));
                    user.setBanMot(c.getString(value + ".BanMotivo"));
                    user.setName(c.getString(value + ".Nome"));
                    user.setMuteMot(c.getString(value + ".MuteMotivo"));
                    user.setBannedPerm(c.getBoolean(value + ".BanidoPermanente"));
                    user.setBanned(c.getBoolean(value + ".Banido"));
                    user.setMuted(c.getBoolean(value + ".Mutado"));
                    user.setMutedPerm(c.getBoolean(value + ".MutadoPermanente"));
                    user.setTempoBan(c.getLong(value + ".TempoBan"));
                    user.setTempoMute(c.getLong(value + ".TempoMute"));
                    user.setAuthorMute(c.getString(value + ".AuthorMute"));
                    user.setAuthorBan(c.getString(value + ".AuthorBan"));
                    if (user.isBannedPerm() || user.isBanned || user.isMutedPerm() || user.isMuted)
                        users.put(user.uuid, user);
                });
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static String getUUID(String nome){
        FileConfiguration c = Bans.getInstance().database.getConfig();
        for (Map.Entry<UUID,User> n : users.entrySet()) {
            if (c.getString(n.getValue().getName()).equals(nome))
                return n.getValue().getName();
        }
        return "null";
    }

    public static void saveAll(){
        users.forEach((u, user) -> save(user));
    }
    public static boolean save(User user){
        try {
            FileConfiguration c = Bans.getInstance().database.getConfig();
            c.set(user.uuid + ".BanMotivo", user.banMot);
            c.set(user.uuid + ".Nome", user.name);
            c.set(user.uuid + ".MuteMotivo", user.muteMot);
            c.set(user.uuid + ".BanidoPermanente", user.BannedPerm);
            c.set(user.uuid + ".Banido", user.isBanned);
            c.set(user.uuid + ".Mutado", user.isMuted);
            c.set(user.uuid + ".MutadoPermanente", user.MutedPerm);
            c.set(user.uuid + ".TempoBan", user.tempoBan);
            c.set(user.uuid + ".TempoMute", user.tempoMute);
            c.set(user.uuid + ".AuthorMute", user.authorMute);
            c.set(user.uuid + ".AuthorBan", user.authorBan);
            Bans.getInstance().database.saveConfig();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }*/
}
