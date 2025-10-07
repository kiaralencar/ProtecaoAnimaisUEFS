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

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TutorControllerTest {
    private AnimalController A;
    private TutorController T;
    private EnderecoController E;
    private SetorController S;
    private Animal animal;
    private Tutor tutor;
    private Endereco endereco;
    private Setor setor;

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

    @Test
    void cadastrarTutorTest(){
        boolean resultado = T.cadastrarTutor(tutor);
        assertTrue(resultado);
    }

    @Test
    void cadastrarTutorNuloTest(){
        boolean resultado = T.cadastrarTutor(null);
        assertFalse(resultado);
    }

    @Test
    void deletarTutorTest(){
        T.cadastrarTutor(tutor);
        boolean resultado = T.deletarTutor(tutor);
        assertTrue(resultado);
    }

    @Test
    void deletarTutorNuloTest(){
        boolean resultado = T.deletarTutor(null);
        assertFalse(resultado);
    }

    @Test
    void buscarPorIDTest(){
        T.cadastrarTutor(tutor);
        Tutor tutor2 = T.buscarTutorPorID(tutor.getID());
        assertNotNull(tutor2);
    }

    @Test
    void buscarPorIDFalsoTest(){
        Tutor tutor2 = T.buscarTutorPorID("D5P");
        assertNull(tutor2);
    }

    @Test
    void adicionarSetorTest(){
        boolean resultado = T.adicionarSetor(tutor, setor);
        assertTrue(resultado);
    }

    @Test
    void adicionarSetorNuloTest(){
        boolean resultado = T.adicionarSetor(tutor, null);
        assertFalse(resultado);
    }

    @Test
    void buscarSetorTest(){
        T.adicionarSetor(tutor, setor);
        Setor setor2 = T.buscarSetor(tutor);
        assertNotNull(setor2);
    }

    @Test
    void buscarSetorNuloTest(){
        Setor setor2 = T.buscarSetor(tutor);
        assertNull(setor2);
    }


}
