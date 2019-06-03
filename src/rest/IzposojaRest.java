<<<<<<< HEAD
<<<<<<< HEAD
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
import EJB.UporabnikEJB;
import projekt.Izposoja;
import projekt.Knjiga;
import projekt.KnjigaDao;
import projekt.Mailer;
import projekt.Narocilo;
import projekt.Uporabnik;

@Path("/izposoja")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IzposojaRest {


	@EJB
	private IzposojaEJB ejb;
	
	@EJB
	private KnjigaDao knjEjb;
	
	@EJB
	private UporabnikEJB upoEjb;
	
	@EJB
	private NarociloEJB narEjb;

	@Context
    private UriInfo context;



	
	/*Omogocen POST knjiga*/
	@POST
	@Path("/izpo/{narId}&{knjId}&{upoId}")
	public Response izposodi(@PathParam("narId") int idNar,@PathParam("knjId") int idKnj,@PathParam("upoId") int idUpo) {
		Narocilo n = narEjb.najd(idNar);
		Knjiga k=knjEjb.najdId(idKnj);
		Uporabnik u=upoEjb.najdId(idUpo);
		Izposoja i= new Izposoja();
		Mailer mailer = new Mailer();
		
		i.setDatumDo(null);
		i.setDatumOd(null);
		i.setStanje(true);
		i.setKnjiga(k);
		i.setUpo(u);
		mailer.akcijaIzp(u.getEmail(),"20.5.2019", k.getNaslov());
		ejb.dodajizposoja(i);
		
		n.setStanje(false);
		narEjb.update(n);
		
			return Response.ok("uspesno").build();
		
	}
	
	//****************************************************** SPREMENI IZ ID V QR
	@POST
	@Path("/vrni/{qr}")
	public Response vrniKnjigo(@PathParam("qr") String qr) {
		
		List<Knjiga> vseKnjige= knjEjb.getKnjige();
		String izpo="nedela";
		for (Knjiga kn:vseKnjige) {
			
			//izpo+=kn.getQrKoda()+", ";
			if(kn.getQrKoda().equals(qr)) {			//tukaj spremeni-- vazi bom ;)
				
				List<Izposoja> vseIz = ejb.vrniVse();
				
				for (Izposoja iz:vseIz) {
					
					System.out.println("dela"+iz.getKnjiga().getId()+kn.getId());
					if(iz.getKnjiga().getId()==kn.getId()&&iz.isStanje()==true) {
						izpo="dela";
						System.out.println("delaas");
						
						iz.setStanje(false);
						System.out.println(iz.isStanje());
						ejb.update(iz);
						kn.setStanje(true);
						knjEjb.posodobi(kn);
						
						break;
					}
				}
			}
		}
		
		
			System.out.println(izpo);
			return Response.ok(izpo).build();
		
	}
	
}
=======
=======
>>>>>>> parent of dc756a0... git gud
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		Knjiga k=knjEjb.najdId(idKnj);
=======
		Knjiga k=knjEjb.najdi(idKnj);
>>>>>>> parent of dc756a0... git gud
=======
		Knjiga k=knjEjb.najdId(idKnj);
>>>>>>> parent of 6e2febf... git gud
=======
		Knjiga k=knjEjb.najdId(idKnj);
>>>>>>> parent of 6e2febf... git gud
		
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
	public Response vrniKnjigo(@PathParam("qr") String qr) {
		
		List<Knjiga> vseKnjige= knjEjb.getKnjige();
		String izpo="nedela";
		for (Knjiga kn:vseKnjige) {
			
			//izpo+=kn.getQrKoda()+", ";
			if(kn.getQrKoda().equals(qr)) {			//tukaj spremeni-- vazi bom ;)
				
				List<Izposoja> vseIz = ejb.vrniVse();
				
				for (Izposoja iz:vseIz) {
					
					System.out.println("dela"+iz.getKnjiga().getId()+kn.getId());
					if(iz.getKnjiga().getId()==kn.getId()&&iz.isStanje()==true) {
						izpo="dela";
						System.out.println("delaas");
						
						iz.setStanje(false);
						ejb.update(iz);
						kn.setStanje(true);
						knjEjb.posodobi(kn);
						
						break;
					}
				}
			}
		}
		
		
			System.out.println(izpo);
			return Response.ok(izpo).build();
		
	}
	
}
<<<<<<< HEAD
>>>>>>> parent of 6e2febf... git gud
=======
>>>>>>> parent of dc756a0... git gud
