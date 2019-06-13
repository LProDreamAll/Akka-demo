package com.lhh.simplifyifelse;

import com.lhh.utils.Utils;
import lombok.extern.slf4j.Slf4j;


/**
 * Copyright (C), 2019-2019
 * FileName: shareService
 * Author:   s·D·bs
 * Date:     2019/6/10 13:52
 * Description: 分享实现类
 * Motto: 0.45%
 */
@Slf4j
public class shareService {

    public static final int TYPE_LINK = 0;
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_TEXT = 2;
    public static final int TYPE_IMAGE_TEXT = 3;

    /**
     * @return void
     * @Author s·D·bs
     * @Description //最原始的具有讽刺意义的if else
     * @Date 14:01 2019/6/10
     * @Param [item, listener]
     */
    public void share(ShareItem item, ShareListener listener) {

        if (item != null) {
            if (item.type == TYPE_LINK) {
                // 分享链接
                if (!Utils.isNull(item.link) && !Utils.isNull(item.title)) {
                    doShareLink(item.link, item.title, item.content, listener);
                } else {
                    if (listener != null) {
                        listener.onCallback(ShareListener.STATE_FAIL, "分享信息不完整");
                    }
                }
            } else if (item.type == TYPE_IMAGE) {
                // 分享图片
                if (!Utils.isNull(item.imagePath)) {
                    doShareImage(item.imagePath, listener);
                } else {
                    if (listener != null) {
                        listener.onCallback(ShareListener.STATE_FAIL, "分享信息不完整");
                    }
                }
            } else if (item.type == TYPE_TEXT) {
                // 分享文本
                if (!Utils.isNull(item.content)) {
                    doShareText(item.content, listener);
                } else {
                    if (listener != null) {
                        listener.onCallback(ShareListener.STATE_FAIL, "分享信息不完整");
                    }
                }
            } else if (item.type == TYPE_IMAGE_TEXT) {
                // 分享图文
                if (!Utils.isNull(item.imagePath) && !Utils.isNull(item.content)) {
                    doShareImageAndText(item.imagePath, item.content, listener);
                } else {
                    if (listener != null) {
                        listener.onCallback(ShareListener.STATE_FAIL, "分享信息不完整");
                    }
                }
            } else {
                if (listener != null) {
                    listener.onCallback(ShareListener.STATE_FAIL, "不支持的分享类型");
                }
            }
        } else {
            if (listener != null) {
                listener.onCallback(ShareListener.STATE_FAIL, "ShareItem 不能为 null");
            }
        }
    }

    private void doShareText(String content, ShareListener listener) {
    }

    private void doShareImage(String imagePath, ShareListener listener) {
    }

    private void doShareLink(String link, String title, String content, ShareListener listener) {
    }

    private void doShareImageAndText(String link, String content, ShareListener listener) {
    }


    /**
     * @return void
     * @Author s·D·bs
     * @Description //TODO 更好一点的方式
     * 接口分层主要解决 空值判断过多
     * @Date 14:02 2019/6/10
     * @Param [item, listener]
     */
    public void share1(ShareItem item, ShareListener listener) {
        if (item == null) {
            if (listener != null) {
                listener.onCallback(ShareListener.STATE_FAIL, "ShareItem 不能为 null");
            }
            return;
        }

        if (listener == null) {
            listener = new ShareListener() {
                @Override
                public void onCallback(int state, String msg) {
                    log.info("DEBUG", "ShareListener is null");
                }
            };
        }

        shareImpl(item, listener);
    }

    private void shareImpl(ShareItem item, ShareListener listener) {
        if (item.type == TYPE_LINK) {
            // 分享链接
            if (!Utils.isNull(item.link) && !Utils.isNull(item.title)) {
                doShareLink(item.link, item.title, item.content, listener);
            } else {
                listener.onCallback(ShareListener.STATE_FAIL, "分享信息不完整");
            }
        } else if (item.type == TYPE_IMAGE) {
            // 分享图片
            if (!Utils.isNull(item.imagePath)) {
                doShareImage(item.imagePath, listener);
            } else {
                listener.onCallback(ShareListener.STATE_FAIL, "分享信息不完整");
            }
        } else if (item.type == TYPE_TEXT) {
            // 分享文本
            if (!Utils.isNull(item.content)) {
                doShareText(item.content, listener);
            } else {
                listener.onCallback(ShareListener.STATE_FAIL, "分享信息不完整");
            }
        } else if (item.type == TYPE_IMAGE_TEXT) {
            // 分享图文
            if (!Utils.isNull(item.imagePath) && !Utils.isNull(item.content)) {
                doShareImageAndText(item.imagePath, item.content, listener);
            } else {
                listener.onCallback(ShareListener.STATE_FAIL, "分享信息不完整");
            }
        } else {
            listener.onCallback(ShareListener.STATE_FAIL, "不支持的分享类型");
        }
    }

}
