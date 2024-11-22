package com.example.blog._core.util;

import lombok.AllArgsConstructor;
import lombok.Data;

// 공통 형태 DTO
@Data
@AllArgsConstructor
public class Resp<T> {
    private Boolean success; // 성공 true, 실패 false
    private String msg;
    private T body; // 성공시 받는 타입 -그때마다 달라서 제네릭

    // 1번과 3번 T는 같은 타입이어야 함
    // 메서드 실행시 타입 정해짐
    public static <T> Resp<T> ok(T body) { // body 지역변수
        return new Resp<>(true, "성공", body);
    }

    public static <T> Resp<T> fail(String msg) {
        return new Resp<>(false, msg, null);
    }
}
