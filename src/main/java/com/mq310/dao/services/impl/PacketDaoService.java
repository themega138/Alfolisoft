/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.dao.services.impl;

import com.mq310.dao.services.IPacketService;
import com.mq310.ent.org.counting.SpecificOffering;


public class PacketDaoService extends GeneralService implements IPacketService {

    @Override
    public void deleteSpecificOffering(SpecificOffering offering) {
        dao.delete(offering);
    }
    
}
