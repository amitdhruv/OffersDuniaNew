package ltd.platona.offersdunia;

import android.content.DialogInterface;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.ViewFlipper;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapters.BestCouponsAdapter;
import adapters.CouponsAdapter;
import model.BestOffer;
import model.Offer;

public class MainActivity extends AppCompatActivity {

    ViewFlipper flippy;
    RecyclerView mRecyclerView,mRecylerView2;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    DatabaseReference mRef2;

    private ArrayList<Offer> offersList = new ArrayList<>();
    private ArrayList<BestOffer> bestOffers=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Offers");


        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mRecylerView2=findViewById(R.id.recyclerView2);
        mRecylerView2.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecylerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mRef=mFirebaseDatabase.getReference("Offers");
        mRef2=mFirebaseDatabase.getReference("BestOffers");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot offersnapshot : dataSnapshot.getChildren()){
                    Offer offerName = new Offer();
                    offerName= offersnapshot.getValue(Offer.class);
                    offerName.appName = offersnapshot.getKey();

                    offersList.add(offerName);
                }
                Log.i("Array Size", ""+offersList.size());

                CouponsAdapter adapter=new CouponsAdapter(MainActivity.this, offersList);

                mRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot bestoffersnapshot : dataSnapshot.getChildren()){
                    BestOffer bestofferName =new BestOffer();
                    bestofferName= bestoffersnapshot.getValue(BestOffer.class);
                    bestofferName.appName1 = bestoffersnapshot.getKey();

                    bestOffers.add(bestofferName);
                }
                Log.i("Array Size", ""+bestOffers.size());

                BestCouponsAdapter adapter1=new BestCouponsAdapter(MainActivity.this, bestOffers);


                mRecylerView2.setAdapter(adapter1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        flippy = findViewById(R.id.vFlipper);
        flippy.setFlipInterval(5000);
        flippy.setInAnimation(MainActivity.this, R.anim.view_transition_in_right);
        flippy.startFlipping();
    }

//    private void firebaseSearch(String searchText) {
//        Query firebaseSearchQuery = mRef.orderByChild("search").startAt(searchText).endAt(searchText + "\uf8ff");
//
//        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
//                new FirebaseRecyclerAdapter<Model, ViewHolder>(
//                        Model.class,
//                        R.layout.row,
//                        ViewHolder.class,
//                        firebaseSearchQuery
//                ) {
//                    @Override
//                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
//                        viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription(), model.getImage());
//                    }
//                };
//        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
//    }

    @Override
    protected void onStart() {
        super.onStart();


//      FirebaseRecyclerAdapter<Model, ViewHolder>firebaseRecyclerAdapter=
//                new FirebaseRecyclerAdapter<Model, ViewHolder>(
//                        Model.class,
//                        R.layout.row,
//                        ViewHolder.class,
//                        mRef
//                ) {
//                    @Override
//                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
//
//                        viewHolder.setDetails(getApplicationContext(),model.getTitle(),model.getDescription(),model.getImage());
//
//                    }
//                };
//mRecyclerView.setAdapter(firebaseRecyclerAdapter);
//
//        FirebaseRecyclerAdapter<Model, ViewHolder>firebaseRecyclerAdapter2=
//                new FirebaseRecyclerAdapter<Model, ViewHolder>(
//                        Model.class,
//                        R.layout.row,
//                        ViewHolder.class,
//                        mRef
//                ) {
//                    @Override
//                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
//
//                        viewHolder.setDetails(getApplicationContext(),model.getTitle(),model.getDescription(),model.getImage());
//
//                    }
//                };
//
////        CouponAdapter adapter = new CouponAdapter(Model.class,R.layout.row, ViewHolder.class, mRef);
////        adapter.setListener(this);
////        mRecyclerView.setAdapter(adapter);
//        mRecylerView2.setAdapter(firebaseRecyclerAdapter2);
    }

//    @Override
//    public void onCouponClicked() {
//
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        MenuItem item= menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                firebaseSearch(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                firebaseSearch(newText);
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }


}

