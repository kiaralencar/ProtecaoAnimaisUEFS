package view;
import controller.GeralController;
import model.Animal;
import java.util.List;
import java.util.Scanner;

/** Classe responsável por gerenciar a interface de usuário (View) para o menu
 * de busca, em que o usuário decide qual entidade ele deseja buscar.
 *
 * @author Kiara Alencar
 * @version 1.1
 * */
public class BuscarMenu {

    /** Um objeto Scanner estático e compartilhado para gerenciar a entrada do usuário
     * em toda a aplicação. */
    static Scanner scan = new Scanner(System.in);

    /** Método responsável por exibir o menu de busca.
     * Este menu lista as entidades que podem ser procurados. */
    public static void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n---------------- MENU DE BUSCA ----------------");
            System.out.println("\nSelecione a opcao desejada:");
            System.out.println("[1] Relacao completa de animais");
            System.out.println("[2] Relatorio animal");
            System.out.println("[3] Relatorio pessoa tutora");
            System.out.println("[4] Relatorio setor responsavel");
            System.out.println("[0] Voltar ao menu inicial");
            opcao = Main.validarOpcao();
            switch (opcao) {
                case 1:
                    exibirAnimais();
                    break;
                case 2:
                    BuscarAnimal.exibirDados();
                    break;
                case 3:
                    BuscarTutor.exibirDados();
                    break;
                case 4:
                    BuscarSetor.exibirDados();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao '" + opcao + "' eh invalida.");
                    System.out.println("Por favor, selecione um numero inteiro entre 0 e 4.");
                    System.out.println("Aperte Enter para voltar ao menu de busca.");
                    scan.nextLine();
                    break;
            }
        } while (opcao != 0);
    }

    /** Método responsável por exibir todos os animais cadastrados no sistema. */
    public static void exibirAnimais(){
        List<Animal> animais = GeralController.A.listarAnimais();
        System.out.println("\n---------------- ANIMAIS CADASTRADOS ----------------\n");
        for (int i = 0; i < animais.size(); i++){
            Animal animal = animais.get(i);
            System.out.println(animal.getID() + " - " + animal.getNome());
        }
        System.out.println("Aperte Enter para voltar ao menu de busca.");
        scan.nextLine();
    }
}