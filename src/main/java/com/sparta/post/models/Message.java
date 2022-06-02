package com.sparta.post.models;

public enum Message {
    Denied("로그인이 필요합니다."),
    SUCCESS("작성 완료"),
    NO_ROLE("권한이 없습니다.");

    private final String msg;
    Message(String msg) {
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
}
