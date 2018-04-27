/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    int basePrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public boolean addedChocolate(View view){
        CheckBox choc = (CheckBox) view;
        boolean addChocolate = choc.isChecked();
        Log.v("MainActivity.java", "checked: " + addChocolate);
        return addChocolate;

    }
    public boolean addedWhipCream(View view){
        CheckBox wc = (CheckBox) view;
        boolean addWhippedCream = wc.isChecked();
        Log.v("MainActivity.java", "checked: " + addWhippedCream);
        return addWhippedCream;
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice(addedWhipCream(findViewById(R.id.whip_cream)), addedChocolate(findViewById(R.id.chocolate)));
        String priceMessage = createOrderSummary(price, addedWhipCream(findViewById(R.id.whip_cream)), addedChocolate(findViewById(R.id.chocolate)));
        displayMessage(priceMessage);
        Log.v("MainActivity.java", "The price is " + price);
    }

    public String createOrderSummary(int total, boolean addWhippedCream, boolean addChocolate){
        EditText n = (EditText)findViewById(R.id.name);
        String name = n.getText().toString();
        String msg = "Name: " + name + "\n";
        if(addWhippedCream){
            msg += "Added whip cream\n";
        }
        if(addChocolate){
            msg += "Added chocolate\n";
        }
        msg += "Quantity: " + quantity + "\nTotal: $" + total + "\nThank you!";
        return msg;
    }
    /**
     * Calculates the price of the order.
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        if(addWhippedCream) basePrice += 1;
        if(addChocolate) basePrice += 2;
        return quantity * (5 + basePrice);
    }
    public void increment(View view) {
            if (quantity == 100) {
                // Show an error message as a toast
                Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
                // Exit this method early because there's nothing left to do
                return;
            }
            quantity = quantity + 1;
            display(quantity);
    }
    public void decrement(View view) {
        if (quantity == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}
