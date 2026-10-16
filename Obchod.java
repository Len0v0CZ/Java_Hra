import java.util.Scanner;

public class Obchod {
    private DruhPredmetu[] druhy = {
        DruhPredmetu.MEC,
        DruhPredmetu.POTION,
        DruhPredmetu.SHIELD,
        DruhPredmetu.ARMOR,
        DruhPredmetu.BOW,
    };
    private String[] nazvy = {
        "Rudův rudý meč",
        "Bílá krémová",
        "Banner Purkyňky",
        "Rausova košile",
        "Čížkovo oko"
    };
    private int[] ceny = { 3, 3, 3, 3, 3 };

    public void nakup(Hrac hrac, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Nabidka obchodu ---");
            for (int index = 0; index < nazvy.length; index++) {
                System.out.println(index + ": " + nazvy[index] + " (" + druhy[index] + ") - " + ceny[index] + " zlataku");
            }
            System.out.println("-1: Odejit z obchodu");
            System.out.println("Aktualni balance: " + hrac.getZlataky() + " zlataku");
            System.out.println("Pocet veci v inventari: " + hrac.otevritInv().pocetPolozek());
            System.out.print("Zadej index predmetu, ktery chces koupit: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Zadej platne cislo.");
                scanner.next();
                continue;
            }

            int index = scanner.nextInt();
            if (index == -1) {
                return;
            }
            if (index < 0 || index >= nazvy.length) {
                System.out.println("Nemame");
                continue;
            }

            if (hrac.koupitPredmet(druhy[index], nazvy[index], ceny[index])) {
                System.out.println("Koupil jsi: " + nazvy[index] + ". Zbyva ti " + hrac.getZlataky() + " zlataku.");
            } else {
                System.out.println("Seš chudej lol (nebo plnej inv)");
            }
        }
    }
}
