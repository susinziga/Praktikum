package projekt;

import java.awt.Graphics2D;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;

import javax.ejb.Singleton;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.DatatypeConverter;

@Entity
@Table(name="Knjiga")
@Singleton
public class Knjiga implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5204134699484276684L;
	
	private int id;
	private String naslov;
	private String avtor;
	private String vrsta;
	byte[]slika;
	private String qrKoda;
	private String stanje;

	
	
	
	
	public Knjiga() {
		this("", "", "",null,"");
	}
	
	public Knjiga(String naslov, String avtor, String vrsta, byte[]slika, String qrKoda) {
		super();
		this.naslov = naslov;
		this.avtor = avtor;
		this.vrsta = vrsta;
		this.slika = slika;
		this.qrKoda = qrKoda;
		this.stanje="navoljo";
		
	}
	
	public String stringSlika(){
		
		
		byte[]b=slika;
		String string="a";
		System.out.println("slika");
		
		if (b==null) return "ni slike";
		string=Base64.getEncoder().encodeToString(b);
		System.out.println(string);
		return string;
	}
	
	
	
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   @Column(name="idKnjiga")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getAvtor() {
		return avtor;
	}

	public void setAvtor(String avtor) {
		this.avtor = avtor;
	}

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}

	

	public String getQrKoda() {
		return qrKoda;
	}

	public void setQrKoda(String qrKoda) {
		this.qrKoda = qrKoda;
	}
	@Override
	public String toString() {
		return "Knjiga [naslov=" + naslov + ", avtor=" + avtor + ", vrsta=" + vrsta+ ", blob="+slika.length+"]";
	}

	public String getStanje() {
		return stanje;
	}

	public void setStanje(String stanje) {
		this.stanje = stanje;
	}

	@Lob
	public byte[] getSlika() {
		return slika;
	}

	public void setSlika(byte[] slika) {
		this.slika = slika;
	}
	
	

	
}
