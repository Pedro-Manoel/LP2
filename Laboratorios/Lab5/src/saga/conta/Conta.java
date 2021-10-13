package saga.conta;

import saga.compra.Compra;
import saga.util.Consistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Representação de uma conta.
 * Cada conta tem o cpf do cliente e um conjunto de compras do cliente.
 *
 * @author Pedro Manoel
 */
public class Conta {

    /**
     * Cpf do cliente da conta.
     */
    private String cpfCliente;

    /**
     * Conjunto de compras da conta.
     */
    private List<Compra> compras;

    /**
     * Contrói uma conta a partir do cpf do cliente.
     *
     * @param cpfCliente cpf do cliente
     */
    public Conta (String cpfCliente) {
        Consistencia.consisteNaoNuloNaoVazio(cpfCliente, "cpf");
        Consistencia.consisteCpf(cpfCliente);

        this.cpfCliente = cpfCliente;
        this.compras = new ArrayList<>();
    }

    /**
     * Adiciona uma compra no conjunto de compras da conta.
     *
     * @param data data da compra
     * @param nomeProduto nome do produto comprado
     * @param descricaoProduto descrição do produto comprado
     * @param preco preço do produto comprado
     */
    public void adicionaCompra (String data, String nomeProduto, String descricaoProduto, double preco) {
        this.compras.add(new Compra(data, nomeProduto, descricaoProduto, preco));
    }

    /**
     * Retorna um lista de String contendo as compras da conta.
     * Cada String da lista segue o seguinte formato: "DescricaoProduto',' DataCompra"
     *
     * @return lista de compras da conta em String
     */
    public List<String> retornaCompras () {
        return this.compras
                .stream()
                .map((Compra compra) ->
                        String.format(
                                "%s, %s",
                                compra.getDescricaoProduto(),
                                compra.getData().replace("-", "/")
                        )
                )
                .collect(Collectors.toList());
    }

    /**
     * Retorna um lista de String contendo as compras da conta, realizadas em determinada data.
     * Cada String da lista segue o seguinte formato: "DescricaoProduto".
     * A lista é ordenada pelas String que a formam.
     *
     * @param dataCompras data das compras
     * @return lista de compras da conta, realizadas em determinada data, em String
     */
    public List<String> retornaComprasPorData (String dataCompras) {
        return this.compras
                .stream()
                .filter((Compra compra) -> compra.getData().equals(dataCompras))
                .map(Compra::getDescricaoProduto)
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Retorna uma lista de String contendo as datas das compras da conta.
     *
     * @return lista de datas das compras da conta em String
     */
    public List<String> retornaDatasDasCompras () {
        return this.compras
                .stream()
                .map(Compra::getData)
                .collect(Collectors.toList());
    }

    /**
     * Retorna o débito da conta.
     *
     * @return débito da conta
     */
    public double getDebito () {
       return this.compras
                .stream()
                .map(Compra::getPreco)
                .reduce(0.0, Double::sum);
    }

    /**
     * Retorna a String que representa a lista de compras da conta.
     * A representação segue o seguinte formato: "Compra1 | Compra2 | Compra3 ...".
     *
     * @return representação da lista de compras da conta em String
     */
    private String listaCompras () {
        return this.compras
                .stream()
                .map(Objects::toString)
                .collect(Collectors.joining(" | "));
    }

    /**
     * Retorna a String que representa a conta.
     * A representação da conta é a representação
     * da lista de compras da conta em String.
     *
     * @return representação da conta em String
     */
    @Override
    public String toString () {
        return listaCompras();
    }

    /**
     * Verifica se o objeto, passado como parâmetro, é igual a
     * conta, e retorna o resultado dessa verificação.
     *
     * @param obj objeto a comparar com a conta
     * @return valor booleano referente ao resultado da verificação
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Conta)) return false;

        Conta conta = (Conta) obj;

        return this.cpfCliente.equals(conta.cpfCliente);
    }

    /**
     * Retorna um valor numérico que representa unicamente a conta.
     *
     * @return valor numérico que representa unicamente a conta
     */
    @Override
    public int hashCode() { return Objects.hash(cpfCliente); }
}
