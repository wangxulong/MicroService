package com.shu.microservice.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shu.microservice.R;
import com.shu.microservice.model.QuestionItem;
import com.shu.microservice.util.AppContext;
import com.shu.microservice.util.TimeFormatUtil;

import java.util.List;

/**
 * Created by wxl on 2016/2/25.
 */
public class QuestionAdapter extends BaseAdapter {
    LayoutInflater layoutInflater = null;
    private List<QuestionItem> items;

    public QuestionAdapter(List<QuestionItem> items) {
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
        if(null==convertView){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.question_item,null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.question_title);
            viewHolder.author = (TextView) convertView.findViewById(R.id.question_author);
            viewHolder.createTime = (TextView) convertView.findViewById(R.id.question_createTime);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        QuestionItem currentItem = items.get(position);
        viewHolder.title.setText(currentItem.getTitle());
        viewHolder.author.setText(currentItem.getAuthor());
        viewHolder.createTime.setText( currentItem.getCreateTime());
        return convertView;
    }
    class ViewHolder{
        public TextView title,author,createTime;
    }
}
