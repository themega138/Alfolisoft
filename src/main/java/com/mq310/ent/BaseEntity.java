/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 *
 * @author Administrador
 */
@MappedSuperclass
public class BaseEntity implements Serializable, Comparable<BaseEntity>{

    /**
     *
     */
    private static final long serialVersionUID = -3264494012206106292L;

    private IntegerProperty idProperty = new SimpleIntegerProperty(0);
    private final ObjectProperty<EntityStatus> statusProperty = new SimpleObjectProperty<>(EntityStatus.ENABLE);
    private final ObjectProperty<EntityCreator> creatorProperty = new SimpleObjectProperty<>(EntityCreator.USER);
    private final ObjectProperty<EntityVisibility> visibilityProperty = new SimpleObjectProperty<>(EntityVisibility.SHOW);

    public BaseEntity() {
    	idProperty.set(0);
    }

    public BaseEntity(Integer id) {
        this.idProperty.set(id);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return idProperty.get();
    }

    public void setId(Integer id) {
        idProperty.set(id);
    }

    @Transient
    public IntegerProperty getIdProperty() {
    	return idProperty;
    }
    
    public void setIdProperty(IntegerProperty idProperty) {
        this.idProperty = idProperty;
    }
    
    @Enumerated(EnumType.STRING)
    public EntityStatus getStatus(){
        return this.statusProperty.get();
    }
    
    public void setStatus(EntityStatus status){
        this.statusProperty.set(status);
    }
    
    @Enumerated(EnumType.STRING)
    public EntityVisibility getVisibility(){
        return this.visibilityProperty.get();
    }
    
    public void setVisibility(EntityVisibility visibility){
        this.visibilityProperty.set(visibility);
    }
    
    @Enumerated(EnumType.STRING)
    public EntityCreator getCreator(){
        return this.creatorProperty.get();
    }
    
    public void setCreator(EntityCreator creator){
        this.creatorProperty.set(creator);
    }

    @Override
    public int compareTo(BaseEntity o) {
        if ((o.getId() == 0) && (getId() == 0)) {
            return 1;
        } else {
            return getId()-o.getId();
        }
    }
    
    protected Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    protected LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
