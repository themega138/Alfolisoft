/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org.counting;

import com.mq310.ent.BaseEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author Administrador
 */
@Entity
public class CashCount extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -9024255792515773365L;

    private IntegerProperty cant_100Property = new SimpleIntegerProperty(0);
    private IntegerProperty cant_50Property = new SimpleIntegerProperty(0);
    private IntegerProperty cant_20Property = new SimpleIntegerProperty(0);
    private IntegerProperty cant_10Property = new SimpleIntegerProperty(0);
    private IntegerProperty cant_5Property = new SimpleIntegerProperty(0);
    private IntegerProperty cant_2Property = new SimpleIntegerProperty(0);
    private IntegerProperty cant_1Property = new SimpleIntegerProperty(0);
    private IntegerProperty cant_050Property = new SimpleIntegerProperty(0);
    private IntegerProperty cant_025Property = new SimpleIntegerProperty(0);
    private IntegerProperty cant_0125Property = new SimpleIntegerProperty(0);
    private IntegerProperty cant_010Property = new SimpleIntegerProperty(0);
    private IntegerProperty cant_005Property = new SimpleIntegerProperty(0);
    private ObjectProperty<Counting> countingProperty = new SimpleObjectProperty<>();

    public Integer getCant_100() {
        return cant_100Property.get();
    }

    public void setCant_100(Integer cant_100) {
        this.cant_100Property.set(cant_100);
    }

    @Transient
    public IntegerProperty getCant_100Property() {
        return cant_100Property;
    }

    public Integer getCant_50() {
        return cant_50Property.get();
    }

    public void setCant_50(Integer cant_50) {
        this.cant_50Property.set(cant_50);
    }

    @Transient
    public IntegerProperty getCant_50Property() {
        return cant_50Property;
    }

    public Integer getCant_20() {
        return cant_20Property.get();
    }

    public void setCant_20(Integer cant_20) {
        this.cant_20Property.set(cant_20);
    }

    @Transient
    public IntegerProperty getCant_20Property() {
        return cant_20Property;
    }

    public Integer getCant_10() {
        return cant_10Property.get();
    }

    public void setCant_10(Integer cant_10) {
        this.cant_10Property.set(cant_10);
    }

    @Transient
    public IntegerProperty getCant_10Property() {
        return cant_10Property;
    }

    public Integer getCant_5() {
        return cant_5Property.get();
    }

    public void setCant_5(Integer cant_5) {
        this.cant_5Property.set(cant_5);
    }

    @Transient
    public IntegerProperty getCant_5Property() {
        return cant_5Property;
    }

    public Integer getCant_2() {
        return cant_2Property.get();
    }

    public void setCant_2(Integer cant_2) {
        this.cant_2Property.set(cant_2);
    }

    @Transient
    public IntegerProperty getCant_2Property() {
        return cant_2Property;
    }

    public Integer getCant_1() {
        return cant_1Property.get();
    }

    public void setCant_1(Integer cant_1) {
        this.cant_1Property.set(cant_1);
    }

    @Transient
    public IntegerProperty getCant_1Property() {
        return cant_1Property;
    }

    public Integer getCant_050() {
        return cant_050Property.get();
    }

    public void setCant_050(Integer cant_050) {
        this.cant_050Property.set(cant_050);
    }

    @Transient
    public IntegerProperty getCant_050Property() {
        return cant_050Property;
    }

    public Integer getCant_025() {
        return cant_025Property.get();
    }

    public void setCant_025(Integer cant_025) {
        this.cant_025Property.set(cant_025);
    }

    @Transient
    public IntegerProperty getCant_025Property() {
        return cant_025Property;
    }

    public Integer getCant_0125() {
        return cant_0125Property.get();
    }

    public void setCant_0125(Integer cant_0125) {
        this.cant_0125Property.set(cant_0125);
    }

    @Transient
    public IntegerProperty getCant_0125Property() {
        return cant_0125Property;
    }

    public Integer getCant_010() {
        return cant_010Property.get();
    }

    public void setCant_010(Integer cant_010) {
        this.cant_010Property.set(cant_010);
    }

    @Transient
    public IntegerProperty getCant_010Property() {
        return cant_010Property;
    }

    public Integer getCant_005(){
        return cant_005Property.get();
    }
    
    public void setCant_005(Integer cant_005){
        this.cant_005Property.set(cant_005);
    }

    @Transient
    public IntegerProperty getCant_005Property() {
        return cant_005Property;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cashCount")
    public Counting getCounting() {
        return countingProperty.get();
    }

    public void setCounting(Counting counting) {
        this.countingProperty.set(counting);
    }
    
    @Transient
    public Double getCashCountTotal(){
        return ((getCant_100()*100.0) +
                (getCant_50()*50.0) +
                (getCant_20()*20.0) +
                (getCant_10()*10.0) +
                (getCant_5()*5.0) +
                (getCant_2()*2.0) +
                (getCant_1()*1.0) +
                (getCant_050()*0.5) +
                (getCant_025()*0.25) +
                (getCant_0125()*0.125) +
                (getCant_010()*0.10) +
                (getCant_005()*0.05)
                );
    }

    @Transient
    public ObjectProperty<Counting> getCountingProperty() {
        return countingProperty;
    }

}
