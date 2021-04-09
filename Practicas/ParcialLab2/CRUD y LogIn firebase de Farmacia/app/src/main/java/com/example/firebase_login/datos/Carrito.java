package com.example.firebase_login.datos;

public class Carrito {
    private String nombre;
    private Double precio;
    private String idcliente;
    String key;

    public Carrito(){}
    public Carrito(String idcliente,String nombre, Double precio){
        this.nombre=nombre;
        this.precio=precio;
        this.idcliente=idcliente;
    }
    public String getNombre(){return  nombre; }
    public  void setNombre(String nombre){this.nombre=nombre;}
    public Double getPrecio(){return  precio; }
    public  void setPrecio(Double precio){this.precio=precio;}
    public String getIdcliente(){return  idcliente; }
    public  void setIdcliente(String idcliente){this.idcliente=idcliente;}
    public String getKey(){return  key; }
    public  void setKey(String key){this.key=key;}

}
