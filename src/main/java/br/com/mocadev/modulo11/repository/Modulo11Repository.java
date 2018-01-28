package br.com.mocadev.modulo11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mocadev.modulo11.model.Modulo11;

@Repository
public interface Modulo11Repository extends JpaRepository<Modulo11, Long> {

}
