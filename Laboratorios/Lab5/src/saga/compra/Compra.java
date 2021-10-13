package saga.compra;

import saga.util.Consistencia;

/**
 * Representação de uma compra.
 * Cada compra tem uma data e tem o nome, a descrição, e o preço do produto comprado.
 *
 * @author Pedro Manoel
 */
public class Compra {

    /**
     * Data da compra.
     */
    private String data;

    /**
     * Nome do produto comprado.
     */
    private String nomeProduto;

    /**
     * Descrição do produto comprado.
     */
    private String descricaoProduto;

    /**
     * Preço do produto comprado.
     */
    private double preco;

    /**
     * Contrói uma compra a partir da data, do nome do produto, da descrição do produto,
     * e do preço do produto.
     *
     * @param data data da compra
     * @param nomeProduto nome do produto comprado
     * @param descricaoProduto descrição do produto comprado
     * @param preco preço do produto comprado
     */
    public Compra (String data, String nomeProduto, String descricaoProduto, double preco) {
        Consistencia.consisteNaoNuloNaoVazio(data, "data", false);
        Consistencia.consisteData(data);
        Consistencia.consisteNaoNuloNaoVazio(nomeProduto, "nome do produto");
        Consistencia.consisteNaoNuloNaoVazio(descricaoProduto, "descricao do produto");
        Consistencia.consisteNaoNegativo(preco, "preco");

        this.data = data.replace("/", "-");
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.preco = preco;
    }

    /**
     * Retorna o preço do produto comprado.
     *
     * @return preço do produto comprado
     */
    public double getPreco() { return preco; }

    /**
     * Retorna a descrição do produto comprado.
     *
     * @return descrição do produto comprado
     */
    public String getDescricaoProduto() { return descricaoProduto; }

    /**
     * Retorna a data do produto comprado.
     *
     * @return data do produto comprado
     */
    public String getData() { return data; }

    /**
     * Retorna a String que representa a compra.
     * A representação segue o seguinte formato: "NomeProduto - Data".
     *
     * @return representação da conta em String
     */
    @Override
    public String toString () {
        return String.format(
                "%s - %s",
                this.nomeProduto,
                this.data
        );
    }
}
