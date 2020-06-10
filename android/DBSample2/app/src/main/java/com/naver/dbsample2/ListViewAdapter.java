package com.naver.dbsample2;

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
    public int getCount() {
        return listViewItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView nameTextView = (TextView) convertView.findViewById(R.id.textViewName);
        TextView numTextView = (TextView) convertView.findViewById(R.id.textViewNum);

        ListViewItem listViewItem = listViewItemArrayList.get(pos);

        nameTextView.setText(listViewItem.getName());
        numTextView.setText(Integer.toString(listViewItem.getNum()));

        return convertView;
    }

    public void addItem(String name, int num){
        ListViewItem item = new ListViewItem();

        item.setName(name);
        item.setNum(num);

        listViewItemArrayList.add(item);
    }
}
