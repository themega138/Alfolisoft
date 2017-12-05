package com.mq310.ent.org;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.mq310.ent.BaseEntity;

@Entity
public class OrganizationBase extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -7813749597329468002L;

    private StringProperty nameProperty = new SimpleStringProperty();
    private StringProperty addressProperty = new SimpleStringProperty();

    public OrganizationBase() {
        super();
    }

    public OrganizationBase(Integer id) {
        super(id);
    }

    public String getName() {
        return nameProperty.get();
    }

    public void setName(String name) {
        this.nameProperty.set(name);
    }

    @Transient
    public StringProperty getNameProperty() {
        return nameProperty;
    }

    public String getAddress() {
        return addressProperty.get();
    }

    public void setAddress(String address) {
        this.addressProperty.set(address);
    }

    @Transient
    public StringProperty getAddressProperty() {
        return addressProperty;
    }

}
