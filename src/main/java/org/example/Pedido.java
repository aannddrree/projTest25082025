package org.example;

public class Pedido {
    private final double valorItens;
    private final double distanciaKm;
    private final double pesoKg;
    private final boolean fragil;
    private final boolean expresso;

    public Pedido(double valorItens, double distanciaKm, double pesoKg, boolean fragil, boolean expresso) {
        this.valorItens = valorItens;
        this.distanciaKm = distanciaKm;
        this.pesoKg = pesoKg;
        this.fragil = fragil;
        this.expresso = expresso;
    }

    public double getValorItens() { return valorItens; }
    public double getDistanciaKm() { return distanciaKm; }
    public double getPesoKg() { return pesoKg; }
    public boolean isFragil() { return fragil; }
    public boolean isExpresso() { return expresso; }
}