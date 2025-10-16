package view;
import model.Animal;
import model.Setor;
import model.Tutor;
import java.util.Scanner;

public class BuscarSetor {
    static Scanner scan = new Scanner(System.in);

    public static void exibirDados(){
        Setor setor = AtualizarSetor.buscar();
        System.out.println("--------------- RELATORIO DO SETOR ---------------");
        System.out.println("\nID: " + setor.getID());
        System.out.println("Nome: " + setor.getNome());
        System.out.println("Endereco: " + Setor.getEndereco());
        System.out.println("Tutores:");
        for (Tutor tutor : setor.getTutores()){
            System.out.println(tutor.getNome());
        }
        System.out.println("Animais:");
        for (Animal animal : setor.getAnimais()){
            System.out.println(animal.getNome());
        }
        System.out.println("Aperte Enter para voltar ao menu de busca.");
        scan.nextLine();
    }
}
