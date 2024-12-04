package services;

import model.Cliente;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteServices {
    private static final Logger logger = Logger.getLogger(ClienteServices.class.getName());

    /**
     * Adiciona um novo cliente ao banco de dados.
     *
     * @param cliente Objeto Cliente a ser adicionado.
     */
    public void adicionarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, documento, tipo_documento, email, telefone, dt_nascimento) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getDocumento());
            preparedStatement.setString(3, cliente.getTipoDocumento().toString());
            preparedStatement.setString(4, cliente.getEmail());
            preparedStatement.setString(5, cliente.getTelefone());
            preparedStatement.setDate(6, Date.valueOf(cliente.getDataNascimento()));

            preparedStatement.executeUpdate();
            logger.info("Cliente adicionado com sucesso: " + cliente.getNome());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao adicionar cliente: " + cliente.getNome(), e);
            throw new RuntimeException("Erro ao adicionar cliente.", e);
        }
    }

    /**
     * Remove um cliente do banco de dados.
     *
     * @param documento Documento do cliente a ser removido.
     */
    public void removerCliente(String documento) {
        String sql = "DELETE FROM clientes WHERE documento = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, documento);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Cliente removido com sucesso: " + documento);
            } else {
                logger.warning("Nenhum cliente encontrado com o documento: " + documento);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao remover cliente com documento: " + documento, e);
            throw new RuntimeException("Erro ao remover cliente.", e);
        }
    }

    /**
     * Atualiza um campo específico de um cliente no banco de dados.
     *
     * @param documento Documento do cliente a ser atualizado.
     * @param campo     Campo a ser atualizado.
     * @param valor     Novo valor do campo.
     */
    public void atualiazarCliente(String documento, String campo, Object valor) {
        String sql = "UPDATE clientes SET " + campo + " = ? WHERE documento = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setObject(1, valor);
            preparedStatement.setString(2, campo);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Cliente atualizado com sucesso: " + documento + ", campo: " + campo);
            } else {
                logger.warning("Nenhum cliente encontrado com o documento: " + documento);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao atualizar cliente. Documento: " + documento + ", Campo: " + campo, e);
            throw new RuntimeException("Erro ao atualizar cliente.", e);
        }
    }

    //Métodos específicos que utilizam o método acima

    public void atualizarNomeCliente(String documento, String nome) {
        atualiazarCliente(documento, "nome", nome);
    }

    public void atualizarEmailCliente(String nome, String email) {
        atualiazarCliente(nome, "email", email);
    }

    public void atualizarTelefone(String nome, String telefone) {
        atualiazarCliente(nome, "telefone", telefone);
    }

    public void atualizarDataNascimento(String nome, Date dataNascimento) {
        atualiazarCliente(nome, "dt_nascimento", dataNascimento);
    }
}
