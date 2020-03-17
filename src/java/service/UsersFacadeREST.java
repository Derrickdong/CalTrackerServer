 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Trakcer.Users;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
@Path("trakcer.users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "CalorieTrackerPU")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Users entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Users entity) {
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
    public Users find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("findByName/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findByName(@PathParam("name") String name){
        Query query = em.createNamedQuery("Users.findByName");
        query.setParameter("name", name);
        return query.getResultList();
    }
    
    @GET
    @Path("findBySurname/{surname}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findBySurname(@PathParam("surname") String surname){
        Query query = em.createNamedQuery("Users.findBySurname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }
    
    @GET
    @Path("findByEmail/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findByEmail(@PathParam("email") String email){
        Query query = em.createNamedQuery("Users.findByEmail");
        query.setParameter("email", email);
        return query.getResultList();
    }
    
    @GET
    @Path("findByDob/{dob}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findByDob(@PathParam("dob") String recievedDate){
        Query query = em.createNamedQuery("Users.findByDob");
        Date dob = dateConverter(recievedDate);
        query.setParameter("dob", dob);
        return query.getResultList();
    }
    
    @GET
    @Path("findByHeight/{height}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findByHeight(@PathParam("height") int height){
        Query query = em.createNamedQuery("Users.findByHeight");
        query.setParameter("height", height);
        return query.getResultList();
    }
    
    @GET
    @Path("findByWeight/{weight}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findByWeight(@PathParam("weight") int weight){
        Query query = em.createNamedQuery("Users.findByWeight");
        query.setParameter("weight", weight);
        return query.getResultList();
    }
    
    @GET
    @Path("findByGender/{gender}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findByGender(@PathParam("gender") String gender){
        Query query = em.createNamedQuery("Users.findByGender");
        query.setParameter("gender", gender.charAt(0));
        return query.getResultList();
    }
    
    @GET
    @Path("findByAddress/{address}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findByAddress(@PathParam("address") String address){
        Query query = em.createNamedQuery("Users.findByAddress");
        query.setParameter("address", address);
        return query.getResultList();
    }
    
    @GET
    @Path("findByPostcode/{postcode}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findByPostcode(@PathParam("postcode") String postcode){
        Query query = em.createNamedQuery("Users.findByPostcode");
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }
    
    @GET
    @Path("findByLevelofactivity/{levelofactivity}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findByLevelofactivity(@PathParam("levelofactivity") int levelofactivity){
        Query query = em.createNamedQuery("Users.findByLevelofactivity");
        query.setParameter("levelofactivity", levelofactivity);
        return query.getResultList();
    }
    
    @GET
    @Path("findByStepspermile/{stepspermile}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findByStepspermile(@PathParam("stepspermile") int stepspermile){
        Query query = em.createNamedQuery("Users.findByStepspermile");
        query.setParameter("stepspermile", stepspermile);
        return query.getResultList();
    }
    
    //combined attributes task3b
    @GET
    @Path("findByNameANDSurname/{name}/{surname}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findByNameANDSurname (@PathParam("name") String name, @PathParam("surname") String surname){
        TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.name = :name AND u.surname = :surname", Users.class);
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        return query.getResultList();
    }
    
    //task3d
    @GET
    @Path("findBySignupdateANDEmail/{signupdate}/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findBySugnupdateANDEmail(@PathParam("signupdate") String receivedDate, @PathParam("email") String email){
        Query query = em.createNamedQuery("Credential.findBySignupdateANDEmail");
        Date signupdate = dateConverter(receivedDate);
        query.setParameter("signupdate", signupdate);
        query.setParameter("email", email);
        return query.getResultList();
    }
    
    //task4a
    @GET
    @Path("caculateCaloriesBurnedPerStep/{userid}")
    @Produces(MediaType.TEXT_PLAIN)
    public double caculateCaloriesBurnedPerStep(@PathParam("userid") int userid){
        Users user = find(userid);
        int stepsPerMile = user.getStepspermile();
        double weightInPound =  user.getWeight() * 2.2046;
        double caloriesBurnedPerMile = weightInPound * 0.49;
        double caloriesBurnedPerStep = caloriesBurnedPerMile / stepsPerMile;
        return caloriesBurnedPerStep;
    }
    
    //task4b
    @GET
    @Path("caculateBMR/{userid}")
    @Produces(MediaType.TEXT_PLAIN)
    public double caculateBMR(@PathParam("userid") int userid){
        Users user = find(userid);
        double height = user.getHeight();
        double weight = user.getWeight();
        Date date = new Date();
        int age = Period.between(dateConverter(user.getDob()), dateConverter(date)).getYears();
        double bmr = 0;
        if (user.getGender() == 'F'){
            bmr = (9.563 * weight) + (1.85 * height) - (4.676 * age) + 655.1;
        }
        else
            bmr = (13.75 * weight) + (5.003 * height) - (6.755 * age) + 66.5;
        return bmr;
    }
    
    public LocalDate dateConverter(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    //task4c
    @GET
    @Path("caculateTotalDailyCalories/{userid}")
    @Produces(MediaType.TEXT_PLAIN)
    public double caculateTotalDailyCalories(@PathParam("userid")int userid){
        Users user = find(userid);
        double bmr = caculateBMR(userid);
        int activityLevel = user.getLevelofactivity();
        double totalCalories = 0;
        switch (activityLevel){
            case 1:
                totalCalories = bmr * 1.2;
                break;
            case 2:
                totalCalories = bmr * 1.375;
                break;
            case 3:
                totalCalories = bmr * 1.55;
                break;
            case 4:
                totalCalories = bmr * 1.725;
                break;
            case 5:
                totalCalories = bmr * 1.9;
                break;
        }
        return totalCalories;
    }
    
    @GET
    @Path("findAllPostcode")
    @Produces(MediaType.TEXT_PLAIN)
    public String findAllPostcode(){
        List<Users> list = findAll();
        String postcodes = "";
        for (Users user:list){
            if (!list.contains(user.getPostcode())){
            postcodes = postcodes + "," + user.getPostcode();
            }
        }
        return postcodes;
    }
    
    @GET
    @Path("findRecentUserid")
    @Produces(MediaType.TEXT_PLAIN)
    public int findRecentUserid(){
        List<Users> user = findAll();
        int userid = 0;
        for (Users users: user){
            if (users.getUserid() > userid){
                userid = users.getUserid();
            }
        }
        return userid;
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
