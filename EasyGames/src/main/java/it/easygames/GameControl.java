package it.easygames;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.easygames.model.Game;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GameControl {
	public synchronized static List<Game> loadGame(){
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Game> model = new ArrayList<Game>();
		
		try {
			connection = DBConnectionPool.getConnection();
			String sql = "SELECT * FROM gioco";
			stmt = connection.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Game item = new Game();
				item.setId(rs.getString("id"));
				item.setName(rs.getString("nome"));
				item.setDesc(rs.getString("descrizione"));
				item.setPlatf(rs.getString("piattaforma"));
				item.setQt(rs.getInt("quantita"));
				item.setPrice(rs.getFloat("prezzo"));
				
				model.add(item);
			}
		} catch (SQLException sqlException) {
			System.out.println(sqlException);
		} 
			finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (connection != null) 
					DBConnectionPool.releaseConnection(connection);
			}
		}
		return model;
	}
	
	public synchronized static List<Game> loadOrderedGames(){
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Game> model = new ArrayList<Game>();
		
		try {
			connection = DBConnectionPool.getConnection();
			String sql = "SELECT * FROM gioco ORDER BY nome";
			stmt = connection.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Game item = new Game();
				item.setId(rs.getString("id"));
				item.setName(rs.getString("nome"));
				item.setDesc(rs.getString("descrizione"));
				item.setPlatf(rs.getString("piattaforma"));
				item.setQt(rs.getInt("quantita"));
				item.setPrice(rs.getFloat("prezzo"));
				
				model.add(item);
			}
		}catch (SQLException sqlException) {
			System.out.println(sqlException);
		} 
			finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (connection != null) 
					DBConnectionPool.releaseConnection(connection);
			}
		}
		return model;
	}

	public synchronized static byte[] loadCover(String id) {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		byte[] bt = null;

		try {
			connection = DBConnectionPool.getConnection();
			String sql = "SELECT copertina FROM gioco WHERE id = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				bt = rs.getBytes("copertina");
			}

			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} 
				finally {
					try {
						if (stmt != null)
							stmt.close();
					} catch (SQLException sqlException) {
						System.out.println(sqlException);
					} finally {
						if (connection != null) 
							DBConnectionPool.releaseConnection(connection);
					}
				}
			return bt;
	}
	
	public static void addGame(Game model) {
		Connection connection = null;
		PreparedStatement stmt = null;

		String sql = "INSERT INTO gioco (id, nome, descrizione, piattaforma, quantita, prezzo) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			connection = DBConnectionPool.getConnection();
			stmt = connection.prepareStatement(sql);			
			
			stmt.setString(1, model.getId());
			stmt.setString(2, model.getName());
			stmt.setString(3, model.getDesc());
			stmt.setString(4, model.getPlatf());
			stmt.setInt(5, model.getQt());
			stmt.setFloat(6, model.getPrice());
			
			stmt.executeUpdate();	
			
			connection.commit();
			
		} catch (SQLException sqlException) {
			System.out.println(sqlException);
		}
			finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (connection != null) 
					DBConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public synchronized static void addCover(String id, InputStream cover) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBConnectionPool.getConnection();
			stmt = con.prepareStatement("UPDATE gioco SET copertina = ? WHERE id = ?");
			try {
				stmt.setBinaryStream(1, cover, cover.available());
				stmt.setString(2, id);	
				stmt.executeUpdate();
				con.commit();
			} catch (IOException e) {
				System.out.println(e);
			}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (con != null)
					DBConnectionPool.releaseConnection(con);
			}
		}
	}
}
