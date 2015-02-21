// Copyright (c) 2015 Olaf Dietsche

//package de.olafdietsche.jpa.hello_world;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.*;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
class Contact {
	@Id
	@GeneratedValue(strategy = SEQUENCE)
	public int id;
	public String name;
	public String email;
	public String phone;
}

public class HelloWorld {
	public static Contact createContact(String name, String email, String phone) {
		Contact contact = new Contact();
		contact.name = name;
		contact.email = email;
		contact.phone = phone;
		return contact;
	}

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ContactManagement");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Contact contact = createContact("John Doe", "john@example.com", "555-123-4567");
		em.persist(contact);
		em.flush();
		tx.commit();

		tx.begin();
		Query insert = em.createNativeQuery("insert into contacts(name, email, phone) values(?, ?, ?)");
		insert.setParameter(1, "Jane Doe");
		insert.setParameter(2, "jane@example.com");
		insert.setParameter(3, "555-456-7890");
		insert.executeUpdate();
		tx.rollback();

		em.close();
		emf.close();
	}
}
