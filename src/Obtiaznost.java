public enum Obtiaznost {
    LAHKA(400),
    STREDNA(320),
    TAZKA(280);

    private final int cislo;

    Obtiaznost(int cislo) {
        this.cislo = cislo;
    }

    public int getCislo() {
        return this.cislo;
    }
}

