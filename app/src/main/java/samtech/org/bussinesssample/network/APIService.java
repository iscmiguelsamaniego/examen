package samtech.org.bussinesssample.network;

import static samtech.org.bussinesssample.Utils.Constants.KEY_GROCERY_STORES;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import samtech.org.bussinesssample.network.pokos.GroceryResponse;
import samtech.org.bussinesssample.network.pokos.GroceryRequest;

public interface APIService {
    @POST(KEY_GROCERY_STORES)
    Call<GroceryResponse> getGroceryStores(@Body GroceryRequest request);
}
