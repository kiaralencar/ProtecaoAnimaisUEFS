package view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import controller.GeralController;
import model.Animal;

import java.io.File;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        System.out.print("Dia de nascimento: ");
        int dia = scan.nextInt();
        System.out.print("Mes de nascimento: ");
        int mes = scan.nextInt();
        System.out.print("Ano de nascimento: ");
        int ano = scan.nextInt();
        int idade = GeralController.A.calcularIdade(YearMonth.of(ano, mes));
        System.out.println("Idade: " + idade);
        YearMonth data = YearMonth.of(ano, mes);
        scan.nextLine();
        System.out.print("Sexo: ");
        String sexo = scan.nextLine();
        System.out.print("Situacao (observacao/adocao/tratamento): ");
        String situacao = scan.nextLine();

        // Criar animal sem setor e sem pessoas tutoras por enquanto
        Animal animal = new Animal(id, nome, especie, raca, data, sexo, situacao, null, new ArrayList<>());

        // Cadastrar via GeralController
        boolean cadastrado = GeralController.A.cadastrarAnimal(animal);
        if (cadastrado) {
            System.out.println("Animal cadastrado com sucesso!");
        } else {
            System.out.println("ERRO. ID invalido ou existente!");
        }

        try {
            // Salvar lista de animais em JSON
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

            File arquivo = new File("animais.json");
            List<Animal> listaAtual = new ArrayList<>();

            // Ler animais existentes do JSON (se o arquivo existir)
            if (arquivo.exists()) {
                Animal[] animaisExistentes = mapper.readValue(arquivo, Animal[].class);
                listaAtual.addAll(Arrays.asList(animaisExistentes));
            }

            // Adicionar o novo animal
            listaAtual.add(animal);

            // Salvar a lista completa de volta no JSON
            writer.writeValue(arquivo, listaAtual);

            System.out.println("Arquivo 'animais.json' atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



/*boolean loop = true;
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