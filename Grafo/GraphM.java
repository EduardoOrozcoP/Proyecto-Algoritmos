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
	  private HashMap<Nodo, HashSet<Arista>>incidencia;
	  private Boolean weighted;
	 


	  //Constructores//

	  public GraphM(int numNodos) {
	    this.graph = new HashMap<Nodo, HashSet<Nodo>>();
	    this.incidencia = new HashMap<Nodo, HashSet<Arista>>();
	    this.numeroNodos = numNodos;
	    this.nodes = new Nodo[numNodos];
	    for (int i = 0; i < numNodos; i++) {
	      Nodo n = new Nodo(i);
	      this.nodes[i] = n;
	      this.graph.put(n, new HashSet<Nodo>());
	      this.incidencia.put(n,  new HashSet<Arista>());
	    }
	    this.weighted = false;
	  }
	  public GraphM(int numNodos, String modelo) {
		  this.graph = new HashMap<Nodo, HashSet<Nodo>>();
		  this.incidencia = new HashMap<Nodo, HashSet<Arista>>();
		  this.numeroNodos = numNodos;
		  this.nodes = new Nodo[numNodos];
		  Random coorX = new Random();
		  Random coorY = new Random();
		  if (modelo == "geo-uniforme") {
			  for (int i = 0; i < numNodos; i++) {
			  Nodo n = new Nodo(i, coorX.nextDouble(), coorY.nextDouble());
			  this.nodes[i] = n;
			  this.graph.put(n, new HashSet<Nodo>());
			  this.incidencia.put(n, new HashSet<Arista>());
		  }
			  }
		  this.weighted = false;
	  }
	  //Métodos auxiliares//
	  
	  
	  //Regresa el grado (número de aristas) de un nodo i
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

	  //Distancia L2 entre vértices para el modelo geofráfico
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
      public Boolean getWeightedFlag() {
    	  return this.weighted; 
      }

	  public HashSet<Nodo> getEdges(int i) {
	    Nodo n = this.getNode(i);
	    return this.graph.get(n);
	  }
	  public HashSet<Arista> getWeightedEdges(int i){
		  Nodo n = this.getNode(i);
		  return this.incidencia.get(n);
	  }
	  public void setWeighted() {
		  this.weighted = true;
	  }
	  
	  public void setIncidencia(int i, HashSet<Arista> aristasPeso) {
		  this.incidencia.put(this.getNode(i), aristasPeso);
	  }
	  
	  public void setAristaPeso(int i, int j, double peso) {
		  if(!this.existeConexion(i, j))  this.conectarNodos(i,j);
		  Arista aristaNuevaij = new Arista(i, j, peso);
		  Arista aristaNuevaji = new Arista(j, i, peso);
		  HashSet<Arista> aristaNodoi = this.getWeightedEdges(i);
		  HashSet<Arista> aristaNodoj = this.getWeightedEdges(j);
		  aristaNodoi.add(aristaNuevaij);
		  aristaNodoj.add(aristaNuevaji);
		  this.setIncidencia(i,aristaNodoi);
		  this.setIncidencia(j,aristaNodoj);
		  if(!this.getWeightedFlag())  this.setWeighted();
	  }
	
	  //////////Método toString para representación en String del Grafo//////////
	  public String toString() {
		  String salida;
		    if (this.getWeightedFlag()) { 
		      salida ="graph {\n";        
		      for (int i = 0; i < this.getNumNodes(); i++) {
		        salida += this.getNode(i).getName() + " [label=\""
		        + this.getNode(i).getName() + " ("+ this.getNode(i).getDistance()
		        + ")\"];\n";
		      }
		      for (int i = 0; i < this.getNumNodes(); i++) {
		        HashSet<Arista> aristas = this.getWeightedEdges(i);
		        for (Arista e : aristas) {
		        salida += e.getN1() + " -- " + e.getN2()
		        + " [weight=" + e.getWeight()+"" + " label="+e.getWeight()+""
		        + "];\n";
		        }
		       }
		      salida += "}\n";
		    }
		    else { 
		      salida ="graph {\n";
		      for (int i = 0; i < this.getNumNodes(); i++) {
		        salida += this.getNode(i).getName() + ";\n";
		      }
		      for (int i = 0; i < this.getNumNodes(); i++) {
		        HashSet<Nodo> aristas = this.getEdges(i);
		        for (Nodo n : aristas) {
		        salida += this.getNode(i).getName() + " -- " + n.getName() + ";\n";
		        }
		       }
		      salida += "}\n";
		    }
		    return salida;
	  }

	  //////////Métodos de instancia de los modelos de generación//////////

	  /*Modelo Erdös-Rényi.
	  Crea n nodoss y elige uniformemente al azar m distintos
	   pares de distintos vértices*/
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

	  /*Modelo geográfico simple.
	Colocar n vértices en un rectángulo unitario con coordenadas uniformes
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

	  /*Modelo Barabási-Albert.
	  Colocar n vértices uno por uno, asignando a cada uno d aristas a vértices
	  distintos de tal manera que la probabilidad de que el vértice nuevo se
	  conecte a un vértice existente v es proporcional a la cantidad de aristas que
	  v tiene actualmente - los primeros d vértices se conecta todos a todos*/
	  public void modeloBA(int d) {
	    Random volado = new Random();
	  /*Para los primeros d vértices, se conecta el vértice i con el vértice j
	  con i distinto de j y recorriendo todos los vértices.*/
	    for(int i = 0; i < d; i++){
	      for(int j = 0; j < i; j++) {
	        if (!existeConexion(i, j)) {
	          conectarNodos(i, j);
	        }
	      }
	    }
	  /*Del vértice d en adelante hasta el vértice n, el vértice i de trata de
	  emparejar con cada vértice j desde el primero hasta i-1. La manera de hacer
	  el emparejamiento es con probabilidad. La probabilidad de que el vértice i
	  se conecte con j es proporcional al número de aristas del vértice j dividido
	  por el número total de aristas en el grafo hasta ese momento. Un número
	  pseudoaleatorio se compara con esa probabilidad, de ser menor, se realiza
	  la conexión. Antes de realizar la conexión se verifica que no exista ya y que
	  el vértice i no tenga ya d o más conexiones.*/
	  // i no se incrementa hasta que ese vértice tiene d conexiones
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

	  /*Método de instancia para escribir a disco en un formato GraphVis.
	  El método toma como argumento, el nombre del archivo.*/
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
	  ////Arboles
	  public GraphM BFS(int s) {
		  GraphM arbol = new GraphM(this.getNumNodes());
		  Boolean[] discovered = new Boolean[this.getNumNodes()];
		  PriorityQueue<Integer> L = new PriorityQueue<Integer>();
		  discovered[s] = true;
		  for(int i = 0; i < this.getNumNodes(); i++) {
			  if (i != s) {
				  discovered[i] = false;
				  
			  }
		  }
		  L.add(s);
		  while (L.peek() != null) {
			  int u = L.poll();
			  HashSet<Nodo>aristas = this.getEdges(u);
			  for(Nodo n : aristas) {
				  if(!discovered[n.getIndex()]){
					  arbol.conectarNodos(u, n.getIndex());
					  discovered[n.getIndex()] = true;
					  L.add(n.getIndex());
				  }
			  }
		  }
		  return arbol;
	  }
	  
	  public GraphM DFS (int s) {
		  GraphM arbol = new GraphM(this.getNumNodes());
		  Boolean[] discovered = new Boolean[this.getNumNodes()];
		  for(int i = 0; i < this.getNumNodes(); i++) {
			  discovered[i] = false;
		  }
		  recursivo(s, discovered, arbol);
		  return arbol;
	  }
	  
	  private void recursivo(int u, Boolean[] discovered ,GraphM arbol) {
		  discovered[u] = true;
		  HashSet<Nodo> aristas = this.getEdges(u);
		  for(Nodo n: aristas) {
			  if(!discovered[n.getIndex()]) {
				  arbol.conectarNodos(u, n.getIndex());
				  recursivo(n.getIndex(), discovered,arbol);
			  }
		  }
	  }
	  public GraphM DFS_I(int s) {
		  GraphM arbol = new GraphM(this.getNumNodes());
		  Boolean[] explored = new Boolean[this.getNumNodes()];
		  Stack<Integer> S = new Stack<Integer>();
		  Integer[] parent = new Integer[this.getNumNodes()];
		  for(int i = 0; i < this.getNumNodes(); i++) {
			  explored[i] = false;
		  }
	  S.push(s);
	  while(!S.isEmpty()) {
		  int u = S.pop();
		  if(!explored[u]) {
			  explored[u] = true;
			  if(u != s) {
				  arbol.conectarNodos(u, parent[u]);
			  }
			  HashSet<Nodo> aristas = this.getEdges(u);
			  for(Nodo n : aristas) {
				  S.push(n.getIndex());
				  parent [n.getIndex()] = u;
			  }
		  }
	  }
	  return arbol;
	  }
	  
	  ///Dijkstra
	  
	  ///metodo de asignacion de pesos
	  public GraphM EdgeValues(double min, double max) {
		  GraphM grafoPesado = new GraphM(this.getNumNodes());
		  Random rand = new Random();
		  double peso;
		  for(int i = 0; i< this.getNumNodes();i++) {
			  for(int j = 0; j < this.getNumNodes(); j++) {
				  if(this.existeConexion(i, j)) {
					  peso = rand.nextFloat()*(max - min)+ min;
					  grafoPesado.setAristaPeso(i, j, peso);
				  }
			  }
		  }
		  return grafoPesado;
	  }
	  
	  public GraphM Dijkstra(int s) {
		  GraphM arbol = new GraphM(this.getNumNodes());
		  double inf = Double.POSITIVE_INFINITY;
		  Integer[] padres = new Integer[arbol.getNumNodes()];
		  for(int i = 0; i < arbol.getNumNodes();i++) {
			  this.getNode(i).setDistance(inf);
			  padres[i] = null;
		  }
		  this.getNode(s).setDistance(0.0);
		  padres[s] = s;
		  PriorityQueue<Nodo> distPQ = new PriorityQueue<>(vertexDistanceComp);
		  for (int i = 0;i < this.getNumNodes(); i++) {
			  distPQ.add(this.getNode(i));
		  }
		  while (distPQ.peek() != null) {
			  Nodo u = distPQ.poll();
			  HashSet<Arista> aristas = this.getWeightedEdges(u.getIndex());
			  for(Arista e : aristas) {
				  if(this.getNode(e.getIntN2()).getDistance() > this.getNode(u.getIndex()).getDistance() + e.getWeight()) {
					  this.getNode(e.getIntN2()).setDistance(this.getNode(u.getIndex()).getDistance() + e.getWeight());
					  padres[e.getIntN2()] = u.getIndex();
				  }
			  }
		  }
		  for(int i = 0; i < arbol.getNumNodes();i++) {
			  arbol.setAristaPeso(i, padres[i], 1.0);
			  arbol.getNode(i).setDistance(this.getNode(i).getDistance());
			  
		  }
		  return arbol;
	  }
	  
	  Comparator<Nodo> vertexDistanceComp = new Comparator<Nodo>() {
		  @Override
		  public int compare(Nodo n1, Nodo n2) {
			  return Double.compare(n1.getDistance(), n2.getDistance());
		  }
	  };
}
