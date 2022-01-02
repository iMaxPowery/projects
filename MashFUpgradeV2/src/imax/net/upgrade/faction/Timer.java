package imax.net.upgrade.faction;

import imax.net.upgrade.FUpgrade;
import imax.net.upgrade.blocks.BlocksManager;
import org.bukkit.Bukkit;

public class Timer {

    int breakedBlocks;
    double timeLeft, timeStart;
    String factag;

    public Timer(double timeLeft, double timeStart, String factag) {
        this.timeLeft = timeLeft;
        this.timeStart = timeStart;
        this.factag = factag;

        isFinal();
    }

    public void isFinal() {
        startMining();
        Bukkit.getScheduler().runTaskLater(FUpgrade.getInstance(), () -> finalMining(), 20 * 60 * 5);
    }

    public double getTime(){
        return timeLeft/1000 - System.currentTimeMillis()/1000;
    }

    private void startMining() {
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("           §d§n§lMina de Elixir");
        Bukkit.broadcastMessage(" §7A Mina está sendo dominada pela facção §f§n" + factag);
        Bukkit.broadcastMessage("");
    }

    public int getBreakedBlocks() {
        return breakedBlocks;
    }

    public void addBreakedBlocks() {
        this.breakedBlocks++;
    }

    private void finalMining() {
        new Methods().removeFac();
        BlocksManager.replaceAll();

        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("           §d§n§lMina de Elixir");
        Bukkit.broadcastMessage(" §7A Mina que estava sendo dominada pela facção §f§n" + factag + "§7 agora está livre.");
        Bukkit.broadcastMessage("");
    }
}
