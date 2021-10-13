package saga.conta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Classe responsável pelos testes unitários da classe Conta.
 *
 * @author Pedro Manoel
 */
class ContaTest {

    private final String CPF_CLIENTE = "58217738123";

    private Conta conta;

    @BeforeEach
    public void criaConta () { this.conta = new Conta(CPF_CLIENTE); }

    @Test
    public void testRetornarAsComprasDaContaComComprasCadastradas () {
        this.conta.adicionaCompra("08/11/2018","Coxao com batata",  "Coxao de frango com batata frita", 8.30);
        this.conta.adicionaCompra("03/12/2013","Refrigerante",  "Refrigerante (lata)", 2.50);

        String retornoEsperado = "[Coxao de frango com batata frita, 08/11/2018, Refrigerante (lata), 03/12/2013]";

        assertEquals(retornoEsperado, this.conta.retornaCompras().toString());
    }

    @Test
    public void testRetornarAsComprasDaContaSemComprasCadastradas () {
        assertEquals("[]", this.conta.retornaCompras().toString());
    }

    @Test
    public void testRetornarComprasDaContaPorDataComComprasCadastradas () {
        this.conta.adicionaCompra("11-12-1998","Coca-cola","Coca-cola (lata)", 3.50);
        this.conta.adicionaCompra("01-11-2016","Coxao de Frango","Coxao de frango com cheddar", 3.00);
        this.conta.adicionaCompra("11-12-1998","Refrigerante","Refrigerante (lata)", 2.50);

        String retornoEsperado = "[Coca-cola (lata), Refrigerante (lata)]";

        assertEquals(retornoEsperado, this.conta.retornaComprasPorData("11-12-1998").toString());
    }

    @Test
    public void testRetornarComprasDaContaPorDataComComprasNaoCadastradas () {
        String retornoEsperado = "[]";

        assertEquals(retornoEsperado, this.conta.retornaComprasPorData("11-12-1998").toString());
    }

    @Test
    public void testRetornarDatasDasComprasComComprasCadastradas () {
        this.conta.adicionaCompra("11-12-1998","Coca-cola","Coca-cola (lata)", 3.50);
        this.conta.adicionaCompra("01-11-2016","Coxao de Frango","Coxao de frango com cheddar", 3.00);
        this.conta.adicionaCompra("11-12-1998","Refrigerante","Refrigerante (lata)", 2.50);

        String retornoEsperado = "[11-12-1998, 01-11-2016, 11-12-1998]";

        assertEquals(retornoEsperado, this.conta.retornaDatasDasCompras().toString());
    }

    @Test
    public void testRetornarDatasDasComprasComComprasNaoCadastradas () {
        String retornoEsperado = "[]";

        assertEquals(retornoEsperado, this.conta.retornaDatasDasCompras().toString());
    }

    @Test
    public void testGetDebitoDaContaComComprasCadastradas () {
        this.conta.adicionaCompra("07-04-1998","Coca-cola","Coca-cola (lata)", 3.50);
        this.conta.adicionaCompra("01-11-2016","Coxao de Frango","Coxao de frango com cheddar", 3.00);
        this.conta.adicionaCompra("11-12-1998","Refrigerante","Refrigerante (lata)", 2.50);

        assertEquals(9.00, this.conta.getDebito());
    }

    @Test
    public void testGetDebitoDaContaComComprasNaoCadastradas () {
        assertEquals(0.0, this.conta.getDebito());
    }

    @Test
    public void testToStringDeContaComComprasCadastradas () {
        this.conta.adicionaCompra("01-11-2016","Coxao de Frango","Coxao de frango com cheddar", 3.00);
        this.conta.adicionaCompra("11-12-1998","Refrigerante","Refrigerante (lata)", 2.50);

        String retornoEsperado = "Coxao de Frango - 01-11-2016 | Refrigerante - 11-12-1998";

        assertEquals(retornoEsperado, this.conta.toString());
    }

    @Test
    public void testToStringDeContaComComprasNaoCadastradas () {
        assertEquals("", this.conta.toString());
    }

    @Test
    public void testEqualsContaComContaNull () {
        Conta contaNull = null;

        assertNotEquals(this.conta, contaNull);
    }

    @Test
    public void testEqualsContaComClasseDiferente () {
        String string = "cliente 1";

        assertNotEquals(this.conta, string);
    }

    @Test
    public void testEqualsContaComCpfDeClienteDiferente () {
        Conta conta1 = new Conta("64269141198");

        assertNotEquals(this.conta, conta1);
    }

    @Test
    public void testEqualsContasComCpfDeClienteIgual () {
        Conta conta1 = new Conta(CPF_CLIENTE);

        assertEquals(this.conta, conta1);
    }

    @Test
    public void testHasCodeContasComCpfDeClienteIgual () {
        Conta conta1 = new Conta(CPF_CLIENTE);

        assertEquals(this.conta.hashCode(), conta1.hashCode());
    }
}