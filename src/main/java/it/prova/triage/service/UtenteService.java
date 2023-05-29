package it.prova.triage.service;

import java.util.List;

import it.prova.triage.model.StatoUtente;
import it.prova.triage.model.Utente;

public interface UtenteService {

	public List<Utente> listAllUtenti();

	public Utente caricaSingoloUtente(Long id);

	public Utente caricaSingoloUtenteConRuoli(Long id);

	public Utente aggiorna(Utente utenteInstance);

	public Utente inserisciNuovo(Utente utenteInstance);

	public Utente inserisciNuovo(Utente utenteInstance, StatoUtente stato);

	public void rimuovi(Long idToDelete);

	public List<Utente> findByExample(Utente example);

	public Utente findByUsernameAndPassword(String username, String password);

	public Utente eseguiAccesso(String username, String password);

	public void changeUserAbilitation(Long utenteInstanceId);

	public Utente findByUsername(String username);

	public void cambiaPassword(String confermaNuovaPassword, String name);

	public void cambiaPassword(Long idUtente);

}