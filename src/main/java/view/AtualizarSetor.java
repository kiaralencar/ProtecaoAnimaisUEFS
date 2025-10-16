package view;
import controller.GeralController;
import model.Setor;
import java.util.List;
import java.util.Scanner;

public class AtualizarSetor {
    static Scanner scan = new Scanner(System.in);

    public static Setor buscar(){
        boolean setorEscolhido = false;
        Setor setorEncontrado = null;
        String IDsetor;
        System.out.println("Insira o nome do setor:");
        String nome = scan.nextLine();
        List<Setor> setores = GeralController.S.buscarSetorPorNome(nome.trim());
        while (setores.isEmpty()){
            System.out.println("Nenhum setor com este nome foi encontrado.");
            System.out.println("Insira o nome do setor:");
            nome = scan.nextLine();
            setores = GeralController.S.buscarSetorPorNome(nome.trim());
        }
        do {
            for (int i = 0; i < setores.size(); i++) {
                Setor setor = setores.get(i);
                System.out.println(setor.getID() + " - " + setor.getNome());
            }
            System.out.println("\nInsira o ID do setor desejado: ");
            IDsetor = scan.nextLine();
            for (Setor setor : setores) {
                if (setor.getID().equalsIgnoreCase(IDsetor.trim())) {
                    setorEncontrado = setor;
                    setorEscolhido = true;
                    break;
                }
            }
        } while (!setorEscolhido);
        return setorEncontrado;
    }

    public static void exibirMenu() {
        Setor setor = buscar();
        int opcao;
        System.out.println("\n--------------- ATUALIZAR SETOR ---------------\n");
        System.out.println("Setor escolhido: " + setor.getNome() + " (" + setor.getID() + ")");
        do {
            System.out.println("\nSelecione a opcao desejada:");
            System.out.println("[1] Atualizar ID");
            System.out.println("[2] Atualizar nome");
            System.out.println("[0] Voltar ao menu de atualizacao");
            opcao = Main.validarOpcao();
            switch (opcao) {
                case 1:
                    atualizarID(setor);
                    break;
                case 2:
                    atualizarNome(setor);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao '" + opcao + "' eh invalida.");
                    System.out.println("Por favor, selecione um numero inteiro entre 0 e 2.");
                    System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
                    scan.nextLine();
                    break;
            }
        } while (opcao != 0);
    }

    public static void atualizarID(Setor setor){
        System.out.println("Insira o novo ID do setor (S + numero. Ex.: S1): ");
        String ID = scan.nextLine();
        while (!GeralController.S.validarIDSetor(ID)){
            System.out.println("ID invalido ou existente. Por favor, tente novamente.");
            ID = scan.nextLine();
        }
        boolean sucesso = GeralController.S.atualizarID(setor, ID.trim().toUpperCase());
        if (sucesso) {
            System.out.println("\n✅ ID do setor atualizado com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar o ID deste setor.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }

    public static void atualizarNome(Setor setor){
        System.out.println("Insira o novo nome do setor: ");
        String nome = scan.nextLine();
        while (!GeralController.S.validarNomeSetor(nome)){
            System.out.println("Nome invalido ou existente. Por favor, tente novamente.");
            nome = scan.nextLine();
        }
        boolean sucesso = GeralController.S.atualizarNome(setor, nome.trim());
        if (sucesso) {
            System.out.println("\n✅ Nome do setor atualizado com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar o nome deste setor.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }
}
