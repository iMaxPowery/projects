package imax.net.bans.commands;

import imax.net.bans.manager.BandMType;
import imax.net.bans.utils.PunishType;
import imax.net.bans.manager.ShowMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (args.length <= 1){
            s.sendMessage("§cInsira o motivo.");
            return false;
        }
        if (!s.hasPermission("street.kick")){
            s.sendMessage("§cSem permissão.");
            return false;
        }
        Player p = Bukkit.getPlayer(args[0]);

        if (p == null){
            s.sendMessage("§cJogador offline.");
            return false;
        }

        String msg = "";
        for (int i = 1; i < args.length; i++)
            msg += args[i] + " ";

        BandMType type = new BandMType(p.getUniqueId(), p.getName(), PunishType.KICK);
        type.setAuthor(s.getName());
        type.setMotivo(msg);
        type.kickPlayer();
        ShowMessages.showKick(type.getNome(), msg, s.getName());

        return false;
    }
}
