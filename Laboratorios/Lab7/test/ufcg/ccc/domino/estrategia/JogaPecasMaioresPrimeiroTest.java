package ufcg.ccc.domino.estrategia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ufcg.ccc.domino.Jogada;
import ufcg.ccc.domino.Mesa;
import ufcg.ccc.domino.Peca;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe responsável pelos testes unitários da classe JogaPecasMaioresPrimeiro.
 *
 * @author Pedro Manoel
 */
class JogaPecasMaioresPrimeiroTest {

    private Mesa mesa;
    private EstrategiaDeJogo estrategia;

    @BeforeEach
    void criaMesaDeJogoECriaEstrategiaDeJogo() {
        this.mesa = new Mesa();
        this.estrategia = new JogaPecasMaioresPrimeiro();
    }

    @Test
    void testDecidePassar () throws Exception {
        this.mesa.jogaNaDireita(new Peca(1, 2));
        this.mesa.jogaNaEsquerda(new Peca(1, 1));

        // Mesa: 1:1 1:2

        Jogada jogada = this.estrategia.decideJogada(this.mesa,
                List.of(
                        new Peca(3, 3),
                        new Peca(0, 3)
                )
        );

        assertEquals(Jogada.TipoJogada.PASSA, jogada.getTipo());
    }

    @Test
    void testDecideJogarMaiorPecaEmMesaSemPecas () {
        // Mesa:

        Jogada jogada = this.estrategia.decideJogada(this.mesa,
                List.of(
                        new Peca(2, 2),
                        new Peca(4, 1),
                        new Peca(1, 0)
                )
        );

        assertEquals(Jogada.TipoJogada.NA_DIREITA, jogada.getTipo());
        assertEquals(4, jogada.getPeca().getNumEsquerdo());
        assertEquals(1, jogada.getPeca().getNumDireito());
    }

    @Test
    void testDecideJogarMaiorPecaEmMesaComPecas () throws Exception {
        this.mesa.jogaNaDireita(new Peca(1, 2));
        this.mesa.jogaNaEsquerda(new Peca(0, 1));

        // Mesa: 0:1 1:2

        Jogada jogada = this.estrategia.decideJogada(this.mesa,
                List.of(
                        new Peca(2, 2),
                        new Peca(4, 1),
                        new Peca(2, 1)
                )
        );

        assertEquals(Jogada.TipoJogada.NA_DIREITA, jogada.getTipo());
        assertEquals(2, jogada.getPeca().getNumEsquerdo());
        assertEquals(2, jogada.getPeca().getNumDireito());
    }

    @Test
    void testDecideJogarPrimeiraPecaDaMaoQuandoTodasAsPecasTemMesmoValorEmMesaSemPecas () {
        // Mesa:

        Jogada jogada = this.estrategia.decideJogada(this.mesa,
                List.of(
                        new Peca(2, 2),
                        new Peca(1, 3)
                )
        );

        assertEquals(Jogada.TipoJogada.NA_DIREITA, jogada.getTipo());
        assertEquals(2, jogada.getPeca().getNumEsquerdo());
        assertEquals(2, jogada.getPeca().getNumDireito());
    }

    @Test
    void testDecideJogarPrimeiraPecaDaMaoQuandoTodasAsPecasTemMesmoValorEmMesaComPecas () throws Exception {
        this.mesa.jogaNaDireita(new Peca(1, 2));
        this.mesa.jogaNaEsquerda(new Peca(3, 1));

        // Mesa: 3:1 1:2

        Jogada jogada = this.estrategia.decideJogada(this.mesa,
                List.of(
                        new Peca(2, 2),
                        new Peca(1, 3)
                )
        );

        assertEquals(Jogada.TipoJogada.NA_DIREITA, jogada.getTipo());
        assertEquals(2, jogada.getPeca().getNumEsquerdo());
        assertEquals(2, jogada.getPeca().getNumDireito());
    }

    @Test
    void testPrefereJogarMaiorPecaNaDireita () throws Exception {
        this.mesa.jogaNaDireita(new Peca(1, 2));
        this.mesa.jogaNaEsquerda(new Peca(3, 1));

        // Mesa: 3:1 1:2

        Jogada jogada = this.estrategia.decideJogada(this.mesa,
                List.of(
                        new Peca(2, 0),
                        new Peca(2, 3),
                        new Peca(2, 1)
                )
        );

        assertEquals(Jogada.TipoJogada.NA_DIREITA, jogada.getTipo());
        assertEquals(2, jogada.getPeca().getNumEsquerdo());
        assertEquals(3, jogada.getPeca().getNumDireito());
    }

    @Test
    void testDecideJogarMaiorPecaNaEsquerda () throws Exception {
        this.mesa.jogaNaDireita(new Peca(1, 1));
        this.mesa.jogaNaEsquerda(new Peca(2, 1));

        // Mesa: 2:1 1:1

        Jogada jogada = this.estrategia.decideJogada(this.mesa,
                List.of(
                        new Peca(3, 2),
                        new Peca(6, 2),
                        new Peca(2, 2)
                )
        );

        assertEquals(Jogada.TipoJogada.NA_ESQUERDA, jogada.getTipo());
        assertEquals(6, jogada.getPeca().getNumEsquerdo());
        assertEquals(2, jogada.getPeca().getNumDireito());
    }

    @Test
    void testToStringDeJogaPecasMaioresPrimeiro () {
        assertEquals("JogaPecasMaioresPrimeiro", this.estrategia.toString());
    }
}