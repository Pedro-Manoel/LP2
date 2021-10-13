package agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe responsável pelos testes unitários da classe Contato.
 *
 * @author Pedro Manoel
 */
public class ContatoTest {

    private Contato contato;

    @BeforeEach
    public void criaContato () {
        String[] telefones = {"111","222","333"};
        this.contato = new Contato("Daenerys", "Targaryen", telefones, 1, 2);
    }

    @Test
    public void testDetalhesContatoComContatoSemTelefones () {
        String[] telefones = {"","",""};
        Contato c1 = new Contato("Daenerys", "Targaryen", telefones, 1, 2);
        assertEquals("Daenerys Targaryen\n", c1.detalhes());
    }

    @Test
    public void testDetalhesContatoComContatoComTelefones () {
        String retornoEsperado =
                "Daenerys Targaryen\n" +
                "111 (prioritário)\n" +
                "222 (zap)\n" +
                "333";
        assertEquals(retornoEsperado, this.contato.detalhes());
    }

    @Test
    public void testGetTelefonePrioritarioQuandoExisteOTelefone () {
        assertEquals("111", this.contato.getTelefonePrioritario());

    }

    @Test
    public void testGetTelefonePrioritarioQuandoNaoExisteOTelefone () {
        String[] telefones = {"","",""};
        Contato c1 = new Contato("Daenerys", "Targaryen", telefones, 1, 1);
        assertEquals("Não tem", c1.getTelefonePrioritario());
    }

    @Test
    public void testGetTelefoneDeWhatsappQuandoExisteOTelefone () {
        assertEquals("222", this.contato.getTelefoneWhatsapp());
    }

    @Test
    public void testGetTelefoneDeWhatsappQuandoNaoExisteOTelefone () {
        String[] telefones = {"","",""};
        Contato c1 = new Contato("Daenerys", "Targaryen", telefones, 1, 1);
        assertEquals("Não tem", c1.getTelefoneWhatsapp());
    }

    @Test
    public void testEqualsContatoComObjetoNull () {
        Contato c1 = null;
        assertNotEquals(c1, this.contato);
    }

    @Test
    public void testEqualsContatoComClasseDiferente () {
        String c1 = "contato 1";
        assertNotEquals(c1, this.contato);
    }

    @Test
    public void testEqualsContatosComNomeESobrenomeDiferentes () {
        String[] telefones = {"","",""};
        Contato c1 = new Contato("Jon", "Snow", telefones, 1, 1);
        assertNotEquals(c1, this.contato);
    }

    @Test
    public void testEqualsContatosComNomeESobrenomeIguais() {
        String[] telefones = {"","",""};
        Contato c1 = new Contato("Daenerys", "Targaryen", telefones, 2,2);
        assertEquals(c1, this.contato);
    }

    @Test
    public void testCriarContatoComNomeNull () {
         assertThrows(NullPointerException.class, () -> {
             String[] telefones = {"","",""};
             Contato c1 = new Contato(null, "Targaryen", telefones, 1,1);
         });
    }

    @Test
    public void testCriarContatoComNomeVazio () {
        assertThrows(IllegalArgumentException.class, () -> {
            String[] telefones = {"","",""};
            Contato c1 = new Contato("", "Targaryen", telefones, 1,1);
        });
    }

    @Test
    public void testCriarContatoComNomePreenchidoComEspacosEmBranco () {
        assertThrows(IllegalArgumentException.class, () -> {
            String[] telefones = {"","",""};
            Contato c1 = new Contato("     ", "Targaryen", telefones, 1,1);
        });
    }

    @Test
    public void testCriarContatoComSobrenomeNull () {
        assertThrows(NullPointerException.class, () -> {
            String[] telefones = {"","",""};
            Contato c1 = new Contato("Daenerys", null, telefones, 1,1);
        });
    }

    @Test
    public void testCriarContatoComSobrenomeVazio () {
        assertThrows(IllegalArgumentException.class, () -> {
            String[] telefones = {"","",""};
            Contato c1 = new Contato("Daenerys", "", telefones, 1,1);
        });
    }

    @Test
    public void testCriarContatoComSobrenomePreenchidoComEspacosEmBranco () {
        assertThrows(IllegalArgumentException.class, () -> {
            String[] telefones = {"","",""};
            Contato c1 = new Contato("Daenerys", "     ", telefones, 1,1);
        });
    }

    @Test
    public void testCriarContatoComTelefonesNull () {
        assertThrows(NullPointerException.class, () -> {
            Contato c1 = new Contato("Daenerys", "Targaryen", null, 1,1);
        });
    }

    @Test
    public void testToString () {
        assertEquals("Daenerys Targaryen", this.contato.toString());
    }

    @Test
    public void testHasCode () {
        String[] telefones = {"","",""};
        Contato c1 = new Contato("Daenerys", "Targaryen", telefones, 2,2);
        assertEquals(c1.hashCode(), this.contato.hashCode());
    }
}