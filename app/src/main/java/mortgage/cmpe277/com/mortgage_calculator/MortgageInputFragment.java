package mortgage.cmpe277.com.mortgage_calculator;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.DecimalMin;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Select;

import java.util.List;

import mortgage.cmpe277.com.mortgage_calculator.domain.InputValidationException;
import mortgage.cmpe277.com.mortgage_calculator.domain.MortgageData;


/**
 * A placeholder fragment containing a simple view.
 */
public class MortgageInputFragment extends Fragment implements View.OnClickListener,Validator.ValidationListener {

    private static String TAG = "Input-Fragment";

    private CalculateListener mCallback;
    private View view;
    private Button calculate;
    private Button reset;

    @NotEmpty(message = "Home Value cannot be empty")
    private EditText homeValueEditable;
    @NotEmpty(message = "Down payment amount cannot be empty")
    private EditText downPaymentEditable;
    @NotEmpty(message = "Interest Rate must be specified")
    private EditText interestEditText;
    private Spinner rateSpinner;
    @NotEmpty(message = "Poperty tax rate cannot be empty")
    private EditText propertyTaxRateView;

    public Validator validator;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.calculate:
                 // Validate Values
                validator.validate();
                // Fetch Values
                break;
            case R.id.reset:
                //do processing
                homeValueEditable.setText("");
                downPaymentEditable.setText("");
                interestEditText.setText("");
                rateSpinner.setSelection(0);
                propertyTaxRateView.setText("");
                break;
            default:
                Log.w(TAG,"Unknown button press");
        }
    }

    private MortgageData getMortgageData() throws InputValidationException {
        Double homeValue = Double.valueOf(homeValueEditable.getText().toString());
        Double downPayment = Double.valueOf(downPaymentEditable.getText().toString());
        Double interestRate = Double.valueOf(interestEditText.getText().toString());
        int term = Integer.valueOf(rateSpinner.getSelectedItem().toString());
        Double propertyTaxRate = Double.valueOf(propertyTaxRateView.getText().toString());
        return new MortgageData(homeValue,downPayment,interestRate,term,propertyTaxRate);
    }

    private void validateInput() {

    }

    @Override
    public void onValidationSucceeded() {
        try {
            MortgageData mortgageData = getMortgageData();
            mCallback.onCalculate(mortgageData);
        } catch (InputValidationException e) {
            e.printStackTrace();
        }
       // Toast.makeText(getActivity(), "Thanks for Input", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getActivity());

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

    public interface CalculateListener{
        public void onCalculate(MortgageData mortgageData);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_main_mortgage, container, false);
            calculate = (Button) view.findViewById(R.id.calculate);
            calculate.setOnClickListener(this);
            reset = (Button) view.findViewById(R.id.reset);
            reset.setOnClickListener(this);
            homeValueEditable = (EditText) view.findViewById(R.id.homeValue);
            downPaymentEditable = (EditText) view.findViewById(R.id.downPaymentEditText);
            interestEditText = (EditText) view.findViewById(R.id.interestEditText);
            rateSpinner = (Spinner) view.findViewById(R.id.rates_spinner);
            propertyTaxRateView = (EditText) view.findViewById(R.id.propertyTaxRateEditView);

            validator = new Validator(this);
            validator.setValidationListener(this);
            return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (CalculateListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SelectionListener");
        }
    }


}
