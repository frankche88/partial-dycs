package unmsm.dycs.orders.application.controllers;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import unmsm.dycs.commons.domain.specification.AbstractSpecification;
import unmsm.dycs.commons.domain.specification.Specification;
import unmsm.dycs.orders.application.OrderService;
import unmsm.dycs.orders.application.assembler.OrderAssembler;
import unmsm.dycs.orders.application.dto.OrderHeaderOutputDto;
import unmsm.dycs.orders.application.dto.OrderInputDto;
import unmsm.dycs.orders.application.dto.OrderOutputDto;
import unmsm.dycs.orders.domain.entity.Order;
import unmsm.dycs.orders.infrastructure.persistence.hibernate.specification.OrderFirstnameBySpecification;
import unmsm.dycs.orders.infrastructure.persistence.hibernate.specification.TodayOrdersSpecification;

@SwaggerDefinition(securityDefinition = @SecurityDefinition(
        apiKeyAuthDefinitions = {
                @ApiKeyAuthDefinition(key = "Bearer", in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER, name = "Bearer") 
                }
        )
)

@RolesAllowed({"admin", "member"})
@Path("/api/orders")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/api/orders")
public class OrderResource {

    private final OrderService orderService;

    private OrderAssembler orderAssembler;

    @Inject
    public OrderResource(OrderService orderService, OrderAssembler assembler) {

        this.orderAssembler = assembler;
        this.orderService = orderService;
    }

    @POST
    @UnitOfWork
    @ApiOperation(value = "Add order", notes = "Agregate new Order", authorizations = {
            @Authorization(value = "Bearer") })
    public Order create(@Valid OrderInputDto orderDto) {

        Order order = orderAssembler.toEntity(orderDto);

        return orderService.create(order);
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    @ApiOperation(value = "Remove order", notes = "Remove Order by id order", authorizations = {
            @Authorization(value = "Bearer") })
    public void delete(@PathParam("id") Long id) {
        orderService.delete(id);
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    @ApiOperation(value = "show order", notes = "Show Order by id order", authorizations = {
            @Authorization(value = "Bearer") })
    public OrderOutputDto ordersList(@QueryParam("id") Long id) {

        Order order = orderService.orderById(id);

        return orderAssembler.toDto(order);
    }

    @GET
    @UnitOfWork
    @ApiOperation(value = "List orders", notes = "List all orders", authorizations = {
            @Authorization(value = "Bearer") })
    public List<OrderHeaderOutputDto> ordersList() {

        return orderAssembler.toHeaderDto(orderService.findAll());

    }
    


    @GET
    @Path("/_today")
    @UnitOfWork
    @ApiOperation(value = "List orders", notes = "List all orders", authorizations = {
            @Authorization(value = "Bearer") })
    public List<OrderHeaderOutputDto> ordersTodayList(@QueryParam("firstname") String firstname) {
    	
    	Specification<Order> specification = getOrderSpecification(firstname);

        return orderAssembler.toHeaderDto(orderService.finBySpecification(specification));

    }
    private Specification<Order> getOrderSpecification(String firstname) {
		AbstractSpecification<Order> specification = new TodayOrdersSpecification();
		
		if(StringUtils.isBlank(firstname)) {
			return specification;
		}
		
		return specification = specification.and(new OrderFirstnameBySpecification(firstname));
		
	}
}
