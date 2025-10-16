package model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe Setor representa um setor da UEFS com informações de nome,
 * endereço, pessoas tutoras {@link Tutor} e animais {@link Animal}.
 * Além disso, esta classe implementa métodos e atributos próprios.
 *
 * @author Kiara Alencar
 * @version 1.4
 * @see Tutor
 * @see Animal
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)

public class Setor {
    /** O ID do setor */
    private String id;

    /** O nome do setor. */
    private String nome;

    /** O endereço do setor, que já é definido como UEFS.
     * static: garante que o atributo pertence à classe em si, e
     * não a qualquer objeto individual.
     * final: garante que o endereço não poderá ser alterado depois
     * de definido (é uma constante).
     * */
    private static final String ENDERECO = "Av. Transnordestina — UEFS";

    /** Pessoas tutoras do setor, que serão ignoradas pelo JSON durante
     * a serialização para evitar loops infinitos. */
    @JsonIgnore
    private List<Tutor> tutores;

    /** Animais do setor, que serão ignoradas pelo JSON durante
     * a serialização para evitar loops infinitos. */
    @JsonIgnore
    private List<Animal> animais;

    /** A lista dos IDs dos tutores do setor. */
    private List<String> tutoresID;

    /** A lista dos IDs dos animais do setor. */
    private List<String> animaisID;

    /**
     * Construtor da classe Setor.
     *<p>
     * @param ID        O ID do setor.
     * @param nome      O nome do setor.
     * @param tutoresID   As pessoas tutoras do setor.
     * @param animaisID   Os animais do setor.
     */
    public Setor(String ID, String nome, List<String> tutoresID, List<String> animaisID){
        this.id = ID;
        this.nome = nome;
        this.tutores = new ArrayList<>();
        this.animais = new ArrayList<>();
        this.tutoresID = tutoresID;
        this.animaisID = animaisID;
    }

    /** Outro construtor da classe Setor, para a biblioteca Jackson
     * conseguir instanciar a classe antes de preencher os atributos. */
    public Setor() {
        this.tutores = new ArrayList<>();
        this.animais = new ArrayList<>();
        this.tutoresID = new ArrayList<>();
        this.animaisID = new ArrayList<>();
    }

    /** Retorna o ID do setor.
     *
     * @return O ID do setor.
     */
    public String getID() { return id; }

    /** Define o ID.
     *
     * @param ID O novo ID a ser atribuído.
     */
    public void setID(String ID) { this.id = ID; }

    /** Retorna o nome do setor.
     *
     * @return O nome do setor.
     */
    public String getNome() { return nome; }

    /** Define o nome.
     *
     * @param nome O novo nome a ser atribuído.
     */
    public void setNome(String nome) { this.nome = nome; }

    /** Retorna o endereço do setor.
     *
     * @return O endereço do setor.
     */
    public static String getEndereco() { return ENDERECO; }

    /** Retorna as pessoas tutoras do setor, que será ignorado pelo JSON
     * durante a serialização para evitar loops infinitos.
     *
     * @return as pessoas tutoras do setor.
     */
    @JsonIgnore
    public List<Tutor> getTutores(){ return tutores; }

    /** Define as pessoas tutoras do setor.
     *
     * @param tutores Os novos tutores a serem atribuídos.
     */
    public void setTutores(List<Tutor> tutores) {
        this.tutores = tutores;
    }

    /** Retorna os animais do setor, que será ignorado pelo JSON
     * durante a serialização para evitar loops infinitos.
     *
     * @return os animais do setor.
     */
    @JsonIgnore
    public List<Animal> getAnimais(){ return animais; }

    /** Define os animais do setor.
     *
     * @param animais Os novos animais a serem atribuídos.
     */
    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    /** Retorna os IDs dos tutores do setor.
     *
     * @return Os IDs dos tutores do setor.
     */
    public List<String> getTutoresIDs(){ return tutoresID; }

    /** Define os IDs dos tutores do setor.
     *
     * @param tutoresIDs Os novos IDs a serem atribuídos.
     */
    public void setTutoresIDs(List<String> tutoresIDs) { this.tutoresID = tutoresIDs; }

    /** Retorna os IDs dos animais do setor.
     *
     * @return Os IDs dos animais do setor.
     */
    public List<String> getAnimaisIDs(){ return animaisID; }

    /** Define os IDs dos animais do setor.
     *
     * @param animaisID Os novos IDs a serem atribuídos.
     */
    public void setAnimaisIDs(List<String> animaisID) { this.animaisID = animaisID; }
}