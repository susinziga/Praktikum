package projekt;

import java.io.IOException;
import java.io.Serializable;
<<<<<<< HEAD
import java.text.ParseException;
=======
>>>>>>> c77c6f284e5f77c6c643b28f7a6dedcbfdbd9bb1
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import iskanje.Iskanje;
import iskanje.IskanjeDela;

@Named("Zrno")
@SessionScoped
public class Zrno implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3108130695607179483L;
	
	private String knjigaInput;
	private List<String> imenaKnjig;
	
	@EJB
	KnjigaDao knjigaDao;
	private String cat=null;
	private String isci=null;
	private List<Integer>prikazIndex = new ArrayList<Integer>();
	private List<Knjiga>prikaz = new ArrayList<Knjiga>();
	
	public void dodajKnjigo() {
		knjigaDao.addBook();
		refreshImenaKnjig();
	}
	public void izbrisiKnjigo() {
		knjigaDao.deleteKnjiga(knjigaInput);
	}
	
	public void spremeniKnjigo() {
		knjigaDao.updateKnjiga(knjigaInput);
	}

	public KnjigaDao getKnjigaDao() {
		return knjigaDao;
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

<<<<<<< HEAD
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

=======
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
>>>>>>> c77c6f284e5f77c6c643b28f7a6dedcbfdbd9bb1
	
}
