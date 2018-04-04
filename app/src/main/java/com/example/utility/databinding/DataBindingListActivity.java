package com.example.utility.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.utility.R;
import com.example.utility.databinding.bean.Phone;

import java.util.ArrayList;

import static com.example.utility.BR.phone;

public class DataBindingListActivity extends AppCompatActivity {
	private ItemAdapter mItemAdapter;
	private ArrayList<Phone> mItems = new ArrayList<>();
	private int index = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rv_db);

		for (int i = 0; i<1; i++){
			Phone phone = new Phone();
			phone.setName("iphone" + index);
			phone.setOwner("Leo_" + index);
			mItems.add(phone);
			index++;
		}

		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		mItemAdapter = new ItemAdapter();
		recyclerView.setAdapter(mItemAdapter);

		findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Phone phone = new Phone();
				phone.setName("iphone " + index);
				phone.setOwner("Leo_" + index);
				mItems.add(phone);
				mItemAdapter.notifyDataSetChanged();

				index++;
			}
		});
	}

	class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

		@Override
		public long getItemId(int position) {
			return super.getItemId(position);
		}

		@Override
		public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rv_item_db, parent, false);
			ItemViewHolder holder = new ItemViewHolder(binding);
			return holder;
		}

		@Override
		public void onBindViewHolder(ItemViewHolder holder, int position) {
			holder.getBinding().setVariable(phone, mItems.get(position));
			holder.getBinding().executePendingBindings();
		}

		@Override
		public int getItemCount() {
			return mItems.size();
		}

		class ItemViewHolder extends RecyclerView.ViewHolder{
			private ViewDataBinding binding;

			public ItemViewHolder(ViewDataBinding binding){
				super(binding.getRoot());
				this.binding = binding;
			}

			public void setBinding(ViewDataBinding binding){
				this.binding = binding;
			}

			public ViewDataBinding getBinding(){
				return this.binding;
			}
		}
	}
}
