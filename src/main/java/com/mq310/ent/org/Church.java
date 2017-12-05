/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org;

import com.mq310.ent.persons.Member;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.beans.property.ObjectProperty;
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
public class Church extends Organization {

    /**
     *
     */
    private static final long serialVersionUID = -933684498400095722L;

    private final ObjectProperty<SortedSet<Member>> membersProperty = new SimpleObjectProperty<>(new TreeSet<>());
    private ObjectProperty<Association> associationProperty = new SimpleObjectProperty<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "church")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<Member> getMembers() {
        return membersProperty.get();
    }

    public void setMembers(SortedSet<Member> members) {
        this.membersProperty.set(members);
    }
    
    @Transient
    public ObjectProperty<SortedSet<Member>> getMembersProperty() {
        return membersProperty;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    public Association getAssociation() {
        return associationProperty.get();
    }

    public void setAssociation(Association association) {
        this.associationProperty.set(association);
    }

    @Transient
    public ObjectProperty<Association> getAssociationProperty() {
        return associationProperty;
    }

    public void setAssociationProperty(
            ObjectProperty<Association> associationProperty) {
        this.associationProperty = associationProperty;
    }
}
