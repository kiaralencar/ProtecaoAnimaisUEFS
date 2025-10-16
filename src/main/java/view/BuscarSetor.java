package view;
import controller.GeralController;
import model.Setor;
import java.util.List;
import java.util.Scanner;

/** Classe responsável por gerenciar a interface de usuário
 * (View) para a busca e exibição de um relatório detalhado
 * de um objeto {@link Setor}.
 *
 * @author Kiara Alencar
 * @version 1.1
 * @see Setor
 * */
public class BuscarSetor {
    static Scanner scan = new Scanner(System.in);

    public static void exibirDados(){
        Setor setor = AtualizarSetor.buscar();
        System.out.println("--------------- RELATORIO DO SETOR ---------------");
        System.out.println("\nID: " + setor.getID());
        System.out.println("Nome: " + setor.getNome());
        System.out.println("Endereco: " + Setor.getEndereco());
        System.out.println("Tutores:");
        List<String> nomesTutores = GeralController.S.listarTutores(setor);
        for (String nome : nomesTutores){
            System.out.println(nome);
        }
        System.out.println("Animais:");
        List<String> nomesAnimais = GeralController.S.listarAnimais(setor);
        for (String nome : nomesAnimais){
            System.out.println(nome);
        }
        System.out.println("Aperte Enter para voltar ao menu de busca.");
        scan.nextLine();
    }
}
