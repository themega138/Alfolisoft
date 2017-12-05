/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mq310.alfolisoft.managers;

import com.mq310.alfolisoft.MainApp;
import com.mq310.alfolisoft.controllers.editors.GeneralEditorDialog;
import com.mq310.dao.services.IAccountService;
import com.mq310.dao.services.IAccountsGroupService;
import com.mq310.dao.services.IAccountsPlanService;
import com.mq310.dao.services.IBankAccountsService;
import com.mq310.dao.services.IBankService;
import com.mq310.dao.services.IInitializerService;
import com.mq310.dao.services.IMemberService;
import com.mq310.ent.EntityCreator;
import com.mq310.ent.EntityVisibility;
import com.mq310.ent.org.Bank;
import com.mq310.ent.org.BankAccount;
import com.mq310.ent.org.BankPhone;
import com.mq310.ent.org.accounts.Account;
import com.mq310.ent.org.accounts.AccountsGroup;
import com.mq310.ent.org.accounts.AccountsPlan;
import java.util.Arrays;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Moises
 */
public class InitializerManager {

    private IBankService bankService;
    private IMemberService memberService;
    private IAccountsGroupService accountsGroupService;
    private IAccountsPlanService accountsPlanService;
    private IBankAccountsService bankAccountsService;

    private GeneralEditorDialog<AccountsPlan> accountsPlanEditor;
    private GeneralEditorDialog<AccountsPlan> balancesEditor;
    private GeneralEditorDialog<BankAccount> bankAccountEditor;

    private Stage initialStage;

    private void showInitialStage() {
        initialStage = new Stage(StageStyle.TRANSPARENT);
        StackPane pane = new StackPane();
        ImageView image = new ImageView(MainApp.class.getResource("/images/Fondo_512x384.png").toString());
        pane.getChildren().add(image);
        Scene scene = new Scene(pane, 600, 400);
        initialStage.setScene(scene);
        initialStage.show();
    }

    private void hideInitialStage() {
        initialStage.hide();
    }

    public Boolean init() {
        showInitialStage();
        if (bankService.getCountsOfBanks() <= 0) {
            createBanks();
        }
        if (accountsGroupService.getAccountsCount() <= 0) {
            createAccounts();
        }
        if (accountsPlanService.getAccountsPlanCount() <= 0) {
            createAccountsPlan();
        }
//        if (bankAccountsService.getBankAccountsCount() <= 0) {
//            
//        }
        hideInitialStage();
        return true;
    }

    @Transactional
    public void createBanks() {
        Bank[] banks = new Bank[]{
            new Bank("100% Banco", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05001001001", EntityCreator.SYSTEM),
                new BankPhone("02122775444", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Bicentenario", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05019999999", EntityCreator.SYSTEM),
                new BankPhone("08002262200", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("BFC Banco Fondo Comun", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05005972222", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Activo", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05002284828", EntityCreator.SYSTEM),
                new BankPhone("02122091350", EntityCreator.SYSTEM),
                new BankPhone("02122091300", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("BanGente", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("050022643683", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Agricola de Venezuela", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05002280001", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("BanCaribe", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05002262274", EntityCreator.SYSTEM),
                new BankPhone("02129507311", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banplus", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("02129090653", EntityCreator.SYSTEM),
                new BankPhone("02129090654", EntityCreator.SYSTEM),
                new BankPhone("02129090751", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Caroní", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05001001001", EntityCreator.SYSTEM),
                new BankPhone("02122775444", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Bancoro", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05002662676", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Casa Propia", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05012272776", EntityCreator.SYSTEM),
                new BankPhone("02512305454", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Bancrecer", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05002732392", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("BanValor", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05002268256", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banesco", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05001001001", EntityCreator.SYSTEM),
                new BankPhone("02122775444", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("100% Banco", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05002262624", EntityCreator.SYSTEM),
                new BankPhone("02125011111", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Provincial", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05005087432", EntityCreator.SYSTEM),
                new BankPhone("05002663724", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Citibank", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("02127052611", EntityCreator.SYSTEM),
                new BankPhone("02127052614", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Guayana", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05002262462", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Nacional de Crédito", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("02125975000", EntityCreator.SYSTEM),
                new BankPhone("05002625000", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Nacional de Crédito", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("02125975000", EntityCreator.SYSTEM),
                new BankPhone("05002625000", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Nacional de Crédito", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("02125975000", EntityCreator.SYSTEM),
                new BankPhone("05002625000", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Nacional de Crédito", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("02125975000", EntityCreator.SYSTEM),
                new BankPhone("05002625000", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco de Venezuela", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("02124092222", EntityCreator.SYSTEM),
                new BankPhone("05002528328", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Industrial", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05002487325", EntityCreator.SYSTEM),
                new BankPhone("02125432332", EntityCreator.SYSTEM),
                new BankPhone("02125462207", EntityCreator.SYSTEM),
                new BankPhone("02125462367", EntityCreator.SYSTEM),
                new BankPhone("02125462214", EntityCreator.SYSTEM),
                new BankPhone("02125462579", EntityCreator.SYSTEM),
                new BankPhone("02125462351", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Bod Banco Occidental de Descuento", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("02125975000", EntityCreator.SYSTEM),
                new BankPhone("05002625000", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Plaza", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05017529200", EntityCreator.SYSTEM),
                new BankPhone("02122082699", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("DELSUR", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05003357870", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco del Tesoro", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05002837676", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Sofitasa", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05007634835", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Exterior", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("02125085333", EntityCreator.SYSTEM),
                new BankPhone("02512305833", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Nacional de Crédito", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("02125975000", EntityCreator.SYSTEM),
                new BankPhone("05002625000", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Mercantil", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05005032424", EntityCreator.SYSTEM),
                new BankPhone("05006002424", EntityCreator.SYSTEM),
                new BankPhone("02125032424", EntityCreator.SYSTEM),
                new BankPhone("02126002424", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("TotalBank", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05005972222", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("Banco Federal", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("053333725", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM),
            new Bank("MiBanco", "/images/bankAccounts.png", new BankPhone[]{
                new BankPhone("05006426446", EntityCreator.SYSTEM)
            }, EntityCreator.SYSTEM)
        };
        for (Bank bank : banks) {
            bankService.saveBank(bank);
        }
    }

    @Transactional
    public void createAccounts() {

        AccountsGroup groups[] = new AccountsGroup[]{
            new AccountsGroup("Generales", "Cuentas para uso generalizado.", Arrays.asList(
            new Account("Servicios publicos.", "Pago de servicios publicos (Luz, Agua, Telefono, Internet, Vigilancia...)", EntityCreator.USER),
            new Account("Fondo de Caridad", "Fondo especial para los pobres...", EntityCreator.USER),
            new Account("Ofrenda Suelta", "Toda ofrenda no contenida en un sobre...", EntityCreator.SYSTEM),
            new Account("Remesa", "Todo el dinero de la remesa...", EntityCreator.SYSTEM, EntityVisibility.HIDE),
            new Account("Ingresos", "Todo el que ingreso a la iglesia...", EntityCreator.SYSTEM, EntityVisibility.HIDE)
            ), EntityCreator.SYSTEM),
            new AccountsGroup("Departamentos", "Cuentas de los distintos departamentos.", Arrays.asList(
            new Account("Musica", "Conciertos, compra de instrumentos, alquiler de equipos, fondos para el coro...", EntityCreator.USER),
            new Account("Diaconisas", "Preparacion de la santa cena...", EntityCreator.USER),
            new Account("Dorcas", "Compra de comida, recoleccion de ropa, almuerzos para los mas necesitados...", EntityCreator.USER),
            new Account("Escuela Sabatica", "Compra de folletos...", EntityCreator.USER),
            new Account("Ministerios Personales", "Obra misionera, compra de tratados y revistas...", EntityCreator.USER)
            ), EntityCreator.USER)
        };

        for (AccountsGroup accountsGroup : groups) {
            accountsGroupService.saveAccountGroup(accountsGroup);
        }
    }

    @Transactional
    public void createAccountsPlan() {
        accountsPlanEditor.setHeadTitle("Crear nuevo plan de cuentas...");
        AccountsPlan plan = null;
        while (plan == null) {
            plan = accountsPlanEditor.editEntity(new AccountsPlan());
            if (plan != null) {
                balancesEditor.setHeadTitle("Ingrese los saldos iniciales de todas las Cuentas...");
                AccountsPlan newPlan = null;
                while (newPlan == null) {
                    newPlan = balancesEditor.editEntity(plan);
                    if (newPlan != null) {
                        accountsPlanService.createNewAccountsPlan(newPlan);
                    }
                }
            }
        }
    }

    @Transactional
    public void createMembers() {

    }

    public void createBankAccount(){
        
    }
    
    public void setAccountsGroupService(IAccountsGroupService accountsGroupService) {
        this.accountsGroupService = accountsGroupService;
    }

    public void setAccountsPlanEditor(GeneralEditorDialog<AccountsPlan> accountsPlanEditor) {
        this.accountsPlanEditor = accountsPlanEditor;
    }

    public void setAccountsPlanService(IAccountsPlanService accountsPlanService) {
        this.accountsPlanService = accountsPlanService;
    }

    public void setBalancesEditor(GeneralEditorDialog<AccountsPlan> balancesEditor) {
        this.balancesEditor = balancesEditor;
    }

    public void setBankAccountEditor(GeneralEditorDialog<BankAccount> bankAccountEditor) {
        this.bankAccountEditor = bankAccountEditor;
    }

    public void setBankService(IBankService bankService) {
        this.bankService = bankService;
    }

    public void setMemberService(IMemberService memberService) {
        this.memberService = memberService;
    }

}
