package com.esprit.validationlastwoop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class testliste extends AppCompatActivity {
    private ListView listView;
    private DatabaseHelper DatabaseHelper;
    private ArrayList<String> VariousView,availability,option;
    private ArrayAdapter<String> adapter,availabilityAdapter,optionadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testliste);

        Button deleteButton = findViewById(R.id.ParticipateButtongettAll);

        listView = findViewById(R.id.listViewtrip);

        DatabaseHelper = new DatabaseHelper(this);
        // Définissez un gestionnaire de clic pour le bouton "Delete"
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Afficher un dialogue de confirmation
                AlertDialog.Builder builder = new AlertDialog.Builder(testliste.this);
                builder.setTitle("Confirmation de suppression");
                builder.setMessage("Voulez-vous vraiment supprimer toutes les données ?");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Supprimer les données de la base de données
                        DatabaseHelper dbHelper = new DatabaseHelper(testliste.this);
                        dbHelper.deleteAllTrips();

                        // Effacer les listes après la suppression
                        VariousView.clear();
                        availability.clear();
                        option.clear();

                        // Mettre à jour les adaptateurs pour refléter les listes vides
                        adapter.notifyDataSetChanged();
                        availabilityAdapter.notifyDataSetChanged();
                        optionadapter.notifyDataSetChanged();

                        // Fermer le dialogue
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Fermer le dialogue sans rien faire
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        VariousView = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, VariousView);
        listView.setAdapter(adapter);

        ListView availabilityListView = findViewById(R.id.listViewavability);
        availability = new ArrayList<>();
        availabilityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, availability);
        availabilityListView.setAdapter(availabilityAdapter);


        ListView opListView = findViewById(R.id.listoption);
        option = new ArrayList<>();
        optionadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, option);
        opListView.setAdapter(optionadapter);

        loadTrips();
        loadTrips2();
        loadTrips3();

    }

    private void loadTrips2() {

        Cursor availabilityCursor = DatabaseHelper.getAlltripsAvailabilties();

        if (availabilityCursor.moveToFirst()) {
            int dateDebutIndex = availabilityCursor.getColumnIndex(DatabaseHelper.COLUMN_DATE_DEBUT);
            int dateFinIndex = availabilityCursor.getColumnIndex(DatabaseHelper.COLUMN_DATE_FIN);

            if (dateDebutIndex != -1 && dateFinIndex != -1) {
                do {
                    String dateDebut = availabilityCursor.getString(dateDebutIndex);
                    String dateFin = availabilityCursor.getString(dateFinIndex);

                    String availabilityInfo = "Date Debut: " + dateDebut
                            + ", Date Fin: " + dateFin;

                    availability.add(availabilityInfo);
                } while (availabilityCursor.moveToNext());
            }
        }

        availabilityCursor.close();
        availabilityAdapter.notifyDataSetChanged();
    }

    private void loadTrips3() {
        Cursor cursor = DatabaseHelper.getAlltripsOptions();

        if (cursor.moveToFirst()) {
            int WifiIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_WIFI);
            int smokeIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SMOKE);
            int housseIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_PETHOUSE);
            int MachineIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_WASHINGMACHINE);
            int bedIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_BED);
            int kitchenIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_KITCHEN);


            if (WifiIndex != -1 && smokeIndex != -1 && housseIndex != -1) {
                do {
                    String Wifi = cursor.getString(WifiIndex);
                    String Smoke = cursor.getString(smokeIndex);
                    String Housse = cursor.getString(housseIndex);
                    String Machine = cursor.getString(MachineIndex);
                    String Bed = cursor.getString(bedIndex);
                    String Kitchen = cursor.getString(kitchenIndex);


                    String tripInfo = "Wifi: " + Wifi
                            + ", Smoke: " + Smoke
                            + ", Housse: " + Housse
                            + ", Machine: " + Machine
                            + ", Bed: " + Bed
                            + ", Kitchen: " + Kitchen;


                    option.add(tripInfo);
                } while (cursor.moveToNext());
            }
        }

        cursor.close();
        adapter.notifyDataSetChanged();
    }





    private void loadTrips() {
        // Replace "columnName" with the actual column names from your trips table
        Cursor cursor = DatabaseHelper.getAlltrips();

        if (cursor.moveToFirst()) {
            int constructionIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_CONSTRUCTION);
            int gardeningIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_GARDENING);
            int educatingIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_EDUCATING);
            int sportsIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SPORTS);
            int babysittingIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_BABYSITTING);
            int designerIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_DESIGNER);


            if (constructionIndex != -1 && gardeningIndex != -1 && educatingIndex != -1) {
                do {
                    String construction = cursor.getString(constructionIndex);
                    String gardening = cursor.getString(gardeningIndex);
                    String educating = cursor.getString(educatingIndex);
                    String sports = cursor.getString(sportsIndex);
                    String babysitting = cursor.getString(babysittingIndex);
                    String designer = cursor.getString(designerIndex);


                    // Build a string with trip information
                    String tripInfo = "Construction: " + construction
                            + ", Gardening: " + gardening
                            + ", Educating: " + educating
                            + ", Sports: " + sports
                            + ", Babysitting: " + babysitting
                            + ", Designer: " + designer;

                    VariousView.add(tripInfo);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }


}