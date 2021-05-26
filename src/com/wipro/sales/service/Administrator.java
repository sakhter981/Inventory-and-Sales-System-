package com.wipro.sales.service;

import com.wipro.sales.bean.*;
import com.wipro.sales.dao.*;
import com.wipro.sales.util.DBUtil;

import java.sql.*;
import java.util.*;
//import java.sql.Date;

public class Administrator {
	Connection con;
	StockDao d1=new StockDao();
	SalesDao s1=new SalesDao();
	PreparedStatement ps;

public String insertStock(Product product) throws Exception
{
	con=DBUtil.getDBConnection();
	
	if(product!=null&&product.getProductName().length()>=2)
	{
		
		String productID =d1.generateProductID(product.getProductName());
		product.setProductID(productID);
		if(d1.insertStock(product)==1)
			System.out.println("Inserted Successfully");
		else
			System.out.println("Error in insertion");
	}
	else
	{
		return "data not valid for insertion";
	}
	return product.getProductID();
	
}
public String deleteStock(String productID) throws Exception
{
	if(d1.deleteStock(productID)==1)
		return "deleted";
	else
		return "record cannot be deleted";
}
public String insertSales(Sales salesObj) throws Exception
{java.sql.Date date,currentDate;
	if(salesObj==null)
		return "Object not valid for insertion";
	if(salesObj.getProductID()==null)
		return "Unknown product for sales";
	con=DBUtil.getDBConnection();
	ps=con.prepareStatement("select QUANTITY_ON_HAND from pranav.tbl_stock where product_id=?");
	ps.setString(1, salesObj.getProductID());
	ResultSet rs=ps.executeQuery();
	int qty=0;
	if(rs.next())
		qty=rs.getInt(1);
	if(qty<salesObj.getQuantitySold())
		return "not enough stock in hand for sale";
	 date=new java.sql.Date(salesObj.getSalesDate().getTime());
	 currentDate=new java.sql.Date(new java.util.Date().getTime());
	 if(date.compareTo(currentDate)>0)
		 return "Invalid date";
	 //salesObj.setSalesID(s1.generateSalesID(date));
	 salesObj.setSalesID(s1.generateSalesID(salesObj.getSalesDate()));
	 if(s1.insertSales(salesObj)==-1)
		 return "Error in Sales insertion";
	 if(d1.updateStock(salesObj.getProductID(), salesObj.getQuantitySold())==-1)
		 return "Error in stock updation";
	 return "Sales Completed";
		 
}
public ArrayList<SalesReport> getSalesReport() throws Exception
{
	return s1.getSalesReport();
}
}
