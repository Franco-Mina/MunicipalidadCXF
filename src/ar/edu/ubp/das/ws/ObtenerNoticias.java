package ar.edu.ubp.das.ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.Gson;

import ar.edu.ubp.das.bean.NoticiaAvisoBean;
import ar.edu.ubp.das.bean.ws.NoticiaResponseBean;
import ar.edu.ubp.das.bean.ws.NoticiasRequestBean;
import ar.edu.ubp.das.db.Dao;
import ar.edu.ubp.das.db.DaoFactory;

@WebService(targetNamespace = "http://ws.das.ubp.edu.ar/", portName = "ObtenerNoticiasPort", serviceName = "ObtenerNoticiasService")
public class ObtenerNoticias {

	
	@WebMethod(operationName = "getNoticias", action = "urn:GetNoticias")
	public String getNoticias(@WebParam(name = "arg0") String request) {
		Gson gson = new Gson();
		
		NoticiasRequestBean noticiaRequest = gson.fromJson(request, NoticiasRequestBean.class);
		
		NoticiaResponseBean response = new NoticiaResponseBean();
		response.setMensaje("");
		response.setRespuesta(0);
		
		if(!this.validarToken(noticiaRequest.getToken(),noticiaRequest.getUsuario())) {
			response.setMensaje("Token no valido");
			return gson.toJson(response);
		}
		
		try {
			Dao<NoticiaAvisoBean,NoticiaAvisoBean> dao = DaoFactory.getDao("NoticiaAviso", "ar.edu.ubp.das");
			
			List<NoticiaAvisoBean> novedades = dao.select(null);
			response.setNovedades(novedades);
								
			response.setRespuesta(1);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gson.toJson(response);
	}
	
	private boolean validarToken(String token, String usuario) {
		
		boolean any = false;
		Connection conn;
		CallableStatement stmt;
		try {
			Context environment = Context.class.cast((new InitialContext()).lookup("java:comp/env"));	
			Class.forName((String)environment.lookup("ProviderName"));
			conn=DriverManager.getConnection((String)environment.lookup("ConnectionString"));
			conn.setAutoCommit(false);
			try {
				
				stmt = conn.prepareCall("{CALL dbo.VALIDAR_TOKEN(?,?)}");
				stmt.setString(1, token);
				stmt.setString(2, usuario);
				
				ResultSet resultSet = stmt.executeQuery();
				any = resultSet.next();
				
				stmt.close();
				}catch (SQLException e) {
					// TODO: handle exception		
					e.printStackTrace();
				}finally {
					conn.close();
				}
		} catch (ClassNotFoundException | NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return any;
	}
}
	
