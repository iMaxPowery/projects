package imax.net.bans.database.manager;

import imax.net.bans.Bans;
import imax.net.bans.database.DBIF;

import java.sql.ResultSet;

public class DataBase {
    private String uuid;
    private String nome;
    private String author;
    private String motivo;
    private String prova;
    private String tipo;
    private double tempo;
    private String server;

    protected DataBase(String uuid, String nome, String author, String motivo, String prova, String tipo, double tempo, String server) {
        this.uuid = uuid;
        this.nome = nome;
        this.prova = prova;
        this.motivo = motivo;
        this.author = author;
        this.tipo = tipo;
        this.tempo = tempo;
        this.server = server;
    }

    public void savePunishments() {
        Bans.getInstance();
        DBIF db = Bans.getInstance().database;
        try {
            if (db != null && db.hasConnection()) {
                ResultSet rs = db.pesquisa("SELECT * FROM `punishments` WHERE `uuid` = '"
                        + uuid + "' AND `tipo` = '" + tipo + "'");
                if (!rs.next() && uuid != null)
                    db.execute("INSERT INTO `punishments` (`uuid`, `nome`, `author`, `motivo`, `prova`, `tipo`, `tempo`, `server`) VALUES ('" + this.uuid
                            + "', '" + this.nome + "', '" + this.author + "', '" + this.motivo + "', '" + this.prova + "', '" + this.tipo + "', '" + this.tempo + "', " +
                            "'" + this.server + "')");
            }
            }catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String nome) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setProva(String prova) {
        this.prova = prova;
    }

    public String getProva() {
        return prova;
    }

    public String getAuthor() {
        return author;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
