package imax.net.upgrade.database.manager;

import imax.net.upgrade.FUpgrade;
import imax.net.upgrade.database.DataBaseIF;

import java.sql.ResultSet;

public class DataBase {
    private String nome;
    private int coins;
    private int claimL;
    private int dclaimT;
    private int dAtaque;
    private int bProtecao;
    private int tRegn;
    private int lMembros;
    private int pPoder;
    private int tRegen;
    private int dSpawner;
    private int lValor;
    private int aLucro;

    protected DataBase(String nome, int coins, int claimL, int dclaimT, int dAtaque, int bProtecao, int tRegn, int lMembros,
                       int pPoder, int tRegen, int dSpawner, int lValor, int aLucro) {
        this.nome = nome;
        this.coins = coins;

        this.setClaimL(claimL);
        this.setDclaimT(dclaimT);
        this.setdAtaque(dAtaque);
        this.setbProtecao(bProtecao);
        this.settRegn(tRegn);
        this.setlMembros(lMembros);
        this.setpPoder(pPoder);
        this.settRegen(tRegen);
        this.setdSpawner(dSpawner);
        this.setlValor(lValor);
        this.setaLucro(aLucro);

    }

    public void salvar() {
        FUpgrade.getInstance();
        DataBaseIF db = FUpgrade.getInstance().database;

        try {
            if (db != null && db.hasConnection()) {
                ResultSet rs = db.pesquisa("SELECT * FROM `fupgrades` WHERE `fac` = '" + this.nome + "'");
                if (rs.next()) {
                    db.execute("UPDATE `fupgrades` set `coins` = '" + this.coins + "' where `fac` = '" + this.nome + "'");
                    db.execute("UPDATE `fupgrades` set `claimL` = '" + this.claimL + "' where `fac` = '" + this.nome + "'");
                    db.execute("UPDATE `fupgrades` set `dclaimT` = '" + this.dclaimT + "' where `fac` = '" + this.nome + "'");
                    db.execute("UPDATE `fupgrades` set `dAtaque` = '" + this.dAtaque + "' where `fac` = '" + this.nome + "'");
                    db.execute("UPDATE `fupgrades` set `bProtecao` = '" + this.bProtecao + "' where `fac` = '" + this.nome + "'");
                    db.execute("UPDATE `fupgrades` set `tRegn` = '" + this.tRegn + "' where `fac` = '" + this.nome + "'");
                    db.execute("UPDATE `fupgrades` set `lMembros` = '" + this.lMembros + "' where `fac` = '" + this.nome + "'");
                    db.execute("UPDATE `fupgrades` set `pPoder` = '" + this.pPoder + "' where `fac` = '" + this.nome + "'");
                    db.execute("UPDATE `fupgrades` set `tRegen` = '" + this.tRegen + "' where `fac` = '" + this.nome + "'");
                    db.execute("UPDATE `fupgrades` set `dSpawner` = '" + this.dSpawner + "' where `fac` = '" + this.nome + "'");
                    db.execute("UPDATE `fupgrades` set `lValor` = '" + this.lValor + "' where `fac` = '" + this.nome + "'");
                    db.execute("UPDATE `fupgrades` set `aLucro` = '" + this.aLucro + "' where `fac` = '" + this.nome + "'");

                } else {
                    db.execute("INSERT INTO `fupgrades` (`fac`, `coins`, `claimL`, `dclaimT`, `dAtaque`, "
                            + "`bProtecao`, `tRegn`, `lMembros`, `pPoder`, `tRegen`, `dSpawner`, `lValor`, `aLucro`) VALUES ('" + this.nome
                            + "', '" + this.coins + "', '" + this.claimL + "', '" + this.dclaimT + "', '" + this.dAtaque + "', '" + this.bProtecao + "', "
                            + "'" + this.tRegn + "', '" + this.lMembros + "', '" + this.pPoder + "', '" + this.tRegen + "', "
                            + "'" + this.dSpawner + "', '" + this.lValor + "', '" + this.aLucro + "')");
                }
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getClaimL() {
        return claimL;
    }

    public void setClaimL(int claimL) {
        this.claimL = claimL;
    }

    public int getDclaimT() {
        return dclaimT;
    }

    public void setDclaimT(int dclaimT) {
        this.dclaimT = dclaimT;
    }

    public int getdAtaque() {
        return dAtaque;
    }

    public void setdAtaque(int dAtaque) {
        this.dAtaque = dAtaque;
    }

    public int getbProtecao() {
        return bProtecao;
    }

    public void setbProtecao(int bProtecao) {
        this.bProtecao = bProtecao;
    }

    public int gettRegn() {
        return tRegn;
    }

    public void settRegn(int tRegn) {
        this.tRegn = tRegn;
    }

    public int getlMembros() {
        return lMembros;
    }

    public void setlMembros(int lMembros) {
        this.lMembros = lMembros;
    }

    public int getpPoder() {
        return pPoder;
    }

    public void setpPoder(int pPoder) {
        this.pPoder = pPoder;
    }

    public int gettRegen() {
        return tRegen;
    }

    public void settRegen(int tRegen) {
        this.tRegen = tRegen;
    }

    public int getdSpawner() {
        return dSpawner;
    }

    public void setdSpawner(int dSpawner) {
        this.dSpawner = dSpawner;
    }

    public int getlValor() {
        return lValor;
    }

    public void setlValor(int lValor) {
        this.lValor = lValor;
    }

    public int getaLucro() {
        return aLucro;
    }

    public void setaLucro(int aLucro) {
        this.aLucro = aLucro;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

}
