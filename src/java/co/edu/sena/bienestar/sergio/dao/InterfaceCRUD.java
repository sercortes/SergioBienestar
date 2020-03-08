/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bienestar.sergio.dao;

import java.util.ArrayList;

/**
 *
 * @author serfin
 */
public interface InterfaceCRUD {
      public abstract boolean insert(Object t);
    public abstract boolean delete(String id);
    public abstract boolean update(Object t);
    public abstract Object getByID(String id);
    public abstract ArrayList<?> getSomeByID(String id);
    public abstract ArrayList<?> getAll();
    public abstract ArrayList<?> getByWord(String keyword);
}
