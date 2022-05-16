package imax.net.bans.commands;

import imax.net.bans.utils.PunishType;
import imax.net.bans.database.manager.DBManagement;
import imax.net.bans.manager.ShowMessages;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Unban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        try {
            if (args.length == 0) {
                s.sendMessage("§cInsira um nick.");
                return false;
            }

            if (!s.hasPermission("street.unban")){
                s.sendMessage("§cSem permissão.");
                return false;
            }

            OfflinePlayer ofp = Bukkit.getOfflinePlayer(args[0]);

            if (!DBManagement.getAPI().isPunished(ofp.getUniqueId(), PunishType.BAN) && !DBManagement.getAPI().isPunished(ofp.getUniqueId(), PunishType.BAN_PERMA)){
                s.sendMessage("§cEsse jogador não está banido.");
                return false;
            }

            try {
                DBManagement.getAPI().delete(ofp.getUniqueId(), PunishType.BAN);
                DBManagement.getAPI().delete(ofp.getUniqueId(), PunishType.BAN_PERMA);
            }catch (Exception e) {
                e.printStackTrace();
            }

            ShowMessages.unBan(ofp.getName());
        }catch (Exception er){
            er.printStackTrace();
            s.sendMessage("§cOcorreu um erro ao desbanir esse jogador.");
        }
        return false;
    }
}
