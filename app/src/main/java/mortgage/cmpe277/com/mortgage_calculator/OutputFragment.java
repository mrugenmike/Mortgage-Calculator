package mortgage.cmpe277.com.mortgage_calculator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mortgage.cmpe277.com.mortgage_calculator.domain.MortgageData;

/**
 * Created by mrugen on 9/20/15.
 */
public class OutputFragment extends Fragment {

    private MortgageData mortgageData;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.ouput,container,false);
    }
}
