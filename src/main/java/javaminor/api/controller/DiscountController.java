package javaminor.api.controller;

import javaminor.api.domain.RestModel;
import javaminor.api.util.RestUtil;
import javaminor.domain.abs.Discount;
import javaminor.logic.DiscountRepository;

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
        return RestUtil.buildReponse(
                new RestModel<>(Discount.ALL,
                        start,
                        limit,
                        DiscountRepository.getDiscounts(start, limit)));
    }

    @GET
    @Path("/today")
    public Response getDiscountsToday(){

        // TODO implement discounts for dates
        return getDiscounts(0,10);
    }
}
