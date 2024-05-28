package uz.otamurod.kmp.newsapp.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import uz.otamurod.kmp.newsapp.android.di.viewModelsModule
import uz.otamurod.kmp.newsapp.di.sharedKoinModules

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = sharedKoinModules + viewModelsModule
        startKoin {
            androidContext(this@NewsApp)
            modules(modules = modules)
        }
    }
}