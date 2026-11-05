package org.example.springmvcexamples.controller;

import org.example.springmvcexamples.exception.Code;
import org.example.springmvcexamples.exception.XException;
import org.example.springmvcexamples.vo.ResultVO;
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
