package com.example.compiler;

import com.apollographql.apollo3.compiler.ApolloCompiler;
import com.apollographql.apollo3.compiler.Options;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Compiler {

    public static void main(String[] args) throws IOException {
        File outputDir = new File(args[0]); // apollo
        String jarFileName = args[1]; // sources.srcjar
        File queryFile = new File(args[2]); // launchlist.graphql
        File schemaFile = new File(args[3]); // schema.graphqls

        Set<File> gqlFiles = Set.of(queryFile);

        Options options = new Options(
                gqlFiles,
                schemaFile,
                outputDir,
                new File("apolloTest"),
                "com.example"
        );

        ApolloCompiler.INSTANCE.write(options);

        File inputDirectory = new File(outputDir, "com");
        File outputJar = new File(jarFileName);

        JarHelper.createJar(inputDirectory, outputJar);
    }
}
