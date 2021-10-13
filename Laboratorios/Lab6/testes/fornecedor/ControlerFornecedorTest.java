package fornecedor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import produto.Produto;
import produto.ProdutoCombo;
import produto.ProdutoSimples;

/**
 * Classe responsável pelos testes unitários da classe ControlerFornecedor.
 *
 * @author Pedro Manoel
 */
class ControlerFornecedorTest {

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

    private final String NOME_FORNECEDOR = "Marcos";
    private final String EMAIL_FORNECEDOR = "marcos@gmail.com";
    private final String TELEFONE_FORNECEDOR = "83 99151-3570";

    private ControlerFornecedor controlerFornecedor;

    @BeforeEach
    public void criaControllerDeFornecedores () {
        this.controlerFornecedor = new ControlerFornecedor();
    }

    @Test
    public void testAdicionarUmProdutoSimplesEmFornecedorPassandoPrecoDoProdutoMenorQueZero () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);

        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, -1.00);
        });
    }

    @Test
    public void testAdicionarUmProdutoSimplesEmFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor (NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        });
    }

    @Test
    public void testAdicionarUmProdutoSimplesEmFornecedor () {
        Produto produto = new ProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);

        assertEquals(produto.toString(), this.controlerFornecedor.retornaProdutoDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1));
    }

    @Test
    public void testRetornoDaAdicaoDeUmProdutoSimplesEmFornecedor () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        String retornoAdicaoProdutoSimples = this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);

        assertEquals(retornoAdicaoProdutoSimples, Produto.geraChave(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1));
    }

    @Test
    public void testAdicionarUmProdutoComboEmFornecedorPassandoFatorDeDescontoDoProdutoMenorQueZero () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);

        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.adicionaProdutoComboEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, -1, PRODUTOS_DO_COMBO_STRING);
        });
    }

    @Test
    public void testAdicionarUmProdutoComboEmFornecedorPassandoFatorDeDescontoDoProdutoIgualZero () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);

        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.adicionaProdutoComboEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, 0, PRODUTOS_DO_COMBO_STRING);
        });
    }

    @Test
    public void testAdicionarUmProdutoComboEmFornecedorPassandoFatorDeDescontoDoProdutoMaiorQueUm () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);

        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.adicionaProdutoComboEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, 1.50, PRODUTOS_DO_COMBO_STRING);
        });
    }

    @Test
    public void testAdicionarUmProdutoComboEmFornecedorPassandoFatorDeDescontoDoProdutoIgualUm () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);

        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.adicionaProdutoComboEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, 1, PRODUTOS_DO_COMBO_STRING);
        });
    }

    @Test
    public void testAdicionarUmProdutoComboEmFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.adicionaProdutoComboEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO, PRODUTOS_DO_COMBO_STRING);
        });
    }

    @Test
    public void testAdicionarUmProdutoComboEmFornecedor () {
        Produto produto = new ProdutoCombo(NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, PRECO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO);
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);
        this.controlerFornecedor.adicionaProdutoComboEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO, PRODUTOS_DO_COMBO_STRING);

        assertEquals(produto.toString(), this.controlerFornecedor.retornaProdutoDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO));
    }

    @Test
    public void testRetornoDaAdicaoDeUmProdutoComboEmFornecedor () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);
        String retornoAdicaoProdutoCombo = this.controlerFornecedor.adicionaProdutoComboEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO, PRODUTOS_DO_COMBO_STRING);

        assertEquals(retornoAdicaoProdutoCombo, Produto.geraChave(NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO));
    }

    @Test
    public void testRetornarUmProdutoDeFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.retornaProdutoDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1);
        });
    }

    @Test
    public void testRetornarUmProdutoDeFornecedorComFornecedorCadastrado () {
        Produto produto = new ProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);

        assertEquals(produto.toString(), this.controlerFornecedor.retornaProdutoDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1));
    }

    @Test
    public void testRetornarOsProdutosDeFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.retornaProdutosDeFornecedor(NOME_FORNECEDOR);
        });
    }

    @Test
    public void testRetornarOsProdutosDeFornecedorComFornecedorCadastrado () {
        Fornecedor fornecedor = new Fornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);
        fornecedor.adicionaProdutoSimples(NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);

        assertEquals(fornecedor.retornaProdutos(), this.controlerFornecedor.retornaProdutosDeFornecedor(NOME_FORNECEDOR));
    }

    @Test
    public void testRetornarOsProdutosDosFornecedoresComProdutosNaoCadastrados () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaFornecedor("Seu Olavo","olavo@gmail.com","83 99348-1092");

        String retorneEsperado = "Marcos - | Seu Olavo -";

        assertEquals(retorneEsperado, this.controlerFornecedor.retornaProdutosDosFornecedores());
    }

    @Test
    public void testRetornarOsProdutosDosFornecedoresComFornecedoresNaoCadastrados () {
        assertEquals("", this.controlerFornecedor.retornaProdutosDosFornecedores());
    }

    @Test
    public void testRetornarOsProdutosDosFornecedoresComProdutosCadastrados () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaFornecedor("Seu Olavo","olavo@gmail.com","83 99348-1092");

        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);

        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor("Seu Olavo", "Batata","Batata doce",4.50);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor("Seu Olavo", "Pão","Pão francês",12.40);

        String retorneEsperado = "Marcos - Café - Café preto - R$2,50 | Marcos - Tapioca - Tapioca de coco - R$3,80 | Seu Olavo - Batata - Batata doce - R$4,50 | Seu Olavo - Pão - Pão francês - R$12,40";

        assertEquals(retorneEsperado, this.controlerFornecedor.retornaProdutosDosFornecedores());
    }

    @Test
    public void testEditarUmProdutoSimplesDeFornecedorComFornecedorCadastrado () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.editaProdutoSimplesDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, 4.60);

        assertEquals(4.60, this.controlerFornecedor.getPrecoProdutoDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1));
    }

    @Test
    public void testEditarUmProdutoSimplesDeFornecedorPassandoNovoPrecoDoProdutoMenorQueZero () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);

        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.editaProdutoSimplesDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, -1);
        });
    }

    @Test
    public void testEditarUmProdutoSimplesDeFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.editaProdutoSimplesDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, 4.60);
        });
    }

    @Test
    public void testEditarUmProdutoComboDeFornecedorComFornecedorCadastrado () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);
        this.controlerFornecedor.adicionaProdutoComboEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO, PRODUTOS_DO_COMBO_STRING);
        this.controlerFornecedor.editaProdutoComboDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, 0.50);

        assertEquals(3.15, this.controlerFornecedor.getPrecoProdutoDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO));
    }

    @Test
    public void testEditarUmProdutoComboDeFornecedorPassandoNovoFatorDeDescontoDoProdutoMenorQueZero () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);
        this.controlerFornecedor.adicionaProdutoComboEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO, PRODUTOS_DO_COMBO_STRING);

        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.editaProdutoComboDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, -1);
        });
    }

    @Test
    public void testEditarUmProdutoComboDeFornecedorPassandoNovoFatorDeDescontoDoProdutoIgualZero () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);
        this.controlerFornecedor.adicionaProdutoComboEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO, PRODUTOS_DO_COMBO_STRING);

        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.editaProdutoComboDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, 0);
        });
    }

    @Test
    public void testEditarUmProdutoComboDeFornecedorPassandoNovoFatorDeDescontoDoProdutoMaiorQueUm () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);
        this.controlerFornecedor.adicionaProdutoComboEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO, PRODUTOS_DO_COMBO_STRING);

        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.editaProdutoComboDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, 1.50);
        });
    }

    @Test
    public void testEditarUmProdutoComboDeFornecedorPassandoNovoFatorDeDescontoDoProdutoIgualUm () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_2, DESCRICAO_PRODUTO_SIMPLES_2, PRECO_PRODUTO_SIMPLES_2);
        this.controlerFornecedor.adicionaProdutoComboEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, FATOR_DESCONTO_PRODUTO_COMBO, PRODUTOS_DO_COMBO_STRING);

        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.editaProdutoComboDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, 1);
        });
    }

    @Test
    public void testEditarUmProdutoComboDeFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.editaProdutoComboDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_COMBO, DESCRICAO_PRODUTO_COMBO, 0.50);
        });
    }

    @Test
    public void testRemoverUmProdutoDeFornecedorComFornecedorCadastrado () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR,EMAIL_FORNECEDOR,TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);
        this.controlerFornecedor.removeProdutoDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1);

        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.retornaProdutoDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1);
        });
    }

    @Test
    public void testRemoverUmProdutoDeFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.removeProdutoDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1);
        });
    }

    @Test
    public void testRetornarPrecoDeUmProdutoDeFornecedorComFornecedorNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.controlerFornecedor.getPrecoProdutoDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1);
        });
    }

    @Test
    public void testRetornarPrecoDeUmProdutoDeFornecedorComFornecedorCadastrado () {
        this.controlerFornecedor.adicionaFornecedor(NOME_FORNECEDOR, EMAIL_FORNECEDOR, TELEFONE_FORNECEDOR);
        this.controlerFornecedor.adicionaProdutoSimplesEmFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1, PRECO_PRODUTO_SIMPLES_1);

        assertEquals(2.50, this.controlerFornecedor.getPrecoProdutoDeFornecedor(NOME_FORNECEDOR, NOME_PRODUTO_SIMPLES_1, DESCRICAO_PRODUTO_SIMPLES_1));
    }
}