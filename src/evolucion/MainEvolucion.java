package evolucion;
import evolucion.Cromosoma;

public class MainEvolucion {
	
	// Variables globales importantes
	private static Cromosoma[] cromosomas; // Array de cromosomas
	private static final float pc = 0.95f; // Probabilidad de crossover
	private static final float pm = 0.25f; // Probabilidad de mutación
	
	public static void main(String[] args) {
		generar(5);
		evaluar();
		for (int i = 0; i < cromosomas.length; i++) {
			System.out.println(cromosomas[i].toString());
		}
		System.out.println("-------------------------------");
		seleccionar();
		for (int i = 0; i < cromosomas.length; i++) {
			System.out.println(cromosomas[i].toString());
		}
		System.out.println("-------------------------------");

		crossover(pc);
		evaluar();
		for (int i = 0; i < cromosomas.length; i++) {
			System.out.println(cromosomas[i].toString());
		}
		mutar(pm);
		evaluar();
		System.out.println("-------------------------------");
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
			cromosomas[i] = new Cromosoma((int)(Math.random()*10+1),(int)(Math.random()*11),(int)(Math.random()*11),(int)(Math.random()*11));
		}
	}
	
	private static void evaluar() {
		int aptitude = 0; // Se inicializa a cero
		for (int i = 0; i < cromosomas.length; i++) {
			aptitude = cromosomas[i].getGen1() + cromosomas[i].getGen2() + cromosomas[i].getGen3()*2 - cromosomas[i].getGen4();
			cromosomas[i].setAptitud(aptitude);
		}
	}
	
	private static void seleccionar() { // Ruleta con pesos
		Cromosoma[] copia = cromosomas.clone();
		float[] probabilidades = new float[cromosomas.length]; // Array con las frecuencias relativas
		int sumaAptitudes = 0;
		
		for (int i = 0; i < cromosomas.length; i++) {
			sumaAptitudes += cromosomas[i].getAptitud();
		}
		
		for (int i = 0; i < cromosomas.length; i++) {
			probabilidades[i] = cromosomas[i].getAptitud()/sumaAptitudes;
		}
		
		
	}
	
	private static void crossover(float probabilidad) {
	    for (int i = 0; i < cromosomas.length - 1; i += 2) {
	        // Obtener los cromosomas en posiciones pares e impares
	        Cromosoma cromosoma1 = cromosomas[i];
	        Cromosoma cromosoma2 = cromosomas[i + 1];

	        // Verificar que estamos trabajando con un par de cromosomas válido
	        if (cromosoma2 != null) { // Si hay un cromosoma al final simplemente no se cambia y ya
	            // Realizar el intercambio solo si se cumple la probabilidad pc
	            if (Math.random() <= probabilidad) {
	                // Intercambiar los valores del gen1 y el gen2
	                int gen1Aux = cromosoma1.getGen1();
	                cromosoma1.setGen1(cromosoma2.getGen1());
	                cromosoma2.setGen1(gen1Aux);

	                int gen2Aux = cromosoma1.getGen2();
	                cromosoma1.setGen2(cromosoma2.getGen2());
	                cromosoma2.setGen2(gen2Aux);
	            }
	        }
	    }
	}

	private static void mutar(float probabilidad) {
	    for (int i = 0; i < cromosomas.length; i++) {
	        Cromosoma cromosoma = cromosomas[i];
	        // Realizar la mutación solo si se cumple la probabilidad pm
	        if (Math.random() <= probabilidad) {
	            int genRandom = (int) (Math.random() * 4) + 1; // Elegir un gen aleatorio
	            int valorRandom = (int) (Math.random() * 11); // Asignarle un valor aleatorio entre 0 y 10
	            // Asignar el valor aleatorio al gen elegido
	            switch (genRandom) {
	                case 1:
	                    cromosoma.setGen1(valorRandom);
	                    break;
	                case 2:
	                    cromosoma.setGen2(valorRandom);
	                    break;
	                case 3:
	                    cromosoma.setGen3(valorRandom);
	                    break;
	                case 4:
	                    cromosoma.setGen4(valorRandom);
	                    break;
	            }
	        }
	    }
	}



	
	
	
	
	
	
	
	
	
	
}
