package imax.net.bans;

import net.md_5.bungee.api.plugin.Plugin;

public class MainBungee extends Plugin {

    public void onEnable() {
        System.out.println(this.getProxy().getName());
    }

    public void onDisable() {
    }
}
