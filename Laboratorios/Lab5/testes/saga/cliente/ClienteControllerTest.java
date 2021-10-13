package saga.cliente;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe responsável pelos testes unitários da classe ClienteController.
 *
 * @author Pedro Manoel
 */
class ClienteControllerTest {

    private final String CPF_CLIENTE = "00023827490";
    private final String NOME_CLIENTE = "Victor Emanuel";
    private final String EMAIL_CLIENTE = "vitao@ccc.ufcg.edu.br";
    private final String LOCALIZACAO_CLIENTE = "Labarc";

    private ClienteController clienteController;

    @BeforeEach
    public void criaControllerDeClientes () {
        this.clienteController = new ClienteController();
    }

    @Test
    public void testCadastrarUmCliente () {
        Cliente cliente = new Cliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);
        this.clienteController.cadastraCliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);

        assertEquals(cliente.toString(), this.clienteController.retornaCliente(CPF_CLIENTE));
    }

    @Test
    public void testRetornoDoCadastroDeUmCliente () {
        String retornoDoCadastro = this.clienteController.cadastraCliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);

        assertEquals(retornoDoCadastro, CPF_CLIENTE );
    }

    @Test
    public void testCadastrarUmMesmoClienteDuasVezes () {
        this.clienteController.cadastraCliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);

        assertThrows(IllegalArgumentException.class, () -> {
            this.clienteController.cadastraCliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);
        });
    }

    @Test
    public void testRetornarUmClienteComClienteCadastrado () {
        Cliente cliente = new Cliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);
        this.clienteController.cadastraCliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);

        assertEquals(cliente.toString(), this.clienteController.retornaCliente(CPF_CLIENTE));
    }

    @Test
    public void testRetornarUmClienteComClienteNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.clienteController.retornaCliente(CPF_CLIENTE);
        });
    }

    @Test
    public void testRetornarOsClientesSemClientesCadastrados () {
        assertEquals("", this.clienteController.retornaClientes());
    }

    @Test
    public void testRetornarOsClientesComClientesCadastrados () {
        this.clienteController.cadastraCliente("44444444444","Ana Silva","anasilva@ccc.ufcg.edu.br", "Embedded");
        this.clienteController.cadastraCliente("22222222222","João Neto", "joaoneto@ccc.ufcg.edu.br", "SPLAB");

        String retornoEsperado = "Ana Silva - Embedded - anasilva@ccc.ufcg.edu.br | João Neto - SPLAB - joaoneto@ccc.ufcg.edu.br";

        assertEquals(retornoEsperado, this.clienteController.retornaClientes());
    }

    @Test
    public void testEditarUmClienteComClienteNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.clienteController.editaCliente(CPF_CLIENTE,"nome", "Vitao Farias");
        });
    }

    @Test
    public void testEditarCpfDeUmCliente () {
        this.clienteController.cadastraCliente(CPF_CLIENTE,NOME_CLIENTE,EMAIL_CLIENTE,LOCALIZACAO_CLIENTE);

        assertThrows(IllegalArgumentException.class, () -> {
            this.clienteController.editaCliente(CPF_CLIENTE,"cpf", "77597778090");
        });
    }

    @Test
    public void testEditarNomeDeUmCliente () {
        Cliente cliente = new Cliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);
        cliente.setNome("Vitao Farias");

        this.clienteController.cadastraCliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);
        this.clienteController.editaCliente(CPF_CLIENTE,"nome", "Vitao Farias");

        assertEquals(cliente.toString(), this.clienteController.retornaCliente(CPF_CLIENTE).toString());
    }

    @Test
    public void testEditarEmailDeUmCliente () {
        Cliente cliente = new Cliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);
        cliente.setEmail("vitao@ufcg.edu.br");

        this.clienteController.cadastraCliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);
        this.clienteController.editaCliente(CPF_CLIENTE,"email", "vitao@ufcg.edu.br");

        assertEquals(cliente.toString(), this.clienteController.retornaCliente(CPF_CLIENTE));
    }

    @Test
    public void testEditarLocalizacaoDeUmCliente () {
        Cliente cliente = new Cliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);
        cliente.setLocalizacao("splab");

        this.clienteController.cadastraCliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);
        this.clienteController.editaCliente(CPF_CLIENTE,"localizacao", "splab");

        assertEquals(cliente.toString(), this.clienteController.retornaCliente(CPF_CLIENTE));
    }

    @Test
    public void testEditarUmAtributoQueNaoExisteEmCliente () {
        this.clienteController.cadastraCliente(CPF_CLIENTE,NOME_CLIENTE,EMAIL_CLIENTE,LOCALIZACAO_CLIENTE);

        assertThrows(IllegalArgumentException.class, () -> {
            this.clienteController.editaCliente(CPF_CLIENTE,"idade", "18");
        });
    }

    @Test
    public void testRemoverUmClienteComClienteNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            this.clienteController.removeCliente(CPF_CLIENTE);
        });
    }

    @Test
    public void testRemoverUmClienteComClienteCadastrado () {
        this.clienteController.cadastraCliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);
        this.clienteController.removeCliente(CPF_CLIENTE);

        assertThrows(IllegalArgumentException.class, () -> {
            this.clienteController.retornaCliente(CPF_CLIENTE);
        });
    }

    @Test
    public void testRetornarNomeDeUmClienteCadastrado () {
        this.clienteController.cadastraCliente(CPF_CLIENTE, NOME_CLIENTE, EMAIL_CLIENTE, LOCALIZACAO_CLIENTE);

        assertEquals(this.NOME_CLIENTE, this.clienteController.getNomeCliente(this.CPF_CLIENTE));
    }

    @Test
    public void testRetornarNomeDeUmClienteNaoCadastrado () {
        assertThrows(IllegalArgumentException.class, () -> {
            assertEquals(this.NOME_CLIENTE, this.clienteController.getNomeCliente(this.CPF_CLIENTE));
        });
    }

    @Test
    public void testRetornarCpfsDeClientesComClientesCadastrados () {
        this.clienteController.cadastraCliente("44444444444","Ana Silva","anasilva@ccc.ufcg.edu.br", "Embedded");
        this.clienteController.cadastraCliente("22222222222","João Neto", "joaoneto@ccc.ufcg.edu.br", "SPLAB");

        String retornoEsperado = "[44444444444, 22222222222]";

        assertEquals(retornoEsperado, this.clienteController.retornaCpfsClientes().toString());
    }

    @Test
    public void testRetornarCpfsDeClientesComClientesNaoCadastrados () {
        assertEquals("[]", this.clienteController.retornaCpfsClientes().toString());
    }
}
