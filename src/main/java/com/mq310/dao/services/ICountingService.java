/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.dao.services;

import com.mq310.ent.org.counting.Counting;
import com.mq310.ent.org.counting.Packet;
import com.mq310.ent.org.counting.docs.IncomingBankDocument;
import java.util.SortedSet;

/**
 *
 * @author Moises
 */
public interface ICountingService {
    
    public Counting getActualCounting();
    
    public Counting getCountingById(Integer id);
    
    public SortedSet<Counting> getAllCounting();
    
    public void closeCounting(Counting counting);
    
    public void saveActualCounting(Counting counting);
    
    public Integer getNewPacketNumber();
    
    public void updateCashCount();
    
    public Integer savePacket(Packet packet);
    
    public void updatePacket(Packet packet);
    
    public Packet getPacketById(Integer Id);
    
    public IncomingBankDocument saveDocument(IncomingBankDocument document);
    
    public void deletedDocument(IncomingBankDocument document);
    
    public Boolean finishCounting();
    
    public Double getChecksTotal();
    
    public Double getTransfersTotal();
    
    public Double getDepositsTotal();
    
    public Double getCashTotal();
    
    public Double getGeneralTotal();
    
    public Double getPacketsTotal();
    
    public Double getDiference();
    
    public Long getPacketsCount();
}
