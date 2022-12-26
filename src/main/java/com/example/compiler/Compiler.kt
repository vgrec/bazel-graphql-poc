package com.example.compiler

import com.apollographql.apollo3.annotations.ApolloExperimental
import com.apollographql.apollo3.compiler.ApolloCompiler
import com.apollographql.apollo3.compiler.Options
import com.example.compiler.JarHelper.createJar
import java.io.File
import java.io.IOException

@OptIn(ApolloExperimental::class)
object Compiler {

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val outputDir = File(args[0]) // apollo
        val jarFileName = args[1] // sources.srcjar
        val queryFile = File(args[2]) // launchlist.graphql
        val schemaFile = File(args[3]) // schema.graphqls
        val gqlFiles = setOf(queryFile)

        val options = Options(
            gqlFiles,
            schemaFile,
            outputDir,
            File("apolloTest"),
            "com.example"
        )

        ApolloCompiler.write(options)

        val inputDirectory = File(outputDir, "com")
        val outputJar = File(jarFileName)
        createJar(inputDirectory, outputJar)
    }
}