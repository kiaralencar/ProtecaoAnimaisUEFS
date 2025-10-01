package controllerTest;

import controller.AnimalController;
import model.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AnimalControllerTest {
    private AnimalController controller;
    private Animal animal;

    @BeforeEach
    void setUp(){
        controller = new AnimalController();
        animal = controller.criarAnimal("A1", "Lilica", "Gato", "Siames",
                YearMonth.of(2020, 9), "Femea", "Disponivel", null, new ArrayList<>());
    }

    @Test
    void criarAnimalTest(){
        assertNotNull(animal);
        assertEquals("A1", animal.getID());
        assertEquals("Lilica", animal.getNome());
        assertEquals("Gato", animal.getEspecie());
        assertEquals("Siames", animal.getRaca());
        assertEquals("Femea", animal.getSexo());
        assertEquals("Disponivel", animal.getSituacao());
        assertNull(animal.getSetor());
        assertNotNull(animal.getTutores());
        assertTrue(animal.getTutores().isEmpty());
        YearMonth dataEsperada = YearMonth.of(2020, 9);
        assertEquals(dataEsperada, animal.getData());

    }

    @Test
    void validarIDTest(){
        boolean resultado = controller.validarIDAnimal("A4");
        assertTrue(resultado);
    }

    @Test
    void validarIDFalsoTest(){
        boolean resultado = controller.validarIDAnimal("B#5");
        assertFalse(resultado);
    }

    @Test
    void validarDataTest(){
        YearMonth data = YearMonth.of(2018, 6);
        boolean resultado = controller.validarData(data);
        assertTrue(resultado);
    }

    @Test
    void validarDataFalsaTest(){
        YearMonth data = YearMonth.of(2032, 11);
        boolean resultado = controller.validarData(data);
        assertFalse(resultado);
    }

    @Test
    void calcularIdadeTest(){
        int idade = controller.calcularIdade(animal.getData());
        assertEquals(5, idade);
    }

    @Test
    void calcularIdadeFalsaTest(){
        int idade = controller.calcularIdade(animal.getData());
        assertNotEquals(8, idade);
    }
}
