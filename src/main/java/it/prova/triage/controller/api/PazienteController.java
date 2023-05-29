package it.prova.triage.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.triage.dto.PazienteDTO;
import it.prova.triage.service.PazienteService;

@RestController
@RequestMapping("/api/paziente")
public class PazienteController {

	@Autowired
	private PazienteService service;

	@GetMapping
	public List<PazienteDTO> listAll() {
		return service.listAll().stream().map(paziente -> PazienteDTO.buildDTOFromPazienteModel(paziente))
				.collect(Collectors.toList());
	}

	@GetMapping("/search")
	public List<PazienteDTO> ricerca(@Valid @RequestBody PazienteDTO input) {
		return service.findByExample(input.buildPazienteModel()).stream()
				.map(paziente -> PazienteDTO.buildDTOFromPazienteModel(paziente)).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public PazienteDTO caricaSingolo(@PathVariable(name = "id", required = true) Long id) {
		return PazienteDTO.buildDTOFromPazienteModel(service.findById(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PazienteDTO inserisciNuovoPaziente(@Valid @RequestBody PazienteDTO input) {

		return PazienteDTO.buildDTOFromPazienteModel(service.inserisciNuovo(input.buildPazienteModel()));
	}

	@PutMapping("/{id}")
	public PazienteDTO aggiornaPaziente(@Valid @RequestBody PazienteDTO input) {
		return PazienteDTO.buildDTOFromPazienteModel(service.aggiorna(input.buildPazienteModel()));
	}

	@PutMapping("/ricovera/{idPaziente}")
	public PazienteDTO ricovera(@PathVariable(name = "idPaziente", required = true) Long id) {
		return PazienteDTO.buildDTOFromPazienteModel(service.ricovera(id));
	}

	@PutMapping("/dimetti/{idPaziente}")
	public PazienteDTO dimetti(@PathVariable(name = "idPaziente", required = true) Long id) {
		return PazienteDTO.buildDTOFromPazienteModel(service.ricovera(id));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void rimuovi(@PathVariable(name = "id", required = true) Long id) {
		service.rimuovi(id);
	}
}