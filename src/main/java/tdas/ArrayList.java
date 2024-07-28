/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tdas;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author DELL
 * @param <E>
 */
public class ArrayList<E> implements List<E>, Serializable {
    private E[] elements;
    private int MAX_SIZE = 100;
    private int effectiveSize;

    public ArrayList() {
        elements = (E[]) new Object[MAX_SIZE];
        effectiveSize = 0;
    }
    
    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    public void clear() {
        if(effectiveSize>0){
            E[] l2=(E[]) new Object[MAX_SIZE];
            elements=l2;
            effectiveSize=0;
        }else{
            System.out.println("Lista vacia");
        }    
    }

    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        }
        if (isFull()) {
            addCapacity();
        }
        elements[effectiveSize] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public E remove(int index) {
        if(index>=effectiveSize || index<0){
            return null;
        }
        E element= elements[index];
        elements[index]=null;
        for(int i=index;i<effectiveSize;i++){
            elements[i]=elements[i+1];
        }
        effectiveSize--;
        return element;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= effectiveSize) {
            return null;
        }
        return elements[index];
    }

    private void addCapacity() {
        MAX_SIZE = MAX_SIZE * 2;
        E[] ne = (E[]) new Object[MAX_SIZE];
        for (int i = 0; i < elements.length; i++) {
            ne[i] = elements[i];
        }
        this.elements = ne;
    }

    private boolean isFull() {
        return effectiveSize == MAX_SIZE;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it=new Iterator<>(){
            int cursor=0;
            @Override
            public boolean hasNext(){
                return cursor<=effectiveSize;
            }

            @Override
            public E next() {
                E e=elements[cursor];
                cursor++;
                return e;
            }
        };
        return it;
    }

}
