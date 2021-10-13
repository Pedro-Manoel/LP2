package controle;

import java.util.Scanner;

/**
 * Interface com menus para gerenciar uma sistema de controle de alunos.
 *
 * @author Pedro Manoel
 */
public class Main {

    /**
     * Objeto Scanner utilizado para a captura de valores da entrada padrão.
     */
    private static Scanner scanner;

    /**
     * Objeto ControleAlunos utilizado para gerenciar um sistema de controle de alunos.
     */
    private static ControleAlunos controleAlunos;

    /**
     * Ponto de início da execução da aplicação.
     *
     * @param args parâmetros que podem ser passados para a aplicação na sua execução
     */
    public static void main (String[] args) {
        scanner = new Scanner(System.in);
        controleAlunos = new ControleAlunos();

        while(true) {
            String opcao = menu();
            executaOpcao(opcao);
        }
    }

    /**
     * Exibe o menu da aplicação e retorna a opção escolhida pelo(a) usuário(a).
     *
     * @return opção escolhida pelo(a) usuário(a).
     */
    private static String menu () {
        System.out.print(
                "\n------------------| MENU |------------------\n" +
                "(C)adastrar Aluno\n" +
                "(E)xibir Aluno\n" +
                "(N)ovo Grupo\n" +
                "(A)locar Aluno no Grupo e Imprimir Grupos\n" +
                "(R)egistrar Aluno que Respondeu\n" +
                "(I)mprimir Alunos que Responderam\n" +
                "(O)ra, vamos fechar o programa!\n" +
                "\n" +
                "Opção> "
        );

        return scanner.nextLine().trim().toUpperCase();
    }

    /**
     * Interpreta a opção escolhida pelo(a) usuário(a) e executa uma ação
     * de acordo com a opção.
     *
     * @param opcao opção escolhida pelo(a) usuário(a)
     */
    private static void executaOpcao (String opcao) {
        switch (opcao) {
            case "C":
                cadastraAluno();
                break;
            case "E":
                exibeAluno();
                break;
            case "N":
                cadastraGrupoEstudo();
                break;
            case "A":
                alocaAlunoEmGrupoEstudoOuExibeGrupoEstudo();
                break;
            case "R":
                registraAlunoRespondeQuestoesQuadro();
                break;
            case "I":
                listaAlunosRespondeQuestoesQuadro();
                break;
            case "O":
                sair();
                break;
            default:
                System.out.println("\nOPÇÃO INVÁLIDA!");
        }
    }

    /**
     * Exibe uma mensagem na saída padrão e retorna a próxima leitura
     * da entrada padrão, do(a) usuário(a), no formato String.
     *
     * @param msg string a ser exibida na tela
     * @return entrada do(a) usuario(a) em String
     */
    private static String input (String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    /**
     * Cadastra um aluno no sistema de controle de alunos.
     */
    private static void cadastraAluno () {
        String matricula = input("\nMatrícula: ");
        String nome = input("Nome: ");
        String curso = input("Curso: ");

        System.out.println("\n" + controleAlunos.cadastraAluno(nome,curso,matricula));
    }

    /**
     * Exibe um aluno cadastrado no sistema de controle de alunos.
     */
    private static void exibeAluno () {
        String matricula = input("\nMatrícula: ");

        System.out.println("\n" + controleAlunos.consultaAluno(matricula));
    }

    /**
     * Cadastra um grupo de estudo no sistema de controle de alunos.
     */
    private static void cadastraGrupoEstudo () {
        String nome = input("\nGrupo: ");

        System.out.println("\n" + controleAlunos.cadastraGrupoEstudo(nome));
    }

    /**
     * Aloca um aluno em um grupo de estudo no sistema de controle de alunos.
     */
    private static void alocaAlunoEmGrupoEstudoOuExibeGrupoEstudo () {
        String opcao = input("\n(A)locar Aluno ou (I)mprimir Grupo? ").trim().toUpperCase();

        if (opcao.equals("A")) {
            String matriculaAluno = input("\nMatrícula: ");
            String nomeGrupoEstudo = input("Grupo: ");

            System.out.println("\n" + controleAlunos.alocaAlunoEmGrupoEstudo(matriculaAluno, nomeGrupoEstudo));
        } else if (opcao.equals("I")) {
            String nome = input("\nGrupo: ");

            System.out.println("\n" + controleAlunos.consultaGrupoEstudo(nome));
        } else {
            System.out.println("\nOPÇÃO INVÁLIDA!");
        }
    }

    /**
     * Registra um aluno, que responde as questões no quadro, no
     * sistema de controle de alunos.
     */
    private static void registraAlunoRespondeQuestoesQuadro () {
        String matricula = input("\nMatrícula: ");

        System.out.println("\n" + controleAlunos.registraAlunoRespondeQuestoesQuadro(matricula));
    }

    /**
     * Exibe os alunos, que respondem as questões no quadro, registrados no
     * sistema de controle de alunos.
     */
    private static void listaAlunosRespondeQuestoesQuadro () {
        System.out.println("\n" + controleAlunos.listaAlunosRespondeQuestoesQuadro());
    }

    /**
     * Encerra a execução da aplicação.
     */
    private static void sair () {
        System.out.println("\n ...Até a próxima :) \n");
        System.exit(0);
    }
}
