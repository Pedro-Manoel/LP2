package produto;

/**
 * Representação de um produto simples.
 * Cada produto tem um nome, uma descrição, e um preço.
 *
 * @author Pedro Manoel
 */
public class ProdutoSimples extends ProdutoAbstract {

	/**
	 * Contrói um produto simples a partir do nome, da descrição, e do preço.
	 *
	 * @param nome nome do produto simples
	 * @param descricao descrição do produto simples
	 * @param preco preço do produto simples
	 */
	public ProdutoSimples (String nome, String descricao, double preco) {
		super(nome, descricao, preco);
	}
}
