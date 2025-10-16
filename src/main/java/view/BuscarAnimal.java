package view;
import controller.GeralController;
import model.Animal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/** Classe responsável por gerenciar a interface de usuário
 * (View) para a busca e exibição de um relatório detalhado
 * de um objeto {@link Animal}.
 *
 * @author Kiara Alencar
 * @version 1.3
 * @see Animal
 * */
public class BuscarAnimal {

    /** Um objeto Scanner estático e compartilhado para gerenciar a entrada do usuário
     * em toda a aplicação. */
    static Scanner scan = new Scanner(System.in);

    /** Método responsável por exibir o relatório com todos os dados do animal. */
    public static void exibirDados(){
        try {
            Animal animal = AtualizarAnimal.buscar();
            if (animal == null) return;
            System.out.println("--------------- RELATORIO DO ANIMAL ---------------");
            System.out.println("\nID: " + animal.getID());
            System.out.println("Nome: " + animal.getNome());
            System.out.println("Data de nascimento: " + animal.getData().format(DateTimeFormatter.ofPattern("MM/yyyy")));
            System.out.println("Idade: " + GeralController.A.calcularIdade(animal.getData()) + " ano(s)");
            System.out.println("Especie: " + animal.getEspecie());
            System.out.println("Raca: " + animal.getRaca());
            System.out.println("Sexo: " + animal.getSexo());
            System.out.println("Situacao: " + animal.getSituacao());
            System.out.println("Setor: " + animal.getSetor().getNome());
            System.out.println("Tutores:");
            List<String> nomesTutores = GeralController.A.listarTutores(animal);
            for (String nome : nomesTutores){
                System.out.println(nome);
            }
            System.out.println("Aperte Enter para voltar ao menu de busca.");
            scan.nextLine();
        } catch (NullPointerException e){
            System.err.println("Ops! Ocorreu um erro: " + e.getMessage() + ".");
        }
    }
}