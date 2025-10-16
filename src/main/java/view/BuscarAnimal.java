package view;
import controller.GeralController;
import model.Animal;
import model.Tutor;
import java.util.Scanner;

public class BuscarAnimal {
    static Scanner scan = new Scanner(System.in);

    public static void exibirDados(){
        Animal animal = AtualizarAnimal.buscar();
        System.out.println("--------------- RELATORIO DO ANIMAL ---------------");
        System.out.println("\nID: " + animal.getID());
        System.out.println("Nome: " + animal.getNome());
        System.out.println("Especie: " + animal.getEspecie());
        System.out.println("Raca: " + animal.getRaca());
        System.out.println("Idade: " + GeralController.A.calcularIdade(animal.getData()));
        System.out.println("Sexo: " + animal.getSexo());
        System.out.println("Situacao: " + animal.getSituacao());
        System.out.println("Setor: " + animal.getSetor().getNome());
        System.out.println("Tutores:");
        for (Tutor tutor : animal.getTutores()){
            System.out.println(tutor.getNome());
        }
        System.out.println("Aperte Enter para voltar ao menu de busca.");
        scan.nextLine();
    }
}
