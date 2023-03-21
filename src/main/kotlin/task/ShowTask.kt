package steganography.task

import steganography.encryption.Decryptor
import steganography.steganography.Steganography
import java.io.File
import javax.imageio.ImageIO

class ShowTask(private val steganography: Steganography, private val decryptor: Decryptor): Task {
    override fun name(): String {
        return "show"
    }

    override fun handle() {
        val inputFile: File
        val password: String

        println("Input image file:")
        inputFile = File(readln().ifBlank { "hide.png" })
        if (!inputFile.canRead()) {
            println("Can't read input file!")

            return
        }

        println("Password:")
        password = readln()

        println("Message:")
        println(decryptor.decrypt(steganography.decode(ImageIO.read(inputFile)), password))
    }
}