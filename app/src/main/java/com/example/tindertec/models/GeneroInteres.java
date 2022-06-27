package com.example.tindertec.models;

public class GeneroInteres {

    private int cod_interes  ;
    private String des_interes ;

    @Override
    public String toString() {
        return "GeneroInteres{" +
                "cod_interes=" + cod_interes +
                ", des_interes='" + des_interes + '\'' +
                '}';
    }

    public GeneroInteres(int cod_interes, String des_interes) {
        this.cod_interes = cod_interes;
        this.des_interes = des_interes;
    }
    public GeneroInteres() {

    }
    public int getCod_interes() {
        return cod_interes;
    }

    public void setCod_interes(int cod_interes) {
        this.cod_interes = cod_interes;
    }

    public String getDes_interes() {
        return des_interes;
    }

    public void setDes_interes(String des_interes) {
        this.des_interes = des_interes;
    }
}
