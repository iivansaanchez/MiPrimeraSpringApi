package com.jacaranda.miPrimeraApi.model;

import java.util.ArrayList;
import java.util.List;

public class StateDTO {
	
	private String StateCod;
	private String StateName;
	private List<TownDTO> listTownOfState;

	public StateDTO() {
		super();
	}

	public StateDTO(String stateCod, String stateName, List<TownDTO> listTownOfState) {
		super();
		StateCod = stateCod;
		StateName = stateName;
		this.listTownOfState = listTownOfState;
	}
	
	public String getStateCod() {
		return StateCod;
	}
	public void setStateCod(String stateCod) {
		StateCod = stateCod;
	}
	public String getStateName() {
		return StateName;
	}
	public void setStateName(String stateName) {
		StateName = stateName;
	}
	public List<TownDTO> getListTownOfState() {
		return listTownOfState;
	}
	public void setListTownOfState(List<TownDTO> listTownOfState) {
		this.listTownOfState = listTownOfState;
	}
	
	public static List<StateDTO> convertToDTO(List<State> list){
		//En primer lugar creamos una lista de StateDTO
		List<StateDTO> res = new ArrayList<StateDTO>();
		
		//En segundo lugar recorremos la lista que tenemos de States para sacar la informacion que necesitamos
		
		for (State st: list) {
			//Creamos un StateDTO con los datos del State menos el pueblo que se añadira despues de obtenerlo
			StateDTO stateDto = new StateDTO(st.getCodState(), st.getName(), null);
			//Creamos una lista de TownDTO en la cual almacenaremos la informacion de los pueblos y su provincia
			List<TownDTO> towns = new ArrayList<TownDTO>();
			//Recorremos la lista de pueblos que tenemos en la provincia seleccionada
			for (Town town : st.getListTown()) {
				//Creamos por cada town un TownDTO
				TownDTO townDto = new TownDTO(town.getCodpue(), town.getName(), stateDto.getStateCod(), stateDto.getStateName());
				//Añadimos el townDto a la lista de Towns
				towns.add(townDto);
			}
			//Actualizamos la lista de pueblos del estado
			stateDto.setListTownOfState(towns);
			//Por ultimo añadimos a la lista de StateDto a la lista resultado la cual devolvemos
			res.add(stateDto);
		}
		return res;
	}
}
