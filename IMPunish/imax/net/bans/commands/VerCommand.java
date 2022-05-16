package imax.net.bans.commands;

import imax.net.bans.manager.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (args.length < 1){
            s.sendMessage("§cInsira o nick do jogador.");
            return false;
        }
        if (!s.hasPermission("street.verbans")){
            s.sendMessage("§cSem permissão.");
            return false;
        }
        OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);

        User user;

        /*if (DBManagement.getAPI().getPunishment(p.getUniqueId().toString()) != null) {

        }
        else{
            s.sendMessage("§cNenhuma punição do jogador encontrada.");
        }*/
        return false;
    }

    private String returnName(boolean b){
        return b ? "§aSim" : "§cNão";
    }
}
