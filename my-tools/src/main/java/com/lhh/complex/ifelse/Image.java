package com.lhh.complex.ifelse;

import com.lhh.simplifyifelse.ShareListener;
import com.lhh.utils.Utils;

import static com.lhh.simplifyifelse.shareService.TYPE_IMAGE;

/**
 * Copyright (C), 2019-2019
 * FileName: Image
 * Author:   s·D·bs
 * Date:     2019/6/10 14:39
 * Description:
 * Motto: 0.45%
 */
public class Image extends ShareItem {

    String imagePath;

    public Image(int type) {
        super(TYPE_IMAGE);
        this.imagePath = !Utils.isNull(imagePath) ? imagePath : "default";
    }

    @Override
    public void doShare(ShareListener listener) {
        //真正做些一些事情
    }
}
