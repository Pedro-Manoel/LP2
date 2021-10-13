package controle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Classe responsável pelos testes unitários da classe GrupoEstudo.
 *
 * @author Pedro Manoel
 */
class GrupoEstudoTest {

    private GrupoEstudo grupoEstudo;

    @BeforeEach
    public void criaGrupoDeEstudo () {
        this.grupoEstudo = new GrupoEstudo("Listas");
    }

    @Test
    public void testCriarGrupoDeEstudoComNomeNull () {
        assertThrows(NullPointerException.class, () -> {
            GrupoEstudo ge1 = new GrupoEstudo(null);
        });
    }

    @Test
    public void testCriarGrupoDeEstudoComNomeVazio () {
        assertThrows(IllegalArgumentException.class, () -> {
            GrupoEstudo ge1 = new GrupoEstudo("");
        });
    }

    @Test
    public void testCriarGrupoDeEstudoComNomePreenchidoComEspacosEmBranco () {
        assertThrows(IllegalArgumentException.class, () -> {
            GrupoEstudo ge1 = new GrupoEstudo("     ");
        });
    }

    @Test
    public void testAlocarAlunoNullEmGrupoDeEstudo () {
        Aluno a1 = null;
        assertThrows(NullPointerException.class, () -> {
            this.grupoEstudo.alocaAluno(a1);
        });
    }

    @Test
    public void testAlocarAlunoEmGrupoDeEstudo () {
        Aluno a1 = new Aluno("Michael","Computação","1533");
        assertEquals("ALUNO ALOCADO", this.grupoEstudo.alocaAluno(a1));
    }

    @Test
    public void testAlocarMesmoAlunoDuasVezesNoGrupoDeEstudo () {
        Aluno a1 = new Aluno("Michael","Computação","1533");
        assertAll(
                () -> assertEquals("ALUNO ALOCADO", this.grupoEstudo.alocaAluno(a1)),
                () -> assertEquals("ALUNO ALOCADO", this.grupoEstudo.alocaAluno(a1))
        );
    }

    @Test
    public void testToStringGrupoDeEstudoSemAlunosAlocados () {
        assertEquals("Alunos do grupo Listas:\n", this.grupoEstudo.toString());
    }

    @Test
    public void testToStringGrupoDeEstudoComUmAlunoAlocado () {
        Aluno a1 = new Aluno("Michael","Computação","1533");
        this.grupoEstudo.alocaAluno(a1);
        String retornoEsperado =
                "Alunos do grupo Listas:\n" +
                "* 1533 - Michael - Computação";
        assertEquals(retornoEsperado, this.grupoEstudo.toString());
    }

    @Test
    public void testEqualsGrupoDeEstudoComObjetoNull () {
        GrupoEstudo ge1 = null;
        assertNotEquals(ge1, this.grupoEstudo);
    }

    @Test
    public void testEqualsGrupoDeEstudoComClasseDiferente () {
        String ge1 = "grupo de estudo 1";
        assertNotEquals(ge1, this.grupoEstudo);
    }

    @Test
    public void testEqualsGruposDeEstudoComNomeDiferente () {
        GrupoEstudo ge1 = new GrupoEstudo("Array");
        assertNotEquals(ge1, this.grupoEstudo);
    }

    @Test
    public void testEqualsGruposDeEstudoComNomeIgual () {
        GrupoEstudo ge1 = new GrupoEstudo("Listas");
        assertEquals(ge1, this.grupoEstudo);
    }

    @Test
    public void testHasCodeGruposDeEstudoComNomeIgual () {
        GrupoEstudo ge1 = new GrupoEstudo("Listas");
        assertEquals(ge1.hashCode(), this.grupoEstudo.hashCode());
    }
}