public class Predmet {
    private DruhPredmetu druhPredmetu;
    private String jmeno;

    public Predmet(DruhPredmetu druhPredmetu, String jmeno) {
        this.druhPredmetu = druhPredmetu;
        this.jmeno = jmeno;
    }

    public DruhPredmetu getDruhPredmetu() {
        return this.druhPredmetu;
    }

    @Override
    public String toString() {
        return "Druh:" + druhPredmetu + ", Název: " + jmeno;
    }
}