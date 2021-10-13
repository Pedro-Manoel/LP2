package saga.cliente;

import saga.util.Consistencia;

import java.util.Objects;

/**
 * Representação de um cliente.
 * Cada cliente tem um cpf, um nome, um email, e uma localização.
 *
 * @author Pedro Manoel
 */
public class Cliente implements Comparable<Cliente> {

    /**
     * Cpf do cliente.
     */
    private String cpf;

    /**
     * Nome do cliente.
     */
    private String nome;

    /**
     * E-mail do cliente.
     */
    private String email;

    /**
     * Localização do cliente.
     */
    private String localizacao;

    /**
     * Contrói um cliente a partir do cpf, do nome, do email, e da localização.
     *
     * @param cpf cpf do cliente
     * @param nome nome do cliente
     * @param email e-mail do cliente
     * @param localizacao localização do cliente
     */
    public Cliente (String cpf, String nome, String email, String localizacao) {
        Consistencia.consisteNaoNuloNaoVazio(cpf, "cpf");
        Consistencia.consisteCpf(cpf);
        Consistencia.consisteNaoNuloNaoVazio(nome, "nome");
        Consistencia.consisteNaoNuloNaoVazio(email, "email");
        Consistencia.consisteNaoNuloNaoVazio(localizacao,"localizacao", false);

        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.localizacao = localizacao;
    }

    /**
     * Retorna o cpf do cliente.
     *
     * @return cpf do cliente
     */
    public String getCpf () { return cpf; }

    /**
     * Altera o nome do cliente.
     *
     * @param nome novo nome do cliente
     */
    public void setNome (String nome) {
        Consistencia.consisteNaoNuloNaoVazio(nome, "nome");

        this.nome = nome;
    }

    /**
     * Retorna o nome do cliente.
     *
     * @return nome do cliente
     */
    public String getNome () { return this.nome; }

    /**
     * Altera o e-mail do cliente.
     *
     * @param email novo e-mail do cliente
     */
    public void setEmail (String email) {
        Consistencia.consisteNaoNuloNaoVazio(email, "email");

        this.email = email;
    }

    /**
     * Altera a localização do cliente.
     *
     * @param localizacao nova localização do cliente
     */
    public void setLocalizacao (String localizacao) {
        Consistencia.consisteNaoNuloNaoVazio(localizacao, "localizacao", false);

        this.localizacao = localizacao;
    }

    /**
     * Retorna o resultado da comparação do cliente
     * com outro cliente.
     *
     * @param outroCliente o outro cliente que sera usado para a comparação
     * @return resultado da comparação
     */
    @Override
    public int compareTo (Cliente outroCliente) {
        return this.toString().compareTo(outroCliente.toString());
    }

    /**
     * Retorna a String que representa o cliente.
     * A representação segue o seguinte formato: "Nome - Localizacao - Email".
     *
     * @return representação do cliente em String
     */
    @Override
    public String toString () {
        return String.format(
                "%s - %s - %s",
                this.nome,
                this.localizacao,
                this.email
        );
    }

    /**
     * Verifica se o objeto, passado como parâmetro, é igual ao
     * cliente, e retorna o resultado dessa verificação.
     *
     * @param obj objeto a comparar com o cliente
     * @return valor booleano referente ao resultado da verificação
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cliente)) return false;

        Cliente cliente = (Cliente) obj;

        return this.cpf.equals(cliente.cpf);
    }

    /**
     * Retorna um valor numérico que representa unicamente o cliente.
     *
     * @return valor numérico que representa unicamente o cliente
     */
    @Override
    public int hashCode() { return Objects.hash(this.cpf); }
}
