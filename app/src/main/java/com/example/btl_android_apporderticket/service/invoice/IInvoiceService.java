package com.example.btl_android_apporderticket.service.invoice;

import com.example.btl_android_apporderticket.handle.mycallback.IServiceCallback;
import com.example.btl_android_apporderticket.model.Invoice;
import com.example.btl_android_apporderticket.service.IGeneralService;

public interface IInvoiceService extends IGeneralService<Invoice, String> {
    void getInvoiceByUserId(String userId, IServiceCallback<Iterable<Invoice>> callback);
}
