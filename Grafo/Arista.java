package Grafo;
//clase que describe una arista
public class Arista {
	private Integer n1;
	private Integer n2;
	private double peso;
	
	public Arista(int n1, int n2, double peso) {
		this.n1 = n1;
		this.n2 = n2;
		this.peso = peso;
		
	}
	
	//getters y variables de instancia
	
	public String getN1() {
		return "n" + n1.toString();
	}
	public String getN2() {
		return "n" + n2.toString();
	}
	public int getIntN1() {
		return n1;
	}
	public int getIntN2() {
		return n2;
	}
	public double getWeight() {
		return peso;
	}

}
