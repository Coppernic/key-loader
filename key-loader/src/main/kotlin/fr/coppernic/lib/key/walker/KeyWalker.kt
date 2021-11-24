package fr.coppernic.lib.key.walker

import fr.coppernic.lib.key.model.KeyInfo
import fr.coppernic.lib.key.model.KeyType
import fr.coppernic.lib.log.KeyLoaderDefines
import java.io.File
import java.util.Properties

internal class KeyWalker {
    internal fun scan(dir: File): List<KeyInfo> {
        KeyLoaderDefines.LOG.debug("Scan ${dir.absoluteFile.absolutePath}")
        return dir.listFiles { file ->
            KeyLoaderDefines.LOG.debug("List ${file.absolutePath}")
            file.isDirectory
        }?.sortedBy { it.name }?.map { subDir ->
            scanDir(subDir)
        } ?: emptyList()
    }

    private fun scanDir(dir: File): KeyInfo {
        KeyLoaderDefines.LOG.debug("scanDir ${dir.absolutePath}")
        val builder = KeyInfo.Builder().apply {
            name = dir.name
        }
        dir.listFiles()?.forEach { file ->
            KeyLoaderDefines.LOG.debug("list  ${file.absolutePath}")
            if (file.name.endsWith(".properties")) {
                parseProperties(builder, file)
            } else if (file.name.endsWith(".jks")) {
                if (file.name.matches(regexDebug)) {
                    builder.debugKey = file
                } else {
                    builder.key = file
                }
            }
        }
        return builder.build()
    }

    private fun parseProperties(infoBuilder: KeyInfo.Builder, propertiesFile: File) {
        val properties = Properties()
        propertiesFile.inputStream().use {
            properties.load(it)
        }

        with(infoBuilder) {
            keyAlias = properties.getProperty("keyAlias")
            keyPassword = properties.getProperty("keyPassword")
            storePassword = properties.getProperty("storePassword")
            // System by default
            type = if (properties["isSystem"] == "false") {
                KeyType.USER
            } else {
                KeyType.SYSTEM
            }
        }
    }

    companion object {
        val regexDebug = ".+[_-]debug.+".toRegex()
    }
}
