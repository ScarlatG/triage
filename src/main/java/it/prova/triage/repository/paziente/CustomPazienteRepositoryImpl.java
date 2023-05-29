package it.prova.triage.repository.paziente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import it.prova.triage.model.Paziente;

public class CustomPazienteRepositoryImpl implements CustomPazienteRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Paziente> findByExample(Paziente example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("from Paziente p where p.id = p.id ");

		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" p.nome  like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" p.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCodiceFiscale())) {
			whereClauses.add(" p.codiceFiscale like :codiceFiscale");
			paramaterMap.put("codiceFiscale", "%" + example.getCodiceFiscale() + "%");
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Paziente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Paziente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}