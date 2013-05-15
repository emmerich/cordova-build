package com.github.emmerich.platform.permission;

import com.github.emmerich.config.Feature;

import java.util.List;

public interface PlatformPermissionMap {
    public List<String> getPermissionsForFeature(Feature feature);
    public List<String> getDefaultPermissions();
}
