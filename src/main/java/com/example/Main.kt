package com.example

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        // bazel build //src/main/java/com/example

        System.out.println(LaunchListQuery().name())
    }
}