package ufcg.ccc.domino.estrategia;

import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Peca;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Tenta sempre jogar os carroções da mão primeiro.
 * A prioridade é sempre tentar jogar os carroções de maior valor.
 * Primeiro tenta jogar o carroção no lado direito, caso não encaixe, tenta jogar no lado esquerdo.
 * Se a mão não possui carroções joga-se a primeira peça que encaixa em algum dos lados,
 * primeiro tentar jogar a peça no lado direito, caso não encaixe, tenta jogar no lado esquerdo.
 *
 * @author Pedro Manoel
 */
public class JogaCarrocoesPrimeiro implements EstrategiaDeJogo {

    /**
     * Decide a jogada na vez do jogador.
     *
     * @param mesa O estado atual da mesa, com as peças já jogadas.
     * @param mao  As peças disponíveis para o jogador.
     * @return Uma Jogada
     */
    @Override
    public Jogada decideJogada (VisaoDaMesa mesa, List<Peca> mao) {
        List<Peca> carrocoesDaMaoOrdenados =
                mao
                .stream()
                .filter(Peca::isCarrocao)
                .sorted(Comparator.comparing(Peca::somaNums).reversed())
                .collect(Collectors.toList());

        for (Peca peca : carrocoesDaMaoOrdenados) {
            if (mesa.getNumPecas() == 0 || peca.encaixa(mesa.getNumNaDireita())) {
                return new Jogada(peca, Jogada.TipoJogada.NA_DIREITA);
            }
            if (peca.encaixa(mesa.getNumNaEsquerda())) {
                return new Jogada(peca, Jogada.TipoJogada.NA_ESQUERDA);
            }
        }

		for (Peca peca : mao) {
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
    public String toString () { return "JogaCarrocoesPrimeiro"; }
}
