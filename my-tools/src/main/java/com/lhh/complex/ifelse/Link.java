package com.lhh.complex.ifelse;

import com.lhh.simplifyifelse.ShareListener;
import com.lhh.simplifyifelse.shareService;
import com.lhh.utils.Utils;

/**
 * Copyright (C), 2019-2019
 * FileName: Link
 * Author:   s·D·bs
 * Date:     2019/6/10 14:37
 * Description:
 * Motto: 0.45%
 */
public class Link extends  ShareItem{

    String title;
    String content;
    String link;

    public Link(String link, String title, String content) {
        super(shareService.TYPE_LINK);
        this.link = !Utils.isNull(link) ? link : "default";
        this.title = !Utils.isNull(title) ? title : "default";
        this.content = !Utils.isNull(content) ? content : "default";
    }

    @Override
    public void doShare(ShareListener listener) {
        //do something
    }

}
