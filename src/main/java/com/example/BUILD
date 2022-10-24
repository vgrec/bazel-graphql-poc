load("@rules_java//java:defs.bzl", "java_import", "java_library")
load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")
load("//src/main/java/com/example/compiler:rules.bzl", "compiler")

compiler(
    name = "compiler",
    srcs = [
        "queries/launchlist.graphql",
        "queries/schema.graphqls",
    ],
)

java_import(
    name = "java_archive",
    jars = [
        "jars/hello.jar",
    ],
)

kt_jvm_library(
    name = "example_gql",
    srcs = [
        "Main.java",
        #        ":compiler",
    ],
    deps = [
        ":java_archive",
        "@maven//:com_apollographql_apollo3_apollo_compiler",
    ],
)
