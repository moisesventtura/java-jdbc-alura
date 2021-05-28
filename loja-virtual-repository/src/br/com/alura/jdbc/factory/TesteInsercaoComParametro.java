package br.com.alura.jdbc.factory;

import br.com.alura.jdbc.ConnectionFactory;

import java.sql.*;

public class TesteInsercaoComParametro {

    public static void main(String[] args) throws SQLException {



        String nome = "";
        String descricao = "";
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();
        PreparedStatement stm =
                connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);

        stm.setString(1, nome);
        stm.setString(2, descricao);

        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();
        while (rst.next()){
            Integer id = rst.getInt(1);
            System.out.println("O id criado foi: " + id);
        }

        rst.close();
    }
}

