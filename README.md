Running:

1.  Go to the Cordova for Android GitHub page and download the zipped
    version of the framework (the rest of this guide assumes version 2.7.0).
    
    Open the command line, and install that zip to your local Maven repository
    like so:
      mvn install:install-file -Dfile=cordova-android-2.7.0.zip
        -DgroupId=org.apache.cordova -DartifactId=cordova-android
        -Dversion=2.7.0 -Dpackaging=zip
        
