package com.example.demo.repositorys;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.models.Convidado;
import com.example.demo.models.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
	Iterable<Convidado> findByEvento(Evento evento);
	Convidado findByRg(long rg);
}
