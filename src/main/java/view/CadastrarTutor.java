package view;
import controller.GeralController;
import model.Animal;
import model.Endereco;
import model.Setor;
import model.Tutor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Classe responsável por gerenciar a interface de usuário (View) para a coleta
 * e registro de dados de um novo objeto {@link Tutor} no sistema.
 *
 * @author Kiara Alencar
 * @version 1.7
 * @see Animal
 * @see Setor
 * @see Tutor
 * @see Endereco
 * */
public class CadastrarTutor {

    /** Um objeto Scanner estático e compartilhado para gerenciar a entrada do usuário
     * em toda a aplicação. */
    static Scanner scan = new Scanner(System.in);

    /** Coleta as informações de um endereço (rua, bairro, CEP, cidade e estado)
     * a partir da entrada do usuário.
     * <p>
     * O método valida o CEP para garantir que ele esteja no formato correto.
     * </p>
     * @return Um objeto {@link Endereco} criado com as informações fornecidas.
     */
    public static Endereco lerEndereco() {
        System.out.println("\nENDERECO COMPLETO");
        System.out.println("Rua/Avenida:");
        String rua = scan.nextLine();
        System.out.println("Bairro:");
        String bairro = scan.nextLine();
        System.out.println("CEP (8 numeros sem pontuacao):");
        String CEP = scan.nextLine();
        while (!GeralController.E.validarCEP(CEP)) {
            System.out.println("CEP invalido. Por favor, digite apenas 8 numeros.");
            CEP = scan.nextLine();
        }
        System.out.println("Cidade:");
        String cidade = scan.nextLine();
        System.out.println("Estado:");
        String estado = scan.nextLine();
        return GeralController.E.criarEndereco(rua, bairro, CEP, cidade, estado);
    }
    /** Método responsável por cadastrar um tutor, solicitando todos os dados
     * necessários e fazendo todas as devidas verificações. */
    public static void cadastrar() {
        List<Setor> setores = GeralController.S.listarSetores();
        if (setores.isEmpty()){
            System.out.println("Nao eh possivel cadastrar tutores,\npois nao ha setores cadastrados.");
            System.out.println("Aperte Enter para voltar ao menu de cadastro.");
            scan.nextLine();
            return;
        }
        String ID;
        Setor setorTutor = null;
        boolean setorEscolhido = false;
        System.out.println("\n------------> CADASTRO DO TUTOR\n");
        System.out.println("Insira o ID do tutor (T + numero. Ex.: T1): ");
        ID = scan.nextLine();
        while (!GeralController.T.validarIDTutor(ID)){
            System.out.println("ID invalido ou existente. Por favor, tente novamente.");
            ID = scan.nextLine();
        }
        System.out.println("Nome: ");
        String nome = scan.nextLine();
        Endereco endereco = lerEndereco();
        System.out.println("Telefone com DDD (11 numeros sem pontuacao): ");
        String telefone = scan.nextLine();
        while (!GeralController.T.validarTelefone(telefone)){
            System.out.println("Telefone invalido. Por favor, tente novamente. ");
            telefone = scan.nextLine();
        }
        System.out.println("E-mail: ");
        String email = scan.nextLine();
        while (!GeralController.T.validarEmail(email)){
            System.out.println("E-mail invalido. Por favor, tente novamente. ");
            email = scan.nextLine();
        }
        do {
            System.out.println("\nSetores ativos:");
            for (int i = 0; i < setores.size(); i++) {
                Setor setorAtivo = setores.get(i);
                System.out.println(setorAtivo.getID() + " - " + setorAtivo.getNome());
            }
            System.out.println("\nInsira o ID do setor escolhido: ");
            String IDsetor = scan.nextLine();
            for (Setor setor : setores){
                if (setor.getID().equalsIgnoreCase(IDsetor.trim())){
                    setorTutor = setor;
                    setorEscolhido = true;
                    break;
                }
            }
        } while (!setorEscolhido);
        List<String> animaisID = new ArrayList<>();
        for (Animal animal : setorTutor.getAnimais()){
            animaisID.add(animal.getID());
        }
        Tutor tutor = GeralController.T.criarTutor(ID.trim().toUpperCase(), nome.trim(),
                endereco, telefone, email, setorTutor.getID(), animaisID);
        boolean cadastrado = GeralController.T.cadastrarTutor(tutor);
        if (cadastrado) {
            System.out.println("\n✅ Tutor cadastrado com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel cadastrar este tutor.");
        }
        System.out.println("Aperte Enter para voltar ao menu de cadastro.");
        scan.nextLine();
    }
}
