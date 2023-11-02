package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {

    public Account registerUser(Account user) {
        Connection connection = ConnectionUtil.getConnection();
        try {

            String sql = "INSERT INTO account(username, password) values(?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();

            if (pkeyResultSet.next()) {
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, user.getUsername(), user.getPassword());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account getUser(Account user) {
        Connection connection = ConnectionUtil.getConnection();

        try {

            String sql = "SELECT * FROM account where username = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getUsername());

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"),
                rs.getString("password"));
                return account;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Account getUserByID(int id) {
        Connection connection = ConnectionUtil.getConnection();

        try {

            String sql = "SELECT * FROM account where account_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
