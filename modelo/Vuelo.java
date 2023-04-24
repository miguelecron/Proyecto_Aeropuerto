package Tarea12_Aeropuerto.modelo;

public abstract class Vuelo implements Comparable<Vuelo>{

    private String destino;
    private String modeloAvion;
    private int plazas;

    public Vuelo(String destino, String modeloAvion, int plazas) {
        this.destino = destino;
        this.modeloAvion = modeloAvion;
        this.plazas = plazas;
    }

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

    // --------------------------- METODOS OVERRIDE ------------------

    @Override
    public int compareTo(Vuelo o) {
        if (this.destino.equals(o.destino)) {
            if (this.plazas == o.plazas) {
                return this.modeloAvion.compareTo(o.modeloAvion);
            } else {
                return this.plazas - o.plazas;
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
        result = 31 * result + modeloAvion.hashCode();
        result = 31 * result + plazas;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n");
        sb.append("Destino: ").append(destino);
        sb.append("\n Avion: ").append(modeloAvion);
        sb.append("\n Plazas: ").append(plazas);
        return sb.toString();
    }
}
