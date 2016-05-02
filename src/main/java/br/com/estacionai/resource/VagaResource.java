package br.com.estacionai.resource;

import java.net.URI;
import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.estacionai.controller.VagaController;
import br.com.estacionai.modelo.Vaga;

import com.google.gson.GsonBuilder;

/**
 * VagaResource - GET, POST, PUT, DELETE 
 * @author Catha Mesquita
 *
 */
@Path("vagas")
public class VagaResource {
	
	/**
	 * Método GET do protocolo HTTP para listar todas as vagas 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTodasVagas(){
		List<Vaga> vagas= VagaController.getTodasVagas();
		String json = new GsonBuilder().setPrettyPrinting().create().toJson(vagas);
		return Response.ok(json).build();		
	}
	/**
	 * Método GET do protocolo HTTP para listar a vaga solicitada por Id
	 * @param id
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVaga(@PathParam("id") Long id){
		Vaga vaga = VagaController.getVaga(id);
		if(vaga == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		String json = new GsonBuilder().setPrettyPrinting().create().toJson(vaga);
		return Response.ok(json).build();		
	}
	
	/**
	 * Método GET do protocolo HTTP para emitir um ticket solicitada por Id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/ticket/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVagaComTicket(@PathParam("id") Long id){
		Vaga vaga = VagaController.emitirTicket(id);
		if(vaga == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		String json = new GsonBuilder().setPrettyPrinting().create().toJson(vaga);
		return Response.ok(json).build();		
	}
	
	/**
	 * Método POST do protocolo HTTP para criar uma nova vaga contido em um json
	 * @param jsonVaga
	 * @return Response (created(location)) || (not_modified)
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createdVaga(String jsonVaga){
		try{
			Vaga vaga = new GsonBuilder().setPrettyPrinting().create().fromJson(jsonVaga, Vaga.class);
			vaga = VagaController.createdVaga(vaga);
			URI location = new URI("http://localhost:8081/estacionai/webapi/veiculos/"+vaga.getId());
			return Response.created(location).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.NOT_MODIFIED).build();			
		}
	}
	
	/**
	 * Método PUT do protocolo HTTP para alterar uma vaga com as informações contidas em um json
	 * @param jsonVaga
	 * @return
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modifiedVaga(String jsonVaga){
		try{
			Vaga vaga = new GsonBuilder().setPrettyPrinting().create().fromJson(jsonVaga, Vaga.class);
			VagaController.modifiedVaga(vaga);
			return Response.ok().build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.NOT_MODIFIED).build();			
		}
	}
	
	/**
	 * Método DELETE do protocolo HTTP para deletar um vaga solicitado por Id
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteVaga(@PathParam("id") Long id){
		try{
			VagaController.deleteVaga(id);
			return Response.ok().build();
		}catch(Exception e){
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}
