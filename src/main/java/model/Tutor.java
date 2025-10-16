package model;
import com.fasterxml.jackson.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe Tutor representa uma pessoa tutora com informações de nome, endereço
 * {@link Endereco}, telefone, email, setor em que está inserida {@link Setor} e
 * animais de quem é responsável {@link Animal}. Além disso, esta classe implementa
 * métodos e atributos próprios.
 *<p>
 * O @JsonIdentityInfo garante que, durante a serializacao para JSON,
 * o objeto seja serializado de forma completa apenas na primeira ocorrencia.
 * Em ocorrencias subsequentes (como em listas de referencia cruzada),
 * o Jackson serializa apenas uma referencia ao seu ID.
 * Isso evita loops infinitos e garante que o objeto seja desserializado corretamente
 * com todas as suas referencias em tempo de execucao.
 *</p>
 *
 * @author Kiara Alencar
 * @version 1.5
 * @see Endereco
 * @see Setor
 * @see Animal
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Tutor {
    /** O ID da pessoa tutora. */
    private String id;

    /** O nome da pessoa tutora. */
    private String nome;

    /** O endereço da pessoa tutora. */
    private Endereco endereco;

    /** O telefone da pessoa tutora. */
    private String telefone;

    /** O email da pessoa tutora. */
    private String email;

    /** O ID do setor da pessoa tutora. */
    private String setorID;

    /** A lista dos IDs dos animais da pessoa tutora. */
    private List<String> animaisID;

    /** O setor da pessoa tutora, que será ignorado pelo JSON durante
     * a serialização para evitar loops infinitos. */
    @JsonIgnore
    private Setor setor;

    /** Os animais da pessoa tutora, que serão ignorados pelo JSON durante
     * a serialização para evitar loops infinitos. */
    @JsonIgnore
    private List<Animal> animais;

    /**
     * Construtor da classe Tutor.
     *<p>
     * @param ID         O ID da pessoa tutora.
     * @param nome       O nome da pessoa tutora.
     * @param endereco   O enderço da pessoa tutora.
     * @param telefone   O telefone da pessoa tutora.
     * @param email      O email da pessoa tutora.
     * @param setorID    O ID do setor da pessoa tutora.
     * @param animaisID  Os IDs dos animais da pessoa tutora.
     */
    public Tutor(String ID, String nome, Endereco endereco, String telefone, String email,
                 String setorID, List<String> animaisID){
        this.id = ID;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.setorID = setorID;
        this.animaisID = animaisID;
        this.animais = new ArrayList<>();
    }

    /** Outro construtor da classe Tutor, para a biblioteca Jackson
     * conseguir instanciar a classe antes de preencher os atributos. */
    public Tutor() {
        this.animais = new ArrayList<>();
        this.animaisID = new ArrayList<>();
    }

    /** Retorna o ID da pessoa tutora.
     *
     * @return O ID da pessoa tutora.
     */
    public String getID() { return id; }

    /** Define o ID.
     *
     * @param ID O novo ID a ser atribuído.
     */
    public void setID(String ID) { this.id = ID; }

    /** Retorna o nome da pessoa tutora.
     *
     * @return O nome da pessoa tutora.
     */
    public String getNome() {
        return nome;
    }

    /** Define o nome.
     *
     * @param nome O novo nome a ser atribuído.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** Retorna o endereço da pessoa tutora.
     *
     * @return O endereço da pessoa tutora.
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /** Define o endereço.
     *
     * @param endereco O novo endereço a ser atribuído.
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /** Retorna o telefone da pessoa tutora.
     *
     * @return O telefone da pessoa tutora.
     */
    public String getTelefone() {
        return telefone;
    }

    /** Define o telefone.
     *
     * @param telefone O novo telefone a ser atribuído.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /** Retorna o email da pessoa tutora.
     *
     * @return O email da pessoa tutora.
     */
    public String getEmail() {
        return email;
    }

    /** Define o email.
     *
     * @param email O novo email a ser atribuído.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** Retorna o setor da pessoa tutora, que será ignorado pelo JSON durante
     * a serialização para evitar loops infinitos..
     *
     * @return O setor da pessoa tutora.
     */
    @JsonIgnore
    public Setor getSetor (){ return setor; }

    /** Define o setor.
     *
     * @param setor O novo setor a ser atribuído.
     */
    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    /** Retorna os animais da pessoa tutora, que será ignorado pelo JSON durante
     * a serialização para evitar loops infinitos..
     *
     * @return os animais da pessoa tutora.
     */
    @JsonIgnore
    public List<Animal> getAnimais(){ return animais; }

    /** Define os animais da pessoa tutora.
     *
     * @param animais Os novos animais a serem atribuídos.
     */
    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }


    /** Retorna o ID do setor do tutor.
     *
     * @return O ID do setor do tutor.
     */
    public String getSetorID(){ return setorID; }

    /** Define o ID do setor do tutor.
     *
     * @param ID O novo ID do setor do tutor a ser atribuído.
     */
    public void setSetorID(String ID) { this.setorID = ID; }

    /** Retorna os IDs dos animais do tutor.
     *
     * @return Os IDs dos animais do tutor.
     */
    public List<String> getAnimaisIDs(){ return animaisID; }

    /** Define os IDs dos animais do tutor.
     *
     * @param animaisID Os novos IDs a serem atribuídos.
     */
    public void setAnimaisIDs(List<String> animaisID) { this.animaisID = animaisID; }

    /** Retorna uma representação em String formatada do telefone da pessoa tutora.
     *
     * @return Uma String formatada com as informações do telefone da pessoa tutora.
     */
    public String formatarTelefone() {
        return telefone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
    }
}