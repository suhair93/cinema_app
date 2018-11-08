package com.cinema_app.customer;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.OnCardFormSubmitListener;
import com.braintreepayments.cardform.utils.CardType;
import com.braintreepayments.cardform.view.CardEditText;
import com.braintreepayments.cardform.view.CardForm;
import com.braintreepayments.cardform.view.SupportedCardTypesView;
import com.cinema_app.R;
import com.cinema_app.models.Keys;
import com.cinema_app.models.Reservation;
import com.cinema_app.models.Seat;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class visacard extends AppCompatActivity implements OnCardFormSubmitListener,
        CardEditText.OnCardTypeChangedListener {
    ProgressDialog progressDialog;
    DatabaseReference ref;
    FirebaseDatabase database;
    Bundle bundle;
    private static final CardType[] SUPPORTED_CARD_TYPES = { CardType.VISA, CardType.MASTERCARD };

    private SupportedCardTypesView mSupportedCardTypesView;
    SharedPreferences prefs =  getSharedPreferences(Keys.KEY_ID, MODE_PRIVATE);
     String  email_customer = prefs.getString(Keys.KEY_CUSTOMER,"");
    protected CardForm mCardForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_payment);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));

        }

        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

            mSupportedCardTypesView = findViewById(R.id.supported_card_types);
        mSupportedCardTypesView.setSupportedCardTypes(SUPPORTED_CARD_TYPES);

        mCardForm = findViewById(R.id.card_form);
        mCardForm.cardRequired(true)
                .maskCardNumber(true)
                .maskCvv(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("Make sure SMS is enabled for this mobile number")
                .actionLabel( "purchase")
                .setup(this);
        mCardForm.setOnCardFormSubmitListener(this);
        mCardForm.setOnCardTypeChangedListener(this);

        // Warning: this is for development purposes only and should never be done outside of this example app.
        // Failure to set FLAG_SECURE exposes your app to screenshots allowing other apps to steal card information.
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);

        bundle = getIntent().getExtras();
        if(bundle != null){
            bundle.get("details");
            bundle.get("id");
            bundle.get("place");
            bundle.get("timeShow");
            bundle.get("length");
            bundle.getParcelableArrayList("seat");




        }

        Button payment = findViewById(R.id.payment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(visacard.this);
                builder.setMessage("Are you sure of the booking?");
                builder.setCancelable(true)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Reservation reservation = new Reservation();
                                reservation.setDetails(bundle.getString("details"));
                                reservation.setId( bundle.getString("id"));
                                reservation.setLength(bundle.getString("length"));
                                reservation.setPrice(bundle.getString("price"));
                                reservation.setTimeShow(bundle.getString("timeShow"));
                                reservation.setSeats(bundle.<Seat>getParcelableArrayList("seat"));
                                reservation.setEmail_customer(email_customer);

                                ref.child("reservation").push().setValue(reservation);
//                                Toast.makeText(getBaseContext(), "Thank you dear for dealing with us", Toast.LENGTH_LONG).show();
//                                Intent i = new Intent(visacard.this,MainActivity.class);
//                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                startActivity(i);
//                                finish();
                                //System.exit(0);
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertdialog = builder.create();
                alertdialog.show();
            }
        });
    }

    @Override
    public void onCardTypeChanged(CardType cardType) {
        if (cardType == CardType.EMPTY) {
            mSupportedCardTypesView.setSupportedCardTypes(SUPPORTED_CARD_TYPES);
        } else {
            mSupportedCardTypesView.setSelected(cardType);
        }
    }

    @Override
    public void onCardFormSubmit() {
        if (mCardForm.isValid()) {
            Toast.makeText(this, R.string.valid, Toast.LENGTH_SHORT).show();
        } else {
            mCardForm.validate();
            Toast.makeText(this, R.string.invalid, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.card_io_item) {
            mCardForm.scanCard(this);
            return true;
        }

        return false;
    }
}
