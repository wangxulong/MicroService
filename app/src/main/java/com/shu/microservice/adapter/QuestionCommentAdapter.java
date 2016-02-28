package com.shu.microservice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shu.microservice.R;
import com.shu.microservice.model.CommentItem;
import com.shu.microservice.model.QuestionItem;
import com.shu.microservice.util.AppContext;
import com.shu.microservice.util.TimeFormatUtil;

import java.util.List;

/**
 * Created by wxl on 2016/2/25.
 */
public class QuestionCommentAdapter extends BaseAdapter {
    LayoutInflater layoutInflater = null;
    private List<CommentItem> items;

    public QuestionCommentAdapter(List<CommentItem> items) {
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
            convertView = layoutInflater.inflate(R.layout.question_comment_item,null);
            viewHolder.content = (TextView) convertView.findViewById(R.id.question_comment_content);
            viewHolder.author = (TextView) convertView.findViewById(R.id.question_comment_author);
            viewHolder.createTime = (TextView) convertView.findViewById(R.id.question_comment_createTime);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CommentItem currentItem = items.get(position);
        viewHolder.content.setText(currentItem.getContent());
        viewHolder.author.setText(currentItem.getUserName());
        viewHolder.createTime.setText(TimeFormatUtil.getFormatStr(null, currentItem.getCreateTime()));
        return convertView;
    }
    class ViewHolder{
        public TextView content,author,createTime;
    }
}
