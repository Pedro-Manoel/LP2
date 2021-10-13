package controle;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representação de um sistema de controle de alunos.
 * O sistema de controle de alunos é responsavel por gerenciar um
 * conjunto de alunos e um conjunto de grupos de estudo, e manter
 * um registro de alunos que respondem questões no quadro.
 *
 * @author Pedro Manoel
 */
public class ControleAlunos {

    /**
     * Conjunto de alunos do sistema.
     */
    private HashMap<String, Aluno> alunos;

    /**
     * Conjunto de grupos de estudo do sistema.
     */
    private HashMap<String, GrupoEstudo> gruposEstudo;

    /**
     * Registro dos alunos que respondem questões no quadro.
     */
    private ArrayList<String> alunosRespondemQuestoesQuadro;

    /**
     * Contrói um sistema de controle de alunos.
     */
    public ControleAlunos () {
        this.alunos = new HashMap<>();
        this.gruposEstudo = new HashMap<>();
        this.alunosRespondemQuestoesQuadro = new ArrayList<>();
    }

    /**
     * Verifica se o aluno existe no conjunto de alunos do sistema,
     * e retorna o resultado desta verificação.
     *
     * @param matricula matrícula do aluno
     * @return valor booleano referente ao resultado da verificação
     */
    private boolean existeAluno (String matricula) {
        return this.alunos.containsKey(matricula);
    }

    /**
     * Verifica se o grupo de estudo existe no conjunto de
     * grupos de estudo do sistema, e retorna o resultado desta verificação.
     *
     * @param nome nome do grupo de estudo
     * @return valor booleano referente ao resultado da verificação
     */
    private boolean existeGrupoEstudo (String nome) {
        return this.gruposEstudo.containsKey(nome.toUpperCase());
    }

    /**
     * Cadastra um aluno no sistema.
     *
     * @param nome nome do aluno
     * @param curso curso do aluno
     * @param matricula matrícula do aluno
     * @return resultado da tentativa de cadastro do aluno
     */
    public String cadastraAluno (String nome, String curso, String matricula) {
        if (this.existeAluno(matricula)) return "MATRÍCULA JÁ CADASTRADA";

        this.alunos.put(matricula, new Aluno(nome, curso, matricula));

        return "CADASTRO REALIZADO";
    }

    /**
     * Retorna a representação do aluno em String
     *
     * @param matricula matrícula do aluno
     * @return representação do aluno em String
     */
    public String consultaAluno (String matricula) {
        if (!this.existeAluno(matricula)) return "ALUNO NÃO CADASTRADO";

        return String.format("Aluno: %s", this.alunos.get(matricula).toString());
    }

    /**
     * Cadastra um grupo de estudo no sistema.
     *
     * @param nome nome do grupo de estudo
     * @return resultado da tentativa de cadastro do grupo de estudo
     */
    public String cadastraGrupoEstudo (String nome) {
        if (this.existeGrupoEstudo(nome)) return "GRUPO JÁ CADASTRADO";

        this.gruposEstudo.put(nome.toUpperCase(), new GrupoEstudo(nome));

        return "CADASTRO REALIZADO";
    }

    /**
     * Aloca um aluno em um grupo de estudo no sistema.
     *
     * @param matriculaAluno matrícula do aluno
     * @param nomeGrupoEstudo nome do grupo de estudo
     * @return resultado da tentativa de alocação do aluno
     */
    public String alocaAlunoEmGrupoEstudo (String matriculaAluno, String nomeGrupoEstudo) {
        if (!this.existeAluno(matriculaAluno)) return "ALUNO NÃO CADASTRADO";
        if (!this.existeGrupoEstudo(nomeGrupoEstudo)) return "GRUPO NÃO CADASTRADO";

        return this.gruposEstudo.get(nomeGrupoEstudo.toUpperCase()).alocaAluno(this.alunos.get(matriculaAluno));
    }

    /**
     * Retorna a representação do grupo de estudo em String
     *
     * @param nome nome do grupo de estudo
     * @return representação do grupo de estudo em String
     */
    public String consultaGrupoEstudo (String nome) {
        if (!this.existeGrupoEstudo(nome)) return "GRUPO NÃO CADASTRADO";

        return this.gruposEstudo.get(nome.toUpperCase()).toString();
    }

    /**
     * Registra um aluno, que responde as questões no quadro, no sistema.
     *
     * @param matricula matrícula do aluno
     * @return resultado da tentativa de registro do aluno
     */
    public String registraAlunoRespondeQuestoesQuadro (String matricula) {
        if (!this.existeAluno(matricula)) return "ALUNO NÃO CADASTRADO";

        this.alunosRespondemQuestoesQuadro.add(matricula);

        return "ALUNO REGISTRADO";
    }

    /**
     * Retorna uma String que representa a lista de alunos
     * que respondem as questões no quadro.
     * Cada aluno é representado da seguinte forma: "Posicao registro - Aluno".
     * A partir da primeira linha da String cada linha seguinte representa um aluno.
     * A representação segue o seguinte formato:
     * "'Alunos:'
     *  ListaAlunos
     * "
     *
     * @return representação da lista de alunos, que respondem as questões no quadro, em String
     */
    public String listaAlunosRespondeQuestoesQuadro () {
        StringBuilder listaAlunos = new StringBuilder("Alunos:\n");

        for (int i = 0; i < this.alunosRespondemQuestoesQuadro.size(); i++) {
            listaAlunos
                    .append(i + 1)
                    .append(". ")
                    .append(this.alunos.get(this.alunosRespondemQuestoesQuadro.get(i)).toString())
                    .append("\n");
        }

        return listaAlunos.toString().trim();
    }
}
