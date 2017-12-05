package com.mq310.ent.org.accounts.movements;

import com.mq310.ent.BaseEntity;
import com.mq310.ent.org.accounts.AccountsPlanItem;
import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;

@Entity
public class Movement extends BaseEntity {
    
    private ObjectProperty<Date> creationDateProperty = new SimpleObjectProperty<>(new Date());
    
    private DoubleProperty amountProperty = new SimpleDoubleProperty(0.0);
    
    private ObjectProperty<AccountsPlanItem> accountsPlanItemProperty = new SimpleObjectProperty<>(new AccountsPlanItem(0.0));

    public Movement() {
        super();
    }
    
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getCreationDate(){
        return creationDateProperty.get();
    }
    
    public void setCreationDate(Date date){
        creationDateProperty.set(date);
    }

    @Transient
    public ObjectProperty<Date> getCreationDateProperty() {
        return creationDateProperty;
    }

    public Double getAmount(){
        return amountProperty.get();
    }
    
    public void setAmount(Double amount){
        amountProperty.set(amount);
    }

    @Transient
    public DoubleProperty getAmountProperty() {
        return amountProperty;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public AccountsPlanItem getAccountsPlanItem(){
        return accountsPlanItemProperty.get();
    }
    
    public void setAccountsPlanItem(AccountsPlanItem item){
        accountsPlanItemProperty.set(item);
    }

    @Transient
    public ObjectProperty<AccountsPlanItem> getAccountsPlanItemProperty() {
        return accountsPlanItemProperty;
    }

}
