package saga.produto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe responsável pelos testes unitários da classe Produto.
 *
 * @author Pedro Manoel
 */
class ProdutoTest {

    private final String NOME_PRODUTO = "X-burguer";
    private final String DESCRICAO_PRODUTO = "Hamburguer de carne com queijo e calabresa";
    private final double PRECO_PRODUTO = 4.50;

    private Produto produto;

    @BeforeEach
    public void criaProduto () {
        this.produto = new Produto(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO);
    }

    @Test
    public void testCompararProdutoComOutroProdutoConsideradoMaior () {
        Produto produto1 = new Produto("Arroz", "arroz branco", 8.50);

        assertTrue(this.produto.compareTo(produto1) >= 1);
    }

    @Test
    public void testCompararProdutoComOutroProdutoConsideradoMenor () {
        Produto produto1 = new Produto("Yakissoba", "yakissoba", 7.50);

        assertTrue(this.produto.compareTo(produto1) < 1);
    }

    @Test
    public void testCompararProdutoComOutroProdutoConsideradoIgual () {
        Produto produto1 = new Produto(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO);

        assertEquals(this.produto.compareTo(produto1), 0);
    }

    @Test
    public void testToStringDeProduto () {
        String retornoEsperado = "X-burguer - Hamburguer de carne com queijo e calabresa - R$4,50";

        assertEquals(retornoEsperado, this.produto.toString());
    }

    @Test
    public void testEqualsProdutoComProdutoNull () {
        Produto produtoNull = null;

        assertNotEquals(this.produto, produtoNull);
    }

    @Test
    public void testEqualsProdutoComClasseDiferente () {
        String string = "produto 1";

        assertNotEquals(this.produto, string);
    }

    @Test
    public void testEqualsProdutosComNomeIgualEDescricaoDiferente () {
        Produto produto1 = new Produto(NOME_PRODUTO,"Feijao com arroz e queijo coalho", PRECO_PRODUTO);

        assertNotEquals(this.produto, produto1);
    }

    @Test
    public void testEqualsProdutosComNomeDiferenteEDescricaoIgual () {
        Produto produto1 = new Produto("Rubacao", DESCRICAO_PRODUTO, PRECO_PRODUTO);

        assertNotEquals(this.produto, produto1);
    }

    @Test
    public void testEqualsProdutosComNomeDiferenteEDescricaoDiferente () {
        Produto produto1 = new Produto("Rubacao", "Feijao com arroz e queijo coalho", PRECO_PRODUTO);

        assertNotEquals(this.produto, produto1);
    }

    @Test
    public void testEqualsProdutoesComNomeEDescricaoIguais () {
        Produto produto1 = new Produto(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO);

        assertEquals(this.produto, produto1);
    }

    @Test
    public void testHasCodeProdutoesComNomeEDescricaoIguais () {
        Produto produto1 = new Produto(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO);

        assertEquals(this.produto.hashCode(), produto1.hashCode());
    }
}
