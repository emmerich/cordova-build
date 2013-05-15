package com.github.emmerich.cordovabuild.platform.permission;

import com.github.emmerich.cordovabuild.config.Feature;

import java.util.List;

public interface PlatformPermissionMap {
    public List<String> getPermissionsForFeature(Feature feature);
    public List<String> getDefaultPermissions();
}
