package agenda;

import java.util.Objects;

/**
 * Representação de um contato.
 * Um contato é representado por um nome, sobrenome, até 3 telefones,
 * sendo 1 principal e 1 sendo o contato via Whatsapp.
 *
 * @author Pedro Manoel
 */
public class Contato {
    /**
     * Constante que representa o número máximo de telefones de cada contato.
     */
    public static final int NUM_MAX_TELEFONES = 3;

    /**
     * Nome do contato.
     */
    private String nome;

    /**
     * Sobrenome do contato.
     */
    private String sobrenome;

    /**
     * Telefones do contato.
     */
    private String[] telefones;

    /**
     * Posição do telefone prioritário na lista de telefones do contato.
     */
    private int posicaoTelefonePrioritario;

    /**
     * Posição do telefone de Whatsapp na lista de telefones do contato.
     */
    private int posicaoTelefoneWhatsapp;

    /**
     * Constrói um contato a partir do nome e sobrenome, da lista de telefones
     * e da posição do telefone prioritário e de Whatsapp.
     *
     * @param nome nome do contato
     * @param sobrenome sobrenome do contato
     * @param telefones lista de telefones do contato
     * @param posicaoTelefonePrioritario posição do telefone prioritário na lista de telefones
     * @param posicaoTelefoneWhatsapp posição do telefone de Whatsapp na lista de telefones
     */
    public Contato (String nome, String sobrenome, String[] telefones, int posicaoTelefonePrioritario, int posicaoTelefoneWhatsapp) {
        if (nome == null || sobrenome == null || telefones == null) {
            throw new NullPointerException("Parâmetro nulo");
        } else if (nome.trim().equals("") || sobrenome.trim().equals("")) {
            throw  new IllegalArgumentException("Parâmetro inválido");
        }

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefones = telefones;
        this.posicaoTelefonePrioritario = posicaoTelefonePrioritario-1;
        this.posicaoTelefoneWhatsapp = posicaoTelefoneWhatsapp-1;
    }

    /**
     * Verifica se existe algum telefone na posição passada como parâmetro
     * e retorna o resultado desta verificação.
     *
     * @param posicao posição do telefone
     * @return valor booleano referente ao resultado da verificação
     */
    private boolean existeTelefone (int posicao) {
        if (posicao < 0  || posicao >= NUM_MAX_TELEFONES) return false;
        return !this.telefones[posicao].equals("");
    }

    /**
     * Retorna a String que representa a lista de telefones.
     * Cada telefone é representado em uma linha da String.
     * A representação do telefone segue o seguinte formato:
     * "Telefone (Tipo de telefone)".
     *
     * @return representação da lista de telefones em String
     */
    private String listaTelefones () {
        StringBuilder listaTelefones = new StringBuilder();

        for (int i = 0; i < NUM_MAX_TELEFONES; i++) {
            if (this.existeTelefone(i)) {
                listaTelefones.append(this.telefones[i]);
                if (i == this.posicaoTelefonePrioritario) listaTelefones.append(" (prioritário)");
                if (i == this.posicaoTelefoneWhatsapp) listaTelefones.append(" (zap)");
                listaTelefones.append("\n");
            }
        }

        return listaTelefones.toString().trim();
    }

    /**
     * Retorna a String que representa os detalhes do contato.
     * A representação segue o seguinte formato:
     * "Nome Sobrenome
     *  Lista de telefones".
     *
     * @return representação dos detalhes do contato em String
     */
    public String detalhes () {
        return String.format(
                "%s %s\n%s",
                this.nome,
                this.sobrenome,
                this.listaTelefones()
        );
    }

    /**
     * Retorna, se existir, o telefone prioritário do contato.
     *
     * @return telefone prioritário do contato
     */
    public String getTelefonePrioritario () {
        return this.existeTelefone(this.posicaoTelefonePrioritario)
                ? this.telefones[this.posicaoTelefonePrioritario]
                : "Não tem";
    }

    /**
     * Retorna, se existir, o telefone de Whatsapp do contato.
     *
     * @return telefone de Whatsapp do contato
     */
    public String getTelefoneWhatsapp () {
        return this.existeTelefone(this.posicaoTelefoneWhatsapp)
                ? this.telefones[this.posicaoTelefoneWhatsapp]
                : "Não tem";
    }

    /**
     * Retorna a String que representa o contato.
     * A representação segue o seguinte formato: "Nome Sobrenome"
     *
     * @return representação do contato em String
     */
    @Override
    public String toString () {
        return String.format("%s %s", this.nome, this.sobrenome);
    }

    /**
     * Verifica se o objeto passado como parâmetro é igual ao
     * contato, e retorna o resultado dessa verificação.
     *
     * @param obj objeto a comparar com contato
     * @return valor booleano referente ao resultado da verificação
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Contato)) return false;

        Contato contato = (Contato) obj;
        return this.nome.equals(contato.nome) && this.sobrenome.equals(contato.sobrenome);
    }

    /**
     * Retorna um valor numérico que representa unicamente o contato.
     *
     * @return valor numérico que representa unicamente o contato
     */
    @Override
    public int hashCode () {
        return Objects.hash(this.nome, this.sobrenome);
    }
}
