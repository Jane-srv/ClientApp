package com.example.clientapp.services;

import com.example.clientapp.model.AddressEntity;
import com.example.clientapp.model.ClientEntity;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@Singleton
public class DbManager {
    @PersistenceContext
    private EntityManager entityManager;

    public List<ClientEntity> findAllClient() {
        List<ClientEntity> clients = entityManager.createNativeQuery("select * from client", ClientEntity.class).getResultList();
        return clients;
    }

    public List<ClientEntity> findClientByParam(String filterParam, String typesParam) {
        String nameParam = new String("%" + filterParam.toLowerCase() + "%");
            Query query = entityManager.createNativeQuery("select * from client c JOIN address a on c.id = a.clientId " +
                    "where (c.name like :nameParam or a.address like :nameParam) and c.type= :typeParam ", ClientEntity.class);
            query.setParameter("nameParam", nameParam);
            query.setParameter("typeParam", typesParam);
            return query.getResultList();
    }

    public ClientEntity findClientById(long id) {
//        Query query = entityManager.createNativeQuery("select * from client where id = :id ", ClientEntity.class);
//        return (ClientEntity)query.getResultList().get(0);
        return entityManager.find(ClientEntity.class, id);
    }

    @Transactional
    public void saveClient(ClientEntity client) {
        entityManager.persist(client);
    }

    public void deleteClient(ClientEntity client) {
        entityManager.remove(client);
    }
}