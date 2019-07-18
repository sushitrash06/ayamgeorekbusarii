package com.example.android.ayamgeprekbusari;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InputPesanan extends AppCompatActivity implements View.OnClickListener {
    int mAyamGeprek, mPecelLele, mGadoGado, mPecekIkanMas, mNasiPutih, mEsTeh, mEsJeruk = 0;

    int hargaAyamGeprek = 13000;
    int hargaPecelLele = 11000;
    int hargaGadoGado = 10000;
    int hargaPecekIkan = 20000;
    int hargaNasiPutih = 4000;
    int hargaEsTeh = 3000;
    int hargaEsJeruk = 10000;

    TextView jmlAyamGeprek, jmlPecekLele, jmlGadoGado, jmlPecekIkan, jmlNasiPutih, jmlEsTeh, jmlEsJeruk;
    ImageView plusAyamGeprek, plusPecekLele, plusGadoGado, plusPecekIkan, plusNasiPutih, plusEsTeh, plusEsJeruk;
    ImageView kurangAyamGreprek, kurangPecekLele, kurangGadoGado, kurangPecekIkan, kurangNasiPutih, kurangEsTeh, kurangEsJeruk;
    TextInputEditText etNoTelp, etCatatan;
    Button btnPesan;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pesanan);

        initView();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (account != null) {
                    Barang barang = new Barang(
                            account.getDisplayName(),
                            account.getId(),
                            getTotal(),
                            Integer.parseInt(jmlAyamGeprek.getText().toString()) * hargaAyamGeprek,
                            Integer.parseInt(jmlPecekLele.getText().toString()) * hargaPecelLele,
                            Integer.parseInt(jmlPecekIkan.getText().toString()) * hargaPecekIkan,
                            Integer.parseInt(jmlNasiPutih.getText().toString()) * hargaNasiPutih,
                            Integer.parseInt(jmlEsTeh.getText().toString()) * hargaEsJeruk,
                            Integer.parseInt(jmlEsJeruk.getText().toString()) * hargaEsJeruk,
                            Integer.parseInt(jmlGadoGado.getText().toString()) * hargaGadoGado,
                            etNoTelp.getText().toString(),
                            etCatatan.getText().toString()
                    );

                    String pesanan_id = myRef.push().getKey();

                    myRef.child(account.getDisplayName()).child(pesanan_id).setValue(barang);

                    Toast.makeText(InputPesanan.this, "Input Berhasil", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(InputPesanan.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(InputPesanan.this, "Successfully signed out", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(InputPesanan.this, LoginActivity.class));
                                finish();
                            }
                        });
            }
        });
    }

    private int getTotal() {

        int totalAyamGeprek = Integer.parseInt(jmlAyamGeprek.getText().toString()) * hargaAyamGeprek;
        int totalPecekLele = Integer.parseInt(jmlPecekLele.getText().toString()) * hargaPecelLele;
        int totalGadoGado = Integer.parseInt(jmlGadoGado.getText().toString()) * hargaGadoGado;
        int totalPecekIkan = Integer.parseInt(jmlPecekIkan.getText().toString()) * hargaPecekIkan;
        int totalNasiPutih = Integer.parseInt(jmlNasiPutih.getText().toString()) * hargaNasiPutih;
        int totalEsTeh = Integer.parseInt(jmlEsTeh.getText().toString()) * hargaEsJeruk;
        int totalEsJeruk = Integer.parseInt(jmlEsJeruk.getText().toString()) * hargaEsJeruk;

        return totalAyamGeprek + totalPecekLele + totalGadoGado + totalPecekIkan + totalNasiPutih + totalEsTeh + totalEsJeruk;
    }

    private void initView() {
        jmlAyamGeprek = findViewById(R.id.jmlAyamGeprek);
        jmlPecekLele = findViewById(R.id.jmlPecel);
        jmlGadoGado = findViewById(R.id.jmlGado);
        jmlPecekIkan = findViewById(R.id.jmlPecekIkan);
        jmlNasiPutih = findViewById(R.id.jmlNasiPutih);
        jmlEsTeh = findViewById(R.id.jmlEsTeh);
        jmlEsJeruk = findViewById(R.id.jmlEsJeruk);

        kurangAyamGreprek = findViewById(R.id.kurangAG);
        kurangPecekLele = findViewById(R.id.kurangPL);
        kurangGadoGado = findViewById(R.id.kurangGG);
        kurangPecekIkan = findViewById(R.id.kurangPIM);
        kurangNasiPutih = findViewById(R.id.kurangNP);
        kurangEsTeh = findViewById(R.id.kurangET);
        kurangEsJeruk = findViewById(R.id.kurangEJ);

        plusAyamGeprek = findViewById(R.id.plusAG);
        plusPecekLele = findViewById(R.id.plusPL);
        plusGadoGado = findViewById(R.id.plusGG);
        plusPecekIkan = findViewById(R.id.plusPIM);
        plusNasiPutih = findViewById(R.id.plusNP);
        plusEsTeh = findViewById(R.id.plusET);
        plusEsJeruk = findViewById(R.id.plusEJ);

        etNoTelp = findViewById(R.id.etNoTelp);
        etCatatan = findViewById(R.id.etCatatan);

        btnPesan = findViewById(R.id.btnPesan);

        kurangAyamGreprek.setOnClickListener(this);
        kurangPecekLele.setOnClickListener(this);
        kurangGadoGado.setOnClickListener(this);
        kurangPecekIkan.setOnClickListener(this);
        kurangNasiPutih.setOnClickListener(this);
        kurangEsTeh.setOnClickListener(this);
        kurangEsJeruk.setOnClickListener(this);
        plusAyamGeprek.setOnClickListener(this);
        plusPecekLele.setOnClickListener(this);
        plusGadoGado.setOnClickListener(this);
        plusPecekIkan.setOnClickListener(this);
        plusNasiPutih.setOnClickListener(this);
        plusEsTeh.setOnClickListener(this);
        plusEsJeruk.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.kurangAG:
                if (mAyamGeprek > 0) {
                    mAyamGeprek = mAyamGeprek - 1;
                    jmlAyamGeprek.setText(String.valueOf(mAyamGeprek));
                }
                break;
            case R.id.kurangPL:
                if (mPecelLele > 0) {
                    mPecelLele = mPecelLele - 1;
                    jmlPecekLele.setText(String.valueOf(mPecelLele));
                }
                break;
            case R.id.kurangGG:
                if (mGadoGado > 0) {
                    mGadoGado = mGadoGado - 1;
                    jmlGadoGado.setText(String.valueOf(mGadoGado));
                }
                break;
            case R.id.kurangPIM:
                if (mPecekIkanMas > 0) {
                    mPecekIkanMas = mPecekIkanMas - 1;
                    jmlPecekIkan.setText(String.valueOf(mPecekIkanMas));
                }
                break;
            case R.id.kurangNP:
                if (mNasiPutih > 0) {
                    mNasiPutih = mNasiPutih - 1;
                    jmlNasiPutih.setText(String.valueOf(mNasiPutih));
                }
                break;
            case R.id.kurangET:
                if (mEsTeh > 0) {
                    mEsTeh = mEsTeh - 1;
                    jmlEsTeh.setText(String.valueOf(mEsTeh));
                }
                break;
            case R.id.kurangEJ:
                if (mEsJeruk > 0) {
                    mEsJeruk = mEsJeruk - 1;
                    jmlEsJeruk.setText(String.valueOf(mEsJeruk));
                }
                break;
            case R.id.plusAG:
                mAyamGeprek++;
                jmlAyamGeprek.setText(String.valueOf(mAyamGeprek));
                break;
            case R.id.plusPL:
                mPecelLele++;
                jmlPecekLele.setText(String.valueOf(mPecelLele));
                break;
            case R.id.plusGG:
                mGadoGado++;
                jmlGadoGado.setText(String.valueOf(mGadoGado));
                break;
            case R.id.plusPIM:
                mPecekIkanMas++;
                jmlPecekIkan.setText(String.valueOf(mPecekIkanMas));
                break;
            case R.id.plusNP:
                mNasiPutih++;
                jmlNasiPutih.setText(String.valueOf(mNasiPutih));
                break;
            case R.id.plusET:
                mEsTeh++;
                jmlEsTeh.setText(String.valueOf(mEsTeh));
                break;
            case R.id.plusEJ:
                mEsJeruk++;
                jmlEsJeruk.setText(String.valueOf(mEsJeruk));
                break;
        }
    }
}