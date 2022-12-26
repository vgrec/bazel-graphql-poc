## Example usage of Bazel + Apollo GraphQL

There's no Bazel equivalent for the Apollo Gradle plugin, however, building
one for Bazel is pretty straightforward. 

### How the Gradle Apollo plugin works
If we check the [sources of the Apollo Gradle plugin](https://github.com/apollographql/apollo-kotlin/blob/main/libraries/apollo-gradle-plugin-external/src/main/kotlin/com/apollographql/apollo3/gradle/internal/ApolloGenerateSourcesTask.kt#L382),
we notice that the gradle plugin is nothing but a facade 
in front of the [apollo-compiler](https://github.com/apollographql/apollo-kotlin/tree/main/libraries/apollo-compiler) library.
`apollo-compiler` is a separate library responsible for the actual code generation, and this library is decoupled from the gradle plugin implementation.

To trigger code generation directly via `apollo-compiler`
one can use:

```kotlin
import java.io.File

val outputDir = File("apollo")
val queryFile = File("queries.graphql")
val schemaFile = File("schema.graphqls")
val testDir = File("apolloTest")
val packageName = "com.example"

val options = Options(
    setOf(queryFile),
    schemaFile,
    outputDir,
    testDir,
    packageName
)

ApolloCompiler.write(options)
```
The generated sources will be placed in a directory defined by `outputDir` in a package defined by `packageName`.

### Building a Bazel rule that triggers the code generation
With the above in place we can now [create a Bazel rule](https://github.com/vgrec/bazel-graphql-poc/blob/main/src/main/java/com/example/compiler/rules.bzl) that takes as input the `schema` and `.graphql` files
and produces as output the generated source code. For convenience, the generated
source code is zipped into a source jar, and this source jar becomes the target's output
which other rules can depend on.



