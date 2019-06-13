package com.lhh.complex.ifelse;

import com.lhh.simplifyifelse.ShareListener;

/**
 * Copyright (C), 2019-2019
 * FileName: ShareItem
 * Author:   s·D·bs
 * Date:     2019/6/10 14:36
 * Description: 抽离
 * Motto: 0.45%
 */
public abstract  class ShareItem {
    int type;

    public ShareItem(int type) {
        this.type = type;
    }

    public abstract void doShare(ShareListener listener);
}
