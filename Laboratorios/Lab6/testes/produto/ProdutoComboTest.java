package produto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Classe responsável pelos testes unitários da classe ProdutoCombo.
 *
 * @author Pedro Manoel
 */
class ProdutoComboTest {

    @Test
    public void testCriarProdutoCombo () {
        Produto produto = new ProdutoCombo("Café + pão", "Café preto com pão com manteiga", 5.50, 0.25);

        assertNotEquals(null, produto);
    }

    @Test
    public void testGetPrecoDeUmProdutoCombo () {
        Produto produtoCombo = new ProdutoCombo("Café + pão", "Café preto com pão com manteiga", 5.50, 0.25);

        assertEquals(4.125, produtoCombo.getPreco());
    }
}