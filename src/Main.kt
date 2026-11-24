fun main() {
    val namaProyek = "TP6 Mini Project — Daftar Buku"
    val layanan = BukuService()


    println("================================")
    println(namaProyek)
    println("================================")
    println("Selamat datang di Aplikasi Daftar Buku!")
    println("Versi 1.0 — Siap digunakan!")
    println()

    while (true) {
        println("===== MENU UTAMA =====")
        println("1. Lihat Daftar Buku")
        println("2. Tambah Buku Baru")
        println("3. Cari Buku")
        println("4. Ubah Status Buku")
        println("5. Jumlah Total Buku")
        println("6. Keluar")
        print("Pilih menu: ")

        when (readLine()) {
            "1" -> layanan.tampilkanSemuaBuku()

            "2" -> {
                print("Masukkan judul buku: ")
                val judul = readLine()?.trim() ?: ""
                if (judul.isEmpty()) {
                    println("❌ Judul tidak boleh kosong!")
                    continue
                }
                print("Masukkan nama penulis: ")
                val penulis = readLine()?.trim() ?: ""
                if (penulis.isEmpty()) {
                    println("❌ Nama penulis tidak boleh kosong!")
                    continue
                }
                layanan.tambahBuku(judul, penulis)
            }

            "3" -> {
                print("Masukkan kata kunci pencarian: ")
                val kunci = readLine()?.trim() ?: ""
                if (kunci.isEmpty()) {
                    println("❌ Kata kunci tidak boleh kosong!")
                    continue
                }
                layanan.cariBuku(kunci)
            }

            "4" -> {
                print("Masukkan ID buku: ")
                val id = readLine()?.trim() ?: ""
                layanan.ubahStatusBuku(id)
            }

            "5" -> println("📊 Jumlah total buku: ${layanan.jumlahBuku()} buku")

            "6" -> {
                println("👋 Terima kasih telah menggunakan aplikasi!")
                return
            }

            "7" -> {
                print("Masukkan ID buku yang akan dihapus: ")
                val id = readLine()?.trim() ?: ""
                layanan.hapusBuku(id)
            }

            "8" -> layanan.urutBukuBerdasarkanJudul()

            "9" -> layanan.filterBukuTersedia()

            "10" -> layanan.tampilkanStatistik()

            else -> println("❌ Pilihan tidak valid, silakan pilih 1–10")
        }
    }
}
