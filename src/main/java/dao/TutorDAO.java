package dao;
import com.fasterxml.jackson.databind.ObjectWriter;
import model.Tutor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Classe responsável por gerenciar a persistência de dados dos objetos {@link Tutor}
 * em um arquivo JSON. Atua como um Data Access Object (DAO), isolando a lógica de I/O
 * (Entrada/Saída) do sistema de controle.
 *
 * <p>Utiliza a biblioteca Jackson para serialização e desserialização dos dados.</p>
 *
 * @author Kiara Alencar
 * @version 1.0
 * @see Tutor
 * @see ObjectMapper
 */
public class TutorDAO {
    /** Nome do arquivo JSON em que os dados dos tutores serão salvos. */
    private final String NOME_ARQUIVO = "tutores.json";

    /** A principal classe da biblioteca Jackson, responsável por mapear objetos Java
     * para JSON e vice-versa. */
    private final ObjectMapper mapper;

    /** Representação abstrata do arquivo de dados. */
    private final File arquivo;

    /** Construtor da classe TutorDAO.
     * <p>
     * Inicializa o {@code ObjectMapper}.
     * </p>
     */
    public TutorDAO(){
        this.mapper = new ObjectMapper();
        this.arquivo = new File(NOME_ARQUIVO);
    }

    /** Serializa e salva todo o mapa de tutores no arquivo JSON, sobrescrevendo
     * qualquer conteúdo existente.
     *
     * @param tutores O {@code Map} completo contendo todos os objetos {@link Tutor} a serem salvos.
     */
    public void salvarTutor(Map<String, Tutor> tutores){
        try {
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            writer.writeValue(arquivo, tutores);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o tutor no arquivo: " + e.getMessage());
        }
    }

    /** Carrega os dados dos tutores do arquivo JSON e os desserializa para um {@code HashMap}.
     *
     * @return Um {@code HashMap} contendo todos os objetos {@link Tutor} persistidos,
     * em que a chave é o ID do tutor ({@code String}) e o valor é o objeto {@code Tutor}.
     */
    public HashMap<String, Tutor> carregarTutores(){
        if (!arquivo.exists() || arquivo.length() == 0) return new HashMap<>();
        try {
            // Garante a correta desserialização do tipo genérico HashMap<String, Tutor>
            Map<String, Tutor> tutores = mapper.readValue(arquivo,
                    mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Tutor.class));
            return new HashMap<>(tutores);
        } catch (IOException e) {
            System.err.println("Erro ao carregar tutores: " + e.getMessage());
            return new HashMap<>();
        }
    }
}