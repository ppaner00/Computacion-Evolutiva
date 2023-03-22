package evolucion;

import evolucion.Cromosoma;

public class MainEvolucion {

	// Variables globales importantes
	private static Cromosoma[] cromosomas; // Array de cromosomas
	private static final float pc = 0.95f; // Probabilidad de crossover
	private static final float pm = 0.25f; // Probabilidad de mutación
	private static final int poblacion = 20; // Numero de individuos


	public static void main(String[] args) {
		generar(poblacion);
		
		for(int i = 0;i<100;i++) {
//			System.out.println("Generación "+i);
//			System.out.println("----GENERACION----");
			//generar(poblacion);
			evaluar();
			if(i==0 || i==99) {
				System.out.println("==============================");
				mostrar();
			}
			
			seleccionar();
			evaluar();
			
			crossover(pc);
			evaluar();

			mutar(pm);
			evaluar();
			
		}

		
		
	}

	/**
	 * @param poblacion Número de cromosomas o individuos
	 */
	private static void generar(int poblacion) {
		// Creación de n objetos de la clase Cromosoma
		cromosomas = new Cromosoma[poblacion];
		for (int i = 0; i < poblacion; i++) {
			cromosomas[i] = new Cromosoma((int) (Math.random() * 11), (int) (Math.random() * 11),(int) (Math.random() * 11), (int) (Math.random() * 11));
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

	private static void seleccionar(){
	    int[] sumaAptitudes = new int[cromosomas.length+1];
	    Cromosoma auxiliar[] = cromosomas.clone(); 
	    int random = -1;
	    
	    sumaAptitudes[0] = 0;
	    //System.out.println("SumaAptitudes[0] = "+sumaAptitudes[0]);
		for (int i=1;i<sumaAptitudes.length;i++){
			sumaAptitudes[i] = cromosomas[i-1].getAptitud() + sumaAptitudes[i-1];
			//System.out.println("SumaAptitudes["+i+"] = "+sumaAptitudes[i]);
		}
		
		
		for (int i=0;i<cromosomas.length;i++){
			random = (int) (Math.random()*(sumaAptitudes[sumaAptitudes.length-1]+1));
			
			for(int j=0;j<cromosomas.length-1;j++) {
				if(random>=sumaAptitudes[j] && random<sumaAptitudes[j+1]) {
					cromosomas[i].duplicate(auxiliar[j]);
					//System.out.println("He seleccionado en la iteración " + i + " el cromosoma " + cromosomas[i].toString()+" con el random "+ random);
				}
				//System.out.println("["+sumaAptitudes[j]+","+sumaAptitudes[j+1]+")");
			}
			//System.out.println("El minimo es:"+sumaAptitudes[0]+" el máximo es:"+sumaAptitudes[sumaAptitudes.length-2]);
			if(random>=sumaAptitudes[sumaAptitudes.length-2]){
				cromosomas[i].duplicate(auxiliar[auxiliar.length-1]);
				//System.out.println("[ESP]He seleccionado en la iteración " + i + " el cromosoma " + cromosomas[i].toString()+"con el random "+ random);
				//System.out.println("["+sumaAptitudes[sumaAptitudes.length-2]+"," + sumaAptitudes[sumaAptitudes.length-1]+")");
			}
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
					int corte = (int)(Math.random()*3+1);
					int auxiliar = -1;
					switch (corte) {
					case 1:
						//Intercambiar g2,g3,g4
						//System.out.println("Caso 1 mi rey");
						auxiliar = cromosoma1.getGen2();
						cromosoma1.setGen2(cromosoma2.getGen2());
						cromosoma2.setGen2(auxiliar);
						
						auxiliar = cromosoma1.getGen3();
						cromosoma1.setGen3(cromosoma2.getGen3());
						cromosoma2.setGen3(auxiliar);
						
						auxiliar = cromosoma1.getGen4();
						cromosoma1.setGen4(cromosoma2.getGen4());
						cromosoma2.setGen4(auxiliar);
						break;
						
					case 2:
						//Intercambiar g3,g4
						//System.out.println("Caso 2 mi rey");
						auxiliar = cromosoma1.getGen3();
						cromosoma1.setGen3(cromosoma2.getGen3());
						cromosoma2.setGen3(auxiliar);
						
						auxiliar = cromosoma1.getGen4();
						cromosoma1.setGen4(cromosoma2.getGen4());
						cromosoma2.setGen4(auxiliar);
						break;
						
					case 3:
						//Intercambiar g4
						//System.out.println("Caso 3 mi rey");
						auxiliar = cromosoma1.getGen4();
						cromosoma1.setGen4(cromosoma2.getGen4());
						cromosoma2.setGen4(auxiliar);
						break;
					}
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
	// Método de ordenación de burbuja
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
	
	
	public static void mostrar() {
		for (int i = 0; i < cromosomas.length; i++) {
		System.out.println(cromosomas[i].toString());
	}
	}
	
	public static float media() {
		float media = 0.0f;
		int total = 0;
		for (int i = 0; i < cromosomas.length; i++) {
			total += cromosomas[i].getAptitud();
		}
		return total/cromosomas.length;
	}
	
	public static int mayor() {
		int mayor = cromosomas[0].getAptitud();
		for (int i = 1; i < cromosomas.length; i++) {
		 if(cromosomas[i].getAptitud()>mayor) {
			 mayor = cromosomas[i].getAptitud();
		 }
		}
		return mayor;
	}
	
	public static int menor() {
		int menor = cromosomas[0].getAptitud();
		for (int i = 1; i < cromosomas.length; i++) {
		 if(cromosomas[i].getAptitud()<menor) {
			 menor = cromosomas[i].getAptitud();
		 }
		}
		return menor;
	}
	
	
	

}
