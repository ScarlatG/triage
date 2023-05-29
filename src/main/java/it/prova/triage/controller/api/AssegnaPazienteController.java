package it.prova.triage.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import it.prova.triage.dto.DottoreRequestDTO;
import it.prova.triage.dto.DottoreResponseDTO;
import it.prova.triage.dto.PazienteDTO;
import it.prova.triage.model.Paziente;
import it.prova.triage.service.PazienteService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/assegnaPaziente")
public class AssegnaPazienteController {

	@Autowired
	private WebClient webClient;

	@Autowired
	private PazienteService service;

	@PutMapping("id")
	public PazienteDTO assegnaPaziente(@PathVariable(required = true, name = "id") Long id,
			@RequestBody DottoreRequestDTO dottore) {

		DottoreResponseDTO dottoreAggiornato = webClient.get().uri("/findByCodice/" + dottore.getCodiceDottore())
				.retrieve().bodyToMono(DottoreResponseDTO.class).block();
		// gestire dottore non trovato, in servizio o disponibile
		Paziente paziente = service.iniziaVisita(id, dottoreAggiornato.getCodiceDottore());

		dottoreAggiornato.setInVisita(true);
		dottoreAggiornato.setCodFiscalePazienteAttualmenteInVisita(paziente.getCodiceFiscale());

		webClient.put().uri("/aggiornaStato").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(dottoreAggiornato), DottoreResponseDTO.class).retrieve()
				.bodyToMono(DottoreResponseDTO.class).block();

		return PazienteDTO.buildDTOFromPazienteModel(paziente);
	}

}