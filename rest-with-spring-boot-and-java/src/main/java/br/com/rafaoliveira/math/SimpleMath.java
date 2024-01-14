package br.com.rafaoliveira.math;

import br.com.rafaoliveira.exceptions.UnsupportedMathOperationException;
import br.com.rafaoliveira.utils.Utils;

public class SimpleMath {
    private Double numberOne;
    private Double numberTwo;
    private Double result;

    public SimpleMath(){}

    public SimpleMath(Double numberOne, Double numberTwo, Double result){
        this.numberOne = numberOne;
        this.numberTwo = numberTwo;
        this.result = result;
    }

    public Double getNumberOne() {
        return numberOne;
    }

    public void setNumberOne(Double numberOne) {
        this.numberOne = numberOne;
    }

    public Double getNumberTwo() {
        return numberTwo;
    }

    public void setNumberTwo(Double numberTwo) {
        this.numberTwo = numberTwo;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Double calculateSum(){
        return getNumberOne() + getNumberTwo();
    }

    public Double calculateSubtraction(){
        return getNumberOne() - getNumberTwo();
    }

    public Double calculateMultiplication(){
        return getNumberOne() * getNumberTwo();
    }

    public Double calculateDivision(){
        if(getNumberTwo() == 0.0){
            throw new UnsupportedMathOperationException("The second number cannot be equal to ZERO. Value not allowed in Division operation.");
        }

        return getNumberOne() / getNumberTwo();
    }

    public Double calculateAverage(){
        return (getNumberOne() + getNumberTwo()) / 2;
    }

    public Double calculateSquareRoot(){
        if(getNumberOne() < 0){
            throw new UnsupportedMathOperationException("Please set a positive numeric value.");
        }

        return Math.sqrt(getNumberOne());
    }


}
