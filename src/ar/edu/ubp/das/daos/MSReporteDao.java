package ar.edu.ubp.das.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ar.edu.ubp.das.bean.ReporteBean;
import ar.edu.ubp.das.db.Dao;

public class MSReporteDao extends Dao<ReporteBean, ReporteBean>{

	@Override
	public ReporteBean delete(ReporteBean arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReporteBean insert(ReporteBean reporte) throws SQLException {
		this.connect();
		
		this.setProcedure("dbo.INSERTAR_REPORTE(?,?,?,?,?)");
		
		this.setParameter(1, reporte.getIdServicio());
		this.setParameter(2, reporte.getTotalHoy());
		this.setParameter(3, reporte.getTotalCanceladasHoy());
		this.setParameter(4, reporte.getFechaDesde());
		this.setParameter(5, reporte.getFechaHasta());
		
		this.executeUpdate();
		
		return reporte;
	}

	@Override
	public ReporteBean make(ResultSet arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReporteBean> select(ReporteBean arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReporteBean update(ReporteBean arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean valid(ReporteBean arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
