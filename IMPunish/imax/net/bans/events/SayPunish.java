package imax.net.bans.events;

import imax.net.bans.manager.BandMType;
import imax.net.bans.utils.ConvertTime;
import imax.net.bans.utils.PunishType;
import imax.net.bans.bungeecord.ServerList;
import imax.net.bans.manager.Time;
import imax.net.bans.utils.ConfigYML;
import net.md_5.bungee.api.chat.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.UUID;

public class SayPunish implements Listener {

    public static HashMap<UUID, BandMType> list = new HashMap<>();

    @EventHandler
    public static void onSay(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        try{
        if (list.containsKey(p.getUniqueId())) {
            BandMType type = list.get(p.getUniqueId());
            e.setCancelled(true);

            if (e.getMessage().startsWith("cancelar")) {
                list.remove(p.getUniqueId());
                return;
            }
            int v = type.getLast();

            switch (v) {
                case 0:
                    type.setMotivo(e.getMessage());
                    type.setLast(1);
                    p.sendMessage("§7");
                    p.sendMessage("    §7Digite o tempo de punição desse jogador no chat.");
                    p.sendMessage("§7  §f* §7Casjo seja §c§npermanente§7, basta digitar '§cpermanente§7' no chat.");
                    p.sendMessage("§7");
                    break;
                case 1:
                    type.setLast(2);
                    if (e.getMessage().startsWith("permanente") || e.getMessage().startsWith("perma"))
                        if (type.getTipo() == PunishType.MUTE)
                            type.setTipo(PunishType.MUTE_PERMA);
                        else
                            type.setTipo(PunishType.BAN_PERMA);
                    else {
                        long tempo = Time.convertToTime(e.getMessage().toLowerCase(), p);
                        type.setTempo(tempo * 1);
                        if (tempo <= 2) {
                            type.setLast(1);
                            p.sendMessage("§7");
                            p.sendMessage("    §7Ocorreu um erro, tente novamente ou cancele a ação.");
                            p.sendMessage("§7");
                            return;
                        }
                    }
                    if (ConfigYML.BungeeCord) {
                        p.sendMessage("§7");
                        p.sendMessage("    §7Clique no servidor que deseja punir o jogador.");
                        p.sendMessage("§7  §7Lista:");
                        ServerList.sendMessageList(p);
                        p.sendMessage("§7");
                    }else {
                        type.setLast(3);
                        p.sendMessage("§7");
                        p.sendMessage("    §7Digite a prova para a punição desse jogador no chat.");
                        p.sendMessage("§7  §f* §7Para cancelar, basta digitar '§ccancelar§7' no chat.");
                        p.sendMessage("§7");
                    }
                    break;
                case 2:
                    type.setLast(3);
                    p.sendMessage("§7");
                    p.sendMessage("    §7Digite a prova para a punição desse jogador no chat.");
                    p.sendMessage("§7  §f* §7Para cancelar, basta digitar '§ccancelar§7' no chat.");
                    p.sendMessage("§7");
                    break;
                case 3:
                    type.setProva(e.getMessage());

                    p.sendMessage("§7");
                    p.sendMessage("§7     Nick ⇾ " + type.getNome());
                    p.sendMessage("§7     Motivo ⇾ " + type.getMotivo());
                    if (!(type.getTipo() == PunishType.BAN_PERMA) && (type.getTipo() == PunishType.MUTE_PERMA))
                        p.sendMessage("§7     Tempo ⇾ " + ConvertTime.getTimeString((long) type.getTempo()));
                    else
                        p.sendMessage("§7     Tempo ⇾ Permanente");
                    p.sendMessage("§7     Prova ⇾ " + type.getProva());
                    p.sendMessage("§7");


                    p.sendMessage("§7");
                    p.sendMessage("    §7Deseja confirmar a punição sobre este jogador?");
                    TextComponent accept = new TextComponent(ConfigYML.CONFIRMAR_ICON);
                    TextComponent cancel = new TextComponent(ConfigYML.CANCELAR_ICON);
                    ClickEvent click = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bconfirmar");
                    ComponentBuilder builder = new ComponentBuilder(ConfigYML.CONFIRMAR);
                    ComponentBuilder builder2 = new ComponentBuilder(ConfigYML.CANCELAR);
                    HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_TEXT, builder.create());
                    HoverEvent hover2 = new HoverEvent(HoverEvent.Action.SHOW_TEXT, builder2.create());
                    ClickEvent click2 = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/bcancelar");
                    cancel.setClickEvent(click2);
                    accept.setClickEvent(click);

                    cancel.setHoverEvent(hover2);
                    accept.setHoverEvent(hover);

                    accept.addExtra(cancel);

                    if (p.hasPermission("im.supressban")) {
                        TextComponent supress = new TextComponent(ConfigYML.CONFIRMSILLENCE_ICON);
                        ComponentBuilder builder3 = new ComponentBuilder(ConfigYML.CONFIRMSILLENCE);
                        HoverEvent hover3 = new HoverEvent(HoverEvent.Action.SHOW_TEXT, builder3.create());
                        ClickEvent click3 = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/sbconfirmar");
                        supress.setHoverEvent(hover3);
                        supress.setClickEvent(click3);

                        accept.addExtra(supress);
                    }

                    if (p.hasPermission("im.ipban")) {
                        TextComponent supress = new TextComponent(ConfigYML.IPBAN_ICON);
                        ComponentBuilder builder3 = new ComponentBuilder(ConfigYML.IPBAN);
                        HoverEvent hover3 = new HoverEvent(HoverEvent.Action.SHOW_TEXT, builder3.create());
                        ClickEvent click3 = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/sbipconfirmar");
                        supress.setHoverEvent(hover3);
                        supress.setClickEvent(click3);

                        accept.addExtra(supress);
                    }

                    p.spigot().sendMessage(accept);
                    p.sendMessage("§7");
                    break;
                }
            }
        }catch (Exception er){

        }
    }
}
