package com.lhh.simplifyifelse;

/**
 * Copyright (C), 2019-2019
 * FileName: ShareListener
 * Author:   s·D·bs
 * Date:     2019/6/10 13:51
 * Description: 回调类
 * Motto: 0.45%
 */
public interface ShareListener {

    int STATE_SUCC = 0;
    int STATE_FAIL = 1;
    void onCallback(int state, String msg);
}
