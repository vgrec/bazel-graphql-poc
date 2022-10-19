load("@rules_java//java:defs.bzl", "java_library")
load("//src/main/java/com/example/compiler:rules.bzl", "compiler")

compiler(
    name = "compiler",
    srcs = [
        "queries/launchlist.graphql",
        "queries/schema.graphqls",
    ],
)

java_library(
    name = "example_gql",
    srcs = [
        "Main.java",
    ],
    deps = [
    ],
)
