package lab2;

import java.util.Arrays;
import java.util.Locale;

/**
 * Representação de uma disciplina acadêmica.
 * Uma disciplina possui nome, notas e quantidade de horas de estudo.
 *
 * @author Pedro Manoel
 */
public class Disciplina {
    /**
     * Nome da disciplina
     */
    private String nome;

    /**
     * Quantidade de horas de estudo na disciplina.
     */
    private int horasEstudo;

    /**
     * Notas da disciplina.
     */
    private double[] notas;

    /**
     * Quantidade de notas da disciplina.
     */
    private int quantNotas;

    /**
     * Peso das notas da disciplina.
     */
    private int[] pesoNotas;

    /**
     * Constrói uma disciplina a partir do nome.
     * As horas de estudo, por padrão, é 0.
     * A quantidade de notas, por padrão, é 4.
     * O Array de notas, por padrão, possui 4 notas 0.
     * O peso das notas, por padrão, é null.
     *
     * @param nome nome da disciplina
     */
    public Disciplina (String nome) {
        this.nome = nome;
        this.horasEstudo = 0;
        this.quantNotas = 4;
        this.notas = new double[4];
        this.pesoNotas = null;
    }

    /**
     * Constrói uma disciplina a partir do nome e da quantidade de notas.
     *
     * @param nome nome da disciplina
     * @param quantNotas quantidade de notas da disciplina
     */
    public Disciplina (String nome, int quantNotas) {
        this(nome);
        this.quantNotas = quantNotas;
        this.notas = new double[quantNotas];
    }

    /**
     * Constrói uma disciplina a partir do nome, da quantidade de notas e
     * dos pesos das notas.
     *
     * @param nome nome da disciplina
     * @param quantNotas quantidade de notas da disciplina
     * @param pesoNotas peso das notas da disciplina
     */
    public Disciplina (String nome, int quantNotas, int[] pesoNotas) {
        this(nome, quantNotas);
        this.pesoNotas = pesoNotas;
    }

    /**
     * Calcula e retorna a média das notas da disciplina.
     * Se as notas tiverem pesos então a média é ponderada.
     * Se as notas não tiverem pesos então a média é aritmética.
     *
     * @return média das notas da disciplina
     */
    private double calculaMedia () {
        double media = 0;

        if (this.pesoNotas == null) {
            return Arrays.stream(this.notas).sum() / this.quantNotas; // Média ponderada
        }

        for (int i = 0; i < this.quantNotas; i++) {
            media += this.notas[i] * this.pesoNotas[i];
        }

        return media / Arrays.stream(this.pesoNotas).sum(); // Média aritmética
    }

    /**
     * Adiciona horas a quantidade de horas de estudo da disciplina.
     *
     * @param horas quantidade de horas
     */
    public void cadastraHoras (int horas) {
        this.horasEstudo += horas;
    }

    /**
     * Cadastra uma nota na disciplina.
     *
     * @param nota qual nota vai ser cadastrada
     * @param valorNota valor da nota
     */
    public void cadastraNota (int nota, double valorNota) {
        this.notas[nota-1] = valorNota;
    }

    /**
     * Retorna o resultado da seguinte verificação:
     * A média das notas é maior ou igual a 7.
     * Se a verificação for verdadeira então a disciplina esta, aprovada.
     *
     * @return disciplina aprovada ou reprovada
     */
    public boolean aprovado ( ) {
        return this.calculaMedia() >= 7.0;
    }

    /**
     * Retorna o nome da disciplina.
     *
     * @return nome da disciplina
     */
    public String getNome () { return nome; }

    /**
     * Retorna a String que representa a disciplina.
     * A representação segue o seguinte formato:
     * "Nome da disciplina Horas de estudo na disciplina Media na disciplina [Notas na disciplina]"
     *
     * @return representação da disciplina em String
     */
    @Override
    public String toString ( ) {
        return String.format(
                Locale.ENGLISH,
                "%s %d %s %s",
                this.nome,
                this.horasEstudo,
                this.calculaMedia(),
                Arrays.toString(this.notas)
        );
    }
}
