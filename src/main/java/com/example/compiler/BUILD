load("@rules_java//java:defs.bzl", "java_binary")
load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

java_binary(
    name = "code_generator",
    main_class = "com.example.compiler.Compiler",
    runtime_deps = [
        ":code_generator_lib",
    ],
)

kt_jvm_library(
    name = "code_generator_lib",
    srcs = [
        "Compiler.kt",
        "JarHelper.kt",
    ],
    deps = [
        "@maven//:com_apollographql_apollo3_apollo_compiler",
    ],
)

package(default_visibility = ["//visibility:public"])
