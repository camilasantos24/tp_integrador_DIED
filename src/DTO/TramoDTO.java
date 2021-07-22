package DTO;

public class TramoDTO {

	
		private int cod_origen;
		private int cod_destino;
		private float distancia;
		private int duracion;
		private int cant_pas;
		private float costo;
		
		public TramoDTO() {};
		
		public int getCod_origen() {
			return cod_origen;
		}
		public void setCod_origen(int cod_origen) {
			this.cod_origen = cod_origen;
		}
		public int getCod_destino() {
			return cod_destino;
		}
		public void setCod_destino(int cod_destino) {
			this.cod_destino = cod_destino;
		}
		
		public float getDistancia() {
			return distancia;
		}
		public void setDistancia(float distancia) {
			this.distancia = distancia;
		}
		public int getDuracion() {
			return duracion;
		}
		public void setDuracion(int duracion) {
			this.duracion = duracion;
		}
		public int getCant_pas() {
			return cant_pas;
		}
		public void setCant_pas(int cant_pas) {
			this.cant_pas = cant_pas;
		}
		public float getCosto() {
			return costo;
		}
		public void setCosto(float costo) {
			this.costo = costo;
		}
		
		
	

}
