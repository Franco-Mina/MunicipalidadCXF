package ar.edu.ubp.das.bean;

import java.sql.Date;

public class EstadisticaBean {

	private int id;
	private int idServicio;
	private int solicitadas;
	private int canceladas;
	private Date fecha;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}
	public int getSolicitadas() {
		return solicitadas;
	}
	public void setSolicitadas(int solicitadas) {
		this.solicitadas = solicitadas;
	}
	public int getCanceladas() {
		return canceladas;
	}
	public void setCanceladas(int canceladas) {
		this.canceladas = canceladas;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
