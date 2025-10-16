package dao;
import com.fasterxml.jackson.databind.ObjectWriter;
import model.Animal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Classe responsável por gerenciar a persistência de dados dos objetos {@link Animal}
 * em um arquivo JSON. Atua como um Data Access Object (DAO), isolando a lógica de I/O
 * (Entrada/Saída) do sistema de controle.
 *
 * <p>Utiliza a biblioteca Jackson para serialização e desserialização dos dados.</p>
 *
 * @author Kiara Alencar
 * @version 1.2
 * @see Animal
 * @see ObjectMapper
 */
public class AnimalDAO {
    /** Nome do arquivo JSON em que os dados dos animais serão salvos. */
    private final String NOME_ARQUIVO = "animais.json";

    /** A principal classe da biblioteca Jackson, responsável por mapear objetos Java
     * para JSON e vice-versa. */
    private final ObjectMapper mapper;

    /** Representação abstrata do arquivo de dados. */
    private final File arquivo;

    /** Construtor da classe AnimalDAO.
     * <p>
     * Inicializa o {@code ObjectMapper} e registra o {@code JavaTimeModule}
     * para garantir a correta manipulação do tipo {@code YearMonth}.
     * </p>
     */
    public AnimalDAO(){
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.arquivo = new File(NOME_ARQUIVO);
    }

    /** Serializa e salva todo o mapa de animais no arquivo JSON, sobrescrevendo
     * qualquer conteúdo existente.
     *
     * @param novosAnimais O {@code Map} completo contendo todos os objetos {@link Animal} a serem salvos.
     */
    public void salvarAnimal(Map<String, Animal> novosAnimais){
        HashMap<String, Animal> existentes = carregarAnimais(); // Carrega o que já está no arquivo
        existentes.putAll(novosAnimais); // Adiciona os novos, mantendo os antigos
        try {
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            writer.writeValue(arquivo, existentes);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o animal no arquivo: " + e.getMessage());
        }
    }

    /** Carrega os dados dos animais do arquivo JSON e os desserializa para um {@code HashMap}.
     *
     * @return Um {@code HashMap} contendo todos os objetos {@link Animal} persistidos,
     * em que a chave é o ID do animal ({@code String}) e o valor é o objeto {@code Animal}.
     */
    public HashMap<String, Animal> carregarAnimais(){
        if (!arquivo.exists() || arquivo.length() == 0) return new HashMap<>();
        try {
            // Garante a correta desserialização do tipo genérico HashMap<String, Animal>
            Map<String, Animal> animais = mapper.readValue(arquivo,
                    mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Animal.class));
            return new HashMap<>(animais);
        } catch (IOException e) {
            System.err.println("Erro ao carregar animais: " + e.getMessage());
            return new HashMap<>();
        }
    }
}