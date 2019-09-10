package Main;
import Grafo.GraphM;
import Grafo.Nodo;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		GraphM uno = new GraphM(30);
//		uno.modeloER(18,true);
//		uno.escribirArchivo("01ER030.gv");
//		
//		GraphM dos = new GraphM(100);
//		dos.modeloER(75,true);
//		dos.escribirArchivo("01ER100.gv");
//		
//		GraphM tres = new GraphM(500);
//		tres.modeloER(400,true);
//		tres.escribirArchivo("01ER500.gv");
//		
//		GraphM cuatro = new GraphM(30);
//		cuatro.modeloGilbert(0.20,true);
//		cuatro.escribirArchivo("02GB030.gv");
//		
//		GraphM cinco = new GraphM(100);
//		cinco.modeloGilbert(0.20,true);
//		cinco.escribirArchivo("02GB100.gv");
//		
//		GraphM seis = new GraphM(500);
//		seis.modeloGilbert(0.20,true);
//		seis.escribirArchivo("02GB500.gv");
//		
		GraphM siete = new GraphM(30);
		siete.modeloGeoSimple(0.3,true);
		siete.escribirArchivo("03GS030.gv");
		
		GraphM ocho = new GraphM(100);
		ocho.modeloGeoSimple(0.3,true);
		ocho.escribirArchivo("03GS100.gv");

		GraphM nueve = new GraphM(500);
		nueve.modeloGeoSimple(0.3,true);
		nueve.escribirArchivo("03GS500.gv");
		
//		GraphM diez = new GraphM(30);
//		diez.modeloBA(7);
//		diez.escribirArchivo("04BA030.gv");
//		
//		GraphM once = new GraphM(100);
//		once.modeloBA(25);
//		once.escribirArchivo("04BA100.gv");
//		
//		GraphM doce = new GraphM(500);
//		doce.modeloBA(120);
//		doce.escribirArchivo("04BA500.gv");
		
	}

}
