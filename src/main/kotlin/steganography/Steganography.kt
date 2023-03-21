package steganography.steganography

import java.awt.Color
import java.awt.image.BufferedImage

class Steganography {
    private val stopSequence = byteArrayOf(0, 0, 3)
    private val lastBitMask = (UByte.MAX_VALUE - 1u).toInt()

    fun encode(inputImage: BufferedImage, message: String): BufferedImage {
        val outputImage: BufferedImage
        var origColor: Color
        var newColor: Color
        val bitsToHide = toBinaryString(message.encodeToByteArray() + stopSequence)
        var pos: Int

        if (inputImage.width * inputImage.height < bitsToHide.length) {
            throw RuntimeException("The input image is not large enough to hold this message.")
        }

        outputImage = BufferedImage(inputImage.width, inputImage.height, BufferedImage.TYPE_INT_RGB)
        for (y in 0 until inputImage.height) {
            for (x in 0 until inputImage.width) {
                origColor = Color(inputImage.getRGB(x, y))
                pos = y * inputImage.width + x
                newColor = if (pos < bitsToHide.length) {
                    val bit = bitsToHide[pos].digitToInt()
                    Color(
                        origColor.red,
                        origColor.green,
                        origColor.blue.and(lastBitMask) or bit
                    )
                } else {
                    origColor
                }

                outputImage.setRGB(x, y, newColor.rgb)
            }
        }

        return outputImage
    }

    fun decode(image: BufferedImage): String {
        val stopSequence = toBinaryString(stopSequence).toList()
        var color: Color
        val message = emptyList<Char>().toMutableList()

        loop@ for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                color = Color(image.getRGB(x, y))
                message.add((color.blue and 0b1).digitToChar())

                if (message.size >= stopSequence.size && message.takeLast(stopSequence.size) == stopSequence) {
                    break@loop
                }
            }
        }

        return binaryListToString(message.dropLast(stopSequence.size))
    }

    private fun binaryListToString(encoded: List<Char>): String {
        return encoded
            .chunked(Byte.SIZE_BITS)
            .map {
                it.joinToString("").toInt(2).toChar()
            }
            .joinToString("")
    }

    private fun toBinaryString(byteArray: ByteArray): String {
        return byteArray.joinToString("") { it.toString(2).padStart(Byte.SIZE_BITS, '0') }
    }
}