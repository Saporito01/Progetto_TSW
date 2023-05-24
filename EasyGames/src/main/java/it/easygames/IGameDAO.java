package it.easygames;

import java.sql.SQLException;
import java.util.Collection;
import it.easygames.model.Game;

public interface IGameDAO {
	
	public void doSave(Game bean) throws SQLException;

	public boolean doDelete(String code) throws SQLException;

	public Collection<Game> searchBarGame(String nome, String piattaforma) throws SQLException;
	
	public Collection<Game> doRetrieveAll(String order) throws SQLException;
}