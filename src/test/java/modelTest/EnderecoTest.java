package modelTest;

import model.Endereco;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

/**
 * Classe de teste para Endereco.
 * <p>
 * Testa a imutabilidade da classe, validando se o construtor
 * inicializa os atributos corretamente e se os getters
 * retornam os valores esperados.
 *
 * @author Kiara Alencar
 * @version 1.0
 * @see model.Endereco
 */
public class EnderecoTest {
    /**
     * Testa se o construtor e os getters da classe Endereco funcionam.
     * <p>
     * Este teste valida se os valores passados para o construtor são
     * corretamente armazenados e podem ser recuperados pelos getters.
     */
    @Test
    void construtorTest() {
        Endereco endereco = new Endereco("Quiriate", "Sarinha", "45007120",
                "Itabuna", "Bahia");

        assertEquals("Quiriate", endereco.getRua());
        assertEquals("Sarinha", endereco.getBairro());
        assertEquals("45007120", endereco.getCEP());
        assertEquals("Itabuna", endereco.getCidade());
        assertEquals("Bahia", endereco.getEstado());
    }

    /**
     * Testa o método toString para garantir a formatação correta..
     */
    @Test
    void toStringTest(){
        Endereco endereco = new Endereco("Pinheiros", "Papagaio", "44021688",
                "Feira de Santana", "Bahia");

        String novoEndereco = "Rua/Avenida Pinheiros, 44021688 - Papagaio (Feira de Santana/Bahia)";
        assertEquals(novoEndereco, endereco.toString());
    }
}