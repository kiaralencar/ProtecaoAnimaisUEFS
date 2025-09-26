/*package view;

import java.util.Scanner;

import static view.Main.validarOpcao;

public class AnimalView {
    private static Scanner scan = new Scanner(System.in);

    public static void exibirMenu(){
        boolean loop = true;
        while (loop){
            System.out.println("---------------------- ANIMAL ----------------------");
            System.out.println("\nSelecione a opcao desejada:");
            System.out.println("[1] Exibir dados do animal");
            System.out.println("[2] Atualizar dados do animal");
            System.out.println("[3] Deletar animal");
            System.out.println("[0] Voltar ao menu inicial");
            int opcao = validarOpcao();
            switch (opcao) {
                case 1:
                    // dadosAnimal();
                    break;
                case 2:
                    //atualizarMenu();
                    break;
                case 3:
                    //deletarAnimal();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    System.out.println("Por favor, selecione um numero inteiro entre 0 e 3.");
                    System.out.println("Aperte Enter para tentar novamente.");
                    scan.nextLine();

            }
        }
    }

/* métodos a implementar:
    - validar animal (ID >= 0 e inexistente) *FEITO*
    - calcular idade *FEITO*
    - cadastrar animal *FEITO*
    - deletar animal *FEITO*
    - adicionar pessoa tutora *FEITO*
    - remover pessoa tutora *FEITO*
    - buscar animal por id *FEITO*
    - buscar setor do animal *FEITO*
    - listar pessoas tutoras do animal *FEITO*
    - listar animais *FEITO*
    - atualizar id do animal *FEITO*
    - atualizar nome do animal *FEITO*
    - atualizar especie do animal *FEITO*
    - atualizar raca do animal *FEITO*
    - atualizar data de nascimento do animal *FEITO*
    - atualizar sexo do animal *FEITO*
    - atualizar setor do animal *FEITO*

}*/