public class Hrac {
    private String name;
    private int zivoty;
    private int zlataky;
    private Array<Predmet> inventar;

    public Hrac(String name) {
        this.name = name;
        this.zivoty = 20;
        this.zlataky = 10;
        this.inventar = new Array<>(5);
    }
    public boolean koupitPredmet(DruhPredmetu druh, String nazev) {
        if (this.zlataky < 3) {
            return false;
        }
        if (this.inventar.pridejPolozku(new Predmet(druh, nazev))) {
            this.zlataky -= 3;
            return true;
        }
        return false;
    }

    public Array<Predmet> otevritInv() {
        return this.inventar;
    }

    public void vyzvatNaSouboj(Hrac souper, int indexPredmetu) {
        Predmet zvolenyPredmet = this.inventar.vratPolozku(indexPredmetu);
        DruhPredmetu efekt = (zvolenyPredmet != null) ? zvolenyPredmet.getDruhPredmetu() : null;

        int ja = this.hodKockou();
        int on = souper.hodKockou();

        if (efekt == DruhPredmetu.BOW) {
            ja += this.hodKockou();
        }

        int zakladniDamage = 4;

        if (ja > on) {
            int damage = (efekt == DruhPredmetu.MEC) ? zakladniDamage * 2 : zakladniDamage;
            souper.zivoty -= damage;
            System.out.println(this.name + " vyhral (" + ja + " vs " + on + "). " + souper.name + " ztraci " + damage + " HP.");
        } else if (on > ja) {
            int damage = (efekt == DruhPredmetu.ARMOR) ? zakladniDamage / 2 : zakladniDamage;
            this.zivoty -= damage;
            System.out.println(this.name + " prohral (" + ja + " vs " + on + "). Ztraci " + damage + " HP.");
        } else {
            System.out.println("Remiza (" + ja + " vs " + on + "). Nikdo neztraci HP.");
        }
    }

    private int hodKockou() {
        return (int)(Math.random() * 6) + 1;
    }

    @Override
    public String toString() {
        return "Hrac [name=" + name + ", zivoty=" + zivoty + ", zlataky=" + zlataky + ", inventar=\n" + inventar + "]";
    }
}