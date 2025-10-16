package controller;
import dao.AnimalDAO;
import dao.SetorDAO;
import dao.TutorDAO;
import model.Animal;
import model.Setor;
import model.Tutor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A classe SetorController é responsável por gerenciar as operações
 * CRUD (Criar, Ler, Atualizar, Deletar) e outras funcionalidades
 * relacionadas a objetos {@link Setor}. Ela atua como uma camada de
 * controle/serviço que orquestra a manipulação dos dados dos setores.
 *
 * @author Kiara Alencar
 * @version 1.2
 * @see Setor
 * @see Animal
 * @see Tutor
 * @see SetorDAO
 * @see AnimalDAO
 * @see TutorDAO
 */
public class SetorController {
    /** Objeto DAO (Data Access Object) responsável por gerenciar a persistência de dados. */
    private final SetorDAO setorDAO;

    /** Um mapa que armazena objetos do tipo {@link Setor}, usando o ID como chave. */
    public static HashMap<String, Setor> setores;

    /** Restaura o relacionamento bidirecional entre Setor e Tutor/Animal
     * após a desserialização do JSON, pois a referência de volta foi ignorada
     * pelo Jackson (via {@code JsonIgnore}). */
    private void ligarReferencias(HashMap<String, Tutor> tutores, HashMap<String, Animal> animais){
        for (Setor setor : setores.values()){
            // Liga tutor ao setor
            if (setor.getTutoresIDs() != null) {
                List<Tutor> listaTutores = new ArrayList<>();
                for (String tutorID : setor.getTutoresIDs()) {
                    Tutor tutor = tutores.get(tutorID);
                    if (tutor != null && !setor.getTutores().contains(tutor)) {
                        listaTutores.add(tutor);
                        tutor.setSetor(setor);
                    }
                }
                setor.setTutores(listaTutores);
            }
            // Liga animal ao setor
            if (setor.getAnimaisIDs() != null){
                List<Animal> listaAnimais = new ArrayList<>();
                for (String animalID : setor.getAnimaisIDs()){
                    Animal animal = animais.get(animalID);
                    if (animal != null && !setor.getAnimais().contains(animal)) {
                        listaAnimais.add(animal);
                        animal.setSetor(setor);
                    }
                }
                setor.setAnimais(listaAnimais);
            }
        }
    }

    /** Construtor  da classe SetorController.
     * <p>
     * Incializa o DAO do setor e carrega os dados do JSON para o Map de setores.
     * Além disso, liga as referências ignoradas durante a serialização do JSON.
     *
     */
    public SetorController(){
        this.setorDAO = new SetorDAO();
        this.setores = setorDAO.carregarSetores();
        AnimalDAO animalDAO = new AnimalDAO();
        TutorDAO tutorDAO = new TutorDAO();
        HashMap<String, Tutor> tutores = tutorDAO.carregarTutores();
        HashMap<String, Animal> animais = animalDAO.carregarAnimais();
        ligarReferencias(tutores, animais);
    }

    /** Salva os dados do setor no aqruivo JSON. */
    private void salvarDadosSetor(){ setorDAO.salvarSetor(this.setores); }

    /** Cria uma nova instância de {@link Setor} com as informações fornecidas.
     * Este método não persiste o setor; ele apenas o instancia.
     *
     * @param ID         O ID do setor.
     * @param nome       O nome do setor.
     * @param tutoresID    A lista de tutores do setor.
     * @param animaisID    A lista de animais setor.
     * @return Uma nova instância de {@link Setor}.
     */
    public Setor criarSetor(String ID, String nome, List<String> tutoresID, List<String> animaisID){
        return new Setor(ID, nome, tutoresID, animaisID);
    }

    /** Valida se o ID inserido pelo usuário segue o padrão estipulado e
     * se já não é um ID existente.
     *
     * @param ID O ID inserido pelo usuário.
     * @return {@code true}, caso o ID seja válido, ou {@code false}, caso contrário.
     */
    public boolean validarIDSetor(String ID){
        return ID.trim().toUpperCase().matches("S[0-9]+") &&
                !setores.containsKey(ID.trim().toUpperCase()) &&
                !ID.trim().toUpperCase().isBlank();
    }

    /** Valida o nome do setor, já que não pode haver setores com mesmo nome.
     *
     * @param nome O nome a ser validado
     * @return {@code true}, caso o nome seja válido, ou {@code false}, caso contrário.
     */
    public boolean validarNomeSetor(String nome){
        if (!nome.trim().isBlank()) {
            for (Setor setor : setores.values()) {
                if (setor.getNome().equalsIgnoreCase(nome.trim())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /** Limpa todos os dados da coleção em memória.
     * Este método deve ser usado apenas para fins de teste. */
    public void limparDadosParaTeste() { this.setores.clear(); }

    /** Cadastra um novo setor no mapa de setores.
     *
     * @param setor O objeto {@link Setor} a ser cadastrado.
     * @return {@code true}, caso o setor consiga ser cadastrado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean cadastrarSetor(Setor setor){
        if (setor == null || setores.containsKey(setor.getID())) return false;
        String novoNome = setor.getNome();
        for (Setor s : setores.values()){
            if (s.getNome().equalsIgnoreCase(novoNome)) return false;
        }
        List<Tutor> tutoresReais = new ArrayList<>();
        for (String tutorID : setor.getTutoresIDs()) {
            Tutor tutor = TutorController.buscarTutorPorID(tutorID);
            if (tutor != null) {
                tutor.setSetor(setor);
                if (setor.getTutores() == null) setor.setTutores(new ArrayList<>());
                setor.getTutores().add(tutor);
                tutoresReais.add(tutor);
            }
        }
        setor.setTutores(tutoresReais);
        List<Animal> animaisReais = new ArrayList<>();
        for (String animalID : setor.getAnimaisIDs()) {
            Animal animal = AnimalController.buscarAnimalPorID(animalID);
            if (animal != null) {
                animal.setSetor(setor);
                if (setor.getAnimais() == null) setor.setAnimais(new ArrayList<>());
                setor.getAnimais().add(animal);
                animaisReais.add(animal);
            }
        }
        setor.setAnimais(animaisReais);
        setores.put(setor.getID(), setor);
        salvarDadosSetor();
        return true;
    }


    /** Exclui um setor do mapa de setores.
     *
     * @param setor O objeto {@link Setor} a ser deletado.
     * @return {@code true}, caso o setor consiga ser deletado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean deletarSetor(Setor setor){
        if (setor != null) {
            if (!setor.getTutores().isEmpty() || !setor.getAnimais().isEmpty()) return false;
            setores.remove(setor.getID());
            salvarDadosSetor();
            return true;
        }
        return false;
    }

    /** Verifica se o setor está disponível para alocar animais. Ou
     * seja, se o setor existe e se há tutores nele.
     *
     * @param setor O objeto {@link Setor} a ser verificado.
     * @return {@code true}, caso o setor esteja disponível, ou {@code false}, caso contrário.
     */
    public boolean setorAtivo(Setor setor){ return setor != null && !setor.getTutores().isEmpty(); }

    /** Adiciona um tutor à lista de tutores do setor.
     *
     * @param setor O objeto {@link Setor} a quem será adicionado o tutor.
     * @param tutor O objeto {@link Tutor} que será adicionado à lista de tutores do setor.
     * @return {@code true}, caso o tutor seja adicionado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean adicionarTutor (Setor setor, Tutor tutor) {
        if (setor != null && tutor != null && !setor.getTutores().contains(tutor)) {
            List<Animal> animaisSetor = setor.getAnimais();
            if (tutor.getSetor() == null) { // Caso seja uma pessoa recém cadastrada, sem setor estabelecido
                for (Animal animal : animaisSetor) {
                    tutor.getAnimais().add(animal);
                    animal.getTutores().add(tutor);
                }
                setor.getTutores().add(tutor);
                tutor.setSetor(setor);
                salvarDadosSetor();
                return true;
            }
            Setor setorAntigo = tutor.getSetor();
            List<Animal> animaisSetorAntigo = setorAntigo.getAnimais();
            // Se tiver outra pessoa no setor antigo, os animais ficarão lá com essa outra pessoa
            if (setorAntigo.getTutores().size() > 1) {
                for (Animal animal : animaisSetorAntigo) {
                    tutor.getAnimais().remove(animal);
                    animal.getTutores().remove(tutor);
                }
                setorAntigo.getTutores().remove(tutor); // Remove a pessoa tutor do setor antigo
                setor.getTutores().add(tutor); // Adiciona a pessoa na lista de tutores do setor
                tutor.setSetor(setor); // Estabelece o novo setor da pessoa tutor
                for (Animal animal : animaisSetor) {
                    tutor.getAnimais().add(animal);
                    animal.getTutores().add(tutor);
                }
                salvarDadosSetor();
                return true;
            }
        }
        /* Se NÃO tiver outra pessoa no setor antigo, a pessoa tutor
        não pode ser movida, pois os animais ficarão sem tutor */
        return false;
    }

    /** Remove um tutor da lista de tutores do setor.
     *
     * @param setor O objeto {@link Setor} a quem será removido o tutor.
     * @param tutor O objeto {@link Tutor} que será removido da lista de tutores do setor.
     * @return {@code true}, caso o tutor seja removido com sucesso, ou {@code false}, caso contrário.
     */
    public boolean removerTutor(Setor setor, Tutor tutor){
        if (setor != null && tutor != null && setor.getTutores().contains(tutor) && tutor.getSetor() == setor){
            if (!setor.getAnimais().isEmpty() && setor.getTutores().size() == 1){
                return false; // Se tiver animais e apenas 1 tutor
            } else {
                for (Animal animal : setor.getAnimais()){
                    tutor.getAnimais().remove(animal);
                    animal.getTutores().remove(tutor);
                }
                setor.getTutores().remove(tutor);
                tutor.setSetor(null);
                salvarDadosSetor();
                return true;
            }
        }
        return false;
    }

    /** Adiciona um animal à lista de animais do setor.
     *
     * @param setor O objeto {@link Setor} a quem será adicionado o animal.
     * @param animal O objeto {@link Animal} que será adicionado à lista de animais do setor.
     * @return {@code true}, caso o animal seja adicionado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean adicionarAnimal(Setor setor, Animal animal){
        if (setor != null && animal != null && !setor.getAnimais().contains(animal) && animal.getSetor() != setor){
            // Se for um animal recém cadastrado, sem setor estabelecido
            if (animal.getSetor() == null){
                for (Tutor tutor : setor.getTutores()){
                    animal.getTutores().add(tutor);
                    tutor.getAnimais().add(animal);
                }
                animal.setSetor(setor);
                setor.getAnimais().add(animal);
                salvarDadosSetor();
                return true;
            }
            // Se o animal vier de outro setor
            Setor setorAntigo = animal.getSetor();
            for (Tutor tutor : setorAntigo.getTutores()){
                animal.getTutores().remove(tutor);
                tutor.getAnimais().remove(animal);
            }
            setorAntigo.getAnimais().remove((animal));
            animal.setSetor(setor);
            setor.getAnimais().add(animal);
            for (Tutor tutor : setor.getTutores()){
                animal.getTutores().add(tutor);
                tutor.getAnimais().add(animal);
            }
            salvarDadosSetor();
            return true;
        }
        return false;
    }

    /** Remove um animal da lista de animais do setor.
     *
     * @param setor O objeto {@link Setor} a quem será removido o animal.
     * @param animal O objeto {@link Animal} que será removido da lista de animais do setor.
     * @return {@code true}, caso o animal seja removido com sucesso, ou {@code false}, caso contrário.
     */
    public boolean removerAnimal(Setor setor, Animal animal){
        if (setor != null && animal != null && setor.getAnimais().contains(animal)
                && animal.getSetor() == setor){
            for (Tutor tutor : setor.getTutores()){
                tutor.getAnimais().remove(animal);
                animal.getTutores().remove(tutor);
            }
            setor.getAnimais().remove(animal);
            animal.setSetor(null);
            salvarDadosSetor();
            return true;
        }
        return false;
    }

    /** Busca um setor pelo seu ID
     *
     * @param ID O ID do setor a ser procurado.
     * @return O objeto {@link Setor} encontrado, ou {@code null} se não for encontrado.
     */
    public static Setor buscarSetorPorID(String ID){ return setores.get(ID.trim()); }

    /** Busca um setor pelo seu nome. Caso haja mais de um setor com o mesmo
     * nome, todos estes setores são retornados numa lista.
     *
     * @param nome O nome a ser procurado
     * @return Uma lista de objetos do tipo {@link Setor}
     * Retorna uma lista vazia se nenhum setor for encontrado ou se o nome for inválido.
     */
    public List<Setor> buscarSetorPorNome(String nome){
        if (!nome.isBlank()) {
            List<Setor> nomes = new ArrayList<>();
            for (Setor setor : setores.values()) {
                if (setor.getNome().equalsIgnoreCase(nome.trim())) {
                    nomes.add(setor);
                }
            }
            return nomes;
        }
        return new ArrayList<>();
    }

    /** Lista todos os setores cadastrados no mapa de setores.
     *
     * @return Uma lista contendo todos os setores.
     */
    public List<Setor> listarSetores (){ return new ArrayList<>(setores.values()); }

    /** Lista todos os tutores do setor.
     *
     * @param setor O objeto {@link Setor} a ter a lista de tutores procurada.
     * @return Uma lista com os nomes de todos os tutores do setor.
     */
    public List<String> listarTutores(Setor setor){
        if (setor == null || setor.getTutores().isEmpty()) return new ArrayList<>();
        List<String> nomeTutores = new ArrayList<>();
        for (Tutor tutor : setor.getTutores()){
            nomeTutores.add(tutor.getNome());
        }
        return nomeTutores;
    }

    /** Lista todos os animais do setor.
     *
     * @param setor O objeto {@link Setor} a ter a lista de animais procurada.
     * @return Uma lista com os nomes de todos os animais do setor.
     */
    public List<String> listarAnimais(Setor setor){
        if (setor == null || setor.getAnimais().isEmpty()) return new ArrayList<>();
        List<String> nomeAnimais = new ArrayList<>();
        for (Animal animal : setor.getAnimais()){
            nomeAnimais.add(animal.getNome());
        }
        return nomeAnimais;
    }

    /** Atualiza o ID do setor.
     * <p>
     * O método remove o setor do mapa e a insere novamente com o novo ID,
     * atualizando a chave.
     * </p>
     * @param setor O objeto {@link Setor} a ter o ID atualizado.
     * @param novoID O novo ID a ser atribuído.
     * @return {@code true} caso o ID seja atualizado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarID (Setor setor, String novoID){
        if (setor != null && validarIDSetor(novoID)) {
            setores.remove(setor.getID()); // Remove o setor com ID antigo
            setor.setID(novoID); // Insere o novo ID no setor
            setores.put(setor.getID(), setor); // Insere o setor com o novo ID no Map
            salvarDadosSetor();
            return true;
        }
        return false;
    }

    /** Atualiza o nome do setor.
     *
     * @param setor O objeto {@link Animal} a ter o nome atualizado.
     * @param novoNome O novo nome a ser atribuído.
     * @return {@code true} caso o nome seja atualizado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarNome(Setor setor, String novoNome){
        if (!novoNome.isBlank()) {
            boolean setorExistente = false;
            for (Setor s : setores.values()) {
                if (s.getNome().equalsIgnoreCase(novoNome)) {
                    setorExistente = true;
                    break;
                }
            }
            if (setor != null && !setor.getNome().equalsIgnoreCase(novoNome)
                    && !setorExistente && validarNomeSetor(novoNome)){
                setor.setNome(novoNome);
                salvarDadosSetor();
                return true;
            }
        }
        return false;
    }
}