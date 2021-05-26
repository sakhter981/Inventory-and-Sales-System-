package com.wipro.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wipro.sales.bean.Product;

import com.wipro.sales.util.DBUtil;

public class StockDao {
	 Connection con=DBUtil.getDBConnection();
	public int insertStock(Product prod) throws Exception
	{int t=0;
		PreparedStatement ps;
	
	ps=con.prepareStatement("insert into pranav.TBL_STOCK values (?,?,?,?,?)");
	ps.setString(1, prod.getProductID());

	
	ps.setString(2, prod.getProductName());
	
	ps.setInt(3,prod.getQuantityOnHand());
	ps.setDouble(4,prod.getProductUnitPrice());
	ps.setInt(5,prod.getReorderLevel());
	t=ps.executeUpdate();
	if(t!=1)
		return -1;

return t;
	}
	public  String generateProductID(String productName) throws Exception
	{
		
		PreparedStatement ps=con.prepareStatement("select pranav.SEQ_PRODUCT_ID.nextval from dual");
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			return productName.substring(0,2)+rs.getInt(1);
			return "";
	}
	public int updateStock(String productID,int soldQty) throws Exception
	{
		int t=0,curr_qty=0;
		PreparedStatement ps=con.prepareStatement("select QUANTITY_ON_HAND from pranav.tbl_stock where product_id=?");
		ps.setString(1,productID);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			curr_qty=rs.getInt(1);
		ps=con.prepareStatement("update pranav.tbl_stock set QUANTITY_ON_HAND = ? where product_id=?");
		ps.setInt(1, curr_qty-soldQty);
		ps.setString(2, productID);
		 t=ps.executeUpdate();
		 if(t!=1)
			 return -1;
		return t;
	}
	public Product getStock(String productID) throws Exception
	{
		Product prod=new Product();
	    PreparedStatement ps=con.prepareStatement("select * from pranav.tbl_stock where product_id=?");
	    ps.setString(1,productID);
	    ResultSet rs=ps.executeQuery();
	    if(rs.next())
	    {
	    	prod.setProductID(rs.getString(1));
	    	prod.setProductName(rs.getString(2));
	    	prod.setQuantityOnHand(rs.getInt(3));
	    	prod.setProductUnitPrice(rs.getDouble(4));
	    	prod.setReorderLevel(rs.getInt(5));
	    		
	    }
	    return prod;
	}
	public int deleteStock(String productID) throws Exception
	{
		PreparedStatement ps=con.prepareStatement("delete from pranav.tbl_stock where product_id=?");
		ps.setString(1, productID);
		if(ps.executeUpdate()!=1)
			return -1;
		return 1;
	}
	
}
