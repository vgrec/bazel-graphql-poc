load("@rules_java//java:defs.bzl", "java_import", "java_library")
load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")
load("//src/main/java/com/example/compiler:rules.bzl", "compiler")

kt_jvm_library(
    name = "example",
    srcs = [
        "Main.java",
        ":compiler",
    ],
    deps = [
        "@maven//:com_apollographql_apollo3_apollo_compiler",
    ],
)

compiler(
    name = "compiler",
    query_file = "queries/launchlist.graphql",
    schema_file = "queries/schema.graphqls",
)
