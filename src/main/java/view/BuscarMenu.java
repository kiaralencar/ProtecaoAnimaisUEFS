package view;
import java.util.Scanner;

public class BuscarMenu {
    static Scanner scan = new Scanner(System.in);
    public static void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n---------------- MENU DE BUSCA ----------------");
            System.out.println("\nSelecione a opcao desejada:");
            System.out.println("[1] Buscar animal");
            System.out.println("[2] Buscar pessoa tutora");
            System.out.println("[3] Buscar setor responsável");
            System.out.println("[0] Voltar ao menu inicial");
            opcao = Main.validarOpcao();
            switch (opcao) {
                case 1:
                    System.out.println("Voce selecionou 1");
                    // A implementar...
                    break;
                case 2:
                    System.out.println("Voce selecionou 2");
                    // A implementar...
                    break;
                case 3:
                    System.out.println("Voce selecionou 3");
                    // A implementar...
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao '" + opcao + "' eh invalida.");
                    System.out.println("Por favor, selecione um numero inteiro entre 0 e 3.");
                    System.out.println("Aperte Enter para voltar ao menu de busca.");
                    scan.nextLine();
                    break;
            }
        } while (opcao != 0);
    }
}