package produto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Classe responsável pelos testes unitários da classe ProdutoSimples.
 *
 * @author Pedro Manoel
 */
class ProdutoSimplesTest {

    @Test
    public void testCriarProdutoSimples () {
        Produto produto = new ProdutoSimples("Café", "Café preto", 2.50);

        assertNotEquals(null, produto);
    }
}