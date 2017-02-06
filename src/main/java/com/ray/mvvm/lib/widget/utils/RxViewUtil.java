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

package com.ray.mvvm.lib.widget.utils;

import android.view.MenuItem;
import android.view.View;

import com.jakewharton.rxbinding.view.RxMenuItem;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Android Studio.
 * ProjectName: IndustrialControlCircle
 * Author:  Lena
 * Date: 5/4/16
 * Time: 11:38 AM
 * \ ----------------------------------------
 * \| A small leak will sink a great ship.!  |
 * \ ----------------------------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public final class RxViewUtil {

    public static Observable<Void> throttleClick(MenuItem menuItem) {
        return RxMenuItem
                .clicks(menuItem)
                .throttleLast(1, TimeUnit.SECONDS)
                .onBackpressureLatest();
    }

    public static Observable<Void> throttleClick(View view) {
        return RxView
                .clicks(view)
                .throttleLast(1, TimeUnit.SECONDS)
                .onBackpressureLatest();
    }

    public static Observable<Void> throttleViewClicked(View view) {
        return RxView.clicks(view)
                .throttleFirst(1, TimeUnit.SECONDS)
                .debounce(500, TimeUnit.MILLISECONDS)
                .onBackpressureLatest()
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Void> throttleViewClicked(MenuItem menuItem) {
        return RxMenuItem.clicks(menuItem)
                .throttleFirst(1, TimeUnit.SECONDS)
                .debounce(500, TimeUnit.MILLISECONDS)
                .onBackpressureLatest()
                .observeOn(AndroidSchedulers.mainThread());
    }

}
