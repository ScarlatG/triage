package it.prova.triage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class DottoreResponseDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String codiceDottore;
	private String codFiscalePazienteAttualmenteInVisita;
	private Boolean inVisita;
	private Boolean inServizio;

	public DottoreResponseDTO(String codiceDottore, String codFiscalePazienteAttualmenteInVisita, Boolean inVisita,
			Boolean inServizio) {
		this.codiceDottore = codiceDottore;
		this.codFiscalePazienteAttualmenteInVisita = codFiscalePazienteAttualmenteInVisita;
		this.inVisita = inVisita;
		this.inServizio = inServizio;
	}

}