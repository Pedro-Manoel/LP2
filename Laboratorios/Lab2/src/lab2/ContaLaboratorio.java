package lab2;

/**
 * Representação de uma conta de um laboratório de computação.
 * A conta do laboratório é responsável por manter o registro
 * da quantidade de espaço utilizado.
 * A conta de laboratório possui um nome, um registro do espaço
 * utilizado e a cota limite de espaço disponível.
 *
 * @author Pedro Manoel
 */
public class ContaLaboratorio {
    /**
     * Nome do laboratório.
     */
    private String nome;

    /**
     * Quantidade de espaço utilizado.
     */
    private int espacoUtiliado;

    /**
     * Quantidade limite de espaço disponível.
     */
    private int cotaLimite;

    /**
     * Constrói uma conta de laboratório a partir do nome.
     * A cota limite, por padrão, é 2000.
     *
     * @param nomeLaboratorio nome do laboratório
     */
    public ContaLaboratorio (String nomeLaboratorio) {
        this.nome = nomeLaboratorio;
        this.cotaLimite = 2000;
    }

    /**
     * Constrói uma conta de laboratório a partir do nome e da cota limite.
     *
     * @param nomeLaboratorio nome do laboratório
     * @param cota cota limite de espaço disponível
     */
    public ContaLaboratorio (String nomeLaboratorio, int cota) {
        this(nomeLaboratorio);
        this.cotaLimite = cota;
    }

    /**
     * Adiciona espaço na quantidade de espaço utilizado no laboratório.
     *
     * @param mbytes quantidade de espaço a ser adicionado
     */
    public void consomeEspaco (int mbytes) {
        this.espacoUtiliado += mbytes;
    }

    /**
     * Remove espaço na quantidade de espaço utilizado no laboratório.
     *
     * @param mbytes quantidade de espaço a ser removido
     */
    public void liberaEspaco (int mbytes){
        this.espacoUtiliado -= mbytes;
    }

    /**
     * Retorna o resultado da seguinte verificação:
     * O espaço utilizado é maior ou igual ao espaço disponível.
     *
     * @return espaço utilizado é maior ou igual ao espaço disponível
     */
    public boolean atingiuCota (){
        return this.espacoUtiliado >= this.cotaLimite;
    }

    /**
     * Retorna o nome do laboratório
     *
     * @return nome do laboratório
     */
    public String getNome () {
        return nome;
    }

    /**
     * Retorna a String que representa a conta de laboratório.
     * A representação segue o seguinte formato:
     * "Nome do laboratório Espaço utilizado/Cota limite".
     *
     * @return representação da conta de laboratório em String
     */
    @Override
    public String toString () {
        return String.format(
                "%s %d/%d",
                this.nome,
                this.espacoUtiliado,
                this.cotaLimite
        );
    }
}
