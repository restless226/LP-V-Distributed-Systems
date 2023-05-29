/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.unique;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author pendse
 */
@WebService(serviceName = "Calculator")
public class Calculator {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "add")
    public int add(@WebParam(name = "num1") int num1, @WebParam(name = "num2") int num2) {
        return num1 + num2;
    }
    
    @WebMethod(operationName = "subtract")
    public int subtract(@WebParam(name = "num1") int num1, @WebParam(name = "num2") int num2) {
        return num1 - num2;
    }
    
    @WebMethod(operationName = "multiply")
    public int multiply(@WebParam(name = "num1") int num1, @WebParam(name = "num2") int num2) {
        return num1 * num2;
    }
    
    @WebMethod(operationName = "divide")
    public int divide(@WebParam(name = "num1") int num1, @WebParam(name = "num2") int num2) {
        if (num2 == 0) return -1;
        return num1 / num2;
    }
}
