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

/**
 * Classe de teste de unidade para o controller da classe {@link Setor},
 * chamada de {@link SetorController}.
 * <p>
 * Esta classe verifica a correta implementação e o comportamento de
 * todas as funcionalidades relacionadas ao gerenciamento de setores.
 *
 * @author Kiara Alencar
 * @version 1.5
 * @see Animal
 * @see Setor
 * @see Tutor
 * @see Endereco
 * @see AnimalController
 * @see SetorController
 * @see TutorController
 * @see EnderecoController
 */
public class SetorControllerTest {
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
        S = new SetorController();
        A = new AnimalController();
        T = new TutorController();
        E = new EnderecoController();
        setor = S.criarSetor("S1", "Reitoria", new ArrayList<>(), new ArrayList<>());
        animal = A.criarAnimal("A1", "Lilica", "Gato", "Siames",
                YearMonth.of(2020, 9), "Femea", "Disponivel", null, new ArrayList<>());
        endereco = E.criarEndereco("A", "B", "C", "D", "E");
        tutor = T.criarTutor("T1", "Ana", endereco, "73765413278",
                "ana@gmail.com", null, new ArrayList<>());
    }

    /** Testa o método criarSetor para garantir que o setor
     * seja definido corretamente. */
    @Test
    void criarSetorTest(){
        assertNotNull(setor);
        assertEquals("S1", setor.getID());
        assertEquals("Reitoria", setor.getNome());
        assertNotNull(setor.getTutores());
        assertNotNull(setor.getAnimais());
        assertTrue(setor.getTutores().isEmpty());
        assertTrue(setor.getAnimais().isEmpty());
    }

    /** A seguir, os métodos testam o método validarIDSetor para garantir
     * que o ID seja inserido corretamente. */
    @Test
    void validarIDTest(){
        boolean resultado = S.validarIDSetor("S3");
        assertTrue(resultado);
    }

    @Test
    void validarIDFalsoTest(){
        boolean resultado = S.validarIDSetor("*UO");
        assertFalse(resultado);
    }

    @Test
    void validarIDVazioTest(){
        boolean resultado = S.validarIDSetor("");
        assertFalse(resultado);
    }

    @Test
    void validarIDEspacoTest(){
        boolean resultado = S.validarIDSetor(" ");
        assertFalse(resultado);
    }

    @Test
    void validarIDEnterTest(){
        boolean resultado = S.validarIDSetor("\t\n");
        assertFalse(resultado);
    }

    /** Testa o método cadastrarSetor para garantir que o setor seja
     * inserido do Map de setores. */
    @Test
    void cadastrarSetorTest(){
        boolean resultado = S.cadastrarSetor(setor);
        assertTrue(resultado);
    }

    /** Não permite o cadastro de um setor nulo. */
    @Test
    void cadastrarSetorNuloTest(){
        boolean resultado = S.cadastrarSetor(null);
        assertFalse(resultado);
    }

    /** Testa o método deletarSetor para garantir que o setor seja
     * removido do Map de setores. */
    @Test
    void deletarSetorTest(){
        S.cadastrarSetor(setor);
        boolean resultado = S.deletarSetor(setor);
        assertTrue(resultado);
    }

    /** Não permite deletar um setor nulo. */
    @Test
    void deletarSetorNuloTest(){
        boolean resultado = S.deletarSetor(null);
        assertFalse(resultado);
    }

    /** Testa o método setorAtivo para garantir que um animal só pode
     * ser adicionado se o setor estiver ativo, ou seja, caso haja tutores nele. */
    @Test
    void setorAtivoTest(){
        S.cadastrarSetor(setor);
        S.adicionarTutor(setor, tutor);
        boolean resultado = S.setorAtivo(setor);
        assertTrue(resultado);
    }

    /** Verifica se o setor está inativo. */
    @Test
    void setorInativoTest(){
        boolean resultado = S.setorAtivo(setor);
        assertFalse(resultado);
    }

    /** Testa o método adicionarTutor para garantir que o tutor seja
     * adicionado à lista de tutores do setor corretamente. */
    @Test
    void adicionarTutorTest(){
        boolean resultado = S.adicionarTutor(setor, tutor);
        assertTrue(resultado);
    }

    /** Não permite que seja adicionado um tutor nulo. */
    @Test
    void adicionarTutorInexistenteTest(){
        boolean resultado = S.adicionarTutor(setor, null);
        assertFalse(resultado);
    }

    /** Não permite que seja adicionado um tutor duplicado. */
    @Test
    void adicionarTutorDuplicadoTest(){
        S.adicionarTutor(setor, tutor);
        boolean resultado = S.adicionarTutor(setor, tutor);
        assertFalse("Nao eh possivel adicionar tutores duplicados.", resultado);
    }

    /** Testa o método removerTutor para garantir que o tutor seja
     * removido da lista de tutores do setor corretamente. */
    @Test
    void removerTutorTest(){
        S.adicionarTutor(setor, tutor);
        boolean resultado = S.removerTutor(setor, tutor);
        assertTrue("Tutor removido com sucesso!", resultado);
    }

    /** Não permite que seja removido um tutor nulo. */
    @Test
    void removerTutorInexistenteTest(){
        boolean resultado = S.removerTutor(setor, tutor);
        assertFalse("Nao foi possivel remover este tutor.", resultado);
    }

    /** Testa o método adicionarAnimal para garantir que o animal seja
     * adicionado à lista de animais do setor corretamente. */
    @Test
    void adicionarAnimalTest(){
        boolean resultado = S.adicionarAnimal(setor, animal);
        assertTrue(resultado);
    }

    /** Não permite que seja adicionado um animal inexistente. */
    @Test
    void adicionarAnimalInexistenteTest(){
        boolean resultado = S.adicionarAnimal(setor, null);
        assertFalse(resultado);
    }

    /** Não permite que seja adicionado um animal duplicado. */
    @Test
    void adicionarAnimalDuplicadoTest(){
        S.adicionarAnimal(setor, animal);
        boolean resultado = S.adicionarAnimal(setor, animal);
        assertFalse(resultado);
    }

    /** Testa o método removerAnimal para garantir que o animal seja
     * removido da lista de animais do setor corretamente. */
    @Test
    void removerAnimalTest(){
        S.adicionarAnimal(setor, animal);
        boolean resultado = S.removerAnimal(setor, animal);
        assertTrue(resultado);
    }

    /** Não permite que seja removido um animal inexistente. */
    @Test
    void removerAnimalInexistenteTest(){
        boolean resultado = S.removerAnimal(setor, animal);
        assertFalse(resultado);
    }

    /** Testa o método buscarSetorPorID para garantir que o setor
     * seja encontrado ao ser buscado pelo seu ID. */
    @Test
    void buscarPorIDTest(){
        S.cadastrarSetor(setor);
        Setor setor2 = S.buscarSetorPorID(setor.getID());
        assertNotNull("O setor foi encontrado com sucesso!", setor2);
    }

    /** Não permite que seja encontrado um setor com um ID falso. */
    @Test
    void buscarPorIDFalsoTest() {
        Setor setor2 = S.buscarSetorPorID("G6@");
        assertNull("Nao foi possivel encontrar este setor.", setor2);
    }

    /** Testa o método listarSetores para pontuar todos os
     * setores cadastrados. */
    @Test
    void listarSetoresTest(){
        Setor setor2 = S.criarSetor("S2", "Modulo 2", new ArrayList<>(), new ArrayList<>());
        Setor setor3 = S.criarSetor("S3", "Modulo 3", new ArrayList<>(), new ArrayList<>());
        S.cadastrarSetor(setor);
        S.cadastrarSetor(setor2);
        S.cadastrarSetor(setor3);
        List<String> nomes = S.listarSetores();
        boolean listaCompleta = !nomes.isEmpty();
        assertTrue(listaCompleta);
    }

    /** Não permite listar setores inexistentes, ou seja, aqueles que não
     * foram cadastrados. */
    @Test
    void listarSetoresInexistentesTest(){
        List<String> nomes = S.listarSetores();
        boolean listaCompleta = !nomes.isEmpty();
        assertFalse(listaCompleta);
    }

    /** Testa o método listarTutores para pontuar todos os
     * tutores do setor. */
    @Test
    void listarTutoresTest(){
        Tutor tutor2 = T.criarTutor("T2", "Maria", endereco, "73765410078",
                "maria@gmail.com", setor, new ArrayList<>());
        Tutor tutor3 = T.criarTutor("T3", "Braga", endereco, "73723410078",
                "braga@gmail.com", setor, new ArrayList<>());
        S.adicionarTutor(setor, tutor);
        S.adicionarTutor(setor, tutor2);
        S.adicionarTutor(setor, tutor3);
        List<String> nomes = S.listarTutores(setor);
        boolean listaCompleta = !nomes.isEmpty();
        assertTrue("Os tutores foram listados com sucesso!", listaCompleta);
    }

    /** Não permite listar tutores inexistentes, ou seja, aqueles que não
     * foram adicionados ao setor. */
    @Test
    void listarTutoresInexistentesTest(){
        List<String> nomes = S.listarTutores(setor);
        boolean listaCompleta = !nomes.isEmpty();
        assertFalse(listaCompleta);
    }

    /** Testa o método listarAnimais para pontuar todos
     * os animais do setor. */
    @Test
    void listarAnimaisTest(){
        Animal animal2 = A.criarAnimal("A2", "Beth", "Cachorro", "Poodle",
                YearMonth.of(2021, 2), "Femea", "Tratamento", null, new ArrayList<>());
        Animal animal3 = A.criarAnimal("A3", "Tony", "Cachorro", "Salsicha",
                YearMonth.of(2019, 3), "Macho", "Observacao", null, new ArrayList<>());
        S.adicionarAnimal(setor, animal);
        S.adicionarAnimal(setor, animal2);
        S.adicionarAnimal(setor, animal3);
        List<String> nomes = S.listarAnimais(setor);
        boolean listaCompleta = !nomes.isEmpty();
        assertTrue(listaCompleta);
    }

    /** Não permite listar animais inexistentes, ou seja, que não
     * foram adicionados à lista de animais do setor. */
    @Test
    void listarAnimaisInexistentesTest(){
        List<String> nomes = S.listarAnimais(setor);
        boolean listaCompleta = !nomes.isEmpty();
        assertFalse(listaCompleta);
    }

    /** A seguir, os métodos testam o método atualizarID para garantir
     * que o novo ID seja inserido corretamente. */
    @Test
    void atualizarIDTest(){
        boolean resultado = S.atualizarID(setor, "S6");
        assertTrue(resultado);
    }

    @Test
    void atualizarIDFalsoTest(){
        boolean resultado = S.atualizarID(setor, "8HY");
        assertFalse(resultado);
    }

    /** A seguir, os métodos testam o método atualizarNome para garantir
     * que o novo nome seja inserido corretamente. */
    @Test
    void atualizarNomeTest(){
        boolean resultado = S.atualizarNome(setor, "Modulo 7");
        assertTrue(resultado);
    }

    @Test
    void atualizarNomeIgualTest(){
        boolean resultado = S.atualizarNome(setor, "Reitoria");
        assertFalse(resultado);
    }

    @Test
    void atualizarNomeVazioTest(){
        boolean resultado = S.atualizarNome(setor, "");
        assertFalse(resultado);
    }

    @Test
    void atualizarNomeEspacoTest(){
        boolean resultado = S.atualizarNome(setor, "   ");
        assertFalse(resultado);
    }

    @Test
    void atualizarNomeEnterTest(){
        boolean resultado = S.atualizarNome(setor, "\t\n");
        assertFalse(resultado);
    }
}
