package it.prova.triage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.triage.model.Paziente;
import it.prova.triage.repository.paziente.PazienteRepository;

@Service
@Transactional
public class PazienteServiceImpl implements PazienteService {

	@Autowired
	private PazienteRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Paziente> listAll() {
		return (List<Paziente>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Paziente> findByExample(Paziente example) {
		return repository.findByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public Paziente findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Paziente inserisciNuovo(Paziente input) {
		// controllare che l' id non sia valorizzato prima di inserire
		return repository.save(input);
	}

	@Override
	public Paziente aggiorna(Paziente input) {
		// controllare se l'input ha un id
		// controllare che esista un paziente con l'id uguale all' id dell'input
		return repository.save(input);
	}

	@Override
	public void rimuovi(Long id) {
		// controllare se esiste un paziente con quell'id
		repository.deleteById(id);
	}

	@Override
	public Paziente iniziaVisita(Long id, String codiceDottore) {
		// verifiche prima dell'aggiornamento
		return null;
	}

	@Override
	public Paziente ricovera(Long id) {
		// verifiche prima dell'aggiornamento
		return null;
	}

	@Override
	public Paziente dimetti(Long id) {
		// verifiche prima dell'aggiornamento
		return null;
	}

}