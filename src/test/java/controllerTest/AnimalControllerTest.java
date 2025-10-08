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

/**
 * Classe de teste de unidade para o controller da classe {@link Animal},
 * chamada de {@link AnimalController}.
 * <p>
 * Esta classe verifica a correta implementação e o comportamento de
 * todas as funcionalidades relacionadas ao gerenciamento de animais.
 *
 * @author Kiara Alencar
 * @version 1.7
 * @see Animal
 * @see Setor
 * @see Tutor
 * @see Endereco
 * @see AnimalController
 * @see SetorController
 * @see TutorController
 * @see EnderecoController
 */
public class AnimalControllerTest {
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
        A = new AnimalController();
        T = new TutorController();
        E = new EnderecoController();
        S = new SetorController();
        animal = A.criarAnimal("A1", "Lilica", "Gato", "Siames",
                YearMonth.of(2020, 9), "Femea", "Disponivel", null, new ArrayList<>());
        endereco = E.criarEndereco("A", "B", "C", "D", "E");
        setor = S.criarSetor("S1", "Modulo 1", new ArrayList<>(), new ArrayList<>());
        tutor = T.criarTutor("T1", "Ana", endereco, "73765413278",
                "ana@gmail.com", setor, new ArrayList<>());
    }

    /** Testa o método criarAnimal para garantir que o animal
     * seja definido corretamente. */
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

    /** A seguir, os métodos testam o método validarIDAnimal para garantir
     * que o ID seja inserido corretamente. */
    @Test
    void validarIDTest(){
        boolean resultado = A.validarIDAnimal("A4");
        assertTrue(resultado);
    }

    @Test
    void validarIDFalsoTest(){
        boolean resultado = A.validarIDAnimal("B#5");
        assertFalse(resultado);
    }

    @Test
    void validarIDVazioTest(){
        boolean resultado = A.validarIDAnimal("");
        assertFalse(resultado);
    }

    @Test
    void validarIDEspacoTest(){
        boolean resultado = A.validarIDAnimal(" ");
        assertFalse(resultado);
    }

    @Test
    void validarIDEnterTest(){
        boolean resultado = A.validarIDAnimal("\t\n");
        assertFalse(resultado);
    }

    /** Testa o método validarData para garantir que a data de
     * nascimento inserida é válida. */
    @Test
    void validarDataTest(){
        YearMonth data = YearMonth.of(2018, 6);
        boolean resultado = A.validarData(data);
        assertTrue(resultado);
    }

    /** Não permite uma data adiantada. */
    @Test
    void validarDataFalsaTest(){
        YearMonth data = YearMonth.of(2032, 11);
        boolean resultado = A.validarData(data);
        assertFalse(resultado);
    }

    /** Testa o método calcularIdade para calcular a idade do animal
     * de acordo com a data de nascimento fornecida pelo usuário.
     *
     * OBS.: a idade esperada foi inserida em 07 de outubro de 2025.
     * Para realizar testes em outra época, deve-se atualizar essa idade. */
    @Test
    void calcularIdadeTest(){
        int idade = A.calcularIdade(animal.getData());
        assertEquals(5, idade);
    }

    /** Testa o método cadastrarAnimal para garantir que o animal seja
     * inserido do Map de animais. */
    @Test
    void cadastrarAnimalTest(){
        boolean resultado = A.cadastrarAnimal(animal);
        assertTrue(resultado);
    }

    /** Não permite o cadastro de um animal nulo. */
    @Test
    void cadastrarAnimalNuloTest(){
        boolean resultado = A.cadastrarAnimal(null);
        assertFalse(resultado);
    }

    /** Testa o método deletarAnimal para garantir que o animal seja
     * removido do Map de animais. */
    @Test
    void deletarAnimalTest(){
        A.cadastrarAnimal(animal);
        boolean resultado = A.deletarAnimal(animal);
        assertTrue(resultado);
    }

    /** Não permite deletar um animal nulo. */
    @Test
    void deletarAnimalNuloTest(){
        boolean resultado = A.deletarAnimal(null);
        assertFalse(resultado);
    }

    /** Testa o método adicionarTutor para garantir que o tutor seja
     * adicionado à lista de tutores do animal corretamente. */
    @Test
    void adicionarTutorTest(){
        boolean resultado = A.adicionarTutor(animal, tutor);
        assertTrue(resultado);
    }

    /** Não permite que seja adicionado um tutor nulo. */
    @Test
    void adicionarTutorInexistenteTest(){
        boolean resultado = A.adicionarTutor(animal, null);
        assertFalse(resultado);
    }

    /** Não permite que seja adicionado um tutor duplicado. */
    @Test
    void adicionarTutorDuplicadoTest(){
        A.adicionarTutor(animal, tutor);
        boolean resultado = A.adicionarTutor(animal, tutor);
        assertFalse("Nao eh possivel adicionar tutores duplicados.", resultado);
    }

    /** Testa o método removerTutor para garantir que o tutor seja
     * removido da lista de tutores do animal corretamente. */
    @Test
    void removerTutorTest(){
        A.adicionarTutor(animal, tutor);
        boolean resultado = A.removerTutor(animal, tutor);
        assertTrue("Tutor removido com sucesso!", resultado);
    }

    /** Não permite que seja removido um tutor nulo. */
    @Test
    void removerTutorNuloTest(){
        boolean resultado = A.removerTutor(animal, tutor);
        assertFalse("Nao foi possivel remover este tutor.", resultado);
    }

    /** Testa o método adicionarSetor para garantir que o setor do
     * animal seja adicionado corretamente. */
    @Test
    void adicionarSetorTest(){
        boolean resultado = A.adicionarSetor(animal, setor);
        assertTrue(resultado);
    }

    /** Não permite que seja adicionado um setor nulo. */
    @Test
    void adicionarSetorNuloTest(){
        boolean resultado = A.adicionarSetor(animal, null);
        assertFalse(resultado);
    }

    /** Testa o método buscarAnimalPorID para garantir que o animal
     * seja encontrado ao ser buscado pelo seu ID. */
    @Test
    void buscarPorIDTest(){
        A.cadastrarAnimal(animal);
        Animal animal2 = A.buscarAnimalPorID(animal.getID());
        assertNotNull("O animal foi encontrado com sucesso!", animal2);
    }

    /** Não permite que seja encontrado um animal com um ID falso. */
    @Test
    void buscarPorIDFalsoTest(){
        Animal animal2 = A.buscarAnimalPorID("C45");
        assertNull("Nao foi possivel encontrar este animal.", animal2);
    }

    /** Testa o método buscarSetor para encontrar em qual
     * setor o animal está situado. */
    @Test
    void buscarSetorTest(){
        A.adicionarSetor(animal, setor);
        Setor setor2 = A.buscarSetor(animal);
        assertNotNull("Setor encontrado com sucesso!", setor2);
    }

    /** Não permite buscar um setor nulo. */
    @Test
    void buscarSetorNuloTest(){
        Setor setor2 = A.buscarSetor(animal);
        assertNull("Nao foi possivel encontrar o setor do animal.", setor2);
    }

    /** Testa o método listarAnimais para pontuar todos
     * os animais cadastrados. */
    @Test
    void listarAnimaisTest(){
        Animal animal2 = A.criarAnimal("A2", "Beth", "Cachorro", "Poodle",
                YearMonth.of(2021, 2), "Femea", "Tratamento", null, new ArrayList<>());
        Animal animal3 = A.criarAnimal("A3", "Tony", "Cachorro", "Salsicha",
                YearMonth.of(2019, 3), "Macho", "Observacao", null, new ArrayList<>());
        A.cadastrarAnimal(animal);
        A.cadastrarAnimal(animal2);
        A.cadastrarAnimal(animal3);
        List<String> nomes = A.listarAnimais();
        boolean listaCompleta = !nomes.isEmpty();
        assertTrue("Os animais foram listados com sucesso!", listaCompleta);
    }

    /** Não permite listar animais inexistentes, ou seja, que não
     * foram devidamente cadastrados. */
    @Test
    void listarAnimaisInexistentesTest(){
        List<String> nomes = A.listarAnimais();
        boolean listaCompleta = !nomes.isEmpty();
        assertFalse("Nao foi possivel listar os animais.", listaCompleta);
    }

    /** Testa o método listarTutores para pontuar todos os
     * tutores do animal. */
    @Test
    void listarTutoresTest(){
        Tutor tutor2 = T.criarTutor("T2", "Maria", endereco, "73765410078",
                "maria@gmail.com", setor, new ArrayList<>());
        Tutor tutor3 = T.criarTutor("T3", "Braga", endereco, "73723410078",
                "braga@gmail.com", setor, new ArrayList<>());
        A.adicionarTutor(animal, tutor);
        A.adicionarTutor(animal, tutor2);
        A.adicionarTutor(animal, tutor3);
        List<String> nomes = A.listarTutores(animal);
        boolean listaCompleta = !nomes.isEmpty();
        assertTrue("Os tutores foram listados com sucesso!", listaCompleta);
    }

    /** Não permite listar tutores inexistentes, ou seja, aqueles que não
     * foram adicionados ao setor do animal. */
    @Test
    void listarTutoresInexistentesTest(){
        List<String> nomes = A.listarTutores(animal);
        boolean listaCompleta = !nomes.isEmpty();
        assertFalse("Nao foi possivel listar os tutores.", listaCompleta);
    }

    /** A seguir, os métodos testam o método atualizarID para garantir
     * que o novo ID seja inserido corretamente. */
    @Test
    void atualizarIDTest(){
        boolean resultado = A.atualizarID(animal, "A5");
        assertTrue("O ID foi atualizado com sucesso!", resultado);
    }

    @Test
    void atualizarIDFalsoTest(){
        boolean resultado = A.atualizarID(animal, "X56");
        assertFalse("Nao foi possivel atualizar o ID.", resultado);
    }

    @Test
    void atualizarIDVazioTest(){
        boolean resultado = A.atualizarID(animal, "");
        assertFalse(resultado);
    }

    @Test
    void atualizarIDEspacoTest(){
        boolean resultado = A.atualizarID(animal, "   ");
        assertFalse(resultado);
    }

    @Test
    void atualizarIDEnterTest(){
        boolean resultado = A.atualizarID(animal, "\t\n");
        assertFalse(resultado);
    }

    /** A seguir, os métodos testam o método atualizarNome para garantir
     * que o novo nome seja inserido corretamente. */
    @Test
    void atualizarNomeTest(){
        boolean resultado = A.atualizarNome(animal, "Aurelio");
        assertTrue("O nome foi atualizado com sucesso!", resultado);
    }

    @Test
    void atualizarNomeIgualTest(){
        boolean resultado = A.atualizarNome(animal, "Lilica");
        assertFalse("Nao foi possivel atualizar o nome.", resultado);
    }

    @Test
    void atualizarNomeVazioTest(){
        boolean resultado = A.atualizarNome(animal, "");
        assertFalse(resultado);
    }

    @Test
    void atualizarNomeEspacoTest(){
        boolean resultado = A.atualizarNome(animal, "   ");
        assertFalse(resultado);
    }

    @Test
    void atualizarNomeEnterTest(){
        boolean resultado = A.atualizarNome(animal, "\t\n");
        assertFalse(resultado);
    }

    /** A seguir, os métodos testam o método atualizarEspecie para garantir
     * que a nova espécie seja inserida corretamente. */
    @Test
    void atualizarEspecieTest(){
        boolean resultado = A.atualizarEspecie(animal, "Cavalo");
        assertTrue("A especie foi atualizada com sucesso!", resultado);
    }

    @Test
    void atualizarEspecieIgualTest(){
        boolean resultado = A.atualizarEspecie(animal, "Gato");
        assertFalse("Nao foi possivel atualizar a especie.", resultado);
    }

    @Test
    void atualizarEspecieVaziaTest(){
        boolean resultado = A.atualizarEspecie(animal, "");
        assertFalse(resultado);
    }

    @Test
    void atualizarEspecieEspacoTest(){
        boolean resultado = A.atualizarEspecie(animal, "   ");
        assertFalse(resultado);
    }

    @Test
    void atualizarEspecieEnterTest(){
        boolean resultado = A.atualizarEspecie(animal, "\t\n");
        assertFalse(resultado);
    }

    /** A seguir, os métodos testam o método atualizarRaca para garantir
     * que a nova raça seja inserida corretamente. */
    @Test
    void atualizarRacaTest(){
        boolean resultado = A.atualizarRaca(animal, "Bengal");
        assertTrue("A raca foi atualizada com sucesso!", resultado);
    }

    @Test
    void atualizarRacaIgualTest(){
        boolean resultado = A.atualizarRaca(animal, "Siames");
        assertFalse("Nao foi possivel atualizar a raca.", resultado);
    }

    @Test
    void atualizarRacaVaziaTest(){
        boolean resultado = A.atualizarRaca(animal, "");
        assertFalse(resultado);
    }

    @Test
    void atualizarRacaEspacoTest(){
        boolean resultado = A.atualizarRaca(animal, "  ");
        assertFalse(resultado);
    }

    @Test
    void atualizarRacaEnterTest(){
        boolean resultado = A.atualizarRaca(animal, "\t\n");
        assertFalse(resultado);
    }

    /** Testa o método atualizarData para garantir que a nova data de
     * nascimento inserida seja válida. */
    @Test
    void atualizarDataTest(){
        YearMonth novaData = YearMonth.of(2018, 7);
        boolean resultado = A.atualizarData(animal, novaData);
        assertTrue("A data de nascimento foi atualizada com sucesso!", resultado);
    }

    /** Não permite a atualização para uma data adiantada. */
    @Test
    void atualizarDataFalsaTest(){
        YearMonth novaData = YearMonth.of(2045, 5);
        boolean resultado = A.atualizarData(animal, novaData);
        assertFalse("Nao foi possivel atualizar a data de nascimento.", resultado);
    }

    /** A seguir, os métodos testam o método atualizarSexo para garantir
     * que o novo sexo seja inserido corretamente. */
    @Test
    void atualizarSexoTest(){
        boolean resultado = A.atualizarSexo(animal, "Macho");
        assertTrue("O sexo foi atualizado com sucesso!", resultado);
    }

    @Test
    void atualizarSexoIgualTest(){
        boolean resultado = A.atualizarSexo(animal, "Femea");
        assertFalse("Nao foi possivel atualizar o sexo.", resultado);
    }

    @Test
    void atualizarSexoVazioTest(){
        boolean resultado = A.atualizarSexo(animal, "");
        assertFalse(resultado);
    }

    @Test
    void atualizarSexoEspacoTest(){
        boolean resultado = A.atualizarSexo(animal, " ");
        assertFalse(resultado);
    }

    @Test
    void atualizarSexoEnterTest(){
        boolean resultado = A.atualizarSexo(animal, "\t\n");
        assertFalse(resultado);
    }

    /** Testa o método atualizarSetor para garantir que o novo setor inserido
     * seja válido. */
    @Test
    void atualizarSetorTest(){
        A.cadastrarAnimal(animal);
        A.adicionarSetor(animal, setor);
        Setor setor2 = S.criarSetor("S4", "Modulo 7", new ArrayList<>(), new ArrayList<>());
        boolean resultado = A.atualizarSetor(animal, setor2);
        assertTrue("O setor foi atualizado com sucesso!", resultado);
    }

    /** Não permite a atualização dpara um setor nulo. */
    @Test
    void atualizarSetorNuloTest(){
        boolean resultado = A.atualizarSetor(animal, setor);
        assertFalse("Nao foi possivel atualizar o setor.", resultado);
    }

    /** Não permite a atualização para o mesmo setor. */
    @Test
    void atualizarSetorIgualTest(){
        A.cadastrarAnimal(animal);
        A.adicionarSetor(animal, setor);
        boolean resultado = A.atualizarSetor(animal, setor);
        assertFalse(resultado);
    }
}