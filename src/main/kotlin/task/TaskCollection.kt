package steganography.task

class TaskCollection(listOfTasks: List<Task>) {
    private val tasks = mutableMapOf<String, Task>()

    init {
        listOfTasks.forEach { add(it) }
    }

    fun add(task: Task) {
        tasks[task.name()] = task
    }

    fun getAvailableTasksNames(): List<String> = tasks.map { it.key }

    fun get(taskName: String): Task = tasks[taskName] ?: FallbackTask(taskName)
}