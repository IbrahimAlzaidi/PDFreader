package com.example.ahmedco.pdfreader;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.GridView;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class ListActivity extends AppCompatActivity {

  private static final String TAG = "List Activity";

  private CustomAdapter adapter;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);

    final GridView gridView = (GridView) findViewById(R.id.grid_view);
    final SearchView searchView = (SearchView) findViewById(R.id.search_bar_view);

    // Setup grid view
    final ArrayList<PDFDoc> bookList = getPDFs();
    adapter = new CustomAdapter(getBaseContext(), bookList);
    gridView.setAdapter(adapter);

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String query) {

        // Fetch the data remotely
        ArrayList<PDFDoc> foundedBookList = search(query.toLowerCase(), bookList);

        // Set new list
        adapter.setPdfDocs(foundedBookList);
        adapter.notifyDataSetChanged();

        // log book list
        for (PDFDoc book : foundedBookList) {
          Log.e(TAG, "book name: " + book.getName());
        }

        // Reset SearchView
        searchView.clearFocus();
        searchView.setQuery("", false);
        searchView.setIconified(true);

        //searchItem.collapseActionView();
        // Set activity title to search query
        ListActivity.this.setTitle(query);

        return false;
      }

      @Override public boolean onQueryTextChange(String newText) {
        return false;
      }
    });
  }

  private ArrayList<PDFDoc> search(String query, List<PDFDoc> bookNameList) {
    ArrayList<PDFDoc> matchList = new ArrayList<>();
    for (PDFDoc book : bookNameList) {
      if (book.getName().toLowerCase().contains(query)) matchList.add(book);
    }
    return matchList;
  }

  private ArrayList<PDFDoc> getPDFs() {
    ArrayList<PDFDoc> pdfDocs = new ArrayList<>();
    //TARGET FOLDER
    File downloadsFolder =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) ;

    PDFDoc pdfDoc;

    if (downloadsFolder.exists()) {
      //GET ALL FILES IN DOWNLOAD FOLDER
      File[] files = downloadsFolder.listFiles();

      //LOOP THRU THOSE FILES GETTING NAME AND URI
      for (File file : files) {
        if (file.getPath().endsWith("pdf")) {
          pdfDoc = new PDFDoc();
          pdfDoc.setName(file.getName().toLowerCase());
          pdfDoc.setPath(file.getAbsolutePath());

          pdfDocs.add(pdfDoc);
        }
      }
    }

    return pdfDocs;
  }


  private List<PDFDoc> getPdfFileList(File path) {
    PDFDoc pdfDoc;

    if (path.exists()) {
      List<PDFDoc> list = new ArrayList<>();
      File[] files = path.listFiles();
      for (File file : files) {
        if (file.getPath().endsWith("pdf")) {
          pdfDoc = new PDFDoc();
          pdfDoc.setName(file.getName().toLowerCase());
          pdfDoc.setPath(file.getAbsolutePath());
          list.add(pdfDoc);
        }
      }

      return list;
    } else {
      return null;
    }
  }

  private File getDownloadPublicDirectory() {
    return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
  }
}
