class Cola {
    private Luchador[] luchadores;
    private int frente;
    private int fin;
    private int tamaño;

    public Cola(int capacidad) {
        luchadores = new Luchador[capacidad];
        frente = 0;
        fin = 0;
        tamaño = 0;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public boolean estaLlena() {
        return tamaño == luchadores.length;
    }

    public void enqueue(Luchador l) {
        if (!estaLlena()) {
            luchadores[fin] = l;
            fin = (fin + 1) % luchadores.length;
            tamaño++;
        } else {
            System.out.println("La cola está llena");
        }
    }

    public Luchador dequeue() {
        if (!estaVacia()) {
            Luchador l = luchadores[frente];
            frente = (frente + 1) % luchadores.length;
            tamaño--;
            return l;
        } else {
            return null;
        }
    }

    public int getTamaño() {
        return tamaño;
    }

    // Método para obtener todos los luchadores en orden de cola
    public String listarCola() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamaño; i++) {
            int index = (frente + i) % luchadores.length;
            sb.append((i + 1) + ". " + luchadores[index].getNombre() + "\n");
        }
        return sb.toString();
    }

    // Método para obtener un luchador en la posición i sin quitarlo de la cola
    public Luchador obtenerEn(int i) {
        if (i < 0 || i >= tamaño) return null;
        int index = (frente + i) % luchadores.length;
        return luchadores[index];
    }
}