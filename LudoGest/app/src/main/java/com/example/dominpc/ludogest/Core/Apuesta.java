package com.example.dominpc.ludogest.Core;


public class Apuesta{
    private int id;
    private String evento;
    private String pronostico;
    private double importe;
    private double cuota;

    private int resultado;

    public Apuesta(int id, String evento, String pronostico, double importe, double cuota, int resultado){
        this.evento = evento;
        this.pronostico = pronostico;
        this.importe = importe;
        this.cuota = cuota;

        this.resultado = resultado;
    }

    public int getId(){return id;}

    public String getEvento(){return evento;}

    public String getPronostico(){return pronostico;}

    public double getImporte(){return importe;}

    public double getCuota(){return cuota;}



    public int getResultado(){return resultado;}

    public String toString(){
        return this.getEvento() + this.getPronostico() + this.getImporte() + this.getCuota()  + this.getResultado();
    }

}
