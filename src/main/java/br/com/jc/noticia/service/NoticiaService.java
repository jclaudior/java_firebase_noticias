package br.com.jc.noticia.service;

import br.com.jc.noticia.model.Noticia;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class NoticiaService {
    public static final String COL_NAME="noticias";

    public String salvarNoticia(Noticia noticia) throws ExecutionException, InterruptedException {
        noticia.setTitulo(noticia.getTitulo().toUpperCase(Locale.ROOT));
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(noticia.getTitulo()).set(noticia);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Noticia buscarNoticia(String titulo) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(titulo.toUpperCase(Locale.ROOT));
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Noticia noticia = null;

        if(document.exists()) {
            noticia = document.toObject(Noticia.class);
            return noticia;
        }else {
            return null;
        }
    }

    public List<Noticia> buscarNoticias() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        List<Noticia> noticias = new ArrayList<>();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            noticias.add(document.toObject(Noticia.class));
        }


        return noticias;

    }

    public String atualizarNoticia(Noticia noticia) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(noticia.getTitulo().toUpperCase(Locale.ROOT)).set(noticia);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deletarNoticia(Noticia noticia) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(noticia.getTitulo().toUpperCase(Locale.ROOT)).delete();
        return "Document with Patient ID "+noticia.getTitulo()+" has been deleted in " +  writeResult.get().getUpdateTime() ;
    }
}
