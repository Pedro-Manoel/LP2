package saga.util;

import java.util.Objects;

/**
 * Classe de validação da consistência dos dados da aplicação.
 * A classe Consistencia vai conter métodos estáticos que serão respónsaveis
 * por validar a consistência dos dados que a aplicação manipula, ela também é
 * respónsavel por lançar as devidas exceções caso os dados não consistam.
 *
 * @author Pedro Manoel
 */
public class Consistencia {

    /**
     * Verifica se uma String consiste em um
     * valor não nulo e não vazio.
     * Caso a String não consista lança uma exceção.
     *
     * @param string string que sera usada para a verificação de consistência
     * @param nomeMsgErro nome que sera utilizado na mensagem de erro da exceção
     * @param msgErroNoMasculino se a mensagem de erro da exceção deve estar no masculino
     * @param pontoNoFinalDaMsgErro se a mensagem de erro da exceção deve ter um ponto no final
     */
    public static void consisteNaoNuloNaoVazio (String string, String nomeMsgErro, boolean msgErroNoMasculino, boolean pontoNoFinalDaMsgErro) {
        if (Objects.isNull(string) || string.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    String.format(
                            "%s nao pode ser vazi%s ou nul%s%s",
                            nomeMsgErro,
                            msgErroNoMasculino ? "o" : "a",
                            msgErroNoMasculino ? "o" : "a",
                            pontoNoFinalDaMsgErro ? "." : ""
                    )
            );
        }
    }

    /**
     * Verifica se uma String consiste em um
     * valor não nulo e não vazio.
     * Caso a String não consista lança uma exceção.
     *
     * @param string string que sera usada para a verificação de consistência
     * @param nomeMsgErro nome que sera utilizado na mensagem de erro da exceção
     * @param msgErroNoMasculino se a mensagem de erro da exceção deve estar no masculino
     */
    public static void consisteNaoNuloNaoVazio (String string, String nomeMsgErro, boolean msgErroNoMasculino) {
        Consistencia.consisteNaoNuloNaoVazio(string, nomeMsgErro, msgErroNoMasculino, true);
    }

    /**
     * Verifica se uma String consiste em um
     * valor não nulo e não vazio.
     * Caso a String não consista lança uma exceção.
     *
     * @param string string que sera usada para a verificação de consistência
     * @param nomeMsgErro nome que sera utilizado na mensagem de erro da exceção
     */
    public static void consisteNaoNuloNaoVazio (String string, String nomeMsgErro) {
        Consistencia.consisteNaoNuloNaoVazio(string, nomeMsgErro, true, true);
    }

    /**
     * Verifica se uma String consiste em um
     * valor referente a um cpf.
     * Caso a String não consista lança uma exceção.
     *
     * @param cpf cpf que sera usado para a verificação de consistência
     */
    public static void consisteCpf (String cpf) {
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("cpf invalido.");
        }
    }

    /**
     * Verifica se uma String consiste em um
     * valor referente a uma data.
     * Caso a String não consista lança uma exceção.
     *
     * @param data data que sera usada para a verificação de consistência
     */
    public static void consisteData (String data) {
        if (data.length() != 10) {
            throw new IllegalArgumentException("data invalida.");
        }
    }

    /**
     * Verifica se um valor double consiste em um
     * valor não negativo.
     * Caso o valor double não consista lança uma exceção.
     *
     * @param valor valor que sera usada para a verificação de consistência
     * @param nomeMsgErro nome que sera utilizado na mensagem de erro da exceção
     */
    public static void consisteNaoNegativo (double valor, String nomeMsgErro) {
        if (valor < 0) {
            throw new IllegalArgumentException(
                    String.format("%s invalido.", nomeMsgErro)
            );
        }
    }
}
