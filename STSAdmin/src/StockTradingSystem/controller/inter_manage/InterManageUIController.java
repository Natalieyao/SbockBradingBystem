package StockTradingSystem.controller.inter_manage;

import StockTradingSystem.controller.utils.AdminUIController;
import StockTradingSystem.data.Stock;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class InterManageUIController extends AdminUIController {
    @FXML private JFXTextField setStateField;
    @FXML private JFXTextField setLimitField;
    @FXML private TableView<Stock> stockTableView;
    @FXML private TableColumn<Stock,String> stockNameCol;    //股票名称列
    @FXML private TableColumn<Stock,String> stockLimitCol;    //股票涨跌幅限制列
    @FXML private TableColumn<Stock,String> stockCodeCol;    //股票代码列
    @FXML private TableColumn<Stock,Double> ceilingPriceCol;    //股票涨停价格列
    @FXML private TableColumn<Stock,Double> floorPriceCol;    //股票跌停价格列
    @FXML private TableColumn<Stock,Double> stockPriceCol;    //股票价格列
    @FXML private TableColumn<Stock,String> stockStateCol;    //股票交易状态列
    @FXML private TableColumn<Stock,String> stockChangeCol;    //股票涨跌幅（现）列
    private ObservableList<Stock> stockObservableList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        stockTableInit();
        // TODO indexTableInit();
    }

    private void stockTableInit() {
        // TODO 显示股票信息
        fetchStockData();

        // TODO 测试TableView能不能用
        // TODO 股票数据显示
        stockNameCol.setCellValueFactory(new PropertyValueFactory<>("stockName"));
        stockCodeCol.setCellValueFactory(new PropertyValueFactory<>("stockCode"));
        ceilingPriceCol.setCellValueFactory(new PropertyValueFactory<>("ceilingPrice"));
        floorPriceCol.setCellValueFactory(new PropertyValueFactory<>("floorPrice"));
        stockPriceCol.setCellValueFactory(new PropertyValueFactory<>("stockPrice"));
        stockStateCol.setCellValueFactory(new PropertyValueFactory<>("stockState"));

        stockTableView.setVisible(true);
        stockNameCol.setVisible(true);
        stockTableView.setEditable(false);

        System.out.println(stockObservableList.get(0).stockNameProperty());

        // TODO 将stockArrayList里面的数据加到stockdata中
        stockTableView.setItems(stockObservableList);
    }

    public void gotoStockDetail() throws Exception{

        // TODO 将选中股票的isSelect状态设置为选中
        //  单选、多选时先清空，再把选中的设置
        stockTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (int i = 0; i < stockObservableList.size(); i++) {
            stockObservableList.get(i).setIsselect(false);
        }
        List<Stock> stockSelected=stockTableView.getSelectionModel().getSelectedItems();
        for (int i = 0; i < stockSelected.size(); i++) {
            for (int j = 0; j < stockObservableList.size(); j++) {
                if (stockSelected.get(i).equals(stockObservableList.get(j))){
                    stockObservableList.get(j).setIsselect(true);
                }
            }
        }
        stockTableView.setOnMouseClicked(event -> {
            if (event.getClickCount()==2&&stockSelected.size()==1){
                try {
                    //application.stage.close();
                    getApp().gotoStockDetailUI();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public void setStockState(){
        // TODO 设置股票交易状态
    }

    public void setStockLimit() {
        // TODO 设置股票涨跌幅
    }

    private void fetchStockData(){
        // TODO 连接数据库，并将stock信息放到arraylist中

        try {
            // TODO 加密JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://139.155.104.140:3306/stock_trading_system" +
                    "?useSSL=false" +
                    "&serverTimezone=GMT" +
                    "&allowPublicKeyRetrieval=true", "root","0000");
            // TODO 到数据库中查询当前股票名称
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM stock_trading_system.stock";
            ResultSet rs = stmt.executeQuery(sql);
            // TODO 放到st中
            while(rs.next()){
                Stock st=new Stock();
                st.setStockCode(rs.getString("stock_code"));
                st.setStockName(rs.getString("stock_name"));
                st.setStockPrice(Double.valueOf(rs.getString("stock_price")));
                st.setStockState(rs.getString("stock_state"));
                st.setCeilingPrice(Double.valueOf(rs.getString("ceiling_price")));
                st.setFloorPrice(Double.valueOf(rs.getString("floor_price")));
                stockObservableList.add(st);
            }
            conn.close();
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }


        // TODO 已经放到缓存StockArraylist中，然后显示到表格里
        System.out.println("已经将股票数据导入缓存");
    }

    public void gotoMainUI() throws Exception {
        super.getApp().gotoAdminMainUI();
    }

}
