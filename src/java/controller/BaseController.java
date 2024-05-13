/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.List;

public abstract class BaseController<E, T> {

    protected E entity;
    protected T dao;
    private List<E> entityList;

    private int epp=10;
    private int cp=0;
    private Class<E> entityClass;
    private Class<T> daoClass;

    public BaseController(Class<E> entityClass, Class<T> daoClass) {
        this.entityClass = entityClass;
        this.daoClass = daoClass;
    }

    public int getEpp() {
        return epp;
    }

    public void setEpp(int epp) {
        this.epp = epp;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    
    public E getEntity() {
        if (entity == null) {
            try {
                this.entity = entityClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public T getDao() {
       
        if (dao == null) {
            try {
                this.dao = daoClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        return dao;
    }

    public void setDao(T dao) {
        this.dao = dao;
    }

    public List<E> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<E> entityList) {
        this.entityList = entityList;
    }

    public Class<E> getEntityClass() {
        return entityClass;
    }

    public Class<T> getDaoClass() {
        return daoClass;
    }

    
    
    
    
    
}

