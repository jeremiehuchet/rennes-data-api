package fr.dudie.keolis.client;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import fr.dudie.keolis.model.ApiResponse;
import fr.dudie.keolis.model.LineIconData;

/**
 * Handles http responses containing bike stations in json format.
 * 
 * <pre>
 * "baseurl":"http:\/\/data.keolis-rennes.com\/uploads\/tx_icsinfotrafic\/",
 * "line":[
 *   {
 *     "name":"1",
 *     "picto":"LM1.png"
 *   }
 * ]
 * </pre>
 * 
 * @author Jérémie Huchet
 */
public final class LineIconsHttpResponseHandler extends JsonResponseHandler<LineIconData> {

    @Override
    ApiResponse<LineIconData> handleJsonResponse(final InputStream inputStream) {

        final Type apiResponseType = new TypeToken<ApiResponse<LineIconData>>() {
        }.getType();
        return KeoUtils.getGsonInstance().fromJson(new InputStreamReader(inputStream),
                apiResponseType);

    }
}
