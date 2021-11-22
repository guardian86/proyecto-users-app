package com.example.retouser.model;

import java.io.Serializable;

public class Persona implements Serializable {


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String uid;
    public Persona(){}

    public Persona(String name, String genero, String descripcion, String imagenURL, String uid) {
        this.name = name;
        this.genero = genero;
        this.descripcion = descripcion;
        this.imagenURL = imagenURL;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    String name;
    String genero;
    String descripcion;
    String imagenURL;

}
