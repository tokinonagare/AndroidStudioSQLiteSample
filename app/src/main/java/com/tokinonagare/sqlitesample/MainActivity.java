package com.tokinonagare.sqlitesample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText tokinonagareInput;
    TextView tokinonagareText;
    MyDBHandler sqLiteDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tokinonagareInput = (EditText) findViewById(R.id.tokinonagareInput);
        tokinonagareText  = (TextView) findViewById(R.id.tokinonagareText);
        sqLiteDatabaseHandler = new MyDBHandler(this, null, null, 1);

        printDatabase();
    }

    //Add a products to database
    public void addButtonClicked(View view) {
        Products product = new Products(tokinonagareInput.getText().toString());
        sqLiteDatabaseHandler.addProduct(product);
        printDatabase();
    }

    //Delete items
    public void deleteButtonClicked(View view) {
        String inputText = tokinonagareInput.getText().toString();
        sqLiteDatabaseHandler.deleteProduct(inputText);
        printDatabase();
    }

    private void printDatabase() {
        String sqLiteDatabaseString = sqLiteDatabaseHandler.databaseToString();
        tokinonagareText.setText(sqLiteDatabaseString);
        tokinonagareInput.setText("");
    }

}
