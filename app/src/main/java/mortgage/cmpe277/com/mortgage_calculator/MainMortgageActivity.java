package mortgage.cmpe277.com.mortgage_calculator;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

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

        if (mOutputFragment == null)
            mOutputFragment = new OutputFragment();

        // If in single-pane mode, replace single visible Fragment

        if (!isInTwoPaneMode()) {
            FragmentTransaction mFragmentTransaction = getFragmentManager().beginTransaction();
            mFragmentTransaction.replace(R.id.fragment_container, mOutputFragment);
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();
            getFragmentManager().executePendingTransactions();
        }

    }
}
