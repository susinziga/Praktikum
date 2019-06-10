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
import projekt.KnjigaDao;
import projekt.Knjigomat;


@Path("/vmesna")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VmesnaRest {

	@EJB
	private KnjigomatEJB Knjigomatejb;
	
	@EJB
	private NarociloEJB narociloEjb;
	
	@EJB
	private KnjigaDao ejbKnjiga;
	
	@EJB
	private KnjigomatKnjigaEJB vmensaEjb;

	@Context
    private UriInfo context;
	
	
	
	
	@POST
	@Path("/vrniLokacijo/{idKnjiga}")
	@Produces("application/json")
	public Response vrniLok( @PathParam("idKnjiga") int idKnjiga ) throws IOException, ParseException {
		Knjiga k= ejbKnjiga.najdId(idKnjiga);
		int index=0;
		for (KnjigomatKnjiga vm:vmensaEjb.vrniVse()) {
			if (vm.getKnjiga().getId()==idKnjiga) {
				index=vm.getMasina().getId();
			}
		}
		String lokacija= Knjigomatejb.najd(index).getLokacija();
		System.out.println("lokacija "+ lokacija);
			return Response.ok(lokacija).build();
			

	
	}
	
	
}
