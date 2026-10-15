import java.util.Random;
import java.util.Scanner;

public class Hrac {
    private String name;
    private int zivoty;
    private int zlataky;
    private Array<Predmet> inventar;
    private static final Scanner SC = new Scanner(System.in);
    Scanner sc = SC;

    public Hrac(String name) {
        this.name = name;
        this.zivoty = 20;
        this.zlataky = 10;
        this.inventar = new Array<>(5);
    }

    private static final int CENA = 3;

    public boolean pridejInventar(DruhPredmetu druh, String nazev) {
        if (this.zlataky < CENA) {
            return false;
        }
        if (!this.inventar.pridejPolozku(new Predmet(druh, nazev))) {
            return false;
        }
        this.zlataky -= CENA;
        return true;
    }

    public static Hrac vytvorHrace() {
        Scanner scanner = SC;
        String jmeno = "";
        while (jmeno.isBlank()) {
            System.out.println("Zadej jmeno hrace:");
            jmeno = scanner.nextLine().trim();
        }
        return new Hrac(jmeno);
    }

    public void nakup() {
        DruhPredmetu[] druhy = DruhPredmetu.values();
        while (true) {
            System.out.println(this.name + " ma " + this.zlataky + " zlataku. Predmet stoji " + CENA + " zlataky. (-1=konec)");
            for (int i = 0; i < druhy.length; i++) {
                System.out.println(i + ": " + druhy[i]);
            }
            System.out.println("Zadej cislo druhu predmetu:");
            int volba = nactiCislo();
            if (volba == -1) {
                return;
            }
            if (volba < 0 || volba >= druhy.length) {
                System.out.println("Neplatna volba.");
                continue;
            }
            System.out.println("Zadej nazev predmetu:");
            String nazev = SC.nextLine().trim();
            if (nazev.isEmpty()) {
                System.out.println("Nic jsi nevybral.");
                continue;
            }
            if (this.pridejInventar(druhy[volba], nazev)) {
                System.out.println("Koupeno: " + nazev + ". Zbyva ti " + this.zlataky + " zlataku.");
            } else {
                System.out.println("Nelze koupit (malo zlataku nebo plny inventar).");
            }
        }
    }

    public void vyzvatDynamicky(Hrac souper) {
        System.out.println(this.name + " vyzyva " + souper.name + ". Souhlasi " + souper.name + "? (a/n)");
        String odp = SC.nextLine().trim().toLowerCase();
        if (odp.equals("a") || odp.equals("ano")) {
            this.vyzvatNaSouboj(souper);
            System.out.println(this.name + ": " + this.zivoty + " zivotu, " + souper.name + ": " + souper.zivoty + " zivotu");
        } else {
            System.out.println(souper.name + " vyzvu odmitl.");
        }
    }

    private int nactiCislo() {
        while (true) {
            String radek = SC.nextLine().trim();
            try {
                return Integer.parseInt(radek);
            } catch (NumberFormatException e) {
                System.out.println("Zadej cele cislo:");
            }
        }
    }

    public Array<Predmet> otevritInv() {
        return this.inventar;
    }

    public void vyzvatNaSouboj(Hrac souper) {
        System.out.println(this.otevritInv());
        System.out.println("Chces neco pouzit? zadej index");
        int odpoved = nactiCislo();
        if (odpoved >= 0) {
            Predmet predmet = this.inventar.vratPolozku(odpoved);
            if (predmet != null) {
                System.out.println("Pouzil jsi: " + predmet);
            } else {
                System.out.println("Predmet neexistuje.");
            }
        }

        int ja = this.hodKockou();
        int on = souper.hodKockou();
        System.out.println(this.name + " hodil " + ja + ", " + souper.name + " hodil " + on);
        if (ja > on) {
            souper.zivoty -= ja - on;
        } else if (on > ja) {
            this.zivoty -= on - ja;
        }
    }

    private int hodKockou() {
        return (int) (Math.random() * 6) + 1;
    }



    @Override
    public String toString() {
        return "Hrac [name=" + name + ", zivoty=" + zivoty + ", zlataky=" + zlataky + ", inventar=" + inventar + "]";
    }

    
    
}
