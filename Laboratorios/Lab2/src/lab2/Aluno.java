package lab2;

import java.util.ArrayList;

/**
 * Representação de um Aluno.
 * Cada aluno possui um conjunto de disciplinas, de contas de laboratório
 * e contas de cantina, além de um estado de saúde.
 *
 * @author Pedro Manoel
 */
public class Aluno {
    /**
     * Conjunto de disciplinas do aluno.
     */
    private ArrayList<Disciplina> disciplinas;

    /**
     * Conjunto de contas de laboratório do aluno.
     */
    private ArrayList<ContaLaboratorio> contasDeLaboratorio;

    /**
     * Conjunto de contas de cantina do aluno.
     */
    private ArrayList<ContaCantina> contasDeCantina;

    /**
     * Estado geral de saúde do aluno.
     */
    private Saude saude;

    /**
     * Controí um Aluno.
     *
     */
    public Aluno () {
        this.disciplinas = new ArrayList<>();
        this.contasDeCantina = new ArrayList<>();
        this.contasDeLaboratorio = new ArrayList<>();
        this.saude = new Saude();
    }

    /**
     * Retorna uma conta de laboratório, se existir, no conjunto de
     * contas de laboratório do aluno.
     *
     * @param nomeLaboratorio nome do laboratório
     * @return conta de laboratório do aluno
     */
    private ContaLaboratorio getContaLaboratorio (String nomeLaboratorio) {
        for (ContaLaboratorio contaLaboratorio : this.contasDeLaboratorio) {
            if (contaLaboratorio.getNome().equals(nomeLaboratorio)) {
                return contaLaboratorio;
            }
        }
        return null;
    }

    /**
     * Retorna uma disciplina, se existir, no conjunto de disciplinas do aluno.
     *
     * @param nomeDisciplina nome da disciplina
     * @return disciplina do aluno
     */
    private Disciplina getDisciplina(String nomeDisciplina) {
        for (Disciplina disciplina : this.disciplinas) {
            if (disciplina.getNome().equals(nomeDisciplina)) {
                return disciplina;
            }
        }
        return null;
    }

    /**
     * Retorna uma conta de cantina, se existir, no conjunto de
     * contas de cantina do aluno.
     *
     * @param nomeCantina nome da cantina
     * @return conta de cantina do aluno
     */
    private ContaCantina getContaCantina (String nomeCantina) {
        for (ContaCantina contaCantina : this.contasDeCantina) {
            if (contaCantina.getNome().equals(nomeCantina)) {
                return contaCantina;
            }
        }
        return null;
    }

    /**
     * Realiza o cadastro de uma conta de laboratório, para o aluno,
     * com o nome do laboratório
     *
     * @param nomeLaboratorio nome do laboratório
     */
    public void cadastraLaboratorio (String nomeLaboratorio) {
        this.contasDeLaboratorio.add(new ContaLaboratorio(nomeLaboratorio));
    }

    /**
     * Realiza o cadastro de uma conta de laboratório, para o aluno,
     * com o nome e a cota limite do laboratório
     *
     * @param nomeLaboratorio nome do laboratório
     * @param cota cota limite de espaço disponível do laboratório
     */
    public void cadastraLaboratorio (String nomeLaboratorio, int cota) {
        this.contasDeLaboratorio.add(new ContaLaboratorio(nomeLaboratorio, cota));
    }

    /**
     * Adiciona espaço na quantidade de espaço utilizado do aluno no laboratório.
     *
     * @param nomeLaboratorio nome do laboratório
     * @param mbytes quantidade de espaço a ser adicionado
     */
    public void consomeEspaco (String nomeLaboratorio, int mbytes) {
        this.getContaLaboratorio(nomeLaboratorio).consomeEspaco(mbytes);
    }

    /**
     * Remove espaço na quantidade de espaço utilizado do aluno no laboratório.
     *
     * @param nomeLaboratorio nome do laboratório
     * @param mbytes quantidade de espaço a ser removido
     */
    public void liberaEspaco (String nomeLaboratorio, int mbytes) {
        this.getContaLaboratorio(nomeLaboratorio).liberaEspaco(mbytes);
    }

    /**
     * Retorna o resultado da seguinte verificação:
     * O espaço utilizado, do laboratório do aluno, é maior ou igual ao espaço disponível.
     *
     * @param nomeLaboratorio nome do laboratório
     * @return espaço utilizado é maior ou igual ao espaço disponível
     */
    public boolean atingiuCota (String nomeLaboratorio) {
        return this.getContaLaboratorio(nomeLaboratorio).atingiuCota();
    }

    /**
     * Retorna a String que representa a conta de laboratório do aluno.
     *
     * @param nomeLaboratorio nome do laboratório
     * @return representação da conta de laboratório em String
     */
    public String laboratorioToString (String nomeLaboratorio) {
        return this.getContaLaboratorio(nomeLaboratorio).toString();
    }

    /**
     * Realiza o cadastro de uma disciplina, para o aluno,
     * com o nome da disciplina.
     *
     * @param nomeDisciplina nome da disciplina
     */
    public void cadastraDisciplina (String nomeDisciplina) {
        this.disciplinas.add(new Disciplina(nomeDisciplina));
    }

    /**
     * Adiciona horas a quantidade de horas de estudo da disciplina do aluno.
     *
     * @param nomeDisciplina nome da diciplina
     * @param horas quantidade de horas
     */
    public void cadastraHoras (String nomeDisciplina, int horas) {
        this.getDisciplina(nomeDisciplina).cadastraHoras(horas);
    }

    /**
     * Cadastra uma nota na disciplina do aluno.
     *
     * @param nomeDisciplina nome da diciplina
     * @param nota qual nota vai ser cadastrada
     * @param valorNota valor da nota
     */
    public void cadastraNota (String nomeDisciplina, int nota, double valorNota) {
        this.getDisciplina(nomeDisciplina).cadastraNota(nota,valorNota);
    }

    /**
     * Retorna o resultado da seguinte verificação:
     * A média das notas, na disciplina do aluno, é maior ou igual a 7.
     * Se a verificação for verdadeira então o aluno esta, aprovado.
     *
     * @param nomeDisciplina nome da diciplina
     * @return aprovado ou reprovado na disciplina
     */
    public boolean aprovado (String nomeDisciplina) {
        return this.getDisciplina(nomeDisciplina).aprovado();
    }

    /**
     * Retorna a String que representa a disciplina do aluno.
     *
     * @param nomeDisciplina nome da diciplina
     * @return representação da disciplina em String
     */
    public String disciplinaToString (String nomeDisciplina) {
        return this.getDisciplina(nomeDisciplina).toString();
    }

    /**
     * Realiza o cadastrando de uma conta de cantina, para o aluno,
     * com o nome da cantina.
     *
     * @param nomeCantina nome da cantina
     */
    public void cadastraCantina (String nomeCantina) {
        this.contasDeCantina.add(new ContaCantina(nomeCantina));
    }

    /**
     * Cadastra um lanche do aluno na cantina
     *
     * @param nomeCantina nome da cantina
     * @param qtdItens quantidade de itens comprados
     * @param valorCentavos valor total do lanche em centavos
     */
    public void cadastraLanche (String nomeCantina, int qtdItens, int valorCentavos) {
        this.getContaCantina(nomeCantina).cadastraLanche(qtdItens, valorCentavos);
    }

    /**
     * Reduz do débito do aluno, na cantina, o valor que o aluno está pagando.
     *
     * @param nomeCantina nome da cantina
     * @param valorCentavos valor total do lanche em centavos
     */
    public void pagaConta (String nomeCantina, int valorCentavos) {
        this.getContaCantina(nomeCantina).pagaConta(valorCentavos);
    }

    /**
     * Retorna o valor que o aluno tem de débito com a cantina.
     *
     * @param nomeCantina nome da cantina
     * @return valor em débito com a cantina
     */
    public int getFaltaPagar (String nomeCantina){
        return this.getContaCantina(nomeCantina).getFaltaPagar();
    }

    /**
     * Retorna a String que representa a conta da cantina do aluno
     *
     * @param nomeCantina nome da cantina
     * @return representação da conta da cantina em String
     */
    public String cantinaToString (String nomeCantina){
        return this.getContaCantina(nomeCantina).toString();
    }

    /**
     * Define o estado da saúde mental do aluno.
     *
     * @param valor estado da saúde mental
     */
    public void defineSaudeMental (String valor) {
        this.saude.defineSaudeMental(valor);
    }

    /**
     * Define o estado da saúde física do aluno.
     *
     * @param valor estado da saúde física
     */
    public void defineSaudeFisica (String valor) {
        this.saude.defineSaudeFisica(valor);
    }

    /**
     * Retorna o estado geral da saúde do aluno.
     *
     * @return estado geral da saúde
     */
    public String getStatusGeral() {
        return this.saude.getStatusGeral();
    }

}
