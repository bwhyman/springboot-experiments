package org.example.backendexamples.controller;

import org.example.backendexamples.exception.Code;
import org.example.backendexamples.exception.XException;
import org.example.backendexamples.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(XException.class)
    public ResultVO handleXException(XException e) {
        if(e.getCode() != null) {
            return  ResultVO.error(e.getCode());
        }
        return ResultVO.error(e.getCodeN(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO handleException(Exception e) {
        return ResultVO.error(Code.ERROR, e.getMessage());
    }
}
