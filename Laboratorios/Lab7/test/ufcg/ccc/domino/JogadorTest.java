package ufcg.ccc.domino;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import ufcg.ccc.domino.Jogada.TipoJogada;
import ufcg.ccc.domino.estrategia.EstrategiaDeJogo;
import ufcg.ccc.domino.estrategia.EstrategiaInvalidaException;
import ufcg.ccc.domino.estrategia.JogaPrimeiraPossivel;
import ufcg.ccc.domino.estrategia.VisaoDaMesa;

class JogadorTest {

	List<Peca> mao1 = List.of(new Peca(0, 2), new Peca(0, 3));

	@Test
	void testGetNumPecas() {
		Jogador jogador = new Jogador("j", new JogaPrimeiraPossivel(), mao1);
		assertEquals(2, jogador.getNumPecas());
	}

	@Test
	void testRemovePeca() {
		Jogador jogador = new Jogador("j", new JogaPrimeiraPossivel(), mao1);
		jogador.removeDaMao(mao1.get(0));
		assertEquals(1, jogador.getNumPecas());
	}
	
	@Test
	void testEstrategiaComBug() throws Exception {
		EstrategiaDeJogo errada = new EstrategiaDeJogo() {
			@Override
			public Jogada decideJogada(VisaoDaMesa mesa, List<Peca> mao) {
				return new Jogada(new Peca(6, 6), TipoJogada.NA_ESQUERDA);
			}
		};
		
		Jogador jogador = new Jogador("j", errada, mao1);
		
		try {
			jogador.decideJogada(new Mesa());
			fail("Deve jogar exceção em caso de decisão impossível da estratégia");
		} catch (EstrategiaInvalidaException e) {
			// esperada
		}
	}

	@Test
	void testRetornarSomaDosNumerosDasPecasDoJogadorComJogadorSemPecas () {
		List<Peca> maoJogador = List.of();
		Jogador jogador = new Jogador("jogador1", new JogaPrimeiraPossivel(), maoJogador);

		assertEquals(0, jogador.somaNumsPecas());
	}

	@Test
	void testRetornarSomaDosNumerosDasPecasDoJogadorComJogadorComPecas () {
		List<Peca> maoJogador1 = List.of(
				new Peca(4, 0),
				new Peca(0, 1)
		);
		List<Peca> maoJogador2 = List.of(
				new Peca(3, 2),
				new Peca(0, 1),
				new Peca(6, 1),
				new Peca(6, 6),
				new Peca(4, 5)
		);
		Jogador jogador1 = new Jogador("jogador1", new JogaPrimeiraPossivel(), maoJogador1);
		Jogador jogador2 = new Jogador("jogador2", new JogaPrimeiraPossivel(), maoJogador2);

		assertEquals(5, jogador1.somaNumsPecas());
		assertEquals(34, jogador2.somaNumsPecas());

		jogador1.removeDaMao(jogador1.getMao().get(0));
		jogador2.removeDaMao(jogador2.getMao().get(3));

		assertEquals(1, jogador1.somaNumsPecas());
		assertEquals(22, jogador2.somaNumsPecas());
	}

	@Test
	void testEstrategiaDecidePassarQuandoExistiaPecaQuePoderiaJogar() throws Exception {
		EstrategiaDeJogo estrategiaQueSemprePassa = (VisaoDaMesa mesa, List<Peca> mao) -> new Jogada();

		Mesa mesa = new Mesa();
		mesa.jogaNaDireita(new Peca(1,1));
		mesa.jogaNaEsquerda(new Peca(2,1));

		// Mao: 0:2, 0:3
		// Mesa: 2:1 1:1

		Jogador jogador = new Jogador("jogador", estrategiaQueSemprePassa, this.mao1);

		assertThrows(EstrategiaInvalidaException.class, () -> {
			jogador.decideJogada(mesa);
		});
	}

	@Test
	void testEstrategiaDecidePassarQuandoNaoExistePecasNaMesa () {
		EstrategiaDeJogo estrategiaQueSemprePassa = (VisaoDaMesa mesa, List<Peca> mao) -> new Jogada();

		Mesa mesa = new Mesa();

		// Mao: 0:2, 0:3
		// Mesa:

		Jogador jogador = new Jogador("jogador", estrategiaQueSemprePassa, this.mao1);

		assertThrows(EstrategiaInvalidaException.class, () -> {
			jogador.decideJogada(mesa);
		});
	}

	@Test
	void testEstrategiaDecidePassarQuandoNaoExistiaPecaQuePoderiaJogar() throws Exception {
		EstrategiaDeJogo estrategiaQueSemprePassa = (VisaoDaMesa mesa, List<Peca> mao) -> new Jogada();

		Mesa mesa = new Mesa();
		mesa.jogaNaDireita(new Peca(1,1));
		mesa.jogaNaEsquerda(new Peca(6,1));

		// Mao: 0:2, 0:3
		// Mesa: 6:1 1:1

		Jogador jogador = new Jogador("jogador", estrategiaQueSemprePassa, this.mao1);

		assertEquals(TipoJogada.PASSA, jogador.decideJogada(mesa).getTipo());
	}
}
