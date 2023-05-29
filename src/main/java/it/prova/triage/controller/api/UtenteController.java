package it.prova.triage.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.triage.dto.UtenteDTO;
import it.prova.triage.service.UtenteService;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;

	@PutMapping("/cambiaStato/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void cambiaStato(@PathVariable(name = "id", required = true) Long id) {
		utenteService.changeUserAbilitation(id);
	}

	@GetMapping
	public List<UtenteDTO> listAll() {
		return utenteService.listAllUtenti().stream().map(utente -> UtenteDTO.buildUtenteDTOFromModel(utente))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public UtenteDTO caricaSingolo(@PathVariable(name = "id", required = true) Long id) {
		return UtenteDTO.buildUtenteDTOFromModel(utenteService.caricaSingoloUtente(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UtenteDTO inserisciNuovoUtente(@Valid @RequestBody UtenteDTO input) {

		return UtenteDTO.buildUtenteDTOFromModel(utenteService.inserisciNuovo(input.buildUtenteModel(true)));
	}

	@PutMapping("/{id}")
	public UtenteDTO aggiornaUtente(@Valid @RequestBody UtenteDTO input) {
		return UtenteDTO.buildUtenteDTOFromModel(utenteService.aggiorna(input.buildUtenteModel(true)));
	}

	@GetMapping("/search")
	public List<UtenteDTO> ricerca(@Valid @RequestBody UtenteDTO input) {
		return utenteService.findByExample(input.buildUtenteModel(true)).stream().map(utente -> {
			return UtenteDTO.buildUtenteDTOFromModel(utente);
		}).collect(Collectors.toList());
	}
}