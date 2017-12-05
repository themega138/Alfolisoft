/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.ent.persons;

import com.mq310.ent.org.Church;
import com.mq310.ent.org.counting.Packet;
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

@Entity
public class Member extends Person {

    /**
     *
     */
    private static final long serialVersionUID = -8476197879136475976L;

    private final ObjectProperty<Church> churchProperty = new SimpleObjectProperty<>();
    private final ObjectProperty<SortedSet<Packet>> packetsProperty = new SimpleObjectProperty<>(new TreeSet<>());

    public Member() {
        super();
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    public Church getChurch() {
        return churchProperty.get();
    }

    public void setChurch(Church church) {
        this.churchProperty.set(church);
    }

    @Transient
    public ObjectProperty<Church> getChurchProperty() {
        return churchProperty;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "member")
    @Fetch(FetchMode.SELECT)
    @OrderBy(clause = "id")
    public SortedSet<Packet> getPackets() {
        return packetsProperty.get();
    }

    public void setPackets(SortedSet<Packet> packets) {
        this.packetsProperty.set(packets);
    }
    
    public void addPacket(Packet packet){
        packet.setMember(this);
        this.packetsProperty.get().add(packet);
    }
    
    public void removePacket(Packet packet){
        this.packetsProperty.get().remove(packet);
    }
}
