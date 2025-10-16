package view;
import controller.GeralController;
import model.Animal;
import model.Setor;
import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Classe responsável por gerenciar a interface de usuário (View) para a busca,
 * seleção e atualização de dados de um objeto {@link Animal}.
 *
 * @author Kiara Alencar
 * @version 1.6
 * @see Animal
 * @see Setor
 * */
public class AtualizarAnimal {

    /** Um objeto Scanner estático e compartilhado para gerenciar a entrada do usuário
     * em toda a aplicação. */
    static Scanner scan = new Scanner(System.in);

    /** Método responsável por buscar o animal desejado pelo usuário.
     * Primeiramente, é solocitado o nome do animal. Depois de serem
     * listados todos os animais com o nome inserido, é solicitado
     * o ID do animal desejado.
     *
     * @return Um objeto do tipo {@link Animal}
     */
    public static Animal buscar(){
        boolean animalEscolhido = false;
        Animal animalEncontrado = null;
        String IDanimal;
        System.out.println("Insira o nome do animal:");
        String nome = scan.nextLine();
        List<Animal> animais = GeralController.A.buscarAnimalPorNome(nome.trim());
        while (animais.isEmpty()){
            System.out.println("Nenhum animal com este nome foi encontrado.");
            System.out.println("Insira o nome do animal:");
            nome = scan.nextLine();
            animais = GeralController.A.buscarAnimalPorNome(nome.trim());
        }
        do {
            for (int i = 0; i < animais.size(); i++) {
                Animal animal = animais.get(i);
                System.out.println(animal.getID() + " - " + animal.getNome());
            }
            System.out.println("\nInsira o ID do animal desejado: ");
            IDanimal = scan.nextLine();
            for (Animal animal : animais) {
                if (animal.getID().equalsIgnoreCase(IDanimal.trim())) {
                    animalEncontrado = animal;
                    animalEscolhido = true;
                    break;
                }
            }
        } while (!animalEscolhido);
        return animalEncontrado;
    }

    /** Método responsável por exibir o menu de atualização do animal.
     * Este menu lista as opções de atualização disponíveis no sistema. */
    public static void exibirMenu() {
        Animal animal = buscar();
        int opcao;
        System.out.println("\n--------------- ATUALIZAR ANIMAL ---------------\n");
        System.out.println("Animal escolhido: " + animal.getNome() + " (" + animal.getID() + ")");
        do {
            System.out.println("\nSelecione a opcao desejada:");
            System.out.println("[1] Atualizar ID");
            System.out.println("[2] Atualizar nome");
            System.out.println("[3] Atualizar especie");
            System.out.println("[4] Atualizar raca");
            System.out.println("[5] Atualizar data de nascimento");
            System.out.println("[6] Atualizar sexo");
            System.out.println("[7] Atualizar situacao");
            System.out.println("[8] Atualizar setor");
            System.out.println("[0] Voltar ao menu de atualizacao");
            opcao = Main.validarOpcao();
            switch (opcao) {
                case 1:
                    atualizarID(animal);
                    break;
                case 2:
                    atualizarNome(animal);
                    break;
                case 3:
                    atualizarEspecie(animal);
                    break;
                case 4:
                    atualizarRaca(animal);
                    break;
                case 5:
                    atualizarData(animal);
                    break;
                case 6:
                    atualizarSexo(animal);
                    break;
                case 7:
                    atualizarSituacao(animal);
                    break;
                case 8:
                    atualizarSetor(animal);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao '" + opcao + "' eh invalida.");
                    System.out.println("Por favor, selecione um numero inteiro entre 0 e 8.");
                    System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
                    scan.nextLine();
                    break;
            }
        } while (opcao != 0);
    }

    /** Método responsável por atualizar o ID do animal.
     * O ID é solicitado e validado antes de ser atualizado. */
    public static void atualizarID(Animal animal){
        System.out.println("Insira o novo ID do animal (A + numero. Ex.: A1): ");
        String ID = scan.nextLine();
        while (!GeralController.A.validarIDAnimal(ID)){
            System.out.println("ID invalido ou existente. Por favor, tente novamente.");
            ID = scan.nextLine();
        }
        boolean sucesso = GeralController.A.atualizarID(animal, ID.trim().toUpperCase());
        if (sucesso) {
            System.out.println("\n✅ ID do animal atualizado com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar o ID deste animal.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }

    /** Método responsável por atualizar o nome do animal. */
    public static void atualizarNome(Animal animal){
        System.out.println("Insira o novo nome do animal: ");
        String nome = scan.nextLine();
        boolean sucesso = GeralController.A.atualizarNome(animal, nome);
        if (sucesso) {
            System.out.println("\n✅ Nome do animal atualizado com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar o nome deste animal.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }

    /** Método responsável por atualizar a espécie do animal. */
    public static void atualizarEspecie(Animal animal){
        System.out.println("Insira a nova especie do animal: ");
        String especie = scan.nextLine();
        boolean sucesso = GeralController.A.atualizarEspecie(animal, especie);
        if (sucesso) {
            System.out.println("\n✅ Especie do animal atualizada com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar a especie deste animal.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }

    /** Método responsável por atualizar a raça do animal. */
    public static void atualizarRaca(Animal animal){
        System.out.println("Insira a nova raca do animal: ");
        String raca = scan.nextLine();
        boolean sucesso = GeralController.A.atualizarRaca(animal, raca);
        if (sucesso) {
            System.out.println("\n✅ Raca do animal atualizada com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar a raca deste animal.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }
    /** Método responsável por atualizar a data de nascimento do animal.
     * A data é solicitada e validada antes de ser atualizada. */
    public static void atualizarData(Animal animal){
        boolean dataValida = false;
        int mes = 0, ano = 0;
        do {
            try {
                System.out.print("Mes de nascimento: ");
                mes = Main.validarOpcao();
                System.out.print("Ano de nascimento: ");
                ano = Main.validarOpcao();
                dataValida = GeralController.A.validarData(YearMonth.of(ano, mes));
            } catch (DateTimeException e) {
                System.err.println("ERRO. Data invalida: " + e.getMessage());
                scan.nextLine();
            }
        } while (!dataValida);
        boolean sucesso = GeralController.A.atualizarData(animal, YearMonth.of(ano, mes));
        if (sucesso) {
            System.out.println("\n✅ Data de nascimento do animal atualizada com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar a data de nascimento deste animal.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }

    /** Método responsável por atualizar o sexo do animal. */
    public static void atualizarSexo(Animal animal){
        System.out.println("Insira o novo sexo do animal [F/M]: ");
        String sexo = scan.nextLine();
        while (!sexo.trim().equalsIgnoreCase("F") && !sexo.trim().equalsIgnoreCase("M")){
            System.out.println("Entrada invalida. Por favor, digite F ou M.");
            sexo = scan.nextLine();
        }
        if (sexo.equalsIgnoreCase("F")) sexo = "Femea";
        else sexo = "Macho";
        boolean sucesso = GeralController.A.atualizarSexo(animal, sexo);
        if (sucesso) {
            System.out.println("\n✅ Sexo do animal atualizado com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar o sexo deste animal.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }

    /** Método responsável por atualizar a situação do animal. */
    public static void atualizarSituacao(Animal animal){
        boolean situacaoValida = false;
        String situacao = "";
        do {
            System.out.print("\nSelecione a nova situacao do animal:\n");
            System.out.println("[1] Em observacao");
            System.out.println("[2] Em tratamento");
            System.out.println("[3] Disponivel para adocao");
            int escolha = Main.validarOpcao();
            switch (escolha) {
                case 1:
                    situacao = "Animal em observacao";
                    situacaoValida = true;
                    break;
                case 2:
                    situacao = "Animal em tratamento";
                    situacaoValida = true;
                    break;
                case 3:
                    situacao = "Animal disponivel para adocao";
                    situacaoValida = true;
                    break;
                default:
                    System.out.println("Opcao '" + escolha + "' eh invalida.");
                    System.out.println("Por favor, selecione um numero inteiro entre 1 e 3.");
                    System.out.println("Aperte Enter para voltar.");
                    scan.nextLine();
                    break;
            }
        } while (!situacaoValida);
        boolean sucesso = GeralController.A.atualizarSituacao(animal, situacao);
        if (sucesso) {
            System.out.println("\n✅ Situacao do animal atualizada com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar a situacao deste animal.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }

    /** Método responsável por atualizar o setor do animal.
     * Antes do setor ser atualizado, verifica-se se há setores ativos. */
    public static void atualizarSetor(Animal animal){
        List<Setor> setores = GeralController.S.listarSetores();
        List<Setor> setoresAtivos = new ArrayList<>();
        for (int i = 0; i < setores.size(); i++) {
            Setor setorAtual = setores.get(i);
            boolean ativo = GeralController.S.setorAtivo(setorAtual);
            if (ativo) setoresAtivos.add(setorAtual);
        }
        if (setoresAtivos.isEmpty()){
            System.out.println("Ops! Ainda nao ha setores ativos. Nao eh possivel atualizar o");
            System.out.println("setor do animal pois nao ha nenhum setor ativo.");
            System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
            scan.nextLine();
            return;
        }
        boolean setorEscolhido = false;
        Setor setorAnimal = null;
        do {
            System.out.println("\nSetores ativos:");
            for (Setor setorAtivo : setoresAtivos){
                System.out.println(setorAtivo.getID() + " - " + setorAtivo.getNome());
            }
            System.out.println("\nInsira o ID do novo setor: ");
            String IDsetor = scan.nextLine();
            for (Setor setor : setores) {
                if (setor.getID().equalsIgnoreCase(IDsetor.trim())) {
                    setorAnimal = setor;
                    setorEscolhido = true;
                    break;
                }
            }
        } while (!setorEscolhido);
        boolean sucesso = GeralController.A.atualizarSetor(animal, setorAnimal);
        if (sucesso) {
            System.out.println("\n✅ Setor do animal atualizado com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel atualizar o setor deste animal.");
        }
        System.out.println("Aperte Enter para voltar ao menu de atualizacao.");
        scan.nextLine();
    }
}