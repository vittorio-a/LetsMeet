package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import it.unisa.studenti.letsmeet.model.PosizioneBean;

public class PosizioneSqlDao extends SqlDao<PosizioneBean> {
	

	private static final String LONGITUDINE_FIELD = "longitudine";
	private static final String LATITUTDINE_FIELD = "latitudine";
	private static final String FORMATTED_ADDRESS_FIELD = "formattedAddress";
	private static final String NOME_COMUNE_FIELD = "nomeComune";
	private static final String ID_COMUNE_FIELD = "idComune";
	private static final String NOME_PROVINCIA_FIELD = "nomeProvincia";
	private static final String ID_PROVINCIA_FIELD = "idProvincia";
	private static final String SIGLA_FIELD = "sigla";
	private static final String NOME_REGIONE_FIELD = "nomeRegione";
	private static final String ID_REGIONE_FIELD = "idRegione";
	private static final String NOME_NAZIONE_FIELD = "nomeNazione";
	private static final String ID_NAZIONE_FIELD = "idNazione";
	private static final String ID_POSIZIONE_FIELD = "idPosizione";

	
	private static final String UPDATE_POSIZIONE = "UPDATE posizione SET latitudine = ?, longitudine = ?, formattedAddress = ? WHERE idPosizione = ?";
	private static final String INSERT_POSIZIONE = "INSERT posizione(latitudine,longitudine,formattedAddress) VALUE(?,?,?)";

	private static final String CHECK_COMUNE = "SELECT * FROM AppartenenzaComune WHERE idPosizione = ?";
	private static final String CHECK_PROVINCIA = "SELECT * FROM AppartenenzaProvincia WHERE idComune = ?";
	private static final String CHECK_REGIONE = "SELECT * FROM AppartenenzaRegione WHERE idProvincia = ?";
	private static final String CHECK_NAZIONE = "SELECT * FROM AppartenezaNazione WHERE idRegione = ?";
	
	private static final String INSERT_COMUNE = "INSERT Comune(nomeComune) VALUE(?)";
	private static final String INSERT_COMUNE_APP = "INSERT AppartenenzaComune(idPosizione, idComune) VALUE(?,?)";
	private static final String INSERT_PROVINCIA = "INSERT Provincia(nomeProvincia, sigla) VALUE(?,?)";
	private static final String INSERT_PROVINCIA_APP = "INSERT AppartenenzaProvincia(idComune, idProvincia) VALUE(?,?)";
	private static final String INSERT_REGIONE = "INSERT Regione(nomeRegione) VALUE(?)";
	private static final String INSERT_REGIONE_APP = "INSERT AppartenenzaRegione(idProvincia, idRegione) VALUE(?,?)";
	private static final String INSERT_NAZIONE = "INSERT Nazione(nomeNazione) VALUE(?)";
	private static final String INSERT_NAZIONE_APP = "INSERT AppartenezaNazione(idRegione, idNazione) VALUE(?,?)";
	
	
	private static final String GET_POSITION_BY_ID = "SELECT p.idPosizione, p.longitudine, p.latitudine, p.formattedAddress, c.nomeComune, c.idComune, pv.nomeProvincia, pv.idProvincia, pv.sigla, r.nomeRegione, r.idRegione, n.nomeNazione, n.idNazione\r\n" + 
			"	 FROM Posizione p\r\n"+
			"    NATURAL JOIN appartenenzacomune ac\r\n" + 
			"    NATURAL JOIN Comune c\r\n" + 
			"    NATURAL JOIN appartenenzaprovincia ap\r\n" + 
			"    natural join provincia pv\r\n" + 
			"    natural join appartenenzaregione ar\r\n" + 
			"    natural join regione r\r\n" + 
			"    natural join appartenezanazione an\r\n" + 
			"    natural join nazione n\r\n" + 
			"    WHERE p.idPosizione = ?";
	
	

	private static final String GET_ALL_POSITIONS = "SELECT p.idPosizione, p.longitudine, p.latitudine, p.formattedAddress, c.nomeComune, c.idComune, pv.nomeProvincia, pv.idProvincia, pv.sigla, r.nomeRegione, r.idRegione, n.nomeNazione, n.idNazione\r\n" + 
			"	 FROM Posizione p\r\n"+
			"    NATURAL JOIN appartenenzacomune ac\r\n" + 
			"    NATURAL JOIN Comune c\r\n" + 
			"    NATURAL JOIN appartenenzaprovincia ap\r\n" + 
			"    natural join provincia pv\r\n" + 
			"    natural join appartenenzaregione ar\r\n" + 
			"    natural join regione r\r\n" + 
			"    natural join appartenezanazione an\r\n" + 
			"    natural join nazione n\r\n";
	
	private static final String DELETE_POSITION_BY_ID = "DELETE FROM Posizione WHERE idPosizione = ?"; 

	
	public PosizioneSqlDao(Connection connection) {
		super(connection);
	}

	@Override
	protected int getKey(PosizioneBean item) {
		return item.getId();
	}

	@Override
	protected PosizioneBean getItemFromResultSet(ResultSet rs) throws SQLException {
		PosizioneBean posizione = new PosizioneBean();
		posizione.setId(rs.getInt(ID_POSIZIONE_FIELD));
		posizione.setFormattedAdress(rs.getString(FORMATTED_ADDRESS_FIELD));
		posizione.setIdComune(rs.getInt(ID_COMUNE_FIELD));
		posizione.setNomeComune(rs.getString(NOME_COMUNE_FIELD));
		posizione.setIdProvincia(rs.getInt(ID_PROVINCIA_FIELD));
		posizione.setNomeProvincia(rs.getString(NOME_PROVINCIA_FIELD));
		posizione.setIdRegione(rs.getInt(ID_PROVINCIA_FIELD));
		posizione.setNomeRegione(rs.getString(NOME_REGIONE_FIELD));
		posizione.setIdNazione(rs.getInt(ID_NAZIONE_FIELD));
		posizione.setNomeNazione(rs.getString(NOME_NAZIONE_FIELD));
		posizione.setLatitudine(rs.getBigDecimal(LATITUTDINE_FIELD));
		posizione.setLongitudine(rs.getBigDecimal(LONGITUDINE_FIELD));
		posizione.setSigla(rs.getString(SIGLA_FIELD));
		posizione.setIdRegione(rs.getInt(ID_REGIONE_FIELD));
		return posizione;
	}

	@Override
	protected PreparedStatement getPreparedGetById(int id) throws SQLException {
		PreparedStatement st = connection.prepareStatement(GET_POSITION_BY_ID);
		st.setInt(1, id);
		return st;
	}

	@Override
	protected PreparedStatement getPreparedGetAll() throws SQLException {
		return connection.prepareStatement(GET_ALL_POSITIONS);
	}

	@Override
	protected PreparedStatement getPreparedDeleteById(int id) throws SQLException {
		PreparedStatement st = connection.prepareStatement(DELETE_POSITION_BY_ID);
		st.setInt(1, id);
		return st;
	}

	@Override
	protected PreparedStatement getPreparedUpdateItem(PosizioneBean item) throws SQLException {
		PreparedStatement st = connection.prepareStatement(UPDATE_POSIZIONE);
		st.setBigDecimal(1, item.getLatitudine());
		st.setBigDecimal(2, item.getLongitudine());
		st.setString(3, item.getFormattedAdress());
		st.setInt(4, item.getId());
		return st;
	}

	@Override
	protected PreparedStatement getPreparedInsertItem(PosizioneBean item) throws SQLException {
		PreparedStatement st = connection.prepareStatement(INSERT_POSIZIONE, Statement.RETURN_GENERATED_KEYS);
		st.setBigDecimal(1, item.getLatitudine());
		st.setBigDecimal(2, item.getLongitudine());
		st.setString(3, item.getFormattedAdress());
		return st;
	}
	
	
	/**
	 * Controlla se checkSt ha almeno 1 elemento di ritorno e nel caso non sia cosi esegue insertSt
	 * @param checkSt lo statement di query che viene utilizzato per il controllo
	 * @param insertSt lo statement di update che viene utilizzato nel caso non ci siano elementi di ritorno per checkSt
	 * @param invalidate parametro che settato a true fa fallire il check su checkSt
	 * @return se il check ha avuto esito positovo 0 altrimenti l'id del record inserito
	 * @throws SQLException
	 * @throws DaoException
	 */
	private int checkAndInsert(PreparedStatement checkSt, PreparedStatement insertStRtn, PreparedStatement insertSt, boolean invalidate) throws SQLException, DaoException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			int returnedId = 0;
			rs = checkSt.executeQuery();
			if(rs.next() && !invalidate) {
				//check andato a buon fine
				return 0;
			}else {
				if(insertStRtn.executeUpdate() <= 0) throw new DaoException("Unable to update position", null, DaoExceptionType.SQLException);
				rs.close();
				rs = insertStRtn.getGeneratedKeys();
				if(rs.next()) returnedId = rs.getInt(1);
				else throw new DaoException("Unable to update position no key retrived", null, DaoExceptionType.SQLException);
				
				insertSt.setInt(2, returnedId);
				if(insertSt.executeUpdate() > 0) return returnedId;
				else throw new DaoException("Unable to update position cannot insert 2nd stm", null, DaoExceptionType.SQLException);
			}
		}catch (SQLException e) {
			throw e;
		}finally {
			try {
				if(rs != null && !rs.isClosed()) rs.close();
				if(ps != null && !ps.isClosed()) ps.close();
			}catch (SQLException e) {
				//do nothing
			}
		}
	}
	
	
	/*private static final String CHECK_COMUNE = "SELECT * FROM AppartenenzaComune WHERE idPosizione = ?";
	private static final String CHECK_PROVINCIA = "SELECT * FROM AppartenenzaProvincia WHERE idComune = ?";
	private static final String CHECK_REGIONE = "SELECT * FROM AppartenenzaRegione WHERE idProvincia = ?";
	private static final String CHECK_NAZIONE = "SELECT * FROM AppartenezaNazione WHERE idRegione = ?";
	
	private static final String INSERT_COMUNE = "INSERT Comune(nomeComune) VALUE(?)";
	private static final String INSERT_COMUNE_APP = "INSERT AppartenenzaComune(idPosizione, idComune) VALUE(?,?)";
	private static final String INSERT_PROVINCIA = "INSERT Provincia(nomeProvincia, sigla) VALUE(?,?)";
	private static final String INSERT_PROVINCIA_APP = "INSERT AppartenenzaProvincia(idComune, idProvincia) VALUE(?,?)";
	private static final String INSERT_REGIONE = "INSERT Regione(nomeRegione) VALUE(?)";
	private static final String INSERT_REGIONE_APP = "INSERT AppartenenzaRegione(idProvincia, idRegione) VALUE(?,?)";
	private static final String INSERT_NAZIONE = "INSERT Nazione(nomeNazione) VALUE(?)";
	private static final String INSERT_NAZIONE_APP = "INSERT AppartenezaNazione(idRegione, idNazione) VALUE(?,?)";*/
	
	
	private int checkNazione(PosizioneBean item, boolean invalidate) throws SQLException, DaoException{
		PreparedStatement check, insertNazione, insertAppartenenza;
		if(!invalidate) {
			check = connection.prepareStatement(CHECK_NAZIONE);
			check.setInt(1, item.getIdRegione());
			insertNazione = connection.prepareStatement(INSERT_NAZIONE, PreparedStatement.RETURN_GENERATED_KEYS);
			insertNazione.setString(1, item.getNomeNazione());
			insertAppartenenza = connection.prepareStatement(INSERT_NAZIONE_APP);
			insertAppartenenza.setInt(1, item.getIdRegione());
		}else{
			throw new UnsupportedOperationException("Not yet implemented");			
		}
		int newIdNazione = checkAndInsert(check, insertNazione, insertAppartenenza, invalidate);
		int idNazione = item.getIdNazione();
		if(idNazione <= 0) item.setIdNazione(newIdNazione);
		return newIdNazione;
	}
	
	
	private int checkRegione(PosizioneBean item, boolean invalidate) throws SQLException, DaoException{
		PreparedStatement check, insertRegione, insertRegioneAppartenenza;
		if(!invalidate) {
			check = connection.prepareStatement(CHECK_REGIONE);
			check.setInt(1, item.getIdProvincia());
			insertRegione = connection.prepareStatement(INSERT_REGIONE, PreparedStatement.RETURN_GENERATED_KEYS);
			insertRegione.setString(1, item.getNomeRegione());
			insertRegioneAppartenenza = connection.prepareStatement(INSERT_REGIONE_APP);
			insertRegioneAppartenenza.setInt(1, item.getIdProvincia());
	
		}else{
			throw new UnsupportedOperationException("Not yet implemented");
		}
		int newIdRegione = checkAndInsert(check, insertRegione, insertRegioneAppartenenza, invalidate);
		int idRegione = item.getIdRegione();
		if(idRegione <= 0) item.setIdRegione(newIdRegione);
		if(!invalidate && newIdRegione > 0) checkNazione(item, invalidate);
		return newIdRegione;
	}
	
	private int checkProvincia(PosizioneBean item, boolean invalidate) throws SQLException, DaoException{
		PreparedStatement check, insertProvincia, insertProvinciaAppartenenza;
		check = connection.prepareStatement(CHECK_PROVINCIA);
		check.setInt(1, item.getIdComune());
		if(!invalidate) {
			insertProvincia= connection.prepareStatement(INSERT_PROVINCIA, PreparedStatement.RETURN_GENERATED_KEYS);
			insertProvincia.setString(1, item.getNomeProvincia());
			insertProvincia.setString(2, item.getSigla());
			insertProvinciaAppartenenza = connection.prepareStatement(INSERT_PROVINCIA_APP);
			insertProvinciaAppartenenza.setInt(1, item.getIdComune());
		}else{
			throw new UnsupportedOperationException("Not yet implemented");
		}
		int newIdProvincia = checkAndInsert(check, insertProvincia, insertProvinciaAppartenenza, invalidate);
		int idProvincia = item.getIdProvincia();
		if(idProvincia <= 0) item.setIdProvincia(newIdProvincia);
		if(!invalidate && newIdProvincia > 0) checkRegione(item, invalidate);
		return newIdProvincia;
	}
	
	private int checkComune(PosizioneBean item, boolean invalidate) throws SQLException, DaoException{
		PreparedStatement check, insertComune, insertComuneAppartenenza;
		check = connection.prepareStatement(CHECK_COMUNE);
		check.setInt(1, item.getId());
		if(!invalidate) {
			insertComune= connection.prepareStatement(INSERT_COMUNE, PreparedStatement.RETURN_GENERATED_KEYS);
			insertComune.setString(1, item.getNomeComune());
			insertComuneAppartenenza = connection.prepareStatement(INSERT_COMUNE_APP);
			insertComuneAppartenenza.setInt(1, item.getId());
		}else{
			throw new UnsupportedOperationException("Not yet implemented");
		}
		int newIdComune = checkAndInsert(check, insertComune, insertComuneAppartenenza, invalidate);
		int idComune = item.getIdComune();
		if(idComune <= 0) item.setIdComune(newIdComune);
		if(!invalidate && newIdComune > 0) checkProvincia(item, invalidate);
		return newIdComune;
	}
	
	@Override
	public int saveOrUpdate(PosizioneBean item) throws DaoException {
		int ret = super.saveOrUpdate(item);
		if(ret > 0) item.setId(ret);

		int idPosizione = item.getId();
		boolean posizioneExists = false, isEqualsInDb = false;
		if(idPosizione > 0) {
			PosizioneBean posizioneInDb = get(idPosizione);
			if(posizioneInDb != null) {
				posizioneExists = true;
				isEqualsInDb = item.equals(posizioneInDb);
			}
		}
		int ret2 = 0;
		try {
			if(posizioneExists && !isEqualsInDb)
				ret2 = checkComune(item, true);
			else{
				ret2 = checkComune(item, false);
			}
		}catch (SQLException e) {
			throw new DaoException("Eccezione mentre si aggiornava catenda posizione", e, DaoExceptionType.SQLException);
		}
		if(ret == 0 && ret2 == 0)
			return 0;
		else
			return item.getId();
	}

}
