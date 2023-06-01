package com.example.clientapp.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "address", schema = "test_db", catalog = "")
public class AddressEntity {
    @Id
    @Column(name = "ip")
    private String ip;
    @Basic
    @Column(name = "mac")
    private String mac;
    @Basic
    @Column(name = "model")
    private String model;
    @Basic
    @Column(name = "address")
    private String address;

    @JoinColumn(name = "clientId")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ClientEntity client;

    public AddressEntity(String ip, String mac, String model, String address) {
        this.ip = ip;
        this.mac = mac;
        this.model = model;
        this.address = address;
    }

    public AddressEntity() {

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(ip, that.ip) && Objects.equals(mac, that.mac) && Objects.equals(model, that.model) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, mac, model, address);
    }
}