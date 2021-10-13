package produto;

/**
 * Representação de um produto combo.
 * Cada produto combo tem um fator de desconto.
 * Cada produto tem um nome, uma descrição, e um preço.
 *
 * @author Pedro Manoel
 */
public class ProdutoCombo extends ProdutoAbstract {

    /**
     * Fator de desconto do produto combo.
     */
    private double fatorDesconto;

    /**
     * Contrói um produto combo a partir do nome, da descrição, do preço,
     * e do fator de desconto.
     *
     * @param nome nome do produto combo
     * @param descricao descrição do produto combo
     * @param preco preço do produto combo
     * @param fatorDesconto fator de desconto do produto combo
     */
    public ProdutoCombo (String nome, String descricao, double preco, double fatorDesconto) {
        super(nome, descricao, preco);
        this.fatorDesconto = fatorDesconto;
    }

    /**
     * Altera o fator de desconto do produto combo.
     *
     * @param fatorDesconto novo fator de desconto
     */
    public void setFatorDesconto (double fatorDesconto) { this.fatorDesconto = fatorDesconto; }

    /**
     * Retorna o preço do produto combo.
     *
     * @return o preço do produto combo
     */
    @Override
    public double getPreco () {
        return super.getPreco() - (super.getPreco() * this.fatorDesconto);
    }
}
