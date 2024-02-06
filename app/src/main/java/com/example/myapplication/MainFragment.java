package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Artikal;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private ListView listView;
    private ArrayList<Artikal> artikalList;
    Artikal[] slideImages = {
            // Existing entries

            // For a2 to a6
            new Artikal("Srce torta", "t1.jfif", "ova divna torta od jagoda je napravljena mešanjem svežih jagoda u sočno testo, stvarajući nalet voćne dobrote sa svakim zalogajem.", 250, "jaja, secer, voda, brasno, i naravno, sočne jagode.", true),
            new Artikal("Cheesecake", "cheesecake.jpg", "Prepustite se kremastoj eleganciji našeg kolača od sira, gde se baršunasta mešavina krem sira, jaja i šećera delikatno peče do savršenstva, stvarajući nebeski desert koji se topi u ustima.", 300, "jaja, secer, voda, brasno, bogata dobrota krem sira, maline.", true),
            new Artikal("Vocna torta", "t2.jfif", "Doživite žarki ples ukusa uz našu tortu od limete i kivija. Oštre note limete i tropske slatkoće kivija spajaju se u harmoničnu fuziju, čineći svaku krišku osvežavajućim užitkom.", 275, "jaja, secer, voda, brasno, limeta, kivi", true),
            new Artikal("Borovnica torta", "t3.jfif", "Uronite u slatku simfoniju borovnica u svakom sloju ove torte. Prasak dobrote borovnice isprepleten je sa vlažnom i mekanom teksturom za odličnu poslasticu.", 400, "jaja, secer, voda, brasno, borovnice", true),
            new Artikal("Cokolada torta", "chock.jpg", "Prepustite se dekadenciji naše čokoladne torte. Bogat kakao u kombinaciji sa baršunastim testom stvara vlažan i ugodan doživljaj, čineći ga istinskim slavljem za ljubitelje čokolade.", 380, "jaja, secer, voda, brasno, cokolada", true),
            new Artikal("Bela torta", "t4.jfif", "Prihvatite jednostavnost i eleganciju naše bele torte. Delikatna mešavina sastojaka daje laganu i mekanu teksturu, pružajući platno za vaš izbor glazura ili preliva.", 420, "jaja, secer, voda, brasno", true)
    };

    // Initializing slideImages2 array
    Artikal[] slideImages2 = {
            new Artikal("Cokoladni mafin", "cc.jpg", "Ovaj čokoladni mafin osvaja svojom bogatom čokoladnom notom i mekanom teksturom. Savršen je za ljubitelje čokolade koji žele uživati u ukusnom zalogaju.", 250, "jaja, secer, voda, brasno", false),
            new Artikal("Sareni mafin", "ck.jpg", "Sareni mafin donosi radost boja i ukusa. Svaki zalogaj je šareno iskustvo, savršeno za sve koji vole veselje i raznolikost.", 150, "jaja, secer, voda, brasno", false),
            new Artikal("Plavi mafin", "cd.jfif", "Plavi mafin osvaja svojom jednostavnošću i ukusom. Savršen je izbor za one koji vole klasične arome i sočnost.", 370, "jaja, secer, voda, brasno", false),
            new Artikal("Plavo beli mafin", "kolaci.jpg", "Ovaj mafin kombinuje harmoniju plave i bele boje s ukusom koji oduševljava. Jedinstveni spoj vizualnog užitka i fantastičnog ukusa.", 300, "jaja, secer, voda, brasno", false),
            new Artikal("Jagoda mafin", "jag.jfif", "Jagoda mafin donosi svežinu jagoda u svaki zalogaj. Sočnost ovog mafina čini ga savršenim desertom za sve ljubitelje voćnih poslastica.", 195, "jaja, secer, voda, brasno", false),
            new Artikal("Roze plavi mafini", "ck.jpg", "Roze plavi mafini spoj su nežnosti roze boje i osvežavajućeg ukusa. Idealan izbor za posebne trenutke i sladokusce koji vole neobične kombinacije.", 400, "jaja, secer, voda, brasno", false)
    };

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the artikalList array
        artikalList = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Find the ListView
        listView = view.findViewById(R.id.listView);

        // Get the arguments passed to the fragment
        Bundle bundle = getArguments();
        if (bundle != null) {
            // Get the title of the tab
            String title = bundle.getString("title");

            // Populate the ListView based on the title
            if ("Torte".equals(title)) {
                populateListViewWithArtikals(slideImages);
            } else if ("Kolaci".equals(title)) {
                populateListViewWithArtikals(slideImages2);
            }
        }

        return view;
    }

    // Method to populate the ListView with Artikal objects
    private void populateListViewWithArtikals(Artikal[] artikals) {
        // Clear the existing list
        artikalList.clear();

        // Add the Artikal objects to the list
        for (Artikal artikal : artikals) {
            artikalList.add(artikal);
        }

        // Create a custom adapter for the ListView
        ImageListAdapter adapter = new ImageListAdapter(requireContext(), artikalList);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);
    }
}
