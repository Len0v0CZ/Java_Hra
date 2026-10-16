class Main {
    public static void main(String[] args) {
        Hrac hrac1 = new Hrac("Damir");
        Hrac hrac2 = new Hrac("Karel");

        // Nakup v obchode (kazda vec za 3 goldy)
        hrac1.koupitPredmet(DruhPredmetu.MEC, "Ocelovy mec");     // index 0
        hrac1.koupitPredmet(DruhPredmetu.ARMOR, "Zelezna zbroj"); // index 1
        hrac1.koupitPredmet(DruhPredmetu.BOW, "Dlouhy luk");      // index 2

        System.out.println(hrac1);

        // Hrac 1 vyzve Hrace 2 a zvoli si predmet na indexu 0 (MEC)
        hrac1.vyzvatNaSouboj(hrac2, 0);

        // Dalsi souboj, Hrac 1 zvoli predmet na indexu 1 (ARMOR)
        hrac1.vyzvatNaSouboj(hrac2, 1);

        System.out.println(hrac1);
        System.out.println(hrac2);
    }
}