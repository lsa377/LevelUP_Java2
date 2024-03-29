package org.levelup.bank.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.levelup.bank.domain.ManagerEntity;


public class ManagerRepository extends AbstractRepository{

    public ManagerRepository(SessionFactory factory){
        super(factory);
    }


    public ManagerEntity addManager(String firstName, String lastName){
        try(Session session = factory.openSession()){
            Transaction tx = session.beginTransaction();
            ManagerEntity manager = new ManagerEntity();
            manager.setFirstName(firstName);
            manager.setLastName(lastName);
            session.persist(manager);
            tx.commit();
            return manager;
        }
    }

    public ManagerEntity getById(Integer managerId){
   //     try(Session session = factory.openSession()){
   //         return session.get(ManagerEntity.class,managerId);
   //     }
        return supplyWithoutTransaction(s -> s.get(ManagerEntity.class,managerId));
    }
    
    public ManagerEntity loadById(Integer managerId){
    //    try(Session session = factory.openSession()){
    //        return session.load(ManagerEntity.class,managerId);
        return  supplyWithoutTransaction(s -> s.load(ManagerEntity.class,managerId));
    //    }
    }

}
