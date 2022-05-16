package imax.net.bans.bungeecord;

public class ServerMng {
    String name, server;
    boolean atual;

    public ServerMng(String name, String server, boolean atual){
        this.name = name;
        this.server = server;
        this.atual = atual;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public boolean isAtual() {
        return atual;
    }

    public void setAtual(boolean atual) {
        this.atual = atual;
    }
}
