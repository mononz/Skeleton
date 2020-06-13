package com.mononz.skeleton

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Rule

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    // Robolectric tests here.
}