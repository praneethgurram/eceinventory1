package com.praneethgurramgmail.eceinventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by praneeth on 4/8/2017.
 */

public class ItemAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public ItemAdapter(Context context, int resources){
        super( context,resources);
    }
    @Override
    public void add(Object object){
        super.add(object);
        list.add(object);
    }
    @Override
    public int getCount(){
        return list.size();
    }
    @Override
    public Object getItem(int position){
        return list.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row;
        row=convertView;
        Itemholder itemholder;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            row=layoutInflater.inflate( R.layout.rowlayout,parent,false );

            itemholder = new Itemholder ();
            Itemholder.textView =(TextView) row.findViewById(R.id.textView );
            row.setTag (itemholder);

        }
        else {
            itemholder = (Itemholder)row.getTag();
        }
            Items items = (Items)this.getItem (position);
            itemholder.textView.setText (items.getOwner ());
       /* itemholder.textView.setText (items.getOrgnCode ());
        itemholder.textView.setText (items.getOrgnTitle ());
        itemholder.textView.setText (items.getRoom ());
        itemholder.textView.setText (items.getBldg ());
        itemholder.textView.setText (items.getSortRoom ());
        itemholder.textView.setText (items.getPtag ());
        itemholder.textView.setText (items.getManufacturer ());
        itemholder.textView.setText (items.getModel ());
        itemholder.textView.setText (items.getSN ());
        itemholder.textView.setText (items.getDescription ());
        itemholder.textView.setText (items.getPO ());
        itemholder.textView.setText (items.getAcqDate ());
        itemholder.textView.setText (items.getAmt ());
        itemholder.textView.setText (items.getSchevYear ());
        itemholder.textView.setText (items.getOwnership ());
        itemholder.textView.setText (items.getTagType ());
        itemholder.textView.setText (items.getAssetType ());
        itemholder.textView.setText (items.getAtypTitle ());
        itemholder.textView.setText (items.getCondition ());
        itemholder.textView.setText (items.getLastInvDate ());
        itemholder.textView.setText (items.getDesignation ());

            */

        return row;
    }


    public static class Itemholder{
        static TextView textView;
    }
}
