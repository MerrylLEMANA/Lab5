package com.example.lab5;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView idView;
    private EditText nameEdit, skuEdit;
    private MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idView = findViewById(R.id.productID);
        nameEdit = findViewById(R.id.productName);
        skuEdit = findViewById(R.id.productSku);

        dbHandler = new MyDBHandler(this);
    }

    public void newProduct(View view) {
        String name = nameEdit.getText().toString().trim();
        String skuStr = skuEdit.getText().toString().trim();

        if (name.isEmpty() || skuStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int sku = Integer.parseInt(skuStr);
        Product product = new Product(name, sku);
        dbHandler.addProduct(product);

        Toast.makeText(this, "Product added!", Toast.LENGTH_SHORT).show();
        nameEdit.setText("");
        skuEdit.setText("");
        idView.setText("Not Assigned");
    }

    public void lookupProduct(View view) {
        String name = nameEdit.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Enter a product name", Toast.LENGTH_SHORT).show();
            return;
        }

        Product product = dbHandler.findProduct(name);

        if (product != null) {
            idView.setText(String.valueOf(product.getID()));
            skuEdit.setText(String.valueOf(product.getSku()));
            Toast.makeText(this, "Product found!", Toast.LENGTH_SHORT).show();
        } else {
            idView.setText("No Match Found");
            skuEdit.setText("");
            Toast.makeText(this, "No match found", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteProduct(View view) {
        String name = nameEdit.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Enter a product name", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean result = dbHandler.deleteProduct(name);

        if (result) {
            idView.setText("Record Deleted");
            nameEdit.setText("");
            skuEdit.setText("");
            Toast.makeText(this, "Product deleted!", Toast.LENGTH_SHORT).show();
        } else {
            idView.setText("No Match Found");
            Toast.makeText(this, "No match found", Toast.LENGTH_SHORT).show();
        }
    }
}