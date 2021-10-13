package fornecedor;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

import utilidades.Util;

/**
 * 
 * Classe que representa o controlador dos fornecedores cadastrados no sistema
 * 
 * @author Aluno de período anterior
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
	 * Adiciona um produto para um fornecedor.
	 * 
	 * @param fornecedor Nome do fornecedore que se deseja adicionar o produto
	 * @param nome       Nome do produto
	 * @param descricao  Desricao do produto
	 * @param preco      preco do produto
	 */
	public void adicionaProduto(String fornecedor, String nome, String descricao, double preco) {
		Util.testaNull(fornecedor, "Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.");
		Util.testaVazio(fornecedor, "Erro no cadastro de produto: fornecedor nao pode ser vazio ou nulo.");

		Util.testaNull(nome, "Erro no cadastro de produto: nome nao pode ser vazio ou nulo.");
		Util.testaVazio(nome, "Erro no cadastro de produto: nome nao pode ser vazio ou nulo.");

		Util.testaNull(descricao, "Erro no cadastro de produto: descricao nao pode ser vazia ou nula.");
		Util.testaVazio(descricao, "Erro no cadastro de produto: descricao nao pode ser vazia ou nula.");

		if (preco < 0) {
			throw new IllegalArgumentException("Erro no cadastro de produto: preco invalido.");
		}

		if (!this.fornecedores.containsKey(fornecedor)) {
			throw new IllegalArgumentException("Erro no cadastro de produto: fornecedor nao existe.");
		}

		this.fornecedores.get(fornecedor).adicionaProduto(nome, descricao, preco);
	}

	/**
	 * Exibe um produto de um fornecedor, dado o nome do fornecedor, o nome e a
	 * descrição do produto
	 * 
	 * @param nome       Nome do produto
	 * @param descricao  Descrição do porduto
	 * @param fornecedor Nome do fornecedor
	 * @return String que é a representação textual do produto
	 */
	public String exibeProduto(String nome, String descricao, String fornecedor) {
		Util.testaNull(fornecedor, "Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		Util.testaVazio(fornecedor, "Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");

		Util.testaNull(nome, "Erro na exibicao de produto: nome nao pode ser vazio ou nulo.");
		Util.testaVazio(nome, "Erro na exibicao de produto: nome nao pode ser vazio ou nulo.");

		Util.testaNull(descricao, "Erro na exibicao de produto: descricao nao pode ser vazia ou nula.");
		Util.testaVazio(descricao, "Erro na exibicao de produto: descricao nao pode ser vazia ou nula.");

		if (!this.fornecedores.containsKey(fornecedor)) {
			throw new IllegalArgumentException("Erro na exibicao de produto: fornecedor nao existe.");
		}

		return this.fornecedores.get(fornecedor).exibeProduto(nome, descricao);

	}

	/**
	 * Lista todos os produtos de um fornecedor em ordem alfabética
	 * 
	 * @param fornecedor Nome do fornecedor
	 * @return String que é a lista de todos os produtos que esse fornecedor oferece
	 *         ordenados em ordem alfabética do nome do produto
	 */
	public String listaProdutos(String fornecedor) {
		Util.testaNull(fornecedor, "Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");
		Util.testaVazio(fornecedor, "Erro na exibicao de produto: fornecedor nao pode ser vazio ou nulo.");

		if (!this.fornecedores.containsKey(fornecedor)) {
			throw new IllegalArgumentException("Erro na exibicao de produto: fornecedor nao existe.");
		}

		return this.fornecedores.get(fornecedor).listaProdutos();
	}

	/**
	 * Lista todos os produtos de todos os forncedores em ordem alfabética dos
	 * fornecedores e dos produtos
	 * 
	 * @return String que é uma lista de todos os produtos de todos os fornecedores
	 *         em ordem alfabética do nome dos forncedores e dos produtos
	 */
	public String listaTodosProdutos() {
		String retorno = "";

		for (int i = 0; i < this.nomesCadastrados.size(); i++) {
			String fornecedor = this.nomesCadastrados.get(i);

			if (i == 0) {
				retorno += this.fornecedores.get(fornecedor).listaProdutos();
			}

			else {
				retorno += " | " + this.fornecedores.get(fornecedor).listaProdutos();
			}
		}

		return retorno;
	}

	/**
	 * Edita o preco de um produto de um fornecedor dado o nome do fornecedor, nome
	 * e descricao do porduto e o novo valor
	 * 
	 * @param nome       Nome do produto
	 * @param descricao  Descricao do produto
	 * @param fornecedor Nome do fornecedor
	 * @param novoPreco  Novo preco do produto
	 */
	public void editaProduto(String nome, String descricao, String fornecedor, double novoPreco) {
		Util.testaNull(nome, "Erro na edicao de produto: nome nao pode ser vazio ou nulo.");
		Util.testaVazio(nome, "Erro na edicao de produto: nome nao pode ser vazio ou nulo.");

		Util.testaNull(descricao, "Erro na edicao de produto: descricao nao pode ser vazia ou nula.");
		Util.testaVazio(descricao, "Erro na edicao de produto: descricao nao pode ser vazia ou nula.");

		Util.testaNull(fornecedor, "Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.");
		Util.testaVazio(fornecedor, "Erro na edicao de produto: fornecedor nao pode ser vazio ou nulo.");

		if (novoPreco < 0) {
			throw new IllegalArgumentException("Erro na edicao de produto: preco invalido.");
		}

		if (!this.fornecedores.containsKey(fornecedor)) {
			throw new IllegalArgumentException("Erro na edicao de produto: fornecedor nao existe.");
		}

		this.fornecedores.get(fornecedor).editaProduto(nome, descricao, novoPreco);

	}

	/**
	 * Remove o produto de um fornecedor dado o nome do fornecedor e o nome e
	 * descricao do produto.
	 * 
	 * @param nome       Nome do produto
	 * @param descricao  Decricao do produto
	 * @param fornecedor Nome do fornecedor
	 */
	public void removeProduto(String nome, String descricao, String fornecedor) {
		Util.testaNull(fornecedor, "Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.");
		Util.testaVazio(fornecedor, "Erro na remocao de produto: fornecedor nao pode ser vazio ou nulo.");

		Util.testaNull(nome, "Erro na remocao de produto: nome nao pode ser vazio ou nulo.");
		Util.testaVazio(nome, "Erro na remocao de produto: nome nao pode ser vazio ou nulo.");

		Util.testaNull(descricao, "Erro na remocao de produto: descricao nao pode ser vazia ou nula.");
		Util.testaVazio(descricao, "Erro na remocao de produto: descricao nao pode ser vazia ou nula.");

		if (!this.fornecedores.containsKey(fornecedor)) {
			throw new IllegalArgumentException("Erro na remocao de produto: fornecedor nao existe.");
		}

		this.fornecedores.get(fornecedor).removeProduto(nome, descricao);

	}

	/**
	 * Retorna o preco de um produto dado o nome do fornecedor, nome e descricao do
	 * produto
	 * 
	 * @param fornecedor       Nome do fornecedor
	 * @param nomeProduto      Nome do produto
	 * @param descricaoProduto Descricao do produto
	 * @return Double que é o preco do produto
	 */
	public double getPrecoProduto(String fornecedor, String nomeProduto, String descricaoProduto) {

		Util.testaNull(fornecedor, "Erro ao pegar preco do produto: fornecedor nao pode ser vazio ou nulo.");
		Util.testaVazio(fornecedor, "Erro ao pegar preco do produto: fornecedor nao pode ser vazio ou nulo.");

		Util.testaNull(nomeProduto, "Erro ao pegar preco do produto: nome do produto nao pode ser vazio ou nulo.");
		Util.testaVazio(nomeProduto, "Erro ao pegar preco do produto: nome do produto nao pode ser vazio ou nulo");

		Util.testaNull(descricaoProduto,
				"Erro ao pegar preco do produto: descricao do produto nao pode ser vazia ou nula.");
		Util.testaVazio(descricaoProduto,
				"Erro ao pegar preco do produto: decricao do produto nao pode ser vazia ou nula.");

		if (!this.fornecedores.containsKey(fornecedor)) {
			throw new IllegalArgumentException("Erro ao pegar preco do produto: fornecedor nao existe.");
		}

		return this.fornecedores.get(fornecedor).getPrecoProduto(nomeProduto, descricaoProduto);
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
