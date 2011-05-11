package net.thinkandrew.ridesplit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RideSplit extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void calcCost(View button) {
        final EditText gasField = (EditText) findViewById(R.id.cost_gas);
        float gasCost = Float.valueOf(gasField.getText().toString());

        final EditText ridersField = (EditText) findViewById(R.id.riders);
        float numRiders = Float.valueOf(ridersField.getText().toString());

        final CheckBox responseCheckBox = (CheckBox) findViewById(R.id.CheckBoxCarWash);
        boolean carwash = responseCheckBox.isChecked();

        if (carwash) {
            gasCost += 5;
        }

        Float driverCost = gasCost / ((float) 1.1 * numRiders);
        Float riderCost = (gasCost - driverCost) / (numRiders - 1);

        if (numRiders == 1) {
            driverCost = gasCost;
            riderCost = (float) 0;
        }

        TextView driverCostField = (TextView) findViewById(R.id.cost_driver);
        TextView riderCostField = (TextView) findViewById(R.id.cost_rider);

        driverCostField.setText(getString(R.string.drivers_cost) + " $" + String.format("%.2f", driverCost));
        riderCostField.setText(getString(R.string.riders_cost) + " $" + String.format("%.2f", riderCost));
    }
}
