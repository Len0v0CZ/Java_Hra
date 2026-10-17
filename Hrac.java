
import java.util.Scanner;

public class Hrac {
    private String name;
    private int zivoty;
    private int zlataky;
    private Array<Predmet> inventar;
    private Scanner scanner;
    private int kolo;
    public Hrac(Scanner scanner) {
        this.scanner = scanner;
        System.out.print("Zadej jméno hráče: ");
        this.name = scanner.nextLine();
        this.zivoty = 25;
        this.zlataky = 29;
        this.inventar = new Array<>(7);
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
        kolo = 0;
        while (this.zivoty > 0 && souper.zivoty > 0) {
            System.out.println("\n--- Nové kolo ---");
            System.out.println("Stav: " + this.name + " (" + this.zivoty + " HP) vs " + souper.name + " (" + souper.zivoty + " HP)");
            System.out.println(this.name + " inventář: " + this.inventar);
            System.out.println(souper.name + " inventář: " + souper.inventar);

            int indexMojehoPredmetu;
            int indexSouperovaPredmetu;
            Predmet myPredmet;
            Predmet souperPredmet;
            DruhPredmetu mujEfekt;
            DruhPredmetu souperEfekt;

            if (kolo % 2 == 0) {
                System.out.print(this.name + ", zadej index předmětu, který chceš použít (nebo -1 pro žádný): ");
                indexMojehoPredmetu = scanner.nextInt();
                myPredmet = this.inventar.vratPolozku(indexMojehoPredmetu);
                mujEfekt = (myPredmet != null) ? myPredmet.getDruhPredmetu() : null;

                System.out.print(souper.name + ", zadej index předmětu, který chceš použít (nebo -1 pro žádný): ");
                indexSouperovaPredmetu = scanner.nextInt();
                souperPredmet = souper.inventar.vratPolozku(indexSouperovaPredmetu);
                souperEfekt = (souperPredmet != null) ? souperPredmet.getDruhPredmetu() : null;
            } else {
                System.out.print(souper.name + ", zadej index předmětu, který chceš použít (nebo -1 pro žádný): ");
                indexSouperovaPredmetu = scanner.nextInt();
                souperPredmet = souper.inventar.vratPolozku(indexSouperovaPredmetu);
                souperEfekt = (souperPredmet != null) ? souperPredmet.getDruhPredmetu() : null;

                System.out.print(this.name + ", zadej index předmětu, který chceš použít (nebo -1 pro žádný): ");
                indexMojehoPredmetu = scanner.nextInt();
                myPredmet = this.inventar.vratPolozku(indexMojehoPredmetu);
                mujEfekt = (myPredmet != null) ? myPredmet.getDruhPredmetu() : null;
            }

            int ja = this.hodKockou();
            int on = souper.hodKockou();

            if (mujEfekt == DruhPredmetu.BOW) {
                ja += (this.hodKockou() - 1);
            }

            if (souperEfekt == DruhPredmetu.BOW) {
                on += souper.hodKockou() - 1;
            }

            if (mujEfekt == DruhPredmetu.POTION) {
                int mojeKocka = this.hodKockou();
                if (mojeKocka <= 2) {
                    this.zivoty += 1;
                    System.out.println(this.name + " použil lektvar a získal 1 život.");
                } else if (mojeKocka <= 4) {
                    this.zivoty += 3;
                    System.out.println(this.name + " použil lektvar a získal 3 životy.");
                } else {
                    this.zivoty += 5;
                    System.out.println(this.name + " použil lektvar a získal 5 životů.");
                }
            }

            if (souperEfekt == DruhPredmetu.POTION) {
                int souperKocka = souper.hodKockou();
                if (souperKocka <= 2) {
                    souper.zivoty += 1;
                    System.out.println(souper.name + " použil lektvar a získal 1 život.");
                } else if (2 < souperKocka && souperKocka <= 4) {
                    souper.zivoty += 3;
                    System.out.println(souper.name + " použil lektvar a získal 3 životy.");
                } else {
                    souper.zivoty += 5;
                    System.out.println(souper.name + " použil lektvar a získal 5 životů.");
                }
            }

            int damageNaSoupera = (mujEfekt == DruhPredmetu.MEC) ? ja * 2 - 1 : ja;
            if (souperEfekt == DruhPredmetu.ARMOR) {
                damageNaSoupera = (damageNaSoupera / 2) - 1;
            }

            int damageNaMne = (souperEfekt == DruhPredmetu.MEC) ? on * 2 - 1 : on;
            if (mujEfekt == DruhPredmetu.ARMOR) {
                damageNaMne = (damageNaMne / 2) - 1;
            }

            if (mujEfekt == DruhPredmetu.SHIELD) {
                if (this.hodKockou() > 3) {
                    damageNaMne = 0;
                    System.out.println(this.name + " použil štít a zablokoval útok!");
                } 
                else {
                    System.out.println(this.name + " použil štít, ale neblokoval útok.");
                }
            }

            if (souperEfekt == DruhPredmetu.SHIELD) {
                if (souper.hodKockou() > 3) {
                    damageNaSoupera = 0;
                    System.out.println(souper.name + " použil štít a zablokoval útok!");
                } 
                else {
                    System.out.println(souper.name + " použil štít, ale neblokoval útok.");
                }
            }

            System.out.println(this.name + " hodil: " + ja + " a způsobuje " + damageNaSoupera + " poškození soupeři.");
            System.out.println(souper.name + " hodil: " + on + " a způsobuje " + damageNaMne + " poškození tobě.");
            souper.zivoty -= damageNaSoupera;
            this.zivoty -= damageNaMne;
            inventar.odeberPolozku(indexMojehoPredmetu);
            souper.inventar.odeberPolozku(indexSouperovaPredmetu);
            kolo++;
        }
        System.out.println("\n=== KONEC SOUBOJE ===");
        if (this.zivoty <= 0 && souper.zivoty <= 0) {
            System.out.println("Oba hráči zemřeli");
        } else if (this.zivoty > 0) {
            System.out.println("Vítězem je " + this.name);
            this.zlataky += 24;
        } else {
            System.out.println("Vítězem je " + souper.name);
            souper.zlataky += 24;
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