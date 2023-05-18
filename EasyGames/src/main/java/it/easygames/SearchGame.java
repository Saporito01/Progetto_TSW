package it.easygames;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import it.easygames.model.Game;

public class SearchGame {
	public synchronized static List<Game> searchBarGame(String nome, String piattaforma){
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Game> model = new ArrayList<Game>();
		
		try {
			connection = DBConnectionPool.getConnection();
			
			if(piattaforma.equals("tutto"))
			{
				String sql = "SELECT * FROM gioco WHERE nome LIKE ?";
				stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, "%"+nome+"%");
				
				rs = stmt.executeQuery();
			}
			else
			{
				String sql = "SELECT * FROM gioco WHERE nome LIKE ? AND piattaforma = ?";
				stmt = connection.prepareStatement(sql);
				
				stmt.setString(1, "%"+nome+"%");
				stmt.setString(2, piattaforma);
				
				rs = stmt.executeQuery();
			}
			
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
}
