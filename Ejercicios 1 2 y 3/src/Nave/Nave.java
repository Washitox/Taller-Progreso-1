class Nave {
    private int id;

    public Nave(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Nave) {
            return this.id == ((Nave) obj).id;
        }
        return false;
    }
}