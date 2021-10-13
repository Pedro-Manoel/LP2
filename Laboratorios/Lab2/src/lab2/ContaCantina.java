package lab2;

import java.util.ArrayList;

/**
 * Representação de uma conta em uma cantina.
 * A conta da cantina possui nome e débito.
 *
 * @author Pedro Manoel
 */
public class ContaCantina {
    /**
     * Nome da cantina.
     */
    private String nome;

    /**
     * Débito na cantina.
     */
    private int debito;

    /**
     * Débito total já cadastrado na cantina.
     */
    private int debitoTotalCadastrado;

    /**
     * Quantidade de itens comprados na cantina.
     */
    private int quantItens;

    /**
     * Detalhes dos lanches comprados na cantina.
     */
    private ArrayList<String> detalhesLanches;

    /**
     * Contrói uma conta de cantina a partir do nome da cantina.
     * O débito, por padrão, é 0.
     * O débito total já cadastrado, por padrão, é 0.
     * A quantidades de itens comprados, por padrão, é 0.
     *
     * @param nomeDaCantina nome da cantina
     */
    public ContaCantina (String nomeDaCantina) {
        this.nome = nomeDaCantina;
        this.debito = 0;
        this.debitoTotalCadastrado = 0;
        this.quantItens = 0;
        this.detalhesLanches = new ArrayList<>();
    }

    /**
     * Cadastra um lanche na cantina.
     *
     * @param qtdItens quantidade de itens comprados
     * @param valorCentavos valor total do lanche em centavos
     */
    public void cadastraLanche (int qtdItens, int valorCentavos) {
        this.quantItens += qtdItens;
        this.debito += valorCentavos;
        this.debitoTotalCadastrado += valorCentavos;
    }

    /**
     * Cadastra um lanche na cantina com os detalhes do lanche.
     *
     * @param qtdItens quantidade de itens comprados
     * @param valorCentavos valor total do lanche em centavos
     * @param detalhes detalhes do lanche comprado
     */
    public void cadastraLanche (int qtdItens, int valorCentavos, String detalhes) {
        this.cadastraLanche(qtdItens, valorCentavos);
        this.detalhesLanches.add(0, detalhes); // Adicionando os detalhes do lanche no início do ArrayList
    }

    /**
     * Reduz do débito na cantina o valor que está sendo pago.
     * Se o valor pago for maior que o débito
     * então não pode reduzir o débito,
     *
     * @param valorCentavos valor a pagar em centavos
     */
    public void pagaConta (int valorCentavos) {
		this.debito -= (valorCentavos > this.debito) ? 0 : valorCentavos;
    }

    /**
     * Retorna o valor do débito na cantina.
     *
     * @return valor do débito na cantina
     */
    public int getFaltaPagar ( ) {
        return this.debito;
    }

    /**
     * Retorna uma String com os últimos 5 detalhes de lanches cadastrados na cantina.
     * Cada detalhe deve esta em uma linha da String.
     *
     * @return últimos 5 detalhes cadastrados
     */
    public String listarDetalhes ( ) {
        StringBuilder listaDeDetalhes = new StringBuilder();
        int limiteListaDeDetalhes = 5;

        for (int i = 0; i < this.detalhesLanches.size(); i++) {
            listaDeDetalhes.append(this.detalhesLanches.get(i)).append("\n");
            if (i == limiteListaDeDetalhes - 1) { break; }
        }

        return listaDeDetalhes.toString().trim();
    }

    /**
     * Retorna o nome da cantina.
     *
     * @return nome da cantina
     */
    public String getNome () { return nome; }

    /**
     * Retorna a String que representa a conta da cantina.
     * A representação segue o seguinte formato:
     * "Nome da cantina Quantidade de itens comprados Débito total cadastrado"
     * Valor dos lanches cadastrados".
     *
     * @return representação da conta da cantina em String
     */
    @Override
    public String toString ( ) {
        return String.format(
                "%s %d %d",
                this.nome,
                this.quantItens,
                this.debitoTotalCadastrado
        );
    }
}