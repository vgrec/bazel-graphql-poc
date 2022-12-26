package com.example.compiler

import java.io.*
import java.util.jar.Attributes
import java.util.jar.JarEntry
import java.util.jar.JarOutputStream
import java.util.jar.Manifest

object JarHelper {

    @Throws(IOException::class)
    fun createJar(inputDirectory: File, outputJar: File?) {
        val manifest = Manifest()
        manifest.mainAttributes[Attributes.Name.MANIFEST_VERSION] = "1.0"
        val target = JarOutputStream(FileOutputStream(outputJar), manifest)
        add(inputDirectory, target)
        target.close()
    }

    @Throws(IOException::class)
    private fun add(source: File, target: JarOutputStream) {
        var name = source.path.replace("\\", "/")
        if (source.isDirectory) {
            if (!name.endsWith("/")) {
                name += "/"
            }
            val entry = JarEntry(name)
            entry.time = source.lastModified()
            target.putNextEntry(entry)
            target.closeEntry()
            for (nestedFile in source.listFiles()) {
                add(nestedFile, target)
            }
        } else {
            val entry = JarEntry(name)
            entry.time = source.lastModified()
            target.putNextEntry(entry)
            BufferedInputStream(FileInputStream(source)).use { `in` ->
                val buffer = ByteArray(1024)
                while (true) {
                    val count = `in`.read(buffer)
                    if (count == -1) break
                    target.write(buffer, 0, count)
                }
                target.closeEntry()
            }
        }
    }
}