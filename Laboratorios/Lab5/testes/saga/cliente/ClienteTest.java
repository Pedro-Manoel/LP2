package saga.cliente;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe responsável pelos testes unitários da classe Cliente.
 *
 * @author Pedro Manoel
 */
class ClienteTest {

    private final String CPF_CLIENTE = "00023827490";
    private final String NOME_CLIENTE = "Victor Emanuel";
    private final String EMAIL_CLIENTE = "vitao@ccc.ufcg.edu.br";
    private final String LOCALIZACAO_CLIENTE = "Labarc";

    private Cliente cliente;

    @BeforeEach
    public void criaCliente () {
        this.cliente = new Cliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);
    }

    @Test
    public void testCompararClienteComOutroClienteConsideradoMaior () {
        Cliente cliente1 = new Cliente(CPF_CLIENTE, "Arthur", EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);

        assertTrue(this.cliente.compareTo(cliente1) >= 1);
    }

    @Test
    public void testCompararClienteComOutroClienteConsideradoMenor () {
        Cliente cliente1 = new Cliente(CPF_CLIENTE, "Zeca", EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);

        assertTrue(this.cliente.compareTo(cliente1) < 1);
    }

    @Test
    public void testCompararClienteComOutroClienteConsideradoIgual () {
        Cliente cliente1 = new Cliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);

        assertEquals(this.cliente.compareTo(cliente1), 0);
    }

    @Test
    public void testToStringDeCliente () {
        String retornoEsperado = "Victor Emanuel - Labarc - vitao@ccc.ufcg.edu.br";

        assertEquals(retornoEsperado, cliente.toString());
    }

    @Test
    public void testEqualsClienteComClienteNull () {
        Cliente clienteNull = null;

        assertNotEquals(cliente, clienteNull);
    }

    @Test
    public void testEqualsClienteComClasseDiferente () {
        String string = "cliente 1";

        assertNotEquals(cliente, string);
    }

    @Test
    public void testEqualsClientesComCpfDiferente () {
        Cliente cliente1 = new Cliente("94412783134", NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);

        assertNotEquals(this.cliente, cliente1);
    }

    @Test
    public void testEqualsClientesComCpfIgual () {
        Cliente cliente1 = new Cliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);

        assertEquals(this.cliente, cliente1);
    }

    @Test
    public void testHasCodeClientesComCpfIgual () {
        Cliente cliente1 = new Cliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);

        assertEquals(this.cliente.hashCode(), cliente1.hashCode());
    }
}