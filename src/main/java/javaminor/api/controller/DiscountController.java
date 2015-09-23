package javaminor.api.controller;

import javaminor.api.domain.concrete.Discounts;
import javaminor.api.util.RestUtil;
import javaminor.domain.abs.Discount;
import javaminor.logic.DiscountRepository;
import javaminor.util.RefUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alex on 9/23/15.
 */
@Path(Discount.ALL)
@Produces({MediaType.APPLICATION_JSON})
public class DiscountController {

    @GET
    public Response getDiscounts(@DefaultValue("0") @QueryParam("start") final int start, @DefaultValue("10") @QueryParam("limit") final int limit) {
        Discounts list = new Discounts();
        if(start > limit){
            list.setPrev(RefUtil.BASE_URL + Discount.ALL + "?start=" + (start-limit) + "&limit=" + limit);
        }else{
            list.setPrev(RefUtil.BASE_URL + Discount.ALL + "?start=" + (0) + "&limit=" + limit);
        }
        list.setNext(RefUtil.BASE_URL + Discount.ALL + "?start=" + (start + limit) + "&limit=" + limit);
        list.setDiscountList(DiscountRepository.getDiscounts(start, limit));
        return RestUtil.buildReponse(list, null);
    }

    @GET
    @Path("/today")
    public Response getDiscountsToday(){

        // TODO implement discounts for dates
        return getDiscounts(0,10);
    }
}
