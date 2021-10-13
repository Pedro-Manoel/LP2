package controle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe responsável pelos testes unitários da classe ControleAlunos.
 *
 * @author Pedro Manoel
 */
class ControleAlunosTest {

    private ControleAlunos controleAlunos;

    @BeforeEach
    public void criaControleAlunos () {
        this.controleAlunos = new ControleAlunos();
    }

    @Test
    public void testCadastraAluno () {
        String retorno = this.controleAlunos.cadastraAluno("Michael","Computação","1533");
        assertEquals(retorno, "CADASTRO REALIZADO");
    }

    @Test
    public void testCadastraMesmoAlunoDuasVezes () {
        this.controleAlunos.cadastraAluno("Michael","Computação","1533");
        String retorno = this.controleAlunos.cadastraAluno("Michael","Computação","1533");
        assertEquals(retorno, "MATRÍCULA JÁ CADASTRADA");
    }

    @Test
    public void testConsultaAlunoComAlunoCadastrado () {
        this.controleAlunos.cadastraAluno("Michael","Computação","1533");
        assertEquals("Aluno: 1533 - Michael - Computação", this.controleAlunos.consultaAluno("1533"));
    }

    @Test
    public void testConsultaAlunoSemAlunoCadastrado () {
        assertEquals("ALUNO NÃO CADASTRADO", this.controleAlunos.consultaAluno("1533"));
    }

    @Test
    public void testCadastraGrupoDeEstudo () {
        String retorno = this.controleAlunos.cadastraGrupoEstudo("Listas");
        assertEquals(retorno, "CADASTRO REALIZADO");
    }

    @Test
    public void testCadastraGrupoDeEstudoComGrupoDeEstudoJaCadastrado () {
        this.controleAlunos.cadastraGrupoEstudo("Listas");
        String retorno = this.controleAlunos.cadastraGrupoEstudo("Listas");
        assertEquals(retorno, "GRUPO JÁ CADASTRADO");
    }

    @Test
    public void testAlocarAlunoEmGrupoDeEstudoComAlunoNaoCadastradoEGrupoNaoCadastrado () {
        assertEquals("ALUNO NÃO CADASTRADO", this.controleAlunos.alocaAlunoEmGrupoEstudo("1533","Listas"));
    }

    @Test
    public void testAlocarAlunoEmGrupoDeEstudoComAlunoNaoCadastradoEGrupoCadastrado () {
        this.controleAlunos.cadastraGrupoEstudo("Listas");
        assertEquals("ALUNO NÃO CADASTRADO", this.controleAlunos.alocaAlunoEmGrupoEstudo("1533","Listas"));
    }

    @Test
    public void testAlocarAlunoEmGrupoDeEstudoComAlunoCadastradoEGrupoNaoCadastrado () {
        this.controleAlunos.cadastraAluno("Michael","Computação","1533");
        assertEquals("GRUPO NÃO CADASTRADO", this.controleAlunos.alocaAlunoEmGrupoEstudo("1533","Listas"));
    }

    @Test
    public void testAlocarAlunoEmGrupoDeEstudoComAlunoCadastradoEGrupoCadastrado () {
        this.controleAlunos.cadastraGrupoEstudo("Listas");
        this.controleAlunos.cadastraAluno("Michael","Computação","1533");
        assertEquals("ALUNO ALOCADO", this.controleAlunos.alocaAlunoEmGrupoEstudo("1533","Listas"));
    }

    @Test
    public void testConsultaGrupoDeEstudoSemGrupoDeEstudoCadastrado () {
        assertEquals("GRUPO NÃO CADASTRADO", this.controleAlunos.consultaGrupoEstudo("Listas"));
    }

    @Test
    public void testConsultaGrupoDeEstudoComGrupoDeEstudoCadastradoMasSemAlunosCadastradosNoGrupo () {
        this.controleAlunos.cadastraGrupoEstudo("Listas");
        assertEquals("Alunos do grupo Listas:\n", this.controleAlunos.consultaGrupoEstudo("Listas"));
    }

    @Test
    public void testConsultaGrupoDeEstudoComGrupoDeEstudoCadastradoEAlunosCadastradosNoGrupo () {
        this.controleAlunos.cadastraGrupoEstudo("Listas");
        this.controleAlunos.cadastraAluno("Michael","Computação","1533");
        this.controleAlunos.alocaAlunoEmGrupoEstudo("1533","listas");
        String retornoEsperado =
                "Alunos do grupo Listas:\n" +
                "* 1533 - Michael - Computação";
        assertEquals(retornoEsperado, this.controleAlunos.consultaGrupoEstudo("Listas"));
    }

    @Test
    public void testRegistrarAlunoQueRespondeQuestoesNoQuadroComAlunoNaoCadastrado () {
        assertEquals("ALUNO NÃO CADASTRADO", this.controleAlunos.registraAlunoRespondeQuestoesQuadro("1533"));
    }

    @Test
    public void testRegistrarAlunoQueRespondeQuestoesNoQuadroComAlunoCadastrado () {
        this.controleAlunos.cadastraAluno("Michael","Computação","1533");
        assertEquals("ALUNO REGISTRADO", this.controleAlunos.registraAlunoRespondeQuestoesQuadro("1533"));
    }

    @Test
        public void testListarAlunosQueRespondeQuestoesNoQuadroSemAlunosRegistrados () {
        assertEquals("Alunos:", this.controleAlunos.listaAlunosRespondeQuestoesQuadro());
    }

    @Test
    public void testListarAlunosQueRespondeQuestoesNoQuadroComAlunosRegistrados () {
        this.controleAlunos.cadastraAluno("Michael","Computação","1533");
        this.controleAlunos.cadastraAluno("Lincoln", "Medicina", "2411");
        this.controleAlunos.cadastraAluno("Sara", "Medicina", "3200");

        this.controleAlunos.registraAlunoRespondeQuestoesQuadro("3200");
        this.controleAlunos.registraAlunoRespondeQuestoesQuadro("2411");
        this.controleAlunos.registraAlunoRespondeQuestoesQuadro("1533");

        String retornoEsperado =
                "Alunos:\n" +
                "1. 3200 - Sara - Medicina\n" +
                "2. 2411 - Lincoln - Medicina\n" +
                "3. 1533 - Michael - Computação";
        assertEquals(retornoEsperado, this.controleAlunos.listaAlunosRespondeQuestoesQuadro());
    }
}