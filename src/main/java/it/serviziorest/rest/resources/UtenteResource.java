package it.serviziorest.rest.resources;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import it.serviziorest.dao.UtenteDao;

import it.serviziorest.entity.Utente;

@Path("/utente")
public class UtenteResource {
	private UtenteDao utenteDao = new UtenteDao();

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Utente> utentiLista = utenteDao.getAll();
		return Response.ok(utentiLista).build();
	}

	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(Utente utente)  {
		if (utente != null) {
			utenteDao.insert(utente);
			return Response.ok(utente).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Utente utente) {
		if (utente != null) {
			utenteDao.update(utente);
			return Response.ok(utente).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) {
		Utente utente = utenteDao.delete(id);
		return Response.ok(utente).build();

	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		Utente utente = utenteDao.get(id);
		return Response.ok(utente).build();
	}

}
