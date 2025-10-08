package controllerTest;

import controller.AnimalController;
import controller.EnderecoController;
import controller.SetorController;
import controller.TutorController;
import model.Animal;
import model.Endereco;
import model.Setor;
import model.Tutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TutorControllerTest {
    /** Instância do controller do animal para os testes. */
    private AnimalController A;

    /** Instância do controller do tutor para os testes. */
    private TutorController T;

    /** Instância do controller do endereço para os testes. */
    private EnderecoController E;

    /** Instância do controller do setor para os testes. */
    private SetorController S;

    /** Instância do animal para os testes. */
    private Animal animal;

    /** Instância do tutor para os testes. */
    private Tutor tutor;

    /** Instância do endereço para os testes. */
    private Endereco endereco;

    /** Instância do setor para os testes. */
    private Setor setor;

    /** Cria novas instâncias da classes antes de cada teste. */
    @BeforeEach
    void setUp(){
        T = new TutorController();
        A = new AnimalController();
        E = new EnderecoController();
        S = new SetorController();
        endereco = E.criarEndereco("A", "B", "C", "D", "E");
        tutor = T.criarTutor("T1", "Ana", endereco, "73765413278",
                "ana@gmail.com", null, new ArrayList<>());
        animal = A.criarAnimal("A1", "Lilica", "Gato", "Siames",
                YearMonth.of(2020, 9), "Femea", "Disponivel", null, new ArrayList<>());
        setor = S.criarSetor("S1", "Modulo 1", new ArrayList<>(), new ArrayList<>());
    }

    /** Testa o método criarTutor para garantir que o tutor
     * seja definido corretamente. */
    @Test
    void criarTutorTest(){
        assertNotNull(tutor);
        assertEquals("T1", tutor.getID());
        assertEquals("Ana", tutor.getNome());
        assertNotNull(tutor.getEndereco());
        assertEquals("73765413278", tutor.getTelefone());
        assertEquals("ana@gmail.com", tutor.getEmail());
        assertNull(tutor.getSetor());
        assertNotNull(tutor.getAnimais());
        assertTrue(tutor.getAnimais().isEmpty());
    }

    /** A seguir, os métodos testam o método validarIDTutor para garantir
     * que o ID seja inserido corretamente. */
    @Test
    void validarIDTest(){
        boolean resultado = T.validarIDTutor("T4");
        assertTrue(resultado);
    }

    @Test
    void validarIDFalsoTest(){
        boolean resultado = T.validarIDTutor("4T5");
        assertFalse(resultado);
    }

    @Test
    void validarIDVazioTest(){
        boolean resultado = T.validarIDTutor("");
        assertFalse(resultado);
    }

    @Test
    void validarIDEspacoTest(){
        boolean resultado = T.validarIDTutor(" ");
        assertFalse(resultado);
    }

    @Test
    void validarIDEnterTest(){
        boolean resultado = T.validarIDTutor("\t\n");
        assertFalse(resultado);
    }

    /** A seguir, os métodos testam o método validarEmail para garantir
     * que o email seja inserido corretamente. */
    @Test
    void validarEmailTest(){
        boolean resultado = T.validarEmail("anacarolina2020@hotmail.com");
        assertTrue(resultado);
    }

    @Test
    void validarEmailFalsoTest(){
        boolean resultado = T.validarEmail("#ana..carol-@hotmail.com");
        assertFalse(resultado);
    }

    @Test
    void validarEmailVazioTest(){
        boolean resultado = T.validarEmail("");
        assertFalse(resultado);
    }

    @Test
    void validarEmailEspacoTest(){
        boolean resultado = T.validarEmail(" ");
        assertFalse(resultado);
    }

    @Test
    void validarEmailEnterTest(){
        boolean resultado = T.validarEmail("\t\n");
        assertFalse(resultado);
    }

    /** A seguir, os métodos testam o método validarTelefone para garantir
     * que o ID seja inserido corretamente. */
    @Test
    void validarTelefoneTest(){
        boolean resultado = T.validarTelefone("65342178950");
        assertTrue(resultado);
    }

    @Test
    void validarTelefoneFalsoTest(){
        boolean resultado = T.validarTelefone("34juf76sgb5");
        assertFalse(resultado);
    }

    @Test
    void validarTelefoneVazioTest(){
        boolean resultado = T.validarTelefone("");
        assertFalse(resultado);
    }

    @Test
    void validarTelefoneEspacoTest(){
        boolean resultado = T.validarTelefone("     ");
        assertFalse(resultado);
    }

    @Test
    void validarTelefoneEnterTest(){
        boolean resultado = T.validarTelefone("\t\n");
        assertFalse(resultado);
    }

    /** Testa o método cadastrarTutor para garantir que o tutor seja
     * inserido do Map de tutores. */
    @Test
    void cadastrarTutorTest(){
        boolean resultado = T.cadastrarTutor(tutor);
        assertTrue(resultado);
    }

    /** Não permite o cadastro de um tutor nulo. */
    @Test
    void cadastrarTutorNuloTest(){
        boolean resultado = T.cadastrarTutor(null);
        assertFalse(resultado);
    }

    /** Testa o método deletarTutor para garantir que o tutor seja
     * removido do Map de tutores. */
    @Test
    void deletarTutorTest(){
        T.cadastrarTutor(tutor);
        boolean resultado = T.deletarTutor(tutor);
        assertTrue(resultado);
    }

    /** Não permite deletar um tutor nulo. */
    @Test
    void deletarTutorNuloTest(){
        boolean resultado = T.deletarTutor(null);
        assertFalse(resultado);
    }

    /** Testa o método buscarTutorPorID para garantir que o tutor
     * seja encontrado ao ser buscado pelo seu ID. */
    @Test
    void buscarPorIDTest(){
        T.cadastrarTutor(tutor);
        Tutor tutor2 = T.buscarTutorPorID(tutor.getID());
        assertNotNull(tutor2);
    }

    /** Não permite que seja encontrado um tutor com um ID falso. */
    @Test
    void buscarPorIDFalsoTest(){
        Tutor tutor2 = T.buscarTutorPorID("D5P");
        assertNull(tutor2);
    }

    /** Testa o método adicionarSetor para garantir que o setor do
     * tutor seja adicionado corretamente. */
    @Test
    void adicionarSetorTest(){
        boolean resultado = T.adicionarSetor(tutor, setor);
        assertTrue(resultado);
    }

    /** Não permite que seja adicionado um setor nulo. */
    @Test
    void adicionarSetorNuloTest(){
        boolean resultado = T.adicionarSetor(tutor, null);
        assertFalse(resultado);
    }

    /** Testa o método buscarSetor para encontrar em qual
     * setor o tutor está situado. */
    @Test
    void buscarSetorTest(){
        T.adicionarSetor(tutor, setor);
        Setor setor2 = T.buscarSetor(tutor);
        assertNotNull(setor2);
    }

    /** Não permite buscar um setor nulo. */
    @Test
    void buscarSetorNuloTest(){
        Setor setor2 = T.buscarSetor(tutor);
        assertNull(setor2);
    }

    /** Testa o método listarTutores para pontuar todos os
     * tutores cadastrados. */
    @Test
    void listarTutoresTest(){
        Tutor tutor2 = T.criarTutor("T5", "Bia", null, "76543212345",
                "bia@outlook.com", null, new ArrayList<>());
        Tutor tutor3 = T.criarTutor("T6", "Joana", null, "76547612345",
                "jojo@gmail.com", null, new ArrayList<>());
        T.cadastrarTutor(tutor);
        T.cadastrarTutor(tutor2);
        T.cadastrarTutor(tutor3);
        List<String> nomes = T.listarTutores();
        boolean listaCompleta = !nomes.isEmpty();
        assertTrue(listaCompleta);
    }

    /** Não permite listar tutores inexistentes, ou seja, aqueles que não
     * foram cadastrados. */
    @Test
    void listarTutoresInexistentesTest(){
        List<String> nomes = T.listarTutores();
        boolean listaCompleta = !nomes.isEmpty();
        assertFalse(listaCompleta);
    }

    /** Testa o método listarAnimais para pontuar todos
     * os animais do tutor. */
    @Test
    void listarAnimaisTest(){
        T.adicionarSetor(tutor, setor);
        Animal animal2 = A.criarAnimal("A2", "Beth", "Cachorro", "Poodle",
                YearMonth.of(2021, 2), "Femea", "Tratamento", null, new ArrayList<>());
        Animal animal3 = A.criarAnimal("A3", "Tony", "Cachorro", "Salsicha",
                YearMonth.of(2019, 3), "Macho", "Observacao", null, new ArrayList<>());
        S.adicionarAnimal(setor, animal);
        S.adicionarAnimal(setor, animal2);
        S.adicionarAnimal(setor, animal3);
        List<String> nomes = T.listarAnimais(tutor);
        boolean listaCompleta = !nomes.isEmpty();
        assertTrue(listaCompleta);
    }

    /** Não permite listar animais inexistentes, ou seja, que não
     * foram adicionados à lista de animais do tutor. */
    @Test
    void listarAnimaisInexistentesTest(){
        List<String> nomes = T.listarAnimais(tutor);
        boolean listaCompleta = !nomes.isEmpty();
        assertFalse(listaCompleta);
    }

    /** A seguir, os métodos testam o método atualizarID para garantir
     * que o novo ID seja inserido corretamente. */
    @Test
    void atualizarIDTest(){
        boolean resultado = T.atualizarID(tutor, "T9");
        assertTrue(resultado);
    }

    @Test
    void atualizarIDIgualTest(){
        boolean resultado = T.atualizarID(tutor, "T1");
        assertFalse(resultado);
    }

    @Test
    void atualizarIDFalsoTest(){
        boolean resultado = T.atualizarID(tutor, "I98U");
        assertFalse(resultado);
    }

    @Test
    void atualizarIDVazioTest(){
        boolean resultado = T.atualizarID(tutor, "");
        assertFalse(resultado);
    }

    @Test
    void atualizarIDEspacoTest(){
        boolean resultado = T.atualizarID(tutor, "   ");
        assertFalse(resultado);
    }

    @Test
    void atualizarIDEnterTest(){
        boolean resultado = T.atualizarID(tutor, "\t\n");
        assertFalse(resultado);
    }

    /** A seguir, os métodos testam o método atualizarNome para garantir
     * que o novo nome seja inserido corretamente. */
    @Test
    void atualizarNomeTest(){
        boolean resultado = T.atualizarNome(tutor, "Freitas");
        assertTrue( resultado);
    }

    @Test
    void atualizarNomeIgualTest(){
        boolean resultado = T.atualizarNome(tutor, "Ana");
        assertFalse(resultado);
    }

    @Test
    void atualizarNomeVazioTest(){
        boolean resultado = T.atualizarNome(tutor, "");
        assertFalse(resultado);
    }

    @Test
    void atualizarNomeEspacoTest(){
        boolean resultado = T.atualizarNome(tutor, "   ");
        assertFalse(resultado);
    }

    @Test
    void atualizarNomeEnterTest(){
        boolean resultado = T.atualizarNome(tutor, "\t\n");
        assertFalse(resultado);
    }

    /** Testa o método atualizarEndereco para garantir
     * que o novo endereço seja definido corretamente. */
    @Test
    void atualizarEnderecoTest(){
        Endereco endereco2 = E.criarEndereco("F", "G", "H", "I", "J");
        boolean resultado = T.atualizarEndereco(tutor, endereco2);
        assertTrue(resultado);
    }

    /** Não permite a atualização para um endereço nulo. */
    @Test
    void atualizarEnderecoNuloTest(){
        boolean resultado = T.atualizarEndereco(tutor, null);
        assertFalse(resultado);
    }

    /** Não permite a atualização para o mesmo endereço. */
    @Test
    void atualizarEnderecoIgualTest(){
        boolean resultado = T.atualizarEndereco(tutor, endereco);
        assertFalse(resultado);
    }

    /** A seguir, os métodos testam o método atualizarTelefone para garantir
     * que o telefone nome seja inserido corretamente. */
    @Test
    void atualizarTelefoneTest(){
        boolean resultado = T.atualizarTelefone(tutor, "45874563214");
        assertTrue(resultado);
    }

    @Test
    void atualizarTelefoneIgualTest(){
        boolean resultado = T.atualizarTelefone(tutor, "73765413278");
        assertFalse(resultado);
    }

    @Test
    void atualizarTelefoneVazioTest(){
        boolean resultado = T.atualizarTelefone(tutor, "");
        assertFalse(resultado);
    }

    @Test
    void atualizarTelefoneEspacoTest(){
        boolean resultado = T.atualizarTelefone(tutor, "   ");
        assertFalse(resultado);
    }

    @Test
    void atualizarTelefoneEnterTest(){
        boolean resultado = T.atualizarTelefone(tutor, "\t\n");
        assertFalse(resultado);
    }

    /** A seguir, os métodos testam o método atualizarEmail para garantir
     * que o novo email seja inserido corretamente. */
    @Test
    void atualizarEmailTest(){
        boolean resultado = T.atualizarEmail(tutor, "lucinda@gmail.com");
        assertTrue(resultado);
    }

    @Test
    void atualizarEmailIgualTest(){
        boolean resultado = T.atualizarEmail(tutor, "ana@gmail.com");
        assertFalse(resultado);
    }

    @Test
    void atualizarEmailVazioTest(){
        boolean resultado = T.atualizarEmail(tutor, "");
        assertFalse(resultado);
    }

    @Test
    void atualizarEmailEspacoTest(){
        boolean resultado = T.atualizarEmail(tutor, "  ");
        assertFalse(resultado);
    }

    @Test
    void atualizarEmailEnterTest(){
        boolean resultado = T.atualizarEmail(tutor, "\t\n");
        assertFalse(resultado);
    }

    /** Testa o método atualizarSetor para garantir
     * que o novo setor seja definido corretamente. */
    @Test
    void atualizarSetorTest(){
        Setor setor2 = S.criarSetor("S4", "Modulo 5", new ArrayList<>(), new ArrayList<>());
        boolean resultado = T.atualizarSetor(tutor, setor2);
        assertTrue(resultado);
    }

    /** Não permite a atualização para um setor nulo. */
    @Test
    void atualizarSetorNuloTest(){
        boolean resultado = T.atualizarSetor(tutor, null);
        assertFalse(resultado);
    }

    /** Não permite a atualização para o mesmo setor. */
    @Test
    void atualizarSetorIgualTest(){
        T.adicionarSetor(tutor, setor);
        boolean resultado = T.atualizarSetor(tutor, setor);
        assertFalse(resultado);
    }
}