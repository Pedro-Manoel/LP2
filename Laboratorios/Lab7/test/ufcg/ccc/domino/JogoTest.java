package ufcg.ccc.domino;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import ufcg.ccc.domino.estrategia.EstrategiaInvalidaException;
import ufcg.ccc.domino.estrategia.JogaPrimeiraPossivel;

class JogoTest {

	@Test
	void testRodadaInicial() throws JogadaInvalidaException, EstrategiaInvalidaException {
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), 14);

		assertEquals(0, j.getNumRodadas());
		assertEquals(14, j.getNumPecasJ1());
		assertEquals(14, j.getNumPecasJ2());

		j.rodada();

		assertEquals(1, j.getNumRodadas());
		assertEquals(13, j.getNumPecasJ1());
		assertEquals(13, j.getNumPecasJ2());
	}
	
	@Test
	void testJogoAleatorio() throws JogadaInvalidaException, EstrategiaInvalidaException {
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), 10, new Random(1));

		HistoricoDeJogo historico = j.jogaJogoCompleto();

		assertTrue(j.isFinalizado());
		assertEquals("J1", j.getVencedor());
		
		System.out.println(historico.toString());
	}

	@Test
	void testVencedorJ1Simples() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 0), new Peca(0, 1));
		List<Peca> mao2 = List.of(new Peca(0, 2), new Peca(0, 3));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		assertFalse(j.isFinalizado());
		assertEquals(null, j.getVencedor());

		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J1", j.getVencedor());
	}

	@Test
	void testVitoriaJ2() throws Exception {
		List<Peca> mao1 = List.of(new Peca(0, 0), new Peca(6, 6));
		List<Peca> mao2 = List.of(new Peca(0, 1), new Peca(1, 2));
		
		Jogo j = new Jogo("J1", new JogaPrimeiraPossivel(), "J2", new JogaPrimeiraPossivel(), mao1, mao2);

		j.jogaJogoCompleto();
		
		assertTrue(j.isFinalizado());
		assertEquals("J2", j.getVencedor());
	}

	@Test
	void testVitoriaDeJogador1PorTerMenosPecasNaMao () throws Exception {
		List<Peca> maoJogador1 = List.of(
				new Peca(0, 0),
				new Peca(4, 6)
		);
		List<Peca> maoJogador2 = List.of(
				new Peca(2, 1),
				new Peca(3, 5)
		);

		Jogo jogo = new Jogo("Jogador1", new JogaPrimeiraPossivel(), "Jogador2", new JogaPrimeiraPossivel(), maoJogador1, maoJogador2);

		jogo.jogaJogoCompleto();

		assertTrue(jogo.isFinalizado());
		assertEquals("Jogador1", jogo.getVencedor());
	}

	@Test
	void testVitoriaDeJogador2PorTerMenosPecasNaMao () throws Exception {
		List<Peca> maoJogador1 = List.of(
				new Peca(0, 0),
				new Peca(2, 6),
				new Peca(4, 6)
		);
		List<Peca> maoJogador2 = List.of(
				new Peca(0, 1),
				new Peca(3, 5)
		);

		Jogo jogo = new Jogo("Jogador1", new JogaPrimeiraPossivel(), "Jogador2", new JogaPrimeiraPossivel(), maoJogador1, maoJogador2);

		jogo.jogaJogoCompleto();

		assertTrue(jogo.isFinalizado());
		assertEquals("Jogador2", jogo.getVencedor());
	}

	@Test
	void testVitoriaDeJogador1PorTerUmaSomaMenorDosNumerosDasPecasNaMao () throws Exception {
		List<Peca> maoJogador1 = List.of(
				new Peca(0, 0),
				new Peca(4, 6)
		);
		List<Peca> maoJogador2 = List.of(
				new Peca(0, 1),
				new Peca(5, 6)
		);

		Jogo jogo = new Jogo("Jogador1", new JogaPrimeiraPossivel(), "Jogador2", new JogaPrimeiraPossivel(), maoJogador1, maoJogador2);

		jogo.jogaJogoCompleto();

		assertTrue(jogo.isFinalizado());
		assertEquals("Jogador1", jogo.getVencedor());
	}

	@Test
	void testVitoriaDeJogador2PorTerUmaSomaMenorDosNumerosDasPecasNaMao () throws Exception {
		List<Peca> maoJogador1 = List.of(
				new Peca(0, 0),
				new Peca(4, 4)
		);
		List<Peca> maoJogador2 = List.of(
				new Peca(0, 1),
				new Peca(4, 2)
		);

		Jogo jogo = new Jogo("Jogador1", new JogaPrimeiraPossivel(), "Jogador2", new JogaPrimeiraPossivel(), maoJogador1, maoJogador2);

		jogo.jogaJogoCompleto();

		assertTrue(jogo.isFinalizado());
		assertEquals("Jogador2", jogo.getVencedor());
	}

	@Test
	void testEmpatePorTerMesmoNumeroDePecasEMesmaSomaDeNumerosDePecasNasMaosDosJogadores () throws Exception {
		List<Peca> maoJogador1 = List.of(
				new Peca(0, 0),
				new Peca(4, 6)
		);
		List<Peca> maoJogador2 = List.of(
				new Peca(0, 1),
				new Peca(5, 5)
		);

		Jogo jogo = new Jogo("Jogador1", new JogaPrimeiraPossivel(), "Jogador2", new JogaPrimeiraPossivel(), maoJogador1, maoJogador2);

		jogo.jogaJogoCompleto();

		assertTrue(jogo.isFinalizado());
		assertNull(jogo.getVencedor());
	}

	@Test
	void testPontuacaoDeVitoriaPorBatidaSimples () throws Exception {
		List<Peca> maoJogador1 = List.of(
				new Peca(0, 0),
				new Peca(1, 6)
		);
		List<Peca> maoJogador2 = List.of(
				new Peca(0, 1),
 				new Peca(5, 5)
		);

		Jogo jogo = new Jogo("Jogador1", new JogaPrimeiraPossivel(), "Jogador2", new JogaPrimeiraPossivel(), maoJogador1, maoJogador2);

		jogo.jogaJogoCompleto();

		assertEquals(1, jogo.pontuacaoVencedor());
	}

	@Test
	void testPontuacaoDeVitoriaPorComparacaoDeNumeroDePecasNaMao () throws Exception {
		List<Peca> maoJogador1 = List.of(
				new Peca(0, 0),
				new Peca(4, 6)
		);
		List<Peca> maoJogador2 = List.of(
				new Peca(2, 1),
				new Peca(3, 5)
		);

		Jogo jogo = new Jogo("Jogador1", new JogaPrimeiraPossivel(), "Jogador2", new JogaPrimeiraPossivel(), maoJogador1, maoJogador2);

		jogo.jogaJogoCompleto();

		assertEquals(1, jogo.pontuacaoVencedor());
	}

	@Test
	void testPontuacaoDeVitoriaPorComparacaoDaSomaDosNumerosDasPecasNaMao () throws Exception {
		List<Peca> maoJogador1 = List.of(
				new Peca(0, 0),
				new Peca(4, 6)
		);
		List<Peca> maoJogador2 = List.of(
				new Peca(0, 1),
				new Peca(5, 6)
		);

		Jogo jogo = new Jogo("Jogador1", new JogaPrimeiraPossivel(), "Jogador2", new JogaPrimeiraPossivel(), maoJogador1, maoJogador2);

		jogo.jogaJogoCompleto();

		assertEquals(1, jogo.pontuacaoVencedor());
	}

	@Test
	void testPontuacaoDeEmpate () throws Exception {
		List<Peca> maoJogador1 = List.of(
				new Peca(0, 0),
				new Peca(4, 6)
		);
		List<Peca> maoJogador2 = List.of(
				new Peca(0, 1),
				new Peca(5, 5)
		);

		Jogo jogo = new Jogo("Jogador1", new JogaPrimeiraPossivel(), "Jogador2", new JogaPrimeiraPossivel(), maoJogador1, maoJogador2);

		jogo.jogaJogoCompleto();

		assertEquals(0, jogo.pontuacaoVencedor());
	}

	@Test
	void testPontuacaoDeVitoriaPorBatidaDeCarrocao () throws Exception {
		List<Peca> maoJogador1 = List.of(
				new Peca(0, 0),
				new Peca(4, 6)
		);
		List<Peca> maoJogador2 = List.of(
				new Peca(0, 1),
				new Peca(1, 1)
		);

		Jogo jogo = new Jogo("Jogador1", new JogaPrimeiraPossivel(), "Jogador2", new JogaPrimeiraPossivel(), maoJogador1, maoJogador2);

		jogo.jogaJogoCompleto();

		assertEquals(2, jogo.pontuacaoVencedor());
	}

	@Test
	void testPontuacaoDeVitoriaPorBatidaDeLaELo () throws Exception {
		List<Peca> maoJogador1 = List.of(
				new Peca(5, 0),
				new Peca(5, 1)
		);
		List<Peca> maoJogador2 = List.of(
				new Peca(3,5),
				new Peca(0, 3)
		);

		Jogo jogo = new Jogo("Jogador1", new JogaPrimeiraPossivel(), "Jogador2", new JogaPrimeiraPossivel(), maoJogador1, maoJogador2);

		jogo.jogaJogoCompleto();

		assertEquals(3, jogo.pontuacaoVencedor());
	}

	@Test
	void testPontuacaoDeVitoriaPorBatidaCruzada () throws Exception {
		List<Peca> maoJogador1 = List.of(
				new Peca(5, 2),
				new Peca(2, 1)
		);
		List<Peca> maoJogador2 = List.of(
				new Peca(2,5),
				new Peca(5, 5)
		);

		Jogo jogo = new Jogo("Jogador1", new JogaPrimeiraPossivel(), "Jogador2", new JogaPrimeiraPossivel(), maoJogador1, maoJogador2);

		jogo.jogaJogoCompleto();

		assertEquals(6, jogo.pontuacaoVencedor());
	}

	@Test
	void testRetornarPontuacaoDeVencedorEmJogoNaoFinalizado () throws Exception {
		List<Peca> maoJogador1 = List.of(
				new Peca(5, 0),
				new Peca(2, 1)
		);
		List<Peca> maoJogador2 = List.of(
				new Peca(5,0),
				new Peca(5, 5)
		);

		Jogo jogo = new Jogo("Jogador1", new JogaPrimeiraPossivel(), "Jogador2", new JogaPrimeiraPossivel(), maoJogador1, maoJogador2);

		assertThrows(IllegalArgumentException.class, () -> {
			int pontuacaoVencedor = jogo.pontuacaoVencedor();
		});
	}
}
