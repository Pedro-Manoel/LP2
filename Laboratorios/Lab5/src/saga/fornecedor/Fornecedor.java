package saga.fornecedor;

import saga.util.Consistencia;
import saga.conta.Conta;
import saga.produto.Produto;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Representação de um fornecedor.
 * Cada fornecedor tem um nome, um email, um telefone, e
 * um conjunto de produtos e contas de clientes.
 *
 * @author Pedro Manoel
 */
public class Fornecedor implements Comparable<Fornecedor> {

    /**
     * Nome do fornecedor.
     */
    private String nome;

    /**
     * E-mail do fornecedor.
     */
    private String email;

    /**
     * Telefone do fornecedor.
     */
    private String telefone;

    /**
     * Conjunto de produtos do fornecedor.
     */
    private Map<String, Produto> produtos;

    /**
     * Conjunto de contas de clientes do fornecedor.
     */
   private Map<String, Conta> contasClientes;

    /**
     * Contrói um fornecedor a partir do nome, do email, e do telefone.
     *
     * @param nome nome do fornecedor
     * @param email e-mail do fornecedor
     * @param telefone telefone do fornecedor
     */
   public Fornecedor (String nome, String email, String telefone) {
       Consistencia.consisteNaoNuloNaoVazio(nome, "nome");
       Consistencia.consisteNaoNuloNaoVazio(email, "email");
       Consistencia.consisteNaoNuloNaoVazio(telefone, "telefone");

       this.nome = nome;
       this.email = email;
       this.telefone = telefone;
       this.produtos = new HashMap<>();
       this.contasClientes = new HashMap<>();
    }

    /**
     * Verifica se o produto existe e retorna o resultado desta verificação.
     *
     * @param nome nome do produto
     * @param descricao descrição do produto
     * @return valor booleano referente ao resultado da verificação
     */
    private boolean existeProduto (String nome, String descricao) { return this.produtos.containsKey(nome.concat(descricao)); }

    /**
     * Faz a validação da existência de um produto no fornecedor.
     *
     * @param nome nome do produto
     * @param produtoDeveExistir se o produto deve existir no fornecedor
     */
    private void validaProdutoExiste (String nome, String descricao, boolean produtoDeveExistir) {
        if (this.existeProduto(nome, descricao) && !produtoDeveExistir) {
            throw new IllegalArgumentException("produto ja existe.");
        }
        if (!this.existeProduto(nome, descricao) && produtoDeveExistir) {
            throw new IllegalArgumentException("produto nao existe.");
        }
    }

    /**
     * Adiciona um produto no fornecedor.
     *
     * @param nome nome do produto
     * @param descricao descição do produto
     * @param preco preço do produto
     */
    public void adicionaProduto (String nome, String descricao, double preco) {
        Consistencia.consisteNaoNuloNaoVazio(nome, "nome");
        Consistencia.consisteNaoNuloNaoVazio(descricao, "descricao", false);
        this.validaProdutoExiste(nome, descricao,false);

        this.produtos.put(nome.concat(descricao), new Produto(nome, descricao, preco));
    }

    /**
     * Retorna o preço de um produto do fornecedor.
     *
     * @param nome nome do produto
     * @param descricao descrição do produto
     * @return preço do produto
     */
    public double getPrecoProduto (String nome, String descricao) {
        Consistencia.consisteNaoNuloNaoVazio(nome, "nome");
        Consistencia.consisteNaoNuloNaoVazio(descricao, "descricao", false);
        this.validaProdutoExiste(nome, descricao,true);

        return this.produtos.get(nome.concat(descricao)).getPreco();
    }

    /**
     * Retorna a representação em String de um produto do fornecedor.
     *
     * @param nome nome do produto
     * @param descricao descrição do produto
     * @return representação em String do produto
     */
    public String retornaProduto (String nome, String descricao) {
        Consistencia.consisteNaoNuloNaoVazio(nome, "nome");
        Consistencia.consisteNaoNuloNaoVazio(descricao, "descricao", false);
        this.validaProdutoExiste(nome, descricao,true);

        return this.produtos.get(nome.concat(descricao)).toString();
    }

    /**
     * Retorna uma String que representa a lista de produtos do fornecedor.
     * A representação segue o seguinte formato:
     * "NomeFornecedor - Produto1 | NomeFornecedor - Produto2 | ..."
     *
     * @return representação da lista de produtos do fornecedor
     */
    public String retornaProdutos () {
         String produtosDoFornecedor = this.produtos
                 .values()
                 .stream()
                 .sorted()
                 .map((Produto produto) ->
                        String.format(
                                "%s - %s",
                                this.nome,
                                produto.toString()
                        ))
                 .collect(Collectors.joining(" | "));

        return  produtosDoFornecedor.length() == 0
                ? String.format("%s -", this.nome)
                : produtosDoFornecedor;
    }

    /**
     * Edita o preco de um produto do fornecedor.
     *
     * @param nome nome do produto
     * @param descricao descrição do produto
     * @param novoPreco novo preço do produto
     */
    public void editaPrecoProduto (String nome, String descricao, double novoPreco) {
        Consistencia.consisteNaoNegativo(novoPreco, "preco");
        Consistencia.consisteNaoNuloNaoVazio(nome, "nome");
        Consistencia.consisteNaoNuloNaoVazio(descricao, "descricao", false);
        this.validaProdutoExiste(nome, descricao,true);

        this.produtos.get(nome.concat(descricao)).setPreco(novoPreco);
    }

    /**
     * Remove um produto do fornecedor.
     *
     * @param nome nome do produto
     * @param descricao descrição do produto
     */
    public void removeProduto (String nome, String descricao) {
        Consistencia.consisteNaoNuloNaoVazio(nome, "nome");
        Consistencia.consisteNaoNuloNaoVazio(descricao, "descricao", false);
        this.validaProdutoExiste(nome, descricao,true);

        this.produtos.remove(nome.concat(descricao));
    }

    /**
     * Cria uma conta de cliente no fornecedor.
     *
     * @param cpf cpf do cliente
     */
    private void criaContaCliente (String cpf) {
        this.contasClientes.put(cpf, new Conta(cpf));
    }

    /**
     * Verifica se a conta de cliente existe e retorna
     * o resultado dessa verificação.
     *
     * @param cpf cpf do cliente
     * @return resultado da verificação
     */
    public boolean existeContaCliente (String cpf) { return this.contasClientes.containsKey(cpf); }

    /**
     * Adiciona uma compra a uma conta de cliente no fornecedor.
     *
     * @param cpfCliente cpf do cliente
     * @param data data da compra
     * @param nomeProduto nome do produto comprado
     * @param descricaoProduto descrição do produto comprado
     * @param preco preço do produto comprado
     */
    public void adicionaCompra (String cpfCliente, String data, String nomeProduto, String descricaoProduto, double preco) {
        if (!this.existeContaCliente(cpfCliente)) {
            this.criaContaCliente(cpfCliente);
        }

        this.contasClientes.get(cpfCliente).adicionaCompra(data, nomeProduto, descricaoProduto, preco);
    }

    /**
     * Retorna uma lista em String contendo as compras de um cliente com o fornecedor.
     *
     * @param cpfCliente cpf do cliente
     * @return compras do cliente com o fornecedor
     */
    public List<String> retornaComprasCliente (String cpfCliente) {
        Consistencia.consisteCpf(cpfCliente);
        if (!this.existeContaCliente(cpfCliente)) {
            throw new IllegalArgumentException("cliente nao tem compras com fornecedor.");
        }

        return this.contasClientes.get(cpfCliente).retornaCompras();
    }

    /**
     * Retorna uma lista em String contendo as compras de um
     * cliente com o fornecedor, realizadas em determinada data.
     *
     * @param cpfCliente cpf do cliente
     * @param dataCompra data das compras
     * @return compras do cliente com o fornecedor
     */
    public List<String> retornaComprasClientePorData (String cpfCliente, String dataCompra) {
        Consistencia.consisteCpf(cpfCliente);
        if (!this.existeContaCliente(cpfCliente)) {
            throw new IllegalArgumentException("cliente nao tem compras com fornecedor.");
        }

        return this.contasClientes.get(cpfCliente).retornaComprasPorData(dataCompra);
    }

    /**
     * Retorna o débito de um cliente com o fornecedor.
     *
     * @param cpfCliente cpf do cliente
     * @return debito da conta do cliente
     */
    public double getDebitoConta (String cpfCliente) {
        if (!this.existeContaCliente(cpfCliente)) {
            throw new IllegalArgumentException("cliente nao tem debito com fornecedor.");
        }

        return this.contasClientes.get(cpfCliente).getDebito();
    }

    /**
     * Retorna a representação em String de uma conta de cliente com o fornecedor.
     * A representação segue o seguinte formato: "NomeFornecedor | ContaCliente"
     *
     * @param cpfCliente cpf do cliente
     * @return representação da conta do cliente com o fornecedor em String
     */
    public String retornaConta (String cpfCliente) {
        if (!this.existeContaCliente(cpfCliente)) {
            throw new IllegalArgumentException("cliente nao tem nenhuma conta com o fornecedor.");
        }

        return this.nome
                .concat(" | ")
                .concat(this.contasClientes.get(cpfCliente).toString());
    }

    /**
     * Retorna uma lista em String contendo as datas
     * das compras dos clientes com o fornecedor.
     *
     * @return lista com as datas das compras dos clientes com o fornecedor
     */
    public List<String> retornaDatasComprasDeClientes () {
        return this.contasClientes
                .values()
                .stream()
                .flatMap((Conta conta) -> conta.retornaDatasDasCompras().stream())
                .collect(Collectors.toList());
    }

    /**
     * Retorna o nome do fornecedor.
     *
     * @return nome do fornecedor
     */
    public String getNome () { return this.nome; }

    /**
     * Altera o email do fornecedor.
     *
     * @param email novo email do fornecedor
     */
    public void setEmail(String email) {
        Consistencia.consisteNaoNuloNaoVazio(email, "email");

        this.email = email;
    }

    /**
     * Altera o telefone do fornecedor.
     *
     * @param telefone novo telefone do fornecedor
     */
    public void setTelefone(String telefone) {
        Consistencia.consisteNaoNuloNaoVazio(telefone, "telefone");

        this.telefone = telefone;
    }

    /**
     * Retorna o resultado da comparação do fornecedor
     * com outro fornecedor.
     *
     * @param outroFornecedor o outro fornecedor que sera usado para a comparação
     * @return resultado da comparação
     */
    @Override
    public int compareTo(Fornecedor outroFornecedor) {
        return this.toString().compareTo(outroFornecedor.toString());
    }

    /**
     * Retorna a String que representa o fornecedor.
     * A representação segue o seguinte formato: "Nome - Email - Telefone".
     *
     * @return representação do fornecedor em String
     */
    @Override
    public String toString () {
        return String.format(
                "%s - %s - %s",
                this.nome,
                this.email,
                this.telefone
        );
    }

    /**
     * Verifica se o objeto, passado como parâmetro, é igual ao
     * fornecedor, e retorna o resultado dessa verificação.
     *
     * @param obj objeto a comparar com o fornecedor
     * @return valor booleano referente ao resultado da verificação
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Fornecedor)) return false;

        Fornecedor fornecedor = (Fornecedor) obj;

        return this.nome.equals(fornecedor.nome);
    }

    /**
     * Retorna um valor numérico que representa unicamente o fornecedor.
     *
     * @return valor numérico que representa unicamente o fornecedor
     */
    @Override
    public int hashCode() { return Objects.hash(this.nome); }
}
