package ar.edu.ubp.das.ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import ar.edu.ubp.das.bean.ReporteBean;
import ar.edu.ubp.das.bean.ws.ReportesRequestBean;
import ar.edu.ubp.das.bean.ws.ReportesResponseBean;
import ar.edu.ubp.das.db.Dao;
import ar.edu.ubp.das.db.DaoFactory;

public class GuardarEstadisticas {

	public String guardarEstadisticas(String request) {
		
		ReportesResponseBean response = new ReportesResponseBean();
		
		response.setMensaje("");
		response.setRespuesta(0);
		Gson gson = new Gson();
		
		try {			
			ReportesRequestBean reporteRequest = gson.fromJson(request, ReportesRequestBean.class);
			
			if(reporteRequest.getToken()!= null && !reporteRequest.getToken().isEmpty()) {
								
				if(!this.validarToken(reporteRequest.getToken(),reporteRequest.getUsuario())) {
					response.setMensaje("Token no valido");
					response.setRespuesta(1);
					return gson.toJson(response);
				}
				
				Dao<ReporteBean,ReporteBean> dao = DaoFactory.getDao("Reporte", "ar.edu.ubp.das");
				
				for (ReporteBean reporte : reporteRequest.getReportes()) {
					dao.insert(reporte);
				}
				
				response.setRespuesta(1);				
			}			
		}catch (SQLException | JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMensaje(e.getMessage());
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
