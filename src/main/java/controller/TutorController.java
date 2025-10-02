package controller;
import model.Animal;
import model.Endereco;
import model.Setor;
import model.Tutor;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A classe TutorController é responsável por gerenciar as operações
 * CRUD (Criar, Ler, Atualizar, Deletar) e outras funcionalidades
 * relacionadas a objetos {@link Tutor}. Ela atua como uma camada de
 * controle/serviço que orquestra a manipulação dos dados dos tutores.
 *
 * @author Kiara Alencar
 * @version 1.4
 * @see Tutor
 * @see Setor
 * @see Animal
 */
public class TutorController {
    /** Um mapa que armazena objetos do tipo {@link Tutor}, usando o ID como chave. */
    private HashMap<String, Tutor> tutores;

    /** Construtor  da classe TutorController.
     * <p>
     * Incializa o mapa como uma nova instância de HashMap.
     *
     */
    public TutorController(){ this.tutores = new HashMap<>(); }

    /** Cria uma nova instância de {@link Tutor} com as informações fornecidas.
     * Este método não persiste o tutor; ele apenas o instancia.
     *
     * @param ID         O ID do tutor.
     * @param nome       O nome do tutor.
     * @param endereco   O objeto {@link Endereco} do tutor.
     * @param telefone   O telefone do tutor.
     * @param email      O email do tutor.
     * @param setor      O setor do tutor.
     * @param animais    A lista de animais do tutor.
     * @return Uma nova instância de {@link Tutor}.
     */
    public Tutor criarTutor(String ID, String nome, Endereco endereco, String telefone,
                            String email, Setor setor, List<Animal> animais) {
        return new Tutor(ID, nome, endereco, telefone, email, setor, new ArrayList<>());
    }

    /** Valida se o ID inserido pelo usuário segue o padrão estipulado e
     * se já não é um ID existente.
     *
     * @param ID O ID inserido pelo usuário.
     * @return {@code true}, caso o ID seja válido, ou {@code false}, caso contrário.
     */
    public boolean validarIDTutor(String ID){
        return ID.matches("T[0-9]+") && !tutores.containsKey(ID) && !ID.isBlank();
    }

    /** Valida se o email inserido pelo usuário segue o padrão estipulado.
     *
     * @param email O email inserido pelo usuário.
     * @return {@code true}, caso o email seja válido, ou {@code false}, caso contrário.
     */
    public boolean validarEmail(String email){ return email.matches("^[a-zA-Z0-9_!#$%&'*+/=?{|}~^-]" +
            "+(\\.[a-zA-Z0-9_!#$%&'*+/=?{|}~^-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,}$"); }

    /** Valida se o telefone inserido pelo usuário segue o padrão estipulado.
     *
     * @param telefone O telefone inserido pelo usuário.
     * @return {@code true}, caso o telefone seja válido, ou {@code false}, caso contrário.
     */
    public boolean validarTelefone(String telefone){ return telefone.matches("\\d{11}");}

    /** Cadastra um novo tutor no mapa de tutores.
     *
     * @param tutor O objeto {@link Tutor} a ser cadastrado.
     * @return {@code true}, caso o tutor consiga ser cadastrado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean cadastrarTutor(Tutor tutor){
        if (tutor == null || tutores.containsKey(tutor.getID())) return false;
        tutores.put(tutor.getID(), tutor);
        return true;
    }

    /** Exclui um tutor do mapa de tutores.
     *
     * @param tutor O objeto {@link Tutor} a ser deletado.
     * @return {@code true}, caso o tutor consiga ser deletado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean deletarTutor(Tutor tutor) {
        if (tutor != null) {
            Setor setor = tutor.getSetor();
            if (setor == null) {
                tutores.remove(tutor.getID()); // Se o tutor já está sem setor, é só remover ele do Map
                return true;
            }
            // Caso haja apenas este tutor no setor e o setor tenha animais
            if (setor.getTutores().size() == 1 && !setor.getAnimais().isEmpty()){
                return false;
            }
            // Caso haja animais no setor, mas tenha outros tutores
            if (!setor.getAnimais().isEmpty()){
                for (Animal animal : tutor.getAnimais()) {
                    animal.getTutores().remove(tutor);
                    tutor.getAnimais().remove(animal);
                }
            }
            setor.getTutores().remove(tutor);
            tutor.setSetor(null);
            tutores.remove(tutor.getID());
            return true;
            }
        return false;
    }

    /** Busca um tutor pelo seu ID
     *
     * @param ID O ID do tutor a ser procurado.
     * @return O objeto {@link Tutor} encontrado, ou {@code null} se não for encontrado.
     */
    public Tutor buscarTutorPorID(String ID) { return tutores.get(ID); }

    /** Busca em que setor está o tutor.
     *
     * @param tutor O objeto {@link Animal} a quem será feita a busca.
     * @return O setor em que está o tutor, ou {@code null} se houver algum erro.
     */
    public Setor buscarSetor(Tutor tutor){
        if (tutor != null && tutor.getSetor()!= null) return tutor.getSetor();
        return null;
    }

    /** Lista todos os tutores cadastrados no mapa de tutores.
     *
     * @return Uma lista contendo os nomes todos os tutores.
     */
    public List<String> listarTutores (){
        if (tutores.isEmpty()) return new ArrayList<>();
        List<String> nomesTutores = new ArrayList<>();
        for (Tutor tutor : tutores.values()){
            nomesTutores.add(tutor.getNome());
        }
        return nomesTutores;
    }

    /** Lista todos os animais do tutor.
     *
     * @param tutor O objeto {@link Setor} a ter a lista de animais procurada.
     * @return Uma lista com os nomes de todos os animais do tutor.
     */
    public List<String> listarAnimais (Tutor tutor){
        if (tutor.getAnimais().isEmpty()) return new ArrayList<>();
        List<String> nomesAnimais = new ArrayList<>();
        for (Animal animal : tutor.getAnimais()){
            nomesAnimais.add(animal.getNome());
        }
        return nomesAnimais;
    }

    /** Atualiza o ID do tutor.
     * <p>
     * O método remove o tutor do mapa e a insere novamente com o novo ID,
     * atualizando a chave.
     * </p>
     * @param tutor O objeto {@link Tutor} a ter o ID atualizado.
     * @param novoID O novo ID a ser atribuído.
     * @return {@code true} caso o ID seja atualizado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarID (Tutor tutor, String novoID){
        if (tutor != null && validarIDTutor(novoID)) {
            tutores.remove(tutor.getID()); // Remove o tutor com ID antigo
            tutor.setID(novoID); // Insere o novo ID no tutor
            tutores.put(tutor.getID(), tutor); // Insere o tutor com o novo ID no Map
            return true;
        }
        return false;
    }

    /** Atualiza o nome do tutor.
     *
     * @param tutor O objeto {@link Tutor} a ter o nome atualizado.
     * @param novoNome O novo nome a ser atribuído.
     * @return {@code true} caso o nome seja atualizado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarNome(Tutor tutor, String novoNome){
        if (tutor != null && !tutor.getNome().equalsIgnoreCase(novoNome) && !novoNome.isBlank()){
            tutor.setNome(novoNome);
            return true;
        }
        return false;
    }

    /** Atualiza o endereço do tutor.
     *
     * @param tutor O objeto {@link Tutor} a ter o endereço atualizado.
     * @param novoEndereco O novo endereço a ser atribuído.
     * @return {@code true} caso o endereço seja atualizado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarEndereco(Tutor tutor, Endereco novoEndereco){
        if (tutor != null && novoEndereco != null){
            tutor.setEndereco(novoEndereco);
            return true;
        }
        return false;
    }

    /** Atualiza o telefone do tutor.
     *
     * @param tutor O objeto {@link Tutor} a ter o telefone atualizado.
     * @param novoTelefone O novo telefone a ser atribuído.
     * @return {@code true} caso o telefone seja atualizado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarTelefone(Tutor tutor, String novoTelefone){
        if (tutor != null && !novoTelefone.equalsIgnoreCase(tutor.getTelefone()) && validarTelefone(novoTelefone)){
            tutor.setTelefone(novoTelefone);
            return true;
        }
        return false;
    }

    /** Atualiza o email do tutor.
     *
     * @param tutor O objeto {@link Animal} a ter o email atualizado.
     * @param novoEmail O novo nome a ser atribuído.
     * @return {@code true} caso o email seja atualizado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarEmail(Tutor tutor, String novoEmail){
        if (tutor != null && !novoEmail.equalsIgnoreCase(tutor.getEmail()) && validarEmail(novoEmail)){
            tutor.setTelefone(novoEmail);
            return true;
        }
        return false;
    }

    /** Atualiza o setor do tutor.
     *
     * @param tutor O objeto {@link Animal} a ter o setor atualizado.
     * @param novoSetor O novo setor a ser atribuído.
     * @return {@code true} caso o setor seja atualizado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarSetor(Tutor tutor, Setor novoSetor){
        if (tutor != null && novoSetor != null && tutor.getSetor() != novoSetor
                && !novoSetor.getTutores().contains(tutor)){
            if (tutor.getSetor() != null) { // Caso o tutor não seja recém criado
                if (tutor.getSetor().getTutores().size() == 1 && !tutor.getSetor().getAnimais().isEmpty()) {
                    return false; // Caso o setor tenha animais e apenas 1 tutor
                }
                if (!tutor.getSetor().getAnimais().isEmpty()) {
                    for (Animal animal : tutor.getSetor().getAnimais()) {
                        tutor.getAnimais().remove(animal);
                        animal.getTutores().remove(tutor);
                    }
                }
            }
            novoSetor.getTutores().add(tutor);
            tutor.setSetor(novoSetor);
            for (Animal animal : novoSetor.getAnimais()){
                animal.getTutores().add(tutor);
                tutor.getAnimais().add(animal);
            }
            return true;
        }
        return false;
    }
}

/* métodos a implementar:
    - construtor *FEITO*
    - validar ID *FEITO*
    - cadastrar tutor *FEITO*
    - deletar tutor *FEITO*
    - buscar tutor por id *FEITO*
    - buscar setor do tutor *FEITO*
    - listar tutores (mostrar seus setores) *FEITO*
    - listar animais do tutor *FEITO*
    - atualizar id do tutor *FEITO*
    - atualizar nome do tutor *FEITO*
    - atualizar endereco do tutor *FEITO*
    - atualizar telefone do tutor *FEITO*
    - atualizar email do tutor *FEITO*
    - atualizar setor do tutor *FEITO*
    */