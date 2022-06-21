package com.example.tindertec;

import android.graphics.Path;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tindertec.R.layout;


import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import com.example.tindertec.adapter.home.CardStackAdapter;
import com.example.tindertec.adapter.home.CardStackCallback;
import com.example.tindertec.model.ItemModel;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;

import static com.yuyakaido.android.cardstackview.Direction.*;


public class HomeFragment extends Fragment{

    private static final String TAG = HomeFragment.class.getSimpleName();
    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    private ImageButton imageButton;
    private ImageButton imageButton2;


    public HomeFragment() {
        // Required empty public constructor

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        //like(view);
        //disLike(view);
        //CardStackView cardStackView = view.findViewById(R.id.card_stack_view);


        return view;
    }

    private String nombre;

    private void init(View view){
        CardStackView cardStackView = view.findViewById(R.id.card_stack_view);

        manager = new CardStackLayoutManager(getContext(), new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {

                Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + " d=" + direction);
                if (direction == Right){

                    Toast.makeText(getContext(), "Me gusta" + nombre , Toast.LENGTH_SHORT).show();
                }
                if (direction == Left){
                    Toast.makeText(getContext(), "No me gusta" + nombre, Toast.LENGTH_SHORT).show();
                }

                //Paginating
                if(manager.getTopPosition() == adapter.getItemCount() - 5){
                   paginate();
                }
            }

            @Override
            public void onCardRewound() {
                //Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                //Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardAppeared(View view, int position) {
                //TextView tv = view.findViewById(R.id.item_name);
                //Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                nombre =  tv.getText().toString();
                //Toast.makeText(getContext(), "Nombre: " + tv.getText() , Toast.LENGTH_SHORT).show();
                //Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.getText());

            }
        });
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new CardStackAdapter(addList());
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());


    }


    private void paginate() {
        List<ItemModel> old = adapter.getItems();
        List<ItemModel> baru = new ArrayList<>(addList());
        CardStackCallback callback = new CardStackCallback(old, baru);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callback);
        adapter.setItems(baru);
        hasil.dispatchUpdatesTo(adapter);
    }


    private List<ItemModel> addList() {
        List<ItemModel> items = new ArrayList<>();
        items.add(new ItemModel(R.drawable.sample1, "Markonah", "24", "Jember"));
        items.add(new ItemModel(R.drawable.sample2, "Marpuah", "20", "Malang"));
        items.add(new ItemModel(R.drawable.sample3, "Sukijah", "27", "Jonggol"));
        items.add(new ItemModel(R.drawable.sample4, "Markobar", "19", "Bandung"));
        items.add(new ItemModel(R.drawable.sample5, "Marmut", "25", "Hutan"));


        return items;
    }

    /*
    private void like(View view){
        CardStackView cardStackView = view.findViewById(R.id.card_stack_view);

        imageButton2 = view.findViewById(R.id.imageButton);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = view.findViewById(R.id.item_name);
                Toast.makeText(getContext(),"No me gusta "+ tv.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void disLike(View view){
        imageButton = view.findViewById(R.id.imageButton2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(getContext(),"Me gusta",Toast.LENGTH_SHORT).show();
            }
        });
    }
*/

}