package javaminor.api.controller;

import com.google.gson.Gson;
import javaminor.api.domain.concrete.Products;
import javaminor.api.util.RestUtil;
import javaminor.domain.concrete.scanitems.Product;
import javaminor.logic.ScanItemRepository;
import javaminor.util.RefUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Alex on 9/22/15.
 */
@Path(Product.ALL)
@Produces({MediaType.APPLICATION_JSON})
public class ProductController {

    @GET
    public Response getProducts(@DefaultValue("0") @QueryParam("start") final int start, @DefaultValue("10") @QueryParam("limit") final int limit) {
        Products list = new Products();
        if(start > limit){
            list.setPrev(RefUtil.BASE_URL + Product.ALL + "?start=" + (start-limit) + "&limit=" + limit);
        }else{
            list.setPrev(RefUtil.BASE_URL + Product.ALL + "?start=" + (0) + "&limit=" + limit);
        }
        list.setNext(RefUtil.BASE_URL + Product.ALL + "?start=" + (start+limit) + "&limit=" + limit);
        list.setProductList(ScanItemRepository.getProducts(start, limit));
        return RestUtil.buildReponse(list, null);
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") final int id){
        return RestUtil.buildReponse(ScanItemRepository.getItemById(id), id);
    }

//    @GET
//    @Path("/barcode/{code}")
//    public Response getProductByCode(@PathParam("code") final String code) {
//        return RestUtil.buildReponse(ScanItemRepository.getItemByCode(code), code);
//    }

    @POST
    @Path("/create")
    public Response create(@FormParam("json") String json) {
        Gson gson = new Gson();

        //Register an InstanceCreator with Gson for this type may fix this problem. Discount
        Product product = gson.fromJson(json, Product.class);
        if(ScanItemRepository.scanItemExists(product)){
            // go to put
        }
        boolean success = ScanItemRepository.addProduct(product);
        if(success){
            return RestUtil.buildReponse("Product added: ", product.getId());
        }else{
            return RestUtil.buildReponse("Failed to add product", json);
        }
    }


    @GET
    @Path("/type")
    public Response getProductByType() {
        return RestUtil.buildReponse(ScanItemRepository.getItemTypes(), null);
    }

    @GET
    @Path("/type/{type}")
    public Response getProductByType(@PathParam("type") final String type) {
        return RestUtil.buildReponse(ScanItemRepository.getItemsByType(ScanItemRepository.getScanItems(), type), type);
    }


}
