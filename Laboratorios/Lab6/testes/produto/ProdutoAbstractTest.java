package produto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe responsável pelos testes unitários da classe ProdutoAbstract.
 *
 * @author Pedro Manoel
 */
class ProdutoAbstractTest {

    private final String NOME_PRODUTO = "X-burguer";
    private final String DESCRICAO_PRODUTO = "Hamburguer de carne com queijo e calabresa";
    private final double PRECO_PRODUTO = 4.50;

    private ProdutoAbstract produto;

    @BeforeEach
    public void criaProdutoAbstract () {
        this.produto = new ProdutoAbstract(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO){};
    }

    @Test
    public void testCompararProdutoAbstractComOutroProdutoAbstractConsideradoMaior () {
        ProdutoAbstract produto1 = new ProdutoAbstract("Arroz", "arroz branco", 8.50){};

        assertTrue(this.produto.compareTo(produto1) >= 1);
    }

    @Test
    public void testCompararProdutoAbstractComOutroProdutoAbstractConsideradoMenor () {
        ProdutoAbstract produto1 = new ProdutoAbstract("Yakissoba", "yakissoba", 7.50){};

        assertTrue(this.produto.compareTo(produto1) < 1);
    }

    @Test
    public void testCompararProdutoAbstractComOutroProdutoAbstractConsideradoIgual () {
        ProdutoAbstract produto1 = new ProdutoAbstract(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO){};

        assertEquals(this.produto.compareTo(produto1), 0);
    }

    @Test
    public void testToStringDeProdutoAbstract () {
        String retornoEsperado = "X-burguer - Hamburguer de carne com queijo e calabresa - R$4,50";

        assertEquals(retornoEsperado, this.produto.toString());
    }

    @Test
    public void testEqualsProdutoAbstractComProdutoAbstractNull () {
        ProdutoAbstract produtoNull = null;

        assertNotEquals(this.produto, produtoNull);
    }

    @Test
    public void testEqualsProdutoAbstractComClasseDiferente () {
        String string = "produto 1";

        assertNotEquals(this.produto, string);
    }

    @Test
    public void testEqualsProdutosAbstractComNomeIgualComDescricaoDiferente () {
        ProdutoAbstract produto1 = new ProdutoAbstract(NOME_PRODUTO,"Feijao com arroz e queijo coalho", PRECO_PRODUTO){};

        assertNotEquals(this.produto, produto1);
    }

    @Test
    public void testEqualsProdutosAbstractComNomeDiferenteComDescricaoIgual () {
        ProdutoAbstract produto1 = new ProdutoAbstract("Rubacao", DESCRICAO_PRODUTO, PRECO_PRODUTO){};

        assertNotEquals(this.produto, produto1);
    }

    @Test
    public void testEqualsProdutosAbstractComNomeDiferenteComDescricaoDiferente () {
        ProdutoAbstract produto1 = new ProdutoAbstract("Rubacao", "Feijao com arroz e queijo coalho", PRECO_PRODUTO){};

        assertNotEquals(this.produto, produto1);
    }

    @Test
    public void testEqualsProdutosAbstracteComNomeIgualComDescricaoIgual () {
        ProdutoAbstract produto1 = new ProdutoAbstract(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO){};

        assertEquals(this.produto, produto1);
    }

    @Test
    public void testHasCodeProdutosAbstracteComNomeIgualComDescricaoIgual () {
        ProdutoAbstract produto1 = new ProdutoAbstract(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO){};

        assertEquals(this.produto.hashCode(), produto1.hashCode());
    }
}