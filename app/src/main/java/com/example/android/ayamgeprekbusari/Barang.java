package com.example.android.ayamgeprekbusari;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Barang implements Serializable {
    private int total;
    private int ayamgeprek;
    private int pecaklele;
    private int pecakikanmas;
    private int nasi;
    private int esteh;
    private int esjeruk;
    private int gado2;
    private String namaUser;
    private String userId;
    private String nohp;
    private String catatan;

    public Barang(String namaUser, String uId, int total, int ayamgeprek, int pecaklele, int pecakikanmas, int nasi, int esteh, int esjeruk, int gado2, String nohp, String catatan) {
        this.namaUser = namaUser;
        this.userId = uId;
        this.total = total;
        this.ayamgeprek = ayamgeprek;
        this.pecaklele = pecaklele;
        this.pecakikanmas = pecakikanmas;
        this.nasi = nasi;
        this.esteh = esteh;
        this.esjeruk = esjeruk;
        this.gado2 = gado2;
        this.nohp = nohp;
        this.catatan = catatan;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public String getUserId() {
        return userId;
    }

    public int getTotal() {
        return total;
    }

    public int getAyamgeprek() {
        return ayamgeprek;
    }

    public int getPecaklele() {
        return pecaklele;
    }

    public int getPecakikanmas() {
        return pecakikanmas;
    }

    public int getNasi() {
        return nasi;
    }

    public int getEsteh() {
        return esteh;
    }

    public int getEsjeruk() {
        return esjeruk;
    }

    public int getGado2() {
        return gado2;
    }

    public String getNohp() {
        return nohp;
    }

    public String getCatatan() {
        return catatan;
    }
}





