package imax.net.bans.commands;

import imax.net.bans.utils.PunishType;
import imax.net.bans.database.manager.DBManagement;
import imax.net.bans.manager.ShowMessages;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Unmute implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        try {
            if (args.length == 0) {
                s.sendMessage("§cInsira um nick.");
                return false;
            }

            if (!s.hasPermission("street.unmute")){
                s.sendMessage("§cSem permissão.");
                return false;
            }

            OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);

            if (!DBManagement.getAPI().isPunished(t.getUniqueId(), PunishType.MUTE) && !DBManagement.getAPI().isPunished(t.getUniqueId(), PunishType.MUTE_PERMA)){
                s.sendMessage("§cEsse jogador não está mutado.");
                return false;
            }

            try {
                DBManagement.getAPI().delete(t.getUniqueId(), PunishType.MUTE);
            }catch (Exception e) {
                DBManagement.getAPI().delete(t.getUniqueId(), PunishType.MUTE_PERMA);
            }

            ShowMessages.unMute(t.getName());
        }catch (Exception er){
            er.printStackTrace();
            s.sendMessage("§cOcorreu um erro ao desmutar esse jogador.");
        }
        return false;
    }
}
