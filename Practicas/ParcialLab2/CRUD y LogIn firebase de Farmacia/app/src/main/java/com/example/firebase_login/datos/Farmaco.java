package com.example.firebase_login.datos;

public class Farmaco {
    private String nombre;
    private Double precio;
    String key;

    public Farmaco(){}
    public Farmaco(String nombre, Double precio){
        this.nombre=nombre;
        this.precio=precio;
    }
    public String getNombre(){return  nombre; }
    public  void setNombre(String nombre){this.nombre=nombre;}
    public Double getPrecio(){return  precio; }
    public  void setPrecio(Double precio){this.precio=precio;}
    public String getKey(){return  key; }
    public  void setKey(String key){this.key=key;}
}

