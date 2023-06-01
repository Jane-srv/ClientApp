package com.example.clientapp.services;

import com.example.clientapp.model.ClientEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateful
public class SelectBean {

    private static Set<ClientEntity> clients;
    @EJB
    private DbManager dbManager;

    public Set<ClientEntity> getAllClient() {
        List<ClientEntity> allClients = dbManager.findAllClient();
        clients = new HashSet<>(allClients);
        return clients;
    }

    public Set<ClientEntity> getClientsParam(String filterParam, String typesParam) {
        List<ClientEntity> paramClients = dbManager.findClientByParam(filterParam, typesParam);
        clients = new HashSet<>(paramClients);
        return clients;
    }

    public ClientEntity getClientById(long id){
        return dbManager.findClientById(id);
    }
}