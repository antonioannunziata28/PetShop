package it.betacom.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.*;


/**
 * The persistent class for the Animali database table.
 * 
 */
@Entity
@NamedQuery(name="Animali.findAll", query="SELECT a FROM Animali a")
public class Animali implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ANIMALI_MATRICOLA_GENERATOR" )
	private BigInteger matricola;

	private Date dataAcquisto;

	private String nomeAnimale;

	private double prezzo;

	private String tipoAnimale;

	//bi-directional many-to-one association to Clienti
	@ManyToOne
	@JoinColumn(name="idCliente", referencedColumnName="idCliente")
	private Clienti clienti;

	public Animali() {
	}

	public BigInteger getMatricola() {
		return this.matricola;
	}

	public void setMatricola(BigInteger matricola) {
		this.matricola = matricola;
	}

	public Date getDataAcquisto() {
		return this.dataAcquisto;
	}

	public void setDataAcquisto(LocalDate dataAcquisto) {
		this.dataAcquisto = java.sql.Date.valueOf(dataAcquisto);;
	}

	public String getNomeAnimale() {
		return this.nomeAnimale;
	}

	public void setNomeAnimale(String nomeAnimale) {
		this.nomeAnimale = nomeAnimale;
	}

	public double getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getTipoAnimale() {
		return this.tipoAnimale;
	}

	public void setTipoAnimale(String tipoAnimale) {
		this.tipoAnimale = tipoAnimale;
	}

	public Clienti getClienti() {
		return this.clienti;
	}

	public void setClienti(Clienti clienti) {
		this.clienti = clienti;
	}

	@Override
	public String toString() {
		return "Animali [matricola=" + matricola + ", dataAcquisto=" + dataAcquisto + ", nomeAnimale=" + nomeAnimale
				+ ", prezzo=" + prezzo + ", tipoAnimale=" + tipoAnimale + ", clienti=" + clienti + "]";
	}
	
	

}