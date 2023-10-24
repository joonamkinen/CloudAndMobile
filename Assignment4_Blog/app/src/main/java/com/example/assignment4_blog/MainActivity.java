package com.example.assignment4_blog;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText commentEditText;
    private LinearLayout blogEntriesLayout;
    private List<BlogEntry> blogEntries = new ArrayList<>();

    private EditText searchTextEditText;
    private EditText searchDateEditText;
    private Button searchButton;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss | dd.MM.yyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        commentEditText = findViewById(R.id.commentEditText);
        blogEntriesLayout = findViewById(R.id.blogEntriesLayout);
        Button submitButton = findViewById(R.id.submitButton);
        searchTextEditText = findViewById(R.id.searchTextEditText);
        searchDateEditText = findViewById(R.id.searchDateEditText);
        searchButton = findViewById(R.id.searchButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String comment = commentEditText.getText().toString();

                if (username.isEmpty() || comment.isEmpty()) {
                    usernameEditText.setBackgroundColor(Color.RED);
                    commentEditText.setBackgroundColor(Color.RED);
                } else {
                    usernameEditText.setBackgroundColor(Color.WHITE);
                    commentEditText.setBackgroundColor(Color.WHITE);
                    BlogEntry entry = new BlogEntry(username, comment);
                    blogEntries.add(0, entry); // Add to the beginning of the list.
                    updateBlogEntriesUI();
                    commentEditText.setText("");
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the search criteria.
                String searchText = searchTextEditText.getText().toString();
                String searchDate = searchDateEditText.getText().toString();

                // Filter blog entries based on search criteria.
                List<BlogEntry> searchResults = filterBlogEntries(searchText, searchDate);

                // Update the UI to display the search results.
                displaySearchResults(searchResults);
            }
        });
    }

    private void updateBlogEntriesUI() {
        blogEntriesLayout.removeAllViews();


        for (int i = 0; i < blogEntries.size(); i++) {
            BlogEntry entry = blogEntries.get(i);
            TextView entryView = new TextView(this);


            String formattedDate = dateFormat.format(entry.getTimestamp());

            entryView.setText(String.format("%d. %s - %s\n%s", i + 1, formattedDate, entry.getUsername(), entry.getComment()));
            blogEntriesLayout.addView(entryView);
        }
    }
    private List<BlogEntry> filterBlogEntries(String searchText, String searchDate) {
        List<BlogEntry> searchResults = new ArrayList<>();

        for (BlogEntry entry : blogEntries) {
            String formattedDate = dateFormat.format(entry.getTimestamp());
            boolean matchesText = entry.getUsername().contains(searchText) || entry.getComment().contains(searchText);
            boolean matchesDate = formattedDate.contains(searchDate);

            if (matchesText && matchesDate) {
                searchResults.add(entry);
            }
        }

        return searchResults;
    }

    private void displaySearchResults(List<BlogEntry> searchResults) {
        blogEntriesLayout.removeAllViews();

        for (int i = 0; i < searchResults.size(); i++) {
            BlogEntry entry = searchResults.get(i);
            TextView entryView = new TextView(this);
            String formattedDate = dateFormat.format(entry.getTimestamp());
            entryView.setText(String.format("%d. %s - %s\n%s", i + 1, formattedDate, entry.getUsername(), entry.getComment()));
            blogEntriesLayout.addView(entryView);
        }
    }
}