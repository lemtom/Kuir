package kuir.recipe;

public class Ingredient {
	public Ingredient(String name) {
		this.name = name;
	}
	Double amount;
	Unit unit;
	String name;
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
