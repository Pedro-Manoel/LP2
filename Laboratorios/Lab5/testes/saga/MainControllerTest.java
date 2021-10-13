package saga;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe responsável pelos testes unitários da classe MainController.
 *
 * @author Pedro Manoel
 */
class MainControllerTest {

    private MainController mainController;

    @BeforeEach
    public void criaMainController () { this.mainController = new MainController(); }

    @Test
    public void testDefinindoClienteComoCriterioDeOrdenacaoDeCompras () {
        this.mainController.defineCriterioOrdenacaoCompras("Cliente");
    }

    @Test
    public void testDefinindoFornecedorComoCriterioDeOrdenacaoDeCompras () {
        this.mainController.defineCriterioOrdenacaoCompras("Fornecedor");
    }

    @Test
    public void testDefinindoDataComoCriterioDeOrdenacaoDeCompras () {
        this.mainController.defineCriterioOrdenacaoCompras("Data");
    }

    @Test
    public void testDefinindoUmCriterioDeOrdenacaoDeComprasDiferenteDeClienteDeFornecedorDeData () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.mainController.defineCriterioOrdenacaoCompras("Descricao");
        });
    }

    @Test
    public void testRetornarDebitoDeContaDeCliente () {
        this.mainController.cadastraFornecedor("Marcos","marcos@gmail.com","83 99151-3570");

        this.mainController.cadastraProduto("Marcos", "Cafe","cafe preto",2.50);
        this.mainController.cadastraProduto("Marcos", "Arroz","arroz branco",12.40);

        this.mainController.cadastraCliente("12253000014","Dalton", "dalton@gmail.com","SPLAB");

        this.mainController.cadastraCompra("12253000014","Marcos", "11-11-2014", "Cafe", "cafe preto");
        this.mainController.cadastraCompra("12253000014","Marcos","08-12-2014", "Arroz","arroz branco");

        String retornoEsperado = "14.90";

        assertEquals(retornoEsperado, this.mainController.retornaDebitoConta("12253000014","Marcos"));
    }

    @Test
    public void testRetornarContaDeCliente () {
        this.mainController.cadastraFornecedor("Marcos","marcos@gmail.com","83 99151-3570");

        this.mainController.cadastraProduto("Marcos", "Cafe","cafe preto",2.50);
        this.mainController.cadastraProduto("Marcos", "Arroz","arroz branco",12.40);

        this.mainController.cadastraCliente("12253000014","Dalton", "dalton@gmail.com","SPLAB");

        this.mainController.cadastraCompra("12253000014","Marcos", "11-11-2014", "Cafe", "cafe preto");
        this.mainController.cadastraCompra("12253000014","Marcos","08-12-2014", "Arroz","arroz branco");

        String retornoEsperado = "Cliente: Dalton | Marcos | Cafe - 11-11-2014 | Arroz - 08-12-2014";

        assertEquals(retornoEsperado, this.mainController.retornaConta("12253000014","Marcos"));
    }

    @Test
    public void testRetornarContasDeClienteComClienteComContasCadastradas () {
        this.mainController.cadastraFornecedor("Marcos","marcos@gmail.com","83 99151-3570");
        this.mainController.cadastraFornecedor("Joabe", "joabecompena@gmail.com", "83 90036-5012");

        this.mainController.cadastraProduto("Marcos", "Cafe","cafe preto",2.50);
        this.mainController.cadastraProduto("Joabe", "Arroz","arroz branco",12.40);

        this.mainController.cadastraCliente("12253000014","Dalton", "dalton@gmail.com","SPLAB");

        this.mainController.cadastraCompra("12253000014","Marcos", "11-12-2014", "Cafe", "cafe preto");
        this.mainController.cadastraCompra("12253000014","Joabe", "11-02-2014", "Arroz","arroz branco");

        String retornoEsperado = "Cliente: Dalton | Joabe | Arroz - 11-02-2014 | Marcos | Cafe - 11-12-2014";

        assertEquals(retornoEsperado, this.mainController.retornaContas("12253000014"));
    }

    @Test
    public void testRetornarContasDeClienteComClienteSemContasCadastradas () {
        this.mainController.cadastraFornecedor("Marcos","marcos@gmail.com","83 99151-3570");
        this.mainController.cadastraFornecedor("Joabe", "joabecompena@gmail.com", "83 90036-5012");

        this.mainController.cadastraProduto("Marcos", "Cafe","cafe preto",2.50);
        this.mainController.cadastraProduto("Joabe", "Arroz","arroz branco",12.40);

        this.mainController.cadastraCliente("12253000014","Dalton", "dalton@gmail.com","SPLAB");

        assertThrows(IllegalArgumentException.class, () -> {
            this.mainController.retornaContas("12253000014");
        });
    }

    @Test
    public void testListarComprasComCriterioNaoDefinido () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.mainController.listaCompras();
        });
    }

    @Test
    public void testListarComprasPorCliente () {
        this.mainController.cadastraFornecedor("Marcos","marcos@gmail.com","83 99151-3570");
        this.mainController.cadastraFornecedor("Joabe", "joabecompena@gmail.com", "83 90036-5012");
        this.mainController.cadastraFornecedor("Severo", "seusevero@hotmail.com", "83 7898-4565");

        this.mainController.cadastraProduto("Marcos","Cafe","cafe preto",2.50);
        this.mainController.cadastraProduto("Joabe", "Arroz","arroz branco",12.40);
        this.mainController.cadastraProduto("Severo","Bolo de Chocolate", "Bolo de trigo com cobertura de chocolate", 2.00);
        this.mainController.cadastraProduto("Marcos", "Trufa de Beijinho", "Doce sabor beijinho", 1.50);
        this.mainController.cadastraProduto("Severo", "Trufa de Beijinho", "Doce sabor beijinho", 1.50);

        this.mainController.cadastraCliente("12253000014","Dalton", "dalton@gmail.com","SPLAB");
        this.mainController.cadastraCliente("78978978978", "Zana", "zanazaninha@bol.com", "LSI");

        this.mainController.cadastraCompra("12253000014", "Marcos", "08/02/2018","Trufa de Beijinho", "Doce sabor beijinho");
        this.mainController.cadastraCompra("12253000014", "Joabe", "08/11/2014","Arroz","arroz branco");
        this.mainController.cadastraCompra("78978978978", "Joabe", "19/08/2018","Arroz","arroz branco");
        this.mainController.cadastraCompra("78978978978", "Severo", "19/08/2017","Bolo de Chocolate", "Bolo de trigo com cobertura de chocolate");
        this.mainController.cadastraCompra("12253000014", "Severo", "24/04/2000","Trufa de Beijinho", "Doce sabor beijinho");
        this.mainController.cadastraCompra("78978978978", "Marcos", "08/11/2014","Cafe","cafe preto");

        this.mainController.defineCriterioOrdenacaoCompras("Cliente");

        String retornoEsperado =
                "Dalton, Joabe, arroz branco, 08/11/2014 | " +
                "Dalton, Marcos, Doce sabor beijinho, 08/02/2018 | " +
                "Dalton, Severo, Doce sabor beijinho, 24/04/2000 | " +
                "Zana, Joabe, arroz branco, 19/08/2018 | " +
                "Zana, Marcos, cafe preto, 08/11/2014 | " +
                "Zana, Severo, Bolo de trigo com cobertura de chocolate, 19/08/2017";

        assertEquals(retornoEsperado, this.mainController.listaCompras());
    }

    @Test
    public void testListarComprasPorClienteSemComprasCadastradas () {
        this.mainController.cadastraFornecedor("Marcos","marcos@gmail.com","83 99151-3570");
        this.mainController.cadastraFornecedor("Joabe", "joabecompena@gmail.com", "83 90036-5012");
        this.mainController.cadastraFornecedor("Severo", "seusevero@hotmail.com", "83 7898-4565");

        this.mainController.cadastraProduto("Marcos","Cafe","cafe preto",2.50);
        this.mainController.cadastraProduto("Joabe", "Arroz","arroz branco",12.40);
        this.mainController.cadastraProduto("Severo","Bolo de Chocolate", "Bolo de trigo com cobertura de chocolate", 2.00);
        this.mainController.cadastraProduto("Marcos", "Trufa de Beijinho", "Doce sabor beijinho", 1.50);
        this.mainController.cadastraProduto("Severo", "Trufa de Beijinho", "Doce sabor beijinho", 1.50);

        this.mainController.cadastraCliente("12253000014","Dalton", "dalton@gmail.com","SPLAB");
        this.mainController.cadastraCliente("78978978978", "Zana", "zanazaninha@bol.com", "LSI");

        this.mainController.defineCriterioOrdenacaoCompras("Cliente");

        assertEquals("", this.mainController.listaCompras());
    }

    @Test
    public void testListarComprasPorFornecedor () {
        this.mainController.cadastraFornecedor("Marcos","marcos@gmail.com","83 99151-3570");
        this.mainController.cadastraFornecedor("Joabe", "joabecompena@gmail.com", "83 90036-5012");
        this.mainController.cadastraFornecedor("Severo", "seusevero@hotmail.com", "83 7898-4565");

        this.mainController.cadastraProduto("Marcos","Cafe","cafe preto",2.50);
        this.mainController.cadastraProduto("Joabe", "Arroz","arroz branco",12.40);
        this.mainController.cadastraProduto("Severo","Bolo de Chocolate", "Bolo de trigo com cobertura de chocolate", 2.00);
        this.mainController.cadastraProduto("Marcos", "Trufa de Beijinho", "Doce sabor beijinho", 1.50);
        this.mainController.cadastraProduto("Severo", "Trufa de Beijinho", "Doce sabor beijinho", 1.50);

        this.mainController.cadastraCliente("12253000014","Dalton", "dalton@gmail.com","SPLAB");
        this.mainController.cadastraCliente("78978978978", "Zana", "zanazaninha@bol.com", "LSI");

        this.mainController.cadastraCompra("12253000014", "Marcos", "08/02/2018","Trufa de Beijinho", "Doce sabor beijinho");
        this.mainController.cadastraCompra("12253000014", "Joabe", "08/11/2014","Arroz","arroz branco");
        this.mainController.cadastraCompra("78978978978", "Joabe", "19/08/2018","Arroz","arroz branco");
        this.mainController.cadastraCompra("78978978978", "Severo", "19/08/2017","Bolo de Chocolate", "Bolo de trigo com cobertura de chocolate");
        this.mainController.cadastraCompra("12253000014", "Severo", "24/04/2000","Trufa de Beijinho", "Doce sabor beijinho");
        this.mainController.cadastraCompra("78978978978", "Marcos", "08/11/2014","Cafe","cafe preto");

        this.mainController.defineCriterioOrdenacaoCompras("Fornecedor");

        String retornoEsperado =
                "Joabe, Dalton, arroz branco, 08/11/2014 | " +
                "Joabe, Zana, arroz branco, 19/08/2018 | " +
                "Marcos, Dalton, Doce sabor beijinho, 08/02/2018 | " +
                "Marcos, Zana, cafe preto, 08/11/2014 | " +
                "Severo, Dalton, Doce sabor beijinho, 24/04/2000 | " +
                "Severo, Zana, Bolo de trigo com cobertura de chocolate, 19/08/2017";

        assertEquals(retornoEsperado, this.mainController.listaCompras());
    }

    @Test
    public void testListarComprasPorFornecedorSemComprasCadastradas () {
        this.mainController.cadastraFornecedor("Marcos","marcos@gmail.com","83 99151-3570");
        this.mainController.cadastraFornecedor("Joabe", "joabecompena@gmail.com", "83 90036-5012");
        this.mainController.cadastraFornecedor("Severo", "seusevero@hotmail.com", "83 7898-4565");

        this.mainController.cadastraProduto("Marcos","Cafe","cafe preto",2.50);
        this.mainController.cadastraProduto("Joabe", "Arroz","arroz branco",12.40);
        this.mainController.cadastraProduto("Severo","Bolo de Chocolate", "Bolo de trigo com cobertura de chocolate", 2.00);
        this.mainController.cadastraProduto("Marcos", "Trufa de Beijinho", "Doce sabor beijinho", 1.50);
        this.mainController.cadastraProduto("Severo", "Trufa de Beijinho", "Doce sabor beijinho", 1.50);

        this.mainController.cadastraCliente("12253000014","Dalton", "dalton@gmail.com","SPLAB");
        this.mainController.cadastraCliente("78978978978", "Zana", "zanazaninha@bol.com", "LSI");

        this.mainController.defineCriterioOrdenacaoCompras("Fornecedor");

        assertEquals("", this.mainController.listaCompras());
    }

    @Test
    public void testListarComprasPorData () {
        this.mainController.cadastraFornecedor("Marcos","marcos@gmail.com","83 99151-3570");
        this.mainController.cadastraFornecedor("Joabe", "joabecompena@gmail.com", "83 90036-5012");
        this.mainController.cadastraFornecedor("Severo", "seusevero@hotmail.com", "83 7898-4565");

        this.mainController.cadastraProduto("Marcos","Cafe","cafe preto",2.50);
        this.mainController.cadastraProduto("Joabe", "Arroz","arroz branco",12.40);
        this.mainController.cadastraProduto("Severo","Bolo de Chocolate", "Bolo de trigo com cobertura de chocolate", 2.00);
        this.mainController.cadastraProduto("Marcos", "Trufa de Beijinho", "Doce sabor beijinho", 1.50);
        this.mainController.cadastraProduto("Severo", "Trufa de Beijinho", "Doce sabor beijinho", 1.50);

        this.mainController.cadastraCliente("12253000014","Dalton", "dalton@gmail.com","SPLAB");
        this.mainController.cadastraCliente("78978978978", "Zana", "zanazaninha@bol.com", "LSI");

        this.mainController.cadastraCompra("12253000014", "Marcos", "08/02/2018","Trufa de Beijinho", "Doce sabor beijinho");
        this.mainController.cadastraCompra("12253000014", "Joabe", "08/11/2014","Arroz","arroz branco");
        this.mainController.cadastraCompra("78978978978", "Joabe", "19/08/2018","Arroz","arroz branco");
        this.mainController.cadastraCompra("78978978978", "Severo", "19/08/2017","Bolo de Chocolate", "Bolo de trigo com cobertura de chocolate");
        this.mainController.cadastraCompra("12253000014", "Severo", "24/04/2000","Trufa de Beijinho", "Doce sabor beijinho");
        this.mainController.cadastraCompra("78978978978", "Marcos", "08/11/2014","Cafe","cafe preto");

        this.mainController.defineCriterioOrdenacaoCompras("Data");

        String retornoEsperado =
                "24/04/2000, Dalton, Severo, Doce sabor beijinho | " +
                "08/11/2014, Dalton, Joabe, arroz branco | " +
                "08/11/2014, Zana, Marcos, cafe preto | " +
                "19/08/2017, Zana, Severo, Bolo de trigo com cobertura de chocolate | " +
                "08/02/2018, Dalton, Marcos, Doce sabor beijinho | " +
                "19/08/2018, Zana, Joabe, arroz branco";

        assertEquals(retornoEsperado, this.mainController.listaCompras());
    }

    @Test
    public void testListarComprasPorDataSemComprasCadastradas () {
        this.mainController.cadastraFornecedor("Marcos","marcos@gmail.com","83 99151-3570");
        this.mainController.cadastraFornecedor("Joabe", "joabecompena@gmail.com", "83 90036-5012");
        this.mainController.cadastraFornecedor("Severo", "seusevero@hotmail.com", "83 7898-4565");

        this.mainController.cadastraProduto("Marcos","Cafe","cafe preto",2.50);
        this.mainController.cadastraProduto("Joabe", "Arroz","arroz branco",12.40);
        this.mainController.cadastraProduto("Severo","Bolo de Chocolate", "Bolo de trigo com cobertura de chocolate", 2.00);
        this.mainController.cadastraProduto("Marcos", "Trufa de Beijinho", "Doce sabor beijinho", 1.50);
        this.mainController.cadastraProduto("Severo", "Trufa de Beijinho", "Doce sabor beijinho", 1.50);

        this.mainController.cadastraCliente("12253000014","Dalton", "dalton@gmail.com","SPLAB");
        this.mainController.cadastraCliente("78978978978", "Zana", "zanazaninha@bol.com", "LSI");

        this.mainController.defineCriterioOrdenacaoCompras("Data");

        assertEquals("", this.mainController.listaCompras());
    }
}
