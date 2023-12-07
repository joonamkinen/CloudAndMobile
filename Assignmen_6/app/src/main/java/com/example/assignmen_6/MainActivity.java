package com.example.assignmen_6;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contacts;
    private AutoCompleteTextView firstNameAutoCompleteTextView;
    private AutoCompleteTextView lastNameAutoCompleteTextView;
    private AutoCompleteTextView phoneNumberAutoCompleteTextView;
    private Button searchButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        contacts = new ArrayList<>();
        contacts.add(new Contact("Matti", "Mattinen", "123432132567890"));
        contacts.add(new Contact("Janne", "Jokunen", "98765123143210"));
        contacts.add(new Contact("Juhani", "Penttilä", "1234321567890"));
        contacts.add(new Contact("Oskari", "Pannila", "98765432133210"));


        firstNameAutoCompleteTextView = findViewById(R.id.firstNameAutoCompleteTextView);
        lastNameAutoCompleteTextView = findViewById(R.id.lastNameAutoCompleteTextView);
        phoneNumberAutoCompleteTextView = findViewById(R.id.phoneNumberAutoCompleteTextView);
        searchButton = findViewById(R.id.searchButton);
        resultTextView = findViewById(R.id.resultTextView);


        setAutoCompleteAdapter(firstNameAutoCompleteTextView, getFirstNames());
        setAutoCompleteAdapter(lastNameAutoCompleteTextView, getLastNames());
        setAutoCompleteAdapter(phoneNumberAutoCompleteTextView, getPhoneNumbers());



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchContacts();
            }
        });
    }

    private void setAutoCompleteAdapter(AutoCompleteTextView autoCompleteTextView, String[] suggestions) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, suggestions);
        autoCompleteTextView.setAdapter(adapter);
    }


    private String[] getFirstNames() {
        List<String> firstNames = new ArrayList<>();
        for (Contact contact : contacts) {
            firstNames.add(contact.getFirstName());
        }
        return firstNames.toArray(new String[0]);
    }

    private String[] getLastNames() {
        List<String> lastNames = new ArrayList<>();
        for (Contact contact : contacts) {
            lastNames.add(contact.getLastName());
        }
        return lastNames.toArray(new String[0]);
    }

    private String[] getPhoneNumbers() {
        List<String> phoneNumbers = new ArrayList<>();
        for (Contact contact : contacts) {
            phoneNumbers.add(contact.getPhoneNumber());
        }
        return phoneNumbers.toArray(new String[0]);
    }

    private void searchContacts() {

        String firstName = firstNameAutoCompleteTextView.getText().toString();
        String lastName = lastNameAutoCompleteTextView.getText().toString();
        String phoneNumber = phoneNumberAutoCompleteTextView.getText().toString();


        List<Contact> searchResults = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getFirstName().startsWith(firstName) &&
                    contact.getLastName().startsWith(lastName) &&
                    contact.getPhoneNumber().startsWith(phoneNumber)) {
                searchResults.add(contact);
            }
        }

        displaySearchResults(searchResults);
    }

    private void displaySearchResults(List<Contact> searchResults) {
        StringBuilder resultText = new StringBuilder("Search Results:\n");
        for (Contact contact : searchResults) {
            resultText.append(contact.getFirstName())
                    .append(" ")
                    .append(contact.getLastName())
                    .append(" - ")
                    .append(contact.getPhoneNumber())
                    .append("\n");
        }
        resultTextView.setText(resultText.toString());
    }
}