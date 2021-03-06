package StockTradingSystem.controller;

import StockTradingSystem.Main;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InterManageUIController extends AdminUIController {
    private Main application;
    private ArrayList<Index> indexArrayList;
    @FXML private JFXButton setstatebtn;
    @FXML private JFXButton setlimitbtn;
    @FXML private JFXTextField JFXlimittext;
    @FXML private TableView<Stock> stocktableview;
    @FXML private TableColumn<Stock,String> jfxstnametv;    //股票名称列
    @FXML private TableColumn<Stock,String> jfxstlimittv;    //股票涨跌幅限制列
    @FXML private TableColumn<Stock,String> jfxstcodetv;    //股票代码列
    @FXML private TableColumn<Stock,Double> jfxstceiltv;    //股票涨停价格列
    @FXML private TableColumn<Stock,Double> jfxstfloortv;    //股票跌停价格列
    @FXML private TableColumn<Stock,Double> jfxstpricetv;    //股票价格列
    @FXML private TableColumn<Stock,String> jfxststatetv;    //股票交易状态列
    @FXML private TableColumn<Stock,String> jfxstchangetv;    //股票涨跌幅（现）列
    @FXML private TableView<Indexdata> indextableview;
    @FXML private TableColumn<Indexdata,String> jfxinnametv;    //指数名称列
    @FXML private TableColumn<Indexdata,String> jfxincodetv;    //指数代码列
    @FXML private TableColumn<Indexdata,String> jfxinnumtv;    //指数数值列
    private ObservableList<Stock> stockObservableList = FXCollections.observableArrayList();
    private ObservableList<Indexdata> indexdata = FXCollections.observableArrayList();

    public void setApp(Main app) { this.application = app; }
    public Main getApp() {return this.application; }

    public void modifyPassword() throws Exception{
        // TODO 跳到修改密码界面
        application.createChangePasswordUI();
    }

    @Override
    public void logout() throws Exception {
        application.stage.close();
        application.gotoAdminLoginUI();
    }

    @Override
    public void quit() {
        // TODO 退出
        application.stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 显示股票信息、指数信息
        displaystock();
        //目前还没有指数数据
        //displayindex();

        // TODO 测试TableView能不能用
        // TODO 股票数据显示
        jfxstnametv.setCellValueFactory(new PropertyValueFactory<>("stockName"));
        jfxstlimittv.setCellValueFactory(new PropertyValueFactory<>("最大涨跌幅"));
        jfxstcodetv.setCellValueFactory(new PropertyValueFactory<>("stockCode"));
        jfxstceiltv.setCellValueFactory(new PropertyValueFactory<>("ceilingPrice"));
        jfxstfloortv.setCellValueFactory(new PropertyValueFactory<>("floorPrice"));
        jfxstpricetv.setCellValueFactory(new PropertyValueFactory<>("stockPrice"));
        jfxststatetv.setCellValueFactory(new PropertyValueFactory<>("stockState"));
        jfxstchangetv.setCellValueFactory(new PropertyValueFactory<>("涨跌幅"));

        stocktableview.setVisible(true);
        jfxstnametv.setVisible(true);
        stocktableview.setEditable(false);
        //stocktableview.setTableMenuButtonVisible(true);

        System.out.println(stockObservableList.get(0).stockNameProperty());

        // TODO 将stockArrayList里面的数据加到stockdata中
        stocktableview.setItems(stockObservableList);

        // stockdata中的数据好像是空的？？？？？？？？？？？？？？？？？？
        //System.out.println(stocktableview.getItems().get(0).stock_codeProperty());
        //System.out.println("4");

        // TODO 指数数据显示
        /*
        jfxinnametv.setCellValueFactory(new PropertyValueFactory<>("指数名称"));
        jfxincodetv.setCellValueFactory(new PropertyValueFactory<>("指数代码"));
        jfxinnumtv.setCellValueFactory(new PropertyValueFactory<>("指数数值"));

        indextableview.setVisible(true);
        indextableview.setEditable(false);

        // TODO 将indexArrayList里面的数据加到indexdata中
        indextableview.setItems(indexdata);
        for (int i = 0; i < indexArrayList.size(); i++) {
            indexdata.add(new Indexdata(indexArrayList.get(i)));
        }
         */

        super.initialize(url, rb);
    }

    public void clickintodetail() throws Exception{
        System.out.println("success");
        // TODO 将选中股票的isSelect状态设置为选中
        // 但是好像是改了之后才监听到的？？？？？？？？？
        /*
        stocktableview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Stock>() {
                    @Override
                    public void changed(ObservableValue<? extends Stock> observable, Stock oldValue, Stock newValue) {
                        newValue.setIsselect(true);
                        System.out.println(newValue.getStockCode());
                    }
                }
                );

         */
        application.stage.close();
        getApp().gotoStockDetailUI();
    }



    public void setstockstate(){
        // TODO 设置股票交易状态
        /*
        for (int i = 0; i < stockObservableList.size()&&stockObservableList.get(i).isIsselect()==true; i++){
            String oldstate=stockObservableList.get(i).getStockState();
            if(oldstate.equals("正常交易")){
                stockObservableList.get(i).setStockState("暂停交易");
                // TODO 在数据库中更改状态
                try {
                    // TODO 连接
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_trading_system", "root","12345678");
                    // TODO 到数据库中设置当前股票状态
                    Statement stmt = conn.createStatement();
                    String sql;
                    sql = "UPDATE stock_trading_system.stock SET stock_state='暂停交易' WHERE stock_code='"+stockObservableList.get(i).getStockCode()+"'";
                    stmt.executeQuery(sql);
                    conn.close();
                } catch (SQLException e){
                    e.printStackTrace();
                } catch (ClassNotFoundException e2){
                    e2.printStackTrace();
                }
            }else{
                stockObservableList.get(i).setStockState("正常交易");
                // TODO 在数据库中更改状态
                try {
                    // TODO 连接
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_trading_system", "root","12345678");
                    // TODO 到数据库中设置当前股票状态
                    Statement stmt = conn.createStatement();
                    String sql;
                    sql = "UPDATE stock_trading_system.stock SET stock_state='正常交易' WHERE stock_code='"+stockObservableList.get(i).getStockCode()+"'";
                    stmt.executeQuery(sql);
                    conn.close();
                } catch (SQLException e){
                    e.printStackTrace();
                } catch (ClassNotFoundException e2){
                    e2.printStackTrace();
                }
            }

        }

         */
        System.out.println("设置交易状态成功");
    }

    public void setstocklimit() throws Exception{
        // TODO 设置股票涨跌幅
        /*
        double risefalllimit=0;
        try{
            risefalllimit=Double.parseDouble(JFXlimittext.getText());
            // TODO 防止将23e2这样的字符转化为2300，先检查一遍是否含有字母
            String str=JFXlimittext.getText();
            for(int i=0;i<str.length();i++){
                if ((str.charAt(i)>='a' && str.charAt(i)<='z')||(str.charAt(i)>='A'&&str.charAt(i)<='Z')){
                    throw new Exception();
                }
            }
            // TODO 超出1或者小于0的设置为1和0
            if (risefalllimit>1 ||risefalllimit<0){
                application.createConfirmWarningUI();
                JFXlimittext.clear();
                if (risefalllimit>1){risefalllimit=1;}
                else{risefalllimit=0;}
            }
            for (int i = 0; i < stockObservableList.size()&&stockObservableList.get(i).isIsselect()==true; i++) {
                double highprice=(1+risefalllimit)*stockObservableList.get(i).getStockPrice();
                double lowprice=(1-risefalllimit)*stockObservableList.get(i).getStockPrice();
                stockObservableList.get(i).setCeilingPrice(highprice);
                stockObservableList.get(i).setFloorPrice(lowprice);
                // TODO 在数据库中更改最大涨跌幅，即涨停价格和跌停价格
                try {
                    // TODO 连接
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_trading_system", "root","12345678");
                    // TODO 到数据库中设置当前股票状态
                    Statement stmt = conn.createStatement();
                    String sql;
                    sql = "UPDATE stock_trading_system.stock SET ceiling_price="+highprice+", floor_price="+lowprice+" WHERE stock_code='"+stockObservableList.get(i).getStockCode()+"'";
                    stmt.executeQuery(sql);
                    conn.close();
                } catch (SQLException e){
                    e.printStackTrace();
                } catch (ClassNotFoundException e2){
                    e2.printStackTrace();
                }
            }
            System.out.println("设置涨跌幅成功");

        }catch (Exception e){
            JFXlimittext.clear();
            application.createConfirmWarningUI();
            System.out.println("设置涨跌幅失败");
        }

         */
    }

    public void displaystock(){
        // TODO 连接数据库，并将stock信息放到arraylist中

        try {
            // TODO 连接
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_trading_system", "root","12345678");
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
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e2){
            e2.printStackTrace();
        }


        // TODO 已经放到缓存StockArraylist中，然后显示到表格里
        System.out.println("已经将股票数据导入缓存");
    }

    public void displayindex(){
        // TODO 连接数据库，并将index信息放到arraylist中，与stock类似
        try {
            // TODO 连接
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_trading_system", "root","12345678");
            Statement stmt = conn.createStatement();
            String sql;
            // TODO
            //  哪一个是指数的数据库？？？？？？？？？？？？？？？？？？？
            sql = "SELECT * FROM stock_trading_system.";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Index in = new Index();
                in.setIndex_code(rs.getString("index_code"));
                in.setIndex_name(rs.getString("index_name"));
                in.setIndex_price(Double.valueOf(rs.getString("index_price")));
                indexArrayList.add(in);
            }
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e2){
            e2.printStackTrace();
        }
        // TODO 已经放到缓存IndexArraylist中，然后显示到表格里
        System.out.println("已经将指数数据导入到缓存");
    }
}