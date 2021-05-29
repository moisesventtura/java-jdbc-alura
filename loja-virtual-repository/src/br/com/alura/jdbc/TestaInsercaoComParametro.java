package br.com.alura.jdbc;

import java.sql.*;

public class TestaInsercaoComParametro {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try(Connection connection = connectionFactory.recuperarConexao()) {

            connection.setAutoCommit(false);

            try (PreparedStatement stm =
                         connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            ) {
                adicionarVariavel("SmartTv", "45", stm);
                adicionarVariavel("Radio", "Radio de Bateria", stm);

                connection.commit();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ROLLBACK EXECUTADO");
                connection.rollback();
            }
        }
    }
        private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
            stm.setString(1, nome);
            stm.setString(2, descricao);

            if(nome.equals("Radio")){
                throw new RuntimeException("Não foi possível adicionar o produto");
            }

            stm.execute();

           try( ResultSet rst = stm.getGeneratedKeys();) {
               while (rst.next()) {
                   Integer id = rst.getInt(1);
                   System.out.println("O id criado foi: " + id);
               }
           }
        }
    }

