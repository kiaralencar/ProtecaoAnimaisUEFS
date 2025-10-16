package view;
import java.util.Scanner;

/** Classe responsável por gerenciar a interface de usuário (View) para o menu
 * de cadastro, em que o usuário decide qual entidade ele deseja cadastrar.
 *
 * @author Kiara Alencar
 * @version 1.3
 * */
public class CadastrarMenu{
    static Scanner scan = new Scanner(System.in);
    public static void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--------------- MENU DE CADASTRO ---------------");
            System.out.println("\nSelecione a opcao desejada:");
            System.out.println("[1] Cadastrar animal");
            System.out.println("[2] Cadastrar pessoa tutora");
            System.out.println("[3] Cadastrar setor responsável");
            System.out.println("[0] Voltar ao menu inicial");
            opcao = Main.validarOpcao();
            switch (opcao) {
                case 1:
                    CadastrarAnimal.cadastrar();
                    break;
                case 2:
                    CadastrarTutor.cadastrar();
                    break;
                case 3:
                    CadastrarSetor.cadastrar();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao '" + opcao + "' eh invalida.");
                    System.out.println("Por favor, selecione um numero inteiro entre 0 e 3.");
                    System.out.println("Aperte Enter para voltar ao menu de cadastro.");
                    scan.nextLine();
                    break;
            }
        } while (opcao != 0);
    }
}