/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tdas;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author USER
 */
public class LCD<E> implements List<E> {
    
    private Node<E> first;

    public LCD() {
        this.first = null;
    }

    @Override
    public boolean addLast(E element) {
        if(element == null){
            return false;
        }
        
        Node<E> nodo = new Node(element);
        
        if(this.isEmpty()){
            first = nodo;
            first.setNext(nodo);
            first.setPrev(nodo);
        } else{
            Node<E> lastNode = first.getPrev();
            nodo.setNext(first);
            nodo.setPrev(lastNode);
            lastNode.setNext(nodo);
            first.setPrev(nodo);
            
        }
        
        return true;
    }

    @Override
    public int size() {
        int cont = 0;
        for(E nodo:this){
            cont++;
        }
        
        return cont;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public E get(int index) {
        
        if(index<0 || isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        
        Node<E> node = first;

        int contador = 0;
        while(contador != index){
            node = node.getNext();
            contador++;
        }
        
        return node.getContent();
    }
    
    @Override
    public String toString(){
        String mss = "";
        for(E nodo:this){
            mss+= " " + nodo +  ",";
        }
        
        return mss;
    }
    
    public E getNextElement(int index){
        Node<E> node = first;
        for(int i=0; i<index;i++){
            node = node.getNext();
        }
        return node.getNext().getContent();
    }
    
    public E getPrevElement(int index){
        Node<E> node = first;
        for(int i=0; i<index;i++){
            node = node.getNext();
        }
        return node.getPrev().getContent();
    }
    
    public E getPrevTooElement(int index){
        Node<E> node = first;
        for(int i=0; i<index;i++){
            node = node.getNext();
        }
        node = node.getPrev();
        return node.getPrev().getContent();
    }
    
    public E getNextTooElement(int index){
        Node<E> node = first;
        for(int i=0; i<index;i++){
            node = node.getNext();
        }
        node = node.getNext();
        return node.getNext().getContent();
    }
    
    @Override
    public E remove(int index) {
        
        if(isEmpty()){
            return null;
        }
        
        E elemento;
        
        if(index == 0){
            if(first.getNext() == null){
                elemento = first.getContent();
                first = null;
            }else{
                Node<E> lastNode = first.getPrev();
                Node<E> nextNode = first.getNext();
                
                elemento = first.getContent();
                nextNode.setPrev(lastNode);
                lastNode.setNext(nextNode);
                first = nextNode;
            }
        } else if(index > 0){
            Node<E> nodoEliminar = first;
            int cont = 0;
            while(cont < index){
                nodoEliminar = nodoEliminar.getNext();
                cont++;
                if(nodoEliminar == first){
                    return null; //Indice Invalido
                }
            }
           
            elemento = nodoEliminar.getContent();
            Node<E> lastNode = nodoEliminar.getPrev();
            Node<E> nextNode = nodoEliminar.getNext();
            
            lastNode.setNext(nextNode);
            nextNode.setPrev(lastNode);
            nodoEliminar = nextNode;
            
        } else{
            elemento = null;
        }
        
        
        return elemento;
    }
    
    public int indexOf(E element){
        int i = 0;
        Node<E> node = first;
        for(E e: this){
            if(e.equals(node.getContent())){
                return i;
                
            }
            i++;
        }
        
        return i;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>(){
            Node<E> cursor = first;
            
            @Override
            public boolean hasNext() { 
                return cursor != null;
            }

            @Override
            public E next() {
                if(!hasNext()){
                    throw new NoSuchElementException();
                }
                
                E e = cursor.getContent();
                cursor = cursor.getNext();
                if(cursor == first){
                    cursor = null;
                }
                
                return e;
            }
            
        };
        
        return it;
    }


    
}
