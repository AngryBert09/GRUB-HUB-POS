package FoodPOS;

public class OrderData {


private int ProductID;
private String ProductName;
private Double Price;
private int Stock;

public void setData(int ProductID, String ProductName, Double price, int Stock) {
	
	this.ProductID = ProductID;
	this.ProductName = ProductName;
	this.Price = price;
	this.Stock = Stock;
	
}

public int getProductID() {
	
	return ProductID;

}
public String getProductName() {
	
	return ProductName;

}
public double getPrice() {
	
	return Price;

}
public int getStock() {
	
	return Stock;

}


}