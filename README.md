# Skeleton Android Application

![app](https://raw.githubusercontent.com/mononz/Skeleton/master/art/skeleton.png)

Sample Android app showcasing use of
 - Dagger2
 - MVVM
 - Databinding
 - Retrofit
 - FragmentFactory
 - Navigation component
 - Bottom Navigation with separate backstacks
 - Firebase analytics/crashlytics

Coming soon
 - Testing
 - CI/Distribution with AppCenter

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


### Code format

Keep code looking nice! Run ktlint with gradle

```
./gradlew ktlintFormat
```

Format xml by right clicking on the /res folder and selecting 'Reformat Code'


### Video

![app](https://raw.githubusercontent.com/mononz/Skeleton/master/art/video.gif)
