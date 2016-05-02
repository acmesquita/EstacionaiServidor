package br.com.estacionai.resource;

import java.net.URI;
import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.estacionai.controller.EstacionamentoController;
import br.com.estacionai.controller.ResponsavelController;
import br.com.estacionai.modelo.Estacionamento;
import br.com.estacionai.modelo.Responsavel;
import br.com.estacionai.modelo.Usuario;

import com.google.gson.GsonBuilder;

/**
 * VagaResource - GET, POST, PUT, DELETE 
 * @author Catha Mesquita
 *
 */
@Path("responsavel")
public class ResponsavelResource {
	
	/**
	 * Método GET do protocolo HTTP para listar todos os responsaveis
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTodosResponsaveis(){
		List<Responsavel> responsaveis = ResponsavelController.getTodosResponsaveis();
		String json = new GsonBuilder().setPrettyPrinting().create().toJson(responsaveis);
		return Response.ok(json).build();		
	}
	/**
	 * Método GET do protocolo HTTP para listar um resposavel solicitada por Id
	 * @param id
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResponsavel(@PathParam("id") Long id){
		Responsavel responsavel = ResponsavelController.getResponsavel(id);
		if(responsavel == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		String json = new GsonBuilder().setPrettyPrinting().create().toJson(responsavel);
		return Response.ok(json).build();		
	}

	/**
	 * Método GET do protocolo HTTP para listar um resposavel solicitada por Id
	 * @param id
	 * @return
	 */
	@GET
	@Path("/autentica")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLoginSenha(String loginSenha){
		Usuario user = new GsonBuilder().create().fromJson(loginSenha, Usuario.class);
		Responsavel responsavel = ResponsavelController.autentica(user);
		if(responsavel != null){
			Estacionamento estacionamento = EstacionamentoController.getPorResponsavel(responsavel);
			if(estacionamento != null)return Response.ok(estacionamento).build();
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	/**
	 * Método POST do protocolo HTTP para criar uma nova vaga contido em um json
	 * @param jsonResponsavel
	 * @return Response (created(location)) || (not_modified)
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createdVaga(String jsonResponsavel){
		try{
			Responsavel responsavel = new GsonBuilder().setPrettyPrinting().create().fromJson(jsonResponsavel, Responsavel.class);
			responsavel = ResponsavelController.createdResponsavel(responsavel);
			URI location = new URI("http://localhost:8081/estacionai/webapi/responsavel/"+responsavel.getId());
			return Response.created(location).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();			
		}
	}
//	
//	/**
//	 * Método PUT do protocolo HTTP para alterar uma vaga com as informações contidas em um json
//	 * @param jsonVaga
//	 * @return
//	 */
//	@PUT
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response modifiedVaga(String jsonVaga){
//		try{
//			Vaga vaga = new GsonBuilder().setPrettyPrinting().create().fromJson(jsonVaga, Vaga.class);
//			VagaController.modifiedVaga(vaga);
//			return Response.ok().build();
//		}catch(Exception e){
//			e.printStackTrace();
//			return Response.status(Status.NOT_MODIFIED).build();			
//		}
//	}
//	
//	/**
//	 * Método DELETE do protocolo HTTP para deletar um vaga solicitado por Id
//	 * @param id
//	 * @return
//	 */
//	@DELETE
//	@Path("{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response deleteVaga(@PathParam("id") Long id){
//		try{
//			VagaController.deleteVaga(id);
//			return Response.ok().build();
//		}catch(Exception e){
//			return Response.status(Status.BAD_REQUEST).build();
//		}
//	}
}
