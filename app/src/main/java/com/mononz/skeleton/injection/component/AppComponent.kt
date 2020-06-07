package com.mononz.skeleton.injection.component

import android.app.Application
import com.mononz.skeleton.SkeletonApplication
import com.mononz.skeleton.injection.module.AppModule
import com.mononz.skeleton.injection.module.ContributesActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, ContributesActivity::class])
interface AppComponent : AndroidInjector<SkeletonApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
