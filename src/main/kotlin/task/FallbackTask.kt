package steganography.task

class FallbackTask(private val taskName: String): Task {
    override fun name(): String {
        return "fallback"
    }

    override fun handle() {
        println("Wrong task: $taskName")
    }
}