package fr.coppernic.lib.key.model

import java.io.File

data class KeyInfo(
    val name: String,
    val storePassword: String = "",
    val keyPassword: String = "",
    val keyAlias: String = "",
    val key: File? = null,
    val debugKey: File? = null,
    val type: KeyType
) {
    internal class Builder() {
        var name: String = ""
        var storePassword: String = ""
        var keyPassword: String = ""
        var keyAlias: String = ""
        var key: File? = null
        var debugKey: File? = null
        var type: KeyType = KeyType.USER

        fun build(): KeyInfo {
            return KeyInfo(name, storePassword, keyPassword, keyAlias, key, debugKey, type)
        }
    }
}
