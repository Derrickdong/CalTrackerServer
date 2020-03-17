/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Trakcer.Food;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.util.ArrayList;
import java.util.List;
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
@Path("trakcer.food")
public class FoodFacadeREST extends AbstractFacade<Food> {

    @PersistenceContext(unitName = "CalorieTrackerPU")
    private EntityManager em;

    public FoodFacadeREST() {
        super(Food.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Food entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Food entity) {
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
    public Food find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("findByName/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findByName(@PathParam("name") String name){
        Query query = em.createNamedQuery("Food.findByName");
        query.setParameter("name", name);
        return query.getResultList();
    }
    
    @GET
    @Path("findByCategory/{category}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findByCategory(@PathParam("category") String category){
        Query query = em.createNamedQuery("Food.findByCategory");
        query.setParameter("category", category);
        return query.getResultList();
    }
    
    @GET
    @Path("findNameByCategory/{category}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<String> findNameByCategory(@PathParam("category") String category){
        Query query = em.createNamedQuery("Food.findByCategory");
        query.setParameter("category", category);
        List<Food> list = query.getResultList();
        List<String> foodNames = new ArrayList<>();
        for (Food food:list){
            foodNames.add(food.getName());
        }
        return (List<String>)foodNames;
    }
    
    @GET
    @Path("findByCalorieamount/{calorieamount}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findByCalorieamount(@PathParam("calorieamount") int calorieamount){
        Query query = em.createNamedQuery("Food.findByCalorieamount");
        query.setParameter("calorieamount", calorieamount);
        return query.getResultList();
    }
    
    @GET
    @Path("findByServingunit/{servingunit}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findByServingunit(@PathParam("servingunit") String servingunit){
        Query query = em.createNamedQuery("Food.findByServingunit");
        query.setParameter("servingunit", servingunit);
        return query.getResultList();
    }
    
    @GET
    @Path("findByFat/{fat}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Food> findByFat(@PathParam("fat") int fat){
        Query query = em.createNamedQuery("Food.findByFat");
        query.setParameter("fat", fat);
        return query.getResultList();
    }
    
    @GET
    @Path("findAllCategories")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String findAllCategories(){
        List<Food> foods = findAll();
        List<String> categories = new ArrayList<>();
        String category = "";
        for (Food food : foods){
            if (!categories.contains(food.getCategory())){
                category = category+ food.getCategory() + "," ;
                categories.add(food.getCategory());
            }
        }
        return category;
    }
}
