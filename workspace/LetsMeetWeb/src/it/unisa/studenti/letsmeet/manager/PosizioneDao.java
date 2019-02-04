package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.studenti.letsmeet.model.CredentialsBean;
import it.unisa.studenti.letsmeet.model.DataSourceSingleton;
import it.unisa.studenti.letsmeet.model.PosizioneBean;
import it.unisa.studenti.letsmeet.model.UtenteBean;

public class PosizioneDao implements Dao<PosizioneBean> {
	private DataSource ds;
	
	
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

	
	private static final String UPDATE_POSIZIONE = "UPDATE posizione WHERE idPosizione = ? SET latitudine = ?, longitudine = ?, idComune = ?, formattedAddress = ?";
	private static final String INSERT_POSIZIONE = "INSERT posizione(latitudine,longitudine,idComune,formattedAddress) VALUE(?,?,?,?)";

	
	
	private static final String GET_POSITION_BY_ID = "SELECT p.idPosizione, p.longitudine, p.latitudine, p.formattedAddress, c.nomeComune, c.idComune, pv.nomeProvincia, pv.idProvincia, pv.sigla, r.nomeRegione, r.idRegione, n.nomeNazione, n.idNazione FROM Evento e\r\n" + 
			"	NATURAL JOIN Posizione p\r\n" + 
			"    NATURAL JOIN appartenenzacomune ac\r\n" + 
			"    NATURAL JOIN Comune c\r\n" + 
			"    NATURAL JOIN appartenenzaprovincia ap\r\n" + 
			"    natural join provincia pv\r\n" + 
			"    natural join appartenenzaregione ar\r\n" + 
			"    natural join regione r\r\n" + 
			"    natural join appartenezanazione an\r\n" + 
			"    natural join nazione n\r\n" + 
			"    WHERE e.id = ?";
	
	

	private static final String GET_ALL_POSITIONS = "SELECT p.idPosizione, p.longitudine, p.latitudine, p.formattedAddress, c.nomeComune, c.idComune, pv.nomeProvincia, pv.idProvincia, pv.sigla, r.nomeRegione, r.idRegione, n.nomeNazione, n.idNazione FROM Evento e\r\n" + 
			"	NATURAL JOIN Posizione p\r\n" + 
			"    NATURAL JOIN appartenenzacomune ac\r\n" + 
			"    NATURAL JOIN Comune c\r\n" + 
			"    NATURAL JOIN appartenenzaprovincia ap\r\n" + 
			"    natural join provincia pv\r\n" + 
			"    natural join appartenenzaregione ar\r\n" + 
			"    natural join regione r\r\n" + 
			"    natural join appartenezanazione an\r\n" + 
			"    natural join nazione n\r\n";
	
	public PosizioneDao() throws DaoException {
		try {
			ds = DataSourceSingleton.getDataSource();
		}catch (NamingException e) {
			throw new DaoException("Non è possibile recuperare data source", e, DaoExceptionType.SQLException);
		}
		
	}
	
	@Override
	public PosizioneBean get(int id) throws DaoException {
		PosizioneBean posizione = null;
		try {
			Connection conn = ds.getConnection();
			PreparedStatement st = conn.prepareStatement(GET_POSITION_BY_ID);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next())
				posizione = getPosizioneBeanFromResultSet(rs);
			st.close();
			rs.close();
			conn.close();
			return posizione;
		}catch (SQLException e) {
			throw new DaoException("SQL excpetion in get by id", e, DaoExceptionType.SQLException);
		}finally {

		}
	}

	private PosizioneBean getPosizioneBeanFromResultSet(ResultSet rs) throws SQLException {
		PosizioneBean posizione = new PosizioneBean();
		posizione.setId(rs.getInt(ID_POSIZIONE_FIELD));
		posizione.setFormattedAdress(rs.getString(FORMATTED_ADDRESS_FIELD));
		posizione.setIdComune(rs.getInt(ID_COMUNE_FIELD));
		posizione.setNomeComune(rs.getString(NOME_COMUNE_FIELD));
		posizione.setIdProvincia(rs.getInt(ID_PROVINCIA_FIELD));
		posizione.setNomeProvincia(rs.getString(NOME_PROVINCIA_FIELD));
		posizione.setIdRegione(rs.getInt(ID_PROVINCIA_FIELD));
		posizione.setNomeRegione(NOME_REGIONE_FIELD);
		posizione.setIdNazione(rs.getInt(ID_NAZIONE_FIELD));
		posizione.setNomeNazione(rs.getString(NOME_NAZIONE_FIELD));
		return posizione;
	}

	@Override
	public List<PosizioneBean> getAll() throws DaoException {
		List<PosizioneBean> positions = new ArrayList<>();
		try {
			Connection conn = ds.getConnection(); 
			PreparedStatement st = conn.prepareStatement(GET_ALL_POSITIONS);
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
				positions.add(getPosizioneBeanFromResultSet(rs));
	
			st.close();
			rs.close();
			conn.close();
		}catch (SQLException e) {
			throw new DaoException("SQL excpetion in getAll", e, DaoExceptionType.SQLException);
		}
		return null;
	}

	@Override
	public boolean saveOrUpdate(PosizioneBean posizoneBean) throws DaoException {
		int idPosizione = posizoneBean.getId();
		
		if(idPosizione != 0) {
			PosizioneBean otherPosizione = get(idPosizione);
			if(otherPosizione.equals(posizoneBean)) {
				return false;
			}
			else {
				try {
					
					Connection conn = ds.getConnection();
					PreparedStatement st = conn.prepareStatement(UPDATE_POSIZIONE);
					st.setInt(1, posizoneBean.getId());
					st.setBigDecimal(2, posizoneBean.getLatitudine());
					st.setBigDecimal(3, posizoneBean.getLongitudine());
					st.setInt(4, posizoneBean.getIdComune());
					st.setString(5, posizoneBean.getFormattedAdress());
					st.executeUpdate();
					st.close();
					conn.close();
				}catch (SQLException e) {
					throw new DaoException("utente update: " + (posizoneBean != null ? posizoneBean.toString() : "null"), e, DaoExceptionType.SQLException);
				}
			}
		}
		else {
			try {
				Connection conn = ds.getConnection();
				PreparedStatement st = conn.prepareStatement(INSERT_POSIZIONE);
				st.setBigDecimal(1, posizoneBean.getLatitudine());
				st.setBigDecimal(2, posizoneBean.getLongitudine());
				st.setInt(3, posizoneBean.getIdComune());
				st.setString(4, posizoneBean.getFormattedAdress());
				
				st.executeUpdate();
				st.close();
				conn.close();
			}catch (SQLException e) {
				throw new DaoException("utente insert: " + (posizoneBean != null ? posizoneBean.toString() : "null"), e, DaoExceptionType.SQLException);
			}
			
		}
		return true;
	}
	

	@Override
	public boolean delete(PosizioneBean t) throws DaoException {
		throw new DaoException("Metodo non implementato", new UnsupportedOperationException(), DaoExceptionType.SQLException);
	}

}
