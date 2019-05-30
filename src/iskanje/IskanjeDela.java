package iskanje;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import EJB.UporabnikEJB;
import projekt.Knjiga;
import projekt.Uporabnik;

public class IskanjeDela {

	public static List<Integer> isci(List <Knjiga> knjige,String iscem,String cat) throws IOException, ParseException  {
		List<Integer> koncna = new ArrayList<Integer>();
		
		for(Knjiga k:knjige) {
			if(cat.equals("naslov")) {
				if(k.getNaslov().toLowerCase().contains(iscem.toLowerCase())){
					koncna.add(k.getId());
				}
			}
			else if(cat.equals("avtor")) {
				if(k.getAvtor().toLowerCase().contains(iscem.toLowerCase())){
					koncna.add(k.getId());
				}
			}
			if(cat.equals("vrsta")) {
				if(k.getVrsta().toLowerCase().contains(iscem.toLowerCase())){
					koncna.add(k.getId());
				}
			}
		}
			
		
		
		return koncna;
		
	}
	

}
