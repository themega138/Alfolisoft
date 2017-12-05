/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.ent.binnacle;

import com.mq310.ent.BaseEntity;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;

/**
 *
 * @author Moises
 */
@Entity
public class Binnacle extends BaseEntity{
    
    private final ObjectProperty<SortedSet<BinnacleEntry>> entriesProperty = new SimpleObjectProperty<>(new TreeSet<>());

    public Binnacle() {
        super();
    }
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "binnacle")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<BinnacleEntry> getEntries(){
        return entriesProperty.get();
    }
    
    public void setEntries(SortedSet<BinnacleEntry> entries){
        this.entriesProperty.set(entries);
    }
    
}
