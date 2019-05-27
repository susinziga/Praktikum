package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import EJB.IKnjigomatEJB;
import EJB.KnjigomatEJB;

import projekt.Knjigomat;


@Path("/masina")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KnjigomatRest {
	
	@EJB
	private IKnjigomatEJB ejb;

	@Context
    private UriInfo context;
	
	@GET
	public Response vrniVseKnjigomate() {
		return Response.ok(ejb.vrniVse()).build();
	}

	/*Omogocen GET knjiga*/	
	@GET
	@Path("/vsi/")
	public Response vrniKnjigo() {
		
		List<Knjigomat> vsi = ejb.vrniVse();
		if (vsi != null) {
			return Response.ok(vsi).build();
		} else {
			return Response.status(403).entity("KnjigeNiMogoceNajtiException").build();
		}
	}
	
}
