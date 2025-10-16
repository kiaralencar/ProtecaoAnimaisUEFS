package view;
import controller.GeralController;
import model.Animal;
import model.Setor;
import model.Tutor;
import java.util.Scanner;

/** Classe responsável por gerenciar a interface de usuário (View) para o menu
 * de deleção, em que o usuário decide qual entidade ele deseja deletar.
 *
 * @author Kiara Alencar
 * @version 1.2
 * */
public class DeletarMenu {

    /** Um objeto Scanner estático e compartilhado para gerenciar a entrada do usuário
     * em toda a aplicação. */
    static Scanner scan = new Scanner(System.in);

    /** Método responsável por exibir o menu de deleção.
     * Este menu lista as entidades que podem ser deletadas. */
    public static void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n---------------- MENU DE DELECAO ----------------");
            System.out.println("\nSelecione a opcao desejada:");
            System.out.println("[1] Deletar animal");
            System.out.println("[2] Deletar pessoa tutora");
            System.out.println("[3] Deletar setor responsável");
            System.out.println("[0] Voltar ao menu inicial");
            opcao = Main.validarOpcao();
            switch (opcao) {
                case 1:
                    deletarAnimal();
                    break;
                case 2:
                    deletarTutor();
                    break;
                case 3:
                    deletarSetor();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao '" + opcao + "' eh invalida.");
                    System.out.println("Por favor, selecione um numero inteiro entre 0 e 3.");
                    System.out.println("Aperte Enter para voltar ao menu de delecao.");
                    scan.nextLine();
                    break;
            }
        } while (opcao != 0);
    }

    /** Método responsável por deletar um animal do sistema. Porém, esta ação
     * apenas é concretizada após a confirmação do usuário. */
    public static void deletarAnimal(){
        Animal animal = AtualizarAnimal.buscar();
        System.out.println("\nTem certeza que deseja deletar este animal?\nOBS.: Todos os dados serão perdidos.");
        System.out.println("\n[1] Sim, tenho certeza.");
        System.out.println("[2] Nao, desejo voltar.");
        int resposta = Main.validarOpcao();
        while (resposta != 1 && resposta != 2){
            System.out.println("Opcao invalida. Por favor, selecione 1 ou 2.");
            resposta = Main.validarOpcao();
        }
        if (resposta == 1){
            boolean sucesso = GeralController.A.deletarAnimal(animal);
            if (sucesso){
                System.out.println("\n✅ Animal deletado com sucesso!");
            } else {
                System.out.println("\n❌ ERRO. Nao foi possivel deletar este animal.");
            }
        } else {
            return;
        }
        System.out.println("Aperte Enter para voltar ao menu inicial.");
        scan.nextLine();
    }

    /** Método responsável por deletar um tutor do sistema. Porém, esta ação
     * apenas é concretizada após a confirmação do usuário. */
    public static void deletarTutor(){
        Tutor tutor = AtualizarTutor.buscar();
        System.out.println("\nTem certeza que deseja deletar este tutor?\nOBS.: Todos os dados serão perdidos.");
        System.out.println("\n[1] Sim, tenho certeza.");
        System.out.println("[2] Nao, desejo voltar.");
        int resposta = Main.validarOpcao();
        while (resposta != 1 && resposta != 2){
            System.out.println("Opcao invalida. Por favor, selecione 1 ou 2.");
            resposta = Main.validarOpcao();
        }
        if (resposta == 1){
            boolean sucesso = GeralController.T.deletarTutor(tutor);
            if (sucesso){
                System.out.println("\n✅ Tutor deletado com sucesso!");
            } else {
                System.out.println("\n❌ ERRO. Nao foi possivel deletar este tutor.");
            }
        } else {
            return;
        }
        System.out.println("Aperte Enter para voltar ao menu inicial.");
        scan.nextLine();
    }

    /** Método responsável por deletar um setor do sistema. Porém, esta ação
     * apenas é concretizada após a confirmação do usuário. */
    public static void deletarSetor(){
        Setor setor = AtualizarSetor.buscar();
        System.out.println("\nTem certeza que deseja deletar este setor?\nOBS.: Todos os dados serão perdidos.");
        System.out.println("\n[1] Sim, tenho certeza.");
        System.out.println("[2] Nao, desejo voltar.");
        int resposta = Main.validarOpcao();
        while (resposta != 1 && resposta != 2){
            System.out.println("Opcao invalida. Por favor, selecione 1 ou 2.");
            resposta = Main.validarOpcao();
        }
        if (resposta == 1){
            boolean sucesso = GeralController.S.deletarSetor(setor);
            if (sucesso){
                System.out.println("\n✅ Setor deletado com sucesso!");
            } else {
                System.out.println("\n❌ ERRO. Nao foi possivel deletar este setor.");
            }
        } else {
            return;
        }
        System.out.println("Aperte Enter para voltar ao menu inicial.");
        scan.nextLine();
    }
}