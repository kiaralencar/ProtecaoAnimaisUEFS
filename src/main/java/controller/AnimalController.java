package controller;

import model.Animal;
import model.Setor;
import model.Tutor;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class AnimalController {
    private HashMap<String, Animal> animais;

    public AnimalController(){ this.animais = new HashMap<>(); }

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

    public boolean cadastrarAnimal(Animal animal){
        if (animal == null || animais.containsKey(animal.getID())) return false;
        animais.put(animal.getID(), animal);
        return true;
    }

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

    public boolean adicionarTutor (Animal animal, Tutor tutor){
        if (animal != null && tutor != null && !animal.getTutores().contains(tutor)){
            animal.getTutores().add(tutor);
            return true;
        }
        return false;
    }

    public boolean removerTutor (Animal animal, Tutor tutor){
        if (animal != null && tutor != null && animal.getTutores().contains(tutor)
                && tutor.getAnimais().contains(animal)){
            animal.getTutores().remove(tutor); // Removo a pessoa da lista de tutores do animal
            tutor.getAnimais().remove(animal); // Remove o animal da lista de animais da pessoa tutor
            return true;
        }
        return false;
    }

    public Animal buscarPorID(String ID){ return animais.get(ID); }

    public Setor buscarSetor(Animal animal){
        if (animal != null) return animal.getSetor();
        return null;
    }

    public List<String> listarAnimais (){
        if (animais.isEmpty()) return new ArrayList<>();
        List<String> nomesAnimais = new ArrayList<>();
        for (Animal animal : animais.values()){
            nomesAnimais.add(animal.getNome());
        }
        return nomesAnimais;
    }

    public List<String> listarTutores (Animal animal){
        if (animal == null || animal.getTutores().isEmpty()) return new ArrayList<>();
        List<String> nomesTutores = new ArrayList<>();
        for (Tutor tutor : animal.getTutores()){
            nomesTutores.add(tutor.getNome());
        }
        return nomesTutores;
    }

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

    public boolean atualizarNome(Animal animal, String novoNome){
        if (animal != null && !animal.getNome().equalsIgnoreCase(novoNome)){
            animal.setNome(novoNome);
            return true;
        }
        return false;
    }

    public boolean atualizarEspecie(Animal animal, String novaEspecie){
        if (animal != null && !animal.getEspecie().equalsIgnoreCase(novaEspecie)){
            animal.setEspecie(novaEspecie);
            return true;
        }
        return false;
    }

    public boolean atualizarRaca(Animal animal, String novaRaca){
        if (animal != null && !animal.getRaca().equalsIgnoreCase(novaRaca)){
            animal.setRaca(novaRaca);
            return true;
        }
        return false;
    }

    public boolean atualizarData(Animal animal, YearMonth novaData){
        if (animal != null && !animal.getData().equals(novaData)){
            animal.setData(novaData);
            return true;
        }
        return false;
    }

    public boolean atualizarSexo(Animal animal, String novoSexo){
        if (animal != null && !animal.getSexo().equalsIgnoreCase(novoSexo)){
            animal.setSexo(novoSexo);
            return true;
        }
        return false;
    }

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
