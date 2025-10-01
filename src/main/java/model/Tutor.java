package model;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe Tutor representa uma pessoa tutora com informações de nome, endereço
 * {@link Endereco}, telefone, email, setor em que está inserida {@link Setor} e
 * animais de quem é responsável {@link Animal}. Além disso, esta classe implementa
 * métodos e atributos próprios.
 *
 * @author Kiara Alencar
 * @version 1.2
 * @see Endereco
 * @see Setor
 * @see Animal
 */
public class Tutor {
    /** O ID da pessoa tutora. */
    private String ID;

    /** O nome da pessoa tutora. */
    private String nome;

    /** O endereço da pessoa tutora. */
    private Endereco endereco;

    /** O telefone da pessoa tutora. */
    private String telefone;

    /** O email da pessoa tutora. */
    private String email;

    /** O setor da pessoa tutra. */
    private Setor setor;

    /** Os animais da pessoa tutora. */
    private List<Animal> animais;

    /**
     * Construtor da classe Tutor.
     *<p>
     * @param ID         O ID da pessoa tutora.
     * @param nome       O nome da pessoa tutora.
     * @param endereco   O enderço da pessoa tutora.
     * @param telefone   O telefone da pessoa tutora.
     * @param email      O email da pessoa tutora.
     * @param setor      O setor da pessoa tutora.
     * @param animais    Os animais da pessoa tutora.
     */
    public Tutor(String ID, String nome, Endereco endereco, String telefone, String email, Setor setor, List<Animal> animais){
        this.ID = ID;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.setor = setor;
        this.animais= (animais != null) ? animais : new ArrayList<>();
    }

    /** Retorna o ID da pessoa tutora.
     *
     * @return O ID da pessoa tutora.
     */
    public String getID() { return ID; }

    /** Define o ID.
     *
     * @param ID O novo ID a ser atribuído.
     */
    public void setID(String ID) { this.ID = ID; }

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

    /** Retorna o setor da pessoa tutora.
     *
     * @return O setor da pessoa tutora.
     */
    public Setor getSetor (){ return setor; }

    /** Define o setor.
     *
     * @param setor O novo setor a ser atribuído.
     */
    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    /** Retorna os animais da pessoa tutora.
     *
     * @return os animais da pessoa tutora.
     */
    public List<Animal> getAnimais(){ return animais; }

    /** Define os animais da pessoa tutora.
     *
     * @param animais Os novos animais a serem atribuídos.
     */
    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    /** Retorna uma representação em String formatada do telefone da pessoa tutora.
     *
     * @return Uma String formatada com as informações do telefone da pessoa tutora.
     */
    public String formatarTelefone() {
        return telefone.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
    }
}