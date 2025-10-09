package dao;
import com.fasterxml.jackson.databind.ObjectWriter;
import model.Setor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Classe responsável por gerenciar a persistência de dados dos objetos {@link Setor}
 * em um arquivo JSON. Atua como um Data Access Object (DAO), isolando a lógica de I/O
 * (Entrada/Saída) do sistema de controle.
 *
 * <p>Utiliza a biblioteca Jackson para serialização e desserialização dos dados.</p>
 *
 * @author Kiara Alencar
 * @version 1.0
 * @see Setor
 * @see ObjectMapper
 */
public class SetorDAO {
    /** Nome do arquivo JSON em que os dados dos setores serão salvos. */
    private final String NOME_ARQUIVO = "setores.json";

    /** A principal classe da biblioteca Jackson, responsável por mapear objetos Java
     * para JSON e vice-versa. */
    private final ObjectMapper mapper;

    /** Representação abstrata do arquivo de dados. */
    private final File arquivo;

    /** Construtor da classe SetorDAO.
     * <p>
     * Inicializa o {@code ObjectMapper}.
     * </p>
     */
    public SetorDAO(){
        this.mapper = new ObjectMapper();
        this.arquivo = new File(NOME_ARQUIVO);
    }

    /** Serializa e salva todo o mapa de setores no arquivo JSON, sobrescrevendo
     * qualquer conteúdo existente.
     *
     * @param setores O {@code Map} completo contendo todos os objetos {@link Setor} a serem salvos.
     */
    public void salvarSetor(Map<String, Setor> setores){
        try {
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            writer.writeValue(arquivo, setores);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o setor no arquivo: " + e.getMessage());
        }
    }

    /** Carrega os dados dos setores do arquivo JSON e os desserializa para um {@code HashMap}.
     *
     * @return Um {@code HashMap} contendo todos os objetos {@link Setor} persistidos,
     * em que a chave é o ID do setor ({@code String}) e o valor é o objeto {@code Setor}.
     */
    public HashMap<String, Setor> carregarSetores(){
        if (!arquivo.exists() || arquivo.length() == 0) return new HashMap<>();
        try {
            // Garante a correta desserialização do tipo genérico HashMap<String, Setor>
            Map<String, Setor> setores = mapper.readValue(arquivo,
                    mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Setor.class));
            return new HashMap<>(setores);
        } catch (IOException e) {
            System.err.println("Erro ao carregar setores: " + e.getMessage());
            return new HashMap<>();
        }
    }
}