rootProject.name = "neetcode-playground"

// Every problems/<topic>/<problem> folder is a subproject named after the
// problem folder, e.g. :contains-duplicate. Problem names must be unique.
file("problems").listFiles { f -> f.isDirectory }.orEmpty().sorted().forEach { topic ->
    topic.listFiles { f -> f.isDirectory && !f.name.startsWith(".") }.orEmpty().sorted().forEach { problem ->
        include(problem.name)
        project(":${problem.name}").projectDir = problem
    }
}
