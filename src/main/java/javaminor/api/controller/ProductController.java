package javaminor.api.controller;

import com.google.gson.Gson;
import javaminor.api.domain.RestModel;
import javaminor.api.util.RestUtil;
import javaminor.domain.concrete.scanitems.Product;
import javaminor.logic.ScanItemRepository;

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
    public Response getProducts(@DefaultValue("0") @QueryParam("start") final
                                int start, @DefaultValue("10")
                                @QueryParam("limit") final int limit) {
        return RestUtil.buildReponse(
                new RestModel<>(Product.ALL,
                        start,
                        limit,
                        ScanItemRepository.getProducts(start, limit)));
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") final int id) {
        return RestUtil.buildReponse(ScanItemRepository.getItemById(id), id);
    }


    @POST
    @Path("/create")
    public Response create(@FormParam("json") String json) {
        Gson gson = new Gson();

        // TODO custom parser so discounts etc. can be added
        Product product = gson.fromJson(json, Product.class);

        boolean success = ScanItemRepository.addProduct(product);
        if (success) {
            return RestUtil.buildReponse(product.getId(), json);
        } else {
            return RestUtil.buildReponse("Failed to add product", json);
        }
    }

    @PUT
    @Path("/update")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response update(@FormParam("json") String json) {
        Gson gson = new Gson();

        // TODO custom parser so discounts etc. can be added
        Product product = gson.fromJson(json, Product.class);
        System.err.println(json);
        if (!ScanItemRepository.scanItemExists(product)) {
            return RestUtil.buildReponse("Failed to update product, id not " +
                    "found.", json);
        }

        boolean success = ScanItemRepository.updateProduct(product);
        if (success) {
            return RestUtil.buildReponse("Product updated: ", product);
        } else {
            return RestUtil.buildReponse("Failed to add product", json);
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") final int id) {
        return RestUtil.buildReponse(ScanItemRepository.setItemDisabled(id),
                id);
    }

    //
    // Extras

    @GET
    @Path("/type")
    public Response getProductByType() {
        return RestUtil.buildReponse(ScanItemRepository.getItemTypes(), null);
    }

    @GET
    @Path("/type/{type}")
    public Response getProductByType(@PathParam("type") final String type) {
        return RestUtil.buildReponse(ScanItemRepository.getItemsByType
                (ScanItemRepository.getScanItems(), type), type);
    }

    @GET
    @Path("/barcode/{code}")
    public Response getProductByCode(@PathParam("code") final String code) {
        return RestUtil.buildReponse(ScanItemRepository.getItemByCode(code),
                code);
    }

}
