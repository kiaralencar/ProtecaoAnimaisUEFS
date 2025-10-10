package view;
import controller.GeralController;
import model.Animal;
import model.Setor;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastrarAnimal {
    static Scanner scan = new Scanner(System.in);
    public void cadastrar() {
        List<String> nomesSetores = GeralController.S.listarSetores();
        if (nomesSetores.isEmpty()){
            System.out.println("Nao eh possivel cadastrar animais,\npois nao ha setores cadastrados.");
            System.out.println("Aperte Enter para voltar ao menu de cadastro.");
            scan.nextLine();
            return;
        }
        String ID;
        String situacao = "";
        boolean situacaoValida = false;
        System.out.println("\n------> CADASTRO DO ANIMAL\n");
        do {
            System.out.println("Insira o ID do animal (formato A + numero. Ex.: A1): ");
            ID = scan.nextLine();
        } while (GeralController.A.validarIDAnimal(ID));
        System.out.println("Nome: ");
        String nome = scan.nextLine();
        System.out.print("Especie: ");
        String especie = scan.nextLine();
        System.out.print("Raca: ");
        String raca = scan.nextLine();
        System.out.print("Mes de nascimento: ");
        int mes = scan.nextInt();
        scan.nextLine();
        System.out.print("Ano de nascimento: ");
        int ano = scan.nextInt();
        scan.nextLine();
        System.out.print("Sexo: ");
        String sexo = scan.nextLine();
        do {
            System.out.print("\nSelecione a situacao do animal:");
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
                default:
                    System.out.println("Opcao '" + escolha + "' eh invalida.");
                    System.out.println("Por favor, selecione um numero inteiro entre 1 e 3.");
                    System.out.println("Aperte Enter para voltar.");
                    scan.nextLine();
                    break;
            }
        } while (!situacaoValida);
        System.out.println("Escolha o setor do animal:");
        for (String nomeSetor : nomesSetores){
            System.out.println("[" + + "] "+ nomeSetor);
        }
        Animal animal = new Animal(ID, nome, especie, raca, YearMonth.of(ano, mes), sexo, situacao, null, new ArrayList<>());

        // Cadastrar via GeralController
        boolean cadastrado = GeralController.A.cadastrarAnimal(animal);
        if (cadastrado) {
            System.out.println("Animal cadastrado com sucesso!");
        } else {
            System.out.println("ERRO. ID invalido ou existente!");
        }

        System.out.println("Dados do animal: ");
        System.out.println(animal.getNome());
        System.out.println(animal.getRaca());
        System.out.println(animal.getEspecie());
    }
}
