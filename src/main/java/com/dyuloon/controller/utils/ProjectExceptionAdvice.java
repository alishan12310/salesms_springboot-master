package com.dyuloon.controller.utils;

//作为springMVC的异常处理器

import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    // 拦截所有异常信息
    @ExceptionHandler
    public ResultVO doException(Exception exception){
        // 记录日志
        // 通知运维
        // 通知开发
        exception.printStackTrace();
        return ResultVOUtil.error("服务器故障，请稍后再试！");
    }
}
