package controller;
import dao.SetorDAO;
import dao.TutorDAO;
import model.Animal;
import model.Setor;
import model.Tutor;
import dao.AnimalDAO;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import static controller.SetorController.setores;
import static controller.TutorController.tutores;

/**
 * A classe AnimalController é responsável por gerenciar as operações
 * CRUD (Criar, Ler, Atualizar, Deletar) e outras funcionalidades
 * relacionadas a objetos {@link Animal}. Ela atua como uma camada de
 * controle/serviço que orquestra a manipulação dos dados dos animais.
 *
 * @author Kiara Alencar
 * @version 1.8
 * @see Animal
 * @see Setor
 * @see Tutor
 * @see AnimalDAO
 * @see SetorDAO
 * @see TutorDAO
 */
public class AnimalController {
    /** Objeto DAO (Data Access Object) responsável por gerenciar a persistência de dados. */
    private final AnimalDAO animalDAO;

    /** Um mapa que armazena objetos do tipo {@link Animal}, usando o ID como chave. */
    private static HashMap<String, Animal> animais;

    /** Construtor  da classe AnimalController.
     * <p>
     * Incializa o DAO do animal e carrega os dados do JSON para o Map de animais.
     * Além disso, liga as referências ignoradas durante a serialização do JSON.
     *
     */
    public AnimalController(){
        this.animalDAO = new AnimalDAO();
        this.animais = animalDAO.carregarAnimais();
        SetorDAO setorDAO = new SetorDAO();
        TutorDAO tutorDAO = new TutorDAO();
        HashMap<String, Setor> setores = setorDAO.carregarSetores();
        HashMap<String, Tutor> tutores = tutorDAO.carregarTutores();
        ligarReferencias(setores, tutores);
    }

    /** Restaura o relacionamento bidirecional entre Animal e Tutor/Setor
     * após a desserialização do JSON, pois a referência de volta foi ignorada
     * pelo Jackson (via {@code JsonIgnore}). */
    private void ligarReferencias(HashMap<String, Setor> setores, HashMap<String, Tutor> tutores){
        for (Animal animal : animais.values()){
            // Liga setor ao animal
            if (animal.getSetorID() != null){
                Setor setor = setores.get(animal.getSetorID());
                if (setor != null) {
                    animal.setSetor(setor);
                    if (!setor.getAnimais().contains(animal)){
                        setor.getAnimais().add(animal);
                    }
                }
            }
            // Liga tutores ao animal
            if (animal.getTutoresIDs() != null){
                List<Tutor> listaTutores = new ArrayList<>();
                for (String tutorID : animal.getTutoresIDs()){
                    Tutor tutor = tutores.get(tutorID);
                    if (tutor != null) {
                        listaTutores.add(tutor);
                        if (!tutor.getAnimais().contains(animal)){
                            tutor.getAnimais().add(animal);
                        }
                    }
                }
                animal.setTutores(listaTutores);
            }
        }
    }

    /** Salva os dados do animal no aqruivo JSON. */
    private void salvarDadosAnimal(){ animalDAO.salvarAnimal(this.animais); }

    /** Cria uma nova instância de {@link Animal} com as informações fornecidas.
     * Este método não persiste o animal; ele apenas o instancia.
     *
     * @param ID         O ID do animal.
     * @param nome       O nome do animal.
     * @param especie    A espécie do animal.
     * @param raca       A raça do animal.
     * @param data       A data de nascimento do animal.
     * @param sexo       O sexo do animal.
     * @param situacao   A situacao do animal.
     * @param setorID    O ID do setor do animal.
     * @param tutoresID  A lista de IDs dos tutores do animal.
     * @return Uma nova instância de {@link Animal}.
     */
    public Animal criarAnimal(String ID, String nome, String especie, String raca, YearMonth data,
                            String sexo, String situacao, String setorID, List<String> tutoresID) {
        return new Animal(ID, nome, especie, raca, data, sexo, situacao, setorID, tutoresID);
    }

    /** Limpa todos os dados da coleção em memória.
     * Este método deve ser usado apenas para fins de teste. */
    public void limparDadosParaTeste() { this.animais.clear(); }

    /** Valida se o ID inserido pelo usuário segue o padrão estipulado e
     * se já não é um ID existente.
     *
     * @param ID O ID inserido pelo usuário.
     * @return {@code true}, caso o ID seja válido, ou {@code false}, caso contrário.
     */
    public boolean validarIDAnimal(String ID){
        return ID.trim().toUpperCase().matches("A[0-9]+") &&
                !animais.containsKey(ID.trim().toUpperCase()) &&
                !ID.trim().toUpperCase().isBlank();
    }

    /** Valida se a data de nascimento inserida pelo usuário é válida,
     * ou seja, se é anterior ou igual à data atual.
     *
     * @param dataNascimento A data de nascimento inserida pelo usuário.
     * @return {@code true}, caso a data seja válida, ou {@code false}, caso contrário.
     */
    public boolean validarData(YearMonth dataNascimento){
        YearMonth dataHoje = YearMonth.now();
        return dataNascimento.isBefore(dataHoje) || dataNascimento.equals(dataHoje);
    }

    /** Calcula a idade aproximada do animal.
     *
     * @param dataNascimento A data de nascimento do animal.
     * @return A idade aproximada do animal.
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
        Setor setor = SetorController.buscarSetorPorID(animal.getSetorID());
        animal.setSetor(setor);
        if (setor != null) {
            if (setor.getAnimais() == null) setor.setAnimais(new ArrayList<>());
            setor.getAnimais().add(animal);
        }
        List<Tutor> tutores = new ArrayList<>();
        for (String tutorID : animal.getTutoresIDs()) {
            Tutor tutor = TutorController.buscarTutorPorID(tutorID);
            if (tutor != null) {
                if (tutor.getAnimais() == null) tutor.setAnimais(new ArrayList<>());
                tutor.getAnimais().add(animal);
                tutores.add(tutor);
            }
        }
        animal.setTutores(tutores);
        animais.put(animal.getID(), animal);
        salvarDadosAnimal();
        return true;
    }

    /** Exclui um animal do mapa de animais.
     *
     * @param animal O objeto {@link Animal} a ser deletado.
     * @return {@code true}, caso o animal consiga ser deletado com sucesso, ou {@code false}, caso contrário.
     */
    public boolean deletarAnimal(Animal animal){
        if (animal == null) return false;
        if (animal.getTutoresIDs() != null) {
            for (String tutorID : animal.getTutoresIDs()) {
                Tutor tutor = tutores.get(tutorID);
                if (tutor.getAnimais() != null) {
                    tutor.getAnimaisIDs().remove(animal.getID());
                }
            }
        }
        animal.getTutoresIDs().clear();
        String setorID = animal.getSetorID();
        if (setorID != null){
            Setor setor = setores.get(setorID);
            setor.getAnimais().remove(animal);
        }
        animal.setSetor(null);
        animais.remove(animal.getID());
        salvarDadosAnimal();
        return true;
    }

    /** Busca um animal pelo seu ID
     *
     * @param ID O ID do animal a ser procurado.
     * @return O objeto {@link Animal} encontrado, ou {@code null} se não for encontrado.
     */
    public static Animal buscarAnimalPorID(String ID){ return animais.get(ID.trim()); }

    /** Busca um animal pelo seu nome. Caso haja mais de um animal com o mesmo
     * nome, todos estes animais são retornados numa lista.
     *
     * @param nome O nome a ser procurado
     * @return Uma lista de objetos do tipo {@link Animal}
     * Retorna uma lista vazia se nenhum animal for encontrado ou se o nome for inválido.
     */
    public List<Animal> buscarAnimalPorNome(String nome){
        if (!nome.isBlank()) {
            List<Animal> animaisNome = new ArrayList<>();
            for (Animal animal : animais.values()) {
                if (animal.getNome().equalsIgnoreCase(nome.trim())) {
                    animaisNome.add(animal);
                }
            }
            return animaisNome;
        }
        return new ArrayList<>();
    }

    /** Lista todos os animais cadastrados no mapa de animais.
     *
     * @return Uma lista contendo os nomes todos os animais.
     */
    public List<Animal> listarAnimais(){
        if (animais.isEmpty()) return new ArrayList<>();
        return new ArrayList<Animal>(animais.values());
    }

    /** Lista todos os tutores do animal.
     *
     * @param animal O objeto {@link Animal} a ter a lista de tutores procurada.
     * @return Uma lista com os nomes de todos os tutores do animal.
     */
    public List<String> listarTutores(Animal animal){
        if (animal == null || animal.getTutoresIDs().isEmpty()) return new ArrayList<>();
        List<String> IDTutores = new ArrayList<>();
        for (String tutorID : animal.getTutoresIDs()){
            IDTutores.add(tutorID);
        }
        return IDTutores;
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
        if (animal != null && validarIDAnimal(novoID)) {
            animais.remove(animal.getID()); // Remove o animal com ID antigo
            animal.setID(novoID); // Insere o novo ID no animal
            animais.put(animal.getID(), animal); // Insere o animal com o novo ID no Map
            salvarDadosAnimal();
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
        if (animal != null && !animal.getNome().equalsIgnoreCase(novoNome.trim()) && !novoNome.isBlank()){
            animal.setNome(novoNome);
            salvarDadosAnimal();
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
        if (animal != null && !animal.getEspecie().equalsIgnoreCase(novaEspecie.trim()) && !novaEspecie.isBlank()){
            animal.setEspecie(novaEspecie);
            salvarDadosAnimal();
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
        if (animal != null && !animal.getRaca().equalsIgnoreCase(novaRaca.trim()) && !novaRaca.isBlank()){
            animal.setRaca(novaRaca);
            salvarDadosAnimal();
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
        if (animal != null && !animal.getData().equals(novaData) && validarData(novaData)){
            animal.setData(novaData);
            salvarDadosAnimal();
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
        if (animal != null && !animal.getSexo().equalsIgnoreCase(novoSexo.trim()) && !novoSexo.isBlank()){
            animal.setSexo(novoSexo);
            salvarDadosAnimal();
            return true;
        }
        return false;
    }

    /** Atualiza a situação do animal.
     *
     * @param animal O objeto {@link Animal} a ter a situação atualizada.
     * @param novaSituacao A a situação a ser atribuída.
     * @return {@code true} caso a situação seja atualizada com sucesso, ou {@code false}, caso contrário.
     */
    public boolean atualizarSituacao(Animal animal, String novaSituacao){
        if (animal != null && !animal.getSituacao().equalsIgnoreCase(novaSituacao.trim()) && !novaSituacao.isBlank()){
            animal.setSituacao(novaSituacao);
            salvarDadosAnimal();
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
        if (animal == null || novoSetor == null) return false;
        // Caso o animal já esteja nesse setor
        if (animal.getSetor() != null && animal.getSetor().getID().equalsIgnoreCase(novoSetor.getID())) return false;
        if (animal.getSetorID().equalsIgnoreCase(novoSetor.getID().trim())) return false;
        animal.setSetor(novoSetor);
        salvarDadosAnimal();
        return true;
    }
}