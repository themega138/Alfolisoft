/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.util;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionProperty<E> extends SimpleObjectProperty<List<E>> {

    private ObservableList<E> list = FXCollections.observableArrayList();

    private Boolean used = false;

    public CollectionProperty() {
        super(new ArrayList<>());
    }

    public void add(E e) {
        list.add(e);
    }
    
    public void remove(E e){
        list.remove(e);
    }

    @Override
    public void set(List<E> newValue) {
        if (newValue != null) {
            list.clear();
            list.addAll(newValue);
        }
//        super.set(newValue);
//        if (newValue != null) {
//            if (used) {
//                if (list == null) {
//                    list = FXCollections.observableArrayList(get());
//                } else {
//                    list.clear();
//                    list.addAll(get());
//                }
//            }
//        }
    }

    @Override
    public void setValue(List<E> v) {
        set(v);
    }

    public ObservableList<E> getObservableList() {
//        if (!used) {
//            if (get() != null) {
//                list = FXCollections.observableArrayList(get());
//            }
//            used = true;
//        }
        return list;
    }
    
    public void clear(){
//        super.get().removeAll(list);
        this.list.clear();
    }

}
