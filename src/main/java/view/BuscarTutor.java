package view;
import controller.GeralController;
import model.Tutor;
import java.util.List;
import java.util.Scanner;

/** Classe responsável por gerenciar a interface de usuário
 * (View) para a busca e exibição de um relatório detalhado
 * de um objeto {@link Tutor}.
 *
 * @author Kiara Alencar
 * @version 1.2
 * @see Tutor
 * */
public class BuscarTutor {

    /** Um objeto Scanner estático e compartilhado para gerenciar a entrada do usuário
     * em toda a aplicação. */
    static Scanner scan = new Scanner(System.in);

    /** Método responsável por exibir o relatório com todos os dados do tutor. */
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
        List<String> nomesAnimais = GeralController.T.listarAnimais(tutor);
        for (String nome : nomesAnimais){
            System.out.println(nome);
        }
        System.out.println("Aperte Enter para voltar ao menu de busca.");
        scan.nextLine();
    }
}
