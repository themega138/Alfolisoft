package com.mq310.ent.org.accounts.movements;

import com.mq310.ent.org.counting.docs.Document;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;

@Entity
public class FundsTransfer extends Document {
    
    private ObjectProperty<Date> creationDateProperty = new SimpleObjectProperty<>(new Date());
    private final ObjectProperty<SortedSet<FundsTransferItem>> fundsTransferItemsProperty = new SimpleObjectProperty<>(new TreeSet<>());
    private final StringProperty descriptionProperty = new SimpleStringProperty("");

    public FundsTransfer() {
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

    public void setCreationDateProperty(ObjectProperty<Date> creationDateProperty) {
        this.creationDateProperty = creationDateProperty;
    }
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "fundsTransfer")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<FundsTransferItem> getFundsTransferItems(){
        return fundsTransferItemsProperty.get();
    }
    
    public void setFundsTransferItems(SortedSet<FundsTransferItem> items){
        this.fundsTransferItemsProperty.set(items);
    }
    
    public void addFundsTransferItem(FundsTransferItem item){
        item.setFundsTransfer(this);
        this.fundsTransferItemsProperty.get().add(item);
    }
    
    public void removeFundsTransferItem(FundsTransferItem item){
        this.fundsTransferItemsProperty.get().remove(item);
    }

    @Transient
    public ObjectProperty<SortedSet<FundsTransferItem>> getFundsTransferItemsProperty() {
        return fundsTransferItemsProperty;
    }
    
    public String getDescription(){
        return descriptionProperty.get();
    }
    
    public void setDescription(String description){
        descriptionProperty.set(description);
    }

    @Transient
    public StringProperty getDescriptionProperty() {
        return descriptionProperty;
    }

}
