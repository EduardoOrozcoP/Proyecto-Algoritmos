package Grafo;

import Grafo.Nodo;
import Grafo.Arista;
import java.util.*;
import java.io.FileNotFoundException;

public class GraphM {
	
	 //Variables de instancia//
	  private Nodo[] nodes;
	  private HashMap<Nodo, HashSet<Nodo>> graph;
	  private final int numeroNodos; 
	  private int numeroAristas;  
	  private static Formatter output; 
	 


	  //Constructores//

	  public GraphM(int numNodos) {
	    this.graph = new HashMap<Nodo, HashSet<Nodo>>();
	    this.numeroNodos = numNodos;
	    this.nodes = new Nodo[numNodos];
	    for (int i = 0; i < numNodos; i++) {
	      Nodo n = new Nodo(i);
	      this.nodes[i] = n;
	      this.graph.put(n, new HashSet<Nodo>());
	    }
	  }
	  //M�todos auxiliares//
	  
	  
	  //Regresa el grado (n�mero de aristas) de un nodo i
	  public int gradoNodo(int i) {
	    Nodo n1 = this.getNode(i);
	    return this.graph.get(n1).size();
	  }

	  //Conecta dos nodos
	  public void conectarNodos(int i, int j) {
	     Nodo n1 = this.getNode(i);
	     Nodo n2 = this.getNode(j);
	     HashSet<Nodo> aristas1 = this.getEdges(i);
	     HashSet<Nodo> aristas2 = this.getEdges(j);

	     /*Se agregan los nodos al conjunto del otro*/
	     aristas1.add(n2);
	     aristas2.add(n1);  
	     this.numeroAristas +=1; 
	  }

	  //Regresa 'true' si ya existe la arista
	  private Boolean existeConexion(int i, int j) {
	     Nodo n1 = this.getNode(i);
	     Nodo n2 = this.getNode(j);
	    HashSet<Nodo> aristas1 = this.getEdges(i);
	    HashSet<Nodo> aristas2 = this.getEdges(j);
	     if (aristas1.contains(n2) || aristas2.contains(n1)) {
	       return true;
	     }
	     else{
	       return false;
	     }
	  }

	  //Distancia L2 entre v�rtices para el modelo geofr�fico
	  private double distanciaNodos(Nodo n1, Nodo n2) {
	    return Math.sqrt(Math.pow((n1.getX() - n2.getX()), 2)+ Math.pow((n1.getY() - n2.getY()), 2));
	  }

	  //////////getters/setters de las variables de instancia//////////
	  public int getNumNodes() {
		  return numeroNodos;
		  }

	  public int getNumEdges() {
		  return numeroAristas;
		  }

	  public Nodo getNode(int i) {
		  return this.nodes[i];
		  }


	  public HashSet<Nodo> getEdges(int i) {
	    Nodo n = this.getNode(i);
	    return this.graph.get(n);
	  }

	
	  //////////M�todo toString para representaci�n en String del Grafo//////////
	  public String toString() {
	    String salida;
	      salida ="graph {\n";
	      for (int i = 0; i < this.getNumNodes(); i++) {
	        salida += this.getNode(i).getName() + ";\n";
	      }
	      for (int i = 0; i < this.getNumNodes(); i++) {
	        HashSet<Nodo> aristas = this.getEdges(i);
	        for (Nodo n : aristas) {
	        salida += this.getNode(i).getName() + " -> " + n.getName() + ";\n";
	        }
	       }
	      salida += "}\n";
	    return salida;
	  }

	  //////////M�todos de instancia de los modelos de generaci�n//////////

	  /*Modelo Erd�s-R�nyi.
	  Crea n nodoss y elige uniformemente al azar m distintos
	   pares de distintos v�rtices*/
	  public void modeloER(int numAristas, boolean self) {
	    Random randomNum1 = new Random();
	    Random randomNum2 = new Random();
	    while (this.getNumEdges() < numAristas) {
	      int num1 = randomNum1.nextInt(this.getNumNodes());
	      int num2 = randomNum2.nextInt(this.getNumNodes());
	      if (!existeConexion(num1, num2)) {
	    	  conectarNodos(num1, num2);
	    	if(num1 == num2) {
	    	continue;	
	    	}
	    	else if (self == true && num1 == num2) {
	          conectarNodos(num1, num2);
	        }
	      }
	    }
	  }

	  /*Modelo de Gilbert.
	  Crear m aristas crear n nodos y poner una arista entre cada par
	  independiente y uniformemente con probabilidad p*/
	  public void modeloGilbert(double probabilidad, boolean self) {
	    Random randomNum = new Random();
	    for(int i = 0; i < this.getNumNodes(); i++) {
	      for(int j = 0; j <this.getNumNodes(); j++) {
	        if ((i != j) && (randomNum.nextDouble() <= probabilidad)) {
	          if (!existeConexion(i, j)) {
	            conectarNodos(i, j);
	          }
	        }
	        else if (self == true && (i == j)&& (randomNum.nextDouble() <= probabilidad)) {
	        	  conectarNodos(i, j);
	          }
	      }
	    }
	  }

	  /*Modelo geogr�fico simple.
	Colocar n v�rtices en un rect�ngulo unitario con coordenadas uniformes
	(o normales) y colocar una arista entre cada par que queda a una
	distancia r o menor*/
	  public void modeloGeoSimple(double r, boolean self) {
	    for(int i = 0; i < this.getNumNodes(); i++) {
	      for(int j = i + 1; j < this.getNumNodes(); j++) {
	        double distancia = distanciaNodos(this.getNode(i), this.getNode(j));
	        if (distancia <= r && (i!=j)) {
	            conectarNodos(i, j);
	        }
	        else if(self == true && distancia <= r && (i == j)) {
	        	conectarNodos(i, j);
	        }
	      }
	    }
	  }

	  /*Modelo Barab�si-Albert.
	  Colocar n v�rtices uno por uno, asignando a cada uno d aristas a v�rtices
	  distintos de tal manera que la probabilidad de que el v�rtice nuevo se
	  conecte a un v�rtice existente v es proporcional a la cantidad de aristas que
	  v tiene actualmente - los primeros d v�rtices se conecta todos a todos*/
	  public void modeloBA(int d) {
	    Random volado = new Random();
	  /*Para los primeros d v�rtices, se conecta el v�rtice i con el v�rtice j
	  con i distinto de j y recorriendo todos los v�rtices.*/
	    for(int i = 0; i < d; i++){
	      for(int j = 0; j < i; j++) {
	        if (!existeConexion(i, j)) {
	          conectarNodos(i, j);
	        }
	      }
	    }
	  /*Del v�rtice d en adelante hasta el v�rtice n, el v�rtice i de trata de
	  emparejar con cada v�rtice j desde el primero hasta i-1. La manera de hacer
	  el emparejamiento es con probabilidad. La probabilidad de que el v�rtice i
	  se conecte con j es proporcional al n�mero de aristas del v�rtice j dividido
	  por el n�mero total de aristas en el grafo hasta ese momento. Un n�mero
	  pseudoaleatorio se compara con esa probabilidad, de ser menor, se realiza
	  la conexi�n. Antes de realizar la conexi�n se verifica que no exista ya y que
	  el v�rtice i no tenga ya d o m�s conexiones.*/
	  // i no se incrementa hasta que ese v�rtice tiene d conexiones
	    for(int i = d; i < this.getNumNodes();) {
	      for(int j = 0; j < i; j++) {
	        double probabilidad =
	        (double)gradoNodo(j)/(double)this.getNumEdges();
	        if (volado.nextDouble() <= probabilidad) {
	          if (!existeConexion(i, j) && (gradoNodo(i) < d)) {
	            conectarNodos(i, j);
	          }
	        }
	      }
	      if (gradoNodo(i) >= d) i++;
	    }
	  }

	  /*M�todo de instancia para escribir a disco en un formato GraphVis.
	  El m�todo toma como argumento, el nombre del archivo.*/
	  public void escribirArchivo(String nombre) {
	    try{
	      output = new Formatter(nombre);
	    }
	    catch (SecurityException securityException) {
	      System.err.println("No hay permiso de escritura.");
	      System.exit(1);
	    }
	    catch (FileNotFoundException fileNotFoundException) {
	      System.err.println("Error al abrir el archivo.");
	      System.exit(1);
	    }
	    try{
	      output.format("%s",this);
	    }
	    catch (FormatterClosedException formatterClosedException) {
	      System.err.println("Error al escribir al archivo");
	    }
	    if (output != null) {
	    output.close();
	  }
	  }
}
