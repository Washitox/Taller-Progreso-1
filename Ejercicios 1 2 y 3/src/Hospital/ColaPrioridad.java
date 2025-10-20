class ColaPrioridad {
    private Paciente[] pacientes;
    private int tamaño;

    public ColaPrioridad(int capacidad) {
        pacientes = new Paciente[capacidad];
        tamaño = 0;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public boolean estaLlena() {
        return tamaño == pacientes.length;
    }

    // Inserta paciente manteniendo el arreglo ordenado por prioridad (menor número = mayor prioridad)
    public void enqueue(Paciente p) {
        if (estaLlena()) return;
        int i = tamaño - 1;
        // mover hacia la derecha todos los que tienen prioridad mayor (número más grande)
        while (i >= 0 && pacientes[i].getPrioridad() > p.getPrioridad()) {
            pacientes[i + 1] = pacientes[i];
            i--;
        }
        pacientes[i + 1] = p; // insertar en su lugar
        tamaño++;
    }

    // Extrae el primer elemento (el de mayor prioridad)
    public Paciente dequeue() {
        if (estaVacia()) return null;
        Paciente atendido = pacientes[0];
        // Desplazar hacia la izquierda
        for (int i = 1; i < tamaño; i++) {
            pacientes[i - 1] = pacientes[i];
        }
        pacientes[tamaño - 1] = null;
        tamaño--;
        return atendido;
    }

    public int getTamaño() {
        return tamaño;
    }

    // Devuelve la lista en orden
    public String listarCola() {
        if (estaVacia()) return "(vacía)\n";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamaño; i++) {
            sb.append((i + 1) + ". " + pacientes[i].toString() + "\n");
        }
        return sb.toString();
    }

    // Busca por nombre y devuelve la posición 1-based o -1 si no existe
    public int buscarPosicion(String nombre) {
        for (int i = 0; i < tamaño; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                return i + 1;
            }
        }
        return -1;
    }
}