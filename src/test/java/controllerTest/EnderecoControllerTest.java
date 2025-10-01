package controllerTest;
import controller.EnderecoController;
import model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 * Classe de teste de unidade para o controller da classe Endereco.
 * <p>
 * Esta classe contém um teste para verificar se o endereço
 * é criado corretamente.
 *
 * @author Kiara Alencar
 * @version 2.0
 * @see Endereco
 */
public class EnderecoControllerTest {
    /** Instância do endereço para o teste. */
    private EnderecoController controller;

    /** Cria uma nova instância da classe Endereco antes do teste. */
    @BeforeEach
    void setUp(){
        controller = new EnderecoController();
    }

    /**
     * Testa o método criarEndereco para garantir que o endereço
     * seja definido corretamente.
     */
    @Test
    void criarEnderecoTest(){
        Endereco endereco = controller.criarEndereco("Quiriate", "Centro", "48555120",
                "Ilheus", "Bahia");
        assertNotNull(endereco);
        assertEquals("Quiriate", endereco.getRua());
        assertEquals("Centro", endereco.getBairro());
        assertEquals("48555120", endereco.getCEP());
        assertEquals("Ilheus", endereco.getCidade());
        assertEquals("Bahia", endereco.getEstado());
    }

    /**
     * A seguir, todos os métodos testam o método validarCEP para garantir
     * que o CEP seja inserido corretamente.
     */
    @Test
    void CEPMenor(){
        boolean resultado = controller.validarCEP("45389");
        assertFalse("O CEP inserido tem menos de 8 digitos", resultado);
    }

    @Test
    void CEPMaior(){
        boolean resultado = controller.validarCEP("453892234");
        assertFalse("O CEP inserido tem mais de 8 digitos", resultado);
    }

    @Test
    void CEPComCaracteres(){
        boolean resultado = controller.validarCEP("45/j9%u34");
        assertFalse("O CEP inserido tem caracteres nao permitidos", resultado);
    }

    @Test
    void CEPVazio(){
        boolean resultado = controller.validarCEP("");
        assertFalse("Nenhum CEP foi inserido", resultado);
    }

    @Test
    void CEPNulo(){
        boolean resultado = controller.validarCEP(null);
        assertFalse("Nenhum CEP foi inserido", resultado);
    }

    @Test
    void CEPValido(){
        boolean resultado = controller.validarCEP("45777888");
        assertTrue("O CEP inserido eh valido", resultado);
    }
}