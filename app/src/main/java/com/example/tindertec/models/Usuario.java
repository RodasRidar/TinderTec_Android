package com.example.tindertec.models;

public class Usuario {

        private int cod_usu  ;
        private String nombres ;
        private String email ;
        private String clave ;
        private String foto1 ;
        private String foto2 ;
        private String foto3 ;
        private String foto4 ;
        private String foto5 ;
        private String fecha_naci ;
        private String descripcion ;
        private int cod_carrera ;
        private String nom_carrera ;
        private int cod_sede ;
        private String nom_sede ;

    public Usuario(String nombres, String foto1, String nom_carrera) {
        this.nombres = nombres;
        this.foto1 = foto1;
        this.nom_carrera = nom_carrera;
    }

    public String getNom_carrera() {
        return nom_carrera;
    }

    public void setNom_carrera(String nom_carrera) {
        this.nom_carrera = nom_carrera;
    }

    public String getNom_sede() {
        return nom_sede;
    }

    public void setNom_sede(String nom_sede) {
        this.nom_sede = nom_sede;
    }

    public String getNom_interes() {
        return nom_interes;
    }

    public void setNom_interes(String nom_interes) {
        this.nom_interes = nom_interes;
    }

    private int cod_interes ;
        private String nom_interes ;
        private int cod_genero ;
        private int cod_suscrip ;

    public Usuario(int cod_usu, String nombres, String email, String clave, String foto1, String foto2, String foto3, String foto4, String foto5, String fecha_naci, String descripcion, int cod_carrera, int cod_sede, int cod_interes, int cod_genero, int cod_suscrip) {
        this.cod_usu = cod_usu;
        this.nombres = nombres;
        this.email = email;
        this.clave = clave;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
        this.foto4 = foto4;
        this.foto5 = foto5;
        this.fecha_naci = fecha_naci;
        this.descripcion = descripcion;
        this.cod_carrera = cod_carrera;
        this.cod_sede = cod_sede;
        this.cod_interes = cod_interes;
        this.cod_genero = cod_genero;
        this.cod_suscrip = cod_suscrip;
    }

    public Usuario() {
    }

    public int getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(int cod_usu) {
        this.cod_usu = cod_usu;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    public String getFoto4() {
        return foto4;
    }

    public void setFoto4(String foto4) {
        this.foto4 = foto4;
    }

    public String getFoto5() {
        return foto5;
    }

    public void setFoto5(String foto5) {
        this.foto5 = foto5;
    }

    public String getFecha_naci() {
        return fecha_naci;
    }

    public void setFecha_naci(String fecha_naci) {
        this.fecha_naci = fecha_naci;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCod_carrera() {
        return cod_carrera;
    }

    public void setCod_carrera(int cod_carrera) {
        this.cod_carrera = cod_carrera;
    }

    public int getCod_sede() {
        return cod_sede;
    }

    public void setCod_sede(int cod_sede) {
        this.cod_sede = cod_sede;
    }

    public int getCod_interes() {
        return cod_interes;
    }

    public void setCod_interes(int cod_interes) {
        this.cod_interes = cod_interes;
    }

    public int getCod_genero() {
        return cod_genero;
    }

    public void setCod_genero(int cod_genero) {
        this.cod_genero = cod_genero;
    }

    public int getCod_suscrip() {
        return cod_suscrip;
    }

    public void setCod_suscrip(int cod_suscrip) {
        this.cod_suscrip = cod_suscrip;
    }
}

