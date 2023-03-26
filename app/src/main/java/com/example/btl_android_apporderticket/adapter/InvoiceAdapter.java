package com.example.btl_android_apporderticket.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btl_android_apporderticket.R;
import com.example.btl_android_apporderticket.model.Invoice;

import java.text.NumberFormat;
import java.util.List;

public class InvoiceAdapter extends BaseAdapter {

    private List<Invoice> listInvoices;

    public InvoiceAdapter(List<Invoice> listInvoices) {
        this.listInvoices = listInvoices;
    }


    @Override
    public int getCount() {
        if (listInvoices != null)
            return listInvoices.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return listInvoices.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listInvoices.get(i).getInvoiceId().hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewInvoice = view;
        if (viewInvoice == null) {
            viewInvoice = View.inflate(viewGroup.getContext(), R.layout.item_invoice, null);
        }
        Invoice invoice = listInvoices.get(i);
        ((TextView) viewInvoice.findViewById(R.id.tvIdInvoice)).setText("ID: " + invoice.getInvoiceId());
        ((TextView) viewInvoice.findViewById(R.id.tvCreateAt)).setText("Date: " + invoice.getCreateAt());
        ((TextView) viewInvoice.findViewById(R.id.tvStatus)).setText("Status: " + invoice.getPaymentStatus());
        ((TextView) viewInvoice.findViewById(R.id.tvMethodPayment)).setText("Method: " + invoice.getPaymentMethod());
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String formattedString = formatter.format(invoice.getTotalPrice());
        ((TextView) viewInvoice.findViewById(R.id.tvTotalPrice)).setText(formattedString);
        return viewInvoice;
    }
}
