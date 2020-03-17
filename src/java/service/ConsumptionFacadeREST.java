/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Trakcer.Consumption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author xdon0010
 */
@Stateless
@Path("trakcer.consumption")
public class ConsumptionFacadeREST extends AbstractFacade<Consumption> {

    @PersistenceContext(unitName = "CalorieTrackerPU")
    private EntityManager em;

    public ConsumptionFacadeREST() {
        super(Consumption.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Consumption entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Consumption entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Consumption find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("findByDate/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findByDate(@PathParam("date") String recievedDate){
        Query query = em.createNamedQuery("Consumption.findByDate");
        Date date = dateConverter(recievedDate);
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    @GET
    @Path("findByQuantity/{quantity}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findByQuantity(@PathParam("quantity") int quantity){
        Query query = em.createNamedQuery("Consumption.findByQuantity");
        query.setParameter("quantity", quantity);
        return query.getResultList();
    }
    
    //task3c
    @GET
    @Path("findByUseridANDDate/{userid}/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findByUseridANDDate(@PathParam("userid") int userid, @PathParam("date") String receivedDate){
        TypedQuery<Consumption> q = em.createQuery("SELECT c FROM Consumption c WHERE c.userid.userid = :userid AND c.date = :date", Consumption.class);
        Date date = dateConverter(receivedDate);
        q.setParameter("userid", userid);
        q.setParameter("date", date);
        return q.getResultList();
    }
    
    @GET
    @Path("totalCaloriesConsumedForTheDay/{userid}/{date}")
    @Produces(MediaType.TEXT_PLAIN)
    public double totalCaloriesConsumedForTheDay(@PathParam("userid") int userid, @PathParam("date") String receivedDate){
        List<Consumption> list = findByUseridANDDate(userid, receivedDate);
        double totalCalories = 0;
        for (Consumption c:list){
            totalCalories = totalCalories + c.getFood().getCalorieamount() * c.getQuantity();
        }
        return totalCalories;
    }
    
    public Date dateConverter(String receivedDate){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(receivedDate);
        } catch (ParseException ex) {
            Logger.getLogger(ConsumptionFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
    
}
