package com.wipro.sales.dao;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.wipro.sales.bean.Sales;
import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.util.DBUtil;
public class SalesDao {
PreparedStatement ps;
int t=0;
public int insertSales(Sales sales) throws Exception
{
Connection con=DBUtil.getDBConnection();
ps=con.prepareStatement("insert into pranav.TBL_SALES values (?,?,?,?,?)");
ps.setString(1, sales.getSalesID());
java.sql.Date dateValue=new java.sql.Date(sales.getSalesDate().getTime());
ps.setDate(2, dateValue);
ps.setString(3, sales.getProductID());
ps.setInt(4,sales.getQuantitySold());
ps.setDouble(5, sales.getSalesPricePerUnit());
t=ps.executeUpdate();
if(t!=1)
	return -1;
return t;

}
public String generateSalesID(java.util.Date salesDate) throws Exception
{
	java.sql.Date dateValue=new java.sql.Date(salesDate.getTime());
	int nextID_from_seq=0;
	LocalDate localDate=dateValue.toLocalDate();
	String y=String.valueOf(localDate.getYear());
	String s=y.substring(2);
Connection con=DBUtil.getDBConnection();
//	Statement st=con.createStatement();
//	ResultSet rs=st.executeQuery("SELECT id FROM TBL_SALES");
	PreparedStatement ps=con.prepareStatement("select pranav.seq_sales_id.nextval from dual");
	ResultSet rs=ps.executeQuery();
	if(rs.next())
	{
		nextID_from_seq=rs.getInt(1);
	}
	String salesId=nextID_from_seq+s;
	return salesId;
}
public ArrayList<SalesReport> getSalesReport() throws Exception{
	Connection con=DBUtil.getDBConnection();
	Statement st=con.createStatement();
	//st.executeQuery("CREATE AS SEQ_SALES_ID SELECT * FROM pranav.V_SALES_REPORT");
	ResultSet rs=st.executeQuery("select * from pranav.V_SALES_REPORT");
	ArrayList<SalesReport> sales=new ArrayList<SalesReport>();
	while(rs.next())
	{
		SalesReport temp=new SalesReport();
		temp.setSalesID(rs.getString("sales_ID"));
		temp.setProductID(rs.getString("product_ID"));
		temp.setProductName(rs.getString("product_Name"));
		temp.setQuantitySold(rs.getInt("quantity_Sold"));
		temp.setProductUnitPrice(rs.getDouble("product_Unit_Price"));
		temp.setSalesPricePerUnit(rs.getDouble("sales_Price_Per_Unit"));
		temp.setProfitAmount(rs.getDouble("profit_Amount"));
		sales.add(temp);
	
	}
	
	return sales;
	
}
}
