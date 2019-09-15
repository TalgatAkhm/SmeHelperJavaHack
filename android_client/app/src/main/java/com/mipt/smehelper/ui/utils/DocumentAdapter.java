package com.mipt.smehelper.ui.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mipt.smehelper.R;

import java.util.List;

public class DocumentAdapter extends BaseAdapter {

    private List<Document> docsList;
    private LayoutInflater inflater;

    public DocumentAdapter(Context context, List<Document> documentList) {
        this.docsList = documentList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return docsList.size();
    }

    @Override
    public Object getItem(int position) {
        return docsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.document_item, parent, false);
        }

        TextView title = view.findViewById(R.id.doc_title);
        TextView text = view.findViewById(R.id.doc_desc);
        ImageView docImg = view.findViewById(R.id.doc_img);

        Document currentDocument = docsList.get(position);

        title.setText(currentDocument.getTitle());
        text.setText(currentDocument.getDescription());
        docImg.setImageResource(currentDocument.getImgResource());

        return view;
    }

    public void addDoc(Document document) {
        docsList.add(0, document);
    }
}
