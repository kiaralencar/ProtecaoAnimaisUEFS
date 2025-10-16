package model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe Animal representa um animal com informações de nome, espécie,
 * raça, data de nascimento, sexo, situação (em observação, disponível
 * para adoção ou em tratamento), setor responsável {@link Setor} e pessoas
 * tutoras {@link Tutor}. Além disso, esta classe implementa métodos e atributos próprios.
 * <p>
 * O @JsonIdentityInfo garante que, durante a serializacao para JSON,
 * o objeto seja serializado de forma completa apenas na primeira ocorrencia.
 * Em ocorrencias subsequentes (como em listas de referencia cruzada),
 * o Jackson serializa apenas uma referencia ao seu ID.
 * Isso evita loops infinitos e garante que o objeto seja desserializado corretamente
 * com todas as suas referencias em tempo de execucao.
 *</p>
 *
 * @author Kiara Alencar
 * @version 1.6
 * @see Setor
 * @see Tutor
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Animal {
    /** O ID do animal. */
    private String id;

    /** O nome do animal. */
    private String nome;

    /** A espécie do animal. */
    private String especie;

    /** A raça do animal. */
    private String raca;

    /** A data de nascimento do animal. */
    private YearMonth data;

    /** O sexo do animal. */
    private String sexo;

    /** A situação do animal. */
    private String situacao;

    /** O ID do setor do animal. */
    private String setorID;

    /** A lista dos IDs dos tutores do animal. */
    private List<String> tutoresID;

    /** O setor do animal, que será ignorado pelo JSON durante
     * a serialização para evitar loops infinitos. */
    @JsonIgnore
    private Setor setor;

    /** As pessoas responsáveis pelo animal, que serão ignoradas pelo JSON durante
     * a serialização para evitar loops infinitos. */
    @JsonIgnore
    private List<Tutor> tutores;

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
     * @param setorID   O ID do setor do animal.
     * @param tutoresID Os IDs dos tutores do animal.
     */
    public Animal(String ID, String nome, String especie, String raca, YearMonth data, String sexo,
                  String situacao, String setorID, List<String> tutoresID){
        this.id = ID;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.data = data;
        this.sexo = sexo;
        this.situacao = situacao;
        this.setorID = setorID;
        this.tutoresID = tutoresID;
    }

    /**
     * Outro construtor da classe Animal, para a biblioteca Jackson
     * conseguir instanciar a classe antes de preencher os atributos.
     */
    public Animal() {
        this.tutores = new ArrayList<>();
        this.tutoresID = new ArrayList<>();
    }

    /** Retorna o ID do animal.
     *
     * @return O ID do animal.
     */
    public String getID (){ return id; }

    /** Define o ID.
     *
     * @param ID O novo ID a ser atribuído.
     */
    public void setID(String ID) { this.id = ID; }

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
    public YearMonth getData() { return data; }

    /** Define a data de nascimento.
     *
     * @param data A nova data de nascimento a ser atribuída.
     */
    public void setData(YearMonth data) { this.data = data; }

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

    /** Retorna o setor do animal, que será ignorado pelo JSON durante
     * a serialização para evitar loops infinitos.
     *
     * @return O setor do animal.
     */
    @JsonIgnore
    public Setor getSetor (){ return setor; }

    /** Define o setor.
     *
     * @param setor O novo setor a ser atribuído.
     */
    public void setSetor(Setor setor) { this.setor = setor; }

    /** Retorna as pessoas responsaveis pelo animal, que será ignorado pelo JSON
     * durante a serialização para evitar loops infinitos.
     *
     * @return as pessoas responsaveis pelo animal.
     */
    @JsonIgnore
    public List<Tutor> getTutores(){ return tutores; }

    /** Define as pessoas responsaveis pelo animal.
     *
     * @param tutores Os novos tutores a serem atribuídos.
     */
    public void setTutores(List<Tutor> tutores) { this.tutores = tutores; }

    /** Retorna o ID do setor do animal.
     *
     * @return O ID do setor do animal.
     */
    public String getSetorID(){ return setorID; }

    /** Define o ID do setor do animal.
     *
     * @param ID O novo ID do setor do animal a ser atribuído.
     */
    public void setSetorID(String ID) { this.setorID = ID; }

    /** Retorna os IDs dos tutores do animal.
     *
     * @return Os IDs dos tutores do animal.
     */
    public List<String> getTutoresIDs(){ return tutoresID; }

    /** Define os IDs dos tutores do animal.
     *
     * @param tutoresIDs Os novos IDs a serem atribuídos.
     */
    public void setTutoresIDs(List<String> tutoresIDs) { this.tutoresID = tutoresIDs; }
}