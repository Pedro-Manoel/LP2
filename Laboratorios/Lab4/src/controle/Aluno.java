package controle;

import java.util.Objects;

/**
 * Representação de um aluno.
 * Cada aluno tem um nome, um curso, e uma matrícula.
 *
 * @author Pedro Manoel
 */
public class Aluno {

    /**
     * Nome do aluno.
     */
    private String nome;

    /**
     * Curso do aluno.
     */
    private String curso;

    /**
     * Matrícula do aluno.
     */
    private String matricula;

    /**
     * Constrói um aluno a partir do nome, do curso, e da matrícula.
     *
     * @param nome nome do aluno
     * @param curso curso do aluno
     * @param matricula matrícula do aluno
     */
    public Aluno (String nome, String curso, String matricula) {
        if (nome == null || curso == null || matricula == null) {
            throw new NullPointerException("Parâmetro nulo");
        } else if (nome.trim().equals("") || curso.trim().equals("") || matricula.trim().equals("")) {
            throw new IllegalArgumentException("Parâmetro inválido");
        }

        this.nome = nome;
        this.curso = curso;
        this.matricula = matricula;
    }

    /**
     * Retorna a String que representa o aluno.
     * A representação segue o seguinte formato: "Matricula - Nome - Curso".
     *
     * @return representação do aluno em String
     */
    @Override
    public String toString () {
        return String.format(
                "%s - %s - %s",
                this.matricula,
                this.nome,
                this.curso
        );
    }

    /**
     * Verifica se o objeto, passado como parâmetro, é igual a
     * aluno, e retorna o resultado dessa verificação.
     *
     * @param obj objeto a comparar com o aluno
     * @return valor booleano referente ao resultado da verificação
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Aluno)) return false;

        Aluno aluno = (Aluno) obj;

        return this.matricula.equals(aluno.matricula);
    }

    /**
     * Retorna um valor numérico que representa unicamente o aluno.
     *
     * @return valor numérico que representa unicamente o aluno
     */
    @Override
    public int hashCode() { return Objects.hash(this.matricula); }
}
