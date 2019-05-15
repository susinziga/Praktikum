package projekt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

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
	
	public void dodajKnjigo() {
		knjigaDao.addBook();
		refreshImenaKnjig();
	}

	public KnjigaDao getKnjigaDao() {
		return knjigaDao;
	}

	public void setKnjigaDao(KnjigaDao knjigaDao) {
		this.knjigaDao = knjigaDao;
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
	
}
