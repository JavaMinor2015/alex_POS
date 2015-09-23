package javaminor.api.controller;

import javaminor.api.domain.concrete.Sales;
import javaminor.api.util.RestUtil;
import javaminor.domain.abs.Discount;
import javaminor.domain.concrete.transactions.Sale;
import javaminor.logic.TransactionRepository;
import javaminor.util.RefUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alex on 9/23/15.
 */
@Path(Sale.ALL)
@Produces({MediaType.APPLICATION_JSON})
public class SalesController {

    @GET
    public Response getDiscounts(@DefaultValue("0") @QueryParam("start") final int start, @DefaultValue("10") @QueryParam("limit") final int limit) {
        Sales list = new Sales();
        if(start > limit){
            list.setPrev(RefUtil.BASE_URL + Discount.ALL + "?start=" + (start-limit) + "&limit=" + limit);
        }else{
            list.setPrev(RefUtil.BASE_URL + Discount.ALL + "?start=" + (0) + "&limit=" + limit);
        }
        list.setNext(RefUtil.BASE_URL + Discount.ALL + "?start=" + (start + limit) + "&limit=" + limit);
        list.setSaleList(TransactionRepository.getSales(start, limit));
        return RestUtil.buildReponse(list, null);
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") final int id){
        return RestUtil.buildReponse(TransactionRepository.getItemById(id), id);
    }
}
