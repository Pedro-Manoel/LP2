package ufcg.ccc.domino;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ufcg.ccc.domino.Jogada.TipoJogada;
import ufcg.ccc.domino.estrategia.EstrategiaDeJogo;
import ufcg.ccc.domino.estrategia.EstrategiaInvalidaException;

/**
 * Um jogo de dominó envolvendo 2 jogadores.
 *
 * @author Pedro Manoel
 */
public class Jogo {

	private Jogador jogador1;
	private Jogador jogador2;

	private final Mesa mesa;
	private int rodadasJogadas;

	private boolean finalizado;
	private String vencedor;

	/**
	 * Tipo de vitória do jogador vencedor.
	 */
	private TipoVitoria tipoVitoriaVencedor;

	/**
	 * Fatora código comum de inicialização.
	 */
	private Jogo() {
		this.rodadasJogadas = 0;
		this.finalizado = false;
		this.vencedor = null;
		this.mesa = new Mesa();
	}

	/**
	 * Para uso em testes apenas: cria, embaralha e distribui peças entre dois
	 * jogadores de maneira reprodutível. Sempre que o mesmo objeto Random for
	 * passado, as peças com cada jogador acabarão sendo as mesmas.
	 * 
	 * @param nomeJogador1            Id do jogador 1.
	 * @param estrategia1             Estratégia para o jogador 1.
	 * @param nomeJogador2            Id do jogador 2.
	 * @param estrategia2             Estratégia para o jogador 2.
	 * @param numPecasInicial         Número de peças a dar para cada jogador no
	 *                                início.
	 * @param geradorDeNumsAleatorios Objeto que determina as peças que cada um
	 *                                receberá após embaralhamento.
	 */
	protected Jogo(String nomeJogador1, EstrategiaDeJogo estrategia1, String nomeJogador2, EstrategiaDeJogo estrategia2,
			int numPecasInicial, Random geradorDeNumsAleatorios) {
		this();
		List<Peca> pecas = criaPecas();
		Collections.shuffle(pecas, geradorDeNumsAleatorios);

		List<Peca> maoJ1 = sorteiaMao(pecas, numPecasInicial);
		List<Peca> maoJ2 = sorteiaMao(pecas, numPecasInicial);

		this.jogador1 = new Jogador(nomeJogador1, estrategia1, maoJ1);
		this.jogador2 = new Jogador(nomeJogador2, estrategia2, maoJ2);
	}

	/**
	 * Cria, embaralha e distribui peças entre dois jogadores.
	 * 
	 * @param nomeJogador1    Id do jogador 1.
	 * @param estrategia1     Estratégia para o jogador 1.
	 * @param nomeJogador2    Id do jogador 2.
	 * @param estrategia2     Estratégia para o jogador 2.
	 * @param numPecasInicial Número de peças a dar para cada jogador no início.
	 */
	public Jogo(String nomeJogador1, EstrategiaDeJogo estrategia1, String nomeJogador2, EstrategiaDeJogo estrategia2,
			int numPecasInicial) {
		this(nomeJogador1, estrategia1, nomeJogador2, estrategia2, numPecasInicial, new Random());
	}

	/**
	 * 
	 * Para uso em testes: cria um jogo com peças escolhidas para a mão dos
	 * jogadores. Isso permite criar situações para testes de unidade mais
	 * facilmente.
	 * 
	 * @param nomeJogador1 Id do jogador 1.
	 * @param estrategia1  Estratégia para o jogador 1.
	 * @param nomeJogador2 Id do jogador 2.
	 * @param estrategia2  Estratégia para o jogador 2.
	 * @param maoJogador1  Mão do jogador 1.
	 * @param maoJogador2  Mão do jogador 2
	 */
	public Jogo(String nomeJogador1, EstrategiaDeJogo estrategia1, String nomeJogador2, EstrategiaDeJogo estrategia2,
			List<Peca> maoJogador1, List<Peca> maoJogador2) {
		this();
		this.jogador1 = new Jogador(nomeJogador1, estrategia1, new LinkedList<Peca>(maoJogador1));
		this.jogador2 = new Jogador(nomeJogador2, estrategia2, new LinkedList<Peca>(maoJogador2));
	}

	/**
	 * Sorteia peças para um jogador.
	 * 
	 * @param pecas           conjunto de peças total (será alterado)
	 * @param numPecasInicial Número de peças a retirar
	 * @return Peças retiradas.
	 */
	private List<Peca> sorteiaMao(List<Peca> pecas, int numPecasInicial) {
		List<Peca> mao = new LinkedList<Peca>();
		for (int i = 0; i < numPecasInicial; i++) {
			mao.add(pecas.remove(0));
		}
		return mao;
	}

	/**
	 * Cria o dominó.
	 * 
	 * @return Conjunto de 28 peças de 0:0 a 6:6
	 */
	private List<Peca> criaPecas() {
		List<Peca> pecas = new LinkedList<Peca>();

		for (int i = 0; i <= 6; i++) {
			for (int j = i; j <= 6; j++) {
				pecas.add(new Peca(i, j));
			}
		}

		return pecas;
	}

	/**
	 * @return Número de peças na mão do jogador 1.
	 */
	public int getNumPecasJ1() {
		return this.jogador1.getNumPecas();
	}

	/**
	 * @return Número de peças na mão do jogador 2.
	 */
	public int getNumPecasJ2() {
		return this.jogador2.getNumPecas();
	}

	/**
	 * Joga uma rodada do jogo. Ambos os jogadores fazem 1 jogada, iniciando pelo
	 * jogador 1. As exceções abaixo são necessárias para proteger o jogo de
	 * estratégias com bugs.
	 *
	 * @throws EstrategiaInvalidaException Se a estratégia de um dos jogadores
	 *                                     decidir jogar uma peça que não está
	 * 	                                   na mão do jogador ou decida passar
	 * 	                                   quando poderia ter jogada alguma peça.
	 *
	 * @throws JogadaInvalidaException     Se a peça escolhida por algum dos
	 *                                     jogadores não encaixar na mesa.
	 */
	public void rodada () throws EstrategiaInvalidaException, JogadaInvalidaException {
		this.rodadasJogadas += 1;

		Jogada jogadaJogador1 = this.jogador1.decideJogada(this.mesa);
		this.jogaJogada(this.jogador1, jogadaJogador1);

		if (this.jogador1.getNumPecas() == 0) {
			// Jogador1 venceu
			this.finalizado = true;
			this.vencedor = this.jogador1.getNome();
			this.tipoVitoriaVencedor = this.tipoVitoriaJogador(this.jogador1);
			return;
		}

		Jogada jogadaJogador2 = this.jogador2.decideJogada(this.mesa);
		this.jogaJogada(this.jogador2, jogadaJogador2);

		if (this.jogador2.getNumPecas() == 0) {
			// Jogador2 venceu
			this.finalizado = true;
			this.vencedor = this.jogador2.getNome();
			this.tipoVitoriaVencedor = this.tipoVitoriaJogador(this.jogador2);
			return;
		}

		if (jogadaJogador1.getTipo() == TipoJogada.PASSA && jogadaJogador2.getTipo() == TipoJogada.PASSA) {
			this.finalizado = true;

			if (this.jogador1.getNumPecas() < this.jogador2.getNumPecas() ||
					this.jogador1.somaNumsPecas() < this.jogador2.somaNumsPecas()) {
				// Jogador1 venceu
				this.vencedor = this.jogador1.getNome();
				this.tipoVitoriaVencedor = this.tipoVitoriaJogador(this.jogador1);
				return;
			}

			if (this.jogador2.getNumPecas() < this.jogador1.getNumPecas() ||
					this.jogador2.somaNumsPecas() < this.jogador1.somaNumsPecas()) {
				// Jogador2 venceu
				this.vencedor = this.jogador2.getNome();
				this.tipoVitoriaVencedor = this.tipoVitoriaJogador(this.jogador2);
			}
		}
	}

    /**
     * Retorna o tipo de vitória do jogador no jogo.
	 *
	 * @return tipo de vitória do jogador no jogo
	 */
	private TipoVitoria tipoVitoriaJogador (Jogador jogador) {
        if (jogador.getUltimaJogada().getTipo() == TipoJogada.PASSA) {
            return TipoVitoria.CONTAGEM;
        } else {
            boolean batidaLaELo = this.mesa.pecaEncaixaDireitaEsquerda(jogador.getUltimaJogada().getPeca());

            if (jogador.getUltimaJogada().getPeca().isCarrocao()) {
                if (batidaLaELo) {
                    return TipoVitoria.BATIDA_CRUZADA;
                }
				return TipoVitoria.BATIDA_CARROCAO;
            } else if (batidaLaELo) {
                return TipoVitoria.BATIDA_LA_E_LO;
            }

            return TipoVitoria.BATIDA_SIMPLES;
        }
	}

    /**
     * Retorna a pontuação do jogador vencedor do jogo.
     *
     * @return pontuação do jogador vencedor do jogo
     */
	public int pontuacaoVencedor () {
	    if (this.isFinalizado()) {
            return this.isResultadoEmpate()
                    ? 0 // pontuação de empate
                    : this.tipoVitoriaVencedor.getPontuacao(); // pontuação do tipo de vitória
	    }
	    throw new IllegalArgumentException("Jogo ainda não finalizado.");
    }

	/**
	 * Joga o jogo do ponto atual até o seu fim.
	 *
	 * @return histórico do jogo
	 * 
	 * @throws EstrategiaInvalidaException Se a estratégia de um dos jogadores
	 *                                     decidir jogar uma peça que não está
	 * 	                                   na mão do jogador ou decida passar
	 * 	                                   quando poderia ter jogada alguma peça.
	 *
	 * @throws JogadaInvalidaException     Se a peça escolhida por algum dos
	 *                                     jogadores não encaixar na mesa.
	 */
	public HistoricoDeJogo jogaJogoCompleto () throws EstrategiaInvalidaException, JogadaInvalidaException {
		HistoricoDeJogo historico = new HistoricoDeJogo(jogador1, jogador2);

		while (!this.isFinalizado()) {
			this.rodada();
			historico.addRodada(jogador1.getUltimaJogada(), jogador2.getUltimaJogada(), mesa);
		}
		
		if(this.isResultadoEmpate()) {
			historico.setResultadoEmpate();
		} else {
			historico.setVencedor(this.getVencedor());
            historico.setPontuacaoVencedor(this.pontuacaoVencedor());
			historico.setTipoVitoriaVencedor(this.tipoVitoriaVencedor);
		}

		return historico;
	}

	/**
	 * Faz a jogada decidida por um dos jogadores ter efeito. Quem realiza de fato
	 * as jogadas é essa classe (Jogo), e nào o Jogador ou a estratégia, para evitar
	 * que estratégias com erro modifiquem indevidamente a mesa ou dados do jogador.
	 * 
	 * @param jogador Jogador jogando
	 * @param jogada  A jogada a colocar em prática
	 * @throws JogadaInvalidaException Caso ela não possa ser jogada na mesa atual
	 */
	private void jogaJogada(Jogador jogador, Jogada jogada) throws JogadaInvalidaException {
		if (jogada.getTipo() == TipoJogada.PASSA) {
			return;
		}

		switch (jogada.getTipo()) {
		case NA_ESQUERDA: {
			this.mesa.jogaNaEsquerda(jogada.getPeca());
			break;
		}
		case NA_DIREITA: {
			this.mesa.jogaNaDireita(jogada.getPeca());
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + jogada.getTipo());
		}

		jogador.removeDaMao(jogada.getPeca());
	}

	@Override
	public String toString() {
		String o = jogador1.toString() + "\n" + jogador2.toString() + "\nMesa: " + mesa.toString();
		return o;
	}

	/**
	 * @return Número de jogadas já jogadas.
	 */
	public int getNumRodadas() {
		return rodadasJogadas;
	}

	/**
	 * O jogo está finalizado quando um dos jogadores não tem mais peças ou não é
	 * mais possível jogar para ambos.
	 * 
	 * @return Se o jogo está encerrado
	 */
	public boolean isFinalizado() {
		return this.finalizado;
	}

	/**
	 * Informa se o jogo terminou e foi empate. Retorna false se o jogo ainda não
	 * acabou.
	 * 
	 * @return true se o jogo acabou com empate.
	 */
	public boolean isResultadoEmpate() {
		return this.isFinalizado() && this.vencedor == null;
	}

	/**
	 * @return Id do vencedor, ou null caso o jogo não esteja finalizado.
	 */
	public String getVencedor() {
		return vencedor;
	}

}
