package saga.fornecedor;

import saga.util.Consistencia;

import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representação de um Controller de fornecedores.
 *
 * @author Pedro Manoel
 */
public class FornecedorController {

    /**
     * Conjunto de fornecedores do controller.
     */
    private Map<String, Fornecedor> fornecedores;

    /**
     * Contrói um controller de fornecedores.
     */
    public FornecedorController () { this.fornecedores = new HashMap<>(); }

    /**
     * Verifica se o fornecedor existe e retorna o resultado desta verificação.
     *
     * @param nome nome do fornecedor
     * @return valor booleano referente ao resultado da verificação
     */
    private boolean existeFornecedor (String nome) { return this.fornecedores.containsKey(nome); }

    /**
     * Faz a validação da existência de um fornecedor no controller.
     *
     * @param nome nome do fornecedor
     * @param fornecedorDeveExistir se fornecedor deve existir no controller
     */
    public void validaFornecedorExiste (String nome, boolean fornecedorDeveExistir) {
        if (this.existeFornecedor(nome) && !fornecedorDeveExistir) {
            throw new IllegalArgumentException("fornecedor ja existe.");
        }
        if (!this.existeFornecedor(nome) && fornecedorDeveExistir) {
            throw new IllegalArgumentException("fornecedor nao existe.");
        }
    }

    /**
     * Cadastra um fornecedor no controller.
     *
     * @param nome nome do fornecedor
     * @param email e-mail do fornecedor
     * @param telefone telefone do fornecedor
     * @return nome do fornecedor
     */
    public String cadastraFornecedor (String nome, String email, String telefone) {
        try {
            Consistencia.consisteNaoNuloNaoVazio(nome, "nome");
            this.validaFornecedorExiste(nome,false);

            this.fornecedores.put(nome, new Fornecedor(nome, email, telefone));

            return nome;
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException (
                    String.format(
                            "Erro no cadastro d%s fornecedor: %s",
                            exception.getMessage().equals("fornecedor ja existe.") ? "e" : "o", // Devido a mudança da frase na linha 14 do <use_case_2>
                            exception.getMessage()
                    )
            );
        }
    }

    /**
     * Retorna a representação em String de um fornecedor.
     *
     * @param nome cpf do fornecedor
     * @return representação em String do fornecedor
     */
    public String retornaFornecedor (String nome) {
        try {
            Consistencia.consisteNaoNuloNaoVazio(nome, "fornecedor");
            this.validaFornecedorExiste(nome, true);

            return this.fornecedores.get(nome).toString();
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Erro na exibicao do fornecedor: ".concat(exception.getMessage()));
        }
    }

    /**
     * Retorna uma String que representa a lista de fornecedores do controller.
     * A representação segue o seguinte formato:
     * "fornecedor1 | fornecedor2 | fornecedor3 ...".
     * A lista é ordenada por fornecedor.
     *
     * @return representação da lista de fornecedores do controller em String
     */
    public String retornaFornecedores () {
        return this.fornecedores
                .values()
                .stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(" | "));
    }

    /**
     * Edita os atributos de um fornecedor.
     *
     * @param nome nome do fornecedor
     * @param atributo atributo a ser editado
     * @param novoValor novo valor do atributo
     */
    public void editaFornecedor (String nome, String atributo, String novoValor) {
        try {
            Consistencia.consisteNaoNuloNaoVazio(nome, "nome");
            this.validaFornecedorExiste(nome,true);
            Consistencia.consisteNaoNuloNaoVazio(atributo, "atributo");
            Consistencia.consisteNaoNuloNaoVazio(novoValor, "novo valor");

            switch (atributo) {
                case "nome":
                    throw new IllegalArgumentException("nome nao pode ser editado.");
                case "email":
                    this.fornecedores.get(nome).setEmail(novoValor);
                    break;
                case "telefone":
                    this.fornecedores.get(nome).setTelefone(novoValor);
                    break;
                default:
                    throw new IllegalArgumentException("atributo nao existe.");
            }
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Erro na edicao do fornecedor: ".concat(exception.getMessage()));
        }
    }

    /**
     * Remove um fornecedor do controller.
     *
     * @param nome nome do fornecedor
     */
    public void removeFornecedor (String nome) {
        try {
            Consistencia.consisteNaoNuloNaoVazio(nome, "nome do fornecedor");
            this.validaFornecedorExiste(nome, true);

            this.fornecedores.remove(nome);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Erro na remocao do fornecedor: ".concat(exception.getMessage()));
        }
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
    public String cadastraProdutoEmFornecedor (String nomeFornecedor, String nomeProduto, String descricaoProduto, double precoProduto) {
        try {
            Consistencia.consisteNaoNuloNaoVazio(nomeFornecedor, "fornecedor");
            this.validaFornecedorExiste(nomeFornecedor, true);

            this.fornecedores.get(nomeFornecedor).adicionaProduto(nomeProduto, descricaoProduto, precoProduto);

            return nomeProduto.concat(descricaoProduto);
        } catch (IllegalArgumentException erro) {
            throw new IllegalArgumentException("Erro no cadastro de produto: ".concat(erro.getMessage()));
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
            Consistencia.consisteNaoNuloNaoVazio(nomeFornecedor, "fornecedor");
            this.validaFornecedorExiste(nomeFornecedor, true);

            return this.fornecedores.get(nomeFornecedor).retornaProduto(nomeProduto, descricaoProduto);
        } catch (IllegalArgumentException exception) {
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
            Consistencia.consisteNaoNuloNaoVazio(nomeFornecedor, "fornecedor");
            this.validaFornecedorExiste(nomeFornecedor, true);

            return this.fornecedores.get(nomeFornecedor).retornaProdutos();
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Erro na exibicao de produto: ".concat(exception.getMessage()));
        }
    }

    /**
     * Retorna a representação em String da lista de produtos de todos os fornecedores.
     * A lista é ordenada por fornecedor
     *
     * @return lista de produtos de todos os fornecedores em String
     */
    public String retornaProdutosDosFornecedores () {
        return this.fornecedores
                .values()
                .stream()
                .sorted()
                .map(Fornecedor::retornaProdutos)
                .collect(Collectors.joining(" | "));
    }

    /**
     * Edita um produto de um fornecedor.
     *
     * @param nomeFornecedor nome do fornecedor
     * @param nomeProduto nome do produto
     * @param descricaoProduto descrição do produto
     * @param novoPrecoProduto novo valor do produto
     */
    public void editaProdutoDeFornecedor (String nomeFornecedor, String nomeProduto, String descricaoProduto, double novoPrecoProduto) {
        try {
            Consistencia.consisteNaoNuloNaoVazio(nomeFornecedor, "fornecedor");
            this.validaFornecedorExiste(nomeFornecedor, true);

            this.fornecedores.get(nomeFornecedor).editaPrecoProduto(nomeProduto, descricaoProduto, novoPrecoProduto);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Erro na edicao de produto: ".concat(exception.getMessage()));
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
            Consistencia.consisteNaoNuloNaoVazio(nomeFornecedor, "fornecedor");
            this.validaFornecedorExiste(nomeFornecedor, true);

            this.fornecedores.get(nomeFornecedor).removeProduto(nomeProduto, descricaoProduto);
        } catch (IllegalArgumentException exception) {
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
        Consistencia.consisteNaoNuloNaoVazio(nomeFornecedor, "fornecedor");
        this.validaFornecedorExiste(nomeFornecedor, true);

        return this.fornecedores.get(nomeFornecedor).getPrecoProduto(nomeProduto, descricaoProduto);
    }

    /**
     * Cadastra uma compra de cliente em um fornecedor.
     *
     * @param nomeFornecedor nome do fornecedor
     * @param cpfCliente cpf do cliente
     * @param data data da compra
     * @param nomeProduto nome do produto comprado
     * @param descricaoProduto descrição do produto comprado
     * @param preco preço do produto comprado
     */
    public void cadastraCompraDeClienteComFornecedor (String nomeFornecedor, String cpfCliente, String data, String nomeProduto, String descricaoProduto, double preco) {
        Consistencia.consisteNaoNuloNaoVazio(nomeFornecedor, "fornecedor");
        this.validaFornecedorExiste(nomeFornecedor, true);

        this.fornecedores.get(nomeFornecedor).adicionaCompra(cpfCliente, data, nomeProduto, descricaoProduto, preco);
    }

    /**
     * Retorna o débito de um cliente com um fornecedor.
     *
     * @param nomeFornecedor nome do fornecedor
     * @param cpfCliente cpf do cliente
     * @return débito do cliente
     */
    public double getDebitoContaDeClienteComFornecedor (String nomeFornecedor, String cpfCliente) {
        Consistencia.consisteNaoNuloNaoVazio(nomeFornecedor, "fornecedor");
        this.validaFornecedorExiste(nomeFornecedor, true);

        return this.fornecedores.get(nomeFornecedor).getDebitoConta(cpfCliente);
    }

    /**
     * Retorna a representação em String da conta de
     * um cliente com um fornecedor.
     *
     * @param nomeFornecedor nome do fornecedor
     * @param cpfCliente cpf do cliente
     * @return representação da conta do cliente em String
     */
    public String retornaContaDeClienteComFornecedor (String nomeFornecedor, String cpfCliente) {
        Consistencia.consisteNaoNuloNaoVazio(nomeFornecedor, "fornecedor");
        this.validaFornecedorExiste(nomeFornecedor, true);

        return this.fornecedores.get(nomeFornecedor).retornaConta(cpfCliente);
    }

    /**
     * Retorna uma lista em String contendo o nome de todos os fornecedores
     * em que o cliente possui conta.
     * A lista é ordenada pelo nome dos fornecedores.
     *
     * @return lista com o nome dos fornecedores em que o cliente possui conta
     */
    public List<String> retornaNomesFornecedoresComContaDeCliente (String cpfCliente) {
        return this.fornecedores
                .values()
                .stream()
                .filter((Fornecedor fornecedor) -> fornecedor.existeContaCliente(cpfCliente))
                .map(Fornecedor::getNome)
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Verifica se o cliente possui conta com o fornecedor
     * e retorna o resultado dessa verificação.
     *
     * @param nomeFornecedor nome do fornecedor
     * @param cpfCliente cpf do cliente
     * @return resultado da verificação
     */
    public boolean existeContaDeClienteComFornecedor (String nomeFornecedor, String cpfCliente) {
        Consistencia.consisteNaoNuloNaoVazio(nomeFornecedor, "fornecedor");
        this.validaFornecedorExiste(nomeFornecedor, true);

        return this.fornecedores.get(nomeFornecedor).existeContaCliente(cpfCliente);
    }

    /**
     * Retorna uma lista em String contendo as
     * compras de um cliente com um fornecedor.
     *
     * @param cpfCliente cpf do cliente
     * @param nomeFornecedor nome do fornecedor
     * @return compras do cliente com o fornecedor
     */
    public List<String> retornaComprasDeClienteComFornecedor (String cpfCliente, String nomeFornecedor) {
        Consistencia.consisteNaoNuloNaoVazio(nomeFornecedor, "fornecedor");
        this.validaFornecedorExiste(nomeFornecedor, true);

        return this.fornecedores.get(nomeFornecedor).retornaComprasCliente(cpfCliente);
    }

    /**
     * Retorna uma lista em String contendo as compras de
     * um cliente com um fornecedor, realizadas em determinada data.
     *
     * @param cpfClinte cpf do cliente
     * @param nomeFornecedor nome do fornecedor
     * @param dataCompra data das compras
     * @return compras do cliente com o fornecedor
     */
    public List<String> retornaComprasDeClienteComFornecedorPorData (String cpfClinte, String nomeFornecedor, String dataCompra) {
        Consistencia.consisteNaoNuloNaoVazio(nomeFornecedor, "fornecedor");
        this.validaFornecedorExiste(nomeFornecedor, true);

        return this.fornecedores.get(nomeFornecedor).retornaComprasClientePorData(cpfClinte, dataCompra);
    }

    /**
     * Retorna uma lista em String contendo o nome de todos os fornecedores.
     * A lista é ordenada pelo nome dos fornecedores.
     *
     * @return lista em String com o nome dos fornecedores
     */
    public List<String> retornaNomesFornecedores () {
        return this.fornecedores
                .values()
                .stream()
                .sorted(Comparator.comparing(Fornecedor::getNome))
                .map(Fornecedor::getNome)
                .collect(Collectors.toList());
    }

    /**
     * Retorna uma lista em String contendo as datas
     * das compras dos clientes com os fornecedores.
     * A lista é ordenada pelas datas em ordem crescente.
     *
     * @return lista com as datas das compras dos clientes com os fornecedores
     */
    public List<String> retornaDatasComprasDeClientesComFornecedores () {
        return this.fornecedores
                .values()
                .stream()
                .flatMap((Fornecedor fornecedor) -> fornecedor.retornaDatasComprasDeClientes().stream())
                .sorted((String data1, String data2) -> {
                    String[] data1EmArray = data1.split("-");
                    String[] data2EmArray = data2.split("-");

                    for (int i = data1EmArray.length-1; i >= 0; i--) {
                        if (!data1EmArray[i].equals(data2EmArray[i])) {
                            return Integer.compare(Integer.parseInt(data1EmArray[i]), Integer.parseInt(data2EmArray[i]));
                        }
                    }

                    return 0;
                })
                .collect(Collectors.toList());
    }
}
