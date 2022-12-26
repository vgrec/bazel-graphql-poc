load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

http_archive(
    name = "rules_jvm_external",
    sha256 = "6274687f6fc5783b589f56a2f1ed60de3ce1f99bc4e8f9edef3de43bdf7c6e74",
    strip_prefix = "rules_jvm_external-4.3",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/4.3.zip",
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    artifacts = [
        "com.apollographql.apollo3:apollo-compiler:3.6.2",
    ],
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)

# BEGIN io_bazel_rules_kotlin
http_archive(
    name = "io_bazel_rules_kotlin",
    sha256 = "946747acdbeae799b085d12b240ec346f775ac65236dfcf18aa0cd7300f6de78",
    urls = ["https://github.com/bazelbuild/rules_kotlin/releases/download/v1.7.0-RC-2/rules_kotlin_release.tgz"],
)

load("@io_bazel_rules_kotlin//kotlin:repositories.bzl", "kotlin_repositories")

kotlin_repositories()  # if you want the default. Otherwise see custom kotlinc distribution below

load("@io_bazel_rules_kotlin//kotlin:core.bzl", "kt_register_toolchains")

kt_register_toolchains()  # to use the default toolchain, otherwise see toolchains below
# END io_bazel_rules_kotlin
