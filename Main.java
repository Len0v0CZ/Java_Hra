class Main {
    public static void main(String[] args) {
        Array<Predmet> predmety = new Array<>(5);


        predmety.pridejPolozku(new Predmet(DruhPredmetu.ARMOR, "helma_tulaka"));
        predmety.pridejPolozku(new Predmet(DruhPredmetu.ARMOR, "breastplate"));
        predmety.pridejPolozku(new Predmet(DruhPredmetu.ARMOR, "gatky"));
        predmety.pridejPolozku(new Predmet(DruhPredmetu.ARMOR, "botydorobory"));

        System.out.println(predmety.odeberPolozku(2));
    

        predmety.pridejPolozku(new Predmet(DruhPredmetu.ARMOR, "gatky"));

        System.out.println(predmety);
        
        Hrac damir = Hrac.vytvorHrace();
        System.out.println(damir.otevritInv());

        Hrac greg = Hrac.vytvorHrace();
        System.out.println(greg.otevritInv());

        damir.nakup();
        greg.nakup();

        damir.vyzvatDynamicky(greg);




    }



    @Override
    public String toString() {
        return "Main []";
    }


    //hrac ma zlataky, itemy, zivoty
    //item stoji zlataky, max  itemu
    //itemy potion, mec, shield
    //hod kockou
}