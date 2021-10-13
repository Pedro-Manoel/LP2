package utilidades;

import java.util.Objects;

/**
 * Classe de utilidades do sistema.
 *
 * @author Pedro Manoel
 */
public class Util {

	/**
	 * Testa se um objeto é nulo.
	 * Se o teste passar lança uma exceção.
	 *
	 * @param object objeto a ser testado
	 * @param mensagemExcecao mensagem da exceção
	 */
	public static void testaNull (Object object, String mensagemExcecao) {
		if (Objects.isNull(object)) {
			throw new NullPointerException(mensagemExcecao);
		}
	}

	/**
	 * Testa se uma String está vazia.
	 * Se o teste passar lança uma exceção.
	 *
	 * @param string string a ser testada
	 * @param mensagemExcecao mensagem da exceção
	 */
	public static void testaVazio (String string, String mensagemExcecao) {
		if (string.trim().isEmpty()) {
			throw new IllegalArgumentException(mensagemExcecao);
		}
	}

	/**
	 * Testa se uma String é nula ou está vazia.
	 * Se o teste passar lança uma exceção.
	 *
	 * @param string string a ser testada
	 * @param mensagemExcecao mensagem da exceção
	 */
	public static void testaNullOuVazio (String string, String mensagemExcecao) {
		testaNull(string, mensagemExcecao);
		testaVazio(string, mensagemExcecao);
	}
}
