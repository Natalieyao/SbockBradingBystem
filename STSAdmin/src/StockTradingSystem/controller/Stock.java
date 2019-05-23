package StockTradingSystem.controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Stock {
    private StringProperty stockCode;
    private StringProperty stockName;
    private DoubleProperty stockPrice;
    private StringProperty ceilingPrice;
    private DoubleProperty floorPrice;
    private StringProperty stockState;
    private StringProperty stockLimit;
    private StringProperty stockChange;
    private boolean isselect;

    public Stock(String stockCode,String stockName,double stockPrice,String  ceilingPrice,double floorPrice,String stockState){
        this.stockCode=new SimpleStringProperty(stockCode);
        this.stockName=new SimpleStringProperty(stockName);
        this.stockPrice=new SimpleDoubleProperty(stockPrice);
        this.ceilingPrice=new SimpleStringProperty(ceilingPrice);
        this.floorPrice=new SimpleDoubleProperty(floorPrice);
        this.stockState=new SimpleStringProperty(stockState);
    }

    public Stock(){
        this.stockCode=new SimpleStringProperty("");
        this.stockName=new SimpleStringProperty("");
        this.stockPrice=new SimpleDoubleProperty(0);
        this.ceilingPrice=new SimpleStringProperty("");
        this.floorPrice=new SimpleDoubleProperty(0);
        this.stockState=new SimpleStringProperty("");
        this.stockLimit=new SimpleStringProperty("");
        this.stockChange=new SimpleStringProperty("");
    }

    public void setStockCode(String stockCode) { this.stockCode.set(stockCode); }
    public void setStockName(String stockName) { this.stockName.set(stockName); }
    public void setCeilingPrice(double ceilingPrice) {
        // TODO 若传入的价格为负，代表此时无限制
        if (ceilingPrice<0){
            this.ceilingPrice.set("无限制");
        }else{
            this.ceilingPrice.set(String.valueOf(ceilingPrice));
        }
    }
    public void setFloorPrice(double floorPrice) { this.floorPrice.set(floorPrice); }
    public void setStockPrice(double stockPrice) { this.stockPrice.set(stockPrice); }
    public void setStockState(String stockState) { this.stockState.set(stockState); }
    public void setStockLimit() {
        if (ceilingPrice.get().equals("无限制")){
            this.stockLimit.set("无限制");
        }else{
            double startprice=(Double.valueOf(ceilingPrice.get())+floorPrice.get())/2;
            double limit=(Double.valueOf(ceilingPrice.get())-startprice)/startprice;
            String templimit=Double.parseDouble(String.format("%.2f",limit*100))+"%";
            this.stockLimit.set(templimit);
        }
    }
    public void setStockChange() {
        double startprice=(Double.valueOf(ceilingPrice.get())+floorPrice.get())/2;
        double change=(stockPrice.get()-startprice)/startprice;
        if (change*100>-0.1 && change*100<0){
            change=0;
        }
        String tempchange=Double.parseDouble(String.format("%.2f",change*100))+"%";
        this.stockChange.set(tempchange);
    }
    public boolean isIsselect() { return isselect; }

    public double getStockPrice() { return stockPrice.get(); }
    public String getStockCode() { return stockCode.get(); }
    public String getStockName() { return stockName.get(); }
    public String getCeilingPrice() { return ceilingPrice.get(); }
    public double getFloorPrice() { return floorPrice.get(); }
    public String getStockState() { return stockState.get(); }
    public String getStockChange() { return stockChange.get(); }
    public String getStockLimit() { return stockLimit.get(); }

    public StringProperty ceilingPriceProperty() { return ceilingPrice; }
    public StringProperty stockCodeProperty() { return stockCode; }
    public StringProperty stockNameProperty() { return stockName; }
    public DoubleProperty floorPriceProperty() { return floorPrice; }
    public DoubleProperty stockPriceProperty() { return stockPrice; }
    public StringProperty stockStateProperty() { return stockState; }
    public StringProperty stockLimitProperty() { return stockLimit; }
    public StringProperty stockChangeProperty() { return stockChange; }
    public void setIsselect(boolean isselect) { this.isselect = isselect; }
}
