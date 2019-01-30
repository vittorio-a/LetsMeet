package it.unisa.studenti.letsmeet.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.studenti.letsmeet.model.DataSourceSingleton;
import it.unisa.studenti.letsmeet.model.PartecipazioneBean;

public class PartecipazioneDao implements DaoDoubleKey<PartecipazioneBean> {

	private DataSource ds;
	
	private static final String GET_PARTECIPATION_BY_EVENT_ID = "SELECT * FROM Partecipazione WHERE idEvento = ?";
	private static final String DELETE_PARTECIPATION = "DELETE FROM Partecipazione WHERE idEvento = ? AND idUtente = ?";
	private static final String GET_PARTECIPATION_BY_EVENT_USER_ID = "SELECT * FROM Partecipazione WHERE idUtente = ?  AND idEvento = ?";
	private static final String GET_PARTECIPATION_BY_USER_ID = "SELECT * FROM Partecipazione WHERE idUtente = ?";
	private static final String INSERT_PARTECIPATION = "INSERT Partecipazione(idUtente, idEvento, isVerification) VALUE(?,?,?)";
	private static final String UPDATE_PARTECIPATION = "UPDATE Rating WHERE idUtente = ? AND idEvento = ? SET isVerificato = ?";
	
	private static final String EVENT_ID_FIELD = "idEvento";
	private static final String USER_ID_FILED = "idUtente";
	private static final String VERIFICATION_FILED = "votazione";
	
	
	public PartecipazioneDao() throws NamingException {
		ds = DataSourceSingleton.getDataSource();
	}
	
	private PartecipazioneBean getPartecipationFromResulSet(ResultSet rs) throws SQLException {
		PartecipazioneBean partecipation = new PartecipazioneBean();
		partecipation.setIdUtente(rs.getInt(USER_ID_FILED));
		partecipation.setIdEvento(rs.getInt(EVENT_ID_FIELD));
		partecipation.setVerificato(rs.getBoolean(VERIFICATION_FILED));
		return partecipation;
	}
	
	@Override
	public List<PartecipazioneBean> getAllForFirstKey(int id) throws DaoException {
		List<PartecipazioneBean> partecipazioni = new ArrayList<PartecipazioneBean>();
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(GET_PARTECIPATION_BY_USER_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				partecipazioni.add(getPartecipationFromResulSet(rs));
			}
			rs.close();
			ps.close();
			con.close();
		}catch(SQLException e) {
			throw new DaoException("sql exception", e, DaoExceptionType.SQLException);
		}
		return partecipazioni;
	}
	@Override
	public List<PartecipazioneBean> getAllForSecondKey(int id) throws DaoException {
		List<PartecipazioneBean> partecipazioni = new ArrayList<PartecipazioneBean>();
		try {
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(GET_PARTECIPATION_BY_EVENT_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				partecipazioni.add(getPartecipationFromResulSet(rs));
			}
			rs.close();
			ps.close();
			con.close();
		}catch(SQLException e) {
			throw new DaoException("sql exception", e, DaoExceptionType.SQLException);
		}
		return partecipazioni;
	}
	
	@Override
	public PartecipazioneBean getFromBothKeys(int id1, int id2) throws DaoException {
		PartecipazioneBean partecipazione = null;
		try {
			Connection conn = ds.getConnection();
			PreparedStatement st = conn.prepareStatement(GET_PARTECIPATION_BY_EVENT_USER_ID);
			st.setInt(1, id1);
			st.setInt(2, id2);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				partecipazione = getPartecipationFromResulSet(rs);
			}
			rs.close();
			st.close();
			conn.close();
			return partecipazione;
		}catch(SQLException e) {
			throw new DaoException("sql exception", e, DaoExceptionType.SQLException);
		}
	}
	
	@Override
	public boolean saveOrUpdate(PartecipazioneBean partecipation) throws DaoException {
		Connection con = null;
		try {
			 con = ds.getConnection();
		} catch (SQLException e) {
			throw new DaoException("error getting the connection", e, DaoExceptionType.SQLException);
		}
		if(getFromBothKeys(partecipation.getIdUtente(), partecipation.getIdEvento()) != null) {
			try {
			PreparedStatement ps = con.prepareStatement(UPDATE_PARTECIPATION);
				ps.setInt(1, partecipation.getIdUtente());
				ps.setInt(2, partecipation.getIdEvento());
				ps.setBoolean(3, partecipation.isVerificato());
				boolean isOkUpdate = (ps.executeUpdate() > 0);
				ps.close();
				con.close();
				return isOkUpdate;
			}catch(SQLException e) {
				throw new DaoException("update " + partecipation.toString(), e, DaoExceptionType.SQLException);
			}
		}else {
			try {
				PreparedStatement ps = con.prepareStatement(INSERT_PARTECIPATION);
				ps.setInt(1, partecipation.getIdUtente());
				ps.setInt(2, partecipation.getIdEvento());
				ps.setBoolean(3, partecipation.isVerificato());
				boolean isOkInsert =  (ps.executeUpdate() > 0);
				ps.close();
				con.close();
				return isOkInsert;
			}catch (SQLException e) {
				throw new DaoException("insert " + partecipation.toString(), e, DaoExceptionType.SQLException);
			}
		}	
	}
	@Override
	public boolean delete(PartecipazioneBean partecipazione) throws DaoException {
		int idUtente = partecipazione.getIdUtente();
		int idEvento = partecipazione.getIdEvento();
		if(idUtente != 0 && idEvento != 0) {
			try {
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(DELETE_PARTECIPATION);
				ps.setInt(1, idUtente);
				ps.setInt(2, idEvento);
				ps.executeQuery();
				ps.close();
				con.close();
			} catch (SQLException e) {
				throw new DaoException("SQLException in delete PartecipationDao", e, DaoExceptionType.SQLException);
			}
			return true;
		}
		return false;
	}

}
