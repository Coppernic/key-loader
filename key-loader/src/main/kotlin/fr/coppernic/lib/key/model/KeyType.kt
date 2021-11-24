package fr.coppernic.lib.key.model

enum class KeyType {
    USER,
    SYSTEM;

    companion object {
        fun fromInt(i: Int): KeyType {
            return if (i == 0) {
                USER
            } else {
                SYSTEM
            }
        }
    }
}
