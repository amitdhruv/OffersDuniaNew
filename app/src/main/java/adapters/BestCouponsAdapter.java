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
import model.BestOffer;
import model.Offer;

public class BestCouponsAdapter extends RecyclerView.Adapter<BestCouponsAdapter.CouponHolder>{

    Context ctx;
    ArrayList<BestOffer>bestOffers;

    public BestCouponsAdapter(Context ctx, ArrayList<BestOffer> bestOffers){
        this.ctx=ctx;
        this.bestOffers=bestOffers;
    }

    @NonNull
    @Override
    public BestCouponsAdapter.CouponHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(ctx).inflate(R.layout.row1, viewGroup, false);
        BestCouponsAdapter.CouponHolder holder=new BestCouponsAdapter.CouponHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CouponHolder couponHolder, int i) {
        BestOffer data = bestOffers.get(i);

        couponHolder.mDetailTv.setText(data.description1);
        couponHolder.mTitleView.setText(data.title1);
        Picasso.get().load(data.image1).into(couponHolder.mImageTv);
    }
    @Override
    public int getItemCount() {
        return bestOffers.size();
    }

    public class CouponHolder extends RecyclerView.ViewHolder {
        View mView;

        TextView mTitleView;
        TextView mDetailTv;
        ImageView mImageTv;

        public CouponHolder(View itemView) {
            super(itemView);

            mView = itemView;
            mTitleView = mView.findViewById(R.id.rTitleTv1);
            mDetailTv = mView.findViewById(R.id.rDescriptionTv1);
            mImageTv = mView.findViewById(R.id.rImageView1);
        }
    }


}
