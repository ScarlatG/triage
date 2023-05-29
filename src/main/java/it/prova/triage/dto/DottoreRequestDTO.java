package it.prova.triage.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DottoreRequestDTO {
	@NotBlank(message = "{nome.notblank}")
	private String nome;
	@NotBlank(message = "{cognome.notblank}")
	private String cognome;
	@NotBlank(message = "{codiceDottore.notblank}")
	private String codiceDottore;

	public DottoreRequestDTO(String nome, String cognome, String codiceDottore) {

		this.nome = nome;
		this.cognome = cognome;
		this.codiceDottore = codiceDottore;
	}
}
