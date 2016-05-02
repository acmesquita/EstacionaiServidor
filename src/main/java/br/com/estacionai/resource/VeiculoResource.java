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

import br.com.estacionai.controller.VeiculoController;
import br.com.estacionai.modelo.Veiculo;

import com.google.gson.GsonBuilder;

/**
 * VeiculoResource - GET, POST, PUT, DELETE 
 * @author Catha Mesquita
 *
 */

@Path("veiculos")
public class VeiculoResource {
	
	/**
	 * Método GET do protocolo HTTP para listar todos os veiculos cadastrados
	 * @return Response (ok)
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVeiculos(){
		List<Veiculo> veiculos = VeiculoController.getTodosVeiculos();
		String json = new GsonBuilder().setPrettyPrinting().create().toJson(veiculos);
		return Response.ok(json).build();		
	}
	/**
	 * Método GET do protocolo HTTP para listar o veiculo solicitado por Id
	 * @return Response (ok) || (not found)
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVeiculo(@PathParam("id")Long id){
		Veiculo veiculo = VeiculoController.getVeiculo(id);
		if(veiculo == null){
			return Response.status(Status.NOT_FOUND).build();
		}
		String json = new GsonBuilder().setPrettyPrinting().create().toJson(veiculo);
		return Response.ok(json).build();		
	}
	/**
	 * Método POST do protocolo HTTP para criar um novo veiculo contido em um json
	 * @param jsonVeiculo
	 * @return Response (created(location)) || (not_modified)
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createdVeiculo(String jsonVeiculo){
		try{
			Veiculo veiculo = new GsonBuilder().setPrettyPrinting().create().fromJson(jsonVeiculo, Veiculo.class);
			veiculo = VeiculoController.createdVeiculo(veiculo);
			URI location = new URI("http://localhost:8081/estacionai/webapi/veiculos/"+veiculo.getId());
			return Response.created(location).build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.NOT_MODIFIED).build();			
		}
	}
	
	/**
	 * Método PUT do protocolo HTTP para alterar um veiculo com as informações contidas em um json
	 * @param jsonVeiculo
	 * @return
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modifiedVeiculo(String jsonVeiculo){
		try{
			Veiculo veiculo = new GsonBuilder().setPrettyPrinting().create().fromJson(jsonVeiculo, Veiculo.class);
			VeiculoController.modifiedVeiculo(veiculo);
			return Response.ok().build();
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(Status.NOT_MODIFIED).build();			
		}
	}
	/**
	 * Método DELETE do protocolo HTTP para deletar o veiculo solicitado por Id
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteVeiculo(@PathParam("id") Long id){
		try{
			VeiculoController.deleteVeiculo(id);
			return Response.ok().build();
		}catch(Exception e){
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}
