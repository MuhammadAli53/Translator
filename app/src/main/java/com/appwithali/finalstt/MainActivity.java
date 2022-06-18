package com.appwithali.finalstt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int REQUEST_PERMISSION_CODE = 1;

    Spinner fromSpinner;
    Spinner toSpinner;
    EditText textView1;
    private TextView textView;
    TextToSpeech textToSpeech;
    TextView button;
    String mSelectedLanguage =  "en";
    String[] fromLanguage = {"English", "German", "Arabic", "Urdu", "Chinese", "French", "Latin","Turkish","Hindi","Russian"};
    String[] toLanguage = {"Urdu", "German", "Arabic", "English", "Chinese", "French", "Latin","Turkish","Hindi","Russian"};
    int fromLanguageCode=0;
    int toLanguageCode=0;

    ArrayList arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.layout);

        button = findViewById(R.id.button);
        TextView button2 = findViewById(R.id.button2);
        TextView button5 = findViewById(R.id.button5);
        TextView button6 = findViewById(R.id.button6);
        textView1 = findViewById(R.id.textView1);
        textView = findViewById(R.id.textView);
        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView3 = findViewById(R.id.imageView3);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView4 = findViewById(R.id.imageView4);
        ImageView iv13 = findViewById(R.id.iv13);
        ImageView iv12 = findViewById(R.id.iv12);
        ImageView iv14 = findViewById(R.id.iv14);

    fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);

        arrayList=new ArrayList<>();
        arrayList.add("English");
        arrayList.add("Urdu");
        arrayList.add("Chinese");
        arrayList.add("German");

        textToSpeech=new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                   if (i==TextToSpeech.SUCCESS)
                   {
                       int lang=textToSpeech.setLanguage(Locale.getDefault());

                   }else{
                        Toast.makeText(MainActivity.this, "Write Something!", Toast.LENGTH_SHORT).show();
                    }}
                });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=textView1.getText().toString();
                if (s.isEmpty()){
                    Toast.makeText(MainActivity.this, "Write Something!", Toast.LENGTH_SHORT).show();
                } else{
                    int speech=textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=textView.getText().toString();
                if (s.isEmpty()){
                    Toast.makeText(MainActivity.this, "Write Something!", Toast.LENGTH_SHORT).show();
                } else{
                    int speech=textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Text1 = textView.getText().toString();
                String Text = textView1.getText().toString();
                if (Text.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Already Empty!", Toast.LENGTH_SHORT).show();
                } else {
                    textView1.setText("");
                    textView.setText("");

                }
            }
        });

/*
        fromSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i=0;
                fromLanguageCode = getLanguageCode(fromLanguage[i]);
                switch (i) {
                    case 0:
                        mSelectedLanguage = "en";
                        break;
                    case 1:
                        mSelectedLanguage = "ur";
                        break;
                    case 2:
                        mSelectedLanguage = "zh";
                        break;
                    case 3:
                        mSelectedLanguage = "de";
                        break;}


             Dialog   dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.search);
                dialog.getWindow().setLayout(650,800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                EditText et=dialog.findViewById(R.id.et);
                ListView lv=dialog.findViewById(R.id.lv);
                ArrayAdapter<String> adapter1=new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1,arrayList);
                lv.setBackgroundColor(Color.BLACK);
                lv.setAdapter(adapter1);
                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }
                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        adapter1.getFilter().filter(charSequence);
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        fromSpinner.setText(adapter1.getItem(i));
                        dialog.dismiss();
                    }
                });
            }
        });
*/
       fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){

                fromLanguageCode = getLanguageCode(fromLanguage[i]);

                switch (i) {
                    case 0:
                        mSelectedLanguage = "en";
                        break;
                    case 1:
                        mSelectedLanguage = "de";
                        break;
                    case 2:
                        mSelectedLanguage = "ar";
                        break;
                    case 3:
                        mSelectedLanguage = "ur";
                        break;
                    case 4:
                        mSelectedLanguage = "zh";
                        break;
                    case 5:
                        mSelectedLanguage = "fr";
                        break;
                    case 6:
                        mSelectedLanguage = "lt";
                        break;
                    case 7:
                        mSelectedLanguage = "tr";
                        break;
                    case 8:
                        mSelectedLanguage = "hi";
                        break;
                    case 9:
                        mSelectedLanguage = "ru";
                        break;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        ArrayAdapter fromAdapter = new ArrayAdapter(this, R.layout.spinner_item, fromLanguage);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(fromAdapter);





        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                toLanguageCode = getLanguageCode(toLanguage[i]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        ArrayAdapter toAdapter = new ArrayAdapter(this, R.layout.spinner_item, toLanguage);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(toAdapter);

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textView.getText().toString();

                if(text.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "can't share empty message!", Toast.LENGTH_SHORT).show();
                }
                else{
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "");
                i.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(i);
            }}
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textView.getText().toString();
                if (text.isEmpty()){
                    Toast.makeText(MainActivity.this, "Can't send empty message!", Toast.LENGTH_SHORT).show();
                }
                else{
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");
                intent.putExtra(Intent.EXTRA_SUBJECT, textView1.getText()).toString();
                intent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(intent.createChooser(intent, ""));
            }}
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=textView.getText().toString();
                if (s.isEmpty()){
                    Toast.makeText(MainActivity.this, "Nothing to copy!", Toast.LENGTH_SHORT).show();
                } else{
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", s);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
            }}
        });
        iv14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textView1.getText().toString();
                if (text.isEmpty()){
                    Toast.makeText(MainActivity.this, "Can't share empty message!", Toast.LENGTH_SHORT).show();
                }else{
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "");
                i.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(i);
            }}
        });
        iv13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textView1.getText().toString();
                if (text.isEmpty()){
                    Toast.makeText(MainActivity.this, "Can't share empty message!", Toast.LENGTH_SHORT).show();
                }
                else{
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");
                intent.putExtra(Intent.EXTRA_SUBJECT, textView1.getText()).toString();
                intent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(intent.createChooser(intent, ""));
            }}
        });
        iv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=textView1.getText().toString();
                if (s.isEmpty()){
                    Toast.makeText(MainActivity.this, "Nothing to copy!", Toast.LENGTH_SHORT).show();
                } else{
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("EditText", s);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
            }}
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, mSelectedLanguage);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say something to translate");
                try {
                    startActivityForResult(intent, REQUEST_PERMISSION_CODE);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                if (textView1.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter text to translate", Toast.LENGTH_SHORT).show();
                }else if (fromLanguageCode == 0){
                    Toast.makeText(MainActivity.this, "Please select Source Language", Toast.LENGTH_SHORT).show();
                }else if (toLanguageCode == 0){
                    Toast.makeText(MainActivity.this, "Please select the language to make translation", Toast.LENGTH_SHORT).show();
                }else{
                    translateText(fromLanguageCode,toLanguageCode,textView1.getText().toString());
                }
            }
        });
    }
    private void translateText(int fromLanguageCode, int toLanguageCode, String source) {
        textView.setText("Downloading model, please wait...");
        FirebaseTranslatorOptions options = new FirebaseTranslatorOptions.Builder()
                .setSourceLanguage(fromLanguageCode)
                .setTargetLanguage(toLanguageCode)
                .build();
        FirebaseTranslator translator = FirebaseNaturalLanguage.getInstance().getTranslator(options);
        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder().build();
        translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                textView.setText("Translating..");
                translator.translate(source).addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        textView.setText(s);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to translate!! try again", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Failed to download model!! Check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                textView1.setText(result.get(0));
            }
        }}
    public int getLanguageCode(String language) {
        int languageCode = 0;
        switch (language){
            case "English":
                languageCode = FirebaseTranslateLanguage.EN;
                break;
            case "German":
                languageCode = FirebaseTranslateLanguage.DE;
                break;
            case "Arabic":
                languageCode = FirebaseTranslateLanguage.AR;
                break;
            case "Urdu":
                languageCode = FirebaseTranslateLanguage.UR;
                break;
            case "Chinese":
                languageCode = FirebaseTranslateLanguage.ZH;
                break;
            case "French":
                languageCode = FirebaseTranslateLanguage.FR;
                break;
            case "Latin":
                languageCode = FirebaseTranslateLanguage.LT;
                break;
            case "Turkish":
                languageCode = FirebaseTranslateLanguage.TR;
                break;
            case "Hindi":
                languageCode = FirebaseTranslateLanguage.HI;
                break;
            case "Russian":
                languageCode = FirebaseTranslateLanguage.RU;
                break;
            default:
                languageCode = 0;
        }
        return languageCode;
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}