package com.example.ahmedco.pdfreader;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

  Context context;
  private ArrayList<PDFDoc> pdfDocs;

  public CustomAdapter(Context c, ArrayList<PDFDoc> pdfDocs) {
    this.context = c;
    this.pdfDocs = pdfDocs;
  }

  public ArrayList<PDFDoc> getPdfDocs() {
    return pdfDocs;
  }

  public void setPdfDocs(ArrayList<PDFDoc> pdfDocs) {
    this.pdfDocs = pdfDocs;
  }

  @Override public int getCount() {
    return pdfDocs.size();
  }

  @Override public Object getItem(int i) {
    return pdfDocs.get(i);
  }

  @Override public long getItemId(int i) {
    return i;
  }

  @Override public View getView(int i, View view, ViewGroup viewGroup) {
    if (view == null) {
      //INFLATE CUSTOM LAYOUT
      view = LayoutInflater.from(context).inflate(R.layout.model, viewGroup, false);
    }

    final PDFDoc pdfDoc = (PDFDoc) this.getItem(i);

    TextView nameTxt = (TextView) view.findViewById(R.id.nameTxt);
    ImageView img = (ImageView) view.findViewById(R.id.pdfImage);

    File downloadsFolder =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

    //BIND DATA
    nameTxt.setText(pdfDoc.getName());
    //   img.setImageBitmap(pdfDoc.setBitmap());

    //VIEW ITEM CLICK
    view.setOnClickListener(view1 -> openPDFView(pdfDoc.getPath()));
    return view;
  }

  //OPEN PDF VIEW
  private void openPDFView(String path) {
    //Intent i = new Intent(context, PDF_Activity.class);
    //i.putExtra("PATH", path);
    //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //context.startActivity(i);

    File downloadsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

    Intent target = new Intent(Intent.ACTION_VIEW);
    target.setDataAndType(Uri.fromFile(downloadsFolder), "application/pdf");
    target.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

    Intent intent = Intent.createChooser(target, "Open File");

    try {
      intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
      context.startActivity(intent);
    } catch (ActivityNotFoundException e) {
    }
  }
}
