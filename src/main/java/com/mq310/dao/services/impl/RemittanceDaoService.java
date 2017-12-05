/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.dao.services.impl;

import com.mq310.dao.Dao;
import com.mq310.dao.services.IRemittanceService;
import com.mq310.ent.org.counting.Counting;
import com.mq310.ent.org.counting.Remittance;
import com.mq310.ent.org.counting.RemittanceStatus;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Moises
 */
public class RemittanceDaoService extends GeneralService implements IRemittanceService {

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Remittance getActualRemittance() {
        RemittanceServiceHelper helper = new RemittanceServiceHelper(dao);
        Remittance r = null;
        if (helper.getRemittanceOpenedCount() > 0) {
            r = helper.getLastOpenedRemittance();
        } else {
            r = helper.createNewRemittance();
        }
        if (r.getId() == 0) {
            r.setId(dao.save(new Remittance()));
            
            r = dao.merge(r);
        }
        return r;
    }

    private class RemittanceServiceHelper {

        private Dao dao;

        public RemittanceServiceHelper(Dao dao) {
            this.dao = dao;
        }

        public Long getRemittanceOpenedCount() {
            return dao.get(Long.class, "select count(r.id) from Remittance as r"
                    + " where r.remittanceStatus = '" + RemittanceStatus.OPENED.toString() + "'");
        }

        public Remittance getLastOpenedRemittance() {
            Integer id = dao.get(Integer.class, "select max(r.id) from Remittance as r"
                    + " where r.remittanceStatus = '" + RemittanceStatus.OPENED.toString() + "'");
            return dao.get(Remittance.class, id);
        }

        private Remittance getLastRemittance() {
            Integer id = dao.get(Integer.class, "select max(r.id) from Remittance as r"
                    + " "
                    + " where r.remittanceStatus = '" + RemittanceStatus.CLOSED.toString() + "'");
            return dao.get(Remittance.class, id);
        }

        public Remittance createNewRemittance() {
            Remittance r;
            if (getRemittanceCount() > 0) {
                r = initNewRemittance(getLastRemittance().getClosingDate());
            } else {
                r = initNewRemittance(new Date());
            }
            
            return r;
        }

        private Remittance initNewRemittance(Date date) {
            Remittance r = new Remittance();
            r.setCreationDate(getNextRemittanceOpenDate(date));
            r.setClosingDate(getNextRemittanceCloseDate(r.getCreationDate()));
            r = populateRemittance(r);
            return r;
        }

        private Remittance populateRemittance(Remittance remittance) {
            Remittance r = remittance;
            Date date = r.getCreationDate();
            Counting c = null;
            while (date.getTime() < r.getClosingDate().getTime()) {
                c = new Counting();
                c.setCreationDate(date);
                r.addCounting(c);
                date = getNextCountingDate(date);
            }
            return r;
        }

        private Long getRemittanceCount() {
            return dao.get(Long.class, "select count(r.id) from Remittance as r");
        }

        private Date getNextRemittanceCloseDate(Date date) {
            Date d = date;
            LocalDate ld = dateToLocalDate(d);
            LocalDate ldc = ld.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.FRIDAY));
            if (ldc.isBefore(ld)) {
                ld = ld.plusMonths(1);
                ldc = ld.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.FRIDAY));
                d = localDateToDate(ldc);
            } else {
                d = localDateToDate(ldc);
            }
            return toEndOfDay(d);
        }

        private Date getNextRemittanceOpenDate(Date date) {
            Date d = date;
            LocalDate ld = dateToLocalDate(d);
            LocalDate ldc = ld.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.SATURDAY));
            if (ld.isAfter(ldc)) {
                ld = ld.plusMonths(1);
                ldc = ld.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.SATURDAY));
                d = localDateToDate(ldc);
            } else {
                d = localDateToDate(ldc);
            }
            return toStartOfDay(d);
        }

        private Date getNextCountingDate(Date date) {
            LocalDate ld = dateToLocalDate(date);
            LocalDate ldc = ld.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
            return toStartOfDay(localDateToDate(ldc));
        }

        private Date localDateToDate(LocalDate localDate) {
            return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        }

        private LocalDate dateToLocalDate(Date date) {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        private Date toStartOfDay(Date date) {
            Date d = date;
            LocalDate ldt = dateToLocalDate(d);
            d = Date.from((ldt.atTime(0, 0, 0).atZone(ZoneId.systemDefault())).toInstant());
            return d;
        }

        private Date toEndOfDay(Date date) {
            Date d = date;
            LocalDate ldt = dateToLocalDate(d);
            d = Date.from((ldt.atTime(23, 59, 59).atZone(ZoneId.systemDefault())).toInstant());
            return d;
        }
    }
}
