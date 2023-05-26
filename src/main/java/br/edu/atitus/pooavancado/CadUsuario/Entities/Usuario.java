package br.edu.atitus.pooavancado.CadUsuario.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario extends GenericEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = true, length = 200)
	private String email;
	
	@Column(nullable = false)
	private boolean status;
	
	@ManyToOne
	@JoinColumn(nullable = false, name ="id_departamento")
	private Departamento departamento;
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}


}