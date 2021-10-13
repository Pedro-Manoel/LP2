package saga;

import easyaccept.EasyAccept;

/**
 * Classe responsável por conter todos as funções e operações do sistema SAGA.
 *
 * @author Pedro Manoel
 */
public class Facade {

    /**
     * Controller central do sistema SAGA.
     */
    private MainController mainController;

    /**
     * Ponto de início da execução do Facade.
     *
     * @param args parâmetros que podem ser passados para o Facade na sua execução
     */
    public static void main (String[] args) {
        args = new String[] {
                "saga.Facade",
                "testes_aceitacao/use_case_1.txt",
                "testes_aceitacao/use_case_2.txt",
                "testes_aceitacao/use_case_3.txt",
                "testes_aceitacao/use_case_4.txt",
                "testes_aceitacao/use_case_5.txt",
                "testes_aceitacao/use_case_6.txt"
        };
        EasyAccept.main(args);
    }

    /**
     * Contrói um Facade.
     */
    public Facade () {
        this.mainController = new MainController();
    }

    /**
     * Adiciona um cliente no sistema SAGA.
     *
     * @param cpf cpf do cliente
     * @param nome nome do cliente
     * @param email e-mail do cliente
     * @param localizacao localização do cliente
     * @return cpf do cliente
     */
    public String adicionaCliente (String cpf, String nome, String email, String localizacao) {
        return this.mainController.cadastraCliente(cpf, nome, email, localizacao);
    }

    /**
     * Retorna a representação em String de um cliente.
     *
     * @param cpf cpf do cliente
     * @return representação em String do cliente
     */
    public String exibeCliente (String cpf) {
        return this.mainController.retornaCliente(cpf);
    }

    /**
     * Retorna uma String que representa a lista de clientes.
     *
     * @return representação da lista de clientes em String
     */
    public String exibeClientes () {
        return this.mainController.retornaClientes();
    }

    /**
     * Edita os atributos do cliente.
     *
     * @param cpf cpf do cliente
     * @param atributo atributo a ser editado
     * @param novoValor novo valor do atributo
     */
    public void editaCliente (String cpf, String atributo, String novoValor) {
        this.mainController.editaCliente(cpf, atributo, novoValor);
    }

    /**
     * Remove um cliente do sistema SAGA.
     *
     * @param cpf cpf do cliente
     */
    public void removeCliente (String cpf) {
        this.mainController.removeCliente(cpf);
    }

    /**
     * Adiciona um fornecedor no sistema SAGA.
     *
     * @param nome nome do fornecedor
     * @param email e-mail do fornecedor
     * @param telefone telefone do fornecedor
     * @return nome do fornecedor
     */
    public String adicionaFornecedor (String nome, String email, String telefone) {
        return this.mainController.cadastraFornecedor(nome, email, telefone);
    }

    /**
     * Retorna a representação em String do fornecedor.
     *
     * @param nome cpf do fornecedor
     * @return representação em String do fornecedor
     */
    public String exibeFornecedor (String nome) {
        return this.mainController.retornaFornecedor(nome);
    }

    /**
     * Retorna uma String que representa a lista de fornecedores.
     *
     * @return representação da lista de fornecedores no sistema
     */
    public String exibeFornecedores () {
        return this.mainController.retornaFornecedores();
    }

    /**
     * Edita os atributos do fornecedor.
     *
     * @param nome nome do fornecedor
     * @param atributo atributo a ser editado
     * @param novoValor novo valor do atributo
     */
    public void editaFornecedor (String nome, String atributo, String novoValor) {
        this.mainController.editaFornecedor(nome, atributo, novoValor);
    }

    /**
     * Remove um fornecedor do sistema SAGA.
     *
     * @param nome nome do fornecedor
     */
    public void removeFornecedor (String nome) {
        this.mainController.removeFornecedor(nome);
    }

    /**
     * Adiciona um produto em um fornecedor.
     *
     * @param fornecedor nome do fornecedor
     * @param nome nome do produto
     * @param descricao descrição do produto
     * @param preco preço do produto
     * @return nome do produto concatenado com a descrição do produto
     */
    public String adicionaProduto (String fornecedor, String nome, String descricao, double preco) {
        return this.mainController.cadastraProduto(fornecedor, nome, descricao, preco);
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
        return this.mainController.retornaProduto(fornecedor, nome, descricao);
    }

    /**
     * Retorna a representação em String da lista de produtos de todos os fornecedores.
     *
     * @return lista de produtos de todos os fornecedores em String
     */
    public String exibeProdutos () {
        return this.mainController.retornaProdutos();
    }

    /**
     * Retorna a representação em String da lista de produtos de um fornecedor.
     *
     * @param fornecedor nome do fornecedor
     * @return lista de produtos de um fornecedor em String
     */
    public String exibeProdutosFornecedor (String fornecedor) {
        return this.mainController.retornaProdutosDeFornecedor(fornecedor);
    }

    /**
     * Edita um produto de um fornecedor.
     *
     * @param fornecedor nome do fornecedor
     * @param nome nome do produto
     * @param descricao descrição do produto
     * @param novoPreco novo valor do produto
     */
    public void editaProduto (String nome, String descricao, String fornecedor, double novoPreco) {
        this.mainController.editaProduto(fornecedor, nome, descricao, novoPreco);
    }

    /**
     * Remove um produto de um fornecedor.
     *
     * @param fornecedor nome do fornecedor
     * @param nome nome do produto
     * @param descricao descrição do produto
     */
    public void removeProduto (String nome, String descricao, String fornecedor) {
        this.mainController.removeProduto(fornecedor, nome, descricao);
    }

    /**
     * Adiciona uma compra de cliente em um fornecedor.
     *
     * @param fornecedor nome do fornecedor
     * @param cpf cpf do cliente
     * @param data data da compra
     * @param nome_prod nome do produto comprado
     * @param desc_prod descrição do produto comprado
     */
    public void adicionaCompra (String cpf, String fornecedor, String data, String nome_prod, String desc_prod) {
        this.mainController.cadastraCompra(cpf, fornecedor, data, nome_prod, desc_prod);
    }

    /**
     * Retorna o debito de um cliente com um fornecedor.
     *
     * @param fornecedor nome do fornecedor
     * @param cpf cpf do cliente
     * @return debito do cliente
     */
    public String getDebito (String cpf, String fornecedor) {
        return this.mainController.retornaDebitoConta(cpf, fornecedor);
    }

    /**
     * Retorna a conta de um cliente com um fornecedor.
     *
     * @param fornecedor nome do fornecedor
     * @param cpf cpf do cliente
     * @return conta do cliente
     */
    public String exibeContas (String cpf, String fornecedor) {
        return this.mainController.retornaConta(cpf, fornecedor);
    }

    /**
     * Retorna as contas do cliente com os fornecedores.
     *
     * @param cpf cpf do cliente
     * @return contas do cliente
     */
    public String exibeContasClientes (String cpf) {
        return this.mainController.retornaContas(cpf);
    }

    /**
     * Define um critério de ordenação das compras cadastradas no sistema SAGA.
     *
     * @param criterio critério de ordenação das compras cadastradas no sistema
     */
    public void ordenaPor (String criterio) {
        this.mainController.defineCriterioOrdenacaoCompras(criterio);
    }

    /**
     * Retorna uma String que representa a lista de todas as compras.
     *
     * @return lista de todas as compras
     */
    public String listarCompras () {
        return this.mainController.listaCompras();
    }
}
