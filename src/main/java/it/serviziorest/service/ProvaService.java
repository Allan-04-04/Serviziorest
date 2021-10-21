package it.serviziorest.service;

import java.util.List;

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

import it.serviziorest.dao.ProvaDao;
import it.serviziorest.entity.Prova;

@Path("/prova")
public class ProvaService {
	private ProvaDao provaDao = new ProvaDao();

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<Prova> utentiLista = provaDao.findAll();
		if (!utentiLista.isEmpty()) {
			return Response.ok(utentiLista).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@Path("/get/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findUtenteById(@PathParam("id") int id) {

		Prova utente = provaDao.findById(id);
		return Response.ok(utente).build();
	}

	@Path("/delete/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) {
		provaDao.delete(id);
		return Response.ok("ok").build();
	}

	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Prova utente) {
		provaDao.update(utente);
		return Response.ok(utente).build();
	}

	@POST
	@Path("/post")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(Prova utente) {
		boolean result = provaDao.insert(utente);
		if (result) {
			return Response.ok(utente).build();
		} else {
			return Response.notModified().build();
		}

	}
}
