package imax.net.bans.manager;

import java.util.UUID;

public class User {

    UUID uuid;
    String name, banMot, muteMot, authorMute, authorBan;
    boolean isBanned, isMuted, BannedPerm, MutedPerm;
    long tempoBan, tempoMute;

    /*public User(String uuid, String name){
        this.uuid = UUID.fromString(uuid);
        this.name = name;
    }

    public User(UUID uuid, String name){
        this.uuid = uuid;
        this.name = name;
    }*/

    public boolean isMuted() {
        return isMuted;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public boolean isBannedPerm() {
        return BannedPerm;
    }

    public boolean isMutedPerm() {
        return MutedPerm;
    }

    public String getMuteMot() {
        return muteMot;
    }

    public String getBanMot() {
        return banMot;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public long getTempoBan() {
        return tempoBan;
    }

    public long getTempoMute() {
        return tempoMute;
    }

    public String getAuthorBan() {
        return authorBan;
    }

    public String getAuthorMute() {
        return authorMute;
    }

    public void setMuteMot(String muteMot) {
        this.muteMot = muteMot;
    }

    public void setMutedPerm(boolean mutedPerm) {
        MutedPerm = mutedPerm;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }

    public void setBannedPerm(boolean bannedPerm) {
        BannedPerm = bannedPerm;
    }

    public void setBanMot(String banMot) {
        this.banMot = banMot;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorBan(String authorBan) {
        this.authorBan = authorBan;
    }

    public void setAuthorMute(String authorMute) {
        this.authorMute = authorMute;
    }

    public void setTempoBan(long tempoBan) {
        this.tempoBan = tempoBan;
    }

    public void setTempoMute(long tempoMute) {
        this.tempoMute = tempoMute;
    }

}
