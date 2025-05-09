package com.example.isa.di.global;

import android.content.Context;

import com.example.isa.di.app.AppComponent;
import com.example.isa.di.app.AppModule;
import com.example.isa.di.app.DaggerAppComponent;
import com.example.isa.di.global.base.BaseComponent;
import com.example.isa.di.global.base.BaseComponentBuilder;
import com.example.isa.di.global.base.BaseModule;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Provider;

public class ComponentHolder {

    private final Context context;

    @Inject
    Map<Class<?>, Provider<BaseComponentBuilder>> builders;

    private Map<Class<?>, BaseComponent> components;

    public ComponentHolder(Context context) {
        this.context = context;
    }

    public void inject() {
        AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule(context)).build();
        appComponent.injectComponentsHolder(this);
        components = new HashMap<>();
    }

    public BaseComponent getComponent(Class<?> cls, BaseModule module) {
        BaseComponent component = components.get(cls);
        if (component == null) {
            BaseComponentBuilder builder = Objects.requireNonNull(builders.get(cls)).get();
            if (module != null) {
                builder.module(module);
            }
            component = builder.build();
            components.put(cls, component);
        }

        return component;
    }

    public BaseComponent getComponent(Class<?> cls) {
        return getComponent(cls, null);
    }

    public void releaseComponent(Class<?> cls) {
        components.put(cls, null);
    }
}