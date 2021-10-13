package facade;

import cliente.ControlerCliente;
import fornecedor.ControlerFornecedor;
import utilidades.Util;

/**
 * Classe que representa o controlador central do sistema.
 *
 * @author Pedro Manoel
 *
 */
public class ControlerCentral {
	
	/**
	 * 
	 */
	ControlerCliente cc = new ControlerCliente();

	/**
	 * 
	 */
	ControlerFornecedor cf = new ControlerFornecedor();

	/**
	 * 
	 */
	public ControlerCentral() {
		this.cc = new ControlerCliente();
		this.cf = new ControlerFornecedor();
	}

	/**
	 * 
	 * @param cpf
	 * @param nome
	 * @param email
	 * @param localizacao
	 * @return
	 */
	public String adicionaCliente(String cpf, String nome, String email, String localizacao) {
		return this.cc.adicionaCliente(cpf, nome, email, localizacao);
	}

	/**
	 * 
	 * @param cpf
	 * @return
	 */
	public String exibeCliente(String cpf) {
		return this.cc.exibeCliente(cpf);
	}

	/**
	 * 
	 * @return
	 */
	public String exibeClientes() {
		return this.cc.listaClientes();
	}

	/**
	 * 
	 * @param cpf
	 * @param atributo
	 * @param novoValor
	 */
	public void editaCliente(String cpf, String atributo, String novoValor) {
		this.cc.editaCliente(cpf, atributo, novoValor);
	}

	/**
	 * 
	 * @param cpf
	 */
	public void removeCliente(String cpf) {
		this.cc.removeCliente(cpf);
	}

	/**
	 * 
	 * @param nome
	 * @param email
	 * @param telefone
	 * @return
	 */
	public String adicionaFornecedor(String nome, String email, String telefone) {
		return this.cf.adicionaFornecedor(nome, email, telefone);
	}

	/**
	 * 
	 * @param nome
	 * @return
	 */
	public String exibeFornecedor(String nome) {
		return this.cf.exibeFornecedor(nome);
	}

	/**
	 * 
	 * @return
	 */
	public String exibeFornecedores() {
		return this.cf.listaFornecedores();
	}

	/**
	 * 
	 * @param nome
	 * @param atributo
	 * @param novoValor
	 */
	public void editaFornecedor(String nome, String atributo, String novoValor) {
		this.cf.editaFornecedor(nome, atributo, novoValor);
	}

	/**
	 * 
	 * @param nome
	 */
	public void removeFornecedor(String nome) {
		this.cf.removeFornecedor(nome);
	}

	/**
	 * Adiciona um produto simples em um fornecedor.
	 *
	 * @param fornecedor nome do fornecedor
	 * @param nome nome do produto simples
	 * @param descricao descrição do produto simples
	 * @param preco preço do produto simples
	 * @return uma String que representa unicamente o produto
	 */
	public String adicionaProdutoSimples (String fornecedor, String nome, String descricao, double preco) {
		return this.cf.adicionaProdutoSimplesEmFornecedor(fornecedor, nome, descricao, preco);
	}

	/**
	 * Adiciona um produto combo em um fornecedor.
	 *
	 * @param fornecedor nome do fornecedor
	 * @param nome nome do produto combo
	 * @param descricao descrição do produto combo
	 * @param fator fator de desconto do produto combo
	 * @param produtos produtos do produto combo
	 * @return uma String que representa unicamente o produto
	 */
	public String adicionaProdutoCombo (String fornecedor, String nome, String descricao, double fator, String produtos) {
		return this.cf.adicionaProdutoComboEmFornecedor(fornecedor, nome, descricao, fator, produtos);
	}

	/**
	 * Retorna a representação em String de um produto de um fornecedor.
	 *
	 * @param fornecedor nome do fornecedor
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 * @return representação do produto em String
	 */
	public String retornaProduto (String fornecedor, String nome, String descricao) {
		return this.cf.retornaProdutoDeFornecedor(fornecedor, nome, descricao);
	}

	/**
	 * Retorna a representação em String da lista de produtos de um fornecedor.
	 *
	 * @param fornecedor nome do fornecedor
	 * @return lista de produtos de um fornecedor em String
	 */
	public String retornaProdutosDeFornecedor (String fornecedor) {
		return this.cf.retornaProdutosDeFornecedor(fornecedor);
	}

	/**
	 * Retorna a representação em String da lista de produtos de todos os fornecedores.
	 * A lista é ordenada pela representação em String dos fornecedores.
	 *
	 * @return lista de produtos de todos os fornecedores em String
	 */
	public String exibeProdutos () {
		return this.cf.retornaProdutosDosFornecedores();
	}

	/**
	 * Edita um produto simples de um fornecedor.
	 *
	 * @param fornecedor nome do fornecedor
	 * @param nome nome do produto simples
	 * @param descricao descrição do produto simples
	 * @param novoPreco novo preço do produto simples
	 */
	public void editaProdutoSimples (String fornecedor, String nome, String descricao, double novoPreco) {
		this.cf.editaProdutoSimplesDeFornecedor(fornecedor, nome, descricao, novoPreco);
	}

	/**
	 * Edita um produto combo de um fornecedor.
	 *
	 * @param fornecedor nome do fornecedor
	 * @param nome nome do produto combo
	 * @param descricao descrição do produto combo
	 * @param novoFator novo fator de desconto do produto combo
	 */
	public void editaProdutoCombo (String fornecedor, String nome, String descricao, double novoFator) {
		this.cf.editaProdutoComboDeFornecedor(fornecedor, nome, descricao, novoFator);
	}

	/**
	 * Remove um produto de um fornecedor.
	 *
	 * @param fornecedor nome do fornecedor
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 */
	public void removeProduto (String fornecedor, String nome, String descricao) {
		this.cf.removeProdutoDeFornecedor(fornecedor, nome, descricao);
	}

	/**
	 * 
	 * @param cpf
	 * @param fornecedor
	 * @param data
	 * @param nomeProduto
	 * @param descricaoProduto
	 */
	public void adicionaCompra(String cpf, String fornecedor, String data, String nomeProduto,
			String descricaoProduto) {
		double precoProduto = 0;

		try {

			precoProduto = this.cf.getPrecoProdutoDeFornecedor(fornecedor, nomeProduto, descricaoProduto);

		} catch (IllegalArgumentException iae) {
			if (iae.getMessage().equals("Erro ao pegar preco do produto: fornecedor nao existe.")) {
				throw new IllegalArgumentException("Erro ao cadastrar compra: fornecedor nao existe.");
			}

			if (iae.getMessage().equals("Erro ao pegar preco do produto: produto nao existe.")) {
				throw new IllegalArgumentException("Erro ao cadastrar compra: produto nao existe.");
			}

			if (iae.getMessage().equals("Erro ao pegar preco do produto: fornecedor nao pode ser vazio ou nulo.")) {
				throw new IllegalArgumentException("Erro ao cadastrar compra: fornecedor nao pode ser vazio ou nulo.");
			}

			if (iae.getMessage()
					.equals("Erro ao pegar preco do produto: nome do produto nao pode ser vazio ou nulo.")) {
				throw new IllegalArgumentException(
						"Erro ao cadastrar compra: nome do produto nao pode ser vazio ou nulo.");
			}

			if (iae.getMessage()
					.equals("Erro ao pegar preco do produto: descricao do produto nao pode ser vazia ou nula.")) {
				throw new IllegalArgumentException(
						"Erro ao cadastrar compra: descricao do produto nao pode ser vazia ou nula.");
			}
		}

		catch (NullPointerException npe) {
			if (npe.getMessage().equals("Erro ao pegar preco do produto: fornecedor nao pode ser vazio ou nulo.")) {
				throw new NullPointerException("Erro ao cadastrar compra: fornecedor nao pode ser vazio ou nulo.");
			}

			if (npe.getMessage()
					.equals("Erro ao pegar preco do produto: nome do produto nao pode ser vazio ou nulo.")) {
				throw new NullPointerException("Erro ao cadastrar compra: nome do produto nao pode ser vazio ou nulo.");
			}

			if (npe.getMessage()
					.equals("Erro ao pegar preco do produto: descricao do produto nao pode ser vazia ou nula.")) {
				throw new NullPointerException(
						"Erro ao cadastrar compra: descricao do produto nao pode ser vazia ou nula.");
			}
		}

		this.cc.adicionaCompra(cpf, fornecedor, data, nomeProduto, descricaoProduto, precoProduto);
	}

	/**
	 * 
	 * @param cpf
	 * @param fornecedor
	 * @return
	 */
	public double getDebito(String cpf, String fornecedor) {
		Util.testaNull(fornecedor, "Erro ao recuperar debito: fornecedor nao pode ser vazio ou nulo.");
		Util.testaVazio(fornecedor, "Erro ao recuperar debito: fornecedor nao pode ser vazio ou nulo.");

		if (!this.cf.existeFornecedor(fornecedor)) {
			throw new IllegalArgumentException("Erro ao recuperar debito: fornecedor nao existe.");
		}

		return this.cc.getDebito(cpf, fornecedor);
	}

	/**
	 * 
	 * @param cpf
	 * @param fornecedor
	 * @return
	 */
	public String exibeContas(String cpf, String fornecedor) {
		Util.testaNull(fornecedor, "Erro ao exibir conta do cliente: fornecedor nao pode ser vazio ou nulo.");
		Util.testaVazio(fornecedor, "Erro ao exibir conta do cliente: fornecedor nao pode ser vazio ou nulo.");

		if (!this.cf.existeFornecedor(fornecedor)) {
			throw new IllegalArgumentException("Erro ao exibir conta do cliente: fornecedor nao existe.");
		}

		return this.cc.exibeContas(cpf, fornecedor);
	}

	/**
	 * 
	 * @param cpf
	 * @return
	 */
	public String exibeContasClientes(String cpf) {
		return this.cc.exibeContasClientes(cpf);
	}
	
	/**
	 * 
	 * @param criterio
	 */
	public void ordenaPor(String criterio) {
		this.cc.ordenaComprasPor(criterio);
	}
	
	/**
	 * 
	 * @return
	 */
	public String listarCompras() {
		return this.cc.listarCompras();
	}
}
