import java.util.Random;
import java.util.Scanner;

public class Hrac {
    private String name;
    private int zivoty;
    private int zlataky;
    private Array<Predmet> inventar;
    Scanner sc = new Scanner(System.in);

    public Hrac(String name) {
        this.name = name;
        this.zivoty = 20;
        this.zlataky = 10;
        this.inventar = new Array<>(5);
    }

    public boolean pridejInventar(DruhPredmetu druh, String nazev) {
        if (this.zlataky < 2) {
            return false;
        }
        this.zlataky -= 2;
        return this.inventar.pridejPolozku(new Predmet(druh, nazev));
    }

    private Array otevritInv() {
        return this.inventar;
    }

    public void vyzvatNaSouboj(Hrac souper) {
        this.otevritInv();
        System.out.println("Chces neco pouzit?");
        int odpoved = sc.nextInt();

        int ja = this.hodKockou();
        int on = this.hodKockou();

        
    }

    private int hodKockou() { 
        return (int)Math.random()*6 + 1;
    }



    @Override
    public String toString() {
        return "Hrac [name=" + name + ", zivoty=" + zivoty + ", zlataky=" + zlataky + ", inventar=" + inventar + "]";
    }

    
    
}
