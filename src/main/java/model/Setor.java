package model;
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
public class Setor {
    /** O ID do setor */
    private String ID;

    /** O nome do setor. */
    private String nome;

    /** O endereço do setor, que já é definido como UEFS.
     * static: garante que o atributo pertence à classe em si, e
     * não a qualquer objeto individual.
     * final: garante que o endereço não poderá ser alterado depois
     * de definido (é uma constante).
     * */
    private static final String ENDERECO = "Av. Transnordestina — UEFS";

    /** Pessoas tutoras do setor. */
    private List<Tutor> tutores;

    /** Animais do setor. */
    private List<Animal> animais;

    /**
     * Construtor da classe Setor.
     *<p>
     * @param nome      O nome do setor.
     * @param tutores   As pessoas tutoras do setor.
     * @param animais   Os animais do setor.
     */
    public Setor(String ID, String nome, List<Tutor> tutores, List<Animal> animais){
        this.ID = ID;
        this.nome = nome;
        this.tutores = tutores;
        this.animais = animais;
    }

    /** Outro construtor da classe Setor, para a biblioteca Jackson
     * conseguir instanciar a classe antes de preencher os atributos. */
    public Setor() {
        this.tutores = new ArrayList<>();
        this.animais = new ArrayList<>();
    }

    /** Retorna o ID do setor.
     *
     * @return O ID do setor.
     */
    public String getID() { return ID; }

    /** Define o ID.
     *
     * @param ID O novo ID a ser atribuído.
     */
    public void setID(String ID) { this.ID = ID; }

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

    /** Retorna as pessoas tutoras do setor.
     *
     * @return as pessoas tutoras do setor.
     */
    public List<Tutor> getTutores(){ return tutores; }

    /** Define as pessoas tutoras do setor.
     *
     * @param tutores Os novos tutores a serem atribuídos.
     */
    public void setTutores(List<Tutor> tutores) {
        this.tutores = tutores;
    }

    /** Retorna os animais do setor.
     *
     * @return os animais do setor.
     */
    public List<Animal> getAnimais(){ return animais; }

    /** Define os animais do setor.
     *
     * @param animais Os novos animais a serem atribuídos.
     */
    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }
}