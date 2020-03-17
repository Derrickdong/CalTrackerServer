/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trakcer;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author xdon0010
 */
@Entity
@Table(name = "FOOD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f")
    , @NamedQuery(name = "Food.findByFoodid", query = "SELECT f FROM Food f WHERE f.foodid = :foodid")
    , @NamedQuery(name = "Food.findByName", query = "SELECT f FROM Food f WHERE f.name = :name")
    , @NamedQuery(name = "Food.findByCategory", query = "SELECT f FROM Food f WHERE f.category = :category")
    , @NamedQuery(name = "Food.findByCalorieamount", query = "SELECT f FROM Food f WHERE f.calorieamount = :calorieamount")
    , @NamedQuery(name = "Food.findByServingunit", query = "SELECT f FROM Food f WHERE f.servingunit = :servingunit")
    , @NamedQuery(name = "Food.findByServingamount", query = "SELECT f FROM Food f WHERE f.servingamount = :servingamount")
    , @NamedQuery(name = "Food.findByFat", query = "SELECT f FROM Food f WHERE f.fat = :fat")})
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FOODID")
    private Integer foodid;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "CALORIEAMOUNT")
    private Integer calorieamount;
    @Size(max = 255)
    @Column(name = "SERVINGUNIT")
    private String servingunit;
    @Size(max = 255)
    @Column(name = "SERVINGAMOUNT")
    private String servingamount;
    @Column(name = "FAT")
    private Integer fat;
    @OneToMany(mappedBy = "food")
    private Collection<Consumption> consumptionCollection;

    public Food() {
    }

    public Food(Integer foodid) {
        this.foodid = foodid;
    }

    public Integer getFoodid() {
        return foodid;
    }

    public void setFoodid(Integer foodid) {
        this.foodid = foodid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCalorieamount() {
        return calorieamount;
    }

    public void setCalorieamount(Integer calorieamount) {
        this.calorieamount = calorieamount;
    }

    public String getServingunit() {
        return servingunit;
    }

    public void setServingunit(String servingunit) {
        this.servingunit = servingunit;
    }

    public String getServingamount() {
        return servingamount;
    }

    public void setServingamount(String servingamount) {
        this.servingamount = servingamount;
    }

    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    @XmlTransient
    public Collection<Consumption> getConsumptionCollection() {
        return consumptionCollection;
    }

    public void setConsumptionCollection(Collection<Consumption> consumptionCollection) {
        this.consumptionCollection = consumptionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foodid != null ? foodid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Food)) {
            return false;
        }
        Food other = (Food) object;
        if ((this.foodid == null && other.foodid != null) || (this.foodid != null && !this.foodid.equals(other.foodid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trakcer.Food[ foodid=" + foodid + " ]";
    }
    
}
