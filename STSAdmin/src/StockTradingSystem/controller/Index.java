package StockTradingSystem.controller;

public class Index {
    private String Index_code;
    private String Index_name;
    private double Index_price;

    public double getIndex_price() { return Index_price; }
    public String getIndex_code() { return Index_code; }
    public String getIndex_name() { return Index_name; }

    public void setIndex_code(String index_code) { this.Index_code = index_code; }
    public void setIndex_name(String index_name) { this.Index_name = index_name; }
    public void setIndex_price(double index_price) { this.Index_price = index_price; }
}
