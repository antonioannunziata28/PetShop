package it.betacom.main;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import it.betacom.entity.Animali;
import it.betacom.entity.Clienti;
import it.betacom.service.csvReader;

public class Main {

	public static void main(String[] args) {
		
		String clientiFilePath = "./tabelleCsv/Clienti.csv";
		String animaliFilePath = "./tabelleCsv/PETSHOP.csv";
		
		Set<Clienti> listaClienti = csvReader.readCsvClienti(clientiFilePath);
		Set<Animali> listaAnimali = csvReader.readCsvAnimali(animaliFilePath, listaClienti);		

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PetShop");
		EntityManager eM = emf.createEntityManager();
		
		try {
            eM.getTransaction().begin();

            for (Clienti cliente : listaClienti) {
                eM.persist(cliente);
            }
            
            for(Animali animale : listaAnimali) {
            	eM.persist(animale);
            }

            eM.getTransaction().commit();
            
        } catch (Exception e) {
            if (eM.getTransaction() != null && eM.getTransaction().isActive()) {
                eM.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            eM.close();
            emf.close();
        }
    }
}

