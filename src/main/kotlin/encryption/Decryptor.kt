package steganography.encryption

interface Decryptor {
    fun decrypt(message: String, password: String): String
}