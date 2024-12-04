package database;

import util.ConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static Connection connection = null;

    static {
        // Carregar configurações do arquivo application.properties
        URL = ConfigLoader.getProperty("db.url");
        USER = ConfigLoader.getProperty("db.username");
        PASSWORD = ConfigLoader.getProperty("db.password");

        if (URL == null || USER == null || PASSWORD == null) {
            logger.severe("Erro ao carregar configurações do banco de dados, verifique o arquivo application.properties.");
            throw new IllegalStateException("Configurações inválidas ou vazias.");
        }
    }

    // Construtor privado para evitar instanciação
    private DatabaseConnection() {
    }

    /**
     * Obtém a conexão com o banco de dados.
     *
     * @return Conexão estabelecida.
     */
    public static Connection getConnection() {
        if (connection == null) {
            synchronized (DatabaseConnection.class) {
                if (connection == null) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        logger.info("Conexão com o banco de dados estabelecida.");
                    } catch (ClassNotFoundException e) {
                        logger.log(Level.SEVERE, "Erro ao carregar o driver JDBC.", e);
                        throw new RuntimeException("Driver JDBC não encontrado.", e);
                    } catch (SQLException e) {
                        logger.log(Level.SEVERE, "Erro ao conectar com o banco de dados.", e);
                        throw new RuntimeException("Erro ao conectar com o banco de dados.", e);
                    }
                }
            }
        }
        return connection;
    }

    /**
     * Fecha a conexão com o banco de dados, se existente.
     */

    public static void closeConnection() {
        if (connection != null) {
            synchronized (DatabaseConnection.class) {
                try {
                    connection.close();
                    connection = null;
                    logger.info("Conexão com o banco de dados encerrada.");
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Erro ao fechar a conexão com o banco de dados.", e);
                    throw new RuntimeException("Erro ao fechar a conexão com o banco de dados.", e);
                }
            }
        }
    }
}
