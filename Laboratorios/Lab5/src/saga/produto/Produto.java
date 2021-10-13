package saga.produto;

import saga.util.Consistencia;

import java.util.Objects;

/**
 * Representação de um produto
 * Cada produto tem um nome, uma descrição, e um preço.
 *
 * @author Pedro Manoel
 */
public class Produto implements Comparable<Produto> {

    /**
     * Nome do produto.
     */
    private String nome;

    /**
     * Descrição do produto.
     */
    private String descricao;

    /**
     * Preço do produto.
     */
    private double preco;

    /**
     * Contrói um produto a partir do nome, da descrição, e do preço.
     *
     * @param nome nome do produto
     * @param descricao descrição do produto
     * @param preco preço do produto
     */
    public Produto (String nome, String descricao, double preco) {
        Consistencia.consisteNaoNuloNaoVazio(nome, "nome");
        Consistencia.consisteNaoNuloNaoVazio(descricao, "descricao", false);
        Consistencia.consisteNaoNegativo(preco, "preco");

        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    /**
     * Altera o preço do produto.
     *
     * @param preco novo preço do produto
     */
    public void setPreco (double preco) {
        Consistencia.consisteNaoNegativo(preco, "preco");
        this.preco = preco;
    }

    /**
     * Retorna o preço do produto.
     *
     * @return o preço do produto
     */
    public double getPreco () { return this.preco; }

    /**
     * Retorna o resultado da comparação do produto
     * com outro produto.
     *
     * @param outroProduto o outro produto que sera usado para a comparação
     * @return resultado da comparação
     */
    @Override
    public int compareTo(Produto outroProduto) { return this.toString().compareTo(outroProduto.toString()); }

    /**
     * Retorna a String que representa o produto.
     * A representação segue o seguinte formato: "Nome - Descricao - 'R$'Preco".
     *
     * @return representação do produto em String
     */
    @Override
    public String toString () {
        return String.format(
                "%s - %s - R$%.2f",
                this.nome,
                this.descricao,
                this.preco
        );
    }

    /**
     * Verifica se o objeto, passado como parâmetro, é igual ao
     * produto, e retorna o resultado dessa verificação.
     *
     * @param obj objeto a comparar com o produto
     * @return valor booleano referente ao resultado da verificação
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Produto)) return false;

        Produto produto = (Produto) obj;

        return this.nome.equals(produto.nome) && this.descricao.equals(produto.descricao);
    }

    /**
     * Retorna um valor numérico que representa unicamente o produto.
     *
     * @return valor numérico que representa unicamente o produto
     */
    @Override
    public int hashCode() { return Objects.hash(nome, descricao); }
}
