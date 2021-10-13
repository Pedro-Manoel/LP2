package saga.cliente;

import saga.util.Consistencia;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Representação de um Controller de Clientes.
 *
 * @author Pedro Manoel
 */
public class ClienteController {

    /**
     * Conjunto de clientes do controller.
     */
    private Map<String, Cliente> clientes;

    /**
     * Contrói um controller de clientes.
     */
    public ClienteController () {
        this.clientes = new HashMap<>();
    }

    /**
     * Verifica se o cliente existe e retorna o resultado desta verificação.
     *
     * @param cpf cpf do cliente
     * @return valor booleano referente ao resultado da verificação
     */
    private boolean existeCliente (String cpf) {
        return this.clientes.containsKey(cpf);
    }

    /**
     * Faz a validação da existência de um cliente no controller.
     *
     * @param cpf cpf do cliente
     * @param clienteDeveExistir se o cliente deve existir no controller
     */
    public void validaClienteExiste (String cpf, boolean clienteDeveExistir) {
        if (this.existeCliente(cpf) && !clienteDeveExistir) {
            throw new IllegalArgumentException("cliente ja existe.");
        }
        if (!this.existeCliente(cpf) && clienteDeveExistir) {
            throw new IllegalArgumentException("cliente nao existe.");
        }
    }

    /**
     * Cadastra um cliente no controller.
     *
     * @param cpf cpf do cliente
     * @param nome nome do cliente
     * @param email e-mail do cliente
     * @param localizacao localização do cliente
     * @return cpf do cliente
     */
    public String cadastraCliente (String cpf, String nome, String email, String localizacao) {
        try {
            this.validaClienteExiste(cpf, false);

            this.clientes.put(cpf, new Cliente(cpf, nome, email, localizacao));

            return cpf;
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException ("Erro no cadastro do cliente: ".concat(exception.getMessage()));
        }
    }

    /**
     * Retorna a representação em String do cliente.
     *
     * @param cpf cpf do cliente
     * @return representação em String do cliente
     */
    public String retornaCliente (String cpf) {
        try {
            Consistencia.consisteNaoNuloNaoVazio(cpf, "cpf");
            Consistencia.consisteCpf(cpf);
            this.validaClienteExiste(cpf, true);

            return this.clientes.get(cpf).toString();
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Erro na exibicao do cliente: ".concat(exception.getMessage()));
        }
    }

    /**
     * Retorna uma String que representa a lista de clientes do controller.
     * A representação segue o seguinte formato: "Cliente1 | Cliente2 | Cliente3 ..."
     *
     * @return representação da lista de clientes do controller em String
     */
    public String retornaClientes () {
        return this.clientes
                .values()
                .stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(" | "));
    }

    /**
     * Edita os atributos do cliente.
     *
     * @param cpf cpf do cliente
     * @param atributo atributo a ser editado
     * @param novoValor novo valor do atributo
     */
    public void editaCliente (String cpf, String atributo, String novoValor) {
        try {
            Consistencia.consisteNaoNuloNaoVazio(cpf, "cpf");
            Consistencia.consisteCpf(cpf);
            this.validaClienteExiste(cpf,true);
            Consistencia.consisteNaoNuloNaoVazio(atributo, "atributo");
            Consistencia.consisteNaoNuloNaoVazio(novoValor, "novo valor");

            switch (atributo) {
                case "cpf":
                    throw new IllegalArgumentException("cpf nao pode ser editado.");
                case "nome":
                    this.clientes.get(cpf).setNome(novoValor);
                    break;
                case "email":
                    this.clientes.get(cpf).setEmail(novoValor);
                    break;
                case "localizacao":
                    this.clientes.get(cpf).setLocalizacao(novoValor);
                    break;
                default:
                    throw new IllegalArgumentException("atributo nao existe.");
            }
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Erro na edicao do cliente: ".concat(exception.getMessage()));
        }
    }

    /**
     * Remove um cliente do controller.
     *
     * @param cpf cpf do cliente
     */
    public void removeCliente (String cpf) {
        try {
            Consistencia.consisteNaoNuloNaoVazio(cpf, "cpf", true, false);
            this.validaClienteExiste(cpf, true);
            Consistencia.consisteCpf(cpf);

            this.clientes.remove(cpf);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Erro na remocao do cliente: ".concat(exception.getMessage()));
        }
    }

    /**
     * Retorna o nome do cliente.
     *
     * @param cpf cpf do cliente
     * @return nome do cliente
     */
    public String getNomeCliente (String cpf) {
        Consistencia.consisteNaoNuloNaoVazio(cpf, "cpf");
        Consistencia.consisteCpf(cpf);
        this.validaClienteExiste(cpf, true);

        return this.clientes.get(cpf).getNome();
    }

    /**
     * Retorna uma lista de String contendo o cpf de todos os clientes.
     * A lista é ordenada pelo nome dos clientes.
     *
     * @return lista com o cpf dos clientes
     */
    public List<String> retornaCpfsClientes () {
        return this.clientes
                .values()
                .stream()
                .sorted(Comparator.comparing(Cliente::getNome))
                .map(Cliente::getCpf)
                .collect(Collectors.toList());
    }
}
