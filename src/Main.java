import java.util.*;
import java.util.ArrayList;
import java.util.List;


class Menu {
    String nama;
    double harga;
    String kategori;

    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
}

class Pesanan{
    Menu menu;
    int jumlah;

    public Pesanan(Menu menu, int jumlah) {
        this.menu = menu;
        this.jumlah = jumlah;
    }

    public double hitungTotalHarga(){
        return menu.harga * jumlah;
    }
}

class Restoran{
    private Menu[] menu;
    private List<Pesanan> pesananList;

    public Restoran(){
        this.menu = new Menu[]{
            new Menu("Nasi Padang", 25000, "makanan"),
            new Menu("Ayam Penyet", 30000, "makanan"),
            new Menu("Es Teh", 5000, "minuman"),
            new Menu("Kopi", 10000, "minuman"),
            new Menu("Sate Ayam", 20000, "makanan"),
            new Menu("Es Jeruk", 15000, "minuman"),
        };
        this.pesananList = new ArrayList<>();
    }

    public void tampilkanMenu(){
        System.out.println("Menu Restoran");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i+1) + ". " + menu[i].nama + " - Rp. " + menu[i].harga + " (" + menu[i].kategori + ")");
        }
    }

    public void tambahPesanan(String namaMenu, int jumlah){
        for (Menu m : menu) {
            if (m.nama.equalsIgnoreCase(namaMenu)) {
                pesananList.add(new Pesanan(m, jumlah));
                return;
            }
        }
        System.out.println("Menu tidak ditemukan");
    }

    public double hitungTotalBiaya(){
        double total = 0;
        for (Pesanan p : pesananList) {
            total += p.hitungTotalHarga();
        }
        return total;
    }

    public void cetakStruk(){
        double totalBiaya = hitungTotalBiaya();
        double pajak = totalBiaya * 0.1;
        double biayaPelayanan = 20000;

        double totalAkhir = totalBiaya + pajak + biayaPelayanan;

        double diskon = 0;
        if (totalBiaya > 100000){
            diskon = totalAkhir * 0.1;
        }

        boolean adaMinumanGratis = totalBiaya > 50000;

        System.out.println("\n--- Struk Pesanan ---");
        for (Pesanan p : pesananList) {
            System.out.println(p.menu.nama + " x " + p.jumlah + " = Rp. " + p.hitungTotalHarga());
        }

        System.out.println("Total Biaya: Rp. " + totalBiaya);
        System.out.println("Pajak (10%): Rp. " + pajak);
        System.out.println("Biaya Pelayanan: Rp. " + biayaPelayanan);

        if (diskon > 0){
            System.out.println("Diskon: Rp. " + diskon);
            totalAkhir -= diskon;
        }

        if (adaMinumanGratis){
            System.out.println("Penawaran: Beli 1 Gratis 1 untuk minuman");
            totalAkhir -= diskon;
        }

        System.out.println("Total Akhir: Rp. " + totalAkhir);
        System.out.println("-----------------------");
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restoran restoran = new Restoran();

        restoran.tampilkanMenu();

        System.out.println("Masukkan pesanan anda (maksimal 4 menu), format 'Nama Menu = Jumlah'. Ketik 'selesai' untuk mengakhiri");

        for (int i = 0; i < 4; i++){
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("selesai")){
                break;
            }
            String[] parts = input.split("=");
            if (parts.length == 2){
                String namaMenu = parts[0].trim();
                int jumlah = Integer.parseInt(parts[1].trim());
                restoran.tambahPesanan(namaMenu, jumlah);
            }else{
                System.out.println("Format input salah. Silakan coba lagi.");
            }
        }

        restoran.cetakStruk();
        scanner.close();
    }

}
