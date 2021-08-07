package br.com.jc.noticia.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Slf4j
@Service
public class FBInitialize {
    @PostConstruct
    public void initialize () {
        try{
            FileInputStream serviceAccount =
                    new FileInputStream("key/noticias-70986-firebase-adminsdk-batc9-e79ab53b75.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://noticias-70986-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

        }catch(Exception e){
            log.error("Erro ao conectar ao Firebase " + e.getMessage());
        }
    }
}
