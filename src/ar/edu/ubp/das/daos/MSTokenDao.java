package ar.edu.ubp.das.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import ar.edu.ubp.das.bean.TokenBean;
import ar.edu.ubp.das.db.Dao;

public class MSTokenDao extends Dao<TokenBean, TokenBean>{

	@Override
	public TokenBean delete(TokenBean token) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TokenBean insert(TokenBean token) throws SQLException {
		try {
			this.connect();
			
			token.setToken(UUID.randomUUID().toString());
			
			this.setProcedure("dbo.INSERTAR_TOKEN(?,?,?,?)");
			this.setParameter(1, token.getIdEntidad());
			this.setParameter(2, token.getToken());
			this.setParameter(3, token.getFechaCreacion());
			this.setParameter(4, token.getFechaExpiracion());
			
			this.executeUpdate();
			
			return token;
			
		} finally {
			this.close();
		}
	}

	@Override
	public TokenBean make(ResultSet resultSet) throws SQLException {
		TokenBean token = new TokenBean();
		
		token.setFechaCreacion(resultSet.getTimestamp("Fecha_Creacion"));
		token.setFechaExpiracion(resultSet.getTimestamp("Fecha_Expiracion"));
		token.setId(resultSet.getInt("Id_Token"));
		token.setToken(resultSet.getString("Token"));
		token.setIdEntidad(resultSet.getInt("Id_Entidad"));

		return token;
	}

	@Override
	public List<TokenBean> select(TokenBean token) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TokenBean update(TokenBean token) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean valid(TokenBean token) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
