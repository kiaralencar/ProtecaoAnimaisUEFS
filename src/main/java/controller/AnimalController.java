package controller;
import model.Animal;
import model.Setor;
import model.Tutor;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe AnimalController é responsável por gerenciar as operações
 * CRUD (Criar, Ler, Atualizar, Deletar) e outras funcionalidades
 * relacionadas a objetos {@link Animal}. Ela atua como uma camada de
 * controle/serviço que orquestra a manipulação dos dados dos animais.
 *
 * @author Kiara Alencar
 * @version 1.3
 * @see Animal
 * @see Setor
 * @see Tutor
 */
public class AnimalController {
    /** Um mapa que armazena objetos do tipo {@link Animal}, usando o ID como chave. */
    private HashMap<String, Animal> animais;

    /** Construtor  da classe AnimalController.
     * <p>
     * Incializa o mapa como uma nova instância de HashMap.
     *
     */
    public AnimalController(){ this.animais = new HashMap<>(); }

    /** Valida se o ID inserido pelo usuário segue o padrão estipulado e
     * se já não é um ID existente.
     *
     * @param ID O ID inserido pelo usuário.
     * @return {@code true}, caso o ID seja válido, ou {@code false}, caso contrário.
     */
    public boolean validarIDAnimal(String ID){ return ID.matches("A[0-9]+") && !animais.containsKey(ID); }

    /** Calcula a idade aproximada do animal.
     *
     * @param dataNascimento A data de nascimento do animal.
     * @return A idade aproximada do animal
     */
    public int calcularIdade(YearMonth dataNascimento){
        YearMonth dataHoje = YearMonth.now();
        int idade = dataHoje.getYear() - dataNascimento.getYear();
        if (dataHoje.getMonthValue() < dataNascimento.getMonthValue()) idade--;
        return idade;
    }

    /** Cadastra um novo animal no mapa de animais.
     *
     * @param animal O objeto {@link Animal} a ser cadastrado.
     * @return {@code true}, caso o animal consiga ser cadastrado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean cadastrarAnimal(Animal animal){
        if (animal == null || animais.containsKey(animal.getID())) return false;
        animais.put(animal.getID(), animal);
        return true;
    }

    /** Exclui um animal do mapa de animais.
     *
     * @param animal O objeto {@link Animal} a ser deletado.
     * @return {@code true}, caso o animal consiga ser deletado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean deletarAnimal(Animal animal){
        if (animal == null) return false;
        if (animal.getTutores() != null) {
            for (Tutor tutor : animal.getTutores()) {
                if (tutor.getAnimais() != null) {
                    tutor.getAnimais().remove(animal); // Remove o animal dos tutores do animal a ser deletado
                }
            }
        }
        animal.getTutores().clear(); // Apaga a lista de tutores
        Setor setor = animal.getSetor(); // Setor em que o animal está
        if (setor != null){
            setor.getAnimais().remove(animal);
        }
        animal.setSetor(null); // Setor do animal agora é nulo
        animais.remove(animal); // Remove o animal do Map de animais
        return true;
    }

    /** Adiciona um tutor à lista de tutores do animal.
     *
     * @param animal O objeto {@link Animal} a quem será adicionado o tutor.
     * @param tutor O objeto {@link Tutor} que será adicionado à lista de tutores do animal.
     * @return {@code true}, caso o tutor seja adicionado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean adicionarTutor (Animal animal, Tutor tutor){
        if (animal != null && tutor != null && !animal.getTutores().contains(tutor)){
            animal.getTutores().add(tutor);
            return true;
        }
        return false;
    }

    /** Remove um tutor da lista de tutores do animal.
     *
     * @param animal O objeto {@link Animal} de quem será removido o tutor.
     * @param tutor O objeto {@link Tutor} que será removido da lista de tutores do animal.
     * @return {@code true}, caso o tutor seja removido com sucesso, ou {@code false}, caso contrário.
     */
    public boolean removerTutor (Animal animal, Tutor tutor){
        if (animal != null && tutor != null && animal.getTutores().contains(tutor)
                && tutor.getAnimais().contains(animal)){
            animal.getTutores().remove(tutor); // Removo a pessoa da lista de tutores do animal
            tutor.getAnimais().remove(animal); // Remove o animal da lista de animais da pessoa tutor
            return true;
        }
        return false;
    }

    /** Busca um animal pelo seu ID
     *
     * @param ID O ID do animal a ser procurado.
     * @return O objeto {@link Animal} encontrado, ou {@code null} se não for encontrado.
     */
    public Animal buscarPorID(String ID){ return animais.get(ID); }

    /** Busca em que setor está o animal.
     *
     * @param animal O objeto {@link Animal} a quem será feita a busca.
     * @return O setor em que está o animal, ou {@code null} se houver algum erro.
     */
    public Setor buscarSetor(Animal animal){
        if (animal != null) return animal.getSetor();
        return null;
    }

    /** Lista todos os animais cadastrados no mapa de animais.
     *
     * @return Uma lista contendo os nomes todos os animais.
     */
    public List<String> listarAnimais (){
        if (animais.isEmpty()) return new ArrayList<>();
        List<String> nomesAnimais = new ArrayList<>();
        for (Animal animal : animais.values()){
            nomesAnimais.add(animal.getNome());
        }
        return nomesAnimais;
    }

    /** Lista todos os tutores do animal.
     *
     * @param animal O objeto {@link Animal} a ter a lista de tutores procurada.
     * @return Uma lista com os nomes de todos os tutores do animal.
     */
    public List<String> listarTutores (Animal animal){
        if (animal == null || animal.getTutores().isEmpty()) return new ArrayList<>();
        List<String> nomesTutores = new ArrayList<>();
        for (Tutor tutor : animal.getTutores()){
            nomesTutores.add(tutor.getNome());
        }
        return nomesTutores;
    }

    /** Atualiza o ID do animal.
     * <p>
     * O método remove o animal do mapa e a insere novamente com o novo ID,
     * atualizando a chave.
     * </p>
     * @param animal O objeto {@link Animal} a ter o ID atualizado.
     * @param novoID O novo ID a ser atribuído.
     * @return {@code true} caso o ID seja atualizado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarID (Animal animal, String novoID){
        /* A verificação da existência do ID é feita em "validarID", que é chamada
        na View a cada vez que é inserido um novo ID */
        if (animal != null) {
            animais.remove(animal.getID()); // Remove o animal com ID antigo
            animal.setID(novoID); // Insere o novo ID no animal
            animais.put(animal.getID(), animal); // Insere o animal com o novo ID no Map
            return true;
        }
        return false;
    }

    /** Atualiza o nome do animal.
     *
     * @param animal O objeto {@link Animal} a ter o nome atualizado.
     * @param novoNome O novo nome a ser atribuído.
     * @return {@code true} caso o nome seja atualizado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarNome(Animal animal, String novoNome){
        if (animal != null && !animal.getNome().equalsIgnoreCase(novoNome)){
            animal.setNome(novoNome);
            return true;
        }
        return false;
    }

    /** Atualiza a espécie do animal.
     *
     * @param animal O objeto {@link Animal} a ter a espécie atualizada.
     * @param novaEspecie A nova espécie a ser atribuída.
     * @return {@code true} caso a espécie seja atualizada com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarEspecie(Animal animal, String novaEspecie){
        if (animal != null && !animal.getEspecie().equalsIgnoreCase(novaEspecie)){
            animal.setEspecie(novaEspecie);
            return true;
        }
        return false;
    }

    /** Atualiza a raça do animal.
     *
     * @param animal O objeto {@link Animal} a ter a raça atualizada.
     * @param novaRaca A nova raça a ser atribuída.
     * @return {@code true} caso a raça seja atualizada com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarRaca(Animal animal, String novaRaca){
        if (animal != null && !animal.getRaca().equalsIgnoreCase(novaRaca)){
            animal.setRaca(novaRaca);
            return true;
        }
        return false;
    }

    /** Atualiza a data de nascimento do animal.
     *
     * @param animal O objeto {@link Animal} a ter a data de nascimento  atualizada.
     * @param novaData A nova data de nascimento  a ser atribuída.
     * @return {@code true} caso a data seja atualizada com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarData(Animal animal, YearMonth novaData){
        if (animal != null && !animal.getData().equals(novaData)){
            animal.setData(novaData);
            return true;
        }
        return false;
    }

    /** Atualiza o sexo do animal.
     *
     * @param animal O objeto {@link Animal} a ter o sexo atualizado.
     * @param novoSexo O novo sexo a ser atribuído.
     * @return {@code true} caso o sexo seja atualizado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarSexo(Animal animal, String novoSexo){
        if (animal != null && !animal.getSexo().equalsIgnoreCase(novoSexo)){
            animal.setSexo(novoSexo);
            return true;
        }
        return false;
    }

    /** Atualiza o setor do animal.
     *
     * @param animal O objeto {@link Animal} a ter o setor atualizado.
     * @param novoSetor O novo setor a ser atribuído.
     * @return {@code true} caso o setor seja atualizado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarSetor(Animal animal, Setor novoSetor){
        if (animal != null && novoSetor != null &&
                !animal.getSetor().getNome().equalsIgnoreCase(novoSetor.getNome())){
            animal.setSetor(novoSetor);
            return true;
        }
        return false;
    }





    /* métodos a implementar:
    - construtor *FEITO*
    - validar ID *FEITO*
    - calcular idade *FEITO*
    - cadastrar animal *FEITO*
    - deletar animal (tirar o animal do setor e da pessoa tutora) *FEITO*
    - adicionar pessoa tutora (listar disponiveis) *FEITO*
    - remover pessoa tutora (tirar o animal da lista dessa pessoa) *FEITO*
    - buscar animal por id *FEITO*
    - buscar setor do animal *FEITO*
    - listar pessoas tutoras do animal *FEITO*
    - listar animais *FEITO*
    - atualizar id do animal *FEITO*
    - atualizar nome do animal *FEITO*
    - atualizar especie do animal *FEITO*
    - atualizar raca do animal *FEITO*
    - atualizar data de nascimento do animal *FEITO*
    - atualizar sexo do animal *FEITO*
    - atualizar setor do animal *FEITO*
    */
}
