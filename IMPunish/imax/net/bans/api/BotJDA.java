package imax.net.bans.api;


import imax.net.bans.Bans;
import imax.net.bans.utils.ConfigYML;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BotJDA extends ListenerAdapter {
    public Bans plugin;
    public static JDA jda;
    public BotJDA(Bans main) {
        this.plugin = main;
        startBot();
    }

    private void startBot() {
        try {
            JDABuilder builder = JDABuilder.createDefault(ConfigYML.TOKEN);
            builder.addEventListeners(new Object[]{this});
            builder.addEventListeners(new Object[]{new Event()});
            builder.setAutoReconnect(true);
            jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public void onReady(ReadyEvent e) {
        Bukkit.getConsoleSender().sendMessage("Bot iniciado com sucesso!");
    }

    public static Guild getGuild(long ID){
        return jda.getGuildById(ID);
    }

    public static GuildChannel getChannel(long gID, long ID){
        Guild g = jda.getGuildById(gID);
        return g.getGuildChannelById(ID);
    }

    public static void sendMessage(String jogador, String motivo, String prova, String duracao, String author){
        Date data = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Color c = new Color(200, 0, 0);
        String channel = ConfigYML.CHANNELBAN;
        String guild = ConfigYML.GUILD;

        TextChannel ch = (TextChannel) BotJDA.getChannel(Long.valueOf(guild), Long.valueOf(channel));
        String url = "https://mc-heads.net/player/" + jogador;
        prova = (prova.contains("https://") || prova.contains("http://") ? prova : "https://" + prova);
        try {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setAuthor(ConfigYML.BOT_TITLE, null, "https://cdn.discordapp.com/icons/750092697070469155/be149e1bdfc28db6d766fa9e5fb73462.png?size=2048");
            embed.setThumbnail(url);
            embed.setDescription("Esse é um sistema de notificações automáticas de punições, caso ache que essa punição tenha sido" +
                    " aplicada incorretamente, recomendamos entrar em contato com o nosso suporte. \n\n > O Jogador `" + jogador + "` foi banido do servidor. \n > Motivo: "
                    + motivo + " \n > Prova: [Clique aqui](" + prova + ") \n > Duração: " + duracao);
            embed.setFooter("Punição apliacada por " + author + " - " + dateFormat.format(data));
            embed.setColor(c);
            ch.sendMessage(embed.build()).queue(m -> {
            });
        } catch (Exception e){
            e.printStackTrace();
            new BotJDA(Bans.getInstance());
        }
    }

    public static void annallyz(String jogador, String motivo, String prova, String duracao, String author){
        Date data = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Color c = new Color(201, 104, 0);
        String channel = ConfigYML.CHANNELSOLICT;
        String guild = ConfigYML.GUILD;

        TextChannel ch = (TextChannel) BotJDA.getChannel(Long.valueOf(guild), Long.valueOf(channel));
        String url = "https://mc-heads.net/player/" + jogador;
        prova = (prova.contains("https://") || prova.contains("http://") ? prova : "https://" + prova);
        try {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setAuthor(ConfigYML.BOT_TITLE_SOLICT, null, "https://cdn.discordapp.com/icons/750092697070469155/be149e1bdfc28db6d766fa9e5fb73462.png?size=2048");
            embed.setThumbnail(url);
            embed.setDescription("Um ajudante deseja punir um jogador do servidor, as informações abaixo são as usadas por ele, " +
                    "basta aceitar ou negar essa punição. \n\n > Jogador `" + jogador + "` \n > Motivo: "
                    + motivo + " \n > Prova: [Clique aqui](" + prova + ") \n > Duração: " + duracao);
            embed.setFooter("Solicitação de " + author + " - " + dateFormat.format(data));
            embed.setColor(c);
            ch.sendMessage(embed.build()).queue(m -> {
                m.addReaction(m.getGuild().getEmoteById("816838497305755649")).queue();
                m.addReaction(m.getGuild().getEmoteById("816838452602601472")).queue();
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
