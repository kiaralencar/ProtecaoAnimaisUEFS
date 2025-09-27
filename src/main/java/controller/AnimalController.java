package controller;

import model.Animal;
import model.Setor;
import model.Tutora;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class AnimalController {
    private HashMap<String, Animal> animais;

    public AnimalController(){ this.animais = new HashMap<>(); }

    public boolean validarID(String ID){ return ID.matches("[Aa][0-9]") && !animais.containsKey(ID); }

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

    public boolean cadastrarAnimal(Animal animal){
        if (animais.containsKey(animal.getID())) return false;
        animais.put(animal.getID(), animal);
        return true;
    }

    public boolean deletarAnimal(String ID){
        Animal animal = animais.get(ID);
        if (animal == null) return false;
        if (animal.getTutores() != null) {
            for (Tutora tutor : animal.getTutores()) {
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

    public boolean adicionarTutora (String ID, Tutora tutora){
        Animal animal = animais.get(ID);
        if (animal != null && tutora != null && !animal.getTutores().contains(tutora)){
            animal.getTutores().add(tutora);
            return true;
        }
        return false;
    }

    public boolean removerTutora (String ID, Tutora tutora){
        Animal animal = animais.get(ID);
        if (animal != null && tutora != null && animal.getTutores().contains(tutora)
                && tutora.getAnimais().contains(animal)){
            animal.getTutores().remove(tutora); // Removo a pessoa da lista de tutores do animal
            tutora.getAnimais().remove(animal); // Remove o animal da lista de animais da pessoa tutora
            return true;
        }
        return false;
    }

    public Animal buscarPorID(String ID){ return animais.get(ID); }

    public Setor buscarSetor(String ID){
        Animal animal = animais.get(ID);
        if (animal != null) return animal.getSetor();
        return null;
    }

    public List<Animal> listarAnimais(){ return new ArrayList<>(animais.values()); }

    public List<String> listarTutoras (String ID){
        Animal animal = animais.get(ID);
        if (animal == null || animal.getTutores().isEmpty()){ return new ArrayList<>(); }
        List<String> nomesTutoras = new ArrayList<>();
        for (Tutora tutora : animal.getTutores()){
            nomesTutoras.add(tutora.getNome());
        }
        return nomesTutoras;
    }

    public boolean atualizarID (String ID, String novoID){
        Animal animal = animais.get(ID);
        /* A verificação da existência do ID é feita em "validarID", que é chamada
        na View a cada vez que é inserido um novo ID */
        if (animal != null) {
            animais.remove(ID); // Remove o animal com ID antigo
            animal.setID(novoID); // Insere o novo ID no animal
            animais.put(animal.getID(), animal); // Insere o animal com o novo ID no Map
            return true;
        }
        return false;
    }

    public boolean atualizarNome(String ID, String novoNome){
        Animal animal = animais.get(ID);
        if (animal != null && !animal.getNome().equalsIgnoreCase(novoNome)){
            animal.setNome(novoNome);
            return true;
        }
        return false;
    }

    public boolean atualizarEspecie(String ID, String novaEspecie){
        Animal animal = animais.get(ID);
        if (animal != null && !animal.getEspecie().equalsIgnoreCase(novaEspecie)){
            animal.setEspecie(novaEspecie);
            return true;
        }
        return false;
    }

    public boolean atualizarRaca(String ID, String novaRaca){
        Animal animal = animais.get(ID);
        if (animal != null && !animal.getRaca().equalsIgnoreCase(novaRaca)){
            animal.setRaca(novaRaca);
            return true;
        }
        return false;
    }

    public boolean atualizarData(String ID, YearMonth novaData){
        Animal animal = animais.get(ID);
        if (animal != null && !animal.getData().equals(novaData)){
            animal.setData(novaData);
            return true;
        }
        return false;
    }

    public boolean atualizarSexo(String ID, String novoSexo){
        Animal animal = animais.get(ID);
        if (animal != null && !animal.getSexo().equalsIgnoreCase(novoSexo)){
            animal.setSexo(novoSexo);
            return true;
        }
        return false;
    }

    public boolean atualizarSetor(String ID, Setor novoSetor){
        Animal animal = animais.get(ID);
        if (animal != null && novoSetor != null &&
                !animal.getSetor().getNome().equalsIgnoreCase(novoSetor.getNome())){
            animal.setSetor(novoSetor);
            return true;
        }
        return false;
    }



    /* métodos a implementar:
    - construtor *FEITO*
    - validar ID (ID >= 0 e inexistente) *FEITO*
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
