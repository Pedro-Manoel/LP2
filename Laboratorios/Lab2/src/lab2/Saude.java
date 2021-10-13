package lab2;

/**
 * Representação de um estado geral de saúde.
 * A saúde geral está dividida em saúde mental e física:
 * E ambas podem estar num estado bom ou fraco.
 *
 * @author Pedro Manoel
 */
public class Saude {
    /**
     * Estado da saúde mental.
     * Pode ser "boa" ou "fraca".
     */
    private String saudeMental;

    /**
     * Estado da saúde física.
     * pode ser "boa" ou "fraca".
     */
    private String saudeFisica;

    /**
     * Emoji no formato de String que descreve a última sensação geral.
     */
    private String emojiSensacaoGeral;

    /**
     * Contrói um estado de saúde geral.
     * O estado da saúde física e mental, por padrão, é "boa".
     * O emoji de sensação geral, por padrão, é null.
     */
    public Saude () {
        this.saudeFisica = "boa";
        this.saudeMental = "boa";
        this.emojiSensacaoGeral = null;
    }

    /**
     * Define o estado da saúde mental.
     *
     * @param valor estado da saúde mental
     */
    public void defineSaudeMental (String valor) {
        this.saudeMental = valor;
        this.emojiSensacaoGeral = null;
    }

    /**
     * Define o estado da saúde física.
     *
     * @param valor estado da saúde física
     */
    public void defineSaudeFisica (String valor) {
        this.saudeFisica = valor;
        this.emojiSensacaoGeral = null;
    }

    /**
     * Define um emoji que descreve a última sensação geral.
     *
     * @param valor emoji de sensação geral
     */
    public void definirEmoji (String valor) {
        this.emojiSensacaoGeral = valor;
    }

    /**
     * Retorna o estado geral de saúde.
     * Se a saúde mental e física estiverem boas, a saúde geral é “boa”.
     * Se a saúde mental e física estiverem fracas, a saúde geral é “fraca”.
     * Se a saúde mental e física forem diferentes, a saúde geral é “ok".
     * Se um emoji de sensação geral estiver definido então deve ser retornando
     * adjunto com o estado geral de saúde.
     *
     * @return estado geral de saúde
     */
    public String getStatusGeral () {
        String statusGeral = (this.saudeFisica.equals(this.saudeMental))
                ? this.saudeFisica :
                "ok";
        return (this.emojiSensacaoGeral == null)
                ? statusGeral :
                String.format("%s %s",statusGeral,this.emojiSensacaoGeral);
    }
}
