package modelTest;

import model.Animal;
import model.Endereco;
import model.Setor;
import model.Tutora;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
 * @version 1.0
 * @see Setor
 * @see Endereco
 */
public class SetorTest {
    /** Instância do setor e do endereço para os testes. */
    private Setor setor;
    private Endereco endereco;

    /** Inicializa as instâncias de Setor e Endereco antes de cada teste. */
    @BeforeEach
    void setUp() {
        endereco = new Endereco("A", "Novo Horizonte", "44600123",
                "Feira de Santana", "Bahia") ;
        setor = new Setor("Modulo 5", endereco, new ArrayList<>(), new ArrayList<>());
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
     * Testa o método setEndereco para verificar a correta atribuição
     * e obtenção do endereço do setor.
     */
    @Test
    void testSetEndereco() {
        Endereco novo = new Endereco("E", "Feira VI", "44678543", "Ilheus", "Piaui");
        setor.setEndereco(novo);
        assertEquals(novo, setor.getEndereco());
    }

    /**
     * Testa o método setTutores para verificar se os tutores do setor
     * são definidos e obtidos corretamente.
     */
    @Test
    void testSetTutores(){
        Endereco endereco1 = new Endereco("A", "B", "C", "D", "E");
        Tutora tutora1 = new Tutora("Kamilly", endereco1, "75999854777", "kamilly@gmail.com", setor, new ArrayList<>());
        Endereco endereco2 = new Endereco("F", "G", "H", "I", "J");
        Tutora tutora2 = new Tutora("Marcelly", endereco2, "75951432567", "marcelly@gmail.com", setor, new ArrayList<>());
        List<Tutora> tutores = new ArrayList<>();
        tutores.add(tutora1);
        tutores.add(tutora2);
        setor.setTutores(tutores);
        List<Tutora> tutoresAtuais = setor.getTutores();
        assertEquals(tutores, tutoresAtuais, "A lista deve conter Kamilly e Marcelly como pessoas tutoras do setor.");
    }

    /**
     * Testa o método setAnimais para verificar se os animais do setor
     * são definidos e obtidos corretamente.
     */
    @Test
    void testSetAnimais(){
        Animal animal1 = new Animal(2, "Mily", "Gato", "Sem raca",
                LocalDate.of(2022, 8, 9), "Femea", "Em tratamento", setor, new ArrayList<>());
        Animal animal2 = new Animal(3, "Nick", "Cachorro", "Sem raca",
                LocalDate.of(2024, 10, 27), "Macho", "Em observacao", setor, new ArrayList<>());
        List<Animal> animais = new ArrayList<>();
        animais.add(animal1);
        animais.add(animal2);
        setor.setAnimais(animais);
        List<Animal> animaisAtuais = setor.getAnimais();
        assertEquals(animais, animaisAtuais, "A lista deve conter Mily e Nick como animais do setor.");
    }
}