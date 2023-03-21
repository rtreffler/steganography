package steganography.encryption

interface Encryptor {
    fun encrypt(message: String, password: String): String
}