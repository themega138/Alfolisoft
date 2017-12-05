/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.controllers.editors;

import javafx.scene.image.ImageView;

import com.mq310.alfolisoft.MainApp;
import com.mq310.alfolisoft.controllers.editors.forms.GeneralFormController;
import com.mq310.ent.BaseEntity;

import javafx.event.ActionEvent;

import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.ButtonBar.ButtonType;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.DefaultDialogAction;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialog.ActionTrait;

public class GeneralEditorDialog<E extends BaseEntity> {

    protected String headTitle = "";
    protected String dialogTitle = "";
    private Dialog dialog;
    protected String imageUrl = "";
    private final Action actionSave = new DefaultDialogAction("Guardar",
            ActionTrait.CLOSING, ActionTrait.DEFAULT) {
                {
                    ButtonBar.setType(this, ButtonType.OK_DONE);
                }

                @Override
                public void handle(ActionEvent ae) {
                    Dialog dlg = (Dialog) ae.getSource();
                    if (formController.getNotValidProperty().get()) {
                        ae.consume();
                    } else {
                        dlg.setResult(this);
                    }
                }

                public String toString() {
                    return "SAVE";
                }
            ;
    };

	private GeneralFormController<E> formController;

    private void init() {
        actionSave.disabledProperty().bindBidirectional(formController.getNotValidProperty());
        dialog = new Dialog(null, dialogTitle, false);
        if (!imageUrl.equals("")) {
            dialog.setGraphic(new ImageView(MainApp.class.getResource(imageUrl)
                    .toString()));
        }
        dialog.setMasthead(headTitle);
        dialog.setContent(formController.getView());
        dialog.setResizable(false);
        dialog.setIconifiable(false);
        dialog.getActions().addAll(actionSave, Dialog.Actions.CANCEL);
    }

    public E editEntity(E entity) {
        init();
        formController.setEditableEntity(entity);
        Action action = dialog.show();
        if (action != null) {
            if (action.toString().equals("SAVE")) {
                E e = formController.getEditedEntity();
                formController.clear();
                return e;
            }
        }
        return null;
    }

    public void setFormController(GeneralFormController<E> formController) {
        this.formController = formController;
    }

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public void setHeadTitle(String headTitle) {
        this.headTitle = headTitle;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
