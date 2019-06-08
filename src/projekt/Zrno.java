package projekt;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;


import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import EJB.KnjigomatEJB;
import EJB.KnjigomatKnjigaEJB;
import EJB.UporabnikEJB;
import iskanje.IskanjeDela;
import iskanje.KnjigomatKnjiga;

@Named("Zrno")
@SessionScoped
public class Zrno implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3108130695607179483L;
	private Part uploadedFile;
	
	private String knjigaInput;
	private List<String> imenaKnjig;
	
	private Knjigomat kn= new Knjigomat();
	private Uporabnik up= new Uporabnik();
	
	
	
	
	@EJB
	KnjigaDao knjigaDao;
	
	@EJB
	UporabnikEJB upo;
	
	@EJB
	KnjigomatEJB knjigomat;
	
	@EJB
	KnjigomatKnjigaEJB knjiEjb;
	
	Mailer mailer = new Mailer();
	
	private StreamedContent image;
	private String cat=null;
	private String isci=null;
	private List<Integer>prikazIndex = new ArrayList<Integer>();
	private List<Knjiga>prikaz = new ArrayList<Knjiga>();
	
	public void filaj() {
		sprazniKnjigomate();
		List<Knjigomat> knjigomati=knjigomat.vrniVse();
		for(Knjigomat k:knjigomati) {
			String vrsta=najboljIzposojene(k.getId());
			List<Knjiga> knjige=knjigaDao.getKnjige();
			Collections.shuffle(knjige);
			List<Knjiga> zaNoter=new ArrayList<Knjiga>();
			int a=(int) ((k.getSkupajProstor()-10)*0.75);
			System.out.println("vrsta"+vrsta+" "+k.getId());
			for(Knjiga kn:knjige) {
				if(!(kn.getStanje().equals("navoljo"))) continue;
				if(vrsta!="") {
					if(kn.getVrsta().equals(vrsta)) {
						zaNoter.add(kn);
					}
				}
				else {
					zaNoter.add(kn);
				}
				
			}
			int j=0;
			for(int i=0;i<zaNoter.size();i++) {
				dodajVKnjigomat(zaNoter.get(i), k);
				j=j+1;
				k.setProstor(k.getProstor()-1);
				knjigomat.update(k);
				if(j>=a) break;
			}
			for(Knjiga kn:knjige) {
				if(kn.getStanje().equals("navoljo")) {
					if(k.getProstor()<=10)	break;
					dodajVKnjigomat(kn, k);
					k.setProstor(k.getProstor()-1);
					knjigomat.update(k);
				}
			}
		}
	
		
		//napolni.sprazniKnjigomate();
		//napolni.najboljIzposojene(1);
	}
	
	
	public void dodajVKnjigomat(Knjiga knj,Knjigomat kn) {
		knj.setStanje("vmasini");
		knjigaDao.posodobi(knj);
		KnjigomatKnjiga knjkn=new KnjigomatKnjiga(knj,kn,true,false);
		knjiEjb.update(knjkn);
	}
	
	public void zazeni() {
	       
        System.out.println("t");
        
        for(int i=1;i<=Integer.parseInt(knjigaDao.getKnjigeSt());i++) {
        	knjigaDao.knjiga=knjigaDao.getKnjigaIde(i);
        	Path path=Paths.get("C:\\slikeKnj","k"+i+".jpg");
    		byte[]b=null;
    		try {
    			
    			b=Files.readAllBytes(path);
    			System.out.println(b.length);
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		knjigaDao.knjiga.setSlika(b);
    		knjigaDao.posodobi(knjigaDao.knjiga);
    		
        }
    } 

	
	
	public void dodajKnjigo() {
		String fileName="";
		for(String cd:uploadedFile.getHeader("content-disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				fileName=cd.substring(cd.indexOf('=')+1).trim();
				fileName=fileName.substring(1,fileName.length()-1);
			}
			
		}
		System.out.println(fileName);
		//Path patha=Paths.get(fileName);
		Path path=saveFile();
		byte[]b=null;
		try {
			
			b=Files.readAllBytes(path);
			System.out.println(b.length);
			Files.delete(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		knjigaDao.addBook(b);
		refreshImenaKnjig();
	}
	public void izbrisiKnjigo() {
		knjigaDao.deleteKnjiga(knjigaInput);
	}
	
	
	public Uporabnik getUp() {
		return up;
	}
	public void setUp(Uporabnik up) {
		this.up = up;
	}
	public void spremeniKnjigo() {
		knjigaDao.updateKnjiga(knjigaInput);
	}
	/*Iskanje*/
	public void isciBaza() throws IOException, ParseException {
		prikaz = new ArrayList<Knjiga>();
		new ArrayList<Integer>();
		System.out.println("Not");
		prikazIndex=IskanjeDela.isci(knjigaDao.getKnjige(),isci,cat);
		for (Integer i: prikazIndex) {
			System.out.println("IDJI: " +i);
			Knjiga vmesnaKnj=  knjigaDao.getKnjigaId(i).get(0);
			prikaz.add(vmesnaKnj);
		}
	}
	
	public void sprazniKnjigomate() {
		List<KnjigomatKnjiga>knkn=knjiEjb.vrniVse();
		for(KnjigomatKnjiga kn:knkn) {
			if(kn.isJeNoter()==true)
				kn.setJeNoter(false);
				knjiEjb.update(kn);
		}
		List<Knjiga>knjige=knjigaDao.getKnjige();
		for(Knjiga k:knjige) {
			if(k.getStanje().equals("vmasini")||k.getStanje().equals("vrnjena")||k.getStanje().equals("naroceno")) {
				k.setStanje("navoljo");
				knjigaDao.posodobi(k);
			}
		}
		List<Knjigomat>knjigomati=knjigomat.vrniVse();
		for(Knjigomat k:knjigomati) {
			k.setProstor(k.getSkupajProstor());
			knjigomat.update(k);
		}
	}
	
	
	public String najboljIzposojene(int idKnjigomat){
		ArrayList<String>vrste=new ArrayList<String>();
		ArrayList<Integer>stevilo=new ArrayList<Integer>();
		
		List<KnjigomatKnjiga>knkn=knjiEjb.vrniVse();
		if(knkn!=null||knkn.size()>0) {
		boolean nas=false;
		for (int i=0;i<knkn.size();i++) {
			if (knkn.get(i).getMasina().getId()!=idKnjigomat)
				continue;
			if(knkn.get(i).isJeSposojena()==false)
				continue;
			System.out.print(i);
			nas=false;
			for(int j=0;j<vrste.size();j++) {
				if(knkn.get(i).getKnjiga().getVrsta().equals(vrste.get(j))){
					stevilo.set(j, stevilo.get(j)+1);
					nas=true;
					break;
				}
				
				
			}
			if(nas==true) continue;
			vrste.add(knkn.get(i).getKnjiga().getVrsta());
			stevilo.add(1);
		}
		if(vrste.size()>0) {
			int max=0;
			int index=0;
			for(int j=0;j<stevilo.size();j++) {
				if (stevilo.get(j)>max) {
					max=stevilo.get(j);
					index=j;
				}
			}
			System.out.println(knkn);
			System.out.println(vrste);
			System.out.println(stevilo);
			
			return vrste.get(index);
			}
		else
			return "";
		}
		else return "";
	}
	
	/*Dodajnaje knjigomatov*/
	public void dodajKnjigomat() {
		knjigomat.dodajKnjikomat(kn);
		kn= new Knjigomat();
	}
	
	/*Dodajnaje uporabnika*/
	public void dodajUporabnika() {
		up.setQrUporabnik("");
		upo.dodajUporabnika(up);
		up= new Uporabnik();
	}
	/*Brisanje knjigomatov*/
	public void izbrisiKnjigomat(int id) {
		Knjigomat brisi = knjigomat.najd(id);
		knjigomat.brisi(brisi);
	}
	public UporabnikEJB getUpo() {
		return upo;
	}
	public void setUpo(UporabnikEJB upo) {
		this.upo = upo;
	}
	public Knjigomat getKn() {
		return kn;
	}
	public void setKn(Knjigomat kn) {
		this.kn = kn;
	}
	
	
	
	public KnjigomatEJB getKnjigomat() {
		return knjigomat;
	}
	public void setKnjigomat(KnjigomatEJB knjigomat) {
		this.knjigomat = knjigomat;
	}
	public KnjigaDao getKnjigaDao() {
		return knjigaDao;
	}
	
	
	public Path saveFile(){
		Path path=null;
		 String fileName="";
		for(String cd:uploadedFile.getHeader("content-disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				fileName=cd.substring(cd.indexOf('=')+1).trim();
			}
			
		}
		System.out.println(fileName);
		if(fileName.indexOf("\\")!=-1) {
			fileName=fileName.substring(fileName.lastIndexOf("\\"));
		}
		System.out.print(fileName);
		fileName=fileName.substring(1,fileName.length()-1);
		 try (InputStream input =  uploadedFile.getInputStream()) {
			 
		
		
			 	System.out.println("dela tu");
		         Files.copy(input, new File("C:\\Slike", fileName).toPath());
		         path=Paths.get("C:\\Slike", fileName);
		         
		     }
		     catch (IOException e) {
		         e.printStackTrace();
		     }
		 
		 return path;
	}
	
	
	
	
	
	public void setKnjigaDao(KnjigaDao knjigaDao) {
		this.knjigaDao = knjigaDao;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getIsci() {
		return isci;
	}

	public void setIsci(String isci) {
		this.isci = isci;
	}

	public List<Integer> getPrikazIndex() {
		return prikazIndex;
	}

	public void setPrikazIndex(List<Integer> prikazIndex) {
		this.prikazIndex = prikazIndex;
	}

	public List<Knjiga> getPrikaz() {
		return prikaz;
	}

	public void setPrikaz(List<Knjiga> prikaz) {
		this.prikaz = prikaz;
	}


	public List<String> getImenaKnjig() {
		return imenaKnjig;
	}

	public void setImenaKnjig(List<String> imenaKnjig) {
		this.imenaKnjig = imenaKnjig;
	}
	
	public String getKnjigaInput() {
		return knjigaInput;
	}

	public void setKnjigaInput(String knjigaInput) {
		this.knjigaInput = knjigaInput;
	}

	public void refreshImenaKnjig() {
		imenaKnjig = new ArrayList<String>();
		for(Knjiga book : knjigaDao.getKnjige()) {
			imenaKnjig.add(book.getNaslov());
		}
	}
	public void izberiKnjigo() {
		knjigaDao.izberiKnjigo(knjigaInput);
	}
	public Part getUploadedFile() {
		return uploadedFile;
	}
	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}


	public StreamedContent getImage() {
		return image;
	}


	public void setImage(StreamedContent image) {
		this.image = image;
	}



	public Mailer getMailer() {
		return mailer;
	}



	public void setMailer(Mailer mailer) {
		this.mailer = mailer;
	}

	

	

	
}
