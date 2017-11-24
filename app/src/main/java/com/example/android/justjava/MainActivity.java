package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

import static android.R.attr.checked;
import static android.R.attr.name;
import static com.example.android.justjava.R.id.user_name;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity=2;

    public void submitOrder(View view) {

        EditText username = (EditText) findViewById(R.id.user_name);
        String name = username.getText().toString();

        CheckBox whipped_cream = (CheckBox) findViewById(R.id.checkbox);
        boolean checked = whipped_cream.isChecked();

        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean chocolate_checked = chocolate.isChecked();

        int price = calculatePrice(quantity, checked, chocolate_checked, 5);

        String message = createOrderSummary(quantity, price, name, checked, chocolate_checked);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order summary for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    
    private int calculatePrice(int quantity,boolean whipped_cream,boolean chocolate,int price){
        int cost = quantity*price;
        if (whipped_cream)
            cost+=1*quantity;
        if (chocolate)
            cost+=2*quantity;
        return cost;
    }

    private String createOrderSummary(int quantity,int price,String name,boolean checked,boolean choclate_checked){
        String message = "Name :"+name+
                "\nAdd whipped cream?"+checked+"" +
                "\nAdd chocolate?"+choclate_checked+"" +
                "\nquantiy:"+quantity+
                "\nTotal Price:"+price+
                "\nThank You!";
        return message;
    }

    public void increment(View view){
        quantity++;
        displayQuantity(quantity);
    }
    public void decrement(View view){
        if (quantity>0)
        quantity--;
        displayQuantity(quantity);
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}