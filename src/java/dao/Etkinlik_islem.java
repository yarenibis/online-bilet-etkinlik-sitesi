/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface Etkinlik_islem<T> {
    void create(T etkinlik);
    void delete(T etkinlik);
    void update(T etkinlik);
    List<T> list(int page, int pageSize);
}
