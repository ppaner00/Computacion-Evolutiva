package evolucion;

public class Cromosoma {

	private int gen1;
	private int gen2;
	private int gen3;
	private int gen4;
	private int aptitud;
	
	/**
	 * @param gen1
	 * @param gen2
	 * @param gen3
	 * @param gen4
	 */
	public Cromosoma(int gen1, int gen2, int gen3, int gen4) {
		this.gen1 = gen1;
		this.gen2 = gen2;
		this.gen3 = gen3;
		this.gen4 = gen4;
	}
	
	
	public int getGen1() {
		return gen1;
	}
	public void setGen1(int gen1) {
		this.gen1 = gen1;
	}
	public int getGen2() {
		return gen2;
	}
	public void setGen2(int gen2) {
		this.gen2 = gen2;
	}
	public int getGen3() {
		return gen3;
	}
	public void setGen3(int gen3) {
		this.gen3 = gen3;
	}
	public int getGen4() {
		return gen4;
	}
	public void setGen4(int gen4) {
		this.gen4 = gen4;
	}
	public int getAptitud() {
		return aptitud;
	}
	public void setAptitud(int aptitud) {
		this.aptitud = aptitud;
	}

	@Override
	public String toString() {
		return "[" + gen1 + "," + gen2 + "," + gen3 + "," + gen4 + "] con aptitud = " + aptitud;
	}
}
