/*
 *  Copyright (C) 2015 Rayman Yan
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ray.mvvm.lib.di.modules;

import android.app.Application;
import android.content.Context;

import com.ray.mvvm.lib.db.DBModule;
import com.ray.mvvm.lib.di.scope.PerApplication;
import com.ray.mvvm.lib.model.cache.FileModule;
import com.ray.mvvm.lib.model.http.RetrofitModule;
import com.ray.mvvm.lib.model.http.okhttp.OkHttpModule;
import com.ray.mvvm.lib.model.service.APIServiceModule;
import com.ray.mvvm.lib.widget.anotations.ContextType;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        SPModule.class,
        OkHttpModule.class,
        RetrofitModule.class,
        ComponentModule.class,
        FileModule.class,
        APIServiceModule.class,
        DBModule.class
})
public final class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Named(ContextType.APPLICATION)
    Context provideAppContext() {
        return application;
    }

    @Provides
    @PerApplication
    Application provideApp() {
        return application;
    }

}
