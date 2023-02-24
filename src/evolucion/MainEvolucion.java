package evolucion;

import evolucion.Cromosoma;

public class MainEvolucion {

	// Variables globales importantes
	private static Cromosoma[] cromosomas; // Array de cromosomas
	private static final float pc = 0.95f; // Probabilidad de crossover
	private static final float pm = 0.25f; // Probabilidad de mutación
	private static final int poblacion = 10; // Numero de individuos


	public static void main(String[] args) {
		
		System.out.println("----GENERACION----");
		generar(poblacion);
		evaluar();
		for (int i = 0; i < cromosomas.length; i++) {
			System.out.println(cromosomas[i].toString());
		}
		System.out.println("----SELECCION----");
		seleccionar();
		for (int i = 0; i < cromosomas.length; i++) {
			System.out.println(cromosomas[i].toString());
		}
		System.out.println("----CROSSOVER----");
		crossover(pc);
		evaluar();
		for (int i = 0; i < cromosomas.length; i++) {
			System.out.println(cromosomas[i].toString());
		}
		System.out.println("----MUTACION----");
		mutar(pm);
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
			cromosomas[i] = new Cromosoma((int) (Math.random() * 10 + 1), (int) (Math.random() * 11),
					(int) (Math.random() * 11), (int) (Math.random() * 11));
		}
	}

	private static void evaluar() {
		int aptitude = 0; // Se inicializa a cero
		for (int i = 0; i < cromosomas.length; i++) {
			aptitude = cromosomas[i].getGen1() + cromosomas[i].getGen2() + cromosomas[i].getGen3() * 2
					- cromosomas[i].getGen4();
			cromosomas[i].setAptitud(aptitude);
		}
	}

	private static void seleccionar() { // Ruleta con pesos
		Cromosoma[] copia = cromosomas.clone(); // Array copia ordenado
		sort(copia);
		
		int[] aptitudes = new int[cromosomas.length];

		// Recorremos el array de objetos Cromosoma y obtenemos sus aptitudes
		for (int i = 0; i < cromosomas.length; i++) {
			aptitudes[i] = cromosomas[i].getAptitud();
		}
		sort(aptitudes);
		
		// TODO arreglar el valor de aptitud maximo porque tendra probabilidad 1/range
		
		int min = aptitudes[0];
		int max = aptitudes[aptitudes.length - 1];
		int pos = -1; // Posicion del cromosoma elegido
		System.out.println("Numeros random: ");
		for (int i=0; i<cromosomas.length;i++) { // Para cada cromosoma
			int random = (int) (Math.random() * (max - min + 1)) + min; // Genera un número entre las aptitudes
			System.out.print(random + ",");
			for (int j = 0; j < aptitudes.length - 1; j++) {
				int inf = aptitudes[j];
				int sup = aptitudes[j+1];
				if ((random >= inf && random < sup)||(random==max)) { // [inf,sup)
					pos = j; // Copiamos la posicion donde entre
				}
			}
			cromosomas [i] = copia [pos];
		}
		System.out.println("");
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
	
	// METODOS AUXILIARES PARA ORDENAR ARRAYS PARA LA RULETA DE PESOS
	// Método de ordenación de burbuja sacado de Internet
	public static void sort(int[] array) {
		int n = array.length;

		// Recorremos el array varias veces para ordenarlo
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				// Si el elemento actual es mayor que el siguiente, los intercambiamos
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}
	
	// Método de ordenación de burbuja modificado para Cromosoma
	public static void sort(Cromosoma[] array) {
		int n = array.length;

		// Recorremos el array varias veces para ordenarlo
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				// Si el elemento actual es mayor que el siguiente, los intercambiamos
				if (array[j].getAptitud() > array[j + 1].getAptitud()) {
					Cromosoma temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}
	
	
	
	

}
