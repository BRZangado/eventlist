package com.example.demo.repositorys;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, String>{
	Evento findByCodigo(long codigo);
}
