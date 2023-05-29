package it.prova.triage.repository.paziente;

import java.util.List;

import it.prova.triage.model.Paziente;

public interface CustomPazienteRepository {

	List<Paziente> findByExample(Paziente example);
}