package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe Setor representa um setor da UEFS com informações de nome,
 * endereço {@link Endereco}, pessoas tutoras {@link Tutora} e animais
 * {@link Animal}. Além disso, esta classe implementa métodos e atributos próprios.
 *
 * @author Kiara Alencar
 * @version 1.1
 * @see Endereco
 * @see Tutora
 * @see Animal
 */
public class Setor {
    /** O nome do setor. */
    private String nome;

    /** O endereço do setor. */
    private Endereco endereco;

    /** Pessoas tutoras do setor. */
    private List<Tutora> tutores;

    /** Animais do setor. */
    private List<Animal> animais;

    /**
     * Construtor da classe Setor.
     *<p>
     * @param nome      O nome do setor.
     * @param endereco  O enderço do setor.
     * @param tutores   As pessoas tutoras do setor.
     * @param animais   Os animais do setor.
     */
    public Setor(String nome, Endereco endereco, List<Tutora> tutores, List<Animal> animais){
        this.nome = nome;
        this.endereco = endereco;
        this.tutores = (tutores != null) ? tutores : new ArrayList<>();
        this.animais = (animais != null) ? animais : new ArrayList<>();
    }

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
    public Endereco getEndereco() { return endereco; }

    /** Define o endereço.
     *
     * @param endereco O novo endereço a ser atribuído.
     */
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    /** Retorna as pessoas tutoras do setor.
     *
     * @return as pessoas tutoras do setor.
     */
    public List<Tutora> getTutores(){ return tutores; }

    /** Define as pessoas tutoras do setor.
     *
     * @param tutores Os novos tutores a serem atribuídos.
     */
    public void setTutores(List<Tutora> tutores) {
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