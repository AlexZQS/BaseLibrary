/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Fri, 11 Nov 2016 22:14:52 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Fri, 11 Nov 2016 22:14:52 +0800.
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

package com.ray.mvvm.lib.presenter;

import com.ray.mvvm.lib.widget.eventbus.event.BaseEvent;
import com.ray.mvvm.lib.widget.lifecycle.LifecycleEvent;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public interface IPresenter {

    <T extends BaseEvent> void subscribeEvent(Class<T> aClass, Action1<T> action1);

    <T> void subscribe(Observable<T> observable, Action1<? super T> action);

    void setLifecycleObs(Observable<LifecycleEvent> obs, LifecycleEvent lifecycleEvent);

    <T> void subscribe(Observable<T> observable, Subscriber<T> subscriber);

//    void subscribe(Subscription subscription);

    void unsubscribe();
}
