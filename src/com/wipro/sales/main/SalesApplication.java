package com.wipro.sales.main;
import java.util.*;
import com.wipro.sales.bean.Product;
import com.wipro.sales.bean.Sales;
import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.service.Administrator;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
public class SalesApplication {
//public String toString()
//{
//	return salesID+" "+salesDate;
//}
	public static void main(String[] args) throws Exception{
	Scanner sc=new Scanner(System.in);
	int choice=0;
	Administrator admin=new Administrator();
	System.out.println("INVENTRY AND SALES SYSTEM");
	do {
	
		System.out.println("1. Insert Stock");
		System.out.println("2. Delete Stock");
		System.out.println("3. Insert Sales");
		System.out.println("4. View Sales Report");
		System.out.print("Enter your Choice: ");
		choice = sc.nextInt();
		
		switch (choice) {
		case 1:
			Product stock = new Product();
			System.out.print("Enter product ID: ");
			stock.setProductID(sc.next());
			System.out.print("Enter product name: ");
			stock.setProductName(sc.next());
			System.out.print("Enter quantity on hand: ");
			stock.setQuantityOnHand(sc.nextInt());
			//sc.nextLine();
			System.out.print("Enter product unit price: ");
			stock.setProductUnitPrice(sc.nextDouble());
			System.out.print("Enter product reorder level: ");
			stock.setReorderLevel(sc.nextInt());
			sc.nextLine();
			admin.insertStock(stock);
			break;
		case 2:
			System.out.print("Enter product id to be deleted: ");
			String removeId = sc.next();
			removeId = admin.deleteStock(removeId);
			if (removeId != null) System.out.println(removeId + " removed successfully");
			break;
		case 3:
			Sales sales = new Sales();
			//System.out.print("Enter sales id: ");
			//sales.setSalesID(sc.next());
			System.out.print("Enter product id: ");
			sales.setProductID(sc.next());
			System.out.print("Enter date (dd-mm-yyyy): ");
			String sDate = sc.next();  
			
		    java.util.Date date =  new SimpleDateFormat("dd-mm-yyyy").parse(sDate); 
			sales.setSalesDate(date);
			
			System.out.print("Enter quantity sold: ");
			sales.setQuantitySold(sc.nextInt());
		
			System.out.print("Enter sales price per unit: ");
			sales.setSalesPricePerUnit(sc.nextDouble());
			String str=admin.insertSales(sales);
			System.out.println(str);
			break;
		case 4:
		//	ArrayList<SalesReport> arr=new ArrayList<SalesReport>;
		ArrayList<SalesReport> arr=new ArrayList<>();	
		
		arr=	admin.getSalesReport();
		System.out.println("SALES ID   SALES DATE   PRODUCT ID  PRODUCT_NAME    QUANTITY_SOLD   PRODUCT_UNIT_PRICE   SALES_PRICE_PER_UNIT   PROFIT_AMOUNT");
		for(SalesReport a:arr)
		System.out.println(a.getSalesID()+"\t\t"+a.getSalesDate()+"\t "+a.getProductID()+"\t    "+a.getProductName()+"\t\t"+a.getQuantitySold()+"\t\t"+a.getProductUnitPrice()+"\t\t"+a.getSalesPricePerUnit()+"\t\t"+a.getProfitAmount());
			break;
		default:
			System.out.println("Exiting...");
			choice = 0;
			break;
	
	
	}
	}
	while(choice>=1&&choice<=4);
	
	}
}
