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
		String str = "SELECT s FROM Suppliers s";         
		TypedQuery<Suppliers> query = em.createQuery(str, Suppliers.class);
		List<Suppliers> suppliers = query.getResultList(); 
		for (Suppliers s : suppliers) {             
			System.out.println("nom = "+ s.getLastName());         
		} 
		em.close(); 
		emf.close();    
	} 
}