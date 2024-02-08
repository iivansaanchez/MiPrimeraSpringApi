package com.jacaranda.miPrimeraApi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.miPrimeraApi.model.State;
import com.jacaranda.miPrimeraApi.model.Town;
import com.jacaranda.miPrimeraApi.model.TownDTO;
import com.jacaranda.miPrimeraApi.repository.TownRepository;

@Service
public class TownService {
	
	@Autowired
	private TownRepository townRepository;
	
	/**
	 * Metodo para obtener un pueblo por su identificador
	 * @param id --> Identificador unico de cada pueblo
	 * @return
	 */
	public Town get(String id) {
		return townRepository.findById(id).orElse(null);
	}
	
	/**
	 * Con este metodo pretendemos obtener un pueblo por su identificador
	 * @param id --> Identificador del pueblo
	 * @return Si el identificador pertenece a un pueblo que existe en la base de datos te lo mostrara sino te
	 * devolvera null
	 */
	public TownDTO getById(String id) {
		Town town = townRepository.findById(id).orElse(null);
		return town != null ? new TownDTO(town.getCodpue(), town.getName(), town.getProvince().getCodState(), town.getProvince().getName()) : null;
	}
	/**
	 * Este metodo sirve para obtener todos los pueblos con el formato de TownDTO
	 * @return devuelve una lista de Pueblos y sus provincias
	 */
	public List<TownDTO> getAll(){
		List<TownDTO> result = new ArrayList<TownDTO>();
		
		for(Town town: townRepository.findAll()) {
			TownDTO tdto = new TownDTO(town.getCodpue(), town.getName(), town.getProvince().getCodState(), town.getProvince().getName());
			result.add(tdto);
		}
		
		return result;
	}
	
	/**
	 * Metodo para añadir pueblos a la BBDD
	 * @param town --> Pueblo a añadir
	 * @return Si se ha añadido a la base de datos devolvera el objeto sino devolvera null
	 */
	public Town save(Town town) {
		return townRepository.save(town);
	}
	
	/**
	 * Metodo para editar un pueblo ya creado y almacenado en la BBDD
	 * @param town --> Pueblo a editar
	 * @return Si se ha actualizado devolvera el objeto sino devolvera null
	 */
	public Town update(Town town) {
		Town result;
		if(this.get(town.getCodpue())==null) {
			result = null;
			
		}else {
			result = this.save(town);
		}
		
		return result;
	}
	
	/**
	 * Metodo para borrar pueblo de la BBDD el cual se buscara por su identificador
	 * @param cod --> Identificador unico de cada pueblo
	 * @return Si se ha borrado devolvera true sino devolvera false
	 */
	public Boolean delete(String cod) {
		Boolean result = true;
		if(this.get(cod)==null) {
			result = false;
			
		}else {
			 this.townRepository.delete(this.get(cod));
		}
		
		return result;
	}

	
	
	
	/**
	 * Este metodo sirve para añadir un pueblo a la base de datos. En este metodo pasaremos por parametros un TownDTO
	 * el cual nos proporcionara los datos para crear un pueblo y si alguno de esos datos no son validos no se creara
	 * el pueblo y por lo tanto no se añadira a la base de datos
	 * @param townDTO --> Objeto con los datos necesarios para formar un pueblo
	 * @return Devolvera un townDTO en el caso de que el codigo del pueblo sea diferente de nulo o vacio
	 * y que el codigo de la provincia sea diferente de nulo o vacio asi evitando devolver un pueblo sin identificador
	 * y sin provincia asignada. Sino se dan esas condiciones devolvera null y eso significara que no se ha añadido de manera
	 * correcta a la base de datos
	 */
	public TownDTO addTown(TownDTO townDTO) {
		//Creamos un objeto Town
		Town town = new Town();
		//Asignamos al pueblo el codigo y el nombre del TownDTO proporcionado
		//Debemos validar que el codigo del pueblo no es nulo sin vacio porque sino no podremos añadirlo a la base de datos
		if(townDTO.getCodTown()!= null && !townDTO.getCodTown().isEmpty()) {
			town.setCodpue(townDTO.getCodTown());
			town.setName(townDTO.getName());
			//Ahora tenemos que crear un objeto State debemos comprobar que el codigo Provincia de TownDTO no es null ni esta vacio
			if(townDTO.getCodState() != null && !townDTO.getCodState().isEmpty()) {
				State state = new State();
				state.setCodState(townDTO.getCodState());
				//Asignamos al pueblo al provincia
				town.setProvince(state);
				//Una vez que tenemos el pueblo con los datos asignados lo añadimos a la base de datos y lo almacenamos en el objeto town
				town = townRepository.save(town);
			}
			
		}
		//Creamos un nuevo townDTO con los datos de Town para devolver el objeto validado
		return townDTO.getCodTown()!= null && !townDTO.getCodTown().isEmpty()
				&& townDTO.getCodState() != null && !townDTO.getCodState().isEmpty()?	
				new TownDTO(town.getCodpue(), town.getName(), town.getProvince().getCodState(),
						town.getProvince().getName()) : null;
	}
	
	/**
	 * Con este metodo pretendemos actualizar un objeto existente en la base de datos
	 * @param id --> Identificador del pueblo a actualizar
	 * @param townDTO --> Objeto creado para modificar objeto Town
	 * @return Si el identificador corresponde a un objeto existente devolveremos el objeto modificado en forma de TownDTO
	 * si el identificador no corresponde a ningun objeto devolveremos null
	 */
	public TownDTO updateTown(String id, TownDTO townDTO) {
		//Creamos un objeto Town y lo igualamos al objeto que tiene ese identificador en la base de datos
		Town town = townRepository.findById(id).orElse(null);
		//Vemos si existe algun pueblo con ese identificador
		if( town != null) {
			//Una vez que hemos comprobado que existe modificamos el objeto
			town.setCodpue(townDTO.getCodTown());
			town.setName(townDTO.getName());;
			//Una vez modificado lo añadimos de nuevo a la base de datos
			townRepository.save(town);
			
			//Para visualizar el objeto que hemos añadido creamos un nuevo TownDTO
			return new TownDTO(town.getCodpue(), town.getName(), town.getProvince().getCodState(), town.getProvince().getName());
		}
		//En el caso de que town sea igual a null devolveremos null y eso indicara que no se ha realizado la operacion con exito
		return null;
	}
	
}
	

