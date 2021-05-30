package adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.Citydata;
import com.example.weather.R;

import org.w3c.dom.Text;

public class CityListAdatper extends BaseAdapter {

	private Context context;
	private List<Citydata> list;
	private LayoutInflater inflater;

	public CityListAdatper(Context context, List<Citydata> list) {
		this.context = context;
		this.list = list;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if(convertView==null){
			holder=new Holder();
			convertView=inflater.inflate(R.layout.item_city_list,null);
			holder.text_city=(TextView)convertView.findViewById(R.id.text_city);
			holder.text_province=(TextView)convertView.findViewById(R.id.text_province);
			holder.text_district=(TextView)convertView.findViewById(R.id.text_district);
			convertView.setTag(holder);
		}else{
			holder=(Holder)convertView.getTag();
		}
		holder.text_city.setText(list.get(position).getCity());
		holder.text_province.setText(list.get(position).getProvince());
		holder.text_district.setText(list.get(position).getDistrict());
		return convertView;
	}

	protected class Holder{
		TextView text_city;
		TextView text_province;
		TextView text_district;
	}
}
