package ufcg.ccc.domino;

import ufcg.ccc.domino.estrategia.*;

import java.util.*;

/**
 * Representação de uma disputa de muitos jogos entre duas estratégias.
 *
 * @author Pedro Manoel
 */
public class DominoBrutalRepetido {

    /**
     * Ponto de início da execução do DominoBrutalRepetido.
     *
     * @param args parâmetros que podem ser passados para o DominoBrutalRepetido na sua execução
	 *
	 * @throws EstrategiaInvalidaException Se a estratégia de um dos jogadores
	 *                                     decidir jogar uma peça que não está
	 * 	                                   na mão do jogador ou decida passar
	 * 	                                   quando poderia ter jogada alguma peça.
	 *
	 * @throws JogadaInvalidaException     Se a peça escolhida por algum dos
	 *                                     jogadores não encaixar na mesa.
     */
	public static void main(String[] args) throws EstrategiaInvalidaException, JogadaInvalidaException {
		final int
                QUANT_JOGOS = 10000,
                NUM_PECAS_INICIAL_JOGADORES = 12;

		final String
                NOME_JOGADOR1 = "Jogador1", NOME_JOGADOR2 = "Jogador2";

		int
                vitoriasJogador1 = 0, pontuacaoJogador1 = 0,
                vitoriasJogador2 = 0, pontuacaoJogador2 = 0,
                empates = 0;

		EstrategiaDeJogo
				estrategiaJogador1 = new JogaPecasMenoresPrimeiro(),
				estrategiaJogador2 = new JogaPecasMenoresPrimeiro();

		Map<TipoVitoria, Integer>
				quantTiposVitoriajogador1 = new HashMap<>(),
		        quantTiposVitoriajogador2 = new HashMap<>();

		for (TipoVitoria tipoVitoria : TipoVitoria.values()) {
			quantTiposVitoriajogador1.put(tipoVitoria, 0);
			quantTiposVitoriajogador2.put(tipoVitoria, 0);
		}

		for (int numJogo = 0; numJogo < QUANT_JOGOS; numJogo++) {
            Jogo jogo;

            if (numJogo < QUANT_JOGOS / 2) {
                jogo = new Jogo(NOME_JOGADOR1, estrategiaJogador1, NOME_JOGADOR2, estrategiaJogador2, NUM_PECAS_INICIAL_JOGADORES); // Jogador1 começa jogando
            } else {
                jogo = new Jogo(NOME_JOGADOR2, estrategiaJogador2, NOME_JOGADOR1, estrategiaJogador1, NUM_PECAS_INICIAL_JOGADORES); // Jogador2 começa jogando
            }

            HistoricoDeJogo historico = jogo.jogaJogoCompleto();

			if (historico.isEmpate()) {
				empates++;
			} else if (historico.getVencedor().equals(NOME_JOGADOR1)) {
				vitoriasJogador1++;
				pontuacaoJogador1 += historico.getPontuacaoVencedor();
				quantTiposVitoriajogador1.computeIfPresent(historico.getTipoVitoriaVencedor(),
                        (TipoVitoria chave, Integer valor) -> valor + 1);
			} else {
                vitoriasJogador2++;
				pontuacaoJogador2 += historico.getPontuacaoVencedor();
                quantTiposVitoriajogador2.computeIfPresent(historico.getTipoVitoriaVencedor(),
                        (TipoVitoria chave, Integer valor) -> valor + 1);
			}
		}

		System.out.printf(
                "\n----------------------------------------------\n"+
                "             DominoBrutalRepetido                 "+
				"\n----------------------------------------------\n"+
				"Jogos: %s"+
				"\n----------------------------------------------\n"+
				"Empates: %s"+
				"\n----------------------------------------------\n"+
				"%s"+
				"\n----------------------------------------------\n"+
				"%s"+
				"\n----------------------------------------------\n",
				QUANT_JOGOS,
				empates,
				geraRelatorioJogador(NOME_JOGADOR1, estrategiaJogador1, vitoriasJogador1, pontuacaoJogador1, quantTiposVitoriajogador1),
				geraRelatorioJogador(NOME_JOGADOR2, estrategiaJogador2, vitoriasJogador2, pontuacaoJogador2, quantTiposVitoriajogador2)
		);
	}

	/**
	 * Gera e retorna um relatório em String sobre um jogador.
	 *
	 * @param nomeJogador nome do jogador
	 * @param estrategiaDeJogoJogador estratégia de jogo do jogador
	 * @param vitoriasJogador vitórias do jogador
	 * @param pontuacaoJogador pontuação do jogador
	 * @param quantTiposVitoriaJogador Map com a contagem dos tipos de vitória do jogador
	 * @return relatório em String do jogador
	 */
	private static String geraRelatorioJogador (String nomeJogador, EstrategiaDeJogo estrategiaDeJogoJogador, int vitoriasJogador, int pontuacaoJogador, Map<TipoVitoria, Integer> quantTiposVitoriaJogador) {
		return String.format(
				"%s - ( %s Vitórias ) - ( %s )\n"+
						"    Pontuação: %s\n"+
						"    Vitórias por Contagem:        %s\n"+
						"    Vitórias Batidas Simples:     %s\n"+
						"    Vitórias Batidas de Carroção: %s\n"+
						"    Vitórias Batidas de Lá e Lô:  %s\n"+
						"    Vitórias Batidas Cruzadas:    %s",
				nomeJogador,
				vitoriasJogador,
				estrategiaDeJogoJogador.toString(),
				pontuacaoJogador,
				quantTiposVitoriaJogador.get(TipoVitoria.CONTAGEM),
				quantTiposVitoriaJogador.get(TipoVitoria.BATIDA_SIMPLES),
				quantTiposVitoriaJogador.get(TipoVitoria.BATIDA_CARROCAO),
				quantTiposVitoriaJogador.get(TipoVitoria.BATIDA_LA_E_LO),
				quantTiposVitoriaJogador.get(TipoVitoria.BATIDA_CRUZADA)
		);
	}
}
