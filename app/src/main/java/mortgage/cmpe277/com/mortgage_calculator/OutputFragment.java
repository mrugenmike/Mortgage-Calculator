package mortgage.cmpe277.com.mortgage_calculator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static mortgage.cmpe277.com.mortgage_calculator.R.layout.fragment_main_mortgage;

/**
 * Created by mrugen on 9/20/15.
 */
public class OutputFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(fragment_main_mortgage,container,false);
    }
}
