package pl.edu.pjwstk.lab10.domain;

public class Car {
	
	private long id;	
	private String name;
	private int year;
	
	public Car() {
	}

	public Car(int id, String name, int year) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
}
