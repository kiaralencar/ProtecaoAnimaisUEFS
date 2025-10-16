package view;
import controller.GeralController;
import model.Endereco;
import model.Setor;
import model.Tutor;
import java.util.List;
import java.util.Scanner;

/** Classe responsável por gerenciar a interface de usuário (View) para a busca,
 * seleção e atualização de dados de um objeto {@link Tutor}.
 *
 * @author Kiara Alencar
 * @version 1.4
 * @see Tutor
 * @see Setor
 * @see Endereco
 * */
public class AtualizarTutor {
    static Scanner scan = new Scanner(System.in);

    public static Tutor buscar(){
        boolean tutorEscolhido = false;
        Tutor tutorEncontrado = null;
        String IDtutor;
        System.out.println("Insira o nome do tutor:");
        String nome = scan.nextLine();
        List<Tutor> tutores = GeralController.T.buscarTutorPorNome(nome.trim());
        while (tutores.isEmpty()){
            System.out.println("Nenhum tutor com este nome foi encontrado.");
            System.out.println("Insira o nome do tutor:");
            nome = scan.nextLine();
            tutores = GeralController.T.buscarTutorPorNome(nome.trim());
        }
        do {
            for (int i = 0; i < tutores.size(); i++) {
                Tutor tutor = tutores.get(i);
                System.out.println(tutor.getID() + " - " + tutor.getNome());
            }
            System.out.println("\nInsira o ID do tutor desejado: ");
            IDtutor = scan.nextLine();
            for (Tutor tutor : tutores) {
                if (tutor.getID().equalsIgnoreCase(IDtutor.trim())) {
                    tutorEncontrado = tutor;
                    tutorEscolhido = true;
                    break;
                }
            }
        } while (!tutorEscolhido);
        return tutorEncontrado;
    }

    public static void exibirMenu() {
        Tutor tutor = buscar();
        int opcao;
        System.out.println("\n--------------- ATUALIZAR TUTOR ---------------\n");
        System.out.println("Tutor escolhido: " + tutor.getNome() + " (" + tutor.getID() + ")");
        do {
            System.out.println("\nSelecione a opcao desejada:");
            System.out.println("[1] Atualizar ID");
            System.out.println("[2] Atualizar nome");
            System.out.println("[3] Atualizar endereco");
            System.out.println("[4] Atualizar telefone");
            System.out.println("[5] Atualizar email");
            System.out.println("[6] Atualizar setor");
            System.out.println("[0] Voltar ao menu de atualizacao");
            opcao = Main.validarOpcao();
            switch (opcao) {
                case 1:
                    atualizarID(tutor);
                    break;
                case 2:
                    atualizarNome(tutor);
                    break;
                case 3:
                    atualizarEndereco(tutor);
                    break;
                case 4:
                    atualizarTelefone(tutor);
                    break;
                case 5:
                    atualizarEmail(tutor);
                    break;
                case 6:
                    atualizarSetor(tutor);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao '" + opcao + "' eh invalida.");
                    System.out.println("Por favor, selecione um numero inteiro entre 0 e 6.");
                    System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
                    scan.nextLine();
                    break;
            }
        } while (opcao != 0);
    }

    public static void atualizarID(Tutor tutor){
        System.out.println("Insira o novo ID do tutor (T + numero. Ex.: T1): ");
        String ID = scan.nextLine();
        while (!GeralController.T.validarIDTutor(ID.trim())){
            System.out.println("ID invalido ou existente. Por favor, tente novamente.");
            ID = scan.nextLine();
        }
        boolean sucesso = GeralController.T.atualizarID(tutor, ID.trim().toUpperCase());
        if (sucesso) {
            System.out.println("\n✅ ID do tutor atualizado com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar o ID deste tutor.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }

    public static void atualizarNome(Tutor tutor){
        System.out.println("Insira o novo nome do tutor: ");
        String nome = scan.nextLine();
        boolean sucesso = GeralController.T.atualizarNome(tutor, nome.trim());
        if (sucesso) {
            System.out.println("\n✅ Nome do tutor atualizado com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar o nome deste tutor.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }

    public static void atualizarEndereco(Tutor tutor){
        Endereco novoEndereco = CadastrarTutor.lerEndereco();
        boolean sucesso = GeralController.T.atualizarEndereco(tutor, novoEndereco);
        if (sucesso) {
            System.out.println("\n✅ Endereco do tutor atualizado com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar o endereco deste tutor.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }

    public static void atualizarTelefone(Tutor tutor){
        System.out.println("Insira o novo telefone do tutor (11 numeros sem pontuacao): ");
        String telefone = scan.nextLine();
        while (!GeralController.T.validarTelefone(telefone)){
            System.out.println("Telefone invalido. Por favor, tente novamente. ");
            telefone = scan.nextLine();
        }
        boolean sucesso = GeralController.T.atualizarTelefone(tutor, telefone);
        if (sucesso) {
            System.out.println("\n✅ Telefone do tutor atualizado com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar o telefone deste tutor.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }

    public static void atualizarEmail(Tutor tutor){
        System.out.println("Insira o novo email do tutor: ");
        String email = scan.nextLine();
        while (!GeralController.T.validarEmail(email)){
            System.out.println("Email invalido. Por favor, tente novamente. ");
            email = scan.nextLine();
        }
        boolean sucesso = GeralController.T.atualizarEmail(tutor, email);
        if (sucesso) {
            System.out.println("\n✅ Email do tutor atualizado com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar o email deste tutor.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }

    public static void atualizarSetor(Tutor tutor){
        List<Setor> setores = GeralController.S.listarSetores();
        boolean setorEscolhido = false;
        Setor setorTutor = null;
        do {
            System.out.println("\nSetores ativos:");
            for (int i = 0; i < setores.size(); i++) {
                Setor setorAtivo = setores.get(i);
                System.out.println(setorAtivo.getID() + " - " + setorAtivo.getNome());
            }
            System.out.println("\nInsira o ID do novo setor do tutor: ");
            String IDsetor = scan.nextLine();
            for (Setor setor : setores){
                if (setor.getID().equalsIgnoreCase(IDsetor.trim())){
                    setorTutor = setor;
                    setorEscolhido = true;
                    break;
                }
            }
        } while (!setorEscolhido);
        boolean sucesso = GeralController.T.atualizarSetor(tutor, setorTutor);
        if (sucesso) {
            System.out.println("\n✅ Setor do tutor atualizado com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar o setor deste tutor.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }
}
