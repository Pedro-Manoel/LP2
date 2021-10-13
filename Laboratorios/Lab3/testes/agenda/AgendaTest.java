package agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Classe responsável pelos testes unitários da classe Agenda.
 *
 * @author Pedro Manoel
 */
class AgendaTest {

    private Agenda agenda;

    private String cadastraContato1EmAgenda (int posicao, Agenda agenda) {
        String[] telefones = {"111", "222", "333"};
        return agenda.cadastraContato(posicao, "Daenerys", "Targaryen", telefones, 1, 1);
    }

    private String cadastraContato2EmAgenda (int posicao, Agenda agenda) {
        String[] telefones = {"444", "555", "666"};
        return agenda.cadastraContato(posicao,"Jon", "Snow", telefones, 2, 1);
    }

    @BeforeEach
    public void criaAgenda () {
        this.agenda = new Agenda();
    }

    @Test
    public void testCadastraContatoNaPosicao0 () {
        String retornoDoCadastro = this.cadastraContato1EmAgenda(0, this.agenda);
        assertEquals(retornoDoCadastro, "POSIÇÃO INVÁLIDA!");
    }

    @Test
    public void testCadastraContatoNaPosicao1 () {
        String retornoDoCadastro = this.cadastraContato1EmAgenda(1, this.agenda);
        assertEquals(retornoDoCadastro, "CADASTRO REALIZADO");
    }

    @Test
    public void testCadastraContatoNaPosicao100 () {
        String retornoDoCadastro = this.cadastraContato1EmAgenda(100, this.agenda);
        assertEquals(retornoDoCadastro, "CADASTRO REALIZADO");
    }

    @Test
    public void testCadastraContatoNaPosicao101 () {
        String retornoDoCadastro = this.cadastraContato1EmAgenda(101, this.agenda);
        assertEquals(retornoDoCadastro, "POSIÇÃO INVÁLIDA!");
    }

    @Test
    public void testCadastraContatoEmPosicaoVazia () {
        String retornoDoCadastro = this.cadastraContato1EmAgenda(10, this.agenda);
        assertEquals(retornoDoCadastro, "CADASTRO REALIZADO");
    }

    @Test
    public void testCadastraContatoEmPosicaoPreenchida () {
        this.cadastraContato1EmAgenda(15, this.agenda);
        String retornoDoCadastroDeContato2 = this.cadastraContato2EmAgenda(15, this.agenda);
        assertEquals(retornoDoCadastroDeContato2, "CADASTRO REALIZADO");
    }

    @Test
    public void testDetalhesContatoNaPosicao1ComContatoNaPosicao1 () {
        this.cadastraContato1EmAgenda(1, this.agenda);
        String retornoEsperado =
                "Daenerys Targaryen\n" +
                "111 (prioritário) (zap)\n" +
                "222\n" +
                "333";
        assertEquals(retornoEsperado, this.agenda.detalhesContato(1));
    }

    @Test
    public void testDetalhesContatoNaPosicao1SemContatoNaPosicao1 () {
        assertEquals("POSIÇÃO INVÁLIDA!", this.agenda.detalhesContato(1));
    }

    @Test
    public void testListarOsContatosComContatosCadastrados () {
        this.cadastraContato1EmAgenda(1, this.agenda);
        this.cadastraContato2EmAgenda(2, this.agenda);
        String retornoEsperado =
                "1 - Daenerys Targaryen\n" +
                "2 - Jon Snow";
        assertEquals(retornoEsperado, this.agenda.listaContatos());
    }

    @Test
    public void testListarOsContatosSemContatosCadastrados () {
        assertEquals("", this.agenda.listaContatos());
    }

    @Test
    public void testListarOsContatosComTelefonePrioritarioComContatosCadastrados () {
        this.cadastraContato1EmAgenda(1, this.agenda);
        this.cadastraContato2EmAgenda(2, this.agenda);
        String retornoEsperado =
                "Daenerys Targaryen - 111\n" +
                "Jon Snow - 555";
        assertEquals(retornoEsperado, this.agenda.listaContatosTelefonePrioritario());
    }

    @Test
    public void testListarOsContatosComTelefonePrioritarioSemContatosCadastrados () {
        assertEquals("", this.agenda.listaContatosTelefonePrioritario());
    }

    @Test
    public void testListarOsContatosComTelefoneDeWhatsappComContatosCadastrados () {
        this.cadastraContato1EmAgenda(1, this.agenda);
        this.cadastraContato2EmAgenda(2, this.agenda);
        String retornoEsperado =
                "Daenerys Targaryen - 111\n" +
                "Jon Snow - 444";
        assertEquals(retornoEsperado, this.agenda.listaContatosTelefoneWhatsapp());
    }

    @Test
    public void testListarOsContatosComTelefoneDeWhatsappSemContatosCadastrados () {
        assertEquals("", this.agenda.listaContatosTelefoneWhatsapp());
    }

    @Test
    public void testEqualsAgendaComObjetoNull () {
        Agenda a1 = null;
        assertNotEquals(a1, this.agenda);
    }

    @Test
    public void testEqualsAgendaComClasseDiferente () {
        String a1 = "agenda 1";
        assertNotEquals(a1, this.agenda);
    }

    @Test
    public void testEqualsAgendasComContatosDiferentesEmPosicoesDiferentes () {
        Agenda agendaTeste = new Agenda();
        this.cadastraContato1EmAgenda(1, agendaTeste);
        this.cadastraContato2EmAgenda(2, this.agenda);
        assertNotEquals(agendaTeste, this.agenda);
    }

    @Test
    public void testEqualsAgendasComContatosDiferentesEmPosicoesIguais () {
        Agenda agendaTeste = new Agenda();
        this.cadastraContato1EmAgenda(1, agendaTeste);
        this.cadastraContato2EmAgenda(1, this.agenda);
        assertNotEquals(agendaTeste, this.agenda);
    }

    @Test
    public void testEqualsAgendasComContatosIguaisEmPosicoesDiferentes () {
        Agenda agendaTeste = new Agenda();
        this.cadastraContato1EmAgenda(1, agendaTeste);
        this.cadastraContato1EmAgenda(2, this.agenda);
        assertNotEquals(agendaTeste, this.agenda);
    }

    @Test
    public void testEqualsAgendasComContatosIguaisEmPosicoesIguais () {
        Agenda agendaTeste = new Agenda();
        this.cadastraContato1EmAgenda(1, agendaTeste);
        this.cadastraContato1EmAgenda(1, this.agenda);
        assertEquals(agendaTeste, this.agenda);
    }

    @Test
    void testHashCode() {
        Agenda agendaTeste = new Agenda();
        this.cadastraContato1EmAgenda(1, agendaTeste);
        this.cadastraContato1EmAgenda(1, this.agenda);
        assertEquals(agendaTeste.hashCode(), this.agenda.hashCode());
    }
}
