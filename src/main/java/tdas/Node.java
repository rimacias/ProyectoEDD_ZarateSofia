/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tdas;

/**
 *
 * @author USER
 * @param <E>
 */
public class Node<E> {
    Node<E> prev;
    Node<E> next;
    E content;

    public Node(E content) {
        this.content = content;
    }

    public Node<E> getPrev() {
        return prev;
    }

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }
    
    
}
