package es.uclm.iso2.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

import es.uclm.iso2.rmi.exceptions.FullGameException;
import es.uclm.iso2.rmi.exceptions.GameNotFoundException;
import es.uclm.iso2.rmi.exceptions.InvalidGameInfoException;
import es.uclm.iso2.rmi.exceptions.InvalidSessionException;
import es.uclm.iso2.rmi.exceptions.InvalidTerritoryException;
import es.uclm.iso2.rmi.exceptions.InvalidTimeException;
import es.uclm.iso2.rmi.exceptions.NotCurrentPlayerGameException;
import es.uclm.iso2.rmi.exceptions.UserAlreadyExistsException;
import es.uclm.iso2.rmi.exceptions.WrongLoginException;

public interface IServer extends java.rmi.Remote {

	void registerUser(String name, String password, String email)
			throws RemoteException, UserAlreadyExistsException;

	UUID loginUser(String name, String password, Remote callback)
			throws RemoteException, WrongLoginException;

	void logoutUser(UUID session) throws RemoteException,
			InvalidSessionException;

	ArrayList<GameInfo> listGames() throws RemoteException;

	UUID createGame(GameInfo info) throws RemoteException,
			InvalidGameInfoException;

	void joinGame(UUID session, UUID game) throws RemoteException,
			FullGameException, GameNotFoundException, InvalidSessionException;

	void resignGame(UUID session, UUID game) throws RemoteException,
			GameNotFoundException, InvalidSessionException;

	Game playGame(UUID session, UUID game) throws RemoteException,
			GameNotFoundException, InvalidSessionException,
			InvalidTimeException;

	void quitGame(UUID session, UUID game) throws RemoteException,
			GameNotFoundException, InvalidSessionException,
			InvalidTimeException;

	void updateGame(UUID session, UUID game, ArrayList<Player> playerUpdate,
			ArrayList<Territory> territoryUpdate, EventType event) throws RemoteException,
			GameNotFoundException, InvalidSessionException,
			NotCurrentPlayerGameException;

	void attackTerritory(UUID session, UUID game, Territory src, Territory dst,
			Arsenal arsenal) throws RemoteException, GameNotFoundException,
			InvalidSessionException, InvalidTerritoryException;

	void acceptAttack(UUID session, UUID game) throws RemoteException,
			GameNotFoundException, InvalidSessionException;

	void requestedNegotiation(UUID session, UUID game, int money, int soldiers)
			throws RemoteException, GameNotFoundException,
			InvalidSessionException;

	void endTurn(UUID session, UUID game) throws Exception, RemoteException;

}