package it.betacom.service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import it.betacom.entity.Animali;
import it.betacom.entity.Clienti;

public class csvReader {

	public static Set<Clienti> readCsvClienti(String filePath){
		
		Set<Clienti> records = new HashSet<>();
		
		try {
			//Configurazione e creazione del parser CSV
			CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
			CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath))
					.withCSVParser(parser)
					.build();
			
			// Salto le prime due righe del file CSV
			csvReader.readNext();
			csvReader.readNext();
			
			String[] record;
			while((record = csvReader.readNext()) != null) {
				// Creazione di un oggetto Cliente per ogni riga
				Clienti cliente = new Clienti();
				// Setto i valori del cliente dai campi del CSV
				cliente.setNome(record[0]); //0 => colonna corrispondente
				cliente.setCognome(record[1]);
				cliente.setCitta(record[2]);
				cliente.setIndirizzo(record[3]);
				cliente.setTelefono(new BigInteger(record[4].replaceAll("\\s+", ""))); //rimuovo gli spazi
				
				records.add(cliente);
			}
			
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
		
		return records;
		
	}
	
	public static Set<Animali> readCsvAnimali(String filePath, Set<Clienti> listaClienti){
		Set<Animali> records = new HashSet<>();
		
		try {
			CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
			CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath))
					.withCSVParser(parser)
					.build();
			
			csvReader.readNext();
			csvReader.readNext();
			
			String[] record;
			while((record = csvReader.readNext()) != null) {
	            final String[] recordCopy = record; // Creare una copia final di record altrimenti non accettata dalla lambda expression
	            
				Animali animale = new Animali();
				animale.setMatricola(new BigInteger(record[7]));
				animale.setTipoAnimale(record[5]);
				animale.setNomeAnimale(record[6]);
				animale.setDataAcquisto(LocalDate.parse(record[8], DateTimeFormatter.ofPattern("d/M/yyyy")));
				animale.setPrezzo(Double.parseDouble(record[9]));

//				Trovo il cliente corrispondente nella lista dei clienti
//				Optional è una classe che può contenere un valore o essere vuota
//				listaClienti.stream(): Questo inizia la trasformazione della lista
//				dei clienti in uno stream. Uno stream è una sequenza di elementi
//				che può essere elaborata in modo sequenziale o parallelo
				Optional<Clienti> clienteCorrispondente = listaClienti.stream()
//						Viene fornita una lambda expression che descrive la condizione di filtraggio
//						Solo gli elementi che soddisfano questa condizione passeranno
//						attraverso lo stream
	                    .filter(cliente -> 
//	                    confronto i valori del cliente con il valore nella colonna
//	                    corrispondente nel file csv
	                            cliente.getNome().equals(recordCopy[0]) &&
	                            cliente.getCognome().equals(recordCopy[1]) &&
	                            cliente.getCitta().equals(recordCopy[2]) &&
	                            cliente.getTelefono().equals(new BigInteger(recordCopy[3].replaceAll("\\s+", ""))) &&
	                            cliente.getIndirizzo().equals(recordCopy[4])
	                    )
//	                    Una volta filtrato lo stream, findFirst restituisce il
//	                    primo elemento che soddisfa la condizione di filtro
//	                    Il risultato è avvolto in un Optional, il che significa
//	                    che può anche essere vuoto se nessun elemento corrisponde
	                    .findFirst();

//				Se il cliente è presente, lo associa all'animale
//				Il metodo ifPresent permette di eseguire un'azione solo se il 
//				valore all'interno dell'Optional è presente
	            clienteCorrispondente.ifPresent(animale::setClienti);
	            
	            records.add(animale);
				
			}
			
		} catch (IOException | CsvValidationException e) {
	        e.printStackTrace();
	    }

	    return records;
	}
}
