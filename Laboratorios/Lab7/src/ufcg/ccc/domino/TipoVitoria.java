package ufcg.ccc.domino;

/**
 * Constantes que representam os tipos de vitória de um jogo de dominó.
 *
 * @author Pedro Manoel
 */
public enum TipoVitoria {

    /**
     * Vencer pela comparação da contagem de peças ou soma dos números das peças na mão dos jogadores.
     */
    CONTAGEM(1),

    /**
     * Bater com uma peça que contenha um número em qualquer uma das extremidades da mesa.
     */
    BATIDA_SIMPLES(1),

    /**
     * Bater o jogo com uma Carroça (peça com o mesmo número nas duas metades).
     */
    BATIDA_CARROCAO(2),

    /**
     * Bater o jogo com uma peça que contenha os mesmos números das extremidades das peças da mesa.
     */
    BATIDA_LA_E_LO(3),

    /**
     * Bater o jogo com uma Carroça que contenha os mesmos números das extremidades das peças da mesa.
     */
    BATIDA_CRUZADA(6);

    /**
     * Pontuação do tipo de vitória.
     */
    private final int pontuacao;

    /**
     * Contrói as constantes de tipos de vitória.
     *
     * @param pontuacao pontuação do tipo de vitória
     */
    TipoVitoria (int pontuacao) { this.pontuacao = pontuacao; }

    /**
     * Retorna a pontuação do tipo de vitória.
     *
     * @return pontuação do tipo de vitória
     */
    public int getPontuacao() { return this.pontuacao; }
}
