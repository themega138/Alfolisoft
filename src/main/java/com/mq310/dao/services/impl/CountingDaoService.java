/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.dao.services.impl;

import com.mq310.dao.services.ICountingService;
import com.mq310.dao.services.IRemittanceService;
import com.mq310.ent.org.counting.Counting;
import com.mq310.ent.org.counting.CountingStatus;
import com.mq310.ent.org.counting.Packet;
import com.mq310.ent.org.counting.Remittance;
import com.mq310.ent.org.counting.SpecificOffering;
import com.mq310.ent.org.counting.docs.BankDocumentType;
import com.mq310.ent.org.counting.docs.IncomingBankDocument;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Moises
 */
public class CountingDaoService extends GeneralService implements ICountingService {

    private IRemittanceService remittanceService;
    private Counting actualCounting;

    @Override
    @Transactional
    public Counting getActualCounting() {
        Remittance remittance = remittanceService.getActualRemittance();
        Long openCountingCount = dao.get(Long.class, "select count(c.id) from Counting as c "
                + "where c.countingStatus = '" + CountingStatus.OPENED.toString() + "'");
        if (openCountingCount > 0) {
            actualCounting = dao.get(Counting.class, "from Counting as c "
                    + "where c.remittance.id = " + remittance.getId()
                    + " and c.countingStatus = '" + CountingStatus.OPENED.toString() + "'");
        } else {
            actualCounting = dao.get(Counting.class, "from Counting as c "
                    + "where c.remittance.id = " + remittance.getId()
                    + " and c.countingStatus = '" + CountingStatus.STANDBY.toString() + "' "
                    + " and c.id = (select min(co.id) from Counting as co where"
                    + " co.countingStatus = '" + CountingStatus.STANDBY.toString() + "'"
                    + " and co.remittance.id = " + remittance.getId() + ")");
        }
        if (actualCounting.getCountingStatus() != CountingStatus.OPENED) {
            actualCounting.setCountingStatus(CountingStatus.OPENED);
            actualCounting.setCountingStartTime(new Date());
            actualCounting = dao.merge(actualCounting);
        }
        actualCounting = dao.get(Counting.class, "from Counting as c "
                + "left join fetch c.packets "
                + "left join fetch c.incomingBankDocuments "
                + "where c.id = "+actualCounting.getId());
        return actualCounting;
    }

    @Override
    @Transactional
    public void saveActualCounting(Counting counting) {
        dao.saveOrUpdate(counting);
    }

    @Override
    @Transactional
    public Integer getNewPacketNumber() {
        Integer num = 0;
        Integer maxNum = dao.get(Integer.class, "select max(p.number) from Packet as p "
                + "where p.counting.remittance.id = " + actualCounting.getRemittance().getId());
        System.out.println(maxNum);
        if (maxNum == null) {
            num = 1;
        } else {
            num = (maxNum + 1);
        }
        return num;
    }

    @Override
    @Transactional
    public Integer savePacket(Packet packet) {
        Packet p = packet;
        if (p.getId() == 0) {
            p.setId(dao.save(new Packet()));
        }
        dao.merge(p);
        return p.getId();
    }

    @Override
    @Transactional
    public IncomingBankDocument saveDocument(IncomingBankDocument document) {
        if (document.getId() == 0) {
            document.setId(dao.save(new IncomingBankDocument()));
        }
        return dao.merge(document);
    }

    @Override
    @Transactional
    public Boolean finishCounting() {
        this.actualCounting.setCountingStatus(CountingStatus.FINISHED);
        this.actualCounting.setCountingFinishTime(new Date());
        dao.update(this.actualCounting);
        return true;
    }

    public void setRemittanceService(IRemittanceService remittanceService) {
        this.remittanceService = remittanceService;
    }

    @Override
    @Transactional
    public Packet getPacketById(Integer Id) {
        return dao.get(Packet.class, "from Packet as p "
                + "inner join fetch p.member "
                + "left join fetch p.specificOfferings as so "
                + "where p.id = " + Id);
    }

    @Override
    @Transactional
    public void updatePacket(Packet packet) {
        Packet p = dao.get(Packet.class, packet.getId());
        p.setMember(packet.getMember());
        p.setNumber(packet.getNumber());
        p.setOffering(packet.getOffering());
        p.setTithe(packet.getTithe());
        p.getSpecificOfferings().clear();
        for (Iterator<SpecificOffering> it = packet.getSpecificOfferings().iterator(); it.hasNext();) {
            SpecificOffering specificOffering = it.next();
            if (specificOffering.getId() == 0) {
                p.addSpecificOfferings(specificOffering);
            } else {
                if (specificOffering.getPacket() == null) {
                    dao.delete(specificOffering);
                } else {
                    p.addSpecificOfferings(dao.merge(specificOffering));
                }
            }
            it.remove();
        }
        dao.merge(p);
    }

    @Override
    @Transactional
    public Double getChecksTotal() {
        Double total = dao.get(Double.class, "select sum(ibd.amount) "
                + "From IncomingBankDocument as ibd "
                + "where ibd.type = '" + BankDocumentType.CHECK.toString() + "' "
                + "and ibd.counting.id = " + actualCounting.getId());
        if (total == null) {
            total = 0.0;
        }
        return total;
    }

    @Override
    @Transactional
    public Double getTransfersTotal() {
        Double total = dao.get(Double.class, "select sum(ibd.amount) "
                + "From IncomingBankDocument as ibd "
                + "where ibd.type = '" + BankDocumentType.TRANSFER.toString() + "' "
                + "and ibd.counting.id = " + actualCounting.getId());
        if (total == null) {
            total = 0.0;
        }
        return total;
    }

    @Override
    @Transactional
    public Double getDepositsTotal() {
        Double total = dao.get(Double.class, "select sum(ibd.amount) "
                + "From IncomingBankDocument as ibd "
                + "where ibd.type = '" + BankDocumentType.DEPOSIT.toString() + "' "
                + "and ibd.counting.id = " + actualCounting.getId());
        if (total == null) {
            total = 0.0;
        }
        return total;
    }

    @Override
    public Double getCashTotal() {
        return actualCounting.getCashCount().getCashCountTotal();
    }

    @Override
    @Transactional
    public Double getGeneralTotal() {
        return getCashTotal() + getChecksTotal() + getDepositsTotal() + getTransfersTotal();
    }

    @Override
    @Transactional
    public Double getPacketsTotal() {
        Double soTotal = dao.get(Double.class, "select sum(so.amount) from SpecificOffering as so where so.packet.counting.id = "+actualCounting.getId());
        if (soTotal == null) {
            soTotal = 0.0;
        }
        Double packetsTotal = dao.get(Double.class, "select sum(p.tithe + p.offering) from Packet as p where p.counting.id = "+actualCounting.getId());
        if (packetsTotal == null) {
            packetsTotal = 0.0;
        }
        Double total = soTotal + packetsTotal;
        return total;
    }

    @Override
    @Transactional
    public Double getDiference() {
        return getGeneralTotal() - getPacketsTotal();
    }

    @Override
    @Transactional
    public Long getPacketsCount() {
        Long total = dao.get(Long.class, "Select count(p.id) "
                + "From Packet as p "
                + "where p.counting.id = " + actualCounting.getId());
        return total;
    }

    @Override
    @Transactional
    public void updateCashCount() {
        dao.merge(actualCounting.getCashCount());
    }

    @Override
    @Transactional
    public void deletedDocument(IncomingBankDocument document) {
        dao.delete(document);
    }

    @Override
    @Transactional
    public Counting getCountingById(Integer id) {
        return dao.get(Counting.class, "from Counting as c "
                + "left join fetch c.packets "
                + "left join fetch c.incomingBankDocuments "
                + "where c.id = "+actualCounting.getId());
    }

    @Override
    @Transactional
    public SortedSet<Counting> getAllCounting() {
        return new TreeSet<>(dao.execute(Counting.class,
                "from Counting as c inner join c.remittance"));
    }

    @Override
    @Transactional
    public void closeCounting(Counting counting) {
        
    }

}
