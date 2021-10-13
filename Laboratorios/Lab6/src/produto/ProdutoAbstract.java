package produto;

import java.util.Objects;

/**
 * Representação abstrata de um produto.
 * Cada produto tem um nome, uma descrição, e um preço.
 *
 * @author Pedro Manoel
 */
public abstract class ProdutoAbstract implements Produto, Comparable<ProdutoAbstract> {

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
     * Contrói um produto abstrato a partir do nome, da descrição, e do preço.
     *
     * @param nome nome do produto
     * @param descricao descrição do produto
     * @param preco preço do produto
     */
    public ProdutoAbstract (String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return nome do produto
     */
    public String getNome () { return this.nome; }

    /**
     * Retorna a descrição do produto.
     *
     * @return descrição do produto
     */
    public String getDescricao () { return this.descricao; }

    /**
     * Retorna o preço do produto.
     *
     * @return preço do produto
     */
    public double getPreco () { return this.preco; }

    /**
     * Altera o preço do produto.
     *
     * @param preco novo preço do produto
     */
    public void setPreco (double preco) { this.preco = preco; }

    /**
     * Retorna o resultado da comparação do produto
     * com outro produto.
     *
     * @param outroProduto o outro produto que sera usado para a comparação
     * @return resultado da comparação
     */
    @Override
    public int compareTo (ProdutoAbstract outroProduto) { return this.getNome().compareTo(outroProduto.getNome()); }

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
                this.getNome(),
                this.getDescricao(),
                this.getPreco()
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
    public boolean equals (Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ProdutoAbstract)) return false;

        ProdutoAbstract produto = (ProdutoAbstract) obj;

        return this.nome.equals(produto.getNome()) && this.descricao.equals(produto.getDescricao());
    }

    /**
     * Retorna um valor numérico que representa unicamente o produto.
     *
     * @return valor numérico que representa unicamente o produto
     */
    @Override
    public int hashCode() { return Objects.hash(this.getNome(), this.getDescricao()); }
}
