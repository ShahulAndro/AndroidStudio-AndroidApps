package com.android.greendao.insertlistobject;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Created by shahulhameed on 14/07/2018.
 */

@Entity
public class CompanyDetails {

    @Id
    private String id;

    @NotNull
    private String branchId;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String numberOfEmployees;

    @NotNull
    private String dateTime;

    @ToMany(referencedJoinProperty = "branchId")
    private List<Employee> employeeList;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1269058390)
    private transient CompanyDetailsDao myDao;

    @Generated(hash = 897537350)
    public CompanyDetails(String id, @NotNull String branchId, @NotNull String name,
            @NotNull String address, @NotNull String numberOfEmployees,
            @NotNull String dateTime) {
        this.id = id;
        this.branchId = branchId;
        this.name = name;
        this.address = address;
        this.numberOfEmployees = numberOfEmployees;
        this.dateTime = dateTime;
    }

    @Generated(hash = 1725163804)
    public CompanyDetails() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranchId() {
        return this.branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberOfEmployees() {
        return this.numberOfEmployees;
    }

    public void setNumberOfEmployees(String numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1419788560)
    public List<Employee> getEmployeeList() {
        if (employeeList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EmployeeDao targetDao = daoSession.getEmployeeDao();
            List<Employee> employeeListNew = targetDao._queryCompanyDetails_EmployeeList(id);
            synchronized (this) {
                if (employeeList == null) {
                    employeeList = employeeListNew;
                }
            }
        }
        return employeeList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 738888862)
    public synchronized void resetEmployeeList() {
        employeeList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 456977236)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCompanyDetailsDao() : null;
    }
}
