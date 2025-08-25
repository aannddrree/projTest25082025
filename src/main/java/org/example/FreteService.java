package org.example;

public class FreteService {

    // Constantes de regra
    private static final double BASE_POR_KM = 1.20;
    private static final double EXCESSO_PESO_LIMITE_KG = 5.0;
    private static final double EXCESSO_PESO_TAXA_POR_KG = 2.0;
    private static final double TAXA_FRAGIL = 15.0;
    private static final double MULTIPLICADOR_EXPRESSO = 1.5;
    private static final double MINIMO = 10.0;
    private static final double MAXIMO = 300.0;

    public double calcularFrete(Pedido p) {
        validar(p);

        // Regra 5: frete grátis (antes de qualquer cálculo) — apenas modalidade padrão
        if (!p.isExpresso() && p.getValorItens() >= 200.0 && p.getDistanciaKm() <= 20.0) {
            return 0.0;
        }

        double total = 0.0;

        // Regra 1: base por distância
        total += p.getDistanciaKm() * BASE_POR_KM;

        // Regra 2: excesso de peso
        if (p.getPesoKg() > EXCESSO_PESO_LIMITE_KG) {
            double excesso = p.getPesoKg() - EXCESSO_PESO_LIMITE_KG;
            total += excesso * EXCESSO_PESO_TAXA_POR_KG;
        }

        // Regra 3: item frágil
        if (p.isFragil()) {
            total += TAXA_FRAGIL;
        }

        // Regra 4: expresso
        if (p.isExpresso()) {
            total *= MULTIPLICADOR_EXPRESSO;
        }

        // Regra 6: piso/teto
        total = Math.max(MINIMO, Math.min(MAXIMO, total));

        // Arredondamento final
        return arredondar2c(total);
    }

    private void validar(Pedido p) {
        if (p.getDistanciaKm() < 0) throw new IllegalArgumentException("Distância não pode ser negativa");
        if (p.getPesoKg() < 0) throw new IllegalArgumentException("Peso não pode ser negativo");
    }

    private double arredondar2c(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    // Demo simples para aula: só System.out.println
    public static void main(String[] args) {
        FreteService svc = new FreteService();

        Pedido padrao = new Pedido(150.0, 30.0, 4.5, false, false);
        Pedido fragil = new Pedido(80.0, 10.0, 8.0, true, false);
        Pedido expresso = new Pedido(90.0, 5.0, 2.0, false, true);
        Pedido gratis = new Pedido(250.0, 15.0, 3.0, false, false);

        System.out.println("Frete padrão: R$ " + svc.calcularFrete(padrao));
        System.out.println("Frete frágil: R$ " + svc.calcularFrete(fragil));
        System.out.println("Frete expresso: R$ " + svc.calcularFrete(expresso));
        System.out.println("Frete grátis: R$ " + svc.calcularFrete(gratis));
    }
}