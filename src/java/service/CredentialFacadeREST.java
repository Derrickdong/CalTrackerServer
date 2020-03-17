/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Trakcer.Credential;
import Trakcer.Users;
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
@Path("trakcer.credential")
public class CredentialFacadeREST extends AbstractFacade<Credential> {

    @PersistenceContext(unitName = "CalorieTrackerPU")
    private EntityManager em;

    public CredentialFacadeREST() {
        super(Credential.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Credential entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Credential entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Credential find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("findByUsername/{username}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findByUsername(@PathParam("username")String username){
        Query q = em.createNamedQuery("Credential.findByUsername");
        q.setParameter("username", username);
        return q.getResultList();
    }
    
    @GET
    @Path("findNameByUsername/{username}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String findNameByUsername(@PathParam("username") String username){
        List<Credential> list = findByUsername(username);
        if (list.isEmpty()){
            return null;
        }
        else{
            return list.get(0).getUserid().getName();
        }
    }
    
    @GET
    @Path("findByPasswordhash/{passwordhash}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findByPasswordhash(@PathParam("passwordhash") String passwordhash){
        Query query = em.createNamedQuery("Credential.findByPasswordhash");
        query.setParameter("passwordhash", passwordhash);
        return query.getResultList();
    }
    
    @GET
    @Path("findBySignupdate/{signupdate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findBySignupdate(@PathParam("signupdate") String receivedSignupdate){
        Query query = em.createNamedQuery("Credential.findBySignupdate");
        Date date = dateConverter(receivedSignupdate);
        query.setParameter("signupdate", date);
        return query.getResultList();
    }
    
    //task3d
    @GET
    @Path("findBySignupdateANDEmail/{signupdate}/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findBySignupdateANDEmail(@PathParam("signupdate") String recievedDate, @PathParam("email") String email){
        Query q = em.createNamedQuery("Credential.findBySignupdateANDEmail");
        Date signupdate = dateConverter(recievedDate);
        q.setParameter("signupdate", signupdate);
        q.setParameter("email", email);
        return q.getResultList();
    }
    
    @GET
    @Path("findByUsernameAndPassword/{username}/{password}")
    public List<Credential> findByUsernameAndPassword(@PathParam("username")String username, @PathParam("password")String password){
        Query q  = em.createNamedQuery("Credential.findByUsernameAndPassword");
        q.setParameter("username", username);
        q.setParameter("password", password);
        return q.getResultList();
    }
    
    @GET
    @Path("findUseridByUsername/{username}")
    @Produces(MediaType.TEXT_PLAIN)
    public String findUseridByUsername(@PathParam("username")String username){
        List<Credential> list = findByUsername(username);
        Users user = list.get(0).getUserid();
        return user.getUserid().toString();
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
