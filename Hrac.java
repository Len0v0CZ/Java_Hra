
import java.util.Scanner;

public class Hrac {
    private String name;
    private int zivoty;
    private int zlataky;
    private Array<Predmet> inventar;
    private Scanner scanner;

    public Hrac(Scanner scanner) {
        this.scanner = scanner;
        System.out.print("Zadej jméno hráče: ");
        this.name = scanner.nextLine();
        this.zivoty = 25;
        this.zlataky = 20;
        this.inventar = new Array<>(5);
    }

    public Array<Predmet> otevritInv() {
        return this.inventar;
    }

    public int getZlataky() {
        return this.zlataky;
    }

    public String getName() {
        return this.name;
    }

    public boolean koupitPredmet(DruhPredmetu druh, String nazev, int cena) {
        if (this.zlataky < cena) {
            return false;
        }
        if (!this.inventar.pridejPolozku(new Predmet(druh, nazev))) {
            return false;
        }
        this.zlataky -= cena;
        return true;
    }

    public void navstivitObchod(Obchod obchod) {
        obchod.nakup(this, this.scanner);
    }

    public void vyzvatNaSouboj(Hrac souper) {

        while (this.zivoty > 0 && souper.zivoty > 0) {
            System.out.println("\n--- Nové kolo ---");
            System.out.println("Stav: " + this.name + " (" + this.zivoty + " HP) vs " + souper.name + " (" + souper.zivoty + " HP)");
            System.out.println(this.name + " inventář: " + this.inventar);
            System.out.println(souper.name + " inventář: " + souper.inventar);

            System.out.print(this.name + ", zadej index předmětu, který chceš použít (nebo -1 pro žádný): ");
            int indexMojehoPredmetu = scanner.nextInt();
            Predmet myPredmet = this.inventar.vratPolozku(indexMojehoPredmetu);
            DruhPredmetu mujEfekt = (myPredmet != null) ? myPredmet.getDruhPredmetu() : null;

            System.out.print(souper.name + ", zadej index předmětu, který chceš použít (nebo -1 pro žádný): ");
            int indexSouperovaPredmetu = scanner.nextInt();
            Predmet souperPredmet = souper.inventar.vratPolozku(indexSouperovaPredmetu);
            DruhPredmetu souperEfekt = (souperPredmet != null) ? souperPredmet.getDruhPredmetu() : null;

            int ja = this.hodKockou();
            int on = souper.hodKockou();

            if (mujEfekt == DruhPredmetu.BOW) {
                ja += (this.hodKockou()/2);
            }

            if (souperEfekt == DruhPredmetu.BOW) {
                on += souper.hodKockou();
            }

            if (mujEfekt == DruhPredmetu.POTION) {
                this.zivoty += 5;
            }

            if (souperEfekt == DruhPredmetu.POTION) {
                souper.zivoty += 5;
            }

            int damageNaSoupera = (mujEfekt == DruhPredmetu.MEC) ? ja * 2 : ja;
            if (souperEfekt == DruhPredmetu.ARMOR) {
                damageNaSoupera /= 2;
            }

            int damageNaMne = (souperEfekt == DruhPredmetu.MEC) ? on * 2 : on;
            if (mujEfekt == DruhPredmetu.ARMOR) {
                damageNaMne /= 2;
            }

            
            if (mujEfekt == DruhPredmetu.SHIELD) {
                if(hodKockou() > 3) {
                    damageNaMne = 0;
                }
            }

            if (souperEfekt == DruhPredmetu.SHIELD) {
                if(hodKockou() > 3) {
                    damageNaSoupera = 0;
                }
            }

            System.out.println(this.name + " hodil: " + ja + " a způsobuje " + damageNaSoupera + " poškození soupeři.");
            System.out.println(souper.name + " hodil: " + on + " a způsobuje " + damageNaMne + " poškození tobě.");
            souper.zivoty -= damageNaSoupera;
            this.zivoty -= damageNaMne;
            inventar.odeberPolozku(indexMojehoPredmetu);
            souper.inventar.odeberPolozku(indexSouperovaPredmetu);
        }
        System.out.println("\n=== KONEC SOUBOJE ===");
        if (this.zivoty <= 0 && souper.zivoty <= 0) {
            System.out.println("Oba hráči zemřeli");
        } else if (this.zivoty > 0) {
            System.out.println("Vítězem je " + this.name);
            this.zlataky += 22;
        } else {
            System.out.println("Vítězem je " + souper.name);
            souper.zlataky += 22;
        }
        this.zivoty = 25;
        souper.zivoty = 25;
        if (this.zlataky <= 5) {
            this.zlataky = 6;
        }

        if (souper.zlataky <= 5) {
            souper.zlataky = 6;
        }
    }

    private int hodKockou() { // hod kočkou
        return (int)(Math.random() * 6) + 1;
    }

    @Override
    public String toString() {
        return "Hrac [name=" + name + ", zivoty=" + zivoty + ", zlataky=" + zlataky + ", inventar=\n" + inventar + "]";
    }
}