/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mq310.dao.services;

import com.mq310.ent.org.accounts.Account;
import javafx.scene.control.TableView;

/**
 *
 * @author Moises
 */
public interface IAccountService {
    
    public void populateTableView(TableView<Account> table);
    
}
