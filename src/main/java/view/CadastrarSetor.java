package view;

import controller.GeralController;
import model.Animal;
import model.Setor;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastrarSetor {
    static Scanner scan = new Scanner(System.in);

    public static void cadastrar() {
        System.out.println("\n------------> CADASTRO DO SETOR\n");
        System.out.println("Insira o ID do setor (S + numero. Ex.: S1): ");
        String ID = scan.nextLine();
        while (!GeralController.S.validarIDSetor(ID)){
            System.out.println("ID invalido ou existente. Por favor, tente novamente.");
            ID = scan.nextLine();
        }
        System.out.println("Nome: ");
        String nome = scan.nextLine();
        while (!GeralController.S.validarNomeSetor(nome)){
            System.out.println("Nome invalido ou existente. Por favor, tente novamente.");
            nome = scan.nextLine();
        }
        Setor setor = GeralController.S.criarSetor(ID.trim().toUpperCase(), nome.trim(), new ArrayList<>(), new ArrayList<>());
        boolean cadastrado = GeralController.S.cadastrarSetor(setor);
        if (cadastrado) {
            System.out.println("\n✅ Setor cadastrado com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel cadastrar este setor.");
        }
        System.out.println("Aperte Enter para voltar ao menu de cadastro.");
        scan.nextLine();
    }
}
