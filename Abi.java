import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Abi {

    public static int dialoogiHaru(int valikuid, String sisendFail) throws Exception {
        Scanner loeValikuFail = new Scanner(new File(sisendFail));
        List<String> tutvustus = new ArrayList<>();
        List<String> lõpp = new ArrayList<>();
        List<String> valik1vastus = new ArrayList<>();
        List<String> valik2vastus = new ArrayList<>();
        List<String> valik3vastus = new ArrayList<>();
        while (loeValikuFail.hasNextLine()) {
            String rida = loeValikuFail.nextLine();
            if (!rida.isEmpty()) {
                String algus = rida.substring(0, 3);
                switch (algus) {
                    case "v1." -> valik1vastus.add(rida.substring(3));
                    case "v2." -> valik2vastus.add(rida.substring(3));
                    case "v3." -> valik3vastus.add(rida.substring(3));
                    case "lõ." -> lõpp.add(rida.substring(3));
                    default -> tutvustus.add(rida);
                }
            } else
                tutvustus.add("");
        }

        for (String rida :
                tutvustus) {
            väljastaPausiga(rida);
        }

        System.out.println("\nSinu valik: ");
        Scanner loeValik = new Scanner(System.in);
        int valik = loeValik.nextInt();

        if (valikuid == 2) {
            switch (valik) {
                case 1 -> {
                    for (String rida :
                            valik1vastus) {
                        väljastaPausiga(rida);
                    }
                }
                case 2 -> {
                    for (String rida :
                            valik2vastus) {
                        väljastaPausiga(rida);
                    }
                }
                default -> {
                    System.out.println("Valikuteks sobivad vaid 1 ja 2.");
                    dialoogiHaru(valikuid, sisendFail);
                }
            }

        }

        if (valikuid == 3) {
            switch (valik) {
                case 1 -> {
                    for (String rida :
                            valik1vastus) {
                        väljastaPausiga(rida);
                    }
                }
                case 2 -> {
                    for (String rida :
                            valik2vastus) {
                        väljastaPausiga(rida);
                    }
                }
                case 3 -> {
                    for (String rida :
                            valik3vastus) {
                        väljastaPausiga(rida);
                    }
                }
                default -> {
                    System.out.println("Valikuteks sobivad vaid 1, 2 ja 3.");
                    dialoogiHaru(valikuid, sisendFail);
                }
            }
        }

        for (String rida :
                lõpp) {
            väljastaPausiga(rida);
        }

        return valik;
    }

    public static void väljastaPausiga(String lause) throws Exception {
        if (lause.isEmpty()) {
            Scanner jätka = new Scanner(System.in);
            System.out.println("\n...");
            jätka.nextLine();
        } else {
            int reapikkus = lause.length();
            System.out.println(lause);
            Thread.sleep(reapikkus); // reapikkus * 75 hetkel vist okei.
        }
    }

    public static void loeFailEtte(String failiNimi) throws Exception {
        Scanner lugeja = new Scanner(new File(failiNimi), StandardCharsets.UTF_8);
        while (lugeja.hasNextLine()) {
            String rida = lugeja.nextLine();
            väljastaPausiga(rida);
        }
    }

    public static int juhuslikArv(int valikuid) {
        int min = 1;
        return (int) (min + Math.random() * valikuid);
    }

    public static void kaheneValik(String failiNimi) throws Exception {
        Scanner sisendKlaverilt = new Scanner(System.in);
        int valik = sisendKlaverilt.nextInt();

        if (valik == 1) {
            loeFailEtte(failiNimi);
        } else if (valik == 2) { // Kui siia jääks vaid if, käivituks sisendi 1 puhul ka else. Miks?
            System.out.println("\nHead aega!");
            System.exit(0);
        } else {
            System.out.println("\nValid valikud 1 ja 2 töötavad.\n");
            kaheneValik(failiNimi);
        }
    }

    // Küsib miskipärast kaks korda. Kas rekursiooni tõttu?

    public static int valiKoht(List<Koht> kohaList) {

        System.out.print("\nSinu valik: \n");
        Scanner valikuNr = new Scanner(System.in);
        int sisestatudNumber = valikuNr.nextInt();

        switch (sisestatudNumber) {
            case 1 -> System.out.println(kohaList.get(0));
            case 2 -> System.out.println(kohaList.get(1));
            case 3 -> System.out.println(kohaList.get(2));
            case 4 -> System.out.println(kohaList.get(3));
            case 5 -> System.out.println(kohaList.get(4));
            default -> {
                System.out.println("Vahemikus 1-5, palun.");
                valiKoht(kohaList);
            }
        }
        return sisestatudNumber;
    }

    // Meetod on jäänuk ühest vanemast lähenemisest, kus ettevõtmisi eraldi meetoditena esitada proovis.

    public static boolean kosk(int õigeNr, Koht kosk) {
        int võimalusi = 2;

        while (võimalusi > 0 && !kosk.isKasÕnnestus()) {
            System.out.println("Sinu valik: ");
            Scanner sisendKlaverilt = new Scanner(System.in);
            int valik = sisendKlaverilt.nextInt();

            String õige1 = "Kae! Oligi seal! Haarad temast kinni";
            String õige2 = "Kae! Oligi seal! Haarad temast kinni ja tuled tiigist välja: räpane ja väsinud, aga võidukas.";
            String õige3 = "Kae! Oligi seal! Haarad temast kinni ja tuled tagasi kaldale.";
            String vale1 = "Vaatad sinna, vaatad tänna. Katsud vett, aga ei näe elukast märkigi.";
            String vale2 = "Ei leidnud. Nüüd oled räpane ja väsinud.";
            String vale3 = "Memm rääkis kunagi, et kose all on saladused. Seekord mitte.";
            String vihje1 = "Vanatoi ütles kunagi, et neile meeldib käia veepinnal sääsevastseid degusteerimas.";
            String vihje2 = "Valgust pidid teised kartma. Kus on siin kõige pimedam?";
            String vihje3 = "Kas Memm mitte ei öelnud kunagi, et meeldib loomale liikuv vesi?";

            String[] valed = {vale1, vale2, vale3};
            String[] õiged = {õige1, õige2, õige3};

            // Kuidas vale ja õige õige numbriga siduda?

            if (valik <= 3 && valik >= 1) {
                if (valik == õigeNr) {
                    System.out.println(õiged[valik - 1]);
                    kosk.setKasÕnnestus(true);
                } else if (õigeNr == 1) {
                    võimalusi--;
                    System.out.println(valed[valik - 1]);
                    if (võimalusi == 1)
                        System.out.println(vihje1);
                } else if (õigeNr == 2) {
                    võimalusi--;
                    System.out.println(valed[valik - 1]);
                    if (võimalusi == 1)
                        System.out.println(vihje2);
                } else if (õigeNr == 3) {
                    võimalusi--;
                    System.out.println(valed[valik - 1]);
                    if (võimalusi == 1)
                        System.out.println(vihje3);
                }
            } else {
                System.out.println("Valikuvariandid on 1, 2 ja 3.");
                kosk(õigeNr, kosk);
            }
        }
        return kosk.isKasÕnnestus();
    }

    // Abimeetod kose koodijupile

    public static void koskLõpeta() throws Exception {
        System.out.println("Sinu valik: ");
        Scanner sisendKlaverilt = new Scanner(System.in);
        int valik = sisendKlaverilt.nextInt();

        String valik1 = """
                Kiiresti kaob ta paari sabahoobiga vee alla.
                Miski ütleb, et ta oleks sinu abita sama teed läinud.
                Tagasi külla.
                """;
        String valik2 = """
                Kostab vali karjatus. Verd on igal pool.
                One hea tunne?
                Tagasi külla.
                """;
        String valik3 = """
                Vingerjas teine! Libises käest ja lipsas kohe vette tagasi.
                Siin pole enam midagi teha.
                Tagasi külla.\s
                """;

        switch (valik) {
            case 1 -> väljastaPausiga(valik1);
            case 2 -> väljastaPausiga(valik2);
            case 3 -> väljastaPausiga(valik3);
            default -> {
                System.out.println("Valikuvariandid on 1, 2 ja 3.");
                koskLõpeta();
            }

        }
    }

    // Sellel ülesandel ka oma programm sarnaselt kosele, kuna juhuslikud elemendid sees.

    public static void altar(int juhuslikArv, Koht altar) throws Exception {
        loeFailEtte("ylesanne2/altarSj.txt");
        if (juhuslikArv == 1) {
            loeFailEtte("ylesanne2/altarVeab.txt");
            int altarVastus1 = dialoogiHaru(2, "ylesanne2/altar1.txt");
            if (altarVastus1 == 1)
                altar.setKasÕnnestus(true);
            altar.setKasKülastatud(true);
        }
        if (juhuslikArv == 2) {
            loeFailEtte("ylesanne2/altarEiVea.txt");
            int altarVastus2 = dialoogiHaru(2, "ylesanne2/altar2.txt");
            if (altarVastus2 == 2)
                altar.setKasÕnnestus(true);
            altar.setKasKülastatud(true);
        }
    }

    // Taas lisameetod, mis arvestab ülesande omapärase juhuslikkusega.

    public static void mõistatused(Koht adlin) throws Exception {
        int võimalusi = 2;
        List<Integer> mõistatused = new ArrayList<>();
        mõistatused.add(1);
        mõistatused.add(2);
        mõistatused.add(3);
        while (võimalusi > 0 && !adlin.isKasÕnnestus()) {
            int valik = juhuslikArv(mõistatused.size());
            int juhuslikMõistatus = mõistatused.get(valik - 1);
            switch (juhuslikMõistatus) {
                case 1 -> {
                    int valik1 = dialoogiHaru(3, "ylesanne4/moistatus1.txt");
                    if (valik1 == 1)
                        adlin.setKasÕnnestus(true);
                    võimalusi--;
                }
                case 2 -> {
                    int valik2 = dialoogiHaru(3, "ylesanne4/moistatus2.txt");
                    if (valik2 == 2)
                        adlin.setKasÕnnestus(true);
                    võimalusi--;
                }
                case 3 -> {
                    int valik3 = dialoogiHaru(3, "ylesanne4/moistatus3.txt");
                    if (valik3 == 3)
                        adlin.setKasÕnnestus(true);
                    võimalusi--;
                }
                default -> {
                }
            }
            mõistatused.remove(juhuslikMõistatus - 1);
        }
        adlin.setKasKülastatud(true);
    }
}