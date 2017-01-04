/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 23:06:48 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 23:06:48 +0800.
 *  This file is originally created by Lena.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ray.mvvm.lib.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.ray.mvvm.lib.widget.anotations.ContextType;
import com.ray.mvvm.lib.widget.anotations.ListType;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class LayoutManagerModule {

    @Provides
    @Named(ListType.VERTICAL)
    static LinearLayoutManager provideVerticalLayout(@Named(ContextType.ACTIVITY) @NonNull Context context) {
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    @Named(ListType.HORIZONTAL)
    static LinearLayoutManager provideHorizontalLayout(@Named(ContextType.ACTIVITY) @NonNull Context context) {
        return new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
    }

    @Provides
    @Named(ListType.GRID)
    static GridLayoutManager provideGridLayout(@Named(ContextType.ACTIVITY) @NonNull Context context) {
        return new GridLayoutManager(context, 3);
    }

}

