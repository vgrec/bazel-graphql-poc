load("@rules_java//java:defs.bzl", "java_binary")

java_binary(
    name = "code_generator",
    srcs = [
        "Compiler.java",
    ],
    main_class = "com.example.compiler.Compiler",
    deps = [
        "@maven//:com_apollographql_apollo3_apollo_compiler",
    ],
)

package(default_visibility = ["//visibility:public"])
