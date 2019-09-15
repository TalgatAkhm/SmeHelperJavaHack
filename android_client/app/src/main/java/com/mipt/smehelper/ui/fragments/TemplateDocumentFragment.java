package com.mipt.smehelper.ui.fragments;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mipt.smehelper.Data;
import com.mipt.smehelper.R;
import com.mipt.smehelper.network.ClientApiPost;
import com.mipt.smehelper.network.NetworkService;
import com.mipt.smehelper.ui.utils.Document;
import com.mipt.smehelper.ui.utils.DocumentAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class TemplateDocumentFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static ClientApiPost clientApiPost = NetworkService.getInstance().getPostClientApi();
    private List<Document> templates = new ArrayList<>();

    private boolean isDownloading = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullTemplates();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_template_documents, container, false);

        ListView docsListView = rootView.findViewById(R.id.template_docs_lv);
        DocumentAdapter adapter = new DocumentAdapter(getActivity(), templates);
        docsListView.setAdapter(adapter);
        docsListView.setOnItemClickListener(this);

        return rootView;
    }

    private void fullTemplates() {
        Document docIpIp = new Document();
        docIpIp.setTitle("Договор подряда ИП - ИП");
        docIpIp.setDescription("Шаблон договора на проведение работ между индивидуальными предпринимателями.");
        docIpIp.setImgResource(R.drawable.ic_ip_icon);
        docIpIp.setCreationDate(null);

        Document docIpFl = new Document();
        docIpFl.setTitle("Договор подряда ИП - ФЛ");
        docIpFl.setDescription("Шаблон договора на проведение работ между индивидуальным предпринимателям и физическим лицом.");
        docIpFl.setImgResource(R.drawable.ic_fl_icon);
        docIpFl.setCreationDate(null);

        Document docIpOOO = new Document();
        docIpOOO.setTitle("Договор работ ИП - ООО");
        docIpOOO.setDescription("Шаблон договора на проведение работ между индивидуальным предпринимателям и обществом с ограниченной ответственностью.");
        docIpOOO.setImgResource(R.drawable.ic_ooo_icon);
        docIpOOO.setCreationDate(null);

        Document docIpFns = new Document();
        docIpFns.setTitle("Налоговая деклорация в ФНС");
        docIpFns.setDescription("Шаблон налоговой декларации в федеральную налоговую службу.");
        docIpFns.setImgResource(R.drawable.ic_fns_icon);
        docIpFns.setCreationDate(null);

        templates.add(docIpIp);
        templates.add(docIpFns);
        templates.add(docIpFl);
        templates.add(docIpOOO);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!isDownloading) {
            isDownloading = true;
            Toast.makeText(getActivity(), "Началась загрузка шаблона", Toast.LENGTH_LONG).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String name = Data.getInstance().getUser().getName();
                    String json = String.format("%s,ИСПОЛНИТЕЛЬ РАБОТ", name);
                    try {
                        Response<ResponseBody> response = clientApiPost.getDoc(json).execute();

                        String tempName = "template_document.docx";
                        ContextWrapper contextWrapper = new ContextWrapper(getActivity());
                        File directory = contextWrapper.getDir(getActivity().getFilesDir().getName(), Context.MODE_PRIVATE);
                        File file = new File(directory, tempName);
                        file.createNewFile();
                        Log.d("TAG", file.getAbsolutePath());
                        FileOutputStream fos = new FileOutputStream(file, true); // save
                        fos.write(response.body().bytes());
                        fos.close();
                        isDownloading = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            Toast.makeText(getActivity(), "Предыдущая загрузка не окончена", Toast.LENGTH_LONG).show();
        }
    }
}
