package it.prova.triage.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import it.prova.triage.dto.DottoreRequestDTO;
import it.prova.triage.dto.DottoreResponseDTO;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/dottore")
public class DottoreController {

	@Autowired
	private WebClient webClient;

	@GetMapping
	public List<DottoreResponseDTO> listAll() {
		List<DottoreResponseDTO> result = (List<DottoreResponseDTO>) webClient.get().uri("/").retrieve()
				.bodyToFlux(DottoreResponseDTO.class).buffer().blockLast();
		return result;
	}

	@GetMapping("/{codiceDottore}")
	public DottoreResponseDTO findByCodice(
			@PathVariable(required = true, name = "codiceDottore") String codiceDottore) {
		return webClient.get().uri("/findByCodice/" + codiceDottore).retrieve().bodyToMono(DottoreResponseDTO.class)
				.block();
	}

	@PostMapping("/aggiungiDottore")
	@ResponseStatus(HttpStatus.CREATED)
	public DottoreResponseDTO censisciDottore(@Valid @RequestBody DottoreRequestDTO input) {
		DottoreResponseDTO result = webClient.post().uri("/")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(input), DottoreRequestDTO.class).retrieve().bodyToMono(DottoreResponseDTO.class)
				.block();
		return result;
	}

	@PutMapping("/aggiornaAnagrafica")
	@ResponseStatus(HttpStatus.OK)
	public DottoreRequestDTO aggiornaAnagraficaDottore(@Valid @RequestBody DottoreRequestDTO input) {
		DottoreRequestDTO result = webClient.put().uri("/aggiornaAnagrafica")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(input), DottoreRequestDTO.class).retrieve().bodyToMono(DottoreRequestDTO.class).block();
		return result;
	}

	@PutMapping("aggiornaStato")
	@ResponseStatus(HttpStatus.OK)
	public DottoreResponseDTO aggiornaStatoDottore(@RequestBody DottoreResponseDTO input) {
		DottoreResponseDTO result = webClient.put().uri("/aggiornaStato")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(input), DottoreResponseDTO.class).retrieve().bodyToMono(DottoreResponseDTO.class)
				.block();
		return result;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void rimuovi(@PathVariable(required = true, name = "id") Long id) {
		webClient.delete().uri("/" + id).retrieve().bodyToMono(DottoreResponseDTO.class).block();
	}
}