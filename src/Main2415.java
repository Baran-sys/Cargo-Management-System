import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main2415 {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Gonderi> gonderiList = new ArrayList<>();
        menu(scanner, gonderiList);
    }

    static void menu(Scanner scanner, ArrayList<Gonderi> gonderiList) throws IOException {
        while (true) {
            System.out.println("\n--- KARGO YÖNETİM SİSTEMİ (No: 2415) ---");
            System.out.println("1: ListeOlusturGoster");
            System.out.println("2: UzunTeslimliKargolariSil");
            System.out.println("3: KategoriBazliSay");
            System.out.println("4: AgirlikBazliEnPahaliKargolariAracaYukle");
            System.out.println("5: KargoUcretleriniGuncelle");
            System.out.println("6: Cikis");
            System.out.print("Seciminiz: ");

            int secim = scanner.nextInt();

            switch (secim) {
                case 1:
                    listeOlusturGoster(gonderiList);
                    break;
                case 2:
                    uzunTeslimliKargolariSil(gonderiList);
                    break;
                case 3:
                    kategoriBazliSay(gonderiList);
                    break;
                case 4:
                    agirlikBazliEnPahaliKargolariAracaYukle(gonderiList, scanner);
                    break;
                case 5:
                    kargoUcretleriniGuncelle(gonderiList, scanner);
                    break;
                case 6:
                    System.out.println("Programdan cikiliyor...");
                    return;
                default:
                    System.out.println("Gecersiz secim! Lutfen tekrar deneyin.");
                    break;
            }
        }
    }

    static void listeOlusturGoster(ArrayList<Gonderi> gonderiList) throws IOException {
        gonderiList.clear();

        FileReader fileReader = new FileReader("Kargo.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        bufferedReader.readLine();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] ayir = line.split("\t");

            Gonderi yeniGonderi = new Gonderi();
            yeniGonderi.GonderiAdi = ayir[0].trim();
            yeniGonderi.KategoriIndex = Integer.parseInt(ayir[1].trim());

            String temizlenmisAgirlik = ayir[2].replace("kg", "").replace(',', '.').trim();
            yeniGonderi.Agirlik = Double.parseDouble(temizlenmisAgirlik);

            yeniGonderi.KargoUcreti = Double.parseDouble(ayir[3]);

            yeniGonderi.TeslimSuresi = Integer.parseInt(ayir[4].trim());

            gonderiList.add(yeniGonderi);
        }

        bufferedReader.close();

        System.out.println("Liste basariyla olusturuldu ve gosteriliyor:");
        listeGoster(gonderiList);
    }

    static void listeGoster(ArrayList<Gonderi> list) {
        if (list.isEmpty()){
            System.out.println("Liste bos.");
            return;
        }

        System.out.println("| GonderiAdi             | KategoriIndex | Agirlik | KargoUcreti | TeslimSuresi (gün) |");
        System.out.println("| ---------------------- | ------------- | ------- | ----------- | ------------------ |");

        for (Gonderi gonderi : list) {
            String agirlikStr = String.format("%.2f kg", gonderi.Agirlik);
            System.out.printf("| %-22s | %-13d | %-7s | %-11.2f | %-18d |%n",
                    gonderi.GonderiAdi,
                    gonderi.KategoriIndex,
                    agirlikStr,
                    gonderi.KargoUcreti,
                    gonderi.TeslimSuresi);
        }
    }

    static void uzunTeslimliKargolariSil(ArrayList<Gonderi> gonderiList) {
        int ilkBoyut = gonderiList.size();
        gonderiList.removeIf(gonderi -> gonderi.TeslimSuresi == 5);
        int sonBoyut = gonderiList.size();
        System.out.println((ilkBoyut - sonBoyut) + " adet teslim suresi 5 gun olan kargo silindi.");
        listeGoster(gonderiList);
    }

    static void kategoriBazliSay(ArrayList<Gonderi> gonderiList) {
        if (gonderiList.isEmpty()) {
            System.out.println("Sayim yapilacak gonderi bulunamadi.");
            return;
        }

        int enBuyukKategoriIndex = 0;
        for (Gonderi gonderi : gonderiList) {
            if (gonderi.KategoriIndex > enBuyukKategoriIndex) {
                enBuyukKategoriIndex = gonderi.KategoriIndex;
            }
        }

        int[] kategoriSayilari = new int[enBuyukKategoriIndex + 1];

        for (Gonderi gonderi : gonderiList) {
            kategoriSayilari[gonderi.KategoriIndex]++;
        }

        System.out.println("KategoriIndex Adet");
        for (int i = 1; i < kategoriSayilari.length; i++) {
            if (kategoriSayilari[i] > 0) {
                System.out.println(i + "\t\t" + kategoriSayilari[i]);
            }
        }
    }

    static void agirlikBazliEnPahaliKargolariAracaYukle(ArrayList<Gonderi> gonderiList, Scanner scanner) {
        for (Gonderi gonderi : gonderiList) {
            gonderi.AgirlikBaz = gonderi.KargoUcreti / gonderi.Agirlik;
        }

        gonderiList.sort((o1, o2) -> Double.compare(o2.AgirlikBaz, o1.AgirlikBaz));

        System.out.print("Aracin maksimum agirlik kapasitesini (X) giriniz: ");
        double kapasite = scanner.nextDouble();
        double mevcutAgirlik = 0;

        ArrayList<Gonderi> yuklenenler = new ArrayList<>();
        for (Gonderi gonderi : gonderiList) {
            if (mevcutAgirlik + gonderi.Agirlik <= kapasite) {
                yuklenenler.add(gonderi);
                mevcutAgirlik += gonderi.Agirlik;
            }
        }

        System.out.println("\nAraca yuklenen kargolar:");
        listeGoster(yuklenenler);
    }

    static void kargoUcretleriniGuncelle(ArrayList<Gonderi> gonderiList, Scanner scanner) {
        if (gonderiList.size() < 2) {
            System.out.println("Bu islem icin listede en az 2 gonderi olmalidir.");
            return;
        }

        double toplamUcret = 0;
        for(Gonderi gonderi : gonderiList) {
            toplamUcret += gonderi.KargoUcreti;
        }
        double ortalamaUcret = toplamUcret / gonderiList.size();
        System.out.printf("Ortalama kargo ucreti: %.2f TL%n", ortalamaUcret);

        Gonderi enYakinAlt = null;
        Gonderi enYakinUst = null;
        double minFarkAlt = Double.MAX_VALUE;
        double minFarkUst = Double.MAX_VALUE;

        for(Gonderi gonderi : gonderiList) {
            double fark = Math.abs(gonderi.KargoUcreti - ortalamaUcret);
            if (gonderi.KargoUcreti < ortalamaUcret && fark < minFarkAlt) {
                minFarkAlt = fark;
                enYakinAlt = gonderi;
            } else if (gonderi.KargoUcreti >= ortalamaUcret && fark < minFarkUst) {
                minFarkUst = fark;
                enYakinUst = gonderi;
            }
        }

        System.out.print("Uygulanacak indirim yuzdesini (%X) giriniz: ");
        double indirimYuzdesi = scanner.nextDouble();

        System.out.println("\nIndirim uygulanan kargolar:");
        if (enYakinAlt != null) {
            enYakinAlt.KargoUcreti -= (enYakinAlt.KargoUcreti * indirimYuzdesi / 100);
            System.out.println("Guncellendi (Ortalama Alti):");
            ArrayList<Gonderi> tekilListe1 = new ArrayList<>();
            tekilListe1.add(enYakinAlt);
            listeGoster(tekilListe1);
        }
        if (enYakinUst != null) {
            enYakinUst.KargoUcreti -= (enYakinUst.KargoUcreti * indirimYuzdesi / 100);
            System.out.println("Guncellendi (Ortalama Ustu):");
            ArrayList<Gonderi> tekilListe2 = new ArrayList<>();
            tekilListe2.add(enYakinUst);
            listeGoster(tekilListe2);
        }
    }
}