package io.github.tiagorgt.vertx.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import io.github.tiagorgt.vertx.api.entity.Contact;


public class ContactDao {
    private static ContactDao instance;
    protected EntityManager entityManager;

    public static ContactDao getInstance(){
        if (instance == null){
            instance = new ContactDao();
        }

        return instance;
    }

    private ContactDao() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

//    public Contact getById(String id) {
//    	  Object result = entityManager.find(Contact.class, id);
//          if (result != null) {
//              return (Contact) result;
//          } else {
//              return null;
//          }
//      }
    

    @SuppressWarnings("unchecked")
    public List<Contact> findAll() {
    	
        return entityManager.createQuery("select c from Contact c order by c.id ").getResultList();
            }
    
  public void persist(Contact contact) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(contact);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    } 
    
    public void removeById(String id) {
        try {
        	entityManager.getTransaction().begin();
        	Query query= entityManager.createQuery("DELETE FROM Contact WHERE id = '"+id+"'");
        	query.executeUpdate();
    		entityManager.getTransaction().commit();
    		 		 
	  }  catch (Exception ex) {
	            ex.printStackTrace();
	            entityManager.getTransaction().rollback();      
    }
        
    }
  
    public void mergeById(String id, String name, String address,String city,String email,String phone, String title) {
        try {
        	
        	entityManager.getTransaction().begin();
            Query contact = entityManager.createQuery("UPDATE Contact set name='"+name+"',address='"+address+"',city='"+city+"',"
            		+ "email='"+email+"',  phone='"+phone+"',title='"+title+"'"
            		+ "WHERE id='"+id+"'");
            contact.executeUpdate();
    		entityManager.getTransaction().commit();
    		 		 
	  }  catch (Exception ex) {
	            ex.printStackTrace();
	            entityManager.getTransaction().rollback();      
    }
        }

    }
