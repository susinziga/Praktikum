package rest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
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
import EJB.KnjigomatEJB;
import EJB.KnjigomatKnjigaEJB;
import EJB.NarociloEJB;
import EJB.UporabnikEJB;
import iskanje.KnjigomatKnjiga;
import projekt.Izposoja;
import projekt.Knjiga;
import projekt.KnjigaDao;
import projekt.Knjigomat;
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
	
	@EJB
	private KnjigomatEJB masinaEjb;
	
	@EJB
	private KnjigomatKnjigaEJB vmesniEjb;

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
		KnjigomatKnjiga knkn=vmesniEjb.getKnjigomatKnjiga(idKnj);
		knkn.setJeNoter(false);
		knkn.setJeSposojena(true);
		vmesniEjb.update(knkn);
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		 java.sql.Date novi = new java.sql.Date(date.getTime() + 14l*24l*60l*60l*1000l);
		    System.out.println(novi);
		i.setDatumDo(novi);
		i.setDatumOd(date);
		i.setStanje(true);
		i.setKnjiga(k);
		i.setUpo(u);
		String dat=i.getDatumDo()+" ";
		mailer.akcijaIzp(u.getEmail(),dat, k.getNaslov());
		ejb.dodajizposoja(i);
		k.setStanje("izposojena");
		knjEjb.posodobi(k);
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
						kn.setStanje("vrnjena");
						knjEjb.posodobi(kn);
						
						break;
					}
				}
			}
		}
		
		
			System.out.println(izpo);
			return Response.ok(izpo).build();
		
	}
@POST
	@Path("/izpis/{idUpo}")
	public Response izpis(@PathParam("idUpo") int idUpo) {
			List <Izposoja> prva = ejb.vrniVse();
			List <Izposoja> izpo= new ArrayList<Izposoja>();
			for (Izposoja i:prva) {
				if(i.getUpo().getId()==idUpo) {
					izpo.add(i);
				}
			}
			System.out.println(prva.size());
			System.out.println(izpo);
			return Response.ok(izpo).build();
		
	}
@POST
@Path("/izposoja/{knjId}&{upoId}&{masina}")
public Response izposodiBrezNar(@PathParam("knjId") int idKnj,@PathParam("upoId") int idUpo,@PathParam("masina") int idmasin) {
	
	Knjiga k=knjEjb.najdId(idKnj);
	Uporabnik u=upoEjb.najdId(idUpo);
	Izposoja i= new Izposoja();
	Mailer mailer = new Mailer();
	java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	 java.sql.Date novi = new java.sql.Date(date.getTime() + 14l*24l*60l*60l*1000l);
	i.setDatumDo(novi);
	i.setDatumOd(date);
	i.setStanje(true);
	i.setKnjiga(k);
	i.setUpo(u);
	KnjigomatKnjiga knkn=vmesniEjb.getKnjigomatKnjiga(idKnj);
	knkn.setJeNoter(false);
	knkn.setJeSposojena(true);
	vmesniEjb.update(knkn);
	mailer.akcijaIzp(u.getEmail(),novi+"", k.getNaslov());
	ejb.dodajizposoja(i);
	k.setStanje("izposojena");
	knjEjb.posodobi(k);
	Knjigomat ma=masinaEjb.najd(idmasin);
	ma.setProstor(ma.getProstor()+1);
	
	
		return Response.ok("uspesno").build();
	
}
@POST
@Path("/vrniDatum/{knjId}")
public Response vrniDat(@PathParam("knjId") int idKnj) {
		List <Izposoja> vse=ejb.vrniVse();
		List <String> datum= new ArrayList<String>();
		for(Izposoja i:vse) {
			if(i.getKnjiga().getId()==idKnj) {
				datum.add( i.getDatumDo()+" ");
			}
		}
	
	
		return Response.ok(datum).build();
	
}
}
