package it.easygames.model.dao;

import java.sql.SQLException;
import java.util.Collection;

import it.easygames.model.bean.Game;

public interface IGameDao {
	
	public void doSave(Game bean) throws SQLException;

	public boolean doDelete(String id) throws SQLException;
	
	public Game doRetrieveByKey(String id) throws SQLException;

	public Collection<Game> searchBarGame(String nome, String piattaforma) throws SQLException;
	
	public Collection<Game> doRetrieveAll(String order) throws SQLException;
	
	public void doUpdate(Game game) throws SQLException;
}