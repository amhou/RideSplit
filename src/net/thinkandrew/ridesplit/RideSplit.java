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
        final EditText gasField = (EditText) findViewById(R.id.cost_gas); //cost of gas
        float gasCost = Float.valueOf(gasField.getText().toString());

        final EditText ridersField = (EditText) findViewById(R.id.riders); //number of riders
        float numRiders = Float.valueOf(ridersField.getText().toString());

        final CheckBox responseCheckBox = (CheckBox) findViewById(R.id.CheckBoxCarWash); // would you like a carwash?

        if (numRiders <= (float) 0) {// prevents division by 0
            Toast.makeText(RideSplit.this, "Number of Riders must be greater than 0.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (responseCheckBox.isChecked()) {// carwash is worth $5
            gasCost += 5;
        }

        Float driverCost = gasCost / ((float) 1.2 * numRiders);      // Driver pays less than riders to compensate for wear and tear on car
        Float riderCost = (gasCost - driverCost) / (numRiders - 1);  // Rider cost

        if (numRiders == 1) {// Special case
            driverCost = gasCost;
            riderCost = (float) 0;
        }

        TextView driverCostField = (TextView) findViewById(R.id.cost_driver); // Output of driver's cost
        TextView riderCostField = (TextView) findViewById(R.id.cost_rider);   // Output of rider's cost

        driverCostField.setText(getString(R.string.drivers_cost) + " $" + String.format("%.2f", driverCost));
        riderCostField.setText(getString(R.string.riders_cost) + " $" + String.format("%.2f", riderCost));
    }
}
