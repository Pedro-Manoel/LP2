package agenda;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Representação de uma agenda de contatos.
 * A agenda de contatos pode ter no máximo até 100 contatos.
 *
 * @author Pedro Manoel
 */
public class Agenda {

    /**
     * Constante que representa o número máximo de contatos de cada agenda.
     */
    public static final int NUM_MAX_CONTATOS = 100;

    /**
     * Lista de contatos da agenda.
     */
    private Contato[] contatos;

    /**
     * Posições dos contatos cadastrados na agenda.
     */
    private ArrayList<Integer> posicoesContatosCadastrados;

    /**
     * Contrói uma Agenda.
     */
    public Agenda () {
        this.contatos = new Contato[NUM_MAX_CONTATOS + 1];
        this.posicoesContatosCadastrados = new ArrayList<>();
    }

    /**
     * Realiza o cadastro de um contato na agenda.
     *
     * @param posicao posição do contato
     * @param nome nome do contato
     * @param sobrenome sobrenome do contato
     * @param telefones lista de telefones do contato
     * @param posicaoTelefonePrioritario posição do telefone prioritário na lista de telefones
     * @param posicaoTelefoneWhatsapp posição do telefone de Whatsapp na lista de telefones
     * @return resultado do cadastro em String
     */
    public String cadastraContato (int posicao, String nome, String sobrenome, String[] telefones, int posicaoTelefonePrioritario, int posicaoTelefoneWhatsapp) {
        if (posicao >= 1 && posicao <= NUM_MAX_CONTATOS) {
            this.contatos[posicao] = new Contato(nome, sobrenome, telefones,posicaoTelefonePrioritario,posicaoTelefoneWhatsapp);

            if (!this.existeContato(posicao)) this.posicoesContatosCadastrados.add(posicao);

            return "CADASTRO REALIZADO";
        }

        return "POSIÇÃO INVÁLIDA!";
    }

    /**
     * Verifica se existe algum contato na posição passada como parâmetro
     * e retorna o resultado desta verificação.
     *
     * @param posicao posição do contato
     * @return valor booleano referente ao resultado da verificação
     */
    public boolean existeContato (int posicao) { return this.posicoesContatosCadastrados.contains(posicao); }

    /**
     * Retorna a String que representa os detalhes do contato,
     * que está na posição passada como parâmetro, na lista de contatos da agenda.
     *
     * @param posicao posição do contato na lista de contatos da agenda
     * @return detalhes do contato em String
     */
    public String detalhesContato (int posicao) {
        return this.existeContato(posicao)
                ? this.contatos[posicao].detalhes()
                : "POSIÇÃO INVÁLIDA!";
    }

    /**
     * Retorna a String que representa a lista de contatos da agenda.
     * Cada contato é representado em uma linha da String.
     * A representação do contato segue o seguinte formato:
     * "Posicao contato - Nome contato Sobrenome contato"
     *
     * @return representação da lista de contatos em String
     */
    public String listaContatos  () {
        StringBuilder listaContatos = new StringBuilder();

        for (int posicaoContato : this.posicoesContatosCadastrados) {
            listaContatos
                    .append(posicaoContato)
                    .append(" - ")
                    .append(this.contatos[posicaoContato].toString())
                    .append("\n");
        }

        return listaContatos.toString().trim();
    }

    /**
     * Retorna a String que representa a lista de contatos da agenda com
     * o telefone prioritário de cada contato.
     * Cada contato é representado em uma linha da String.
     * A representação do contato segue o seguinte formato:
     * "Nome contato Sobrenome contato - Telefone prioritário contato"
     *
     * @return representação da lista de contatos com telefone prioritário em String
     */
    public String listaContatosTelefonePrioritario () {

        StringBuilder listaContatosTelefonePrioritario = new StringBuilder();

        for (int posicaoContato : this.posicoesContatosCadastrados) {
            listaContatosTelefonePrioritario
                    .append(this.contatos[posicaoContato].toString())
                    .append(" - ")
                    .append(this.contatos[posicaoContato].getTelefonePrioritario())
                    .append("\n");
        }

        return listaContatosTelefonePrioritario.toString().trim();
    }

    /**
     * Retorna a String que representa a lista de contatos da agenda com
     * o telefone de Whatsapp de cada contato.
     * Cada contato é representado em uma linha da String.
     * A representação do contato segue o seguinte formato:
     * "Nome contato Sobrenome contato - Telefone whatsapp"
     *
     * @return representação da lista de contatos com o telefone de Whatsapp em String
     */
    public String listaContatosTelefoneWhatsapp () {

        StringBuilder listaContatosTelefoneWhatsapp = new StringBuilder();

        for (int posicaoContato : this.posicoesContatosCadastrados) {
            listaContatosTelefoneWhatsapp
                    .append(this.contatos[posicaoContato].toString())
                    .append(" - ")
                    .append(this.contatos[posicaoContato].getTelefoneWhatsapp())
                    .append("\n");
        }

        return listaContatosTelefoneWhatsapp.toString().trim();
    }

    /**
     * Verifica se o objeto passado como parâmetro é igual a
     * agenda e retorna o resultado dessa verificação.
     *
     * @param obj objeto a comparar com a agenda
     * @return valor booleano referente ao resultado da verificação
     */
    @Override
    public boolean equals (Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Agenda)) return false;

        Agenda agenda = (Agenda) obj;
        return Arrays.equals(this.contatos, agenda.contatos);
    }

    /**
     * Retorna um valor númerico que representa unicamente a agenda.
     *
     * @return valor numérico que representa unicamente a agenda
     */
    @Override
    public int hashCode () { return Arrays.hashCode(this.contatos); }
}
