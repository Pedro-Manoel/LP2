package controle;

import java.util.HashSet;
import java.util.Objects;

/**
 * Representação de um grupo de estudo.
 * Cada grupo de estudo tem um nome e um conjunto de alunos.
 *
 * @author Pedro Manoel
 */
public class GrupoEstudo {

    /**
     * Nome do grupo de estudo.
     */
    private String nome;

    /**
     * Conjunto de alunos do grupo de estudo.
     */
    private HashSet<Aluno> alunos;

    /**
     * Constrói um grupo de estudo a partir do nome.
     *
     * @param nome nome do grupo de estudo
     */
    public GrupoEstudo (String nome) {
        if (nome == null) throw new NullPointerException("Parâmetro nulo");
        else if (nome.trim().equals("")) throw new IllegalArgumentException("Parâmetro inválido");

        this.nome = nome;
        this.alunos = new HashSet<>();
    }

    /**
     * Aloca um aluno no grupo de estudo.
     *
     * @param aluno aluno a ser alocado no grupo de estudo
     */
    public String alocaAluno (Aluno aluno) {
        if (aluno == null) throw new NullPointerException("Parâmetro nulo");

        this.alunos.add(aluno);

        return "ALUNO ALOCADO";
    }

    /**
     * Retorna a String que representa a lista de alunos do grupo de estudo.
     * Cada aluno é representado em uma linha da String.
     * A representação do aluno segue o seguinte formato: "'*' Aluno"
     *
     * @return lista de alunos do grupo de estudo em String
     */
    private String listaAlunos () {
        StringBuilder listaAlunos = new StringBuilder();

        for (Aluno aluno : this.alunos) {
            listaAlunos
                    .append("* ")
                    .append(aluno.toString())
                    .append("\n");
        }

        return listaAlunos.toString().trim();
    }

    /**
     * Retorna a String que representa o grupo de estudo.
     * A representação segue o seguinte formato:
     * "'Alunos do grupo' Nome
     *  ListaAlunos
     * "
     *
     * @return representação do grupo de estudo em String
     */
    @Override
    public String toString() {
        return String.format(
                "Alunos do grupo %s:\n%s",
                this.nome,
                this.listaAlunos()
        );
    }

    /**
     * Verifica se o objeto, passado como parâmetro, é igual ao
     * grupo de estudo, e retorna o resultado dessa verificação.
     *
     * @param obj objeto a comparar com o grupo de estudo
     * @return valor booleano referente ao resultado da verificação
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof GrupoEstudo)) return false;

        GrupoEstudo grupoEstudo = (GrupoEstudo) obj;

        return this.nome.equals(grupoEstudo.nome);
    }

    /**
     * Retorna um valor numérico que representa unicamente o grupo de estudo.
     *
     * @return valor numérico que representa unicamente o grupo de estudo
     */
    @Override
    public int hashCode() { return Objects.hash(this.nome); }
}
