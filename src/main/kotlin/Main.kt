package steganography

import steganography.encryption.Xor
import steganography.steganography.Steganography
import steganography.task.*

fun main() {
    val steganography = Steganography()
    val xor = Xor()
    val taskCollection = TaskCollection(listOf(
        HideTask(steganography, xor),
        ShowTask(steganography, xor),
        ExitTask()
    ))
    var task: Task

    do {
        println("Task (${taskCollection.getAvailableTasksNames().joinToString()}):")
        task = taskCollection.get(readln())
        task.handle()
    } while (task !is ExitTask)
}
