package com.github.emmerich.platform;

import com.github.emmerich.config.cordova.Feature;

import java.util.List;

public interface PermissionMap {
    public List<String> getPermissionsForFeature(Feature feature);
    public List<String> getDefaultPermissions();
}
