package com.example.clientapp.services;

import com.example.clientapp.exceptions.ValidationException;
import com.example.clientapp.model.AddressEntity;
import com.example.clientapp.model.ClientEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

@Stateful
public class UpdateBean {
    @EJB
    private DbManager dbManager;

    @Transactional
    public void createClient(ClientEntity temp) {
        AddressEntity tempAddress = temp.getAddresses().get(0);
        Pattern pName = Pattern.compile("^[А-я]$", CASE_INSENSITIVE);
        Pattern pIp = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}\\.\\d{3}$");
        Pattern pMac = Pattern.compile("^[a-z0-9]{2}-[a-z0-9]{2}-[a-z0-9]{2}-[a-z0-9]{2}-[a-z0-9]{2}-[a-z0-9]{2}$", CASE_INSENSITIVE);
        String[] ipNums = tempAddress.getIp().split("\\.");

//        if (temp.getName() != null && temp.getName().length() <= 100 && pName.matcher(temp.getName()).matches()) {
//
//        } else {
//            throw new ValidationException("Ошибка ввода данных: недопустимые значения");
//        }
//        if (temp.getType() != null && temp.getName().length() <= 100
//                && (temp.getType().equals("Юридическое лицо") || temp.getType().equals("Физическое лицо"))) {
//
//        } else {
//            throw new ValidationException("Ошибка ввода данных: недопустимые значения");
//        }
//        if (temp.getAdded() != null) {
//
//        } else {
//            throw new ValidationException("Ошибка ввода данных: недопустимые значения");
//        }
//        if (tempAddress.getIp() != null && tempAddress.getIp().length() <= 20 && pIp.matcher(tempAddress.getIp()).matches()
//                && Integer.parseInt(ipNums[0]) <= 255 && Integer.parseInt(ipNums[1]) <= 255 && Integer.parseInt(ipNums[2]) <= 255
//                && Integer.parseInt(ipNums[3]) <= 255) {
//
//        } else {
//            throw new ValidationException("Ошибка ввода данных: недопустимые значения");
//        }
//        if (tempAddress.getMac() != null && tempAddress.getMac().length() <= 20 && pMac.matcher(tempAddress.getMac()).matches()) {
//
//        } else {
//            throw new ValidationException("Ошибка ввода данных: недопустимые значения");
//        }
//        if (tempAddress.getModel() != null && tempAddress.getModel().length() <= 100) {
//
//        } else {
//            throw new ValidationException("Ошибка ввода данных: недопустимые значения");
//        }
//        if (tempAddress.getAddress() != null && tempAddress.getAddress().length() <= 200) {
//
//        } else {
//            throw new ValidationException("Ошибка ввода данных: недопустимые значения");
//        }
        dbManager.saveClient(temp);
    }

    public void updateClient(ClientEntity temp) {
        long id = temp.getId();
        ClientEntity exist = dbManager.findClientById(id);
        exist.setName(temp.getName());
        exist.setType(temp.getType());
        exist.setAdded(temp.getAdded());
        List<AddressEntity> tempAddresses = temp.getAddresses();
        exist.setAddresses(tempAddresses);
        dbManager.saveClient(temp);
    }

    public void deleteClient(ClientEntity client) {
        dbManager.deleteClient(client);
    }
}