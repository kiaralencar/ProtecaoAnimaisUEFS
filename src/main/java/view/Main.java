package view;
import controller.GeralController;
import model.Animal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Entrada dos dados
        System.out.print("ID do animal: ");
        String id = scan.nextLine();
        System.out.print("Nome do animal: ");
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
        int idade = GeralController.A.calcularIdade(YearMonth.of(ano, mes));
        System.out.println("Idade: " + idade); // mostra a idade so p conferir se ta certa
        System.out.print("Sexo: ");
        String sexo = scan.nextLine();
        System.out.print("Situacao (observacao/adocao/tratamento): ");
        String situacao = scan.nextLine();

        // Criar animal sem setor e sem pessoas tutoras por enquanto
        Animal animal = new Animal(id, nome, especie, raca, YearMonth.of(ano, mes), sexo, situacao, null, new ArrayList<>());

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



/*
Cannot deserialize value of type `java.util.HashMap<java.lang.String,model.Animal>` from Array value (token `JsonToken.START_ARRAY`)
 at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 1]

boolean loop = true;
        while (loop) {
            System.out.println("\n🐾🐾🐾 PROTECAO DE ANIMAIS DA UEFS 🐾🐾🐾");
            System.out.println("\nSelecione a opcao desejada:");
            System.out.println("[1] Atualizar");
            System.out.println("[2] Buscar");
            System.out.println("[3] Cadastrar");
            System.out.println("[4] Deletar");
            System.out.println("[0] Encerrar");
            int opcao = validarOpcao();
            switch (opcao) {
                case 1:
                    //exibirMenu();
                    break;
                case 2:
                    //exibirMenu();
                    break;
                case 3:
                    //exibirMenu();
                    break;
                case 4:
                    // algo
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    System.out.println("Por favor, selecione um numero inteiro entre 0 e 4.");
                    //aperteEnter();
            }
        }
        System.out.println("Sistema encerrado!")
    public static void exibirMenu(){
        boolean loop = true;
        while (loop){
            System.out.println("---------------------- ATUALIZAR ----------------------");
            System.out.println("[1] Animal");
            System.out.println("[2] Pessoa Tutor");
            System.out.println("[3] Setor Responsavel");
            System.out.println("[0] Voltar ao menu anterior");
            int opcao = validarOpcao();
            switch (opcao){

            }
        }
    }*/