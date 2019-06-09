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
import EJB.KnjigomatKnjigaEJB;
import EJB.NarociloEJB;
import iskanje.KnjigomatKnjiga;
import projekt.Knjiga;
import projekt.Knjigomat;


@Path("/masina")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KnjigomatRest {
	
	@EJB
	private KnjigomatEJB ejb;
	
	@EJB
	private NarociloEJB narociloEjb;
	
	@EJB
	private KnjigomatKnjigaEJB vmensaEjb;

	@Context
    private UriInfo context;
	
	@GET
	public Response vrniVseKnjigomate() {
		return Response.ok(ejb.vrniVse()).build();
	}

	
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
	
	@POST
	@Path("/iskanjeMasina/{masina}")
	@Produces("application/json")
	public Response iskanjeMasina( @PathParam("masina") int masina ) throws IOException, ParseException {
		List<KnjigomatKnjiga> zac = vmensaEjb.vrniVse();
		List<Knjiga> koncna=new ArrayList<Knjiga>();
		for(KnjigomatKnjiga k:zac) {
			if(k.getMasina().getId()==masina) {
				if(k.isJeNoter()==true) {
					koncna.add(k.getKnjiga());
				}
			}
		}
		if (koncna.size()>0) {
			return Response.ok(koncna).build();
		} else {
			return Response.status(403).entity("Napaka").build();
		}

		/*Knjigomat najdene = ejb.najd(Integer.parseInt(masina));
		
		
		
		List<KnjigomatKnjiga> prva = vmensaEjb.vrniVse();
		List<Knjiga>vmesna= new ArrayList<Knjiga>();
		for (KnjigomatKnjiga v:prva) {
			if(v.getMasina().getIme().equals(masina)) {
				vmesna.add(v.getKnjiga());
			}
		}
		
		
		List<Knjiga> konec=new ArrayList<Knjiga>();
		for(Knjiga k:vmesna) {
			if(k.getStanje().equals("navoljo")) {
				konec.add(k);
			}
		}
		if (najdene!=null) {
			return Response.ok(konec).build();
		} else {
			return Response.status(403).entity("Napaka").build();
		}*/
	}
	
	@GET
	@Path("/knjigomat/{id}")
	public Response vrniKnjigoPost(@PathParam("id") String idS) {
		int id = Integer.parseInt(idS);
		Knjigomat kn = ejb.najd(id);
		if (kn != null) {
			return Response.ok(kn).build();
		} else {
			return Response.status(403).entity("KnjigomataNiMogoceNajtiException").build();
		}
	}
	
}
