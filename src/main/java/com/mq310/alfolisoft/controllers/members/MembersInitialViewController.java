/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.members;

import com.mq310.alfolisoft.controllers.GeneralInitViewController;
import java.io.IOException;

import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Moises
 */
public class MembersInitialViewController extends GeneralInitViewController {

    public static final String VIEW_NAME = "membersInitialView";
    public static final String VIEW_PATH = MembersMainViewController.MEMBERSMAINVIEWNAVIGATOR_PATH + DP + VIEW_NAME;

    public static MembersInitialViewController create() throws IOException {
        return (MembersInitialViewController) loadInitController(new MembersInitialViewController());
    }

    @Override
    public void init() {
        setTitle("AlfoliSoft - Miembros");
    }

    public void handle(ActionEvent event) {
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
