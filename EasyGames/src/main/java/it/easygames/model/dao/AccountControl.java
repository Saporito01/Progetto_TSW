package it.easygames.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import it.easygames.model.bean.Account;

public class AccountControl {
	public synchronized static Collection<Account> load() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Account> accounts = new LinkedList<Account>();

		String selectSQL = "SELECT * FROM account ORDER BY nickname";

		try
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Account account = new Account();
				
				account.setNickname(rs.getString("nickname"));
				account.setNome(rs.getString("nome"));
				account.setCognome(rs.getString("cognome"));
				account.setDataNascita(rs.getString("data_nascita"));
				account.setEmail(rs.getString("email"));
				account.setPassword(rs.getString("password"));
				
				accounts.add(account);
			}
		}
		finally
		{
			try
			{
				if (preparedStatement != null)
					preparedStatement.close();
			}
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return accounts;
	}
	
	
	public synchronized static void doSave(Account account) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO account (nickname, nome, cognome, data_nascita, email, password) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection =DriverManagerConnectionPool.getConnection();
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, account.getNickname());
			preparedStatement.setString(2, account.getNome());
			preparedStatement.setString(3, account.getCognome());
			preparedStatement.setString(4, account.getDataNascita());
			preparedStatement.setString(5, account.getEmail());
			preparedStatement.setString(6, account.getPassword());

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
}
