package facade;

public class Facade {
	private ControlerCentral cc;

	public Facade() {
		this.cc = new ControlerCentral();
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
		return this.cc.exibeClientes();
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
		return this.cc.adicionaFornecedor(nome, email, telefone);
	}

	/**
	 * 
	 * @param nome
	 * @return
	 */
	public String exibeFornecedor(String nome) {
		return this.cc.exibeFornecedor(nome);
	}

	/**
	 * 
	 * @return
	 */
	public String exibeFornecedores() {
		return this.cc.exibeFornecedores();
	}

	/**
	 * 
	 * @param nome
	 * @param atributo
	 * @param novoValor
	 */
	public void editaFornecedor(String nome, String atributo, String novoValor) {
		this.cc.editaFornecedor(nome, atributo, novoValor);
	}

	/**
	 * 
	 * @param nome
	 */
	public void removeFornecedor(String nome) {
		this.cc.removeFornecedor(nome);
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
	public String adicionaProduto (String fornecedor, String nome, String descricao, double preco) {
		return this.cc.adicionaProdutoSimples(fornecedor, nome, descricao, preco);
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
	public String adicionaCombo (String fornecedor, String nome, String descricao, double fator, String produtos) {
		return this.cc.adicionaProdutoCombo(fornecedor, nome, descricao, fator, produtos);
	}

	/**
	 * Retorna a representação em String de um produto de um fornecedor.
	 *
	 * @param fornecedor nome do fornecedor
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 * @return representação do produto em String
	 */
	public String exibeProduto (String nome, String descricao, String fornecedor) {
		return this.cc.retornaProduto(fornecedor, nome, descricao);
	}

	/**
	 * Retorna a representação em String da lista de produtos de um fornecedor.
	 *
	 * @param fornecedor nome do fornecedor
	 * @return lista de produtos de um fornecedor em String
	 */
	public String exibeProdutosFornecedor (String fornecedor) {
		return this.cc.retornaProdutosDeFornecedor(fornecedor);
	}

	/**
	 * Retorna a representação em String da lista de produtos de todos os fornecedores.
	 * A lista é ordenada pela representação em String dos fornecedores.
	 *
	 * @return lista de produtos de todos os fornecedores em String
	 */
	public String exibeProdutos () {
		return this.cc.exibeProdutos();
	}

	/**
	 * Edita um produto simples de um fornecedor.
	 *
	 * @param fornecedor nome do fornecedor
	 * @param nome nome do produto simples
	 * @param descricao descrição do produto simples
	 * @param novoPreco novo preço do produto simples
	 */
	public void editaProduto (String nome, String descricao, String fornecedor, double novoPreco) {
		this.cc.editaProdutoSimples(fornecedor, nome, descricao, novoPreco);
	}

	/**
	 * Edita um produto combo de um fornecedor.
	 *
	 * @param fornecedor nome do fornecedor
	 * @param nome nome do produto combo
	 * @param descricao descrição do produto combo
	 * @param novoFator novo fator de desconto do produto combo
	 */
	public void editaCombo (String nome, String descricao, String fornecedor, double novoFator) {
		this.cc.editaProdutoCombo(fornecedor, nome, descricao, novoFator);
	}

	/**
	 * Remove um produto de um fornecedor.
	 *
	 * @param fornecedor nome do fornecedor
	 * @param nome nome do produto
	 * @param descricao descrição do produto
	 */
	public void removeProduto (String nome, String descricao, String fornecedor) {
		this.cc.removeProduto(fornecedor, nome, descricao);
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
		this.cc.adicionaCompra(cpf, fornecedor, data, nomeProduto, descricaoProduto);
	}

	/**
	 * 
	 * @param cpf
	 * @param fornecedor
	 * @return
	 */
	public double getDebito(String cpf, String fornecedor) {
		return this.cc.getDebito(cpf, fornecedor);
	}

	/**
	 * 
	 * @param cpf
	 * @param fornecedor
	 * @return
	 */
	public String exibeContas(String cpf, String fornecedor) {
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
		this.cc.ordenaPor(criterio);
	}

	/**
	 * 
	 * @return
	 */
	public String listarCompras() {
		return this.cc.listarCompras();
	}
}
