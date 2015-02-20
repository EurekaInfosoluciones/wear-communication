package com.eurekainfosoluciones.wearcom;

import com.eurekainfosoluciones.wearcom.model.DataRequest;
import com.google.android.gms.wearable.PutDataRequest;

public interface PutDataRequester {

    void putData(DataRequest request);

}
