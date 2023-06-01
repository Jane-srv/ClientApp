package com.example.clientapp.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "client", schema = "test_db", catalog = "")
public class ClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "added")
    private Date added;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<AddressEntity> addresses = new ArrayList<>();

    public ClientEntity(long id, String name, String type, Date added) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.added = added;
    }

    public ClientEntity(String name, String type, Date added) {
        this.name = name;
        this.type = type;
        this.added = added;
    }

    public ClientEntity() {

    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }
    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
    }

    public void setAddress(AddressEntity address){
        addresses.add(address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(added, that.added);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, added);
    }
}