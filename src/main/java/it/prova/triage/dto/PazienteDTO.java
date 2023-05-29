package it.prova.triage.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import it.prova.triage.model.Paziente;
import it.prova.triage.model.StatoPaziente;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class PazienteDTO {

	private Long id;
	@NotBlank(message = "{nome.notblank}")
	private String nome;
	@NotBlank(message = "{cognome.notblank}")
	private String cognome;
	@NotBlank(message = "{codiceFiscale.notblank}")
	private String codiceFiscale;
	private LocalDate dataRegistrazione;
	private StatoPaziente stato;

	public PazienteDTO(Long id, String nome, String cognome, String codiceFiscale, LocalDate dataRegistrazione,
			StatoPaziente stato) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataRegistrazione = dataRegistrazione;
		this.stato = stato;
	}

	public Paziente buildPazienteModel() {
		return new Paziente(this.id, this.nome, this.cognome, this.codiceFiscale, this.dataRegistrazione, this.stato);
	}

	public static PazienteDTO buildDTOFromPazienteModel(Paziente model) {
		return new PazienteDTO(model.getId(), model.getNome(), model.getCognome(), model.getCodiceFiscale(),
				model.getDataRegistrazione(), model.getStato());
	}
}
