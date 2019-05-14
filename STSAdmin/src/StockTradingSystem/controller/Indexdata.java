package StockTradingSystem.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Indexdata extends Index{
    private final SimpleStringProperty Index_name;
    private final SimpleStringProperty Index_code;
    private final SimpleDoubleProperty Index_price;

    public Indexdata(Index i){
        this.Index_name=new SimpleStringProperty(i.getIndex_name());
        this.Index_code=new SimpleStringProperty(i.getIndex_code());
        this.Index_price=new SimpleDoubleProperty(i.getIndex_price());
    }
}
