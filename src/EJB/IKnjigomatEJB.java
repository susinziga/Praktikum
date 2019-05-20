package EJB;

import java.util.List;

import javax.ejb.Local;

import projekt.Knjigomat;
@Local
public interface IKnjigomatEJB {
	public void dodajKnjikomat(Knjigomat k);
	List<Knjigomat> getKnjigomat(int id);
	List<Knjigomat> vrniVse();
	Knjigomat najd(int id);
	void update(Knjigomat k);
}
