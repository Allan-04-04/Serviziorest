package it.serviziorest.dao;

import java.util.ArrayList;
import java.util.List;

import it.serviziorest.entity.Prova;

public class ProvaDao {
	private List<Prova> provaLista = new ArrayList<Prova>();

	public List<Prova> findAll() {
		provaLista.add(new Prova(1, "pippo", "rossi"));
		provaLista.add(new Prova(2, "paolo", "rossi"));
		provaLista.add(new Prova(3, "francesco", "rossi"));
		return provaLista;
	}

	public Prova findById(int id) {
		for (Prova utente : findAll()) {
			if (id == utente.getId())
				return utente;
		}
		return null;
	}

	public boolean insert(Prova prova) {
		return provaLista.add(prova);

	}

	public void update(Prova prova) {
		for (Prova pro : provaLista) {
			if (prova.getId() == (pro.getId())) {
				provaLista.remove(pro);
				provaLista.add(prova);
			}

		}

	}

	public void delete(int id) {
		for (Prova prova : provaLista) {
			if (prova.getId() == id) {
				provaLista.remove(prova);

			}

		}

	}

	public List<Prova> getProvaLista() {
		return provaLista;
	}

	public void setProvaLista(List<Prova> provaLista) {
		this.provaLista = provaLista;
	}

}
