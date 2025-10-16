package controller;
import dao.AnimalDAO;
import dao.SetorDAO;
import dao.TutorDAO;
import model.Animal;
import model.Endereco;
import model.Setor;
import model.Tutor;
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
 * @see TutorDAO
 */
public class TutorController {
    /** Objeto DAO (Data Access Object) responsável por gerenciar a persistência de dados. */
    private final TutorDAO tutorDAO;

    /** Um mapa que armazena objetos do tipo {@link Tutor}, usando o ID como chave. */
    private static HashMap<String, Tutor> tutores;

    /** Restaura o relacionamento bidirecional entre Tutor e Animal/Setor
     * após a desserialização do JSON, pois a referência de volta foi ignorada
     * pelo Jackson (via {@code JsonIgnore}). */
    private void ligarReferencias(HashMap<String, Setor> setores, HashMap<String, Animal> animais){
        for (Tutor tutor : tutores.values()){
            // Liga setor ao tutor
            if (tutor.getSetorID() != null){
                Setor setor = setores.get(tutor.getSetorID());
                if (setor != null) {
                    tutor.setSetor(setor);
                    if (!setor.getTutores().contains(tutor)){
                        setor.getTutores().add(tutor);
                    }
                }
            }
            // Liga animais ao tutor
            if (tutor.getAnimaisIDs() != null) {
                List<Animal> listaAnimais = new ArrayList<>();
                for (String animalID : tutor.getAnimaisIDs()){
                    Animal animal = animais.get(animalID);
                    if (animal != null) {
                        listaAnimais.add(animal);
                        if (!animal.getTutores().contains(tutor)){
                            animal.getTutores().add(tutor);
                        }
                    }
                }
                tutor.setAnimais(listaAnimais);
            }
        }
    }

    /** Construtor  da classe TutorController.
     * <p>
     * Incializa o DAO do tutor e carrega os dados do JSON para o Map de tutores.
     *
     */
    public TutorController(){
        this.tutorDAO = new TutorDAO();
        this.tutores = tutorDAO.carregarTutores();
        SetorDAO setorDAO = new SetorDAO();
        AnimalDAO animalDAO = new AnimalDAO();
        HashMap<String, Setor> setores = setorDAO.carregarSetores();
        HashMap<String, Animal> animais = animalDAO.carregarAnimais();
        ligarReferencias(setores, animais);
    }

    /** Salva os dados do tutor no aqruivo JSON. */
    private void salvarDadosTutor(){ tutorDAO.salvarTutor(this.tutores); }

    /** Cria uma nova instância de {@link Tutor} com as informações fornecidas.
     * Este método não persiste o tutor; ele apenas o instancia.
     *
     * @param ID         O ID do tutor.
     * @param nome       O nome do tutor.
     * @param endereco   O objeto {@link Endereco} do tutor.
     * @param telefone   O telefone do tutor.
     * @param email      O email do tutor.
     * @param setorID    O setor do tutor.
     * @param animaisID  A lista de IDs dos animais do tutor.
     * @return Uma nova instância de {@link Tutor}.
     */
    public Tutor criarTutor(String ID, String nome, Endereco endereco, String telefone,
                            String email, String setorID, List<String> animaisID) {
        return new Tutor(ID, nome, endereco, telefone, email, setorID, animaisID);
    }

    /** Valida se o ID inserido pelo usuário segue o padrão estipulado e
     * se já não é um ID existente.
     *
     * @param ID O ID inserido pelo usuário.
     * @return {@code true}, caso o ID seja válido, ou {@code false}, caso contrário.
     */
    public boolean validarIDTutor(String ID){
        return ID.trim().toUpperCase().matches("T[0-9]+") &&
                !tutores.containsKey(ID.trim().toUpperCase()) &&
                !ID.trim().toUpperCase().isBlank();
    }

    /** Limpa todos os dados da coleção em memória.
     * Este método deve ser usado apenas para fins de teste. */
    public void limparDadosParaTeste() { this.tutores.clear(); }

    /** Valida se o email inserido pelo usuário segue o padrão estipulado.
     *
     * @param email O email inserido pelo usuário.
     * @return {@code true}, caso o email seja válido, ou {@code false}, caso contrário.
     */
    public boolean validarEmail(String email){ return email.matches("^[a-zA-Z0-9]" +
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
        Setor setor = SetorController.buscarSetorPorID(tutor.getSetorID());
        tutor.setSetor(setor);
        if (setor != null) {
            if (setor.getTutores() == null) setor.setTutores(new ArrayList<>());
            setor.getTutores().add(tutor);
        }
        List<Animal> animais = new ArrayList<>();
        for (String animalID : tutor.getAnimaisIDs()) {
            Animal animal = AnimalController.buscarAnimalPorID(animalID);
            if (animal != null) {
                if (animal.getTutores() == null) animal.setTutores(new ArrayList<>());
                animal.getTutores().add(tutor);
                animais.add(animal);
            }
        }
        tutor.setAnimais(animais);
        tutores.put(tutor.getID(), tutor);
        salvarDadosTutor();
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
                salvarDadosTutor();
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
            salvarDadosTutor();
            return true;
        }
        return false;
    }

    /** Busca um tutor pelo seu ID
     *
     * @param ID O ID do tutor a ser procurado.
     * @return O objeto {@link Tutor} encontrado, ou {@code null} se não for encontrado.
     */
    public static Tutor buscarTutorPorID(String ID) { return tutores.get(ID.trim()); }

    /** Busca um tutor pelo seu nome. Caso haja mais de um tutor com o mesmo
     * nome, todos estes tutores são retornados numa lista.
     *
     * @param nome O nome a ser procurado
     * @return Uma lista de objetos do tipo {@link Tutor}
     * Retorna uma lista vazia se nenhum tutor for encontrado ou se o nome for inválido.
     */
    public List<Tutor> buscarTutorPorNome(String nome){
        if (!nome.isBlank()) {
            List<Tutor> tutoresNome = new ArrayList<>();
            for (Tutor tutor : tutores.values()) {
                if (tutor.getNome().equalsIgnoreCase(nome.trim())) {
                    tutoresNome.add(tutor);
                }
            }
            return tutoresNome;
        }
        return new ArrayList<>();
    }

    /** Adiciona o setor do tutor.
     *
     * @param tutor O objeto {@link Tutor} a quem será adicionado o setor.
     * @param setor O objeto {@link Setor} que será adicionado ao tutor.
     * @return {@code true}, caso o setor seja adiconado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean adicionarSetor(Tutor tutor, Setor setor){
        if (tutor != null && setor != null && tutor.getSetor() == null && !setor.getTutores().contains(tutor)){
            tutor.setSetor(setor);
            setor.getTutores().add(tutor);
            salvarDadosTutor();
            return true;
        }
        return false;
    }

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
    public List<Animal> listarAnimais (Tutor tutor){
        if (tutor.getAnimais().isEmpty()) return new ArrayList<>();
        return tutor.getAnimais();
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
        if (tutor != null && validarIDTutor(novoID) && !novoID.equalsIgnoreCase(tutor.getID())) {
            tutores.remove(tutor.getID()); // Remove o tutor com ID antigo
            tutor.setID(novoID); // Insere o novo ID no tutor
            tutores.put(tutor.getID(), tutor); // Insere o tutor com o novo ID no Map
            salvarDadosTutor();
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
            salvarDadosTutor();
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
        if (tutor != null && novoEndereco != null && novoEndereco != tutor.getEndereco()){
            tutor.setEndereco(novoEndereco);
            salvarDadosTutor();
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
            salvarDadosTutor();
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
            salvarDadosTutor();
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
            salvarDadosTutor();
            return true;
        }
        return false;
    }
}