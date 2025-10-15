package view;
import java.util.Scanner;

public class AtualizarMenu {
    static Scanner scan = new Scanner(System.in);
    public static void exibirMenu(){
        int opcao;
        do {
            System.out.println("\n------------- MENU DE ATUALIZACAO -------------");
            System.out.println("\nSelecione a opcao desejada:");
            System.out.println("[1] Atualizar animal");
            System.out.println("[2] Atualizar pessoa tutora");
            System.out.println("[3] Atualizar setor responsavel");
            System.out.println("[0] Voltar ao menu inicial");
            opcao = Main.validarOpcao();
            switch (opcao) {
                case 1:
                    AtualizarAnimal.exibirMenu();
                    break;
                case 2:
                    AtualizarTutor.exibirMenu();
                    break;
                case 3:
                    AtualizarSetor.exibirMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao '" + opcao + "' eh invalida.");
                    System.out.println("Por favor, selecione um numero inteiro entre 0 e 3.");
                    System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
                    scan.nextLine();
                    break;
            }
        } while (opcao != 0);
    }
}
