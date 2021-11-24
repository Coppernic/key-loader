package fr.coppernic.lib.key.walker

import fr.coppernic.lib.key.model.KeyType
import fr.coppernic.lib.log.KeyLoaderDefines
import java.io.File
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldHaveSize
import org.junit.Before
import org.junit.Test

class KeyWalkerTest {

    private lateinit var sut: KeyWalker

    @Before
    fun setUp() {
        KeyLoaderDefines.verbose = true
        sut = KeyWalker()
    }

    @Test
    fun test() {
        sut.scan(File("src/test/resources/AndroidDeviceKeys")).apply {
            shouldHaveSize(6)
            get(0).apply {
                name.`should be equal to`("access")
                storePassword.`should be equal to`("store")
                keyPassword.`should be equal to`("pass")
                keyAlias.`should be equal to`("alias")
                key?.path.`should be equal to`("src/test/resources/AndroidDeviceKeys/access/platform.jks")
                debugKey.shouldBeNull()
                type.`should be equal to`(KeyType.SYSTEM)
            }
        }
    }
}
