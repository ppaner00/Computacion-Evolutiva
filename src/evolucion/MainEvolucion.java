package evolucion;
import evolucion.Cromosoma;

public class MainEvolucion {
	 private static Cromosoma[] cromosomas; // Array de cromosomas

	public static void main(String[] args) {
		generar(10);
		evaluar();
		
		for (int i = 0; i < cromosomas.length; i++) {
			System.out.println(cromosomas[i].toString());
		}
		
		
		
		
		
	}

	
	
	
	/**
	 * @param poblacion Número de cromosomas o individuos
	 */
	private static void generar(int poblacion) {
		// Creación de n objetos de la clase Cromosoma
		cromosomas = new Cromosoma[poblacion];
		for (int i = 0; i < poblacion; i++) {
			cromosomas[i] = new Cromosoma((int)(Math.random()*10+1),(int)(Math.random()*10+1),(int)(Math.random()*10+1),(int)(Math.random()*10+1));
			System.out.println(cromosomas[i].toString());
		}
	}
	
	private static void evaluar() {
		int aptitude = 0; // Se inicializa a cero
		for (int i = 0; i < cromosomas.length; i++) {
			aptitude = cromosomas[i].getGen1() + cromosomas[i].getGen1() + cromosomas[i].getGen1() + cromosomas[i].getGen1();
			cromosomas[i].setAptitud(aptitude);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
