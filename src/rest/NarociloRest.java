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
import EJB.NarociloEJB;
import EJB.UporabnikEJB;
import iskanje.IskanjeDela;
import projekt.Knjiga;
import projekt.KnjigaDao;
import projekt.Knjigomat;
import projekt.Narocilo;
import projekt.Uporabnik;


@Path("/narocilo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class NarociloRest {
	@EJB
	private NarociloEJB ejb;
	
	@EJB
	private KnjigaDao knjigaEjb;
	
	@EJB
	private KnjigomatEJB knjigomatEjb;
	
	@EJB
	private UporabnikEJB upoEjb;

	@Context
    private UriInfo context;
	
	/*Dodaj narocilo*/
	@POST
	@Path("/dodaj/{upo}&{knjiga}&{masina}")
	@Produces("application/json")
	public Response iskanjeKnjige(@PathParam("upo") int idUpo,@PathParam("knjiga") int idKnjija,@PathParam("masina") String masinaLokacija ) throws IOException, ParseException {
	
		Knjiga k = knjigaEjb.najdId(idKnjija);
		k.setStanje(false);
		knjigaEjb.posodobi(k);
		int idknjigomat=0;
		List<Knjigomat> vsi= knjigomatEjb.vrniVse();
		Knjigomat masina= null;
		for(Knjigomat knj:vsi) {
			if(knj.getLokacija().equals(masinaLokacija)) {
				masina=knj;
				idknjigomat=knj.getId();
			}
		}
		
		Uporabnik upo = upoEjb.najdId(idUpo);
		Narocilo nar = new Narocilo();
		nar.setDatumDo(null);
		nar.setDatumOd(null);
		nar.setKnjiga(k);
		nar.setKnjigomat(masina);
		nar.setUporabnik(upo);
		nar.setStanje(true);
		
		ejb.dodajNarocilo(nar);
		
		k.setStanje(false);
		
		knjigaEjb.posodobi(k);
		
		knjigomatEjb.dodajKnjigo(idknjigomat, k);
		knjigomatEjb.update(masina);
		
		
			return Response.ok("Uspesno").build();
		
	}
	
	/*Dobi narocila za uporabnika*/
	@POST
	@Path("/vrni/{upo}")
	@Produces("application/json")
	public Response vrniNarocila(@PathParam("upo") int idUpo ) throws IOException, ParseException {
	List <Narocilo> vsi = ejb.getNarocila();
	List <Integer> koncna= new ArrayList<Integer>();
	for (Narocilo n : vsi) {
		if (n.getUporabnik().getId()==idUpo&&n.getStanje()==true) {
			koncna.add(n.getId());
		}
	}
	
		if (koncna.size()>0) {
			return Response.ok(koncna).build();
		}
		else 
			return Response.ok().build();
		
	}
	@POST
	@Path("/celo/{upo}&{masina}")
	@Produces("application/json")
	public Response vrniNar(@PathParam("upo") int idUpo, @PathParam("masina") int masina ) throws IOException, ParseException {
	List <Narocilo> vsi = ejb.getNarocila();
	List <Narocilo> vsi2= new ArrayList<Narocilo>();
	List <Knjiga> knjige= new ArrayList<Knjiga>();
	for (Narocilo n:vsi) {
		if (n.getStanje()==true&&n.getKnjigomat().getId()==masina&&n.getUporabnik().getId()==idUpo) {
			vsi2.add(n);
			
		}
	}
	
	for (Narocilo n:vsi2) {
	Knjiga k=knjigaEjb.najdId(n.getKnjiga().getId());
	knjige.add(k);
	
	}
	
	
		if (vsi.size()>0) {
			return Response.ok(knjige).build();
		}
		else 
			return Response.ok().build();
		
	}
	@POST
	@Path("/vrniID/{upo}&{knjiga}&{masina}")
	@Produces("application/json")
	public Response vrniID(@PathParam("upo") int idUpo,@PathParam("knjiga") int idKnjija,@PathParam("masina")int masinaID ) throws IOException, ParseException {
	
	List<Narocilo> vsi= ejb.getNarocila();
	int koncna = 0;
	for(Narocilo n:vsi) {
		if(n.getKnjigomat().getId()==masinaID && n.getUporabnik().getId()==idUpo && n.getKnjiga().getId()==idKnjija ) {
	koncna=n.getId();
		}

	}
	return Response.ok(koncna).build();
	}
}
