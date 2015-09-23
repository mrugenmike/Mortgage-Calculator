package mortgage.cmpe277.com.mortgage_calculator;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import mortgage.cmpe277.com.mortgage_calculator.domain.MortgageData;


public class MainMortgageActivity  extends Activity implements MortgageInputFragment.CalculateListener{
    private static final String TAG = "Mortgage-Activity";

    private MortgageInputFragment mMortgageInputFragment;
    private OutputFragment mOutputFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mortgage);

        if(!isInTwoPaneMode()){
            mMortgageInputFragment = new MortgageInputFragment();

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container,mMortgageInputFragment);
            fragmentTransaction.commit();
        } else{
            // save a reference of fragment for later use

            mOutputFragment = (OutputFragment)getFragmentManager()
                    .findFragmentById(R.id.output_frag);
        }
    }

    private boolean isInTwoPaneMode() {
        return findViewById(R.id.fragment_container)==null;
    }

    @Override
    public void onCalculate(MortgageData mortgageData) {

        Log.i(TAG, "User provided Mortgage Data "+mortgageData);

        Bundle bundle = new Bundle();
        bundle.putSerializable("data",mortgageData);
        if (mOutputFragment == null){
            mOutputFragment = new OutputFragment();
        }
        mOutputFragment.setArguments(bundle);
        // If in single-pane mode, replace single visible Fragment

        if (!isInTwoPaneMode()) {
            FragmentTransaction mFragmentTransaction = getFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.fragment_container, mOutputFragment);
            mOutputFragment.setArguments(bundle);
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();
            getFragmentManager().executePendingTransactions();
        }

    }


}
