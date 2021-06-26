package org.levelup.bank.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.levelup.bank.domain.ClientEntity;
import org.levelup.bank.util.DateUtils;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class ClientRepository{
    private final SessionFactory factory;

    public ClientEntity createClient(String firstName, String lastName, LocalDate birthday){
        try(Session session = factory.openSession()) {
           Transaction tx = session.beginTransaction();
           ClientEntity client = new ClientEntity();
           client.setFirstName(firstName);
           client.setLastName(lastName);
           client.setBirthday(birthday);
           session.persist(client);
           tx.commit();
           return client;
        }
    }

    public ClientEntity createClient(String firstName, String lastName, String middleName, LocalDate birthday){
        ClientEntity client = this.createClient(firstName, lastName, birthday);
        return this.editClient(client.getClientId(),null,null,middleName,null);
    }

    public ClientEntity editClient(long clientid, String firstName,String lastName, String middleName, LocalDate birthday){
        try(Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            ClientEntity client = session.load(ClientEntity.class,clientid);
            if(firstName != null) {
                client.setFirstName(firstName);
            }
            if(lastName != null){
                client.setLastName(lastName);
            }
            if(middleName != null){
                client.setMiddleName(middleName);
            }
            if(birthday != null){
               client.setBirthday(birthday);
            }
            session.merge(client);
            tx.commit();
            return  client;
        }
    }

    public void deleteClient(long clientid){
        try(Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createQuery("DELETE from ClientEntity where id = :clientid").setParameter("clientid",clientid).executeUpdate();
            tx.commit();
        }
    }

    public void printAllClients(){
        try(Session session = factory.openSession()) {
            List<ClientEntity> clients = session.createQuery("from ClientEntity").getResultList();
            for(ClientEntity client : clients){
                System.out.println("Client - ID: "+client.getClientId()+", first name: "+client.getFirstName()+", middle name :"+client.getMiddleName()+", last name: "+client.getLastName()+", birth date: "+client.getBirthday());
            }
        }
    }

    public Collection<ClientEntity> findClientsWhenBirthdayBetween(LocalDate begin, LocalDate end){
        try(Session session = factory.openSession()) {
            return session.createQuery("from ClientEntity where birthday between :begin and :end").setParameter("begin",begin).
                    setParameter("end",end).getResultList();
        }
    }
}