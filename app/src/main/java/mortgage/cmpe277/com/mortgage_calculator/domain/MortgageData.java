package mortgage.cmpe277.com.mortgage_calculator.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mrugen on 9/20/15.
 */
public class MortgageData implements Serializable {
    private final Double homeValue;
    private final Double downPayment;
    private final Double interestRate;
    private final int terms;
    private final Double propertyTax;
    private final Double principal;

    public MortgageData(Double homeValue,Double downPayment,Double interestRate,int terms,Double propertyTax) throws InputValidationException{
        this.homeValue = homeValue;
        this.downPayment = downPayment;
        this.interestRate = interestRate;
        this.terms = terms;
        this.propertyTax = propertyTax;
        principal = homeValue - downPayment;
    }


    public double getMonthlyMortgagePayments(){
        double financeAmount = homeValue - downPayment;
        return ((financeAmount*interestRate)/12)/Math.pow((1-(1+interestRate/12)),-terms*12);
    }

    public double totalPropertyTaxPaid(){
        return homeValue*(propertyTax/100)*terms;
    }
    public  double monthlyPropertyTax(){
        return (totalPropertyTaxPaid())/(terms*12);
    }

    public double monthlyMortgagePayment(){
        double i = (interestRate / 100)/12;
        double pow = Math.pow(1 + i, terms * 12);
        return principal* i * pow / (pow-1);
    }

    public double totalMonthlyMortgagePayment(){
        return monthlyMortgagePayment()+monthlyPropertyTax();
    }

    public double totalInterestPaid(){
        Double totalInterestPaid = totalMonthlyMortgagePayment() * (terms * 12);
        return new BigDecimal(totalInterestPaid ).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public Calendar payOffDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH,(terms*12));
        return cal;
    }
}
