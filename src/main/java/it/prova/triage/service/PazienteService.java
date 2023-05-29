package it.prova.triage.service;

import java.util.List;

import it.prova.triage.model.Paziente;

public interface PazienteService {

	List<Paziente> listAll();

	List<Paziente> findByExample(Paziente example);

	Paziente findById(Long id);

	Paziente inserisciNuovo(Paziente input);

	Paziente aggiorna(Paziente input);

	void rimuovi(Long id);

	Paziente iniziaVisita(Long id, String CodiceDottore);

	Paziente ricovera(Long id);

	Paziente dimetti(Long id);

}