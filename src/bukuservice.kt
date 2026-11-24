import Buku
import java.io.File

class BukuService {
    private val berkasData = File("data_buku.txt")
    private val daftarBuku = Penyimpanan.muat(berkasData)
    private var urutId = if (daftarBuku.isNotEmpty()) {
        val nomorTerakhir = daftarBuku.last().id.drop(1).toIntOrNull() ?: 0
        nomorTerakhir + 1
    } else 1

    private fun simpanData() {
        Penyimpanan.simpan(berkasData, daftarBuku)
    }

    fun tambahBuku(judul: String, penulis: String) {
        val bukuBaru = Buku("B$urutId", judul, penulis)
        daftarBuku.add(bukuBaru)
        urutId++
        simpanData()
        println("Buku berhasil ditambahkan!")
    }

    fun tampilkanSemuaBuku() {
        if (daftarBuku.isEmpty()) {
            println("Belum ada data buku.")
            return
        }
        println("\n=== Daftar Buku ===")
        for ((i, buku) in daftarBuku.withIndex()) {
            val status = if (buku.tersedia) "Tersedia" else "Dipinjam"
            println("${i + 1}. ${buku.id} — ${buku.judul} — ${buku.penulis} | $status")
        }
    }

    fun cariBuku(kunci: String) {
        val hasil = daftarBuku.filter {
            it.judul.contains(kunci, ignoreCase = true) ||
                    it.penulis.contains(kunci, ignoreCase = true)
        }
        if (hasil.isEmpty()) {
            println("Buku dengan kata kunci '$kunci' tidak ditemukan.")
            return
        }
        println("\n=== Hasil Pencarian ===")
        for ((i, buku) in hasil.withIndex()) {
            val status = if (buku.tersedia) "Tersedia" else "Dipinjam"
            println("${i + 1}. ${buku.id} — ${buku.judul} — ${buku.penulis} | $status")
        }
    }

    fun ubahStatusBuku(id: String) {
        val buku = daftarBuku.find { it.id == id }
        if (buku == null) {
            println("Buku dengan ID $id tidak ditemukan.")
            return
        }
        buku.tersedia = !buku.tersedia
        val status = if (buku.tersedia) "tersedia" else "dipinjam"
        simpanData()
        println("Status buku diperbarui: $status")
    }

    fun jumlahBuku(): Int {
        return daftarBuku.size
    }

    fun hapusBuku(id: String): Boolean {
        val buku = daftarBuku.find { it.id == id }
        return if (buku != null) {
            daftarBuku.remove(buku)
            simpanData()
            println("✅ Buku berhasil dihapus!")
            true
        } else {
            println("❌ Buku dengan ID $id tidak ditemukan.")
            false
        }
    }

    fun urutBukuBerdasarkanJudul() {
        if (daftarBuku.isEmpty()) {
            println("📂 Belum ada data buku.")
            return
        }
        val urut = daftarBuku.sortedBy { it.judul.lowercase() }
        println("\n=== Daftar Buku (Diurutkan A–Z) ===")
        urut.forEachIndexed { i, buku ->
            val status = if (buku.tersedia) "✅ Tersedia" else "📖 Dipinjam"
            println("${i + 1}. ${buku.id} — ${buku.judul} — ${buku.penulis} | $status")
        }
    }

    fun filterBukuTersedia() {
        val tersedia = daftarBuku.filter { it.tersedia }
        if (tersedia.isEmpty()) {
            println("📖 Tidak ada buku yang sedang tersedia.")
            return
        }
        println("\n=== Buku yang Tersedia ===")
        tersedia.forEachIndexed { i, buku ->
            println("${i + 1}. ${buku.id} — ${buku.judul} — ${buku.penulis}")
        }
    }

    fun tampilkanStatistik() {
        val total = daftarBuku.size
        val jumlahTersedia = daftarBuku.count { it.tersedia }
        val jumlahDipinjam = total - jumlahTersedia
        println("\n===== STATISTIK PERPUSTAKAAN =====")
        println("📚 Total Buku      : $total")
        println("✅ Tersedia        : $jumlahTersedia")
        println("📖 Sedang Dipinjam : $jumlahDipinjam")
        println("====================================")
    }
}