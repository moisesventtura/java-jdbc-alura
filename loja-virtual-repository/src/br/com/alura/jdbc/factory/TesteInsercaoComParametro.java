package br.com.alura.jdbc.factory;

import br.com.alura.jdbc.ConnectionFactory;

import java.sql.*;

public class TesteInsercaoComParametro {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();
        connection.setAutoCommit(false);
        PreparedStatement stm =
                connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);

        adicionarVariavel("SmartTv", "45", stm);
        adicionarVariavel("Radio", "Radio de Bateria", stm);
    }

        private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
            stm.setString(1, nome);
            stm.setString(2, descricao);

//            if(nome.equals("Radio")){
//                throw new RuntimeException("Não foi possível adicionar o produto");
//            }

            stm.execute();

            ResultSet rst = stm.getGeneratedKeys();
            while (rst.next()){
                Integer id = rst.getInt(1);
                System.out.println("O id criado foi: " + id);
            }
            rst.close();
        }

    }

