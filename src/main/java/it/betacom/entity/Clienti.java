package it.betacom.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the Clienti database table.
 * 
 */
@Entity
@NamedQuery(name="Clienti.findAll", query="SELECT c FROM Clienti c")
public class Clienti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;
	private String citta;
	private String cognome;
	private String indirizzo;
	private String nome;
	private BigInteger telefono;

	//bi-directional many-to-one association to Animali
	@OneToMany(mappedBy="clienti")
	private List<Animali> listaAnimali;

	public Clienti() {
	}

	public String getCitta() {
		return this.citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigInteger getTelefono() {
		return this.telefono;
	}

	public void setTelefono(BigInteger telefono) {
		this.telefono = telefono;
	}

	public List<Animali> getAnimalis() {
		return this.listaAnimali;
	}

	public void setAnimalis(List<Animali> listaAnimali) {
		this.listaAnimali = listaAnimali;
	}

	public Animali addAnimali(Animali animali) {
		getAnimalis().add(animali);
		animali.setClienti(this);

		return animali;
	}

	public Animali removeAnimali(Animali animali) {
		getAnimalis().remove(animali);
		animali.setClienti(null);

		return animali;
	}

	@Override
	public String toString() {
		return "Clienti [idCliente=" + idCliente + ", citta=" + citta + ", cognome=" + cognome + ", indirizzo="
				+ indirizzo + ", nome=" + nome + ", telefono=" + telefono + ", listaAnimali=" + listaAnimali + "]";
	}
	
	

}