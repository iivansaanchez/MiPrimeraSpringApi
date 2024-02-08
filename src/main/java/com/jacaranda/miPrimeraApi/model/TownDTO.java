package com.jacaranda.miPrimeraApi.model;

public class TownDTO {
	
	private String codTown;
	private String name;
	private String state;
	private String codState;
	
	public TownDTO() {
		super();
	}
	
	public TownDTO(String codTown, String name, String state, String codState) {
		super();
		this.codTown = codTown;
		this.name = name;
		this.state = state;
		this.codState = codState;
	}

	public String getCodTown() {
		return codTown;
	}
	public void setCodTown(String codTown) {
		this.codTown = codTown;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCodState() {
		return codState;
	}
	public void setCodState(String codState) {
		this.codState = codState;
	}
}
