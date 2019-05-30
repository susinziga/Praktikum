package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import EJB.UporabnikEJB;
import projekt.Uporabnik;

@Path("/upoti")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UporabnikRest {
	@EJB
	private UporabnikEJB ejb;

	@Context
    private UriInfo context;

	
	/*Omogocen POST uporabnik*/
	@POST
	@Path("/upo/{id}")
	public Response vrniKnjigoPost(@PathParam("id") String idS) {
		int id = Integer.parseInt(idS);
		Uporabnik upo = ejb.najdId(id);
		
			return Response.ok(upo).build();
		
	}
	
	/*Omogocen POST uporabnik*/
	@POST
	@Path("/upot/")
	public Response prijava() {
	//	int id = Integer.parseInt(idS);
		
		int id=0;
		List <Uporabnik> vsi = ejb.vrniVse();
		
		
			return Response.ok(vsi).build();
		
	}
}