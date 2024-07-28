/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tdas;

/**
 *
 * @author USER
 * @param <E>
 */
public interface List<E> extends Iterable<E> {
    boolean addLast(E element);
    int size();
    boolean isEmpty();
    E get(int index);
    E remove(int index);
}
