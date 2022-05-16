package imax.net.bans.api;

import imax.net.bans.utils.PunishType;
import imax.net.bans.events.SayPunish;
import imax.net.bans.manager.BandMType;
import imax.net.bans.utils.ConfigYML;
import imax.net.bans.utils.ConvertTime;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

import java.awt.*;

public class Event extends ListenerAdapter {

    public void onMessageReactionAdd(MessageReactionAddEvent e) {
        e.getChannelType();
        if (!e.getUser().isBot()) {
            if (!e.getMember().hasPermission(Permission.BAN_MEMBERS)){
                return;
            }
            if (!e.getTextChannel().getId().equals(ConfigYML.CHANNELSOLICT)){
                return;
            }
            if (e.getReactionEmote().getName().equals("verde")) {
                e.getChannel().retrieveMessageById(e.getMessageId()).queue(message -> {
                    message.getEmbeds().stream().limit(1).forEach(m -> {
                        String[] msg = m.getDescription().replace("Um ajudante deseja punir um jogador do servidor, as informações " +
                                "abaixo são as usadas por ele, basta aceitar ou negar essa punição.", "").split(">");
                        try{
                            String pname, author, prova, motivo;
                            pname = msg[1].replace("Jogador ", "")
                                    .replace("`", "").replace(" ", "").replace("\n", "");
                            String url = "https://mc-heads.net/player/" + pname;
                            EmbedBuilder embed = new EmbedBuilder();
                            embed.setDescription(m.getDescription());
                            embed.setThumbnail(url);
                            embed.setFooter(m.getFooter().getText() + " | Punição autorizada por - " + e.getMember().getNickname());
                            embed.setColor(new Color(76, 206, 9));
                            e.getTextChannel().sendMessage(embed.build()).queue();
                            String[] at = m.getFooter().getText().split(" ");
                            author = at[2];
                            prova = msg[3].replace("\n", "").replace(")", "").split("https://")[1];
                            motivo = msg[2].replace("Motivo: ", "").replace("\n", "")
                                    .replace("'", "").replace("`", "");
                            if (!SayPunish.list.containsKey(Bukkit.getOfflinePlayer(author).getUniqueId())){
                                try {
                                    PunishType typePunish;
                                    if (msg[4].contains("Permanente"))
                                        typePunish = PunishType.BAN_PERMA;
                                    else
                                        typePunish = PunishType.BAN;
                                    BandMType type = new BandMType(Bukkit.getOfflinePlayer(pname).getUniqueId(), pname, typePunish);
                                    type.setAuthor(author);
                                    type.setProva(prova);
                                    type.setTempo(ConvertTime.getTimeLong(msg[4].replace("Duração: ", "")));
                                    type.setMotivo(motivo);

                                    if (type.getTipo() == PunishType.BAN)
                                        type.setTempo(ConvertTime.getTimeLong(msg[4].replace("Duração: ", "")));
                                    type.ban(false);
                                }catch (Exception er){
                                    er.printStackTrace();
                                }
                            }else{
                                BandMType type = SayPunish.list.get(Bukkit.getPlayer(author).getUniqueId());
                                type.ban(false);
                            }
                            return;
                        }catch (Exception v){
                            v.printStackTrace();
                        }
                        return;
                    });
                    message.delete().queue();
                });
            }
            if (e.getReactionEmote().getName().equals("vermelho")) {
                    e.getChannel().retrieveMessageById(e.getMessageId()).queue(message -> {
                        message.getEmbeds().forEach(m -> {
                            String[] msg = m.getDescription().replace("Um ajudante deseja punir um jogador do servidor, as informações " +
                                    "abaixo são as usadas por ele, basta aceitar ou negar essa punição.", "").split(" ");
                            try{
                                String url = "https://mc-heads.net/player/" + msg[4];
                                EmbedBuilder embed = new EmbedBuilder();
                                embed.setDescription(m.getDescription());
                                embed.setThumbnail(url);
                                embed.setFooter(m.getFooter().getText() + " | Punição negada por - " + e.getMember().getNickname());
                                embed.setColor(new Color(250, 0, 0));
                                e.getTextChannel().sendMessage(embed.build()).queue();
                            }catch (Exception v){
                                v.printStackTrace();
                            }
                        });
                        message.delete().queue();
                    });
            }
        }
    }
}
