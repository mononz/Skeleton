package com.mononz.skeleton.injection.module

import com.mononz.skeleton.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributesActivity {

    @ContributesAndroidInjector(modules = [ContributesFragment::class])
    internal abstract fun mainActivity(): MainActivity
}
