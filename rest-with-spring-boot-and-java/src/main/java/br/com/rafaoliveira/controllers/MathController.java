package br.com.rafaoliveira.controllers;

import br.com.rafaoliveira.exceptions.UnsupportedMathOperationException;
import br.com.rafaoliveira.math.SimpleMath;
import br.com.rafaoliveira.utils.Utils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {
    private static final AtomicLong counter = new AtomicLong();
    private SimpleMath math = new SimpleMath();

    @RequestMapping(value="/sum/{numberOne}/{numberTwo}", method= RequestMethod.GET)
    public SimpleMath sum(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        if(!Utils.isNumeric(numberOne) || !Utils.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a NUMERIC value");
        }

        math.setNumberOne(Utils.convertStringToDouble(numberOne));
        math.setNumberTwo(Utils.convertStringToDouble(numberTwo));


        return new SimpleMath(math.getNumberOne(), math.getNumberTwo(), math.calculateSum());
    }

    @RequestMapping(value="/subtraction/{numberOne}/{numberTwo}", method= RequestMethod.GET)
    public SimpleMath subtraction(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        if(!Utils.isNumeric(numberOne) || !Utils.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a NUMERIC value");
        }

        math.setNumberOne(Utils.convertStringToDouble(numberOne));
        math.setNumberTwo(Utils.convertStringToDouble(numberTwo));

        return new SimpleMath(math.getNumberOne(), math.getNumberTwo(), math.calculateSubtraction());
    }

    @RequestMapping(value="/multiplication/{numberOne}/{numberTwo}", method= RequestMethod.GET)
    public SimpleMath multiplication(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        if(!Utils.isNumeric(numberOne) || !Utils.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a NUMERIC value");
        }

        math.setNumberOne(Utils.convertStringToDouble(numberOne));
        math.setNumberTwo(Utils.convertStringToDouble(numberTwo));

        return new SimpleMath(math.getNumberOne(), math.getNumberTwo(), math.calculateMultiplication());
    }

    @RequestMapping(value="/division/{numberOne}/{numberTwo}", method= RequestMethod.GET)
    public SimpleMath division(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        if(!Utils.isNumeric(numberOne) || !Utils.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a NUMERIC value");
        }

        math.setNumberOne(Utils.convertStringToDouble(numberOne));
        math.setNumberTwo(Utils.convertStringToDouble(numberTwo));

        return new SimpleMath(math.getNumberOne(), math.getNumberTwo(), math.calculateDivision());
    }

    @RequestMapping(value="/average/{numberOne}/{numberTwo}", method= RequestMethod.GET)
    public SimpleMath average(@PathVariable String numberOne, @PathVariable String numberTwo) throws Exception {
        if(!Utils.isNumeric(numberOne) || !Utils.isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Please set a NUMERIC value");
        }

        math.setNumberOne(Utils.convertStringToDouble(numberOne));
        math.setNumberTwo(Utils.convertStringToDouble(numberTwo));

        return new SimpleMath(math.getNumberOne(), math.getNumberTwo(), math.calculateAverage());
    }

    @RequestMapping(value="/squareRoot/{numberOne}", method= RequestMethod.GET)
    public SimpleMath squareRoot(@PathVariable String numberOne) throws Exception {
        if(!Utils.isNumeric(numberOne)){
            throw new UnsupportedMathOperationException("Please set a NUMERIC value");
        }

        math.setNumberOne(Utils.convertStringToDouble(numberOne));

        return new SimpleMath(math.getNumberOne(), 0.0, math.calculateSquareRoot());
    }


}
