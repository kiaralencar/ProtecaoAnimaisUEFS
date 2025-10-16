package view;
import controller.GeralController;
import model.Animal;
import model.Setor;
import model.Tutor;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastrarAnimal {
    static Scanner scan = new Scanner(System.in);

    public static void cadastrar(){
        List<Setor> setores = GeralController.S.listarSetores();
        if (setores.isEmpty()){
            System.out.println("Nao eh possivel cadastrar animais,\npois nao ha setores cadastrados.");
            System.out.println("Aperte Enter para voltar ao menu de cadastro.");
            scan.nextLine();
            return;
        }
        int mes = 0, ano = 0;
        String ID, sexo;
        String situacao = "";
        Setor setorAnimal = null;
        boolean dataValida = false;
        boolean situacaoValida = false;
        boolean setorEscolhido = false;
        System.out.println("\n------------> CADASTRO DO ANIMAL\n");
        System.out.println("Insira o ID do animal (A + numero. Ex.: A1): ");
        ID = scan.nextLine();
        while (!GeralController.A.validarIDAnimal(ID)){
            System.out.println("ID invalido ou existente. Por favor, tente novamente.");
            ID = scan.nextLine();
        }
        System.out.println("Nome: ");
        String nome = scan.nextLine();
        System.out.print("Especie: ");
        String especie = scan.nextLine();
        System.out.print("Raca: ");
        String raca = scan.nextLine();
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
        System.out.print("Sexo [F/M]: ");
        sexo = scan.nextLine();
        while (!sexo.trim().equalsIgnoreCase("F") && !sexo.trim().equalsIgnoreCase("M")){
            System.out.println("Entrada invalida. Por favor, digite F ou M.");
            sexo = scan.nextLine();
        }
        if (sexo.equalsIgnoreCase("F")) sexo = "Femea";
        else sexo = "Macho";
        do {
            System.out.print("\nSelecione a situacao do animal:\n");
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
                    setorAnimal = setor;
                    setorEscolhido = true;
                    break;
                }
            }
        } while (!setorEscolhido);
        List<String> tutoresID = new ArrayList<>();
        for (Tutor tutor: setorAnimal.getTutores()){
            tutoresID.add(tutor.getID());
        }
        Animal animal = GeralController.A.criarAnimal(ID.trim().toUpperCase(), nome.trim(), especie.trim(), raca.trim(),
                YearMonth.of(ano, mes), sexo, situacao, setorAnimal.getID(), tutoresID);
        boolean cadastrado = GeralController.A.cadastrarAnimal(animal);
        if (cadastrado) {
            System.out.println("\n✅ Animal cadastrado com sucesso!");
        } else {
            System.out.println("\n❌ ERRO. Nao foi possivel cadastrar este animal.");
        }
        System.out.println("Aperte Enter para voltar ao menu de cadastro.");
        scan.nextLine();
    }
}