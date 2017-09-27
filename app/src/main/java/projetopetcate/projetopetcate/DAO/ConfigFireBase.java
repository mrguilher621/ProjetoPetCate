package projetopetcate.projetopetcate.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by guilh on 27/09/2017.
 */

public class ConfigFireBase {

    private static DatabaseReference refenciaFirebase;
    private static FirebaseAuth autenticaçao;

    public static DatabaseReference getFirebase(){
        if (refenciaFirebase == null){
            refenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return refenciaFirebase;
    }

    public static FirebaseAuth getFirebaseAutenticaçao(){
        if (autenticaçao == null){
            autenticaçao = FirebaseAuth.getInstance();
        }
        return autenticaçao;
    }
}
