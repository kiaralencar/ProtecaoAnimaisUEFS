package modelTest;

import model.Animal;
import model.Endereco;
import model.Setor;
import model.Tutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste de unidade para a classe Animal.
 * <p>
 * Esta classe contém testes para verificar se os métodos getters e setters
 * da classe Animal funcionam corretamente.
 *
 * @author Kiara Alencar
 * @version 1.2
 * @see Animal
 */
class AnimalTest {
    /** Instância do animal para os testes. */
    private Animal animal;

    /** Inicializa uma nova instância da classe Animal antes de cada teste. */
    @BeforeEach
    void setUp() {
        this.animal = new Animal("A1", "Lulu", "Gato", "Persa",
                YearMonth.of(2018, 5), "Macho", "Em tratamento", null, new ArrayList<>());
    }

    /**
     * Testa o método setID para garantir que o ID do animal
     * seja definido e recuperado corretamente.
     */
    @Test
    void testSetID() {
        animal.setNome("A3");
        assertEquals("A3", animal.getID(), "O ID do animal deve ser 'A3'");
    }

    /**
     * Testa o método setNome para garantir que o nome do animal
     * seja definido e recuperado corretamente.
     */
    @Test
    void testSetNome() {
        animal.setNome("Dalila");
        assertEquals("Dalila", animal.getNome(), "O nome do animal deve ser 'Dalila'");
    }

    /**
     * Testa o método setEspecie para verificar a correta atribuição
     * e obtenção da espécie do animal.
     */
    @Test
    void testSetEspecie() {
        animal.setEspecie("Cachorro");
        assertEquals("Cachorro", animal.getEspecie(), "A especie do animal deve ser 'Cachorro'");
    }

    /**
     * Testa o método setRaca para assegurar que a raça do animal
     * seja definida e recuperada corretamente.
     */
    @Test
    void testSetRaca() {
        animal.setRaca("Maltes");
        assertEquals("Maltes", animal.getRaca(), "A raca do animal deve ser 'Maltes'");
    }

    /**
     * Testa o método setData para garantir a correta atribuição
     * da data de nascimento do animal.
     */
    @Test
    void testSetData() {
        YearMonth dataNascimento = YearMonth.of(2010, 7);
        animal.setData(dataNascimento);
        assertEquals(dataNascimento, animal.getData(), "A data de nascimento do animal deve ser 2010-07-16.");
    }

    /**
     * Testa o método setSexo para verificar se o sexo do animal
     * é definido e obtido corretamente.
     */
    @Test
    void testSetSexo() {
        animal.setSexo("Femea");
        assertEquals("Femea", animal.getSexo(), "O sexo do animal deve ser 'Femea'");
    }

    /**
     * Testa o método setSituacao para garantir a correta atribuição
     * da situação do animal.
     */
    @Test
    void testSetSituacao() {
        animal.setSituacao("Disponivel para adocao");
        assertEquals("Disponivel para adocao", animal.getSituacao(), "A situacao do animal deve ser 'Disponivel para adocao'");
    }

    /**
     * Testa o método setSetor para verificar se o setor do animal
     * é definido e obtido corretamente.
     */
    @Test
    void testSetSetor() {
        Endereco endereco = new Endereco("F", "G", "H", "I", "J");
        Setor novo = new Setor("Modulo 1", new ArrayList<>(), new ArrayList<>());
        animal.setSetor(novo);
        assertEquals("Modulo 1", animal.getSetor().getNome(), "O setor do animal deve ser 'Modulo 1'");
    }

    /**
     * Testa o método setTutores para verificar se os tutores do animal
     * são definidos e obtidos corretamente.
     */
    @Test
    void testSetTutores(){
        Endereco endereco1 = new Endereco("K", "L", "M", "N", "O");
        Tutor tutor1 = new Tutor("Helder", endereco1, "75988887777", "helder@gmail.com", null, new ArrayList<>());
        Endereco endereco2 = new Endereco("P", "Q", "R", "S", "T");
        Tutor tutor2 = new Tutor("Alana", endereco2, "75989987777", "alana@gmail.com", null, new ArrayList<>());
        List<Tutor> tutores = new ArrayList<>();
        tutores.add(tutor1);
        tutores.add(tutor2);
        animal.setTutores(tutores);
        List<Tutor> tutoresAtuais = animal.getTutores();
        assertEquals(tutores, tutoresAtuais, "A lista deve conter Helder e Alana como tutores do animal.");
    }
}