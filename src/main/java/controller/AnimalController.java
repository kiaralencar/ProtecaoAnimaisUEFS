package controller;

import model.Animal;
import model.Setor;
import model.Tutora;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class AnimalController {
    private HashMap<Integer, Animal> animais;

    public AnimalController(){ this.animais = new HashMap<>(); }

    public boolean validarID(int ID){ return ID >= 0 && !animais.containsKey(ID); }

    /** Calcula a idade aproximada do animal.
     * Este código foi retirado do Canal do YouTube "Lucas Dicas Java", no
     * vídeo intitulado "Como fazer o cálculo de idade com Java", publicado em 2024.
     * <p>
     * @param dataNascimento A data de nascimento do animal.
     * @return A idade aproximada do animal
     */
    public int calcularIdade(LocalDate dataNascimento){
        LocalDate dataHoje = LocalDate.now();
        int idade = dataHoje.getYear() - dataNascimento.getYear();
        if (dataHoje.getMonthValue() < dataNascimento.getMonthValue()){
            idade--;
        } else if (dataHoje.getMonthValue() == dataNascimento.getMonthValue()
                && dataHoje.getDayOfMonth() < dataNascimento.getDayOfMonth()){
            idade--;
        }
        return idade;
    }

    public boolean cadastrarAnimal(Animal animal){
        if (animais.containsKey(animal.getID())) return false;
        animais.put(animal.getID(), animal);
        return true;
    }

    public boolean deletarAnimal(int ID){
        if (!animais.containsKey(ID)) return false;
        animais.remove(ID);
        return true;
    }

    public boolean adicionarTutora (int ID, Tutora tutora){
        Animal animal = animais.get(ID);
        if (animal != null && tutora != null && !animal.getTutores().contains(tutora)){
            animal.getTutores().add(tutora);
            return true;
        }
        return false;
    }

    public boolean removerTutora (int ID, Tutora tutora){
        Animal animal = animais.get(ID);
        if (animal != null && tutora != null && animal.getTutores().contains(tutora)){
            animal.getTutores().remove(tutora);
            return true;
        }
        return false;
    }

    public Animal buscarPorID(int ID){ return animais.get(ID); }

    public Setor buscarSetor(int ID){
        Animal animal = animais.get(ID);
        if (animal != null) return animal.getSetor();
        return null;
    }

    public List<Animal> listarAnimais(){ return new ArrayList<>(animais.values()); }

    public List<String> listarTutoras (int ID){
        Animal animal = animais.get(ID);
        if (animal == null || animal.getTutores().isEmpty()){ return new ArrayList<>(); }
        List<String> nomesTutoras = new ArrayList<>();
        for (Tutora tutora : animal.getTutores()){
            nomesTutoras.add(tutora.getNome());
        }
        return nomesTutoras;
    }

    public boolean atualizarID (int ID, int novoID){
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

    public boolean atualizarNome(int ID, String novoNome){
        Animal animal = animais.get(ID);
        if (animal != null && !animal.getNome().equalsIgnoreCase(novoNome)){
            animal.setNome(novoNome);
            return true;
        }
        return false;
    }

    public boolean atualizarEspecie(int ID, String novaEspecie){
        Animal animal = animais.get(ID);
        if (animal != null && !animal.getEspecie().equalsIgnoreCase(novaEspecie)){
            animal.setEspecie(novaEspecie);
            return true;
        }
        return false;
    }

    public boolean atualizarRaca(int ID, String novaRaca){
        Animal animal = animais.get(ID);
        if (animal != null && !animal.getRaca().equalsIgnoreCase(novaRaca)){
            animal.setRaca(novaRaca);
            return true;
        }
        return false;
    }

    public boolean atualizarData(int ID, LocalDate novaData){
        Animal animal = animais.get(ID);
        if (animal != null && !animal.getData().equals(novaData)){
            animal.setData(novaData);
            return true;
        }
        return false;
    }

    public boolean atualizarSexo(int ID, String novoSexo){
        Animal animal = animais.get(ID);
        if (animal != null && !animal.getSexo().equalsIgnoreCase(novoSexo)){
            animal.setSexo(novoSexo);
            return true;
        }
        return false;
    }

    public boolean atualizarSetor(int ID, Setor novoSetor){
        Animal animal = animais.get(ID);
        if (animal != null && novoSetor != null &&
                !animal.getSetor().getNome().equalsIgnoreCase(novoSetor.getNome())){
            animal.setSetor(novoSetor);
            return true;
        }
        return false;
    }



    /* métodos a implementar:
    - validar animal (ID >= 0 e inexistente) *FEITO*
    - calcular idade *FEITO*
    - cadastrar animal *FEITO*
    - deletar animal *FEITO*
    - adicionar pessoa tutora *FEITO*
    - remover pessoa tutora *FEITO*
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
