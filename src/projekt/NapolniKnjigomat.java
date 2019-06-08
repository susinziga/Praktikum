package projekt;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;


import EJB.KnjigomatEJB;
import EJB.KnjigomatKnjigaEJB;
import iskanje.KnjigomatKnjiga;



public class NapolniKnjigomat implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	KnjigomatKnjigaEJB knknEjb;
	
	@EJB
	KnjigaDao knjigaEjb;
	
	@EJB
	KnjigomatEJB knjigomatEjb;
	
	
	
	public void sprazniKnjigomate() {
		List<KnjigomatKnjiga>knkn=knknEjb.vrniVse();
		for(KnjigomatKnjiga kn:knkn) {
			if(kn.isJeNoter()==true)
				kn.setJeNoter(false);
				knknEjb.update(kn);
		}
		List<Knjiga>knjige=knjigaEjb.getKnjige();
		for(Knjiga k:knjige) {
			if(k.getStanje().equals("vmasini")||k.getStanje().equals("vrnjena")||k.getStanje().equals("naroceno")) {
				k.setStanje("navoljo");
				knjigaEjb.posodobi(k);
			}
		}
		List<Knjigomat>knjigomati=knjigomatEjb.vrniVse();
		for(Knjigomat k:knjigomati) {
			k.setProstor(k.getSkupajProstor());
			knjigomatEjb.update(k);
		}
	}
	
	
	public String najboljIzposojene(int idKnjigomat){
		ArrayList<String>vrste=new ArrayList<String>();
		ArrayList<Integer>stevilo=new ArrayList<Integer>();
		
		List<KnjigomatKnjiga>knkn=knknEjb.vrniVse();
		boolean nas=false;
		for (int i=0;i<knkn.size();i++) {
			if (knkn.get(i).getId()!=idKnjigomat)
				continue;
			if(knkn.get(i).isJeSposojena()==false)
				continue;
			nas=false;
			for(int j=0;j<vrste.size();j++) {
				if(knkn.get(i).getKnjiga().getVrsta().equals(vrste.get(j))){
					stevilo.set(j, stevilo.get(j)+1);
					nas=true;
				}
				if (nas==true)	continue;
			}
			vrste.add(knkn.get(i).getKnjiga().getVrsta());
			stevilo.add(1);
		}
		int max=0;
		int index=0;
		for(int j=0;j<stevilo.size();j++) {
			if (stevilo.get(j)>max) {
				max=stevilo.get(j);
				index=j;
			}
		}
		System.out.println(vrste);
		System.out.println(stevilo);
		return vrste.get(index);
	}
	
	public void neki() {
		List<KnjigomatKnjiga>knkn=null;
		try {
			knkn=knknEjb.vrniVse();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(knkn);
	}
	
	
	
}
