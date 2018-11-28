package com.example.android.inventory;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.inventory.data.InventoryContract.InventoryEntry;
import com.example.android.inventory.data.InventoryDBHelper;

public class EditorActivity extends AppCompatActivity {

    /**
     * EditText field to enter the product's name
     */
    private EditText mNameEditText;

    /**
     * EditText field to enter the product's price
     */
    private EditText mPriceEditText;

    /**
     * initial quantity's value
     */
    private int item_qty = 0;

    /**
     * Increasing Button
     */
    private Button mIncrementBtn;

    /**
     * Decreasing Button
     */
    private Button mDecrementBtn;

    /**
     * EditText field to enter the product's quantity
     */
    private EditText mQuantityEditText;

    /**
     * EditText field to enter the Supplier's Name
     */
    private EditText mSupplierNameEditText;

    /**
     * EditText field to enter the Supplier's Phone Number
     */
    private EditText mSupplierPhoneNumberEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views and initialize them
        mNameEditText = findViewById(R.id.edit_product_name);
        mPriceEditText = findViewById(R.id.edit_price);
        mQuantityEditText = findViewById(R.id.edit_quantity);
        mSupplierNameEditText = findViewById(R.id.edit_supplier_name);
        mSupplierPhoneNumberEditText = findViewById(R.id.edit_supplier_phone_number);
        mIncrementBtn = findViewById(R.id.increment_qty_btn_id);
        mDecrementBtn = findViewById(R.id.decrement_qty_btn_id);

        item_qty = Integer.parseInt(mQuantityEditText.getText().toString());

        mIncrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIncrementQty();
            }
        });
        mDecrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDecrementQty();
            }
        });
    }

    /**
     * Get user input from editor and save new product into database.
     */
    private void insertProduct() {
        String nameString = mNameEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();
        String quantityString = mQuantityEditText.getText().toString().trim();
        String supplierNameString = mSupplierNameEditText.getText().toString().trim();
        String supplierPhoneNumberString = mSupplierPhoneNumberEditText.getText().toString().trim();
        int price = Integer.parseInt(priceString);
        int quantity = Integer.parseInt(quantityString);

        // Create database helper
        InventoryDBHelper mDBHelper = new InventoryDBHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and product attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, nameString);
        values.put(InventoryEntry.COLUMN_PRICE, price);
        values.put(InventoryEntry.COLUMN_QUANTITY, quantity);
        values.put(InventoryEntry.COLUMN_SUPPLIER_NAME, supplierNameString);
        values.put(InventoryEntry.COLUMN_SUPPLIER_PHONE_NUMBER, supplierPhoneNumberString);

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(InventoryEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was a error with insertion.
            Toast.makeText(this, "Error with saving product", Toast.LENGTH_LONG).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Product saved with row id: " + newRowId, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save pet to database
                insertProduct();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Increase Quantity by one every time this button is clicked
     * more than ten units is not allowed
     */
    private void mIncrementQty() {
        item_qty = Integer.parseInt(mQuantityEditText.getText().toString());
        item_qty = item_qty + 1;
        if (item_qty >= 11) {
            //Show an error message as a toast
            Toast.makeText(this, getString(R.string.more_than_ten), Toast.LENGTH_LONG).show();
        } else {
            mQuantityEditText.setText(Integer.toString(item_qty));
        }
    }

    /**
     * Decrease Quantity by one every time this button is clicked
     * less than zero units is not allowed
     */
    private void mDecrementQty() {
        item_qty = Integer.parseInt(mQuantityEditText.getText().toString());
        item_qty = item_qty - 1;
        if (item_qty <= -1) {
            //Show an error message as a toast
            Toast.makeText(this, getString(R.string.less_than_zero), Toast.LENGTH_LONG).show();
        } else {
            mQuantityEditText.setText(Integer.toString(item_qty));
        }
    }
}
