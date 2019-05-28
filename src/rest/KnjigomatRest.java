package rest;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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


import EJB.KnjigomatEJB;
import projekt.Knjiga;
import projekt.Knjigomat;


@Path("/masina")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KnjigomatRest {
	
	@EJB
	private KnjigomatEJB ejb;

	@Context
    private UriInfo context;
	
	@GET
	public Response vrniVseKnjigomate() {
		return Response.ok(ejb.vrniVse()).build();
	}

	/*Omogocen GET knjiga*/	
	@POST
	@Path("/vsi/")
	@Produces("application/json")
	public Response iskanjeKnjige() throws IOException, ParseException {
		List <Knjigomat> najdene = ejb.vrniVse();
		List <String> vrni =new ArrayList<String>();
		for (Knjigomat k:najdene) {
			vrni.add(k.getLokacija());
		}
	
		
		
		if (najdene.size() > 0) {
			return Response.ok(vrni).build();
		} else {
			return Response.status(403).entity("KnjigeNiMogoceNajtiException").build();
		}
	}
	
}
