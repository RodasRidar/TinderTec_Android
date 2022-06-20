package com.example.tindertec.model;



public class ItemModel {
    private int image;
    private String nombre,edad,localidad;

    public ItemModel() {}

    public ItemModel(int image, String nombre, String edad, String localidad) {
        this.image = image;
        this.nombre = nombre;
        this.edad = edad;
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "image=" + image +
                ", nombre='" + nombre + '\'' +
                ", edad='" + edad + '\'' +
                ", localidad='" + localidad + '\'' +
                '}';
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
