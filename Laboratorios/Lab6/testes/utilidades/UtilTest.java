package utilidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe responsável pelos testes unitários da classe Util.
 *
 * @author Pedro Manoel
 */
class UtilTest {

    @Test
    public void testTestarNullPassandoObjetoNull () {
        assertThrows(NullPointerException.class, () -> {
            String nome = null;
            Util.testaNull(nome, "Erro");
        });
    }

    @Test
    public void testTestarNullPassandoObjetoNaoNull () {
        String nome = "Marcos";
        Util.testaNull(nome, "Erro");
    }

    @Test
    public void testTestarVazioPassandoStringVazia () {
        assertThrows(IllegalArgumentException.class, () -> {
            String nome = "";
            Util.testaVazio(nome, "Erro");
        });
    }

    @Test
    public void testTestarVazioPassandoStringPreenchidaComEspacosEmBranco () {
        assertThrows(IllegalArgumentException.class, () -> {
            String nome = "     ";
            Util.testaVazio(nome, "Erro");
        });
    }

    @Test
    public void testTestarVazioPassandoStringNaoVazia () {
        String nome = "Marcos";
        Util.testaVazio(nome, "Erro");
    }

    @Test
    public void testTestarNullOuVazioPassandoStringNull () {
        assertThrows(NullPointerException.class, () -> {
            String nome = null;
            Util.testaNullOuVazio(nome, "Erro");
        });
    }

    @Test
    public void testTestarNullOuVazioPassandoStringVazia () {
        assertThrows(IllegalArgumentException.class, () -> {
            String nome = "";
            Util.testaNullOuVazio(nome, "Erro");
        });
    }

    @Test
    public void testTestarNullOuVazioPassandoStringPreenchidaComEspacosEmBranco () {
        assertThrows(IllegalArgumentException.class, () -> {
            String nome = "     ";
            Util.testaNullOuVazio(nome, "Erro");
        });
    }

    @Test
    public void testTestarNullOuVazioPassandoStringNaoNullNaoVazia () {
        String nome = "Marcos";
        Util.testaNullOuVazio(nome, "Erro");
    }
}