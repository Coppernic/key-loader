package fr.coppernic.lib.key

import fr.coppernic.lib.key.model.KeyInfo
import fr.coppernic.lib.key.model.KeyLoaderException
import fr.coppernic.lib.key.walker.KeyWalker
import java.io.File

class Loader {
    private val keyWalker = KeyWalker()

    fun load(dir: File): List<KeyInfo> {
        if (!dir.isDirectory) {
            throw KeyLoaderException("${dir.absolutePath} is not a directory")
        }
        return keyWalker.scan(dir)
    }
}
