package com.wbike.ivan.fastbiking.com.wbike.ivan.fastbiking.model;

import java.util.Date;

/**
 * Created by Ivan on 21/06/2015.
 * Modelo de rutas de la aplicación
 */
public class Ruta {
    private int duracion;
    private int velocidadMedia;
    private int distancia;
    private Date fecha;
    private String descripcion;
    private String nombre;


///---------Getters and Setters-----------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getVelocidadMedia() {
        return velocidadMedia;
    }

    public void setVelocidadMedia(int velocidadMedia) {
        this.velocidadMedia = velocidadMedia;
    }

///----------------------------------------
}
