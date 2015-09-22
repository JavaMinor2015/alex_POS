package javaminor.api.controller;

import com.google.gson.Gson;
import javaminor.domain.concrete.scanitems.Product;
import javaminor.logic.ScanItemRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Alex on 9/22/15.
 */
@Path("/products")
@Produces({MediaType.APPLICATION_JSON})
public class ProductController {

    @GET
    public Response getProducts() {
        return buildReponse(ScanItemRepository.getScanItems(), null);
    }

    @GET
    @Path("/{code: \\w+}")
    public Response getProductByCode(@PathParam("code") final String code) {
        return buildReponse(ScanItemRepository.getItemByCode(code), code);
    }

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
            return buildReponse("Product added: ",product);
        }else{
            return buildReponse("Failed to add product", json);
        }
    }

    @GET
    @Path("/type/")
    public Response getProductByType() {
        return buildReponse(ScanItemRepository.getItemTypes(), null);
    }

    @GET
    @Path("/type/{type}")
    public Response getProductByType(@PathParam("type") final String type) {
        return buildReponse(ScanItemRepository.getItemsByType(ScanItemRepository.getScanItems(), type), type);
    }

    /**
     * Builds a response around the object and parameters.
     *
     * @param o the object to jsonify
     * @param params optional parameters from the user
     * @return a Response object
     */
    private Response buildReponse(Object o, Object... params){
        Gson gson = new Gson();
        if(o==null){
            return Response.ok(gson.toJson("Product(s) not found for parameters: " + params)).build();
        }
        return Response.ok(gson.toJson(o)).build();
    }
}
