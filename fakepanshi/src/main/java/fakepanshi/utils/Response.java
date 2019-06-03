package fakepanshi.utils;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019
 * FileName: Response
 * Author:   s·D·bs
 * Date:     2019/5/31 16:28
 * Description: 实体类
 * Motto: 0.45%
 */
@Setter
@Getter
@RequiredArgsConstructor(staticName = "of")
public class Response implements Serializable {

    private static final String OK = "ok";
    private static final String ERROR = "error";

    private Meta meta;
    private Object data;

    public Response success() {
        this.meta =  Meta.of(true, OK);
        this.data = "成功";
        return this;
    }

    public Response success(Object data) {
        this.meta =  Meta.of(true, OK);
        this.data = data;
        return this;
    }

    public Response success(Object data, String ipCity) {
        this.meta =  Meta.of(true, OK);
        this.data = data;
        return this;
    }

    public Response success(Response response, String ipCity) {
        this.meta = response.getMeta();
        this.data = response.getData();
        return this;
    }

    public Response failure() {
        this.meta = Meta.of(false, ERROR);
        this.data = "失败";
        return this;
    }

    public Response failure(String message) {
        this.meta =  Meta.of(false, message);
        this.data = "失败";
        return this;
    }

    /**
     * 输出json格式
     *
     * @return
     */
    public String toJson() {
        return Utils.formatJson(Utils.toJson(this));
    }

    @Setter
    @Getter
    @RequiredArgsConstructor(staticName = "of")
    public static class Meta implements Serializable {
        @NonNull
        private boolean success;
        @NonNull
        private String message;
    }
}
