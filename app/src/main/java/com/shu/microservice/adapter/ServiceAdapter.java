package com.shu.microservice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shu.microservice.R;
import com.shu.microservice.model.ServiceItem;
import com.shu.microservice.util.AppContext;
import com.shu.microservice.util.AppImageLoader;
import com.shu.microservice.util.MImageLoader;
import com.shu.microservice.util.TimeFormatUtil;

import java.util.List;

/**
 * Created by wxl on 2016/2/24.
 */
public class ServiceAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    //数据源
    private List<ServiceItem> items;
    public ServiceAdapter(List<ServiceItem> items) {
        this.items = items;
        layoutInflater = LayoutInflater.from(AppContext.getAppContext());
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(null == convertView){
            convertView = layoutInflater.inflate(R.layout.service_item,null);
            viewHolder = new ViewHolder();
            viewHolder.pic = (ImageView) convertView.findViewById(R.id.pic);
            viewHolder.author = (TextView) convertView.findViewById(R.id.author);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.createTime = (TextView) convertView.findViewById(R.id.createTime);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ServiceItem currentItem = items.get(position);
        String imageUrl = currentItem.getPicUrl();
        //viewHolder.pic.setImageResource(R.mipmap.ic_launcher);
        viewHolder.pic.setTag(imageUrl);
//         new MImageLoader().showImageByThread(viewHolder.pic, imageUrl);
//        new MImageLoader().showImageByTask(viewHolder.pic,imageUrl);
        new AppImageLoader(viewHolder.pic,imageUrl).loadImage();;
        viewHolder.title.setText(currentItem.getTitle());
        viewHolder.author.setText(currentItem.getAuthor());
        viewHolder.createTime.setText(currentItem.getCreateTime());
        return convertView;
    }

    class ViewHolder{
        public TextView title,author,createTime;
        public ImageView pic;
    }
}
