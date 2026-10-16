class Main {
    public static void main(String[] args) {
        Hrac hrac1 = new Hrac();
        Hrac hrac2 = new Hrac();

        hrac1.koupitPredmet(DruhPredmetu.MEC, "Ocelovy mec");     // index 0
        hrac1.koupitPredmet(DruhPredmetu.ARMOR, "Zelezna zbroj"); // index 1
        hrac1.koupitPredmet(DruhPredmetu.BOW, "Dlouhy luk");      // index 2

        hrac1.vyzvatNaSouboj(hrac2);

        System.out.println(hrac1);
        System.out.println(hrac2);
    }
}