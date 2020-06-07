package com.mononz.skeleton.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mononz.skeleton.injection.ViewModelFactory
import com.mononz.skeleton.injection.ViewModelKey
import com.mononz.skeleton.ui.dashboard.DashboardViewModel
import com.mononz.skeleton.ui.home.HomeViewModel
import com.mononz.skeleton.ui.notifications.NotificationsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContributesViewModel {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindsHomeViewModel(model: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    internal abstract fun bindsDashboardViewModel(model: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotificationsViewModel::class)
    internal abstract fun bindsNotificationsViewModel(model: NotificationsViewModel): ViewModel

    @Binds
    internal abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
