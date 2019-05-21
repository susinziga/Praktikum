package rest;

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

import projekt.Knjiga;
import projekt.KnjigaDao;

@Path("/knjige")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KnjigaRest {

	

		@EJB
		private KnjigaDao ejb;

		@Context
	    private UriInfo context;
		
		@GET
		public Response vrniVseOsebe() {
			return Response.ok(ejb.getKnjige()).build();
		}

		@GET
		@Path("/knjiga/{id}")
		public Response vrniOsebo(@PathParam("id") String idS) {
			int id = Integer.parseInt(idS);
			Knjiga kn = ejb.najdId(id);
			if (kn != null) {
				return Response.ok(kn).build();
			} else {
				return Response.status(403).entity("KnjigeNiMogoceNajtiException").build();
			}
		}

}

