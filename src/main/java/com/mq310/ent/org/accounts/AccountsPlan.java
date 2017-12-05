/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org.accounts;

import com.mq310.ent.BaseEntity;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.SortComparator;

/**
 *
 * @author Administrador
 */
@Entity
public class AccountsPlan extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -7697760833217017441L;

    private final BooleanProperty enableProperty = new SimpleBooleanProperty(true);
    private final ObjectProperty<Date> creationDateProperty = new SimpleObjectProperty<>(new Date());
    private final ObjectProperty<SortedSet<AccountsPlanItem>> accountsPlanItemsProperty = new SimpleObjectProperty<>(new TreeSet<>());
    
    public AccountsPlan() {
    }

    public Boolean getEnable() {
        return enableProperty.get();
    }

    public void setEnable(Boolean enable) {
        this.enableProperty.set(enable);
    }

    @Transient
    public BooleanProperty getEnableProperty() {
        return enableProperty;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreationDate() {
        return creationDateProperty.get();
    }

    public void setCreationDate(Date creationDate) {
        this.creationDateProperty.set(creationDate);
    }

    @Transient
    public ObjectProperty<Date> getCreationDateProperty() {
        return creationDateProperty;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountsPlan")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<AccountsPlanItem> getAccountsPlanItems() {
        return accountsPlanItemsProperty.get();
    }

    public void setAccountsPlanItems(SortedSet<AccountsPlanItem> accountsPlanItems) {
        this.accountsPlanItemsProperty.set(accountsPlanItems);
    }
    
    public void addAccountPlanItem(AccountsPlanItem item){
        this.accountsPlanItemsProperty.get().add(item);
    }

    @Transient
    public ObjectProperty<SortedSet<AccountsPlanItem>> getAccountsPlanItemsProperty() {
        return accountsPlanItemsProperty;
    }
    
    @Override
    public String toString() {
        return "Plan de cuentas ("+(getEnable()?"activo":"inactivo")+") Version: "+getCreationDate().getTime();
    }
}
