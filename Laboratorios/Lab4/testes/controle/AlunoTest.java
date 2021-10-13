package controle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe responsável pelos testes unitários da classe Aluno.
 *
 * @author Pedro Manoel
 */
class AlunoTest {

    private Aluno aluno;

    @BeforeEach
    public void criaAluno () {
        this.aluno = new Aluno("Michael","Computação","1533");
    }

    @Test
    public void testCriarAlunoComNomeNull () {
        assertThrows(NullPointerException.class, () -> {
            Aluno a1 = new Aluno(null, "Computação", "1533");
        });
    }

    @Test
    public void testCriarAlunoComNomeVazio () {
        assertThrows(IllegalArgumentException.class, () -> {
            Aluno a1 = new Aluno("", "Computação", "1533");
        });
    }

    @Test
    public void testCriarAlunoComNomePreenchidoComEspacosEmBranco () {
        assertThrows(IllegalArgumentException.class, () -> {
            Aluno a1 = new Aluno("     ", "Computação", "1533");
        });
    }

    @Test
    public void testCriarAlunoComCursoNull () {
        assertThrows(NullPointerException.class, () -> {
            Aluno a1 = new Aluno("Michael", null, "1533");
        });
    }

    @Test
    public void testCriarAlunoComCursoVazio () {
        assertThrows(IllegalArgumentException.class, () -> {
            Aluno a1 = new Aluno("Michael", "", "1533");
        });
    }

    @Test
    public void testCriarAlunoComCursoPreenchidoComEspacosEmBranco () {
        assertThrows(IllegalArgumentException.class, () -> {
            Aluno a1 = new Aluno("Michael", "     ", "1533");
        });
    }

    @Test
    public void testCriarAlunoComMatriculaNull () {
        assertThrows(NullPointerException.class, () -> {
            Aluno a1 = new Aluno("Michael", null, "1533");
        });
    }

    @Test
    public void testCriarAlunoComMatriculaVazia () {
        assertThrows(IllegalArgumentException.class, () -> {
            Aluno a1 = new Aluno("Michael", "Computação", "");
        });
    }

    @Test
    public void testCriarAlunoComMatriculaPreenchidaComEspacosEmBranco () {
        assertThrows(IllegalArgumentException.class, () -> {
            Aluno a1 = new Aluno("Michael", "Computação", "     ");
        });
    }

    @Test
    public void testToStringAluno () {
        assertEquals("1533 - Michael - Computação", this.aluno.toString());
    }

    @Test
    public void testEqualsAlunoComObjetoNull () {
        Aluno a1 = null;
        assertNotEquals(a1, this.aluno);
    }

    @Test
    public void testEqualsAlunoComClasseDiferente () {
        String a1 = "aluno 1";
        assertNotEquals(a1, this.aluno);
    }

    @Test
    public void testEqualsAlunosComMatriculaDiferente () {
        Aluno a1 = new Aluno("Lincoln", "Medicina", "2411");
        assertNotEquals(a1, this.aluno);
    }

    @Test
    public void testEqualsAlunosComMatriculaIgual () {
        Aluno a1 = new Aluno("Lincoln", "Medicina", "1533");
        assertEquals(a1, this.aluno);
    }

    @Test
    public void testHasCodeAlunosComMatriculaIgual () {
        Aluno a1 = new Aluno("Lincoln", "Medicina", "1533");
        assertEquals(a1.hashCode(), this.aluno.hashCode());
    }
}