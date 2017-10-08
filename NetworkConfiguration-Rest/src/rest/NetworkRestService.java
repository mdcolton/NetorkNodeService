package rest;

import java.net.URI;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.network.Node;

import network.NetworkServiceLocal;

@Path("/nodes")
public class NetworkRestService {
	
    @EJB
    private NetworkServiceLocal networkService;
	
    @Path("/count")
	@GET()
    @RolesAllowed({"SysAdmin","Operator","Temp"})
	@Produces({javax.ws.rs.core.MediaType.TEXT_XML,javax.ws.rs.core.MediaType.APPLICATION_JSON})
	public Response getNodeCount(){
		Long count = networkService.getNodeCount();
		return Response.ok(count.toString()).build();
	}
    
    @Path("/fill")
	@GET()
    @RolesAllowed("SysAdmin")
	@Produces({javax.ws.rs.core.MediaType.TEXT_XML,javax.ws.rs.core.MediaType.APPLICATION_JSON})
	public Response populateTable(){
		networkService.populateTable();
		return Response.ok().build();
	}
    
    @Path("/all")
	@GET()
    @RolesAllowed({"SysAdmin","Operator","Temp"})
	@Produces({javax.ws.rs.core.MediaType.TEXT_XML,javax.ws.rs.core.MediaType.APPLICATION_JSON})
	public Response getAllNodes(){
		return Response.ok(new GenericEntity<List<Node>>((List<Node>) networkService.getAllNodes()){}).build();
	}
    
    @Path("/{id}")
    @GET()
    @RolesAllowed({"SysAdmin","Operator","Temp"})
    @Produces({javax.ws.rs.core.MediaType.TEXT_XML,javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public Response getNodeById(@PathParam("id") int id){
    	Node node = networkService.getNodeById(id);
    	return Response.ok(node).build();
    }
    
	@POST
	@Path("/add")
	@RolesAllowed({"SysAdmin","Operator"})
	@Consumes({"application/json",javax.ws.rs.core.MediaType.APPLICATION_JSON})
	public Response createNodeInJSON(Node node) {
		networkService.addNode(node);
		URI uri = UriBuilder.fromResource(NetworkRestService.class).build();
		return Response.created(uri).entity(node).build();
	}
	
	@PUT
	@Path("/edit")
	@RolesAllowed({"SysAdmin","Operator"})
	@Consumes({"application/json",javax.ws.rs.core.MediaType.APPLICATION_JSON})
	public Response editNodeInJSON(Node node) {
		networkService.updateNodeInDatabase(node);
		return Response.ok().build();
	}
	
    @Path("/remove/{id}")
	@DELETE()
    @RolesAllowed("SysAdmin")
	@Produces({javax.ws.rs.core.MediaType.TEXT_XML,javax.ws.rs.core.MediaType.APPLICATION_JSON})
	public Response removeNodeById(@PathParam("id") int id){
    	networkService.removeNodeById(id);
		return Response.ok().build();
	}
}
