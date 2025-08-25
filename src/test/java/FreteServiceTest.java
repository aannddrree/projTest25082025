import org.example.FreteService;
import org.example.Pedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FreteServiceTest {

    private final FreteService svc = new FreteService();
    private static final double DELTA = 0.0001; // tolerância p/ double

    @Test
    void deveCalcularBasePorDistanciaSemExtras() {
        Pedido p = new Pedido(50.0, 10.0, 3.0, false, false);
        // 10 km * 1.20 = 12.00 -> mínimo é 10, então fica 12.00
        assertEquals(12.00, svc.calcularFrete(p), DELTA);
    }

    @Test
    void deveAplicarExcessoDePesoApenasAcimaDoLimite() {
        Pedido pNoLimite = new Pedido(50.0, 10.0, 5.0, false, false);
        Pedido pAcima = new Pedido(50.0, 10.0, 5.5, false, false);
        // No limite: 10*1.2=12.00
        assertEquals(12.00, svc.calcularFrete(pNoLimite), DELTA);
        // Acima: base 12.00 + (0.5kg*2)=1.00 => 13.00
        assertEquals(13.00, svc.calcularFrete(pAcima), DELTA);
    }

    @Test
    void deveSomarTaxaFragil() {
        Pedido p = new Pedido(80.0, 10.0, 6.0, true, false);
        // base 12.00 + excesso (1kg*2)=2.00 + frágil 15.00 => 29.00
        assertEquals(29.00, svc.calcularFrete(p), DELTA);
    }

    @Test
    void deveMultiplicarPorExpresso() {
        Pedido p = new Pedido(90.0, 5.0, 2.0, false, true);
        // base 6.00; expresso *1.5 => 9.00 (>=10? mínimo é 10.00)
        assertEquals(10.00, svc.calcularFrete(p), DELTA, "Aplica piso após multiplicar");
    }

    @Test
    void freteGratisSomenteEmPadraoComValorMinimoEDistancia() {
        Pedido gratis = new Pedido(220.0, 20.0, 2.0, false, false);
        assertEquals(0.0, svc.calcularFrete(gratis), DELTA);

        Pedido expressoNaoGratis = new Pedido(300.0, 10.0, 2.0, false, true);
        assertNotEquals(0.0, svc.calcularFrete(expressoNaoGratis), "Expresso não é grátis");
    }

    @Test
    void deveAplicarTetoMaximo() {
        Pedido p = new Pedido(50.0, 1000.0, 20.0, true, true);
        // Valor ficaria enorme; garantir que limita a 300
        assertEquals(300.00, svc.calcularFrete(p), DELTA);
    }

    @Test
    void deveRejeitarParametrosInvalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> svc.calcularFrete(new Pedido(50.0, -1.0, 2.0, false, false)));
        assertThrows(IllegalArgumentException.class,
                () -> svc.calcularFrete(new Pedido(50.0, 5.0, -2.0, false, false)));
    }
}
