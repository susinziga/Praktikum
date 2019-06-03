package rest;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
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

import iskanje.IskanjeDela;
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

		/*Omogocen GET knjiga*/	
		/*@GET
		@Path("/knjiga/{id}")
		public Response vrniKnjigo(@PathParam("id") String idS) {
			int id = Integer.parseInt(idS);
			Knjiga kn = ejb.najdId(id);
			if (kn != null) {
				return Response.ok(kn).build();
			} else {
				return Response.status(403).entity("KnjigeNiMogoceNajtiException").build();
			}
		}*/
		
		/*Omogocen POST knjiga*/
		
		@GET
		@Path("/knjiga/{byte}")
		public String vrniStr(@PathParam("byte") byte[]b) {
			String str=Base64.getEncoder().encodeToString(b);
			System.out.println("dela");
			return str;
			//return Response.ok(str).build();
		}
		
		@GET
		@Path("/knjiga/")
		public String vrniStr() {
			
			System.out.println("dela");
			return "ja";
			//return Response.ok(str).build();
		}
		
		
		@POST
		@Path("/knjiga/{id}")
		public Response vrniKnjigoPost(@PathParam("id") String idS) {
			int id = Integer.parseInt(idS);
			Knjiga kn = ejb.najdId(id);
			if (kn != null) {
				return Response.ok(kn).build();
			} else {
				return Response.status(403).entity("KnjigeNiMogoceNajtiException").build();
			}
		}
		
		/* POST knjiga iskanje*/
		@POST
		@Path("/iskanje/{cat}&{iskanje}")
		@Produces("application/json")
		public Response iskanjeKnjige(@PathParam("cat") String cat,@PathParam("iskanje") String isci ) throws IOException, ParseException {
			List <Knjiga> najdene = new ArrayList<Knjiga>();
			
			
			List <Integer> najdeniID = IskanjeDela.isci(ejb.getKnjige(), isci, cat);
			
			for (Integer i: najdeniID) {
				Knjiga k = ejb.najdId(i);
				najdene.add(k);
			
			}
			
			for(int i=0;i<najdene.size();i++) {
				najdene.get(i).setSlika(null);
			}
			
			if (najdene.size() > 0) {
				return Response.ok(najdene).build();
			} else {
				return Response.status(403).entity("KnjigeNiMogoceNajtiException").build();
			}
		}
		
		

		
		@POST
		@Path("/iskanje/")
		@Produces("application/json")
		public Response iskanjeKnjige() throws IOException, ParseException {
			List <Knjiga> najdene = ejb.getKnjige();
			
			for(int i=0;i<najdene.size();i++) {
				najdene.get(i).setSlika(null);
			}
			
			if (najdene.size() > 0) {
				return Response.ok(najdene).build();
			} else {
				return Response.status(403).entity("KnjigeNiMogoceNajtiException").build();
			}
		}

}

