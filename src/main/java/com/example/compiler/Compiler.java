package com.example.compiler;

import com.apollographql.apollo3.compiler.ApolloCompiler;
import com.apollographql.apollo3.compiler.Options;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Stream;

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

        savePaths(outputDir);

    }

    private static void savePaths(File outputDir) throws IOException {
        File pathsFile = new File(outputDir, "paths.txt");
        FileWriter fileWriter = new FileWriter(pathsFile, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String pathPrefix = "src/main/java/com/example/";

        try (Stream<Path> stream = Files.walk(Paths.get(outputDir.getAbsolutePath()))) {
            stream.filter(path -> path.toString().endsWith(".kt"))
                    .forEach(path -> {
                        try {
                            String filePath = path.toString().substring(
                                    path.toString().indexOf(pathPrefix) + pathPrefix.length()
                            );
                            String line = "ctx.actions.declare_file(\"" + filePath + "\"),";
                            bufferedWriter.write(line);
                            bufferedWriter.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

        bufferedWriter.close();

        System.out.println("Paths saved at: " + pathsFile.getAbsolutePath());

    }
}
