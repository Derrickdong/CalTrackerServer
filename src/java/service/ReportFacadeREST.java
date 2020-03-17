/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Trakcer.Report;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
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
@Path("trakcer.report")
public class ReportFacadeREST extends AbstractFacade<Report> {

    @PersistenceContext(unitName = "CalorieTrackerPU")
    private EntityManager em;

    public ReportFacadeREST() {
        super(Report.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Report entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Report entity) {
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
    public Report find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    public List<Report> findByDate(@PathParam("date") String recievedDate){
        Query query = em.createNamedQuery("Report.findByDate");
        Date date = dateConverter(recievedDate);
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    @GET
    @Path("findByTotalcaloriesconsumed/{totalcaloriesconsumed}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findByTotalcaloriesconsumed(@PathParam("totalcaloriesconsumed") int totalcaloriesconsumed){
        Query query = em.createNamedQuery("Report.findByTotalcaloriesconsumed");
        query.setParameter("totalcaloriesconsumed", totalcaloriesconsumed);
        return query.getResultList();
    }
    
    @GET
    @Path("findByTotalcaloriesburrend/{totalcaloriesburrend}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findByTotalcaloriesburrend(@PathParam("totalcaloriesburrend") int totalcaloriesburrend){
        Query query = em.createNamedQuery("Report.findByTotalcaloriesburrend");
        query.setParameter("totalcaloriesburrend", totalcaloriesburrend);
        return query.getResultList();
    }
    
    @GET
    @Path("findByTotalsteps/{totalsteps}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findByTotalsteps(@PathParam("totalsteps") int totalsteps){
        Query query = em.createNamedQuery("Report.findByTotalsteps");
        query.setParameter("totalsteps", totalsteps);
        return query.getResultList();
    }
    
    @GET
    @Path("findBySetcaloriegoal/{setcaloriegoal}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findBySetcaloriegoal(@PathParam("setcaloriegoal") int setcaloriegoal){
        Query query = em.createNamedQuery("Report.findBySetcaloriegoal");
        query.setParameter("setcaloriegoal", setcaloriegoal);
        return query.getResultList();
    }
    
    //task5a
    @GET
    @Path("generateSingleDayReport/{userid}/{date}")
    @Produces({MediaType.APPLICATION_JSON}) 
    public Object generateSingleDayReport(@PathParam("userid") int userid, @PathParam("date") String receivedDate){
        List<Report> list = findByDate(receivedDate);
        Report report = null;
        for(Report r:list){
            if (r.getUserid().getUserid() == userid){
                report = r;
            }
        }
        JsonObject jo = Json.createObjectBuilder().add("total calories consumed", report.getTotalcaloriesconsumed()).add("total calories burned", report.getTotalcaloriesburrend())
                .add("remaining calories", report.getSetcaloriegoal() - report.getTotalcaloriesburrend()).build();
        return jo;
    }
    
    //task5b
    @GET
    @Path("generatePeriodReport/{userid}/{startdate}/{enddate}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object generatePeriodReport(@PathParam("userid") int userid, @PathParam("startdate") String date1, @PathParam("enddate") String date2){
        Date startdate = dateConverter(date1);
        Date enddate = dateConverter(date2);
        Date date = new Date();
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        TypedQuery q = em.createQuery("SELECT r FROM Report r WHERE r.userid.userid = :userid AND r.date BETWEEN :start AND :end",Report.class);
        q.setParameter("userid", userid);
        q.setParameter("end", enddate);
        q.setParameter("start", startdate);
        List<Report> list = q.getResultList();
        double totalCaloriesBurned = 0;
        double totalCaloriesConsumed = 0;
        int totalStepsTaken = 0;
        for (Report r : list){
            date = r.getDate();
            totalCaloriesBurned = totalCaloriesBurned + r.getTotalcaloriesburrend();
            totalCaloriesConsumed = totalCaloriesConsumed + r.getTotalcaloriesconsumed();
            totalStepsTaken = totalStepsTaken + r.getTotalsteps();
        }
        JsonObject jo = Json.createObjectBuilder().add("date", simple.format(date)).add("total calories burned", totalCaloriesBurned).add("total calories consumed", totalCaloriesConsumed).add("total steps taken", totalStepsTaken).build();
        return jo;
    }
    
    @GET
    @Path("generatePeriod/{userid}/{startdate}/{enddate}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Report> generatePeriod(@PathParam("userid") int userid, @PathParam("startdate") String date1, @PathParam("enddate") String date2){
        Date startdate = dateConverter(date1);
        Date enddate = dateConverter(date2);
        Date date = new Date();
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        TypedQuery q = em.createQuery("SELECT r FROM Report r WHERE r.userid.userid = :userid AND r.date BETWEEN :start AND :end",Report.class);
        q.setParameter("userid", userid);
        q.setParameter("end", enddate);
        q.setParameter("start", startdate);
        List<Report> list = q.getResultList();
        return list;
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
