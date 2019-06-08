package projekt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Knjigomat")
public class Knjigomat implements Serializable {
private int id;
private String ime;
private String lokacija;
private int prostor;	//prostor na voljo
private int skupajProstor;	//skupaj prostor na voljo v knjigomatu



@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
@Column(name="idKnjigomat")
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getIme() {
	return ime;
}
public void setIme(String ime) {
	this.ime = ime;
}
public String getLokacija() {
	return lokacija;
}
public void setLokacija(String lokacija) {
	this.lokacija = lokacija;
}

public int getProstor() {
	return prostor;
}
public void setProstor(int prostor) {
	this.prostor = prostor;
}
public int getSkupajProstor() {
	return skupajProstor;
}
public void setSkupajProstor(int skupajProstor) {
	this.skupajProstor = skupajProstor;
}
 




}
