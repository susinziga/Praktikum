package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import EJB.IzposojaEJB;
import EJB.NarociloEJB;
import projekt.Izposoja;
import projekt.Knjiga;
import projekt.KnjigaDao;
import projekt.Narocilo;

@Path("/izposoja")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IzposojaRest {


	@EJB
	private IzposojaEJB ejb;
	
	@EJB
	private KnjigaDao knjEjb;
	
	
	@EJB
	private NarociloEJB narEjb;

	@Context
    private UriInfo context;



	
	/*Omogocen POST knjiga*/
	@POST
	@Path("/izpo/{narId}&{knjId}")
	public Response izposodi(@PathParam("narId") int idNar,@PathParam("knjId") int idKnj) {
		Narocilo n = narEjb.najd(idNar);
		Knjiga k=knjEjb.najdId(idKnj);
		
		Izposoja i= new Izposoja();
		i.setDatumDo(null);
		i.setDatumOd(null);
		i.setStanje(true);
		i.setKnjiga(k);
		ejb.dodajizposoja(i);
		
		n.setStanje(false);
		narEjb.update(n);
		
			return Response.ok("uspesno").build();
		
	}
	
	//****************************************************** SPREMENI IZ ID V QR
	@POST
	@Path("/vrni/{qr}")
	public Response vrniKnjigo(@PathParam("qr") int qr) {
		
		List<Knjiga> vseKnjige= knjEjb.getKnjige();
	
		for (Knjiga kn:vseKnjige) {
			if(kn.getId()==qr) {			//tukaj spremeni
				
				List<Izposoja> vseIz = ejb.vrniVse();
				
				for (Izposoja iz:vseIz) {
					if(iz.getKnjiga()==kn) {
						iz.setStanje(false);
						ejb.update(iz);
						kn.setStanje(true);
						knjEjb.posodobi(kn);
						break;
					}
				}
			}
		}
		
		
	
			return Response.ok("uspesno").build();
		
	}
	
}
