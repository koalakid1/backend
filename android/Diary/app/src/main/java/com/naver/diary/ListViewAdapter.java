package com.naver.diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemArrayList;

    public ListViewAdapter(ArrayList<ListViewItem> arrayList){
        if (arrayList == null){
            this.listViewItemArrayList = new ArrayList<>();
        }else{
            this.listViewItemArrayList = arrayList;
        }
    }

    @Override
    public int getCount() {return listViewItemArrayList.size();}

    @Override
    public Object getItem(int position) {return listViewItemArrayList.get(position);}

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView dateTextView = (TextView) convertView.findViewById(R.id.textViewDate);
        TextView contentTextView = (TextView) convertView.findViewById(R.id.textViewContent);

        ListViewItem listViewItem = listViewItemArrayList.get(pos);

        dateTextView.setText(listViewItem.getDate());
        contentTextView.setText(listViewItem.getContent());

        return convertView;
    }

    public void addItem(String date, String content){
        ListViewItem item = new ListViewItem();

        item.setDate(date);
        item.setContent(content);

        listViewItemArrayList.add(item);
    }
}
