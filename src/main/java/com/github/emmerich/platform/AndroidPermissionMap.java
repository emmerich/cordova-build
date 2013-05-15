package com.github.emmerich.platform;

import com.github.emmerich.config.Feature;

import java.util.*;

public class AndroidPermissionMap implements PermissionMap {
    private static final String PERMISSION_PREFIX = "android.permission.";

    private Map<String, List<String>> featureConvert;
    private List<String> defaults;

    public AndroidPermissionMap() {
        featureConvert = new HashMap<String, List<String>>();

        // https://build.phonegap.com/docs/config-xml - PhoneGap API Features
        featureConvert.put("http://api.phonegap.com/1.0/battery",
                Arrays.asList("android.permission.BROADCAST_STICKY"));

        featureConvert.put("http://api.phonegap.com/1.0/camera",
                Arrays.asList("android.permission.CAMERA"));

        featureConvert.put("http://api.phonegap.com/1.0/contacts",
                Arrays.asList("android.permission.READ_CONTACTS",
                        "android.permission.WRITE_CONTACTS",
                        "android.permission.GET_ACCOUNTS"));

        featureConvert.put("http://api.phonegap.com/1.0/file",
                Arrays.asList("android.permission.WRITE_EXTERNAL_STORAGE"));

        featureConvert.put("http://api.phonegap.com/1.0/geolocation",
                Arrays.asList("android.permission.ACCESS_COURSE_LOCATION",
                        "android.permission.ACCESS_FINE_LOCATION",
                        "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS"));

        featureConvert.put("http://api.phonegap.com/1.0/media",
                Arrays.asList("android.permission.RECORD_AUDIO",
                        "android.permission.RECORD_VIDEO",
                        "android.permission.MODIFY_AUDIO_SETTINGS"));

        featureConvert.put("http://api.phonegap.com/1.0/network",
                Arrays.asList("android.permission.ACCESS_NETWORK_STATE"));

        featureConvert.put("http://api.phonegap.com/1.0/notification",
                Arrays.asList("android.permission.VIBRATE"));

        defaults = Arrays.asList("android.permission.INTERNET");
    }

    @Override
    public List<String> getPermissionsForFeature(Feature feature) {
        List<String> permissions = featureConvert.get(feature.getName());

        if(permissions == null) {
            return new ArrayList<String>();
        } else {
            return permissions;
        }
    }

    @Override
    public List<String> getDefaultPermissions() {
        return defaults;
    }
}
