package steganography.encryption

import kotlin.experimental.xor

class Xor: Encryptor, Decryptor {
    override fun encrypt(message: String, password: String): String {
        val byteMessage = message.encodeToByteArray()
        val bytePassword = if (message.length > password.length) {
            password.repeat(1 + message.length / password.length).substring(0, message.length).encodeToByteArray()
        } else {
            password.encodeToByteArray()
        }

        return byteMessage
            .mapIndexed { index, byte -> byte.xor(bytePassword[index]).toInt().toChar() }
            .joinToString("")
    }

    override fun decrypt(message: String, password: String): String {
        return encrypt(message, password)
    }
}