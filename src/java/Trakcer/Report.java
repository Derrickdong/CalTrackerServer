/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trakcer;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xdon0010
 */
@Entity
@Table(name = "REPORT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
    , @NamedQuery(name = "Report.findById", query = "SELECT r FROM Report r WHERE r.id = :id")
    , @NamedQuery(name = "Report.findByDate", query = "SELECT r FROM Report r WHERE r.date = :date")
    , @NamedQuery(name = "Report.findByTotalcaloriesconsumed", query = "SELECT r FROM Report r WHERE r.totalcaloriesconsumed = :totalcaloriesconsumed")
    , @NamedQuery(name = "Report.findByTotalcaloriesburrend", query = "SELECT r FROM Report r WHERE r.totalcaloriesburrend = :totalcaloriesburrend")
    , @NamedQuery(name = "Report.findByTotalsteps", query = "SELECT r FROM Report r WHERE r.totalsteps = :totalsteps")
    , @NamedQuery(name = "Report.findBySetcaloriegoal", query = "SELECT r FROM Report r WHERE r.setcaloriegoal = :setcaloriegoal")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "TOTALCALORIESCONSUMED")
    private Integer totalcaloriesconsumed;
    @Column(name = "TOTALCALORIESBURREND")
    private Integer totalcaloriesburrend;
    @Column(name = "TOTALSTEPS")
    private Integer totalsteps;
    @Column(name = "SETCALORIEGOAL")
    private Integer setcaloriegoal;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne
    private Users userid;

    public Report() {
    }

    public Report(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTotalcaloriesconsumed() {
        return totalcaloriesconsumed;
    }

    public void setTotalcaloriesconsumed(Integer totalcaloriesconsumed) {
        this.totalcaloriesconsumed = totalcaloriesconsumed;
    }

    public Integer getTotalcaloriesburrend() {
        return totalcaloriesburrend;
    }

    public void setTotalcaloriesburrend(Integer totalcaloriesburrend) {
        this.totalcaloriesburrend = totalcaloriesburrend;
    }

    public Integer getTotalsteps() {
        return totalsteps;
    }

    public void setTotalsteps(Integer totalsteps) {
        this.totalsteps = totalsteps;
    }

    public Integer getSetcaloriegoal() {
        return setcaloriegoal;
    }

    public void setSetcaloriegoal(Integer setcaloriegoal) {
        this.setcaloriegoal = setcaloriegoal;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trakcer.Report[ id=" + id + " ]";
    }
    
}
