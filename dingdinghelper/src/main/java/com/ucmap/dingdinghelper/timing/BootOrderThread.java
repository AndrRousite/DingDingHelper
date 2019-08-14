/*
 * Copyright (C)  Justson(https://github.com/Justson/DingDingHelper)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ucmap.dingdinghelper.timing;

import android.os.SystemClock;

import com.alipay.hulu.common.tools.CmdTools;
import com.alipay.hulu.common.utils.LogUtil;
import com.ucmap.dingdinghelper.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class BootOrderThread extends Thread {

    private List<String> o;
    private int flag;

    public BootOrderThread(List<String> o, int flag) {
        this.o = o;
        this.flag = flag;
    }

    @Override
    public void run() {

//        ShellUtils.CommandResult mCommandResult = ShellUtils.execCmd(o, true);
        List<String> results = CmdTools.execHighPrivilegeCmds(o, 3000);
        LogUtil.i("BootOrderThread", "result:" + results + " o:" + o);
        SystemClock.sleep(2000);
        EventBus.getDefault().post(new MessageEvent(flag));
    }
}
