/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Association extends Organization {

    /**
     *
     */
    private static final long serialVersionUID = -8799328457379758707L;

    private final ObjectProperty<SortedSet<Church>> churchesProperty = new SimpleObjectProperty<>(new TreeSet<>());
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "association")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<Church> getChurches() {
        return churchesProperty.get();
    }

    public void setChurches(SortedSet<Church> churches) {
        this.churchesProperty.set(churches);
    }

    @Transient
    public ObjectProperty<SortedSet<Church>> getChurchesProperty() {
        return churchesProperty;
    }
    
}
