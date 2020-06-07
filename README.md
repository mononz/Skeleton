# Skeleton Android Application

Sample Android app showcasing use of
 - Dagger2
 - MVVM
 - Databinding
 - Retrofit
 - FragmentFactory
 - Navigation component (bottom navigation)
 - Separate backstacks
 - Firebase analytics/crashlytics
 - Testing (coming soon)

Build using Android Studio v4.0


### Project Setup

Create firebase android apps for 
 - com.mononz.skeleton
 - com.mononz.skeleton.beta
 - com.mononz.skeleton.dev
 
Add corresponding google-services.json files into
 - /app/src/dev/google-services.json
 - /app/src/stag/google-services.json
 - /app/src/prod/google-services.json

Copy the key.properties.template file to key.properties
 - Signing config and environment variables can be added here and applied in app/build.gradle