package Tarea12_Aeropuerto.modelo;

public abstract class Vuelo implements Comparable<Vuelo>{

    private String destino;
    private String modeloAvion;
    private int plazas;
    private int precio;

    public Vuelo(String destino, String modeloAvion, int plazas, int precio) {
        this.destino = destino;
        this.modeloAvion = modeloAvion;
        this.plazas = plazas;
        this.precio = precio;
    }

    public abstract double calcularPrecioBillete();

    // ------------------------- GETTERS & SETTERS -----------------------------------

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getModeloAvion() {
        return modeloAvion;
    }

    public void setModeloAvion(String modeloAvion) {
        this.modeloAvion = modeloAvion;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    // --------------------------- METODOS OVERRIDE ------------------

    @Override
    public int compareTo(Vuelo o) {
        if (this.destino.equals(o.destino)) {
            if (this.modeloAvion.compareTo(o.modeloAvion) == 0) {
                return this.plazas - o.plazas;
            } else {
                return this.modeloAvion.compareTo(o.modeloAvion);
            }
        } else {
            return this.destino.compareTo(o.destino);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vuelo vuelo = (Vuelo) o;

        if (plazas != vuelo.plazas) return false;
        if (!destino.equals(vuelo.destino)) return false;
        return modeloAvion.equals(vuelo.modeloAvion);
    }

    @Override
    public int hashCode() {
        int result = destino.hashCode();
        result = 33 * result + modeloAvion.hashCode();
        result = 33 * result + plazas;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n");
        sb.append("Destino: ").append(destino);
        sb.append("\nAvion: ").append(modeloAvion);
        sb.append("\nPlazas: ").append(plazas);
        sb.append("n Precio billete: ").append(calcularPrecioBillete()).append(" €");
        return sb.toString();
    }
}
