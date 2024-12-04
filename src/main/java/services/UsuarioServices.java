package services;

import model.Usuario;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioServices {
    private static final Logger logger = Logger.getLogger(UsuarioServices.class.getName());

    /**
     * Adiciona um novo usuário ao banco de dados.
     *
     * @param usuario Objeto Usuario a ser adicionado.
     */
    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, documento, tipo_Documento, email, senha, permissao, dt_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getDocumento());
            preparedStatement.setString(3, usuario.getTipoDocumento().toString());
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setString(5, usuario.getSenha());
            preparedStatement.setString(6, usuario.getPermissao().toString());
            preparedStatement.setDate(7, Date.valueOf(usuario.getDataNascimento()));

            preparedStatement.executeUpdate();
            logger.info("Usuário adicionado com sucesso: " + usuario.getNome());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao adicionar usuário: " + usuario.getNome(), e);
            throw new RuntimeException("Erro ao adicionar usuário.", e);
        }
    }

    public void removerUsuario(Usuario usuario) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM usuarios WHERE documento = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getDocumento());
            preparedStatement.executeUpdate();
            System.out.println("Usuário removido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao remover usuário: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void atualizarNomeUsuario(Usuario usuario) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE usuarios SET nome = ? WHERE documento = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getDocumento());
            preparedStatement.executeUpdate();
            System.out.println("Usuário atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void atualizarEmailUsuario(Usuario usuario) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE usuarios SET email = ? WHERE documento = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getEmail());
            preparedStatement.setString(2, usuario.getDocumento());
            preparedStatement.executeUpdate();
            System.out.println("Usuário atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void atualizarSenhaUsuario(Usuario usuario) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE usuarios SET senha = ? WHERE documento = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getSenha());
            preparedStatement.setString(2, usuario.getDocumento());
            preparedStatement.executeUpdate();
            System.out.println("Usuário atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void atualizarPermissaoUsuario(Usuario usuario) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE usuarios SET permissao = ? WHERE documento = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getPermissao().toString());
            preparedStatement.setString(2, usuario.getDocumento());
            preparedStatement.executeUpdate();
            System.out.println("Usuário atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void atualizarDataNascimentoUsuario(Usuario usuario) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE usuarios SET dt_nascimento = ? WHERE documento = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(usuario.getDataNascimento()));
            preparedStatement.setString(2, usuario.getDocumento());
            preparedStatement.executeUpdate();
            System.out.println("Usuário atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void consultarUsuario(Usuario usuario) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM usuarios WHERE documento = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getDocumento());
            preparedStatement.executeQuery();
            System.out.println("Usuário consultado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao consultar usuário: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
