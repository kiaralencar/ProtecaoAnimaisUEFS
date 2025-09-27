package modelTest;

import model.Animal;
import model.Endereco;
import model.Setor;
import model.Tutora;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de teste de unidade para a classe Tutora.
 * <p>
 * Esta classe contém testes para verificar se os métodos getters e setters
 * da classe Tutora funcionam corretamente.
 *
 * @author Kiara Alencar
 * @version 1.1
 * @see Tutora
 * @see Endereco
 */
public class TutoraTest {
    /** Instância da pessoa tutora e do endereço para os testes. */
    private Tutora pessoa;
    private Endereco endereco;

    /** Inicializa as instâncias de Tutora e Endereco antes de cada teste. */
    @BeforeEach
    void setUp() {
        endereco = new Endereco("Bela", "Pontalzinho", "45632167",
                "Itape", "Bahia") ;
        pessoa = new Tutora("Aline", endereco, "73988543211", "alinemoreira@gmail.com", null, new ArrayList<>());
    }

    /**
     * Testa o método setNome para garantir que o nome da pessoa tutora
     * seja definido e recuperado corretamente.
     */
    @Test
    void testSetNome() {
        pessoa.setNome("Milena");
        assertEquals("Milena", pessoa.getNome(), "O nome da pessoa tutora deve ser 'Milena'");
    }

    /**
     * Testa o método setEndereco para verificar a correta atribuição
     * e obtenção do endereço da pessoa tutora.
     */
    @Test
    void testSetEndereco() {
        Endereco novo = new Endereco("Oliveiras", "Mangabinha", "4568756",
                "Itajuipe", "Ceara");
        pessoa.setEndereco(novo);
        assertEquals(novo, pessoa.getEndereco());
    }

    /**
     * Testa o método setTelefone para garantir que o telefone da pessoa tutora
     * seja definido e recuperado corretamente.
     */
    @Test
    void testSetTelefone() {
        pessoa.setTelefone("73954322212");
        assertEquals("73954322212", pessoa.getTelefone(), "O telefone da pessoa " +
                "tutora deve ser '(73) 95432-2212'");
    }

    /**
     * Testa o método setEmail para garantir que o email da pessoa tutora
     * seja definido e recuperado corretamente.
     */
    @Test
    void testSetEmail() {
        pessoa.setEmail("auroraboreal@uefs.br");
        assertEquals("auroraboreal@uefs.br", pessoa.getEmail(), "O email da pessoa" +
                " tutora deve ser 'auroraboreal@uefs.br'");
    }

    /**
     * Testa o método setSetor para verificar se o setor da pessoa tutora
     * é definido e obtido corretamente.
     */
    @Test
    void testSetSetor() {
        Endereco endereco = new Endereco("A", "B", "C", "D", "E");
        Setor novo = new Setor("Modulo 3", new ArrayList<>(), new ArrayList<>());
        pessoa.setSetor(novo);
        assertEquals("Modulo 3", pessoa.getSetor().getNome(), "O setor da pessoa tutora deve ser 'Modulo 3'");
    }

    /**
     * Testa o método setAnimais para verificar se os animais da pessoa tutora
     * são definidos e obtidos corretamente.
     */
    @Test
    void testSetAnimais(){
        Animal animal1 = new Animal("A4", "Charlie", "Cachorro", "Pastor alemao",
                YearMonth.of(2018, 2), "Femea", "Em tratamento", null, new ArrayList<>());
        Animal animal2 = new Animal("A5", "Tiffany", "Cachorro", "Sem raca",
                YearMonth.of(2016, 10), "Femea", "Disponivel", null, new ArrayList<>());
        List<Animal> animais = new ArrayList<>();
        animais.add(animal1);
        animais.add(animal2);
        pessoa.setAnimais(animais);
        List<Animal> animaisAtuais = pessoa.getAnimais();
        assertEquals(animais, animaisAtuais, "A lista deve conter Charlie e Tiffany como animais da pessoa tutora.");
    }
}