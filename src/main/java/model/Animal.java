package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe Animal representa um animal com informações de nome, espécie,
 * raça, data de nascimento, sexo, situação (em observação, disponível
 * para adoção ou em tratamento), setor responsável {@link Setor} e pessoas
 * tutoras {@link Tutora}. Além disso, esta classe implementa métodos e atributos próprios.
 *
 * @author Kiara Alencar
 * @version 1.1
 * @see Setor
 * @see Tutora
 */
public class Animal {
    /** O ID do animal. */
    private int ID;

    /** O nome do animal. */
    private String nome;

    /** A espécie do animal. */
    private String especie;

    /** A raça do animal. */
    private String raca;

    /** A data de nascimento do animal. */
    private LocalDate data;

    /** O sexo do animal. */
    private String sexo;

    /** A situação do animal. */
    private String situacao;

    /** O setor do animal. */
    private Setor setor;

    /** As pessoas responsáveis pelo animal. */
    private List<Tutora> tutores;

    /**
     * Construtor da classe Animal.
     *<p>
     * @param ID        O ID do animal.
     * @param nome      O nome do animal.
     * @param especie   A espécie do animal.
     * @param raca      A raça do animal.
     * @param data      A data de nascimento do animal.
     * @param sexo      O sexo do animal.
     * @param situacao  A situacao do animal.
     * @param setor     O setor do animal.
     * @param tutores   As pessoas tutoras do animal.
     */
    public Animal(int ID, String nome, String especie, String raca, LocalDate data, String sexo,
                  String situacao, Setor setor, List<Tutora> tutores){
        this.ID = ID;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.data = data;
        this.sexo = sexo;
        this.situacao = situacao;
        this.setor = setor;
        this.tutores = (tutores != null) ? tutores : new ArrayList<>();
    }

    /**
     * Outro construtor da classe Animal, para a biblioteca Jackson
     * conseguir instanciar a classe antes de preencher os atributos.
     */
    public Animal() { this.tutores = new ArrayList<>(); }

    /** Retorna o ID do animal.
     *
     * @return O ID do animal.
     */
    public int getID (){ return ID; }

    /** Define o ID.
     *
     * @param ID O novo ID a ser atribuído.
     */
    public void setID(int ID) { this.ID = ID; }

    /** Retorna o nome do animal.
     *
     * @return O nome do animal.
     */
    public String getNome() { return nome; }

    /** Define o nome.
     *
     * @param nome O novo nome a ser atribuído.
     */
    public void setNome(String nome) { this.nome = nome; }

    /** Retorna a espécie do animal.
     *
     * @return A espécie do animal.
     */
    public String getEspecie() { return especie; }

    /** Define a espécie.
     *
     * @param especie A nova espécie a ser atribuída.
     */
    public void setEspecie(String especie) { this.especie = especie; }

    /** Retorna a raça do animal.
     *
     * @return A raça do animal.
     */
    public String getRaca() { return raca; }

    /** Define a raça.
     *
     * @param raca A nova raca a ser atribuída.
     */
    public void setRaca(String raca) { this.raca = raca; }

    /** Retorna a data de nascimento do animal.
     *
     * @return A data de nascimento do animal.
     */
    public LocalDate getData() { return data; }

    /** Define a data de nascimento.
     *
     * @param data A nova data de nascimento a ser atribuída.
     */
    public void setData(LocalDate data) { this.data = data; }

    /** Retorna o sexo do animal.
     *
     * @return O sexo do animal.
     */
    public String getSexo() { return sexo; }

    /** Define o sexo.
     *
     * @param sexo O novo sexo a ser atribuído.
     */
    public void setSexo(String sexo) { this.sexo = sexo; }

    /** Retorna a situação do animal.
     *
     * @return A situação do animal.
     */
    public String getSituacao() { return situacao; }

    /** Define a situação.
     *
     * @param situacao A nova situacao a ser atribuída.
     */
    public void setSituacao(String situacao) { this.situacao = situacao; }

    /** Retorna o setor do animal.
     *
     * @return O setor do animal.
     */
    public Setor getSetor (){ return setor; }

    /** Define o setor.
     *
     * @param setor O novo setor a ser atribuído.
     */
    public void setSetor(Setor setor) { this.setor = setor; }

    /** Retorna as pessoas responsaveis pelo animal.
     *
     * @return as pessoas responsaveis pelo animal.
     */
    public List<Tutora> getTutores(){ return tutores; }

    /** Define as pessoas responsaveis pelo animal.
     *
     * @param tutores Os novos tutores a serem atribuídos.
     */
    public void setTutores(List<Tutora> tutores) { this.tutores = tutores; }
}