package com.example.compiler;

import java.io.File;
import java.util.Set;

import com.apollographql.apollo3.compiler.ApolloCompiler;
import com.apollographql.apollo3.compiler.Options;

public class Compiler {

    public static void main(String[] args) {
        Set<File> executables = Set.of(
                new File(args[1]) // launchlist.graphql
        );

        File schemaFile = new File(args[2]); // schema.graphqls
        File outputDir = new File(args[0]); // apollo
        File testDir = new File(args[0] + "/apolloTest"); // "apollo"

        Options options = new Options(
                executables,
                schemaFile,
                outputDir,
                testDir,
                "com.example"
        );

        ApolloCompiler.INSTANCE.write(options);
    }
}
