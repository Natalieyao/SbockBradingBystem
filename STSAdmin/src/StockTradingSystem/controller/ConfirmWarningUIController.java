package StockTradingSystem.controller;

import javafx.event.ActionEvent;


public class ConfirmWarningUIController extends AdminUIController{
    public void confirm(ActionEvent actionEvent) {
        // TODO 确认警告，返回设定涨跌幅限制的界面
        getApp().floatStage.close();
    }
}

