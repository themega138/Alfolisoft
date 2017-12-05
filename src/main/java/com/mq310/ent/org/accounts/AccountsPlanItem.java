/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org.accounts;

import com.mq310.ent.BaseEntity;
import com.mq310.ent.org.accounts.movements.Movement;
import com.mq310.ent.org.counting.SpecificOffering;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;

/**
 *
 * @author Administrador
 */
@Entity
public class AccountsPlanItem extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1051800795921981155L;

    private DoubleProperty percentProperty = new SimpleDoubleProperty(0.0);
    private ObjectProperty<Account> accountProperty = new SimpleObjectProperty<>(new Account());
    private ObjectProperty<AccountsPlan> accountsPlanProperty = new SimpleObjectProperty<>(new AccountsPlan());
    private final ObjectProperty<SortedSet<SpecificOffering>> specificOfferingsProperty = new SimpleObjectProperty<>(new TreeSet<>());
    private final ObjectProperty<SortedSet<Movement>> movementsProperty = new SimpleObjectProperty<>(new TreeSet<>());

    public AccountsPlanItem() {
        super(0);
    }

    public AccountsPlanItem(Double percent) {
        percentProperty.set(percent);
    }

    public Double getPercent() {
        return percentProperty.get();
    }

    public void setPercent(Double percent) {
        this.percentProperty.set(percent);
    }

    @Transient
    public DoubleProperty getPercentProperty() {
        return percentProperty;
    }

    public void setPercentProperty(DoubleProperty percentProperty) {
        this.percentProperty = percentProperty;
    }

    @ManyToOne
    public Account getAccount() {
        return accountProperty.get();
    }

    public void setAccount(Account account) {
        this.accountProperty.set(account);
    }

    @Transient
    public ObjectProperty<Account> getAccountProperty() {
        return accountProperty;
    }

    public void setAccountProperty(ObjectProperty<Account> accountProperty) {
        this.accountProperty = accountProperty;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public AccountsPlan getAccountsPlan() {
        return accountsPlanProperty.get();
    }

    public void setAccountsPlan(AccountsPlan accountsPlan) {
        this.accountsPlanProperty.set(accountsPlan);
    }

    @Transient
    public ObjectProperty<AccountsPlan> getAccountsPlanProperty() {
        return accountsPlanProperty;
    }

    public void setAccountsPlanProperty(
            ObjectProperty<AccountsPlan> accountsPlanProperty) {
        this.accountsPlanProperty = accountsPlanProperty;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountsPlanItem")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<SpecificOffering> getSpecificOfferings() {
        return specificOfferingsProperty.get();
    }

    public void setSpecificOfferings(SortedSet<SpecificOffering> specificOfferings) {
        this.specificOfferingsProperty.set(specificOfferings);
    }

    public void addSpecificOffering(SpecificOffering offering) {
        offering.setAccountsPlanItem(this);
        this.specificOfferingsProperty.get().add(offering);
    }

    @Transient
    public ObjectProperty<SortedSet<SpecificOffering>> getSpecificOfferingsProperty() {
        return specificOfferingsProperty;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountsPlanItem")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<Movement> getMovements() {
        return movementsProperty.get();
    }

    public void setMovements(SortedSet<Movement> movements) {
        this.movementsProperty.set(movements);
    }

    public void addMovement(Movement movement) {
        movement.setAccountsPlanItem(this);
        movementsProperty.get().add(movement);
    }

    @Transient
    public ObjectProperty<SortedSet<Movement>> getMovementsProperty() {
        return movementsProperty;
    }

    @Transient
    public Double getAccountBalance() {
        Double amount = 0.0;
        amount = getMovements().stream().map((movement) -> movement.getAmount()).reduce(amount, (accumulator, _item) -> accumulator + _item);
        return amount;
    }

    @Override
    public String toString() {
        return getAccount().getName() + ": " + getPercent() + "%" + " (" + getAccountBalance() + " Bs.)";
    }

}
