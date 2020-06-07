package com.mononz.skeleton.injection.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.mononz.skeleton.injection.FragmentInjectionFactory
import com.mononz.skeleton.injection.FragmentKey
import com.mononz.skeleton.ui.dashboard.DashboardFragment
import com.mononz.skeleton.ui.detail.DetailFragment
import com.mononz.skeleton.ui.home.HomeFragment
import com.mononz.skeleton.ui.notifications.NotificationsFragment
import com.mononz.skeleton.ui.second.SecondFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContributesFragment {

    @Binds
    @IntoMap
    @FragmentKey(DashboardFragment::class)
    abstract fun bindDashboardFragment(fragment: DashboardFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment::class)
    abstract fun bindHomeFragment(fragment: HomeFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(NotificationsFragment::class)
    abstract fun bindNotificationsFragment(fragment: NotificationsFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(SecondFragment::class)
    abstract fun bindSecondFragment(fragment: SecondFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DetailFragment::class)
    abstract fun bindDetailFragment(fragment: DetailFragment): Fragment

    @Binds
    abstract fun bindFragmentFactory(factory: FragmentInjectionFactory): FragmentFactory
}
