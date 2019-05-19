package iskanje;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import projekt.Knjiga;

public class IskanjeDela {
	
	
	public static List<Integer> isci(List <Knjiga> knjige,String iscem,String cat) throws IOException, ParseException  {
		List<Integer> koncna = new ArrayList<Integer>();
		
		for(Knjiga k:knjige) {
			if(cat.equals("naslov")) {
				if(k.getNaslov().contains(iscem)){
					koncna.add(k.getId());
				}
			}
			else if(cat.equals("avtor")) {
				if(k.getAvtor().contains(iscem)){
					koncna.add(k.getId());
				}
			}
			if(cat.equals("vrsta")) {
				if(k.getVrsta().contains(iscem)){
					koncna.add(k.getId());
				}
			}
		}
			
		
		
		return koncna;
		
	}
	

}
