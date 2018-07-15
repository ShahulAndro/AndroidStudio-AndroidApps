package com.android.greendao.insertobject;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by shahulhameed on 14/07/2018.
 */

@Entity
public class Student {

    @Id
    private long id;

    private long studentId;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String email;

    @NotNull
    private String yearOfBirth;

    @NotNull
    private String age;

    @ToOne(joinProperty = "studentId")
    private ContactInfo contactInfo;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1943931642)
    private transient StudentDao myDao;

    @Generated(hash = 1647251011)
    public Student(long id, long studentId, @NotNull String firstname,
            @NotNull String lastname, @NotNull String email,
            @NotNull String yearOfBirth, @NotNull String age) {
        this.id = id;
        this.studentId = studentId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.yearOfBirth = yearOfBirth;
        this.age = age;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getYearOfBirth() {
        return this.yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Generated(hash = 1068781086)
    private transient Long contactInfo__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1530975805)
    public ContactInfo getContactInfo() {
        long __key = this.studentId;
        if (contactInfo__resolvedKey == null
                || !contactInfo__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ContactInfoDao targetDao = daoSession.getContactInfoDao();
            ContactInfo contactInfoNew = targetDao.load(__key);
            synchronized (this) {
                contactInfo = contactInfoNew;
                contactInfo__resolvedKey = __key;
            }
        }
        return contactInfo;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1062547551)
    public void setContactInfo(@NotNull ContactInfo contactInfo) {
        if (contactInfo == null) {
            throw new DaoException(
                    "To-one property 'studentId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.contactInfo = contactInfo;
            studentId = contactInfo.getId();
            contactInfo__resolvedKey = studentId;
        }
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
    @Generated(hash = 1701634981)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStudentDao() : null;
    }
}
