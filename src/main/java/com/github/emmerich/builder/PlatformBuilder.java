package com.github.emmerich.builder;

import com.github.emmerich.context.ApplicationContext;
import com.github.emmerich.context.PlatformContext;

public interface PlatformBuilder {

    public void build(ApplicationContext applicationContext, PlatformContext context);

}
