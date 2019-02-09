package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		posizione.setNomeRegione(NOME_REGIONE_FIELD);
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
		PreparedStatement st = connection.prepareStatement(GET_POSITION_BY_ID);
		st.setInt(1, id);
		return st;
	}

	@Override
	protected PreparedStatement getPreparedUpdateItem(PosizioneBean item) throws SQLException {
		PreparedStatement st = connection.prepareStatement(UPDATE_POSIZIONE);
		st.setInt(1, item.getId());
		st.setBigDecimal(2, item.getLatitudine());
		st.setBigDecimal(3, item.getLongitudine());
		st.setInt(4, item.getIdComune());
		st.setString(5, item.getFormattedAdress());
		return st;
	}

	@Override
	protected PreparedStatement getPreparedInsertItem(PosizioneBean item) throws SQLException {
		PreparedStatement st = connection.prepareStatement(INSERT_POSIZIONE);
		st.setBigDecimal(1, item.getLatitudine());
		st.setBigDecimal(2, item.getLongitudine());
		st.setInt(3, item.getIdComune());
		st.setString(4, item.getFormattedAdress());
		return st;
	}

}
