package imax.net.bans.manager;

import imax.net.bans.utils.PunishType;
import imax.net.bans.Bans;
import imax.net.bans.database.manager.DBManagement;
import imax.net.bans.utils.ConfigYML;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BandMType {

    UUID uuid;
    String motivo, nome, prova, author, server;
    long tempo;
    int last;
    PunishType tipo;

    public BandMType(UUID uuid, String nome, PunishType tipo){
        this.uuid = uuid;
        this.nome = nome;
        this.tipo = tipo;
    }

    public void ban(boolean supress){
        if (DBManagement.getAPI().isPunished(uuid, tipo)){
            Bukkit.getPlayer(author).sendMessage("§cEsse jogador já está punido.");
            return;
        }
        long v = System.currentTimeMillis() / 1000;
        prova = prova.replace(" ", "");

        DBManagement.getAPI().createPunish(uuid.toString(), nome, author, motivo,
                prova, tipo.toString(), tempo + v, server);

        kickPlayerBan();

        if (tipo == PunishType.BAN_PERMA){
            if (!supress)
            ShowMessages.showBanPerm(nome, motivo, author, prova);
            return;
        }else{
            if (!supress)
            ShowMessages.showBan(nome, motivo, author, tempo, prova);
        }
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getNome() {
        return nome;
    }

    public double getTempo() {
        return tempo;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getProva() {
        return prova.replace(" ", "");
    }

    public String getAuthor() {
        return author;
    }

    public void mute(boolean supress){
        if (DBManagement.getAPI().isPunished(uuid, tipo)){
            Bukkit.getPlayer(author).sendMessage("§cEsse jogador já está punido.");
            return;
        }
        long v = System.currentTimeMillis() / 1000;
        prova = prova.replace(" ", "");

        DBManagement.getAPI().createPunish(uuid.toString(), nome, author, motivo,
                prova, tipo.toString(), tempo + v, server);

        if (tipo == PunishType.MUTE_PERMA){
            if (!supress)
                ShowMessages.showMutePerm(nome, motivo, author, prova);
            return;
        }else{
            if (!supress)
                ShowMessages.showMute(nome, motivo, author, tempo, prova);
        }
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public PunishType getTipo() {
        return tipo;
    }

    public int getLast() {
        return last;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setTempo(long tempo) {
        this.tempo = tempo;
    }

    public void setProva(String prova) {
        this.prova = prova;
    }

    public void setTipo(PunishType tipo) {
        this.tipo = tipo;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public void kickPlayerBan(){

        Bukkit.getScheduler().runTask(Bans.getInstance(), new Runnable() {
            public void run() {
                String msg = (tipo != PunishType.BAN_PERMA ? ConfigYML.getScreenBan(nome,
                        motivo, author, prova, getTempo()) :
                        ConfigYML.getScreenBanPerma(nome,
                                motivo, author, prova));

                if (Bukkit.getPlayer(uuid) != null) {
                    Player p = Bukkit.getPlayer(uuid);
                    p.kickPlayer(msg);
                }
            }
        });
    }

    public void kickPlayer(){
        Bukkit.getScheduler().runTask(Bans.getInstance(), new Runnable() {
            public void run() {
                String msg = (ConfigYML.getScreenKick(nome, author, motivo));

                if (Bukkit.getPlayer(uuid) != null) {
                    Player p = Bukkit.getPlayer(uuid);
                    p.kickPlayer(msg);
                }
            }
        });
    }
}
