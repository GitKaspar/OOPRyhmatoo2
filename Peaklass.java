import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Peaklass {
    public static void main(String[] args) throws Exception {
        Abi.loeFailEtte("algus/algus.txt");

        System.out.println("\n1. Alusta");
        System.out.println("2. Lõpeta\n");

        Abi.kaheneValik("algus/samaaniTutvustus.txt");

        System.out.println("Võid ülesandega alustada sealt, kust soovid.\n");

        // Kõigepealt loeme failist kohtade jaoks tarvilikud andmed, mis loovad ka klassi Koht isendid
        // Klass koht kannab endas erinevat kohtadega seonduvat infot

        Scanner kohad = new Scanner(new File("algus/kohad.txt"), StandardCharsets.UTF_8);
        List<Koht> kohaList = new ArrayList<>();
        while (kohad.hasNextLine()) {
            String rida = kohad.nextLine();
            String[] tükeldatudRida = rida.split(";");
            Koht uusKoht = new Koht(tükeldatudRida[0], Boolean.parseBoolean(tükeldatudRida[1]), tükeldatudRida[2]);
            kohaList.add(uusKoht);
        }

        /**
         * Peamine tsükkel. Laseme mängijal valida koha ja käivitame vastava alamülesande.
         * Vastavalt sellele, kas alamülesandes on juhuslikkust või mitte, käivituvad ülesanded erinevalt:
         * a) juhuslikkusetta ülesanded kasutavad meetodit DialoogiHaru (Abi klassist)
         * b) juhuslikkusega ülesanded on Abi klassis eraldi meetoditena lahti kirjutatud
         * Kui 5 ülesannet on läbitud (i kasvab viieni), käivitub lõpp.
         */

        for (int i = 0; i < 5; ) {
            int jrkLuger = 1;
            for (Koht koht :
                    kohaList) {
                System.out.println(jrkLuger++ + ") " + koht);
            }
            
            /*
             See koht vist probleemne: kui tekib rekursioon, tagastatakse mitu väärtust. Kõik väärtused söödetakse tsüklile.
             St, et nt 8 sisestamisel järgneb switch-case(8), mis aga default haruna lõpetab tsükli ja alustab uuesti.
             Kohta küsitakse kaks korda.
            */
            int valik = Abi.valiKoht(kohaList);
            switch (valik) {
                case 1: // Kosk
                    if (kohaList.get(0).isKasKülastatud()) {
                        System.out.println("Siia ei saa enam naasta.");
                        break;
                    } else {
                        Abi.loeFailEtte("ylesanne1/koskSj.txt");
                        boolean edu = Abi.kosk(Abi.juhuslikArv(3), kohaList.get(0));
                        if (edu) {
                            kohaList.get(0).setKasÕnnestus(true);
                            kohaList.get(0).setKasKülastatud(true);
                            i++;
                            Abi.loeFailEtte("ylesanne1/koskEdu.txt");
                            Abi.koskLõpeta();
                            break;
                        } else {
                            kohaList.get(0).setKasKülastatud(true);
                            i++;
                            Abi.loeFailEtte("ylesanne1/koskEbaõnn.txt");
                            break;
                        }
                    }
                case 2: // Altar
                    if (kohaList.get(1).isKasKülastatud()) {
                        System.out.println("Siia ei saa enam naasta.");
                        break;
                    } else {
                        Abi.altar(Abi.juhuslikArv(2), kohaList.get(1));
                        i++;
                        break;
                    }
                case 3:
                    if (kohaList.get(2).isKasKülastatud()) {
                        System.out.println("Siia ei saa enam naasta.");
                        break;
                    } else {
                        int jürkaValik1 = Abi.dialoogiHaru(3, "ylesanne3/jurka.txt");
                        switch (jürkaValik1) {
                            case 1:
                                Abi.dialoogiHaru(2, "ylesanne3/jurka1.txt");
                                kohaList.get(2).setKasKülastatud(true);
                                i++;
                                break;
                            case 2:
                                Abi.dialoogiHaru(2, "ylesanne3/jurka2.txt");
                                kohaList.get(2).setKasKülastatud(true);
                                i++;
                                break;
                            case 3:
                                int jürkaValik2 = Abi.dialoogiHaru(3, "ylesanne3/jurka3.txt");
                                switch (jürkaValik2) {
                                    case 1, 3 -> {
                                        kohaList.get(2).setKasKülastatud(true);
                                        i++;
                                    }
                                    case 2 -> {
                                        kohaList.get(2).setKasKülastatud(true);
                                        kohaList.get(2).setKasÕnnestus(true);
                                        i++;
                                    }
                                }
                            default:
                                break;
                        }
                        break;
                    }// Jürka. PROBLEEM! Läheb edasi neljanda ülesande peale (Fallthrough?)
                case 4:
                    if (kohaList.get(3).isKasKülastatud()) {
                        System.out.println("Siia ei saa enam naasta.");
                        break;
                    } else {
                        int adlinValik1 = Abi.dialoogiHaru(3, "ylesanne4/adlin.txt");
                        switch (adlinValik1) {
                            case 1 -> {
                                Abi.dialoogiHaru(3, "ylesanne4/adlin1.txt");
                                kohaList.get(3).setKasKülastatud(true);
                                i++;
                            }
                            case 2 -> {
                                Abi.dialoogiHaru(2, "ylesanne4/adlin2.txt");
                                kohaList.get(3).setKasKülastatud(true);
                                i++;
                            }
                            case 3 -> {
                                Abi.mõistatused(kohaList.get(3));
                                i++;
                            }
                            default -> System.out.println("Kui oled siin, on kuskil viga.");
                        }
                        break;
                    }
                    // Viirukimeistri hütt
                case 5:
                    if (kohaList.get(4).isKasKülastatud()) {
                        System.out.println("Siia ei saa enam naasta.");
                        break;
                    } else {
                        int šamaaniValik1 = Abi.dialoogiHaru(3, "ylesanne5/aklupatiSamaan.txt");
                        switch (šamaaniValik1) {
                            case 1 -> {
                                int šamaaniValik2 = Abi.dialoogiHaru(2, "ylesanne5/aklupatiSamaan1.txt");
                                if (šamaaniValik2 == 1)
                                    kohaList.get(4).setKasÕnnestus(true);
                                kohaList.get(4).setKasKülastatud(true);
                                    i++;
                            }
                            case 2, 3 -> {
                                i++;
                                kohaList.get(4).setKasKülastatud(true);
                            }
                        }
                    }// Aplukati šaamani koguduseplats
                default:
                    break;
            }
        }


        /**
         * Lõppvaatus. Esitatakse sissejuhatus ja loetakse tulemused vastavalt infole, mis klassi Koht isendites
         * isenditesse on märgitud, kas ülesanne õnnestus võit mitte ja vastavalt sellele tulemus
         * Ükshaaval loetakse mängijale riituse sammud vastavalt tulemustele ette.
         * Kui mängija oli edukas vähemalt 3 ülesandes, saab ta hea lõpu, kui õnnestumisi oli vähem kui kolm, halva
         */

        int õnnestumisi = 0;

        Abi.loeFailEtte("lopp/riitusSj.txt");
        System.out.println();
        for (Koht koht :
                kohaList) {
            koht.loeTulemused();
            koht.väljastaTulemus();
            System.out.println();
            if (koht.isKasÕnnestus())
                õnnestumisi++;
        }
        Abi.loeFailEtte("lopp/ootus.txt");

        if (õnnestumisi > 2)
            Abi.loeFailEtte("lopp/oledEdukas.txt");
        else
            Abi.loeFailEtte("lopp/kukudLabi.txt");
    }
}