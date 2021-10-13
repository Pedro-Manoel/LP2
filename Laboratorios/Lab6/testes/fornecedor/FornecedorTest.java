package fornecedor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import produto.Produto;
import produto.ProdutoCombo;
import produto.ProdutoSimples;

/**
 * Classe responsável pelos testes unitários da classe Fornecedor.
 *
 * @author Pedro Manoel
 */
class FornecedorTest {

    private final String NOME_PRODUTO_SIMPLES_1 = "Café";
    private final String NOME_PRODUTO_SIMPLES_2 = "Tapioca";
    private final String DESCRICAO_PRODUTO_SIMPLES_1 = "Café preto";
    private final String DESCRICAO_PRODUTO_SIMPLES_2 = "Tapioca de coco";
    private final double PRECO_PRODUTO_SIMPLES_1 = 2.50;
    private final double PRECO_PRODUTO_SIMPLES_2 = 3.80;

    private final String NOME_PRODUTO_COMBO = "Café + Tapioca";
    private final String DESCRICAO_PRODUTO_COMBO = "Café preto com tapioca de coco";
    private final double FATOR_DESCONTO_PRODUTO_COMBO = 0.25;
    private final String PRODUTOS_DO_COMBO_STRING = "Café - Café preto, Tapioca - Tapioca de coco";
    private final double PRECO_PRODUTO_COMBO = PRECO_PRODUTO_SIMPLES_1 + PRECO_PRODUTO_SIMPLES_2;

    private Fornecedor fornecedor;

    @BeforeEach
    public void criaFornecedor () {
        this.fornecedor = new Fornecedor("Marcos", "marcos@gmail.com", "83 99151-3570");
    }

    @Test
    public void testAdicionarUmProdutoSimples () {
        Produto produto = new ProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);

        assertEquals(produto.toString(), this.fornecedor.retornaProduto(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1));
    }

    @Test
    public void testAdicionarUmMesmoProdutoSimplesDuasVezes () {
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);

        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        });
    }

    @Test
    public void testAdicionarUmProdutoComboComUmProdutoDoComboNaoCadastrado () {
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);

        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.adicionaProdutoCombo(NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO, PRODUTOS_DO_COMBO_STRING);
        });
    }

    @Test
    public void testAdicionarUmProdutoComboComOsProdutosDoComboCadastrados () {
        Produto produto = new ProdutoCombo(NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, PRECO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO);
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);
        this.fornecedor.adicionaProdutoCombo(NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO, PRODUTOS_DO_COMBO_STRING);

        assertEquals(produto.toString(), this.fornecedor.retornaProduto(NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO));
    }

    @Test
    public void testAdicionarUmProdutoComboComUmDosProdutosDoComboSendoUmProdutoCombo () {
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);
        this.fornecedor.adicionaProdutoCombo(NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO, PRODUTOS_DO_COMBO_STRING);

        this.fornecedor.adicionaProdutoSimples("Suco de manga","Suco de manga verde gelado", 2.70);
        this.fornecedor.adicionaProdutoCombo("Suco de manga + Tapioca", "Suco de manga gelado com tapioca de coco", 0.25, "Suco de manga - Suco de manga verde gelado, Tapioca - Tapioca de coco");

        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.adicionaProdutoCombo("Suco de manga + Café preto + Tapioca", "Suco de manga gelado com cafe preto e tapioca de coco", 0.25, "Suco de manga + Tapioca - Suco de manga gelado com tapioca de coco, Café - Café preto");
        });
    }

    @Test
    public void testAdicionarUmMesmoProdutoComboDuasVezes () {
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);
        this.fornecedor.adicionaProdutoCombo(NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO, PRODUTOS_DO_COMBO_STRING);

        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.adicionaProdutoCombo(NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO, PRODUTOS_DO_COMBO_STRING);
        });
    }

    @Test
    public void testRetornarUmProdutoComProdutoNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.retornaProduto(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1);
        });
    }

    @Test
    public void testRetornarUmProdutoComProdutoCadastrado () {
        Produto produto = new ProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);

        assertEquals(produto.toString(), this.fornecedor.retornaProduto(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1));
    }

    @Test
    public void testRetornarOsProdutosComProdutosNaoCadastrados () {
        assertEquals("Marcos -", this.fornecedor.retornaProdutos());
    }

    @Test
    public void testRetornaOsProdutosComProdutosCadastrados () {
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);

        String retorneEsperado = "Marcos - Café - Café preto - R$2,50 | Marcos - Tapioca - Tapioca de coco - R$3,80";

        assertEquals(retorneEsperado, this.fornecedor.retornaProdutos());
    }

    @Test
    public void testEditarUmProdutoSimpleComProdutoSimplesNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.editaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, 4.30);
        });
    }

    @Test
    public void testEditarUmProdutoSimpleComProdutoSimplesCadastrado () {
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.fornecedor.editaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, 4.30);

        assertEquals(4.30, this.fornecedor.getPrecoProduto(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1));
    }

    @Test
    public void testRetornarPrecoDeUmProdutoComProdutoNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.getPrecoProduto(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1);
        });
    }

    @Test
    public void testRetornarPrecoDeUmProdutoComProdutoCadastrado () {
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);

        assertEquals(PRECO_PRODUTO_SIMPLES_1, this.fornecedor.getPrecoProduto(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1));
    }

    @Test
    public void testEditarUmProdutoComboComProdutoComboNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.editaProdutoCombo(NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, 0.45);
        });
    }

    @Test
    public void testEditarUmProdutoComboComProdutoComboCadastrado () {
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);
        this.fornecedor.adicionaProdutoCombo(NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO, PRODUTOS_DO_COMBO_STRING);
        this.fornecedor.editaProdutoCombo(NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, 0.45);

        assertEquals(3.465, this.fornecedor.getPrecoProduto(NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO));
    }

    @Test
    public void testRemoverUmProdutoComProdutoNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.removeProduto(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1);
        });
    }

    @Test
    public void testRemoverUmProdutoComProdutoCadastrado () {
        this.fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.fornecedor.removeProduto(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1);

        assertThrows(IllegalArgumentException.class, () -> {
            this.fornecedor.retornaProduto(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1);
        });
    }
}
