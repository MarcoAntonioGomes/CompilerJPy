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
public class _no_const extends No {
	private double valor;

    public _no_const(int id, int line) {
        super(id, line);
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
        
}
