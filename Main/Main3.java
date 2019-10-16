package Main;

import Grafo.GraphM;

public class Main3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		GraphM uno = new GraphM(30);
	    uno.modeloER(90,false); 
	    GraphM unoPesado = uno.EdgeValues(1.0, 15.0);
	    unoPesado.escribirArchivo("01ER030_3.gv");
	    GraphM unoDijkstra = unoPesado.Dijkstra(0);
	    unoDijkstra.escribirArchivo("01ER030_3Dijkstra.gv");
	    
	    GraphM dos = new GraphM(100);
	    dos.modeloER(500,false); 
	    GraphM dosPesado = dos.EdgeValues(1.0, 15.0);
	    dosPesado.escribirArchivo("02ER100_3.gv");
	    GraphM dosDijkstra = dosPesado.Dijkstra(0);
	    dosDijkstra.escribirArchivo("02ER100_3Dijkstra.gv");
	    
	    GraphM tres = new GraphM(500);
	    tres.modeloER(5000,false); 
	    GraphM tresPesado = tres.EdgeValues(1.0, 15.0);
	    tresPesado.escribirArchivo("03ER500_3.gv");
	    GraphM tresDijkstra = tresPesado.Dijkstra(0);
	    tresDijkstra.escribirArchivo("03ER500_3Dijkstra.gv");
	    
	    GraphM cuatro = new GraphM(30);
	    cuatro.modeloGilbert(0.25,false); 
	    GraphM cuatroPesado = cuatro.EdgeValues(1.0, 15.0);
	    cuatroPesado.escribirArchivo("04GB030_3.gv");
	    GraphM cuatroDijkstra = cuatroPesado.Dijkstra(0);
	    cuatroDijkstra.escribirArchivo("04GB030_3Dijkstra.gv");
	    
	    GraphM cinco = new GraphM(100);
	    cinco.modeloGilbert(0.25,false); 
	    GraphM cincoPesado = cinco.EdgeValues(1.0, 15.0);
	    cincoPesado.escribirArchivo("05GB100_3.gv");
	    GraphM cincoDijkstra = cincoPesado.Dijkstra(0);
	    cincoDijkstra.escribirArchivo("05GB100_3Dijkstra.gv");
	    
	    GraphM seis = new GraphM(500);
	    seis.modeloGilbert(0.25,false); 
	    GraphM seisPesado = seis.EdgeValues(1.0, 15.0);
	    seisPesado.escribirArchivo("06GB500_3.gv");
	    GraphM seisDijkstra = seisPesado.Dijkstra(0);
	    seisDijkstra.escribirArchivo("06GB500_3Dijkstra.gv");
	    
	    GraphM siete = new GraphM(30);
	    siete.modeloGeoSimple(0.25,false); 
	    GraphM sietePesado = siete.EdgeValues(1.0, 15.0);
	    sietePesado.escribirArchivo("07GS030_3.gv");
	    GraphM sieteDijkstra = sietePesado.Dijkstra(0);
	    sieteDijkstra.escribirArchivo("07GS030_3Dijkstra.gv");
	    
	    GraphM ocho = new GraphM(100);
	    ocho.modeloGeoSimple(0.25,false); 
	    GraphM ochoPesado = ocho.EdgeValues(1.0, 15.0);
	    ochoPesado.escribirArchivo("08GS100_3.gv");
	    GraphM ochoDijkstra = ochoPesado.Dijkstra(0);
	    ochoDijkstra.escribirArchivo("08GS100_3Dijkstra.gv");
	    
	    GraphM nueve = new GraphM(500);
	    nueve.modeloGeoSimple(0.3,false); 
	    GraphM nuevePesado = nueve.EdgeValues(1.0, 15.0);
	    nuevePesado.escribirArchivo("09GS500_3.gv");
	    GraphM nueveDijkstra = nuevePesado.Dijkstra(0);
	    nueveDijkstra.escribirArchivo("09GS500_3Dijkstra.gv");
	    
	    GraphM diez = new GraphM(30);
	    diez.modeloBA(15); 
	    GraphM diezPesado = diez.EdgeValues(1.0, 15.0);
	    diezPesado.escribirArchivo("10BA030_3.gv");
	    GraphM diezDijkstra = diezPesado.Dijkstra(0);
	    diezDijkstra.escribirArchivo("10BA030_3Dijkstra.gv");
	    */
	    GraphM once = new GraphM(100);
	    once.modeloBA(80); 
	    GraphM oncePesado = once.EdgeValues(1.0, 15.0);
	    oncePesado.escribirArchivo("11BA100_3.gv");
	    GraphM onceDijkstra = oncePesado.Dijkstra(0);
	    onceDijkstra.escribirArchivo("11BA100_3Dijkstra.gv");
	    
	    GraphM doce = new GraphM(500);
	    doce.modeloBA(250); 
	    GraphM docePesado = doce.EdgeValues(1.0, 15.0);
	    docePesado.escribirArchivo("12BA500_3.gv");
	    GraphM doceDijkstra = docePesado.Dijkstra(0);
	    doceDijkstra.escribirArchivo("12BA500_3Dijkstra.gv");
	    
	}

}
