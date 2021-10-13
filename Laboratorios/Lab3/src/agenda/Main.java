package agenda;

import java.util.Scanner;

/**
 * Interface com menus para gerenciar uma Agenda de contatos.
 *
 * @author Pedro Manoel
 */
public class Main {

    /**
     * Ponto de início da execução da aplicação.
     *
     * @param args parâmetros que podem ser passados para a aplicação na sua execução
     */
    public static void main (String[] args) {
        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String opcao = menu(scanner);
            executaOpcao(opcao, agenda, scanner);
        }
    }

    /**
     * Exibe o menu da agenda e retorna a opção escolhida pelo(a) usuário(a).
     *
     * @param scanner objeto scanner utilizado para a captura de valores na entrada padrão
     * @return opção escolhido(a) pelo(a) usuário(a).
     */
    private static String menu(Scanner scanner) {
        System.out.print(
                "\n--------| MENU |--------\n" +
                "(C)adastrar Contato\n" +
                "(L)istar Contatos\n" +
                "(E)xibir Contato\n" +
                "(T)elefones preferidos\n" +
                "(Z)aps\n" +
                "(S)air\n" +
                "\n" +
                "Opção> "
        );
        return scanner.nextLine().trim().toUpperCase();
    }

    /**
     * Interpreta a opção escolhida pelo(a) usuário(a) e executa uma ação de acordo com
     * a opção.
     *
     * @param opcao   opção escolhido(a) pelo(a) usuário(a)
     * @param agenda  a agenda que está sendo gerenciada
     * @param scanner objeto scanner utilizado para a captura de valores na entrada padrão
     */
    private static void executaOpcao(String opcao, Agenda agenda, Scanner scanner) {
        switch (opcao) {
            case "C":
                cadastraContato(agenda, scanner);
                break;
            case "L":
                listarContatos(agenda);
                break;
            case "E":
                exibeContato(agenda, scanner);
                break;
            case "T":
                listarContatosTelefonePreferido(agenda);
                break;
            case "Z":
                listarContatosTelefoneWhatsapp(agenda);
                break;
            case "S":
                sair();
                break;
            default:
                System.out.println("\nOPÇÃO INVÁLIDA!");
        }
    }

    /**
     * Exibe uma mensagem na saída padrão e retorna a próxima leitura da entrada padrão,
     * do(a) usuário(a), no formato String.
     *
     * @param msg string a ser exibida na tela
     * @param scanner objeto scanner utilizado para a captura de valores na entrada padrão
     * @return entrada do(a) usuario(a) em String
     */
    private static String inputStr (String msg, Scanner scanner) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    /**
     * Exibe uma mensagem na saída padrão e retorna a próxima leitura da entrada padrão,
     * do(a) usuário(a), no formato int.
     *
     * @param msg string a ser exibida na tela
     * @param scanner objeto scanner utilizado para a captura de valores na entrada padrão
     * @return entrada do(a) usuario(a) em int
     */
    private static int inputInt (String msg, Scanner scanner) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Cadastra um contato na agenda.
     *
     * @param agenda a agenda que está sendo gerenciada
     * @param scanner objeto scanner utilizado para a captura de valores na entrada padrão
     */
    private static void cadastraContato (Agenda agenda, Scanner scanner) {
        int posicao = inputInt("\nPosição: ", scanner);
        String nome = inputStr("Nome: ", scanner);
        String sobrenome = inputStr("Sobrenome: ", scanner);

        String[] telefones = new String[Contato.NUM_MAX_TELEFONES];
        for (int i = 0; i < Contato.NUM_MAX_TELEFONES; i++) telefones[i] = inputStr(String.format("Telefone%d: ", i + 1), scanner);

        int posicaoTelefonePrioritario = inputInt("Telefone Prioritário: ", scanner);
        int posicaoTelefoneWhatsapp = inputInt("Contato Whatsapp: ", scanner);

        System.out.println("\n" + agenda.cadastraContato(posicao,nome,sobrenome,telefones,posicaoTelefonePrioritario,posicaoTelefoneWhatsapp));
    }

    /**
     * Exibe a lista de contatos da agenda.
     *
     * @param agenda a agenda que estamos gerenciando
     */
    private static void listarContatos (Agenda agenda) {
        System.out.println("\n" + agenda.listaContatos());
    }

    /**
     * Exibe um contato da agenda.
     *
     * @param agenda a agenda que estamos gerenciando
     * @param scanner objeto scanner utilizado para a captura de valores na entrada padrão
     */
    private static void exibeContato (Agenda agenda, Scanner scanner) {
        int posicao = inputInt("Contato> ", scanner);
        System.out.println("\n" + agenda.detalhesContato(posicao));
    }

    /**
     * Exibe a lista de contatos com seus respectivos telefones preferidos.
     *
     * @param agenda a agenda que estamos gerenciando
     */
    private static void listarContatosTelefonePreferido (Agenda agenda) {
        System.out.println("\n" + agenda.listaContatosTelefonePrioritario());
    }

    /**
     * Exibe a lista de contatos com seus respectivos telefones de Whatsapp.
     *
     * @param agenda a agenda que estamos gerenciando
     */
    private static void listarContatosTelefoneWhatsapp (Agenda agenda) {
        System.out.println("\n" + agenda.listaContatosTelefoneWhatsapp());
    }

    /**
     * Encerra a execução da aplicação.
     */
    private static void sair () {
        System.out.println("\n ...Até a próxima :) \n");
        System.exit(0);
    }
}
