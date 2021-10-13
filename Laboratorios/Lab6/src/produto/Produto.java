package produto;

/**
 * Interface que representa um produto.
 *
 * @author Pedro Manoel
 */
public interface Produto {

    /**
     * Retorna o preço do produto.
     *
     * @return preço do produto
     */
    double getPreco ();

    /**
     * Altera o preço do produto.
     *
     * @param preco novo preço do produto
     */
    void setPreco (double preco);

    /**
     * Retorna a String que representa o produto.
     * A representação segue o seguinte formato: "Nome - Descricao - 'R$'Preco".
     *
     * @return representação do produto em String
     */
    String toString ();

    /**
     * Verifica se o objeto, passado como parâmetro, é igual ao
     * produto, e retorna o resultado dessa verificação.
     *
     * @param obj objeto a comparar com o produto
     * @return valor booleano referente ao resultado da verificação
     */
    boolean equals (Object obj);

    /**
     * Retorna um valor numérico que representa unicamente o produto.
     *
     * @return valor numérico que representa unicamente o produto
     */
    int hashCode ();

    /**
     * Gera e retorna uma chave em String que representa unicamente um produto,
     * dado o nome e a descrição do produto.
     *
     * @param nome nome do produto
     * @param descricao descrição do produto
     * @return chave em String que representa unicamente o produto
     */
    static String geraChave (String nome, String descricao) {
        return String.format("%s - %s", nome, descricao);
    }
}
