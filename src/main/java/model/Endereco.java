package model;

/**
 * A classe  Endereco representa um endereço com informações de localização,
 * incluindo rua, bairro, CEP, cidade e estado.
 *
 * @author Kiara Alencar
 * @version 2.0
 */
public class Endereco {

    /** O nome da rua ou avenida. */
    private String rua;

    /** O nome do bairro. */
    private String bairro;

    /** O CEP. */
    private String cep;

    /** O nome da cidade. */
    private String cidade;

    /** O nome do estado. */
    private String estado;

    /**
     * Construtor da classe Endereco.
     *<p>
     * @param rua    O nome da rua ou avenida.
     * @param bairro O nome do bairro.
     * @param cep    O CEP do endereço.
     * @param cidade O nome da cidade.
     * @param estado O nome do estado.
     */
    public Endereco(String rua, String bairro, String cep, String cidade, String estado) {
        this.rua = rua;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    /** Outro construtor da classe Animal, para a biblioteca Jackson
     * conseguir instanciar a classe antes de preencher os atributos. */
    public Endereco(){}

    /** Retorna o nome da rua.
     *
     * @return A rua do endereço.
     */
    public String getRua() {
        return rua;
    }

    /** Retorna o nome do bairro.
     *
     * @return O bairro do endereço.
     */
    public String getBairro() {
        return bairro;
    }

    /** Retorna o CEP do endereço.
     *
     * @return O CEP do endereço.
     */
    public String getCEP() {
        return cep;
    }

    /** Retorna o nome da cidade.
     *
     * @return A cidade do endereço.
     */
    public String getCidade() {
        return cidade;
    }

    /** Retorna o nome do estado.
     *
     * @return O estado do endereço.
     */
    public String getEstado() {
        return estado;
    }

    /** Retorna uma representação em String formatada do endereço.
     *
     * @return Uma String formatada com as informações do endereço.
     */
    @Override
    public String toString() {
        return String.format("Rua/Avenida %s, %s - %s (%s/%s)", rua, cep, bairro, cidade, estado);
    }
}