package StockTradingSystem.controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Stock {
    private StringProperty stockCode;
    private StringProperty stockName;
    private DoubleProperty stockPrice;
    private DoubleProperty ceilingPrice;
    private DoubleProperty floorPrice;
    private StringProperty stockState;
    private DoubleProperty stockLimit;
    private DoubleProperty stockChange;
    private boolean isselect;

    public Stock(String stockCode,String stockName,double stockPrice,double ceilingPrice,double floorPrice,String stockState){
        this.stockCode=new SimpleStringProperty(stockCode);
        this.stockName=new SimpleStringProperty(stockName);
        this.stockPrice=new SimpleDoubleProperty(stockPrice);
        this.ceilingPrice=new SimpleDoubleProperty(ceilingPrice);
        this.floorPrice=new SimpleDoubleProperty(floorPrice);
        this.stockState=new SimpleStringProperty(stockState);
    }

    public Stock(){
        this.stockCode=new SimpleStringProperty("");
        this.stockName=new SimpleStringProperty("");
        this.stockPrice=new SimpleDoubleProperty(0);
        this.ceilingPrice=new SimpleDoubleProperty(0);
        this.floorPrice=new SimpleDoubleProperty(0);
        this.stockState=new SimpleStringProperty("");
        this.stockLimit=new SimpleDoubleProperty(0);
        this.stockChange=new SimpleDoubleProperty(0);
    }

    public void setStockCode(String stockCode) { this.stockCode.set(stockCode); }
    public void setStockName(String stockName) { this.stockName.set(stockName); }
    public void setCeilingPrice(double ceilingPrice) { this.ceilingPrice.set(ceilingPrice); }
    public void setFloorPrice(double floorPrice) { this.floorPrice.set(floorPrice); }
    public void setStockPrice(double stockPrice) { this.stockPrice.set(stockPrice); }
    public void setStockState(String stockState) { this.stockState.set(stockState); }
    public void setStockLimit() {
        double startprice=(ceilingPrice.get()+floorPrice.get())/2;
        double limit=(ceilingPrice.get()-startprice)/startprice;
        this.stockLimit.set(Double.parseDouble(String.format("%.2f",limit)));
    }
    public void setStockChange() {
        double startprice=(ceilingPrice.get()+floorPrice.get())/2;
        this.stockChange.set(Math.round((stockPrice.get()-startprice)/startprice*1000)/1000);
    }
    public boolean isIsselect() { return isselect; }

    public double getStockPrice() { return stockPrice.get(); }
    public String getStockCode() { return stockCode.get(); }
    public String getStockName() { return stockName.get(); }
    public double getCeilingPrice() { return ceilingPrice.get(); }
    public double getFloorPrice() { return floorPrice.get(); }
    public String getStockState() { return stockState.get(); }
    public double getStockChange() { return stockChange.get(); }
    public double getStockLimit() { return stockLimit.get(); }

    public DoubleProperty ceilingPriceProperty() { return ceilingPrice; }
    public StringProperty stockCodeProperty() { return stockCode; }
    public StringProperty stockNameProperty() { return stockName; }
    public DoubleProperty floorPriceProperty() { return floorPrice; }
    public DoubleProperty stockPriceProperty() { return stockPrice; }
    public StringProperty stockStateProperty() { return stockState; }
    public DoubleProperty stockLimitProperty() { return stockLimit; }
    public DoubleProperty stockChangeProperty() { return stockChange; }
    public void setIsselect(boolean isselect) { this.isselect = isselect; }
}
