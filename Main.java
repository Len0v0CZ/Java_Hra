import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hrac hrac1 = new Hrac(scanner);
        Hrac hrac2 = new Hrac(scanner);
        Obchod obchod = new Obchod();
        Hrac aktivniHrac = hrac1;

        while (true) {
            System.out.println("\n--- Tah hráče: " + aktivniHrac.getName() + " ---");
            System.out.println("0: Vyzvat k souboji");
            System.out.println("1: obchod");
            System.out.println("2: inventář");
            System.out.println("3: Přepnout na hráče 2");
            System.out.print("Vyber akci: ");

            if (!scanner.hasNext()) {
                break;
            }
            int volba = scanner.nextInt();
            if (volba == 0) {
                Hrac souper = aktivniHrac == hrac1 ? hrac2 : hrac1;
                aktivniHrac.vyzvatNaSouboj(souper);
            } else if (volba == 1) {
                aktivniHrac.navstivitObchod(obchod);
            } else if (volba == 2) {
                System.out.println(aktivniHrac);
            } else if (volba == 3) {
                aktivniHrac = aktivniHrac == hrac1 ? hrac2 : hrac1;
            } else {
                System.out.println("Neplatná volba.");
            }
        }

        System.out.println("\nKonečný stav hráčů:");
        System.out.println(hrac1);
        System.out.println(hrac2);
    }
}