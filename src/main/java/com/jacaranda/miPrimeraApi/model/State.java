package com.jacaranda.miPrimeraApi.model;

import java.util.List;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="provincias")
public class State {

	@Id
	@Column(name="codpro")
	private String codState;
	
	@Column(name="nombre")
	private String name;
	
	@OneToMany(mappedBy = "province")
    private List<Town> listTown;
	
	public State() {
		super();
	}

	public String getCodState() {
		return codState;
	}

	public void setCodState(String codState) {
		this.codState = codState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Town> getListTown() {
		return listTown;
	}

	public void setListTown(List<Town> listTown) {
		this.listTown = listTown;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codState);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		return Objects.equals(codState, other.codState);
	}
}
