package ufcg.ccc.domino;

import ufcg.ccc.domino.estrategia.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Representação de uma disputa de muitos jogos entre várias estratégias.
 *
 * @author Pedro Manoel
 */
public class DominoCampeonatoBrutal {

    /**
     * Ponto de início da execução do DominoCampeonatoBrutal.
     *
     * @param args parâmetros que podem ser passados para o DominoCampeonatoBrutal na sua execução
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

        EstrategiaDeJogo[] estrategias = new EstrategiaDeJogo[]{
                new JogaPrimeiraPossivel(),
                new JogaCarrocoesPrimeiro(),
                new JogaPecasMaioresPrimeiro(),
                new JogaPecasMenoresPrimeiro()
        };

        Map<String, Integer> pontuacaoEstrategias = new HashMap<>();

        for (EstrategiaDeJogo estrategia: estrategias) {
            pontuacaoEstrategias.put(estrategia.toString(), 0);
        }

        for (int indiceEstrategia1 = 0; indiceEstrategia1 < estrategias.length - 1; indiceEstrategia1++) {
            for (int indiceEstrategia2 = indiceEstrategia1 + 1; indiceEstrategia2 < estrategias.length; indiceEstrategia2++) {
                for (int numJogo = 0; numJogo < QUANT_JOGOS; numJogo++) {
                    Jogo jogo;

                    if (numJogo < QUANT_JOGOS / 2) {
                        jogo = new Jogo(NOME_JOGADOR1, estrategias[indiceEstrategia1], NOME_JOGADOR2, estrategias[indiceEstrategia2], NUM_PECAS_INICIAL_JOGADORES); // Jogador1 começa jogando
                    } else {
                        jogo = new Jogo(NOME_JOGADOR2, estrategias[indiceEstrategia2], NOME_JOGADOR1, estrategias[indiceEstrategia1], NUM_PECAS_INICIAL_JOGADORES); // Jogador2 começa jogando
                    }

                    HistoricoDeJogo historico = jogo.jogaJogoCompleto();

                    if (!historico.isEmpate()) {
                        pontuacaoEstrategias.computeIfPresent(
                                historico.getVencedor().equals(NOME_JOGADOR1)
                                        ? estrategias[indiceEstrategia1].toString()
                                        : estrategias[indiceEstrategia2].toString(),
                                (String chave, Integer valor) -> valor + historico.getPontuacaoVencedor()
                        );
                    }
                }
            }
        }

        System.out.printf(
                "\n----------------------------------------------\n"+
                "            DominoCampeonatoBrutal                "+
                "\n----------------------------------------------\n"+
                "Jogos: %s"+
                "\n----------------------------------------------\n"+
                "%s"+
                "\n----------------------------------------------\n",
                QUANT_JOGOS,
                geraRankingEstrategias(pontuacaoEstrategias)
        );
    }

    /**
     * Gera e retorna um ranking em String de várias estratégias dadas as suas pontuações.
     *
     * @param pontuacaoEstrategias Map com a pontuação das estratégias
     * @return ranking em String de várias estratégias
     */
    private static String geraRankingEstrategias (Map<String, Integer> pontuacaoEstrategias) {
        StringBuilder rankingEstrategias = new StringBuilder();

        Map<String, Integer> pontuacaoEstrategiasOrdenadas =
                pontuacaoEstrategias
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.<String, Integer> comparingByValue().reversed())
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey, Map.Entry::getValue,
                                        (chave, valor) -> chave, LinkedHashMap::new
                                )
                        );

        int classificacaoEstrategia = 0;
        for (Map.Entry<String, Integer> pontuacaoEstrategia : pontuacaoEstrategiasOrdenadas.entrySet()) {
            rankingEstrategias.append(
                    String.format(
                            "(%s) - %s: %s pontos\n",
                            ++classificacaoEstrategia,
                            pontuacaoEstrategia.getKey(),
                            pontuacaoEstrategia.getValue()
                    )
            );
        }

        return rankingEstrategias.toString().trim();
    }
}
