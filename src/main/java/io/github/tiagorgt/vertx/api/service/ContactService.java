package io.github.tiagorgt.vertx.api.service;

import java.util.List;

import io.github.tiagorgt.vertx.api.entity.Contact;
import io.github.tiagorgt.vertx.api.repository.ContactDao;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class ContactService {
    private ContactDao contactDao = ContactDao.getInstance();

    public void list(Handler<AsyncResult<List<Contact>>> handler){
        Future<List<Contact>> future = Future.future();
        future.setHandler(handler);

        try {
        
            List<Contact> result = contactDao.findAll();
            future.complete(result);
        } catch (Throwable ex) {
            future.fail(ex);
        }
    }
    
    
    public void save(Contact newContact, Handler<AsyncResult<Contact>> handler) {
        Future<Contact> future = Future.future();
        future.setHandler(handler);
        

        try {
        	contactDao.persist(newContact);
            future.complete();
        } catch (Throwable ex) {
            future.fail(ex);
            
        }
    }
    
    public void update(String id,Contact contact, Handler<AsyncResult<Contact>> handler) {
        Future<Contact> future = Future.future();
        future.setHandler(handler);

        try {
        	contactDao.mergeById(id,contact.getName(),contact.getAddress(),contact.getCity(),contact.getEmail(),contact.getPhone()
        			, contact.getTitle());
            future.complete();
        } catch (Throwable ex) {
            future.fail(ex);
        }
    }

    public void remove(String id, Handler<AsyncResult<Contact>> handler) {
        Future<Contact> future = Future.future();
        future.setHandler(handler);

        try {
        	contactDao.removeById(id);
            future.complete();
        } catch (Throwable ex) {
            future.fail(ex);
        }
    }
    
}
