package services;

import model.Produto;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoServices {
    public void adicionarProduto(Produto produto) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO produtos (descricao, codigo, preco, qtde_estoque, categoria) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, produto.getDescricao());
            preparedStatement.setString(2, produto.getCodigo());
            preparedStatement.setDouble(3, produto.getPreco());
            preparedStatement.setInt(4, produto.getQuantidadeEstoque());
            preparedStatement.setString(5, produto.getCategoria());
            preparedStatement.executeUpdate();
            System.out.println("Produto adicionado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar produto: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void removerProduto(Produto produto) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM produtos WHERE codigo = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, produto.getCodigo());
            preparedStatement.executeUpdate();
            System.out.println("Produto removido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao remover produto: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void atualizarDescricaoProduto(Produto produto) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE produtos SET descricao = ? WHERE codigo = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, produto.getDescricao());
            preparedStatement.setString(2, produto.getCodigo());
            preparedStatement.executeUpdate();
            System.out.println("Produto atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void atualizarPrecoProduto(Produto produto) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE produtos SET preco = ? WHERE codigo = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, produto.getPreco());
            preparedStatement.setString(2, produto.getCodigo());
            preparedStatement.executeUpdate();
            System.out.println("Produto atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void atualizarQuantidadeEstoqueProduto(Produto produto) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE produtos SET qtde_estoque = ? WHERE codigo = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, produto.getDescricao());
            preparedStatement.setInt(2, produto.getQuantidadeEstoque());
            preparedStatement.executeUpdate();
            System.out.println("Produto atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void atualizarCategoriaProduto(Produto produto) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE produtos SET categoria = ? WHERE codigo = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, produto.getCategoria());
            preparedStatement.setString(2, produto.getCodigo());
            preparedStatement.executeUpdate();
            System.out.println("Produto atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void consultarProduto(Produto produto) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM produtos WHERE codigo = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, produto.getCodigo());
            preparedStatement.executeQuery();
            System.out.println("Produto consultado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao consultar produto: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
