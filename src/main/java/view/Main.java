package view;
import java.util.InputMismatchException;
import java.util.Scanner;

/** Classe principal do sistema "Proteção de Animais — UEFS".
 * <p>
 * Responsável por inicializar a aplicação (método main), exibir
 * o menu de navegação principal e delegar o controle para os módulos
 * específicos (cadastro, atualização, busca e deleção).
 *
 * @author Kiara Alencar
 * @version 1.3
 */
public class Main {

    /** Um objeto Scanner estático e compartilhado para gerenciar a entrada do usuário
     * em toda a aplicação. */
    static Scanner scan = new Scanner(System.in);

    /** Valida a entrada do usuário para garantir que seja um número inteiro.
     * O método continua solicitando a entrada até que um valor válido seja fornecido,
     * prevenindo exceções de tipo (`InputMismatchException`).
     *
     * @return O valor inteiro digitado pelo usuário.
     */
    public static int validarOpcao() {
        int opcao = -1;
        boolean opcaoValida = false;
        do {
            try {
                opcao = scan.nextInt();
                scan.nextLine();
                opcaoValida = true;
            } catch (InputMismatchException e) {
                System.out.println("INVALIDO. Por favor, digite um número inteiro.");
                scan.nextLine();
            }
        } while (!opcaoValida);
        return opcao;
    }

    /** Método principal, responsável pelo o fluxo do programa. */
    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n🐾🐾🐾 PROTECAO DE ANIMAIS — UEFS 🐾🐾🐾");
            System.out.println("\nSelecione a opcao desejada:");
            System.out.println("[1] Cadastrar");
            System.out.println("[2] Atualizar");
            System.out.println("[3] Buscar");
            System.out.println("[4] Deletar");
            System.out.println("[0] Encerrar o sistema");
            opcao = validarOpcao();
            switch (opcao){
                case 1:
                    CadastrarMenu.exibirMenu();
                    break;
                case 2:
                    AtualizarMenu.exibirMenu();
                    break;
                case 3:
                    BuscarMenu.exibirMenu();
                    break;
                case 4:
                    DeletarMenu.exibirMenu();
                    break;
                case 0:
                    System.out.println("\n🐾🐾🐾 Sistema encerrado! 🐾🐾🐾");
                    break;
                default:
                    System.out.println("Opcao '" + opcao + "' eh invalida.");
                    System.out.println("Por favor, selecione um numero inteiro entre 0 e 4.");
                    System.out.println("Aperte Enter para voltar ao menu inicial.");
                    scan.nextLine();
                    break;
            }
        } while (opcao != 0);
    }
}