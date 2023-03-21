package steganography.task

import steganography.encryption.Encryptor
import steganography.steganography.Steganography
import java.io.File
import javax.imageio.ImageIO

class HideTask(private val steganography: Steganography, private val encryptor: Encryptor): Task {
    override fun name(): String {
        return "hide"
    }

    override fun handle() {
        val inputFile: File
        val outputFile: File
        val message: String
        val password: String

        println("Input image file:")
        inputFile = File(readln().ifBlank { "testimage.png" })
        if (!inputFile.canRead()) {
            println("Can't read input file!")

            return
        }
        println("Output image file:")
        outputFile = File(readln().ifBlank { "hide.png" })

        println("Message to hide:")
        message = readln()

        println("Password:")
        password = readln()

        try {
            ImageIO.write(
                steganography.encode(ImageIO.read(inputFile), encryptor.encrypt(message, password)),
                "png",
                outputFile
            )
            println("Message saved in ${outputFile.name} image.")
        } catch (e: Exception) {
            println(e.message)
        }
    }
}