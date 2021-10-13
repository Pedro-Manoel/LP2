package fornecedor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import produto.Produto;
import utilidades.Util;

/**
 * 
 * Classe que representa o controlador dos fornecedores cadastrados no sistema.
 * 
 * @author Pedro Manoel
 *
 */
public class ControlerFornecedor {

	/**
	 * HashMap que armazena todos os fornecedores, onde a chave é o nome do
	 * fornecedor e o valor é o fornecedor
	 */
	private HashMap<String, Fornecedor> fornecedores;

	/**
	 * Lista com o nome de todos os fornecedores cadastrados no sistema, ordenados
	 * em ordem alfabetica
	 */
	private ArrayList<String> nomesCadastrados;

	/**
	 * Contrói o controlador de fornecedores, não recebe nenhum parâmetro, só
	 * inicializa as variaveis
	 */
	public ControlerFornecedor() {
		this.fornecedores = new HashMap<String, Fornecedor>();
		this.nomesCadastrados = new ArrayList<String>();
	}

	/**
	 * Adiciona um fornecedor ao sistema dado seu nome, telefone e email
	 * 
	 * @param nome     Nome do fornecedor
	 * @param email    Email do fornecedor
	 * @param telefone Telefone do fornecedor
	 * @return Nome do fornecedor cadastrado
	 */
	public String adicionaFornecedor(String nome, String email, String telefone) {
		Util.testaNull(nome, "Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.");
		Util.testaVazio(nome, "Erro no cadastro do fornecedor: nome nao pode ser vazio ou nulo.");

		Util.testaNull(email, "Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.");
		Util.testaVazio(email, "Erro no cadastro do fornecedor: email nao pode ser vazio ou nulo.");

		Util.testaNull(telefone, "Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.");
		Util.testaVazio(telefone, "Erro no cadastro do fornecedor: telefone nao pode ser vazio ou nulo.");

		if (this.fornecedores.containsKey(nome)) {
			throw new IllegalArgumentException("Erro no cadastro de fornecedor: fornecedor ja existe.");
		}

		this.fornecedores.put(nome, new Fornecedor(nome, email, telefone));
		this.nomesCadastrados.add(nome);

		for (int i = this.nomesCadastrados.size() - 1; i > 0; i--) {
			String nome1 = this.nomesCadastrados.get(i);
			String nome2 = this.nomesCadastrados.get(i - 1);

			int compare = nome1.compareTo(nome2);

			if (compare < 0) {
				Collections.swap(this.nomesCadastrados, i, i - 1);
			}

		}

		return nome;
	}

	/**
	 * Retorna a representação textual de um fornecedor dado o seu nome
	 * 
	 * @param nome Nome do fornecedor que se deseja obter a representação textual
	 * @return String que é a representação textual do fornecedor
	 */
	public String exibeFornecedor(String nome) {
		Util.testaNull(nome, "Erro na exibicao do fornecedor: fornecedor nao pode ser vazio ou nulo.");
		Util.testaVazio(nome, "Erro na exibicao do fornecedor: fornecedor nao pode ser vazio ou nulo.");

		if (!this.fornecedores.containsKey(nome)) {
			throw new IllegalArgumentException("Erro na exibicao do fornecedor: fornecedor nao existe.");
		}

		return this.fornecedores.get(nome).toString();
	}

	/**
	 * Retorna uma lista dosfornecedores em String.
	 * 
	 * @return String que é a lista de todos os fornecedores cadastrados no sistema.
	 */
	public String listaFornecedores() {
		String retorno = "";

		for (int i = 0; i < this.nomesCadastrados.size(); i++) {
			String nome = this.nomesCadastrados.get(i);

			if (i == 0) {
				retorno += this.fornecedores.get(nome).toString();
			}

			else {
				retorno += " | " + this.fornecedores.get(nome).toString();
			}
		}

		return retorno;
	}

	/**
	 * Edita um fornecedor dado o nome do fornecedor, o atributo que se deseja
	 * editar e o novo valor.
	 * 
	 * @param nome      Nome dor fornecedor que se deseja editar.
	 * @param atributo  Nome do atributo que se deseja editar.
	 * @param novoValor Novo valor que se deseja atribuir ao atributo.
	 */
	public void editaFornecedor(String nome, String atributo, String novoValor) {
		Util.testaNull(nome, "Erro na edicao do fornecedor: nome nao pode ser vazio ou nulo.");
		Util.testaVazio(nome, "Erro na edicao do fornecedor: nome nao pode ser vazio ou nulo.");

		Util.testaNull(atributo, "Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.");
		Util.testaVazio(atributo, "Erro na edicao do fornecedor: atributo nao pode ser vazio ou nulo.");

		Util.testaNull(novoValor, "Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");
		Util.testaVazio(novoValor, "Erro na edicao do fornecedor: novo valor nao pode ser vazio ou nulo.");

		if (atributo.equals("nome")) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: nome nao pode ser editado.");
		}

		if (!atributo.equals("email") && !atributo.equals("telefone")) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: atributo nao existe.");
		}

		if (!this.fornecedores.containsKey(nome)) {
			throw new IllegalArgumentException("Erro na edicao do fornecedor: fornecedor nao existe.");
		}

		if (atributo.equals("email")) {
			this.fornecedores.get(nome).setEmail(novoValor);
		}

		if (atributo.equals("telefone")) {
			this.fornecedores.get(nome).setTelefone(novoValor);
		}
	}

	/**
	 * Remove um fornecedor do cadastro dado o nome do fornecedor
	 * 
	 * @param nome Nome do fornecedor que se deseja remover.
	 */
	public void removeFornecedor(String nome) {
		Util.testaNull(nome, "Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio ou nulo.");
		Util.testaVazio(nome, "Erro na remocao do fornecedor: nome do fornecedor nao pode ser vazio ou nulo.");

		if (!this.fornecedores.containsKey(nome)) {
			throw new IllegalArgumentException("Erro na remocao do fornecedor: fornecedor nao existe.");
		}

		this.fornecedores.remove(nome);

		for (int i = 0; i < this.nomesCadastrados.size(); i++) {
			if (this.nomesCadastrados.get(i).equals(nome)) {
				this.nomesCadastrados.remove(i);
				break;
			}
		}
	}

	/**
	 * Adiciona um produto simples em um fornecedor.
	 *
	 * @param nomeFornecedor nome do fornecedor
	 * @param nomeProduto nome do produto simples
	 * @param descricaoProduto descrição do produto simples
	 * @param precoProduto preço do produto simples
	 * @return uma String que representa unicamente o produto
	 */
	public String adicionaProdutoSimplesEmFornecedor (String nomeFornecedor, String nomeProduto, String descricaoProduto, double precoProduto) {
		try {
			Util.testaNullOuVazio(nomeFornecedor, "fornecedor nao pode ser vazio ou nulo.");
			Util.testaNullOuVazio(nomeProduto, "nome nao pode ser vazio ou nulo.");
			Util.testaNullOuVazio(descricaoProduto, "descricao nao pode ser vazia ou nula.");
			if (precoProduto < 0) {
				throw new IllegalArgumentException("preco invalido.");
			}
			if (!this.fornecedores.containsKey(nomeFornecedor)) {
				throw new IllegalArgumentException("fornecedor nao existe.");
			}

			this.fornecedores.get(nomeFornecedor).adicionaProdutoSimples(nomeProduto, descricaoProduto, precoProduto);

			return Produto.geraChave(nomeProduto, descricaoProduto);
		} catch (RuntimeException exception) {
			throw new IllegalArgumentException("Erro no cadastro de produto: ".concat(exception.getMessage()));
		}
	}

	/**
	 * Adiciona um produto combo em um fornecedor.
	 *
	 * @param nomeFornecedor nome do fornecedor
	 * @param nomeProduto nome do produto combo
	 * @param descricaoProduto descrição do produto combo
	 * @param fatorDesconto fator de desconto do produto combo
	 * @param produtos produtos do produto combo
	 * @return uma String que representa unicamente o produto
	 */
	public String adicionaProdutoComboEmFornecedor (String nomeFornecedor, String nomeProduto, String descricaoProduto, double fatorDesconto, String produtos) {
		try {
			Util.testaNullOuVazio(nomeFornecedor, "fornecedor nao pode ser vazio ou nulo.");
			Util.testaNullOuVazio(nomeProduto, "nome nao pode ser vazio ou nulo.");
			Util.testaNullOuVazio(descricaoProduto, "descricao nao pode ser vazia ou nula.");
			Util.testaNullOuVazio(produtos, "combo deve ter produtos.");
			if (fatorDesconto <= 0 || fatorDesconto >= 1 ) {
				throw new IllegalArgumentException("fator invalido.");
			}
			if (!this.fornecedores.containsKey(nomeFornecedor)) {
				throw new IllegalArgumentException("fornecedor nao existe.");
			}

			this.fornecedores.get(nomeFornecedor).adicionaProdutoCombo(nomeProduto, descricaoProduto, fatorDesconto, produtos);

			return Produto.geraChave(nomeProduto, descricaoProduto);
		} catch (RuntimeException exception) {
			throw new IllegalArgumentException("Erro no cadastro de combo: ".concat(exception.getMessage()));
		}
	}

	/**
	 * Retorna a representação em String de um produto de um fornecedor.
	 *
	 * @param nomeFornecedor nome do fornecedor
	 * @param nomeProduto nome do produto
	 * @param descricaoProduto descrição do produto
	 * @return representação do produto em String
	 */
	public String retornaProdutoDeFornecedor (String nomeFornecedor, String nomeProduto, String descricaoProduto) {
		try {
			Util.testaNullOuVazio(nomeFornecedor, "fornecedor nao pode ser vazio ou nulo.");
			Util.testaNullOuVazio(nomeProduto, "nome nao pode ser vazio ou nulo.");
			Util.testaNullOuVazio(descricaoProduto, "descricao nao pode ser vazia ou nula.");
			if (!this.fornecedores.containsKey(nomeFornecedor)) {
				throw new IllegalArgumentException("fornecedor nao existe.");
			}

			return this.fornecedores.get(nomeFornecedor).retornaProduto(nomeProduto, descricaoProduto);
		} catch (RuntimeException exception) {
			throw new IllegalArgumentException("Erro na exibicao de produto: ".concat(exception.getMessage()));
		}
	}

	/**
	 * Retorna a representação em String da lista de produtos de um fornecedor.
	 *
	 * @param nomeFornecedor nome do fornecedor
	 * @return lista de produtos de um fornecedor em String
	 */
	public String retornaProdutosDeFornecedor (String nomeFornecedor) {
		try {
			Util.testaNullOuVazio(nomeFornecedor, "fornecedor nao pode ser vazio ou nulo.");
			if (!this.fornecedores.containsKey(nomeFornecedor)) {
				throw new IllegalArgumentException("fornecedor nao existe.");
			}

			return this.fornecedores.get(nomeFornecedor).retornaProdutos();
		} catch (RuntimeException exception) {
			throw new IllegalArgumentException("Erro na exibicao de produto: ".concat(exception.getMessage()));
		}
	}

	/**
	 * Retorna a representação em String da lista de produtos de todos os fornecedores.
	 * A lista é ordenada pela representação em String dos fornecedores.
	 *
	 * @return lista de produtos de todos os fornecedores em String
	 */
	public String retornaProdutosDosFornecedores () {
		return this.fornecedores
				.values()
				.stream()
				.sorted(Comparator.comparing(Fornecedor::toString))
				.map(Fornecedor::retornaProdutos)
				.collect(Collectors.joining(" | "));
	}

	/**
	 * Edita um produto simples de um fornecedor.
	 *
	 * @param nomeFornecedor nome do fornecedor
	 * @param nomeProduto nome do produto simples
	 * @param descricaoProduto descrição do produto simples
	 * @param novoPrecoProduto novo preço do produto simples
	 */
	public void editaProdutoSimplesDeFornecedor (String nomeFornecedor, String nomeProduto, String descricaoProduto, double novoPrecoProduto) {
		try {
			Util.testaNullOuVazio(nomeProduto, "nome nao pode ser vazio ou nulo.");
			Util.testaNullOuVazio(descricaoProduto, "descricao nao pode ser vazia ou nula.");
			Util.testaNullOuVazio(nomeFornecedor, "fornecedor nao pode ser vazio ou nulo.");
			if (novoPrecoProduto < 0) {
				throw new IllegalArgumentException("preco invalido.");
			}
			if (!this.fornecedores.containsKey(nomeFornecedor)) {
				throw new IllegalArgumentException("fornecedor nao existe.");
			}

			this.fornecedores.get(nomeFornecedor).editaProdutoSimples(nomeProduto, descricaoProduto, novoPrecoProduto);
		} catch (RuntimeException exception) {
			throw new IllegalArgumentException("Erro na edicao de produto: ".concat(exception.getMessage()));
		}
	}

	/**
	 * Edita um produto combo de um fornecedor.
	 *
	 * @param nomeFornecedor nome do fornecedor
	 * @param nomeProduto nome do produto combo
	 * @param descricaoProduto descrição do produto combo
	 * @param novoFatorDescontoProduto novo fator de desconto do produto combo
	 */
	public void editaProdutoComboDeFornecedor (String nomeFornecedor, String nomeProduto, String descricaoProduto, double novoFatorDescontoProduto) {
		try {
			Util.testaNullOuVazio(nomeProduto, "nome nao pode ser vazio ou nulo.");
			Util.testaNullOuVazio(descricaoProduto, "descricao nao pode ser vazia ou nula.");
			Util.testaNullOuVazio(nomeFornecedor, "fornecedor nao pode ser vazio ou nulo.");
			if (novoFatorDescontoProduto <= 0 || novoFatorDescontoProduto >= 1 ) {
				throw new IllegalArgumentException("fator invalido.");
			}
			if (!this.fornecedores.containsKey(nomeFornecedor)) {
				throw new IllegalArgumentException("fornecedor nao existe.");
			}

			this.fornecedores.get(nomeFornecedor).editaProdutoCombo(nomeProduto, descricaoProduto, novoFatorDescontoProduto);
		} catch (RuntimeException exception) {
			throw new IllegalArgumentException("Erro na edicao de combo: ".concat(exception.getMessage()));
		}
	}

	/**
	 * Remove um produto de um fornecedor.
	 *
	 * @param nomeFornecedor nome do fornecedor
	 * @param nomeProduto nome do produto
	 * @param descricaoProduto descrição do produto
	 */
	public void removeProdutoDeFornecedor (String nomeFornecedor, String nomeProduto, String descricaoProduto) {
		try {
			Util.testaNullOuVazio(nomeFornecedor, "fornecedor nao pode ser vazio ou nulo.");
			Util.testaNullOuVazio(nomeProduto, "nome nao pode ser vazio ou nulo.");
			Util.testaNullOuVazio(descricaoProduto, "descricao nao pode ser vazia ou nula.");
			if (!this.fornecedores.containsKey(nomeFornecedor)) {
				throw new IllegalArgumentException("fornecedor nao existe.");
			}

			this.fornecedores.get(nomeFornecedor).removeProduto(nomeProduto, descricaoProduto);
		} catch (RuntimeException exception) {
			throw new IllegalArgumentException("Erro na remocao de produto: ".concat(exception.getMessage()));
		}
	}

	/**
	 * Retorna o preço de um produto de um fornecedor.
	 *
	 * @param nomeFornecedor nome do fornecedor
	 * @param nomeProduto nome do produto
	 * @param descricaoProduto descrição do produto
	 * @return preço do produto
	 */
	public double getPrecoProdutoDeFornecedor (String nomeFornecedor, String nomeProduto, String descricaoProduto) {
		try {
			Util.testaNullOuVazio(nomeFornecedor, "fornecedor nao pode ser vazio ou nulo.");
			Util.testaNullOuVazio(nomeProduto, "nome nao pode ser vazio ou nulo.");
			Util.testaNullOuVazio(descricaoProduto, "descricao nao pode ser vazia ou nula.");
			if (!this.fornecedores.containsKey(nomeFornecedor)) {
				throw new IllegalArgumentException("fornecedor nao existe.");
			}

			return this.fornecedores.get(nomeFornecedor).getPrecoProduto(nomeProduto, descricaoProduto);
		} catch (RuntimeException exception) {
			throw new IllegalArgumentException("Erro ao pegar preco do produto: ".concat(exception.getMessage()));
		}
	}

	/**
	 * Verifica se existe um fornecedor no sistema dado o nome do fornecedor
	 * 
	 * @param fornecedor Nome do fornecedor que se deseja saber se ele existe no
	 *                   sistema
	 * @return True caso exista, false caso contrário
	 */
	public boolean existeFornecedor(String fornecedor) {
		if (this.fornecedores.containsKey(fornecedor)) {
			return true;
		}

		return false;
	}
}
