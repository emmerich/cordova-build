package com.github.emmerich.platform;

import com.github.emmerich.config.Feature;

import java.util.List;

public interface PermissionMap {
    public List<String> getPermissionsForFeature(Feature feature);
    public List<String> getDefaultPermissions();
}
