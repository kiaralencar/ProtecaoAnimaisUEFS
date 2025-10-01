package controller;

/**
 * A classe GeralController atua como um ponto de acesso central para
 * todos os controladores do sistema. Ela fornece instâncias estáticas
 * de cada controlador, permitindo que sejam acessados de qualquer
 * lugar da aplicação sem a necessidade de criação de novos objetos.
 * Isso simplifica a arquitetura e o gerenciamento do estado dos dados.
 *
 * @author Kiara Alencar
 * @version 2.0
 * @see AnimalController
 * @see SetorController
 * @see TutorController
 * @see EnderecoController
 */
public class GeralController {
    /** Instância estática do controlador dos animais. */
    public static AnimalController A = new AnimalController();

    /** Instância estática do controlador dos setores. */
    public static SetorController S = new SetorController();

    /** Instância estática do controlador dos tutores. */
    public static TutorController T = new TutorController();

    /** Instância estática do controlador do endereço. */
    public static EnderecoController E = new EnderecoController();
}