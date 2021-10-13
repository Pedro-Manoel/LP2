package saga;

import saga.cliente.ClienteController;
import saga.fornecedor.FornecedorController;
import saga.util.Consistencia;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Representação de um controller central do sistema.
 *
 * @author Pedro Manoel
 */
public class MainController {

    /**
     * Controller de clientes do sistema.
     */
    private ClienteController clienteController;

    /**
     * Controller de fornecedores do sistema.
     */
    private FornecedorController fornecedorController;

    /**
     * Criterio de ordenação das compras cadastradas no sistema.
     */
    private String criterioOrdenacaoCompras;

    public MainController () {
        this.clienteController = new ClienteController();
        this.fornecedorController = new FornecedorController();
        this.criterioOrdenacaoCompras = null;
    }

    /**
     * Define um critério de ordenação das compras cadastradas no sistema.
     *
     * @param criterioOrdenacaoCompras critério de ordenação das compras cadastradas no sistema
     */
    public void defineCriterioOrdenacaoCompras(String criterioOrdenacaoCompras) {
        try {
            Consistencia.consisteNaoNuloNaoVazio(criterioOrdenacaoCompras, "criterio");
            if (!Arrays.asList("Cliente","Fornecedor","Data").contains(criterioOrdenacaoCompras)) {
                throw new IllegalArgumentException("criterio nao oferecido pelo sistema.");
            }

            this.criterioOrdenacaoCompras = criterioOrdenacaoCompras;
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Erro na listagem de compras: ".concat(exception.getMessage()));
        }
    }

    /**
     * Cadastra um cliente no sistema.
     *
     * @param cpf cpf do cliente
     * @param nome nome do cliente
     * @param email e-mail do cliente
     * @param localizacao localização do cliente
     * @return cpf do cliente
     */
    public String cadastraCliente (String cpf, String nome, String email, String localizacao) {
        return this.clienteController.cadastraCliente(cpf, nome, email, localizacao);
    }

    /**
     * Retorna a representação em String de um cliente.
     *
     * @param cpf cpf do cliente
     * @return representação em String do cliente
     */
    public String retornaCliente (String cpf) {
        return this.clienteController.retornaCliente(cpf);
    }

    /**
     * Retorna uma String que representa a lista de clientes.
     *
     * @return representação da lista de clientes em String
     */
    public String retornaClientes () {
        return this.clienteController.retornaClientes();
    }

    /**
     * Edita os atributos do cliente no sistema.
     *
     * @param cpf cpf do cliente
     * @param atributo atributo a ser editado
     * @param novoValor novo valor do atributo
     */
    public void editaCliente (String cpf, String atributo, String novoValor) {
        this.clienteController.editaCliente(cpf, atributo, novoValor);
    }

    /**
     * Remove um cliente do sistema.
     *
     * @param cpf cpf do cliente
     */
    public void removeCliente (String cpf) {
        this.clienteController.removeCliente(cpf);
    }

    /**
     * Cadastra um fornecedor no sistema.
     *
     * @param nome nome do fornecedor
     * @param email e-mail do fornecedor
     * @param telefone telefone do fornecedor
     * @return nome do fornecedor
     */
    public String cadastraFornecedor (String nome, String email, String telefone) {
        return this.fornecedorController.cadastraFornecedor(nome, email, telefone);
    }

    /**
     * Retorna a representação em String do fornecedor no sistema.
     *
     * @param nome cpf do fornecedor
     * @return representação em String do fornecedor
     */
    public String retornaFornecedor (String nome) {
        return this.fornecedorController.retornaFornecedor(nome);
    }

    /**
     * Retorna uma String que representa a lista de fornecedores.
     *
     * @return representação da lista de fornecedores no sistema
     */
    public String retornaFornecedores () {
        return this.fornecedorController.retornaFornecedores();
    }

    /**
     * Edita os atributos do fornecedor no sistema.
     *
     * @param nome nome do fornecedor
     * @param atributo atributo a ser editado
     * @param novoValor novo valor do atributo
     */
    public void editaFornecedor (String nome, String atributo, String novoValor) {
        this.fornecedorController.editaFornecedor(nome, atributo, novoValor);
    }

    /**
     * Remove um fornecedor do sistema.
     *
     * @param nome nome do fornecedor
     */
    public void removeFornecedor (String nome) {
        this.fornecedorController.removeFornecedor(nome);
    }

    /**
     * Cadastra um produto em um fornecedor.
     *
     * @param nomeFornecedor nome do fornecedor
     * @param nomeProduto nome do produto
     * @param descricaoProduto descrição do produto
     * @param precoProduto preço do produto
     * @return nome do produto concatenado com a descrição do produto
     */
    public String cadastraProduto (String nomeFornecedor, String nomeProduto, String descricaoProduto, double precoProduto) {
        return this.fornecedorController.cadastraProdutoEmFornecedor(nomeFornecedor, nomeProduto, descricaoProduto, precoProduto);
    }

    /**
     * Retorna a representação em String de um produto de um fornecedor.
     *
     * @param nomeFornecedor nome do fornecedor
     * @param nomeProduto nome do produto
     * @param descricaoProduto descrição do produto
     * @return representação do produto em String
     */
    public String retornaProduto (String nomeFornecedor, String nomeProduto, String descricaoProduto) {
        return this.fornecedorController.retornaProdutoDeFornecedor(nomeFornecedor, nomeProduto, descricaoProduto);
    }

    /**
     * Retorna a representação em String da lista de produtos de todos os fornecedores.
     *
     * @return lista de produtos de todos os fornecedores em String
     */
    public String retornaProdutos () {
        return this.fornecedorController.retornaProdutosDosFornecedores();
    }

    /**
     * Retorna a representação em String da lista de produtos de um fornecedor.
     *
     * @param nomeFornecedor nome do fornecedor
     * @return lista de produtos de um fornecedor em String
     */
    public String retornaProdutosDeFornecedor (String nomeFornecedor) {
        return this.fornecedorController.retornaProdutosDeFornecedor(nomeFornecedor);
    }

    /**
     * Edita um produto de um fornecedor.
     *
     * @param nomeFornecedor nome do fornecedor
     * @param nomeProduto nome do produto
     * @param descricaoProduto descrição do produto
     * @param novoPrecoProduto novo valor do produto
     */
    public void editaProduto (String nomeFornecedor, String nomeProduto, String descricaoProduto, double novoPrecoProduto) {
        this.fornecedorController.editaProdutoDeFornecedor(nomeFornecedor, nomeProduto, descricaoProduto, novoPrecoProduto);
    }

    /**
     * Remove um produto de um fornecedor.
     *
     * @param nomeFornecedor nome do fornecedor
     * @param nomeProduto nome do produto
     * @param descricaoProduto descrição do produto
     */
    public void removeProduto (String nomeFornecedor, String nomeProduto, String descricaoProduto) {
        this.fornecedorController.removeProdutoDeFornecedor(nomeFornecedor, nomeProduto, descricaoProduto);
    }

    /**
     * Cadastra uma compra de cliente em um fornecedor.
     *
     * @param nomeFornecedor nome do fornecedor
     * @param cpfCliente cpf do cliente
     * @param dataCompra data da compra
     * @param nomeProduto nome do produto comprado
     * @param descricaoProduto descrição do produto comprado
     */
    public void cadastraCompra (String cpfCliente, String nomeFornecedor, String dataCompra, String nomeProduto, String descricaoProduto) {
        try {
            Consistencia.consisteNaoNuloNaoVazio(cpfCliente, "cpf");
            Consistencia.consisteCpf(cpfCliente);
            this.clienteController.validaClienteExiste(cpfCliente, true);
            Consistencia.consisteNaoNuloNaoVazio(nomeFornecedor, "fornecedor");
            this.fornecedorController.validaFornecedorExiste(nomeFornecedor, true);
            Consistencia.consisteNaoNuloNaoVazio(nomeProduto, "nome do produto");
            Consistencia.consisteNaoNuloNaoVazio(descricaoProduto, "descricao do produto", false);

            double precoProduto = this.fornecedorController.getPrecoProdutoDeFornecedor(nomeFornecedor, nomeProduto, descricaoProduto);
            this.fornecedorController.cadastraCompraDeClienteComFornecedor(nomeFornecedor, cpfCliente, dataCompra, nomeProduto, descricaoProduto, precoProduto);
        } catch (IllegalArgumentException erro) {
            throw new IllegalArgumentException("Erro ao cadastrar compra: " + erro.getMessage());
        }
    }

    /**
     * Retorna o debito de um cliente com um fornecedor.
     *
     * @param nomeFornecedor nome do fornecedor
     * @param cpfCliente cpf do cliente
     * @return debito do cliente
     */
    public String retornaDebitoConta (String cpfCliente, String nomeFornecedor) {
        try {
            Consistencia.consisteNaoNuloNaoVazio(cpfCliente, "cpf");
            Consistencia.consisteCpf(cpfCliente);
            this.clienteController.validaClienteExiste(cpfCliente, true);
            Consistencia.consisteNaoNuloNaoVazio(nomeFornecedor, "fornecedor");
            this.fornecedorController.validaFornecedorExiste(nomeFornecedor, true);

            return String.format(
                    Locale.ENGLISH,
                    "%.2f",
                    this.fornecedorController.getDebitoContaDeClienteComFornecedor(nomeFornecedor, cpfCliente)
            );
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Erro ao recuperar debito: ".concat(exception.getMessage()));
        }
    }

    /**
     * Retorna a conta de um cliente com um fornecedor.
     *
     * @param nomeFornecedor nome do fornecedor
     * @param cpfCliente cpf do cliente
     * @return conta do cliente
     */
    public String retornaConta (String cpfCliente, String nomeFornecedor) {
        try {
            Consistencia.consisteNaoNuloNaoVazio(cpfCliente, "cpf");
            Consistencia.consisteCpf(cpfCliente);
            this.clienteController.validaClienteExiste(cpfCliente, true);
            Consistencia.consisteNaoNuloNaoVazio(nomeFornecedor, "fornecedor");
            this.fornecedorController.validaFornecedorExiste(nomeFornecedor, true);

            return String.format(
                    "Cliente: %s | %s",
                    this.clienteController.getNomeCliente(cpfCliente),
                    this.fornecedorController.retornaContaDeClienteComFornecedor(nomeFornecedor, cpfCliente)
            );
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Erro ao exibir conta do cliente: ".concat(exception.getMessage()));
        }
    }

    /**
     * Retorna as contas do cliente com os fornecedores.
     *
     * @param cpfCliente cpf do cliente
     * @return contas do cliente
     */
    public String retornaContas (String cpfCliente) {
        try {
            Consistencia.consisteNaoNuloNaoVazio(cpfCliente, "cpf");
            Consistencia.consisteCpf(cpfCliente);
            this.clienteController.validaClienteExiste(cpfCliente, true);

            List<String> nomesFornecedoresComContaDeCliente = this.fornecedorController.retornaNomesFornecedoresComContaDeCliente(cpfCliente);

            if (nomesFornecedoresComContaDeCliente.size() == 0) {
                throw new IllegalArgumentException("cliente nao tem nenhuma conta.");
            }

            return String.format(
                    "Cliente: %s | %s",
                    this.clienteController.getNomeCliente(cpfCliente),
                    nomesFornecedoresComContaDeCliente
                            .stream()
                            .map((String nomeFornecedor) -> this.fornecedorController.retornaContaDeClienteComFornecedor(nomeFornecedor, cpfCliente))
                            .collect(Collectors.joining(" | "))
            );
        } catch (Exception exception) {
            throw new IllegalArgumentException("Erro ao exibir contas do cliente: ".concat(exception.getMessage()));
        }
    }

    /**
     * Retorna uma String que representa a lista de todas as compras do sistema ordenadas por cliente.
     *
     * @return lista de todas as compras ordenadas por cliente
     */
    private String listaComprasPorCliente() {
        List<String> cpfsClientes = this.clienteController.retornaCpfsClientes();
        List<String> nomesFornecedores = this.fornecedorController.retornaNomesFornecedores();
        Set<String> comprasPorCliente = new TreeSet<>();

        for (String cpfCliente: cpfsClientes) {
            for (String nomeFornecedor: nomesFornecedores) {
                if (this.fornecedorController.existeContaDeClienteComFornecedor(nomeFornecedor, cpfCliente)) {
                    for (String compra : this.fornecedorController.retornaComprasDeClienteComFornecedor(cpfCliente, nomeFornecedor)) {
                        comprasPorCliente.add(
                                String.format(
                                        "%s, %s, %s",
                                        this.clienteController.getNomeCliente(cpfCliente),
                                        nomeFornecedor,
                                        compra
                                )
                        );
                    }
                }
            }
        }

        return comprasPorCliente.size() != 0
                ? String.join(" | ", comprasPorCliente)
                : "";
    }

    /**
     * Retorna uma String que representa a lista de todas as compras do sistema ordenadas por fornecedor.
     *
     * @return lista de todas as compras ordenadas por fornecedor
     */
    private String listaComprasPorFornecedor() {
        List<String> cpfsClientes = this.clienteController.retornaCpfsClientes();
        List<String> nomesFornecedores = this.fornecedorController.retornaNomesFornecedores();
        Set<String> comprasPorFornecedor = new TreeSet<>();

        for (String nomeFornecedor: nomesFornecedores) {
            for (String cpfCliente: cpfsClientes) {
                if (this.fornecedorController.existeContaDeClienteComFornecedor(nomeFornecedor, cpfCliente)) {
                    for (String compra : this.fornecedorController.retornaComprasDeClienteComFornecedor(cpfCliente, nomeFornecedor)) {
                        comprasPorFornecedor.add(
                                String.format(
                                        "%s, %s, %s",
                                        nomeFornecedor,
                                        this.clienteController.getNomeCliente(cpfCliente),
                                        compra
                                )
                        );
                    }
                }
            }
        }

        return comprasPorFornecedor.size() != 0
                ? String.join(" | ", comprasPorFornecedor)
                : "";
    }

    /**
     * Retorna uma String que representa a lista de todas as compras do sistema ordenadas por data.
     *
     * @return lista de todas as compras ordenadas por data
     */
    private String listaComprasPorData() {
        List<String> cpfsClientes = this.clienteController.retornaCpfsClientes();
        List<String> nomesFornecedores = this.fornecedorController.retornaNomesFornecedores();
        List<String> datasCompras = this.fornecedorController.retornaDatasComprasDeClientesComFornecedores();
        Set<String> comprasPorData = new LinkedHashSet<>();

        for (String dataCompra: datasCompras) {
            for (String cpfCliente: cpfsClientes) {
                for (String nomeFornecedor: nomesFornecedores) {
                    if (this.fornecedorController.existeContaDeClienteComFornecedor(nomeFornecedor, cpfCliente)) {
                        for (String compra : this.fornecedorController.retornaComprasDeClienteComFornecedorPorData(cpfCliente, nomeFornecedor, dataCompra)) {
                            comprasPorData.add(
                                    String.format(
                                            "%s, %s, %s, %s",
                                            dataCompra.replace("-", "/"),
                                            this.clienteController.getNomeCliente(cpfCliente),
                                            nomeFornecedor,
                                            compra
                                    )
                            );
                        }
                    }
                }
            }
        }

        return comprasPorData.size() != 0
                ? String.join(" | ", comprasPorData)
                : "";
    }

    /**
     * Retorna uma String que representa a lista de todas as compras do sistema.
     *
     * @return lista de todas as compras
     */
    public String listaCompras () {
        try {
            if (this.criterioOrdenacaoCompras == null) {
                throw new IllegalArgumentException("criterio ainda nao definido pelo sistema.");
            }

            switch (this.criterioOrdenacaoCompras) {
                case "Cliente":
                    return this.listaComprasPorCliente();
                case "Fornecedor":
                    return this.listaComprasPorFornecedor();
                case "Data":
                    return this.listaComprasPorData();
                default:
                    throw new IllegalArgumentException("criterio nao oferecido pelo sistema.");
            }
        } catch (Exception exception) {
            throw new IllegalArgumentException("Erro na listagem de compras: ".concat(exception.getMessage()));
        }
    }
}
