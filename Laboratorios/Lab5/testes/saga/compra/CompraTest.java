package saga.compra;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe responsável pelos testes unitários da classe Compra.
 *
 * @author Pedro Manoel
 */
class CompraTest {

    @Test
    public void testToStringDeCompra () {
        Compra compra = new Compra("03/12/2013", "Coxao com batata", "Coxao de frango com batata frita", 5.00);

        assertEquals("Coxao com batata - 03-12-2013", compra.toString());
    }
}
