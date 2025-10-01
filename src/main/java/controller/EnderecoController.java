package controller;
import model.Endereco;

/**
 * A classe EnderecoController é responsável por gerenciar operações
 * relacionadas à criação de objetos do tipo {@link Endereco}.
 * Ela atua como uma fábrica para instâncias de endereço.
 *
 * @author Kiara Alencar
 * @version 2.0
 * @see Endereco
 */
public class EnderecoController {

    /** Cria e retorna um novo objeto {@link Endereco}.
     * Este método é utilizado para instanciar endereços de forma padronizada.
     *
     * @param rua    O nome da rua ou avenida.
     * @param bairro O nome do bairro.
     * @param CEP    O CEP.
     * @param cidade O nome da cidade.
     * @param estado O nome do estado.
     * @return O objeto {@link Endereco} recém-criado.
     */
    public Endereco criarEndereco(String rua, String bairro, String CEP, String cidade, String estado) {
        return new Endereco(rua, bairro, CEP, cidade, estado);
    }

    /** Valida se o CEP inserido pelo usuário tem 8
     * dígitos, conforme o padrão. */
    public boolean validarCEP(String CEP){
        if (CEP != null) return CEP.matches("\\d{8}");
        return false;
    }
}