package imax.net.bans.bungeecord;

import imax.net.bans.Bans;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ServerList {

    static List<ServerMng> servers = new ArrayList<>();

    public ServerList(){
        FileConfiguration config = Bans.getInstance().getConfig();

        config.getConfigurationSection("Servidores.").getKeys(false).forEach(key -> {
            String name = config.getString("Servidores." + key + ".Nome");
            String server = config.getString("Servidores." + key + ".Server");
            boolean atual = config.getBoolean("Servidores." + key + ".Atual");
            ServerMng sv = new ServerMng(name, server, atual);
            servers.add(sv);
        });
    }

    public static void sendMessageList(Player p){
        servers.forEach(sv -> {
            TextComponent server = new TextComponent(" §f* §7" + sv.getName());
            ComponentBuilder builder = new ComponentBuilder("Clique para escrever");
            HoverEvent hover3 = new HoverEvent(HoverEvent.Action.SHOW_TEXT, builder.create());
            ClickEvent click3 = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, sv.getServer());
            server.setHoverEvent(hover3);
            server.setClickEvent(click3);

            p.spigot().sendMessage(server);
        });
    }
}
