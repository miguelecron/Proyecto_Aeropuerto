package Tarea12_Aeropuerto.modelo;

import java.util.*;

public class Aeropuerto {

    private Map<String, Set<Vuelo>> vuelos;

    public Aeropuerto() {
        vuelos = new TreeMap<>();
    }

    /**
     * Anade un vuelo a la aerolinea correspondiente solo en el caso de que el vuelo
     * no estuviese ya introducido, si la aerolinea no existiese se anade tanto la
     * aerolinea como el vuelo.
     */
    public void addVuelo(String aerolinea, Vuelo vuelo) {

        if (!this.vuelos.containsKey(aerolinea)) {
            Set<Vuelo> tmpVuelos = new TreeSet<>();
            tmpVuelos.add(vuelo);
            this.vuelos.put(aerolinea, tmpVuelos);
        } else {
            this.vuelos.get(aerolinea).add(vuelo);
        }
    }

    /**
     * Imprime los vuelos por cada aerolinea ordenados por destino, tanto aerolineas
     * como vuelos estaran ordenados alfabeticamente (Ver resultados de ejecucion)
     */
    public void ordenAerolineasAlfabetico() {
        for (String aerolinea : vuelos.keySet()) {
            System.out.println();
            System.out.println(aerolinea);
            System.out.println("========");
            for (Vuelo vuelo : this.vuelos.get(aerolinea)) {
                System.out.println(vuelo);
            }
        }
    }

    /**
     * Muestra los vuelos regulares de la aerolinea pasada por parametro, se
     * visualizaran de mayor a menor segun el numero de plazas
     *
     * @param aerolinea Aerolinea de la que se imprimiran los vuelos regulares
     */
    public void regularPorPlazas(String aerolinea) {
        Set<Regular> vuelosRegulares = new TreeSet<>(new Comparator<Regular>() {
            @Override
            public int compare(Regular r1, Regular r2) {
                // Ordenar por plazas libres de mayor a menor
                return r2.getPlazasLibres() - r1.getPlazasLibres();
            }
        });

        // Cogemos solo los vuelos regulares y los añadimos a vuelosRegulares

        for (Vuelo vuelo : this.vuelos.get(aerolinea)) {
            if (vuelo instanceof Regular) {
                vuelosRegulares.add((Regular) vuelo);
            }
        }

        // Imprimimos los vuelos regulares por consola

        for (Regular regular : vuelosRegulares) {
            System.out.println(regular);
        }
    }


    /**
     * Devuelve una lista con vuelos regulares con plazas libres
     *
     * @return aerolina Aerolina del avion charter con m?s capacidad
     */
    public List<Vuelo> plazasLibres() {
        List<Vuelo> vuelosConPlazasLibres = new ArrayList<>();

        for (String aerolinea: this.vuelos.keySet()) {
            for (Vuelo vuelo: this.vuelos.get(aerolinea)) {
                if ((vuelo instanceof Regular) && ((Regular) vuelo).getPlazasLibres() > 0){
                    vuelosConPlazasLibres.add((Regular) vuelo);
                }
            }
        }

        return vuelosConPlazasLibres;
    }

    /**
     * Muestra el numero de vuelos de cada aerolinea que llegan al destino pasado
     * por parametro, ver resultados de ejecucion
     *
     * @param destino Destino del que se debe sacar la estadistica
     */
    public void estadisticaDestino(String destino) {

        for (String aerolinea: this.vuelos.keySet()) {
            int vuelosTotales = 0;
            int vuelosDestino = 0;

            for (Vuelo vuelo: this.vuelos.get(aerolinea)) {
                if (vuelo.getDestino().equals(destino)){
                    vuelosDestino++;
                    vuelosTotales++;
                } else {
                    vuelosTotales++;
                }
            }
            System.out.println("\n" + vuelosDestino + " de cada " + vuelosTotales + " de la aerolinea "
             + aerolinea + " vuelan a " + destino);
        }
    }

    /**
     * Borra los vuelos reservados por una empresa y devuelve el numero de vuelos
     * borrados, utiliza un conjunto de entradas
     *
     * @param nifEmpresa
     * @return numero de vuelos borrados
     */
    public int borrarVuelosEmpresa(String nifEmpresa) {
        Set<Map.Entry<String, Set<Vuelo>>> tmpVuelos = this.vuelos.entrySet();
        int vuelosBorrados = 0;

        for (Map.Entry<String, Set<Vuelo>> vuelos: tmpVuelos) {

            Iterator<Vuelo> it = vuelos.getValue().iterator();

            while (it.hasNext()){

                Vuelo vuelo = it.next();

                if ((vuelo instanceof Charter) && (((Charter) vuelo).getNifEmpresa().equals(nifEmpresa))){
                    it.remove();
                    vuelosBorrados++;
                }
            }
        }

        return vuelosBorrados;
    }

    /**
     * Imprime la lista de vuelos pasada por parametro
     *
     * @param listaVuelos
     */
    public void imprimirListaVuelos(List<Vuelo> listaVuelos) {
        for (Vuelo vuelo: listaVuelos) {
            System.out.println(vuelo);
        }
    }

    /**
     * Para una aerolínea, calcula el número total de viajeros entre todos sus vuelos.
     * El cálculo del número de viajeros se hará de la siguiente manera:
     * ? Vuelos Charter: será igual al número de plazas del vuelo.
     * ? Vuelos Regulares: será la resta del número de plazas del vuelo menos el de plazas libres.
     * @param aerolinea aerolínea de la que queremos calcular el número de viajeros
     */
    public int imprimirPasajerosPorAerolinea(String aerolinea){
        int totalViajeros = 0;

        for (Vuelo vuelo: this.vuelos.get(aerolinea)) {
            if (vuelo instanceof Charter){
                totalViajeros += vuelo.getPlazas();
            } else {
                totalViajeros += ((Regular) vuelo).getPlazas() - ((Regular) vuelo).getPlazasLibres();
            }
        }
        return totalViajeros;
    }

    /**
     *
     Imprime, por cada línea, los vuelos cuyo número de plazas es igual o superior
     al número de plazas de todos los vuelos de esa aerolínea.
     */
    public void imprimirVuelosMasPasajerosQueMedia(){
        for (String aerolinea: this.vuelos.keySet()) {
            double mediaPlazas = mediaPlazasAerolinea(aerolinea);
            System.out.println("\nLa media de plazas de los vuelos de " + aerolinea + " es " + mediaPlazas);
            System.out.println("Los vuelos de " + aerolinea + " con más plazas que la media son: ");
            for (Vuelo vuelo: this.vuelos.get(aerolinea)) {
                if (vuelo.getPlazas() >= mediaPlazas){
                    System.out.println(vuelo.toString());
                }
            }
        }
    }

    /**
     * Calcula la media de plazas de una aerolínea;
     * @param aerolinea
     * @return
     */
    private double mediaPlazasAerolinea(String aerolinea){
        double plazasTotales = 0;
        double numeroVuelos = 0;

        for (Vuelo vuelo: this.vuelos.get(aerolinea)) {
            numeroVuelos++;
            plazasTotales += vuelo.getPlazas();
        }
        return plazasTotales / numeroVuelos;
    }

    /**
     * Represetaci?n textual del mapa tal y como se muestra en el enunciado
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (String aerolinea: this.vuelos.keySet()) {
            sb.append("\n");
            sb.append(aerolinea);
            sb.append("\n========");
            for (Vuelo vuelo: this.vuelos.get(aerolinea)) {
                sb.append("\n");
                sb.append(vuelo.toString());
            }
        }

        return sb.toString();
    }

    /**
     * Rellena el mapa haciendo uso de un fichero de texto
     */
    public void leerFicheroCursos() {
        Scanner entrada = null;
        try {
            entrada = new Scanner(this.getClass().getResourceAsStream("../aviones.txt"));
            while (entrada.hasNextLine()) {
                String linea = entrada.nextLine();
                int pos = linea.indexOf(":");
                String aerolinea = linea.substring(0, pos);
                String[] vuelo = linea.substring(pos + 1).split(":");
                String destino = vuelo[1];
                String avion = vuelo[2];
                int plazas = Integer.parseInt(vuelo[3].trim());
                if (vuelo[0].equals("R")) {
                    int plazasLibres = Integer.parseInt(vuelo[4].trim());
                    this.addVuelo(aerolinea, new Regular(destino, avion, plazas, plazasLibres));
                } else {
                    String nifEmpresa = vuelo[4];
                    this.addVuelo(aerolinea, new Charter(destino, avion, plazas, nifEmpresa));
                }
            }

        } finally {
            try {
                entrada.close();
            } catch (NullPointerException e) {
                System.out.println("Error en IO , no se ha creado el fichero");
            }
        }

    }

}
