package com.revolut.exceptions;

public class InvalidAmountException  extends IllegalArgumentException{

   public InvalidAmountException(String message){
       super(message);
   }
}
