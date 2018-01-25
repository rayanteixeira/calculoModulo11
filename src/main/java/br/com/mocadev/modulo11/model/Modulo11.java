package br.com.mocadev.modulo11.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Modulo11 {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	
	@NotEmpty
	String numero;

	public Modulo11(long id, String numero) {
		this.id = id;
		this.numero = numero;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modulo11 other = (Modulo11) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	
	
	
	
}
