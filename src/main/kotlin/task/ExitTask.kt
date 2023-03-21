package steganography.task

class ExitTask: Task {
    override fun name(): String {
        return "exit"
    }

    override fun handle() {
        println("Bye!")
    }
}