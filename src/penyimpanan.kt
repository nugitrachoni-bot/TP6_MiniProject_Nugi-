import java.io.File

object Penyimpanan {
    fun muat(file: File): MutableList<Buku> {
        val hasil = mutableListOf<Buku>()
        if (!file.exists()) return hasil

        for (baris in file.readLines()) {
            val bagian = baris.split("|")
            if (bagian.size != 4) continue

            val id = bagian[0].trim()
            val judul = bagian[1].trim()
            val penulis = bagian[2].trim()
            val tersedia = bagian[3].trim().toBooleanStrictOrNull() ?: true

            if (id.isNotBlank() && judul.isNotBlank() && penulis.isNotBlank()) {
                hasil.add(Buku(id, judul, penulis, tersedia))
            }
        }
        return hasil
    }

    fun simpan(file: File, data: List<Buku>) {
        val teks = data.joinToString("\n") {
            "${it.id}|${it.judul}|${it.penulis}|${it.tersedia}"
        }
        file.writeText(teks)
    }
}
