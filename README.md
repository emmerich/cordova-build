# Cordova Build Maven Plugin

## Description
A Maven plugin used to build WAR projects into native applications for mobile platforms, using Apache Cordova.

## Usage
### Dependencies
* Maven!
* The underlying SDK of the platform(s) you're building for.
* The Apache Cordova project, in zipped format and **at version 2.5.0**, for the platform(s) you're building for. Get them [here](https://github.com/apache/).

### Preparation
#### Installing Cordova to your local Maven repository
1. `cd` to the directory containing the zipped Cordova project(s) for your platform(s).
2. Run the following Maven command (example below for `cordova-android`, replace with your file name):
`mvn install:install-file -Dfile=cordova-android-2.5.0.zip -DgroupId=org.apache.cordova -DartifactId=cordova-android -Dversion=2.5.0 -Dpackaging=zip`

#### POM file
Look at the POM file in the sample directory for a good example of how to get started.
The important configuration is the location of your PhoneGap `config.xml`. Make sure it points to the right place.
        
