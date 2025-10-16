package view;

import controller.GeralController;
import model.Animal;
import model.Tutor;

import java.util.Scanner;

public class BuscarTutor {
    static Scanner scan = new Scanner(System.in);

    public static void exibirDados(){
        Tutor tutor = AtualizarTutor.buscar();
        System.out.println("--------------- RELATORIO DO TUTOR ---------------");
        System.out.println("\nID: " + tutor.getID());
        System.out.println("Nome: " + tutor.getNome());
        System.out.println("Endereco: " + tutor.getEndereco());
        System.out.println("Telefone: " + tutor.formatarTelefone());
        System.out.println("Email: " + tutor.getEmail());
        System.out.println("Setor: " + tutor.getSetor().getNome());
        System.out.println("Animais:");
        for (Animal animal : tutor.getAnimais()){
            System.out.println(animal.getNome());
        }
        System.out.println("Aperte Enter para voltar ao menu de busca.");
        scan.nextLine();
    }
}
