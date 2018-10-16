package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Convidado;
import com.example.demo.models.Evento;
import com.example.demo.repositorys.ConvidadoRepository;
import com.example.demo.repositorys.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository er;
	@Autowired
	private ConvidadoRepository cr;
	
	@RequestMapping(value="/evento", method=RequestMethod.GET)
	public String evento() {
		return "evento/formevento";
	}
	
	@RequestMapping(value="/evento", method=RequestMethod.POST)
	public String criarEvento(Evento evento) {
		er.save(evento);
		return "redirect:/evento";
	}
	
	@RequestMapping("/listarEventos")
	public ModelAndView listarEventos() {
		
		ModelAndView mv = new ModelAndView("evento/listaeventos");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		
		return mv;
	}
	
	@RequestMapping(value="/evento/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesevento");
		mv.addObject("evento", evento);
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);
		return mv;
	}
	
	@RequestMapping(value="/evento/{codigo}", method=RequestMethod.POST)
	public String addConvidado(@PathVariable("codigo") long codigo, Convidado convidado) {
		
		Evento evento = er.findByCodigo(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);
		
		return "redirect:/evento/{codigo}";
	}
	
	@RequestMapping(value="/deletarConvidado/{rg}")
	public String excluirConvidado(@PathVariable("rg") long rg) {
		
		Convidado convidado = cr.findByRg(rg);
		long evento_id = convidado.getEvento().getCodigo();
		cr.delete(convidado);
		return "redirect:/evento/"+evento_id;
	}
	
}
