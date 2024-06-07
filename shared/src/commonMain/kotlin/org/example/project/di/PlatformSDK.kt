package org.example.project.di

import org.example.project.core.configuration.Configuration
import org.example.project.di.modules.authModule
import org.example.project.di.modules.commonModule
import org.example.project.di.modules.createConfigurationModule
import org.example.project.di.modules.favouriteRecipesModule
import org.example.project.di.modules.mainModule
import org.example.project.di.modules.networkModule
import org.example.project.di.modules.recipeDetailsModule
import org.example.project.di.modules.recommendedWinesModule
import org.example.project.di.modules.storageModule
import org.kodein.di.DI
import org.kodein.di.DirectDI
import org.kodein.di.LazyDelegate
import org.kodein.di.direct
import org.kodein.di.instance

object PlatformSDK {

    private var _di: DirectDI? = null

    val di: DirectDI
        get() = requireNotNull(_di)

    fun init(conf: Configuration) {
        _di = DI {
            importAll(
                createConfigurationModule(conf),
                networkModule,
                storageModule,
                commonModule,
                authModule,
                mainModule,
                favouriteRecipesModule,
                recommendedWinesModule,
                recipeDetailsModule,
            )
        }.direct
    }

    inline fun <reified T : Any> lazyInstance(tag: Any? = null): LazyDelegate<T> {
        return di.lazy.instance(tag)
    }
}
