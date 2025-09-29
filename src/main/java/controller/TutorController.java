package controller;

import model.Animal;
import model.Endereco;
import model.Setor;
import model.Tutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TutorController {
    private HashMap<String, Tutor> tutores;

    public TutorController(){ this.tutores = new HashMap<>(); }

    public boolean validarIDSetor(String ID){ return ID.matches("T[0-9]+") && !tutores.containsKey(ID); }

    public boolean cadastrarTutor(Tutor tutor){
        if (tutor == null || tutores.containsKey(tutor.getID())) return false;
        tutores.put(tutor.getID(), tutor);
        return true;
    }

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

    public Tutor buscarTutorPorID(String ID) { return tutores.get(ID); }

    public Setor buscarSetor(Tutor tutor){
        if (tutor != null) return tutor.getSetor();
        return null;
    }

    public List<String> listarTutores (){
        if (tutores.isEmpty()) return new ArrayList<>();
        List<String> nomesTutores = new ArrayList<>();
        for (Tutor tutor : tutores.values()){
            nomesTutores.add(tutor.getNome());
        }
        return nomesTutores;
    }

    public List<String> listarAnimais (Tutor tutor){
        if (tutor.getAnimais().isEmpty()) return new ArrayList<>();
        List<String> nomesAnimais = new ArrayList<>();
        for (Animal animal : tutor.getAnimais()){
            nomesAnimais.add(animal.getNome());
        }
        return nomesAnimais;
    }

    public boolean atualizarID (Tutor tutor, String novoID){
        /* A verificação da existência do ID é feita em "validarID", que é chamada
        na View a cada vez que é inserido um novo ID */
        if (tutor != null) {
            tutores.remove(tutor.getID()); // Remove o tutor com ID antigo
            tutor.setID(novoID); // Insere o novo ID no tutor
            tutores.put(tutor.getID(), tutor); // Insere o tutor com o novo ID no Map
            return true;
        }
        return false;
    }

    public boolean atualizarNome(Tutor tutor, String novoNome){
        if (tutor != null && !tutor.getNome().equalsIgnoreCase(novoNome)){
            tutor.setNome(novoNome);
            return true;
        }
        return false;
    }

    public boolean atualizarEndereco(Tutor tutor, Endereco novoEndereco){
        if (tutor != null && novoEndereco != null){
            tutor.setEndereco(novoEndereco);
            return true;
        }
        return false;
    }

    public boolean atualizarTelefone(Tutor tutor, String novoTelefone){
        if (tutor != null && !novoTelefone.equalsIgnoreCase(tutor.getTelefone())){
            tutor.setTelefone(novoTelefone);
            return true;
        }
        return false;
    }

    public boolean atualizarEmail(Tutor tutor, String novoEmail){
        if (tutor != null && !novoEmail.equalsIgnoreCase(tutor.getEmail())){
            tutor.setTelefone(novoEmail);
            return true;
        }
        return false;
    }

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