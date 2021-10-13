package saga.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe responsável pelos testes unitários da classe Consistencia.
 *
 * @author Pedro Manoel
 */
class ConsistenciaTest {

    @Test
    public void testVerificacaoDeUmValorNaoNuloNaoVazioPassandoValorNulo () {
        assertThrows(IllegalArgumentException.class, () -> {
            Consistencia.consisteNaoNuloNaoVazio(null, "nome");
        });
    }

    @Test
    public void testVerificacaoDeUmValorNaoNuloNaoVazioPassandoValorVazio () {
        assertThrows(IllegalArgumentException.class, () -> {
            Consistencia.consisteNaoNuloNaoVazio("", "nome");
        });
    }

    @Test
    public void testVerificacaoDeUmValorNaoNuloNaoVazioPassandoValorPreenchidoComEspacosEmBranco () {
        assertThrows(IllegalArgumentException.class, () -> {
            Consistencia.consisteNaoNuloNaoVazio("     ", "nome");
        });
    }

    @Test
    public void testVerificacaoDeUmValorNaoNuloNaoVazioPassandoValorNaoNuloNaoVazio () {
        Consistencia.consisteNaoNuloNaoVazio("marcos", "nome");
    }

    @Test
    public void testMensagemDeErroDeUmaVerificacaoDeUmValorNaoNuloNaoVazioComMsgDeErroNoMasculinoFalse () {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            Consistencia.consisteNaoNuloNaoVazio(null, "localização", false);
        });

        assertEquals("localização nao pode ser vazia ou nula.", excecao.getMessage());
    }

    @Test
    public void testMensagemDeErroDeUmaVerificacaoDeUmValorNaoNuloNaoVazioComMsgDeErroNoMasculinoTrue () {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            Consistencia.consisteNaoNuloNaoVazio(null, "cpf", true);
        });

        assertEquals("cpf nao pode ser vazio ou nulo.", excecao.getMessage());
    }

    @Test
    public void testMensagemDeErroDeUmaVerificacaoDeUmValorNaoNuloNaoVazioComComPontoNoFinalDaMsgErroFalse () {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            Consistencia.consisteNaoNuloNaoVazio(null, "cpf", true, false);
        });

        assertEquals("cpf nao pode ser vazio ou nulo", excecao.getMessage());
    }

    @Test
    public void testMensagemDeErroDeUmaVerificacaoDeUmValorNaoNuloNaoVazioComComPontoNoFinalDaMsgErroTrue () {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            Consistencia.consisteNaoNuloNaoVazio(null, "cpf", true, true);
        });

        assertEquals("cpf nao pode ser vazio ou nulo.", excecao.getMessage());
    }

    @Test
    public void testVerificacaoDeUmaDataComMaisDe10Caracteres () {
        assertThrows(IllegalArgumentException.class, () -> {
            Consistencia.consisteData("01/110/2002");
        });
    }

    @Test
    public void testVerificacaoDeUmaDataComMenosDe10Caracteres () {
        assertThrows(IllegalArgumentException.class, () -> {
            Consistencia.consisteData("01/1/2002");
        });
    }

    @Test
    public void testVerificacaoDeUmaDataCom10Caracteres () {
        Consistencia.consisteData("01/11/2002");
    }

    @Test
    public void testMensagemDeErroDeUmaVerificacaoDeUmData () {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            Consistencia.consisteData("01/11/20");
        });

        assertEquals("data invalida.", excecao.getMessage());
    }

    @Test
    public void testVerificacaoDeUmCpfComMaisDe11Caracteres () {
        assertThrows(IllegalArgumentException.class, () -> {
            Consistencia.consisteCpf("000000000000");
        });
    }

    @Test
    public void testVerificacaoDeUmCpfComMenosDe11Caracteres () {
        assertThrows(IllegalArgumentException.class, () -> {
            Consistencia.consisteCpf("000");
        });
    }

    @Test
    public void testVerificacaoDeUmCpfCom11Caracteres () {
        Consistencia.consisteCpf("00000000000");
    }

    @Test
    public void testMensagemDeErroDeUmaVerificacaoDeUmCpf () {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            Consistencia.consisteCpf("000");
        });

        assertEquals("cpf invalido.", excecao.getMessage());
    }

    @Test
    public void testVerificacaoDeUmValorNaoNegativoPassandoUmValorMenorQueZero () {
        assertThrows(IllegalArgumentException.class, () -> {
            Consistencia.consisteNaoNegativo(-1.00, "preco");
        });
    }

    @Test
    public void testVerificacaoDeUmNaoNegativoPassandoUmValorMaiorQueZero () {
        Consistencia.consisteNaoNegativo(1.00, "preco");
    }

    @Test
    public void testVerificacaoDeUmValorNaoNegativoPassandoUmValorIgualAZero () {
        Consistencia.consisteNaoNegativo(0.00, "preco");
    }

    @Test
    public void testMensagemDeErroDeUmaVerificacaoDeUmNaoNegativo () {
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            Consistencia.consisteNaoNegativo(-1, "valor");
        });

        assertEquals("valor invalido.", excecao.getMessage());
    }
}