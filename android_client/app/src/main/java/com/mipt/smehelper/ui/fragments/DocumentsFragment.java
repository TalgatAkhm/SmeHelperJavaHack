package com.mipt.smehelper.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.mipt.smehelper.R;

public class DocumentsFragment extends Fragment {

    private FloatingActionsMenu floatingActionsMenu;
    private FloatingActionButton sendDocsBtn;
    private FloatingActionButton templateDocsBtn;
    private FrameLayout docsContainer;

    private Fragment sendDocuments;
    private Fragment templateDocuments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sendDocuments = new SendDocumentFragment();
        templateDocuments = new TemplateDocumentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_document, container, false);
        floatingActionsMenu = rootView.findViewById(R.id.multiple_actions_down);
        sendDocsBtn = rootView.findViewById(R.id.send_docs_acbtn);
        templateDocsBtn = rootView.findViewById(R.id.template_docs_acbtn);
        docsContainer = rootView.findViewById(R.id.document_container);

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.document_container, sendDocuments);
        fragmentTransaction.commit();

        templateDocsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.document_container, templateDocuments);
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }
}
