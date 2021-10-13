package saga.fornecedor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe responsável pelos testes unitários da classe FornecedorController.
 *
 * @author Pedro Manoel
 */
class FornecedorControllerTest {

    private final String NOME_FORNECEDOR = "Marcos";
    private final String EMAIL_FORNECEDOR = "marcos@gmail.com";
    private final String TELEFONE_FORNECEDOR = "83 99151-3570";

    private FornecedorController fornecedorController;

    @BeforeEach
    public void criaControllerDeFornecedores () {
        this.fornecedorController = new FornecedorController();
    }

    @Test
    public void testCadastrarUmFornecedor () {
        Fornecedor fornecedor = new Fornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);

        assertEquals(fornecedor.toString(), this.fornecedorController.retornaFornecedor(NOME_FORNECEDOR));
    }

    @Test
    public void testRetornoDoCadastroDeUmFornecedor () {
        String retornoDoCadastro = this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);

        assertEquals(retornoDoCadastro, NOME_FORNECEDOR);
    }

    @Test
    public void testCadastrarUmMesmoFornecedorDuasVezes () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);

        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        });
    }

    @Test
    public void testRetornarUmFornecedorComFornecedorCadastrado () {
        Fornecedor fornecedor = new Fornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);

        assertEquals(fornecedor.toString(), this.fornecedorController.retornaFornecedor(NOME_FORNECEDOR));
    }

    @Test
    public void testRetornarUmFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.retornaFornecedor(NOME_FORNECEDOR);
        });
    }

    @Test
    public void testRetornarFornecedoresComFornecedoresCadastrados () {
        this.fornecedorController.cadastraFornecedor("Dona Inês","dines@gmail.com", "83 9999-5050");
        this.fornecedorController.cadastraFornecedor("Josenilda","nilda@computacao.ufcg.edu.br","83 98736-5050");

        String retornoEsperado = "Dona Inês - dines@gmail.com - 83 9999-5050 | Josenilda - nilda@computacao.ufcg.edu.br - 83 98736-5050";

        assertEquals(retornoEsperado, this.fornecedorController.retornaFornecedores());
    }

    @Test
    public void testRetornarFornecedoresComFornecedoresNaoCadastrados () {
        assertEquals("", this.fornecedorController.retornaFornecedores());
    }

    @Test
    public void testEditarUmFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.editaFornecedor(NOME_FORNECEDOR,"telefone", "83 95807-7570");
        });
    }

    @Test
    public void testEditarNomeDeUmFornecedor () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);

        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.editaFornecedor(NOME_FORNECEDOR,"nome", "paulo");
        });
    }

    @Test
    public void testEditarEmailDeUmFornecedor () {
        Fornecedor fornecedor = new Fornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        fornecedor.setEmail("marcos@ufcg.edu.com");

        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        this.fornecedorController.editaFornecedor(NOME_FORNECEDOR,"email", "marcos@ufcg.edu.com");

        assertEquals(fornecedor.toString(), this.fornecedorController.retornaFornecedor(NOME_FORNECEDOR));
    }

    @Test
    public void testEditarTelefoneDeUmFornecedor () {
        Fornecedor fornecedor = new Fornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        fornecedor.setTelefone ("83 95807-7570");

        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        this.fornecedorController.editaFornecedor(NOME_FORNECEDOR,"telefone", "83 95807-7570");

        assertEquals(fornecedor.toString(), this.fornecedorController.retornaFornecedor(NOME_FORNECEDOR));
    }

    @Test
    public void testEditarUmAtributoQueNaoExisteEmFornecedor () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);

        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.editaFornecedor(NOME_FORNECEDOR,"idade", "18");
        });
    }

    @Test
    public void testRemoverUmFornecedorComFornecedorCadastrado () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        this.fornecedorController.removeFornecedor(NOME_FORNECEDOR);

        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.retornaFornecedor(NOME_FORNECEDOR);
        });
    }

    @Test
    public void testRemoverUmFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.removeFornecedor(NOME_FORNECEDOR);
        });
    }

    @Test
    public void testRetornoDoCadastroDeUmProduto () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);

        String retornoCadastroDeProduto = this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto",2.50);

        assertEquals("Cafe".concat("cafe preto"), retornoCadastroDeProduto);
    }

    @Test
    public void testCadastrarUmProdutoEmFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto",2.50);
        });
    }

    @Test
    public void testRetornarUmProdutoDeFornecedorComFornecedorCadastrado () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto",2.50);

        assertTrue(this.fornecedorController.retornaProdutoDeFornecedor(NOME_FORNECEDOR, "Cafe", "cafe preto").length() >=  1);
    }

    @Test
    public void testRetornarUmProdutoDeFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.retornaProdutoDeFornecedor(NOME_FORNECEDOR, "Cafe", "cafe preto");
        });
    }

    @Test
    public void testRetornarOsProdutosDeFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.retornaProdutosDeFornecedor(NOME_FORNECEDOR);
        });
    }

    @Test
    public void testRetornarOsProdutosDeFornecedorComFornecedorCadastrado () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto",2.50);
        this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Arroz","arroz branco",12.40);

        assertTrue(this.fornecedorController.retornaProdutosDeFornecedor(NOME_FORNECEDOR).length() >= 1);
    }

    @Test
    public void testRetornarOsProdutosDosFornecedoresComProdutosCadastrados () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.fornecedorController.cadastraFornecedor("Seu Olavo","olavo@gmail.com","83 99348-1092");

        this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto",2.50);
        this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Arroz","arroz branco",12.40);

        this.fornecedorController.cadastraProdutoEmFornecedor("Seu Olavo", "Batata","batata doce",4.50);
        this.fornecedorController.cadastraProdutoEmFornecedor("Seu Olavo", "Pão","pão frances",12.40);

        String retorneEsperado = "Marcos - Arroz - arroz branco - R$12,40 | Marcos - Cafe - cafe preto - R$2,50 | Seu Olavo - Batata - batata doce - R$4,50 | Seu Olavo - Pão - pão frances - R$12,40";

        assertEquals(retorneEsperado, this.fornecedorController.retornaProdutosDosFornecedores());
    }

    @Test
    public void testRetornarOsProdutosDosFornecedoresComProdutosNaoCadastrados () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.fornecedorController.cadastraFornecedor("Seu Olavo","olavo@gmail.com","83 99348-1092");

        String retorneEsperado = "Marcos - | Seu Olavo -";

        assertEquals(retorneEsperado, this.fornecedorController.retornaProdutosDosFornecedores());
    }

    @Test
    public void testRetornarOsProdutosDosFornecedoresComFornecedoresNaoCadastrados () {
        assertEquals("", this.fornecedorController.retornaProdutosDosFornecedores());
    }

    @Test
    public void testEditarUmProdutoDeFornecedorComFornecedorCadastrado () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto",2.50);
        this.fornecedorController.editaProdutoDeFornecedor(NOME_FORNECEDOR, "Cafe", "cafe preto", 5.00);

        assertEquals(5.00, this.fornecedorController.getPrecoProdutoDeFornecedor(NOME_FORNECEDOR, "Cafe", "cafe preto"));
    }

    @Test
    public void testEditarUmProdutoDeFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.editaProdutoDeFornecedor(NOME_FORNECEDOR, "Cafe", "cafe preto", 1.50);
        });
    }

    @Test
    public void testRemoverUmProdutoDeFornecedorComFornecedorCadastrado () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto",2.50);
        this.fornecedorController.removeProdutoDeFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto");

        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.retornaProdutoDeFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto");
        });
    }

    @Test
    public void testRemoverUmProdutoDeFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.removeProdutoDeFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto");
        });
    }

    @Test
    public void testRetornarPrecoDeUmProdutoDeFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.getPrecoProdutoDeFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto");
        });
    }

    @Test
    public void testCadastrarUmaCompraDeClienteComUmFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.cadastraCompraDeClienteComFornecedor(NOME_FORNECEDOR, "11111111111", "12-11-2013","Cafe","cafe preto",2.50);
        });
    }

    @Test
    public void testRetornarDebitoDeUmClienteComUmFornecedorCadastrado () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);

        this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto",2.50);
        this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Arroz","arroz branco",12.40);

        this.fornecedorController.cadastraCompraDeClienteComFornecedor(NOME_FORNECEDOR, "12253000014","11-11-2014", "Cafe", "cafe preto", 2.50);
        this.fornecedorController.cadastraCompraDeClienteComFornecedor(NOME_FORNECEDOR, "12253000014","08-12-2014", "Arroz","arroz branco",12.40);

        assertEquals(14.90, this.fornecedorController.getDebitoContaDeClienteComFornecedor(NOME_FORNECEDOR, "12253000014"));
    }

    @Test
    public void testRetornarDebitoDeUmClienteComUmFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.getDebitoContaDeClienteComFornecedor(NOME_FORNECEDOR, "11111111111");
        });
    }

    @Test
    public void testRetornarContaDeClienteComUmFornecedorCadastrado () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto",2.50);
        this.fornecedorController.cadastraCompraDeClienteComFornecedor(NOME_FORNECEDOR, "12253000014","11-11-2014", "Cafe", "cafe preto", 2.50);

        assertTrue(this.fornecedorController.retornaContaDeClienteComFornecedor(NOME_FORNECEDOR, "12253000014").length() >= 1);
    }

    @Test
    public void testRetornarContaDeClienteComUmFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.retornaContaDeClienteComFornecedor(NOME_FORNECEDOR, "11111111111");
        });
    }

    @Test
    public void testRetornarNomeDeFornecedoresComContaDeClienteComFornecedoresCadastrados () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.fornecedorController.cadastraFornecedor("Seu Olavo","olavo@gmail.com","83 99348-1092");

        this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto",2.50);
        this.fornecedorController.cadastraProdutoEmFornecedor("Seu Olavo", "Arroz","arroz branco",12.40);

        this.fornecedorController.cadastraCompraDeClienteComFornecedor(NOME_FORNECEDOR, "12253000014","11-11-2014", "Cafe", "cafe preto", 2.50);
        this.fornecedorController.cadastraCompraDeClienteComFornecedor("Seu Olavo", "12253000014","08-12-2014", "Arroz","arroz branco",12.40);

        String retornoEsperado = String.format("[%s, Seu Olavo]", NOME_FORNECEDOR);

        assertEquals(retornoEsperado, this.fornecedorController.retornaNomesFornecedoresComContaDeCliente("12253000014").toString());
    }

    @Test
    public void testRetornarNomeDeFornecedoresComContaDeClienteComFornecedoresNaoCadastrados () {
        String retornoEsperado = "[]";

        assertEquals(retornoEsperado, this.fornecedorController.retornaNomesFornecedoresComContaDeCliente("12253000014").toString());
    }

    @Test
    public void testExisteContaDeClienteComUmFornecedorCadastrado () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto",2.50);
        this.fornecedorController.cadastraCompraDeClienteComFornecedor(NOME_FORNECEDOR, "12253000014","11-11-2014", "Cafe", "cafe preto", 2.50);

        assertTrue(this.fornecedorController.existeContaDeClienteComFornecedor(NOME_FORNECEDOR,"12253000014"));
    }

    @Test
    public void testExisteContaDeClienteComUmFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.existeContaDeClienteComFornecedor(NOME_FORNECEDOR, "11111111111");
        });
    }

    @Test
    public void testRetornaComprasDeClienteComUmFornecedorCadastrado () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto",2.50);
        this.fornecedorController.cadastraCompraDeClienteComFornecedor(NOME_FORNECEDOR, "12253000014","11-11-2014", "Cafe", "cafe preto", 2.50);

        assertTrue(this.fornecedorController.retornaComprasDeClienteComFornecedor("12253000014", NOME_FORNECEDOR).size() >= 1);
    }

    @Test
    public void testRetornaComprasDeClienteComUmFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.retornaComprasDeClienteComFornecedor(NOME_FORNECEDOR, "11111111111");
        });
    }

    @Test
    public void testRetornaComprasDeClienteComFornecedorPorDataComUmFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedorController.retornaComprasDeClienteComFornecedorPorData("11111111111", NOME_FORNECEDOR, "11-12-2014");
        });
    }

    @Test
    public void testRetornaComprasDeClienteComFornecedorPorDataComUmFornecedorCadastrado () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto",2.50);
        this.fornecedorController.cadastraCompraDeClienteComFornecedor(NOME_FORNECEDOR, "12253000014","11-11-2014", "Cafe", "cafe preto", 2.50);

        assertTrue(this.fornecedorController.retornaComprasDeClienteComFornecedorPorData("12253000014", NOME_FORNECEDOR,"11-11-2014").size() >= 1);
    }

    @Test
    public void testRetornarOsNomesDeFornecedoresComFornecedoresCadastrados () {
        this.fornecedorController.cadastraFornecedor("Seu Olavo","olavo@gmail.com","83 99348-1092");
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);

        String retornoEsperado = "[Marcos, Seu Olavo]";

        assertEquals(retornoEsperado, this.fornecedorController.retornaNomesFornecedores().toString());
    }

    @Test
    public void testRetornarOsNomesDeFornecedoresComFornecedoresNaoCadastrados () {
        String retornoEsperado = "[]";

        assertEquals(retornoEsperado, this.fornecedorController.retornaNomesFornecedores().toString());
    }

    @Test
    public void testRetornarDatasDeComprasDeClientesComClientesSemCompras () {
        String retornoEsperado = "[]";

        assertEquals(retornoEsperado, this.fornecedorController.retornaDatasComprasDeClientesComFornecedores().toString());
    }

    @Test
    public void testRetornarDatasDeComprasDeClientesComDatasDiferentes () {
        this.fornecedorController.cadastraFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.fornecedorController.cadastraFornecedor("Seu Olavo","olavo@gmail.com","83 99348-1092");

        this.fornecedorController.cadastraProdutoEmFornecedor(NOME_FORNECEDOR, "Cafe","cafe preto",2.50);
        this.fornecedorController.cadastraProdutoEmFornecedor("Seu Olavo", "Arroz","arroz branco",12.40);

        this.fornecedorController.cadastraCompraDeClienteComFornecedor(NOME_FORNECEDOR, "11111111111","11-11-2014", "Cafe", "cafe preto", 2.50);
        this.fornecedorController.cadastraCompraDeClienteComFornecedor("Seu Olavo", "22222222222","08-12-2018", "Arroz","arroz branco",12.40);

        assertEquals("[11-11-2014, 08-12-2018]", this.fornecedorController.retornaDatasComprasDeClientesComFornecedores().toString());
    }
}