package com.wipro.sales.bean;
import java.util.*;
public class Sales {
public String salesID;
public Date salesDate;
public String productID;
public int quantitySold;
public double salesPricePerUnit;
public  String getSalesID() {
	return salesID;
}
public void setSalesID(String salesID) {
	this.salesID = salesID;
}
public Date getSalesDate() {
	return salesDate;
}
public void setSalesDate(Date salesDate) {
	this.salesDate = salesDate;
}
public String getProductID() {
	return productID;
}
public void setProductID(String productID) {
	this.productID = productID;
}
public int getQuantitySold() {
	return quantitySold;
}
public void setQuantitySold(int quantitySold) {
	this.quantitySold = quantitySold;
}
public double getSalesPricePerUnit() {
	return salesPricePerUnit;
}
public void setSalesPricePerUnit(double salesPricePerUnit) {
	this.salesPricePerUnit = salesPricePerUnit;
}
}
