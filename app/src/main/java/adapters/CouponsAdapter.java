package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ltd.platona.offersdunia.R;
import model.Offer;

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.CouponHolder>{

    Context context;
    ArrayList<Offer> offersList;

    public CouponsAdapter(Context context, ArrayList<Offer> offersList){
        this.context = context;
        this.offersList = offersList;
    }
//
    @NonNull
    @Override
    public CouponHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.row, viewGroup, false);
        CouponHolder holder=new CouponHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CouponHolder couponHolder, int i) {
        Offer data = offersList.get(i);

        couponHolder.mDetailTv.setText(data.description);
        couponHolder.mTitleView.setText(data.title);
        Picasso.get().load(data.image).into(couponHolder.mImageTv);
    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    public class CouponHolder extends RecyclerView.ViewHolder {
        View mView;

        TextView mTitleView;
        TextView mDetailTv;
        ImageView mImageTv;

        public CouponHolder(View itemView) {
            super(itemView);

            mView = itemView;
            mTitleView = mView.findViewById(R.id.rTitleTv);
            mDetailTv = mView.findViewById(R.id.rDescriptionTv);
            mImageTv = mView.findViewById(R.id.rImageView);
        }
    }


}
