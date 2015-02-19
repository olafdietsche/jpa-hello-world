// Copyright (c) 2015 Olaf Dietsche

//package de.olafdietsche.jpa.hello_world;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
class Contact {
	@Id
	public int id;
	public String name;
	public String email;
	public String phone;
}

public class HelloWorld {
	public static Contact createContact(int id, String name, String email, String phone) {
		Contact contact = new Contact();
		contact.id = id;
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

		Contact contact = createContact(7, "John Doe", "john@example.com", "555-123-4567");
		em.persist(contact);
		em.flush();
		tx.commit();

		tx.begin();
		Query insert = em.createNativeQuery("insert into contacts(id, name, email, phone) values(?, ?, ?, ?)");
		insert.setParameter(1, 8);
		insert.setParameter(2, "Jane Doe");
		insert.setParameter(3, "jane@example.com");
		insert.setParameter(4, "555-456-7890");
		insert.executeUpdate();
		tx.rollback();

		em.close();
		emf.close();
	}
}
