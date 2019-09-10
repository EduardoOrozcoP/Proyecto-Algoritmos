package Grafo;
//la clase Nodo que exprersa un nodo
public class Nodo {
	private String name;
	private Integer numAristas;
	private Integer index;
	
	//variables para el modelo geografico simple
	private double x;
	private double y;
	
	  private double distance;
	//constructor que 
	public Nodo(String name) {
		this.name = name;
		this.numAristas = 0;
	}

	public Nodo(int name) {
		this.index = name;
		this.name = "n" + String.valueOf(name);
		this.numAristas = 0;
	}
	
	public Nodo(int name, double x, double y) {
		this.index = name;
		this.name = "n" + String.valueOf(name);
		this.x = x;
		this.y = y;
	}
	
	//getters 
	
	public String getName() {
		return name;
	}
	public Integer getNumEdges() {
		return numAristas;
	}
	public Integer getIndex() {
		return index;
	}
	public double getDistance() {
		return distance;
	}
	  public void setDistance(double d) {
		  this.distance = d;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
}
