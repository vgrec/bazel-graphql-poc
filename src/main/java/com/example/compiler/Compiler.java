package com.example.compiler;

import com.apollographql.apollo3.compiler.ApolloCompiler;
import com.apollographql.apollo3.compiler.Options;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Compiler {

    public static void main(String[] args) throws IOException {
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
