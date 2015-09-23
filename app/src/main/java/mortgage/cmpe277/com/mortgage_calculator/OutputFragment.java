package mortgage.cmpe277.com.mortgage_calculator;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mortgage.cmpe277.com.mortgage_calculator.domain.MortgageData;

/**
 * Created by mrugen on 9/20/15.
 */
public class OutputFragment extends Fragment {

    private MortgageData mortgageData;
    private String TAG = "Output-Fragment";
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.ouput, container, false);
        MortgageData mortgageData = (MortgageData)getArguments().getSerializable("data");

        TextView monthlyPayment = (TextView) view.findViewById(R.id.monthlyPayment);
        monthlyPayment.setText(String.format("%.2f", mortgageData.totalMonthlyMortgagePayment()));

        TextView totalInterestPaid = (TextView) view.findViewById(R.id.totalInterestPaid);
        totalInterestPaid.setText(String.format("%.2f", mortgageData.totalInterestPaid()));

        TextView totalPropertyTaxPaid = (TextView)view.findViewById(R.id.totalPropertyTaxPaid);
        totalPropertyTaxPaid.setText(String.format("%.2f", mortgageData.totalPropertyTaxPaid()));

        TextView payOffDate = (TextView)view.findViewById(R.id.payOffDate);
        Calendar date = mortgageData.payOffDate();
        payOffDate.setText(new SimpleDateFormat("MMM, dd , yyyy").format(date.getTime()));
        return view;
    }
}
