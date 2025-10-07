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

public class SetorControllerTest {
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
    void cadastrarSetorTest(){
        boolean resultado = S.cadastrarSetor(setor);
        assertTrue(resultado);
    }

    @Test
    void cadastrarSetorNuloTest(){
        boolean resultado = S.cadastrarSetor(null);
        assertFalse(resultado);
    }

    @Test
    void deletarSetorTest(){
        S.cadastrarSetor(setor);
        boolean resultado = S.deletarSetor(setor);
        assertTrue(resultado);
    }

    @Test
    void deletarSetorNuloTest(){
        boolean resultado = S.deletarSetor(null);
        assertFalse(resultado);
    }

    @Test
    void setorAtivoTest(){
        S.cadastrarSetor(setor);
        S.adicionarTutor(setor, tutor);
        boolean resultado = S.setorAtivo(setor);
        assertTrue(resultado);
    }

    @Test
    void setorInativoTest(){
        boolean resultado = S.setorAtivo(setor);
        assertFalse(resultado);
    }

    @Test
    void adicionarTutorTest(){
        boolean resultado = S.adicionarTutor(setor, tutor);
        assertTrue(resultado);
    }

    @Test
    void adicionarTutorInexistenteTest(){
        boolean resultado = S.adicionarTutor(setor, null);
        assertFalse(resultado);
    }

    @Test
    void adicionarTutorDuplicadoTest(){
        S.adicionarTutor(setor, tutor);
        boolean resultado = S.adicionarTutor(setor, tutor);
        assertFalse("Nao eh possivel adicionar tutores duplicados.", resultado);
    }

    @Test
    void removerTutorTest(){
        S.adicionarTutor(setor, tutor);
        boolean resultado = S.removerTutor(setor, tutor);
        assertTrue("Tutor removido com sucesso!", resultado);
    }

    @Test
    void removerTutorInexistenteTest(){
        boolean resultado = S.removerTutor(setor, tutor);
        assertFalse("Nao foi possivel remover este tutor.", resultado);
    }

    @Test
    void adicionarAnimalTest(){
        boolean resultado = S.adicionarAnimal(setor, animal);
        assertTrue(resultado);
    }

    @Test
    void adicionarAnimalInexistenteTest(){
        boolean resultado = S.adicionarAnimal(setor, null);
        assertFalse(resultado);
    }

    @Test
    void adicionarAnimalDuplicadoTest(){
        S.adicionarAnimal(setor, animal);
        boolean resultado = S.adicionarAnimal(setor, animal);
        assertFalse(resultado);
    }

    @Test
    void removerAnimalTest(){
        S.adicionarAnimal(setor, animal);
        boolean resultado = S.removerAnimal(setor, animal);
        assertTrue(resultado);
    }

    @Test
    void removerAnimalInexistenteTest(){
        boolean resultado = S.removerAnimal(setor, animal);
        assertFalse(resultado);
    }

    @Test
    void buscarPorIDTest(){
        S.cadastrarSetor(setor);
        Setor setor2 = S.buscarSetorPorID(setor.getID());
        assertNotNull("O setor foi encontrado com sucesso!", setor2);
    }

    @Test
    void buscarPorIDFalsoTest() {
        Setor setor2 = S.buscarSetorPorID("G6@");
        assertNull("Nao foi possivel encontrar este setor.", setor2);
    }

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

    @Test
    void listarSetoresInexistentesTest(){
        List<String> nomes = S.listarSetores();
        boolean listaCompleta = !nomes.isEmpty();
        assertFalse(listaCompleta);
    }

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

    @Test
    void listarTutoresInexistentesTest(){
        List<String> nomes = S.listarTutores(setor);
        boolean listaCompleta = !nomes.isEmpty();
        assertFalse(listaCompleta);
    }

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

    @Test
    void listarAnimaisInexistentesTest(){
        List<String> nomes = S.listarAnimais(setor);
        boolean listaCompleta = !nomes.isEmpty();
        assertFalse(listaCompleta);
    }

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
}
