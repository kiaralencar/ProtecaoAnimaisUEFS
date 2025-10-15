package view;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);

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
                    System.out.println("Voce selecionou 3");
                    // A implementar...
                    break;
                case 4:
                    System.out.println("Voce selecionou 4");
                    // A implementar...
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