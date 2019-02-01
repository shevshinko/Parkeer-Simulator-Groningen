package view;

import javax.swing.*;

import model.*;

public class MoneyView extends AbstractView {

    private JLabel money;
    private double moneyAdHoc = 0;
    private double moneyPass = 0;
    private double moneyReserved = 0;

    private double moneyAdHocPlus;
    private double moneyReservedPlus;

    public MoneyView(SimulatorModel model){
        super(model);
        money=new JLabel("test");
        add(money);
        updateView();
        setVisible(true);
    }

    private void setMoney(double moneyAdHoc, double moneyPass, double moneyReserved, double moneyTotal){

        money.setText("<html><b><h3>Ad-Hoc: € "+(int) moneyAdHoc+",-<br>Gereserveerd: €  "+(int) moneyReserved+",-<br>Totaal: €  "+(int)moneyTotal+",-</h3></b></html>");
    }

    @Override
    public void updateView() {
        super.updateView();

        SimulatorModel model = (SimulatorModel) super.model;

        if (model.getPay() == model.getAdHocPay()){
        moneyAdHocPlus = model.getPay();}
        if (model.getPay() == model.getReservedPay()){
        moneyReservedPlus = model.getPay();}

        double moneyAdHoc = this.moneyAdHoc + moneyAdHocPlus;
        double moneyReserved = this.moneyReserved + moneyReservedPlus;
        double moneyTotal = moneyAdHoc + moneyPass + moneyReserved;

        this.moneyAdHoc = moneyAdHoc;
        this.moneyPass = moneyPass;
        this.moneyReserved = moneyReserved;
        setMoney(moneyAdHoc, moneyPass, moneyReserved, moneyTotal);
        repaint();
    }
}