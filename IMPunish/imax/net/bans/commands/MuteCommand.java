package imax.net.bans.commands;

import imax.net.bans.manager.BandMType;
import imax.net.bans.utils.PunishType;
import imax.net.bans.database.manager.DBManagement;
import imax.net.bans.events.SayPunish;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (args.length != 1){
            s.sendMessage("§cInsira apenas o nick do jogador.");
            return false;
        }

        if (!s.hasPermission("street.mute")){
            s.sendMessage("§cSem permissão.");
            return false;
        }
        OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);

        if (DBManagement.getAPI().isPunished(p.getUniqueId(), PunishType.MUTE) || DBManagement.getAPI().isPunished(p.getUniqueId(), PunishType.MUTE_PERMA)){
            s.sendMessage("§cEsse jogador já está punido.");
            return false;
        }

        BandMType type = new BandMType(p.getUniqueId(), p.getName(), PunishType.MUTE);
        type.setAuthor(s.getName());
        type.setLast(0);
        Player ps = (Player) s;
        SayPunish.list.put(ps.getUniqueId(), type);

        s.sendMessage("§7");
        s.sendMessage("    §7Digite o motivo da punição desse jogador no chat.");
        s.sendMessage("§7");
        return false;
    }
}
