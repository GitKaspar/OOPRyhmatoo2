import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Koht {
        private final String nimi;
        private boolean kasKülastatud;
        private boolean kasÕnnestus;
        private List<String> õnnestumine = new ArrayList<>();
        private List<String> ebaõnnestumine = new ArrayList<>();
        private final String tulemusteFail;


    public Koht(String nimi, boolean kasKülastatud, String tulemusteFail) {
        this.nimi = nimi;
        this.kasKülastatud = kasKülastatud;
        this.tulemusteFail = tulemusteFail;
        this.kasÕnnestus = false;
    }

    @Override
        public String toString() {
            if (kasKülastatud)
                return nimi + ". Oled siin käinud.";
            else
                return nimi + ". Sa ei ole siin käinud.";
    }

    public boolean isKasKülastatud() {
        return kasKülastatud;
    }

    public void setKasKülastatud(boolean kasKülastatud) {
        this.kasKülastatud = kasKülastatud;
    }

    public boolean isKasÕnnestus() {
        return kasÕnnestus;
    }

    public void setKasÕnnestus(boolean kasÕnnestus) {
        this.kasÕnnestus = kasÕnnestus;
    }

    /**
     * See ja järgmine meetod on kasutusel mängu lõpus.
     * Esimene loeb failist koha õnnestumisel või ebaõnnestumisel kuvatava sisu
     * teine kuvab kasutajale sõnumi vastavalt sellele, kas ülesanne selles kohas õnnestus
     */

    public void loeTulemused() throws FileNotFoundException {
        Scanner loeFailist = new Scanner(new File(this.tulemusteFail));
        while (loeFailist.hasNextLine()){
            String rida = loeFailist.nextLine();
            if (rida.startsWith("T."))
                    this.õnnestumine.add(rida.substring(2));
            else
                this.ebaõnnestumine.add(rida);
        }
    }

    public void väljastaTulemus() throws Exception {
        if (this.kasÕnnestus)
            for (String rida:
                 this.õnnestumine) {
                Abi.väljastaPausiga(rida);
            }
        else{
            for (String rida:
                 this.ebaõnnestumine) {
                Abi.väljastaPausiga(rida);
            }
        }

    }
}