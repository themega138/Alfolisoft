/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.org.counting;

import com.mq310.ent.BaseEntity;
import com.mq310.ent.persons.Member;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
public class Packet extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 8247839305039888815L;

    private final IntegerProperty numberProperty = new SimpleIntegerProperty(0);
    private final ObjectProperty<Counting> countingProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<Member> memberProperty = new SimpleObjectProperty<>(new Member());
    private final DoubleProperty titheProperty = new SimpleDoubleProperty(0.0);
    private final DoubleProperty offeringProperty = new SimpleDoubleProperty(0.0);
    private final ObjectProperty<SortedSet<SpecificOffering>> specificOfferingsProperty = new SimpleObjectProperty<>(new TreeSet<>());

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "packet", orphanRemoval = true,
            targetEntity = SpecificOffering.class)
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<SpecificOffering> getSpecificOfferings() {
        return specificOfferingsProperty.get();
    }

    public void setSpecificOfferings(SortedSet<SpecificOffering> list) {
        this.specificOfferingsProperty.set(list);
    }

    public void addSpecificOfferings(SpecificOffering offering) {
        offering.setPacket(this);
        this.specificOfferingsProperty.get().add(offering);
    }

    public void clearSpecificOfferings() {
        this.specificOfferingsProperty.get().clear();
    }

    public void removeSpecificOffering(SpecificOffering offering) {
        this.specificOfferingsProperty.get().removeIf((SpecificOffering t) -> {
            return (Objects.equals(offering.getAccountsPlanItem().getId(),
                    t.getAccountsPlanItem().getId()))
                    && (Objects.equals(offering.getAmount(), t.getAmount()));
        });
    }

    public Integer getNumber() {
        return numberProperty.get();
    }

    public void setNumber(Integer number) {
        numberProperty.set(number);
    }

    @Transient
    public IntegerProperty getNumberProperty() {
        return numberProperty;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Counting getCounting() {
        return countingProperty.get();
    }

    public void setCounting(Counting counting) {
        this.countingProperty.set(counting);
    }

    @Transient
    public ObjectProperty<Counting> getCountingProperty() {
        return countingProperty;
    }

    @ManyToOne
    public Member getMember() {
        return memberProperty.get();
    }

    public void setMember(Member member) {
        this.memberProperty.set(member);
    }

    @Transient
    public ObjectProperty<Member> getMemberProperty() {
        return memberProperty;
    }

    public Double getTithe() {
        return titheProperty.get();
    }

    public void setTithe(Double tithe) {
        this.titheProperty.set(tithe);
    }

    @Transient
    public DoubleProperty getTitheProperty() {
        return titheProperty;
    }

    public Double getOffering() {
        return offeringProperty.get();
    }

    public void setOffering(Double offering) {
        this.offeringProperty.set(offering);
    }

    @Transient
    public DoubleProperty getOfferingProperty() {
        return offeringProperty;
    }

    @Transient
    public Double getPacketTotal() {
        Double total = 0.0;
        total = getSpecificOfferings().stream().map((specificOffering) -> specificOffering.getAmount()).reduce(total, (accumulator, _item) -> accumulator + _item);
        total += getTithe();
        total += getOffering();
        return total;
    }

}
