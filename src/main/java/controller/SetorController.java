package controller;

import model.Animal;
import model.Setor;
import model.Tutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SetorController {
    private HashMap<String, Setor> setores;

    public SetorController(){ this.setores = new HashMap<>(); }

    public boolean validarIDSetor(String ID){ return ID.matches("S[0-9]+") && !setores.containsKey(ID); }

    public boolean cadastrarSetor(Setor setor){
        if (setor == null || setores.containsKey(setor.getID())) return false;
        String novoNome = setor.getNome();
        for (Setor s : setores.values()){
            if (s.getNome().equalsIgnoreCase(novoNome)) return false;
        }
        setores.put(setor.getID(), setor);
        return true;
    }

    public boolean deletarSetor(Setor setor){
        if (setor != null) {
            if (!setor.getTutores().isEmpty() || !setor.getAnimais().isEmpty()) return false;
            setores.remove(setor.getID());
            return true;
        }
        return false;
    }

    public boolean setorDisponivel(Setor setor){ return setor != null && !setor.getTutores().isEmpty(); }

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
                return true;
            }
        }
        /* Se NÃO tiver outra pessoa no setor antigo, a pessoa tutor
        não pode ser movida, pois os animais ficarão sem tutor */
        return false;
    }

    public boolean removerTutor(Setor setor, Tutor tutor){
        if (setor != null && tutor != null && setor.getTutores().contains(tutor) && tutor.getSetor() == setor ){
            // Se há outros tutores no setor, o tutor passado como parâmetro pode ser removido
            if (setor.getTutores().size() > 1){
                for (Animal animal : setor.getAnimais()){
                    tutor.getAnimais().remove(animal);
                    animal.getTutores().remove(tutor);
                }
                setor.getTutores().remove(tutor);
                tutor.setSetor(null);
                return true;
            }
        }
        /* Se não há outros tutores no setor, a pessoa não pode
        ser removida, pois os animais ficariam sem tutor */
        return false;
    }

    public boolean adicionarAnimal(Setor setor, Animal animal){
        if (setor != null && animal != null && !setor.getAnimais().contains(animal)){
            // Se for um animal recém cadastrado, sem setor estabelecido
            if (animal.getSetor() == null){
                for (Tutor tutor : setor.getTutores()){
                    animal.getTutores().add(tutor);
                    tutor.getAnimais().add(animal);
                }
                animal.setSetor(setor);
                setor.getAnimais().add(animal);
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
            return true;
        }
        return false;
    }

    public boolean removerAnimal(Setor setor, Animal animal){
        if (setor != null && animal != null && setor.getAnimais().contains(animal)
                && animal.getSetor() == setor){
            for (Tutor tutor : setor.getTutores()){
                tutor.getAnimais().remove(animal);
                animal.getTutores().remove(tutor);
            }
            setor.getAnimais().remove(animal);
            animal.setSetor(null);
            return true;
        }
        return false;
    }

    public Setor buscarSetorPorID(String ID){ return setores.get(ID); }

    public List<String> listarSetores (){
        if (setores.isEmpty()) return new ArrayList<>();
        List<String> nomesSetores = new ArrayList<>();
        for (Setor setor : setores.values()){
            nomesSetores.add(setor.getNome());
        }
        return nomesSetores;
    }

    public List<String> listarTutores(Setor setor){
        if (setor == null || setor.getTutores().isEmpty()) return new ArrayList<>();
        List<String> nomeTutores = new ArrayList<>();
        for (Tutor tutor : setor.getTutores()){
            nomeTutores.add(tutor.getNome());
        }
        return nomeTutores;
    }

    public List<String> listarAnimais(Setor setor){
        if (setor == null || setor.getAnimais().isEmpty()) return new ArrayList<>();
        List<String> nomeAnimais = new ArrayList<>();
        for (Animal animal : setor.getAnimais()){
            nomeAnimais.add(animal.getNome());
        }
        return nomeAnimais;
    }

    public boolean atualizarID (Setor setor, String novoID){
        /* A verificação da existência do ID é feita em "validarID", que é chamada
        na View a cada vez que é inserido um novo ID */
        if (setor != null) {
            setores.remove(setor.getID()); // Remove o setor com ID antigo
            setor.setID(novoID); // Insere o novo ID no setor
            setores.put(setor.getID(), setor); // Insere o setor com o novo ID no Map
            return true;
        }
        return false;
    }

    public boolean atualizarNome(Setor setor, String novoNome){
        boolean setorExistente = false;
        for (Setor s : setores.values()){
            if (s.getNome().equalsIgnoreCase(novoNome)) {
                setorExistente = true;
                break;
            }
        }
        if (setor != null && !setor.getNome().equalsIgnoreCase(novoNome) && !setorExistente){
            setor.setNome(novoNome);
            return true;
        }
        return false;
    }

     /* métodos a implementar:
    - validar ID *FEITO*
    - construtor *FEITO*
    - cadastrar setor *FEITO*
    - deletar setor (apenas se nao houver animais nem pessoas tutoras) *FEITO*
    - validar setor (se ele esta disponivel) *FEITO*
    - adicionar pessoa tutora (tem que colocar os animais nela) *FEITO*
    - remover pessoa tutora (tem que tirar os animais dela) *FEITO*
    - adicionar animal (tem que colocar pessoas tutoras nele) *FEITO*
    - remover animal (tem que tirar pessoas tutoras dele) *FEITO*
    - buscar setor por ID *FEITO*
    - listar setores *FEITO*
    - listar pessoas tutoras do setor *FEITO*
    - listar animais do setor *FEITO*
    - atualizar ID do setor *FEITO*
    - atualizar nome do setor *FEITO*
    */
}
