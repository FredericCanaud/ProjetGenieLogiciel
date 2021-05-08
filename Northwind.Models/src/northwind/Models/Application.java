package northwind.Models;

import java.util.List; 

import javax.persistence.EntityManager; 
import javax.persistence.EntityManagerFactory; 
import javax.persistence.Persistence; 
import javax.persistence.TypedQuery;

public class Application { 
	public static void main(String[] args) {         
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Northwind.Models");
		EntityManager em = emf.createEntityManager();         
		@SuppressWarnings("unchecked")
		TypedQuery<Customers> query = (TypedQuery<Customers>) em.createNamedQuery("Customers.findAll");
		List<Customers> customers = query.getResultList(); 
		for (Customers c : customers) {             
			System.out.println("nom = "+ c.getEmailAddress());         
		} 
		em.close(); 
		emf.close();    
	} 
}