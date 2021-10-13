package saga.fornecedor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saga.produto.Produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe responsável pelos testes unitários da classe Fornecedor.
 *
 * @author Pedro Manoel
 */
class FornecedorTest {

    private final String NOME_FORNECEDOR = "Marcos";
    private final String EMAIL_FORNECEDOR = "marcos@gmail.com";
    private final String TELEFONE_FORNECEDOR = "83 99151-3570";

    private final String NOME_PRODUTO = "X-burguer";
    private final String DESCRICAO_PRODUTO = "Hamburguer de carne com queijo e calabresa";
    private final double PRECO_PRODUTO = 4.50;

    private Fornecedor fornecedor;

    @BeforeEach
    public void criaFornecedor () {
        this.fornecedor = new Fornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
    }

    @Test
    public void testAdicionarUmProduto () {
        Produto produto = new Produto(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO);
        this.fornecedor.adicionaProduto(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO);

        assertEquals(produto.toString(), this.fornecedor.retornaProduto(NOME_PRODUTO, DESCRICAO_PRODUTO));
    }

    @Test
    public void testAdicionarUmMesmoProdutoDuasVezes () {
        this.fornecedor.adicionaProduto(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO);

        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.adicionaProduto(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO);
        });
    }

    @Test
    public void testRetornarPrecoDeUmProdutoComProdutoCadastrado () {
        this.fornecedor.adicionaProduto(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO);

        assertEquals(PRECO_PRODUTO, this.fornecedor.getPrecoProduto(NOME_PRODUTO, DESCRICAO_PRODUTO));
    }

    @Test
    public void testRetornarPrecoDeUmProdutoComProdutoNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.getPrecoProduto(NOME_PRODUTO, DESCRICAO_PRODUTO);
        });
    }

    @Test
    public void testRetornarUmProdutoComProdutoCadastrado () {
        Produto produto = new Produto(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO);
        this.fornecedor.adicionaProduto(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO);

        assertEquals(produto.toString(), this.fornecedor.retornaProduto(NOME_PRODUTO, DESCRICAO_PRODUTO));
    }

    @Test
    public void testRetornarUmProdutoComProdutoNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.retornaProduto(NOME_PRODUTO, DESCRICAO_PRODUTO);
        });
    }

    @Test
    public void testRetornarOsProdutosComProdutosNaoCadastrados () {
        assertEquals("Marcos -", this.fornecedor.retornaProdutos());
    }

    @Test
    public void testRetornaOsProdutosComProdutosCadastrados () {
        this.fornecedor.adicionaProduto("Cafe","cafe preto",2.50);
        this.fornecedor.adicionaProduto("Arroz","arroz branco",12.40);

        String retorneEsperado = "Marcos - Arroz - arroz branco - R$12,40 | Marcos - Cafe - cafe preto - R$2,50";

        assertEquals(retorneEsperado, this.fornecedor.retornaProdutos());
    }

    @Test
    public void testEditarPrecoDeUmProdutoComProdutoNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.editaPrecoProduto(NOME_PRODUTO, DESCRICAO_PRODUTO, 8.50);
        });
    }

    @Test
    public void testEditarPrecoDeUmProdutoComProdutoCadastrado () {
        this.fornecedor.adicionaProduto(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO);
        this.fornecedor.editaPrecoProduto(NOME_PRODUTO, DESCRICAO_PRODUTO, 8.50);

        assertEquals(8.50, this.fornecedor.getPrecoProduto(NOME_PRODUTO, DESCRICAO_PRODUTO));
    }

    @Test
    public void testRemoverUmProdutoComProdutoNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.removeProduto(NOME_PRODUTO, DESCRICAO_PRODUTO);
        });
    }

    @Test
    public void testRemoverUmProdutoComProdutoCadastrado () {
        this.fornecedor.adicionaProduto(NOME_PRODUTO, DESCRICAO_PRODUTO, PRECO_PRODUTO);
        this.fornecedor.removeProduto(NOME_PRODUTO, DESCRICAO_PRODUTO);

        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.retornaProduto(NOME_PRODUTO, DESCRICAO_PRODUTO);
        });
    }

    @Test
    public void testRetornarAsComprasDeUmClienteComClienteSemCompras () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.retornaComprasCliente("11111111111");
        });
    }

    @Test
    public void testRetornarAsComprasDeUmClienteComClienteComCompras () {
        this.fornecedor.adicionaProduto("Cafe","cafe preto",2.50);
        this.fornecedor.adicionaProduto("Arroz","arroz branco",12.40);

        this.fornecedor.adicionaCompra("11111111111", "12/03/2016", "Cafe","cafe preto",2.50);
        this.fornecedor.adicionaCompra("11111111111", "17/03/2016", "Arroz","arroz branco",12.40);

        assertTrue(this.fornecedor.retornaComprasCliente("11111111111").size() >= 1);
    }

    @Test
    public void testRetornarAsComprasDeUmClientePorDataComClienteSemCompras () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.retornaComprasClientePorData("11111111111","12/03/2016");
        });
    }

    @Test
    public void testRetornarAsComprasDeUmClientePorDataComClienteComCompras () {
        this.fornecedor.adicionaProduto("Cafe","cafe preto",2.50);
        this.fornecedor.adicionaProduto("Arroz","arroz branco",12.40);

        this.fornecedor.adicionaCompra("11111111111", "12/03/2016", "Cafe","cafe preto",2.50);
        this.fornecedor.adicionaCompra("11111111111", "17/03/2016", "Arroz","arroz branco",12.40);

        assertTrue(this.fornecedor.retornaComprasClientePorData("11111111111", "12-03-2016").size() >= 1);
    }

    @Test
    public void testGetDebitoDeUmaContaDeClienteComClienteSemConta () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.getDebitoConta("00023827490");
        });
    }

    @Test
    public void testGetDebitoDeUmaContaDeClienteComClienterComConta () {
        this.fornecedor.adicionaCompra("00023827490", "11/02/1995", "arroz", "arroz branco", 7.50);

        assertEquals(7.50, this.fornecedor.getDebitoConta("00023827490"));
    }

    @Test
    public void testRetornaUmaContaDeClienteComClienteComConta () {
        this.fornecedor.adicionaCompra("00023827490", "11/02/1995", "arroz", "arroz branco", 7.50);
        this.fornecedor.adicionaCompra("00023827490", "11/12/1995", "limão", "limão verda", 2.50);

        String retornoEsperado = "Marcos | arroz - 11-02-1995 | limão - 11-12-1995";

        assertEquals(retornoEsperado, this.fornecedor.retornaConta("00023827490"));
    }

    @Test
    public void testRetornaUmaContaDeClienteComClienteSemConta () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.retornaConta("00023827490");
        });
    }

    @Test
    public void testRetornarAsDatasDasComprasDosClientesComClientesComCompras () {
        this.fornecedor.adicionaProduto("Cafe","cafe preto",2.50);
        this.fornecedor.adicionaProduto("Arroz","arroz branco",12.40);

        this.fornecedor.adicionaCompra("11111111111", "12/03/2016", "Cafe","cafe preto",2.50);
        this.fornecedor.adicionaCompra("11111111111", "10/03/2016", "Arroz","arroz branco",12.40);
        this.fornecedor.adicionaCompra("22222222222", "17/03/2016", "Arroz","arroz branco",12.40);

        String retornoEsperado = "[12-03-2016, 10-03-2016, 17-03-2016]";

        assertEquals(retornoEsperado, this.fornecedor.retornaDatasComprasDeClientes().toString());
    }

    @Test
    public void testRetornarAsDatasDasComprasDosClientesComClientesSemCompras () {
        this.fornecedor.adicionaProduto("Cafe","cafe preto",2.50);
        this.fornecedor.adicionaProduto("Arroz","arroz branco",12.40);

        String retornoEsperado = "[]";

        assertEquals(retornoEsperado, this.fornecedor.retornaDatasComprasDeClientes().toString());
    }

    @Test
    public void testCompararFornecedorComOutroFornecedorConsideradoMaior () {
        Fornecedor fornecedor1 = new Fornecedor("Arthur", EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);

        assertTrue(this.fornecedor.compareTo(fornecedor1) >= 1);
    }

    @Test
    public void testCompararFornecedorComOutroFornecedorConsideradoMenor () {
        Fornecedor fornecedor1 = new Fornecedor("Zeca", EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);

        assertTrue(this.fornecedor.compareTo(fornecedor1) < 1);
    }

    @Test
    public void testCompararFornecedorComOutroFornecedorConsideradoIgual () {
        Fornecedor fornecedor1 = new Fornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);

        assertEquals(this.fornecedor.compareTo(fornecedor1), 0);
    }

    @Test
    public void testToStringDeFornecedor () {
        String toStringEsperado = "Marcos - marcos@gmail.com - 83 99151-3570";

        assertEquals(toStringEsperado, this.fornecedor.toString());
    }

    @Test
    public void testEqualsFornecedorComFornecedorNull () {
        Fornecedor fornecedorNull = null;

        assertNotEquals(this.fornecedor, fornecedorNull);
    }

    @Test
    public void testEqualsFornecedorComClasseDiferente () {
        String string = "fornecedor 1";

        assertNotEquals(this.fornecedor, string);
    }

    @Test
    public void testEqualsFornecedoresComNomeDiferente () {
        Fornecedor fornecedor1 = new Fornecedor("Carlos", EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);

        assertNotEquals(this.fornecedor, fornecedor1);
    }

    @Test
    public void testEqualsFornecedoresComNomeIgual () {
        Fornecedor fornecedor1 = new Fornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);

        assertEquals(this.fornecedor, fornecedor1);
    }

    @Test
    public void testHasCodeFornecedoresComNomeIgual () {
        Fornecedor fornecedor1 = new Fornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);

        assertEquals(this.fornecedor.hashCode(), fornecedor1.hashCode());
    }
}