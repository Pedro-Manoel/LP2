package fornecedor;

import java.util.*;
import java.util.stream.Collectors;

import produto.Produto;
import produto.ProdutoCombo;
import produto.ProdutoSimples;

/**
 * Classe que representa um fornecedor do sistema.
 * 
 * @author Pedro Manoel
 *
 */
public class Fornecedor {

	/**
	 * Nome do fornecedor
	 */
	private String nome;

	/**
	 * Email do fornecedor
	 */
	private String email;

	/**
	 * Telefone do fornecedor
	 */
	private String telefone;

	/**
	 * Conjunto de produtos do fornecedor.
	 */
	private Map<String, Produto> produtos;

	/**
	 * Constrói um fornecedor dado seu nome, email e telefone
	 * 
	 * @param nome     Nome do fornecedor
	 * @param email    Email do fornecedor
	 * @param telefone Telefone do fornecedor
	 */
	public Fornecedor(String nome, String email, String telefone) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.produtos = new HashMap<>();
	}

	/**
	 * Retorna a representação textual do fornecedor no formato "Nome" - "Email" -
	 * "Telefone"
	 */
	@Override
	public String toString() {
		return this.getNome() + " - " + this.getEmail() + " - " + this.getTelefone();
	}

	/**
	 * Compara dois fornecedores pelo nome
	 */
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Fornecedor) {
			Fornecedor f = (Fornecedor) o;

			if (this.getNome().equals(f.getNome())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gera o hashCode do fornecedor baseado no nome
	 */
	@Override
	public int hashCode() {
		return this.getNome().hashCode();
	}

	/**
	 * Retorna o nome do fornecedor
	 * 
	 * @return String contendo o nome do fornecedor
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Retorna o telefone do fornecedor
	 * 
	 * @return String contendo o telefone do fornecedor
	 */
	private String getTelefone() {
		return this.telefone;
	}

	/**
	 * Retorna o email do fornecedor
	 * 
	 * @return String contendo o email do fornecedor
	 */
	private String getEmail() {
		return this.email;
	}

	/**
	 * Redefine o email do fornecedor dado o novo valor
	 * 
	 * @param email Novo email do fornecedor
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Redefine o telefone do fornecedor dado o novo valor
	 * 
	 * @param telefone Novo numero de telefone do fornecedor
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Adiciona um produto simples no fornecedor.
	 *
	 * @param nome nome do produto simples
	 * @param descricao descrição do produto simples
	 * @param preco preço do produto simples
	 */
	public void adicionaProdutoSimples (String nome, String descricao, double preco) {
		if (this.produtos.containsKey(Produto.geraChave(nome, descricao))) {
			throw new IllegalArgumentException("produto ja existe.");
		}

		this.produtos.put(Produto.geraChave(nome, descricao), new ProdutoSimples(nome, descricao, preco));
	}

	/**
	 * Adiciona um produto combo no fornecedor.
	 *
	 * @param nome nome do produto combo
	 * @param descricao descrição do produto combo
	 * @param fatorDesconto fator de desconto do produto combo
	 * @param produtos produtos do produto combo
	 */
	public void adicionaProdutoCombo (String nome, String descricao, double fatorDesconto, String produtos) {
		if (this.produtos.containsKey(Produto.geraChave(nome, descricao))) {
			throw new IllegalArgumentException("combo ja existe.");
		}

		double precoProdutoCombo = 0;

		for (String chaveProduto : produtos.split(", ")) {
			if (this.produtos.containsKey(chaveProduto)) {
				if (!(this.produtos.get(chaveProduto) instanceof ProdutoCombo)) {
					precoProdutoCombo += this.produtos.get(chaveProduto).getPreco();
				} else {
					throw new IllegalArgumentException("um combo nao pode possuir combos na lista de produtos.");
				}
			} else {
				throw new IllegalArgumentException("produto nao existe.");
			}
		}

		this.produtos.put(Produto.geraChave(nome, descricao), new ProdutoCombo(nome, descricao, precoProdutoCombo, fatorDesconto));
	}

	/**
	 * Retorna a representação em String de um produto do fornecedor.
	 *
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 * @return representação em String do produto
	 */
	public String retornaProduto (String nome, String descricao) {
		if (!this.produtos.containsKey(Produto.geraChave(nome, descricao))) {
			throw new IllegalArgumentException("produto nao existe.");
		}

		return this.produtos.get(Produto.geraChave(nome, descricao)).toString();
	}

	/**
	 * Retorna uma String que representa a lista de produtos do fornecedor.
	 * A representação segue o seguinte formato:
	 * "NomeFornecedor - Produto1 | NomeFornecedor - Produto2 | ...".
	 * A lista é ordenada por produto.
	 *
	 * @return representação da lista de produtos do fornecedor
	 */
	public String retornaProdutos () {
		String produtosDoFornecedor = this.produtos
				.values()
				.stream()
				.sorted()
				.map((Produto produto) -> String.format("%s - %s", this.nome, produto.toString()))
				.collect(Collectors.joining(" | "));

		return  produtosDoFornecedor.length() == 0
				? String.format("%s -", this.nome)
				: produtosDoFornecedor;
	}

	/**
	 * Edita um produto simples do fornecedor.
	 *
	 * @param nome nome do produto simples
	 * @param descricao descrição do produto simples
	 * @param novoPreco novo preço do produto simples
	 */
	public void editaProdutoSimples (String nome, String descricao, double novoPreco) {
		if (!this.produtos.containsKey(Produto.geraChave(nome, descricao))) {
			throw new IllegalArgumentException("produto nao existe.");
		}

		this.produtos.get(Produto.geraChave(nome, descricao)).setPreco(novoPreco);
	}

	/**
	 * Edita um produto combo do fornecedor.
	 *
	 * @param nome nome do produto combo
	 * @param descricao descrição do produto combo
	 * @param novoFatorDesconto novo fator de desconto do produto combo
	 */
	public void editaProdutoCombo (String nome, String descricao, double novoFatorDesconto) {
		if (!this.produtos.containsKey(Produto.geraChave(nome, descricao))) {
			throw new IllegalArgumentException("produto nao existe.");
		}

		((ProdutoCombo) this.produtos.get(Produto.geraChave(nome, descricao))).setFatorDesconto(novoFatorDesconto);
	}

	/**
	 * Remove um produto do fornecedor.
	 *
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 */
	public void removeProduto (String nome, String descricao) {
		if (!this.produtos.containsKey(Produto.geraChave(nome, descricao))) {
			throw new IllegalArgumentException("produto nao existe.");
		}

		this.produtos.remove(Produto.geraChave(nome, descricao));
	}

	/**
	 * Retorna o preço de um produto do fornecedor.
	 *
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 * @return preço do produto
	 */
	public double getPrecoProduto (String nome, String descricao) {
		if (!this.produtos.containsKey(Produto.geraChave(nome, descricao))) {
			throw new IllegalArgumentException("produto nao existe.");
		}

		return this.produtos.get(Produto.geraChave(nome, descricao)).getPreco();
	}
}
