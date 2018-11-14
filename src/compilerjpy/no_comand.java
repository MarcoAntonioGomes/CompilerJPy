/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerjpy;

/**
 *
 * @author marco
 */
public class no_comand extends No{
   private No next;

    public no_comand(int id, int line) {
        super(id, line);
    }

    public No getNext() {
        return next;
    }

    public void setNext(No next) {
        this.next = next;
    }
   
}
