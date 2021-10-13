package ufcg.ccc.domino.estrategia;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Peca;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tenta sempre jogar as peças de menor valor primeiro.
 * A prioridade é sempre tentar jogar a peça no lado direito, caso não encaixe,
 * tenta jogar no lado esquerdo.
 *
 * @author Pedro Manoel
 */
public class JogaPecasMenoresPrimeiro implements EstrategiaDeJogo {

    /**
     * Decide a jogada na vez do jogador.
     *
     * @param mesa O estado atual da mesa, com as peças já jogadas.
     * @param mao  As peças disponíveis para o jogador.
     * @return Uma Jogada
     */
    @Override
    public Jogada decideJogada (VisaoDaMesa mesa, List<Peca> mao) {
        List<Peca> pecasOrdenadasPeloMenorValor = mao
                .stream()
                .sorted(Comparator.comparing(Peca::somaNums))
                .collect(Collectors.toList());

        for (Peca peca : pecasOrdenadasPeloMenorValor) {
            if (mesa.getNumPecas() == 0 || peca.encaixa(mesa.getNumNaDireita())) {
                return new Jogada(peca, Jogada.TipoJogada.NA_DIREITA);
            }
            if (peca.encaixa(mesa.getNumNaEsquerda())) {
                return new Jogada(peca, Jogada.TipoJogada.NA_ESQUERDA);
            }
        }

        return new Jogada();
    }

    /**
     * Retorna a representação em String da estratégia de jogo.
     *
     * @return representação em String da estratégia de jogo
     */
    @Override
    public String toString () { return "JogaPecasMenoresPrimeiro"; }
}
