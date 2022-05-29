package com.sora.gateway.advice;

import com.sora.utils.R;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @className: AdviceController
 * @description: 异常捕获类
 * @date: 2022/05/29
 * @author: Sora33
 */
@RestControllerAdvice
@Log4j2
public class AdviceController {

    @ExceptionHandler(RuntimeException.class)
    public R runTimeException(RuntimeException runtimeException) {
        log.info("捕获到异常,异常信息[{}],请求路径:[{}]", runtimeException.getMessage());
        return R.error(500, "发生异常!!!");
    }
}
