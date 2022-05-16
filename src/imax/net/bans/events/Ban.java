package imax.net.bans.events;

import imax.net.bans.api.BotJDA;
import imax.net.bans.manager.BandMType;
import imax.net.bans.utils.ConfigYML;
import imax.net.bans.utils.ConvertTime;
import imax.net.bans.utils.PunishType;
import imax.net.bans.database.manager.DBManagement;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Ban implements Listener {

    @EventHandler
    public static void playerCommand(PlayerCommandPreprocessEvent e){
        String msg = e.getMessage();

        Player p = e.getPlayer();

        if (SayPunish.list.containsKey(e.getPlayer().getUniqueId())) {
            if (msg.startsWith("/bconfirmar")) {
                BandMType type = SayPunish.list.get(p.getUniqueId());
                SayPunish.list.remove(e.getPlayer().getUniqueId());
                if (type.getTipo() == PunishType.BAN || type.getTipo() == PunishType.BAN_PERMA) {

                    if (DBManagement.getAPI().isPunished(type.getUuid(), type.getTipo())){
                        p.sendMessage("§cEsse jogador já está punido.");
                        return;
                    }

                    if (p.hasPermission("street.bans"))
                        type.ban(false);
                    else if (p.hasPermission("street.bans.ajudantes")){
                        p.sendMessage("");
                        p.sendMessage("§7Sua solicitação de ban foi enviada com sucesso!");
                        p.sendMessage("");
                        String t = type.getTipo() == PunishType.BAN_PERMA ? "Permanente" : ConvertTime.getTimeString((long) type.getTempo());
                        BotJDA.annallyz(type.getNome(), type.getMotivo(), type.getProva(),
                                t, type.getAuthor());
                    }
                } else {
                    type.mute(false);
                }
                SayPunish.list.remove(p.getUniqueId());
            } else if (msg.startsWith("/bcancelar")) {
                SayPunish.list.remove(p.getUniqueId());
                p.sendMessage("§cPunição cancelada com sucesso!");
            } else if (msg.startsWith("/sbconfirmar")) {
                BandMType type = SayPunish.list.get(p.getUniqueId());
                SayPunish.list.remove(e.getPlayer().getUniqueId());
                if (type.getTipo() == PunishType.BAN || type.getTipo() == PunishType.BAN_PERMA) {

                    if (DBManagement.getAPI().isPunished(type.getUuid(), PunishType.BAN)) {
                        p.sendMessage("§cEsse jogador já está punido.");
                        return;
                    }

                    if (p.hasPermission("street.bans")) {
                        type.ban(true);
                        p.sendMessage("§aPunido com sucesso!");
                    }
                    else if (p.hasPermission("street.bans.ajudantes")) {
                        p.sendMessage("");
                        p.sendMessage("§7Sua solicitação de ban foi enviada com sucesso!");
                        p.sendMessage("");
                        String t = type.getTipo() == PunishType.BAN_PERMA ? "Permanente" : ConvertTime.getTimeString((long) type.getTempo());
                        if (ConfigYML.BOT)
                        BotJDA.annallyz(type.getNome(), type.getMotivo(), type.getProva(),
                                t, type.getAuthor());
                    }
                }   else {
                    p.sendMessage("§aPunido com sucesso!");
                    type.mute(true);
                }
            }else if (msg.startsWith("/sbipconfirmar")) {
                BandMType type = SayPunish.list.get(p.getUniqueId());
                SayPunish.list.remove(e.getPlayer().getUniqueId());

                if (type.getTipo() == PunishType.BAN || type.getTipo() == PunishType.BAN_PERMA) {
                    type.setNome(String.valueOf(Bukkit.getPlayer(type.getUuid()).getAddress().getAddress().getHostAddress()));
                }

                if (DBManagement.getAPI().isPunished(type.getUuid(), PunishType.BAN)) {
                    p.sendMessage("§cEsse jogador já está punido.");
                    return;
                }

                if (p.hasPermission("street.bans")) {
                    type.ban(true);
                    p.sendMessage("§aPunido com sucesso!");
                }
                else if (p.hasPermission("street.bans.ajudantes")) {
                    p.sendMessage("");
                    p.sendMessage("§7Sua solicitação de ban foi enviada com sucesso!");
                    p.sendMessage("");
                    String t = type.getTipo() == PunishType.BAN_PERMA ? "Permanente" : ConvertTime.getTimeString((long) type.getTempo());
                    if (ConfigYML.BOT)
                    BotJDA.annallyz(type.getNome(), type.getMotivo(), type.getProva(),
                            t, type.getAuthor());
                }
            }
        }
    }
}
