package projekt;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import EJB.IKnjigomatEJB;
import iskanje.IskanjeDela;

@Named("Zrno")
@SessionScoped
public class Zrno implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3108130695607179483L;
	private Part uploadedFile;
	private String folder = "C:\\FERI\\2.letnik\\Praktikum\\slike";
	private String knjigaInput;
	private List<String> imenaKnjig;
	
	private Knjigomat kn= new Knjigomat();
	
	
	@EJB
	KnjigaDao knjigaDao;
	
	@EJB
	IKnjigomatEJB knjigomat;
	
	private String cat=null;
	private String isci=null;
	private List<Integer>prikazIndex = new ArrayList<Integer>();
	private List<Knjiga>prikaz = new ArrayList<Knjiga>();
	
	public void dodajKnjigo() {
		String path=saveFile();
		
		knjigaDao.addBook(path);
		refreshImenaKnjig();
	}
	public void izbrisiKnjigo() {
		knjigaDao.deleteKnjiga(knjigaInput);
	}
	
	public void spremeniKnjigo() {
		knjigaDao.updateKnjiga(knjigaInput);
	}

	/*Dodajnaje knjigomatov*/
	public void dodajKnjigomat() {
		knjigomat.dodajKnjikomat(kn);
		kn= new Knjigomat();
	}
	
	
	public Knjigomat getKn() {
		return kn;
	}
	public void setKn(Knjigomat kn) {
		this.kn = kn;
	}
	public IKnjigomatEJB getKnjigomat() {
		return knjigomat;
	}
	public void setKnjigomat(IKnjigomatEJB knjigomat) {
		this.knjigomat = knjigomat;
	}
	/*Brisanje knjigomatov*/
	public void izbrisiKnjigomat(int id) {
		Knjigomat brisi = knjigomat.najd(id);
		knjigomat.brisi(brisi);
	}
	
	public KnjigaDao getKnjigaDao() {
		return knjigaDao;
	}
	
	
	public String saveFile(){
		 String fileName="";
		for(String cd:uploadedFile.getHeader("content-disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				fileName=cd.substring(cd.indexOf('=')+1).trim();
			}
			
		}
		System.out.print(fileName);
		fileName=fileName.substring(1,fileName.length()-1);
		 try (InputStream input =  uploadedFile.getInputStream()) {
			 
		
		
			 	System.out.println("dela tu");
		         Files.copy(input, new File(folder, fileName).toPath());
		     }
		     catch (IOException e) {
		         e.printStackTrace();
		     }
		 
		 return folder+"\\"+fileName;
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
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}

	
}
