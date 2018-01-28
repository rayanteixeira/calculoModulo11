package br.com.mocadev.modulo11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mocadev.modulo11.model.Modulo11;
import br.com.mocadev.modulo11.repository.Modulo11Repository;

@Service
public class Modulo11Service {

	@Autowired
	private Modulo11Repository modulo11Repository;
	
	public List<Modulo11> findAll(){
		return modulo11Repository.findAll();
	}
	
	public Modulo11 findOne(Long id){
		return modulo11Repository.findOne(id);
	}
	
	public Modulo11 save(Modulo11 modulo11){
		return modulo11Repository.saveAndFlush(modulo11);
	}
	
	public void delete(Long id){
		modulo11Repository.delete(id);
	}
	
	public void deleteAll() {
		modulo11Repository.deleteAllInBatch();
	}
}
