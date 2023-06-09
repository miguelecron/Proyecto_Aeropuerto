package Tarea12_Aeropuerto.modelo;

public class Regular extends Vuelo {

    private int plazasLibres;

    public Regular(String destino, String modeloAvion, int plazas, int plazasLibres, int precio) {
        super(destino, modeloAvion, plazas, precio);
        this.plazasLibres = plazasLibres;
    }

    // ------------------------- GETTERS & SETTERS -----------------------------------

    public int getPlazasLibres() {
        return plazasLibres;
    }

    public void setPlazasLibres(int plazasLibres) {
        this.plazasLibres = plazasLibres;
    }

    // --------------------------- METODOS OVERRIDE -----------------------------------


    @Override
    public double calcularPrecioBillete() {
        return getPrecio() * 1.1 + (5 * plazasLibres);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nVuelo Regular");
        sb.append("\n--------------");
        sb.append(super.toString());
        sb.append("\nPlazas Libres: ").append(plazasLibres);

        return sb.toString();
    }
}
