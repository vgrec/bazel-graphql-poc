package com.example;

public class Main {
    public static void main(String[] args) {
        // bazel build //src/main/java/com/example

        System.out.println(new LaunchListQuery().name());
    }
}
