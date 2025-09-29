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

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de teste de unidade para a classe Setor.
 * <p>
 * Esta classe contém testes para verificar se os métodos getters e setters
 * da classe Setor funcionam corretamente.
 *
 * @author Kiara Alencar
 * @version 1.3
 * @see Setor
 */
public class SetorTest {
    /** Instância do setor e do endereço para os testes. */
    private Setor setor;

    /** Inicializa as instâncias de Setor antes de cada teste. */
    @BeforeEach
    void setUp() {
        setor = new Setor("Modulo 5", new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Testa o método setNome para garantir que o nome do setor
     * seja definido e recuperado corretamente.
     */
    @Test
    void testSetNome() {
        setor.setNome("Reitoria");
        assertEquals("Reitoria", setor.getNome(), "O nome do setor deve ser 'Reitoria'");
    }

    /**
     * Testa o método setTutores para verificar se os tutores do setor
     * são definidos e obtidos corretamente.
     */
    @Test
    void testSetTutores(){
        Endereco endereco1 = new Endereco("A", "B", "C", "D", "E");
        Tutor tutor1 = new Tutor("Kamilly", endereco1, "75999854777", "kamilly@gmail.com", setor, new ArrayList<>());
        Endereco endereco2 = new Endereco("F", "G", "H", "I", "J");
        Tutor tutor2 = new Tutor("Marcelly", endereco2, "75951432567", "marcelly@gmail.com", setor, new ArrayList<>());
        List<Tutor> tutores = new ArrayList<>();
        tutores.add(tutor1);
        tutores.add(tutor2);
        setor.setTutores(tutores);
        List<Tutor> tutoresAtuais = setor.getTutores();
        assertEquals(tutores, tutoresAtuais, "A lista deve conter Kamilly e Marcelly como pessoas tutoras do setor.");
    }

    /**
     * Testa o método setAnimais para verificar se os animais do setor
     * são definidos e obtidos corretamente.
     */
    @Test
    void testSetAnimais(){
        Animal animal1 = new Animal("A2", "Mily", "Gato", "Sem raca",
                YearMonth.of(2022, 8), "Femea", "Em tratamento", setor, new ArrayList<>());
        Animal animal2 = new Animal("A3", "Nick", "Cachorro", "Sem raca",
                YearMonth.of(2024, 10), "Macho", "Em observacao", setor, new ArrayList<>());
        List<Animal> animais = new ArrayList<>();
        animais.add(animal1);
        animais.add(animal2);
        setor.setAnimais(animais);
        List<Animal> animaisAtuais = setor.getAnimais();
        assertEquals(animais, animaisAtuais, "A lista deve conter Mily e Nick como animais do setor.");
    }

    /**
     * Testa o método getEndereco para verificar se o endereço do
     * setor é realmente a UEFS.
     */
    @Test
    void getEnderecoTest(){
        assertEquals("Universidade Estadual de Feira de Santana (UEFS)", Setor.getEndereco(),
                "O endereco do setor deve ser 'Universidade Estadual de Feira de Santana (UEFS)'");
    }
}